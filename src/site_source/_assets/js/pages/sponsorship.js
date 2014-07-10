(function (window, document, $) {
  var app = window.devsite;

  app.pages.sponsorship = function() {
    // Repopulate form from query string
    if (app.getParameter('error')) {
      var fields = ['event_name', 'num_attendees', 'start_date', 'end_date', 'location', 'venue',
        'event_url', 'code_of_conduct_url', 'event_twitter_handle', 'contact_name', 'contact_email'];

      // First do all of the text fields
      fields.forEach(function(field) {
        if (app.getParameter(field)) {
          $('input[name=' + field + ']').val(app.getParameter(field));
        }
      });

      // Textarea
      if (app.getParameter('type_of_sponsorship')) {
        $('textarea[name=' + 'type_of_sponsorship' + ']').text(app.getParameter('type_of_sponsorship'));
      }

      // Checkboxes
      if (app.getParameter('is_online_only')) {
        $('input[name=' + 'is_online_only' + ']').prop('checked', true);
      }

      // Checkboxes
      if (app.getParameter('speaking_opportunity')) {
        $('input[name=' + 'speaking_opportunity' + ']').prop('checked', true);
      }

      // Show a friendly error message
      $('.sponsorship-result')
        .removeClass('hidden')
        .addClass('alert-danger')
        .html('<strong>Oh, no!</strong> Your sponsorship request didn\'t go through. Can you try again? You can also contact us directly at sponsorships@rackspace.com.');

      // Make sure the file input is highlighted
      $('input[name=prospectus]')
        .closest('.form-group').addClass('has-error');

      $('input[name=prospectus]')
        .closest('.form-group').find('.help-block').removeClass('hidden');
    }
    else if (app.getParameter('success')) {
      // Show a friendly success message
      $('.sponsorship-result')
        .removeClass('hidden')
        .addClass('alert-success')
        .html('<strong>Woo Hoo!</strong> Thanks for submitting your sponsorship request. We\'ll review your information and get back to you soon.');
    }
  };
}(window, document, jQuery));
