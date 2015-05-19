---
layout: post
title: "Weekly Security Link Dump (Week of May 18th)"
date: 2015-05-19 23:59
comments: true
author: Charles Neill
published: true
categories:
    - Security
---

Not a big update this week, but there are definitely some interesting links in here. If you have any feedback, please let me know. Hope you enjoy!

<!-- more -->

# News / Opinions

- [__HTTP/2 RFC released__][http2] - The RFC for HTTP/2 was released recently, and it looks to be quite a major shift from HTTP/1.1 in a lot of ways, including compressed headers, server push, and more. Changes that impact security include mandating TLS >=1.2 and deprecating a large number of cipher suites, among other things. Section 10 is all about the security considerations to keep in mind with HTTP/2, if you're interested in learning more.

# Security Research

- [__Weak homegrown crypto dooms open smart grid protocol__][smart_grid] - Security researchers have discovered serious issues in the cryptography of the Open Smart Grid Protocol. In the words of these researchers, some of the crypto "has been found to be extremely weak, and cannot be assumed to provide any authenticity guarantee whatsoever." Thankfully, the OSGP Alliance has stated that [they intend to update this crypto][smart_grid2], but they will still be using their own algorithms instead of using common standards, which is heavily frowned upon by most experts in cryptography.

- [__How to make two binaries with the same MD5 hash__][md5] - In case you needed any more proof that you shouldn't be using MD5 anymore, especially for verifying hashes of arbitrary binary files, someone has developed [a simple PHP tool][md52] to generate hash collisions, allowing virtually anyone to easily spoof MD5 hashes.

- [__What one may find in robots.txt__][robots] - Security researchers will often use a tool like DirBuster or wfuzz to discover directories or files that might not be linked to in public places during an engagement, but this test is only as good as your wordlist. To create a new wordlist, a French security researcher has harvested thousands of robots.txt files that often explicitly list files that website owners don't want search engine crawlers (or bad guys) to know about, and sorted it by frequency of occurrences. Great way to keep an updated list if you ask me.

# Reference / Tutorials

- [__A week with a Rails security strategy__][rails] - This article presents a nice framework for taking care of the security of your products on a weekly basis. It is geared towards Rails, but you can replace a lot of the Rails-specific pieces with the applicable tools for the language you're using. It includes simple processes like checking for outdated packages that might have security issues and checking out and updating TLS/SSL configurations, but it also contains more holistic processes like thinking about the guts of your application's login system, for example.

- [__Secure yourself, part 1: air-gapped computer, GPG and smartcards__][secure_yourself] - Feeling particularly paranoid today? This guide will tell you how to set up an [air-gapped computer][air_gap], configure a [Yubikey][yubikey], and much more.

# Random Link of the Week

- [__Lily__][lily] - Ever wanted a drone to capture all your motions on HD video? Just throw this thing in the air, and it will fly around tracking you and shooting video automatically. Personally, I'm not looking forward to the day we have thousands of these things flying around everywhere capturing everything we do, but at least they'll look cool.


[http2]: http://www.rfc-editor.org/rfc/rfc7540.txt

[smart_grid]: https://threatpost.com/weak-homegrown-crypto-dooms-open-smart-grid-protocol/112680
[smart_grid2]: https://threatpost.com/open-smart-grid-protocol-alliance-plans-to-fix-its-weak-crypto/112794
[md5]: http://natmchugh.blogspot.com/2015/05/how-to-make-two-binaries-with-same-md5.html
[md52]: https://github.com/natmchugh/longEgg
[robots]: http://thiébaud.fr/robots.txt.html

[rails]: http://bauland42.com/articles/a-week-with-a-rails-security-strategy/
[secure_yourself]: http://viccuad.me/blog/secure-yourself-part-1-airgapped-computer-and-GPG-smartcards/
[air_gap]: http://en.wikipedia.org/wiki/Air_gap_%28networking%29
[yubikey]: https://www.yubico.com/products/yubikey-hardware/

[lily]: https://www.lily.camera/
