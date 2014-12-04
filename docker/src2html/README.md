# devsite/src2html

This image responsible for compiling site content and assets from `src/docs` and `src/site_source` with Sphinx and Jekyll and producing the final markup. It can also be used to temporarily serve a local preview of the content.

The image contains only the tooling and none of the content. Specifically, it expects the caller to mount volumes for:

 * `/home/publisher/src-sphinx`: source directory for Sphinx content
 * `/home/publisher/src-jekyll`: source directory for Jekyll content
 * `/home/publisher/config`: mount *.yml files in here to configure Jekyll
 * `/home/publisher/_site`: compiled site is written here

Convenient frontends for this image are `script/preview` and `script/src2html`.
