(function (window, document, $) {
  var app = window.devsite;

  app.pages.signup = function () {
    if (window.utag_data && window.utag_data.country === 'GB') {
      $('.signup').attr('href', 'https://cart.rackspace.com/en-gb/cloud/developer-plus');
    }
  };
}(window, document, jQuery));
