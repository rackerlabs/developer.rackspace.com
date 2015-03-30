(function (window, document, $) {
  var app = window.devsite;

  var docsDict = {
    '/docs/cloud-servers': [ 'go', 'java', '.net', 'node.js', 'php','python','ruby','shell' ],
    '/docs/cloud-load-balancers': [ 'go', 'java', '.net', 'node.js', 'php','python','ruby','shell' ],
    '/docs/cloud-files': [ 'go', 'java', '.net', 'node.js', 'php','python','ruby','shell' ],
    '/docs/cloud-dns': [ 'go', 'java', '.net', 'node.js', 'php','python','ruby','shell' ],
    '/docs/cloud-block-storage': [ 'go', 'java', '.net', 'node.js', 'php','python','ruby','shell' ],
    '/docs/cloud-databases': [ 'go', 'java', '.net', 'node.js', 'php','python','ruby','shell' ],
    '/docs/auto-scale': [ 'go', 'java', '.net', 'php','python','ruby','shell' ],
    '/docs/cloud-images': [ 'go', 'java', '.net', 'php','python','ruby','shell' ],
    '/docs/cloud-queues': [ 'go', 'java', '.net', 'php','python','ruby','shell' ],
    '/docs/cloud-monitoring': [ 'go', 'java', '.net', 'php','python','ruby','shell' ],
    '/docs/cloud-identity': [ 'go', 'java', '.net', 'php','python','ruby','shell' ],
    '/docs/orchestration': [ 'go','javascript','ruby','shell' ]
  };

  var cookieName = 'devsite-language';

  var showLanguage = function (language, ignoreSave) {
    hideAll();

    var $btn = $('.lang-btn[data-query="' + language + '"]');

    $btn.toggleClass('active');

    $('.' + $btn.data('class')).show();

    if (!ignoreSave) {
      $.cookie(cookieName, language, { path: '/' });
    }
  };

  var hideAll = function () {
    // hide all of the code blocks
    $($(".lang-btn").map(function () {
      return '.' + $(this).data('class')
    }).toArray().join(',')).hide();

    // hide the selected btn
    $('.lang-btn').removeClass('active');
  };

  app.pages.docs = function () {

    var currentPage = '/docs',
      match = false;

    Object.keys(docsDict).forEach(function(page) {
      if (!match && window.location.pathname.indexOf(page) === 0) {
        match = true;
        currentPage = page;
        docsDict[page].forEach(function(item) {
          $('.language-bar .lang-btn[data-query="' + item + '"]').css('display','inline-block');
        });
      }
    });

    if (match) {
      $('.language-list').show();
    }

    if (app.getParameter('lang') && docsDict[currentPage].indexOf(app.getParameter('lang')) >= 0) {
      showLanguage(app.getParameter('lang'));
    }
    else if ($.cookie(cookieName) && docsDict[currentPage].indexOf($.cookie(cookieName)) >= 0) {
      showLanguage($.cookie(cookieName));
    }
    else {
      showLanguage('shell', true);
    }

    $('.lang-btn').on('click', function () {
      showLanguage($(this).data('query'));
    });

  };

}(window, document, jQuery));
