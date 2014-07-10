var restify = require('restify'),
  async = require('async'),
  config = require('./config.json'),
  fs = require('fs'),
  logging = require('./logging'),
  Mailgun = require('mailgun-js'),
  pkgcloud = require('pkgcloud'),
  os = require('os'),
  _ = require('underscore');

// create our API Server
var server = restify.createServer();

// configure for uploading files
server.use(restify.bodyParser({
  mapParams: true,
  mapFiles: true,
  uploadDir: os.tmpdir()
}));

// Configure out logging
var log = logging.getLogger(config.logLevel || 'debug');

// configure our pkgcloud storage client
var client = pkgcloud.storage.createClient(config.pkgcloudConfig);

// wire up logging for pkgcloud
client.on('log::*', logging.logFunction);

// create our mailgun client
var mailgun = new Mailgun({apiKey: config.mailgun.apiKey, domain: config.mailgun.domain});

server.post('/api/sponsorship', function(req, res, next) {

  // Define our standard form values that would need to be returned over the wire
  // in case of an error
  var fields = ['event_name', 'num_attendees', 'start_date', 'end_date', 'location', 'venue',
    'event_url', 'code_of_conduct_url', 'event_twitter_handle', 'type_of_sponsorship',
    'contact_name', 'contact_email',
    'is_online_only', 'speaking_opportunity'];

  // construct our data payload to be used in saving our archive
  var data = _.pick(req.params, fields);

  data = _.extend({
    id: data.event_name + '-' + data.contact_email + '-' + new Date().toISOString()
  }, data);

  // eagerly return to caller
  // TODO Deal with timeouts from Cloud Files
  // see https://github.com/rackerlabs/developer.rackspace.com/issues/529

  res.header('Location', '/community?success=true');
  res.send(302);

  /** this is where the magic happens
   *
   * first, upload the prospectus (if we have one) and the data to cloud files
   *
   * after the response, we send two notification emails, one to the requestor,
   *   and one to rackspace DRG team
   */
  async.series([ uploadProspectus, uploadData ], function(err) {
    if (err) {
      log.error('Error uploading to cloud files', err);
      log.error('Error during save', data);

      // Update json data to reference that it failed to archive for the notification email
      data.failedToArchive = true;

      // Remove the prospectus, if any
      fs.unlink(req.files.prospectus.path);
    }

    log.info('success...');

    async.parallel([ sendNotificationEmail, sendResponseEmail], function(err) {
      if (err) {
        log.error('Unable to send notifications', err);
      }

      // clean up our prospectus, if any
      fs.unlink(req.files.prospectus.path);
    });
  });

  function uploadProspectus(callback) {
    // Skip if we have no prospectus
    if (!req.files || !req.files.prospectus || !req.files.prospectus.name) {
      callback();
      return;
    }

    data.prospectus = req.files.prospectus.name;
    data.prospectus_path = data.id + '-file';

    log.info('Uploading Prospectus...');

    client.upload({
      container: config.containerName,
      remote: data.prospectus_path,
      local: req.files.prospectus.path
    }, callback);
  }

  function uploadData(callback) {
    log.info('Uploading data...');

    var stream = client.upload({
      container: config.containerName,
      remote: data.id,
      headers: {
        'content-type': 'application/json'
      }
    }, callback);

    // Serialize our data payload to json over the wire
    stream.write(JSON.stringify(data, null, '  '));
  }

  function sendNotificationEmail(callback) {
    var emailData = {
      from: config.fromAddress,
      to: config.notificationAddress,
      subject: 'Sponsorship Request: ' + data.event_name,
      text: JSON.stringify(data, null, '  ')
    };

    if (req.files && req.files.prospectus && req.files.prospectus.name) {
      var file = fs.readFileSync(req.files.prospectus.path);

      emailData.attachment = new Mailgun.Attachment(file, req.files.prospectus.name);
    }

    mailgun.messages().send(emailData, callback);
  }

  function sendResponseEmail(callback) {
    var emailData = {
      from: config.fromAddress,
      to: data.contact_email,
      subject: 'Sponsorship Request: ' + data.event_name,
      text: 'Hi!\n\nYou submitted a request through (http://developer.rackspace.com/)[developer.rackspace.com] for sponsorship of a community event or project. 
      Thanks for reaching out to us. A Racker from the Developer Relations Group will contact you in the next few days. You can 
      email us directly at <sponsorships@rackspace.com> if you have questions about the status of your request. If you have technical 
      support questions, please send us an email at <SDK-support@rackspace.com>. We\'re happy to help.\n\nThanks!\nRackspace Developer Relations'
    };

    mailgun.messages().send(emailData, callback);
  }
});

server.listen(8111, function() {
  log.info('Server listening at ' + server.url);
});
