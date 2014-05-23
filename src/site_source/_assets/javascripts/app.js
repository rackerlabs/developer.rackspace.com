// This is the master, all-in-one JavaScript file that's concatenated from the
// others under this directory. Rather than adding JavaScript here, add it
// somewhere else in this directory and it'll be included.

//= require lib/jquery/jquery-1.11.1
//= require lib/jquery/jquery.cookie-1.4.1
//= require lib/bootstrap/collapse
//= require lib/bootstrap/dropdown

(function (window, document, $) {
  var app = window.devsite || {},
    cookieName = 'devsite-language';

  $.extend(app, {
    // TODO move to some kind of utils object or something
    getParameter: (function () {
      var cache = {};
      return function (name) {
        if ('getParameter' in window.location && typeof(window.location.getParameter) === 'function') {
          return window.location.getParameter(name);
        }

        var query = window.location.search.substring(1);
        if (cache[query]) {
          return cache[query][name];
        }

        var kvp = query.split('&'), values = {};
        for (var i = 0; i < kvp.length; i++) {
          var kv = kvp[i].split('=');
          values[kv[0]] = decodeURIComponent(kv[1] || '').replace(/\+/g, ' ');
        }

        cache[query] = values;
        return cache[query][name];
      };
    }()),

    showLanguage: function(language, ignoreSave) {
      this.hideAll();

      var $btn = $('.lang-btn[data-query="' + language + '"]');

      $btn.toggleClass('active');

      $('.' + $btn.data('class')).show();

      if (!ignoreSave) {
        $.cookie(cookieName, language, { path: '/' });
      }
    },

    hideAll: function() {
      // hide all of the code blocks
      $($(".lang-btn").map(function () {
        return '.' + $(this).data('class')
      }).toArray().join(',')).hide();

      // hide the selected btn
      $('.lang-btn').removeClass('active');
    }
  });

  $(document).on('ready', function() {
    if (app.getParameter('lang')) {
      app.showLanguage(app.getParameter('lang'));
    }
    else if ($.cookie(cookieName)) {
      app.showLanguage($.cookie(cookieName));
    }
    else {
      app.showLanguage('shell', true);
    }

    $('.lang-btn').on('click', function() {
      app.showLanguage($(this).data('query'));
    });
  });

}(window, document, jQuery));