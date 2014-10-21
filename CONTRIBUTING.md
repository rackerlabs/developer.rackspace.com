# Contributing to this repository

We welcome contributions to this repository. To make a contribution, please send a pull request (PR) to the "dev" branch of this repository. This is the default behavior so you shouldn't have to do anything special when sending in your PR.

Sometimes you might want to get a PR started but still keep working on it. This way, others will be able to review your work and give you guidance, before it's ready to be merged into the "dev" branch. To indicate this, add the "wip" label to your PR.

## Contribution workflow

Once you send in your contribution PR, the following steps will take place:

1. The PR will be reviewed by members of our team. Our team members may comment on your PR to discuss specific changes. Eventually, you may re-send an updated PR and it will be merged by a member of our team into the "dev" branch of this reposistory.

2. Your contribution will be pushed to our staging web site. We will do a quick visual review of these changes to make sure they look good.

3. Assuming everything looks good, we will merge these changes into the "master" branch of this repository.

4. Your contribution will be pushed to our production web site.

### Adding new support for a programming language/SDK
If you wish to add support for a programming language (for example, support for Go and Gophercloud was added in October, 2014), you will need to perform the following five steps.
1. Add the .highlight-{language_id} (e.g. .highlight-go) class to the CSS file '/src/site_source/_assets/css/components/guides.less'
2. Add the language id to the list of languages in the script '/script/completion'.
3. Edit the page '/src/site_source/_layouts/docs-page.html' to include the language.
4. Update example code in the '/src/docs/*.rst' files to include the new language.
5. Edit the javascript '/src/site_source/_assets/js/pages/docs.js' to include the new language.

## Content guidelines

If you are contributing to the Getting Started documentation in this repository, please review our [Documention Conventions](src/docs/CONTRIBUTING.rst).

If you are contributing content to our Developer focused Blog, please review our [workflow and procedures](src/site_source/CONTRIBUTING.md).
