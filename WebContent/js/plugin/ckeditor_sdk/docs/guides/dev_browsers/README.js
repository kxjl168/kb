Ext.data.JsonP.dev_browsers({"guide":"<!--\nCopyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.\nFor licensing, see LICENSE.md.\n-->\n\n\n<h1 id='dev_browsers-section-browser-compatibility'>Browser Compatibility</h1>\n<div class='toc'>\n<p><strong>Contents</strong></p>\n<ol>\n<li><a href='#!/guide/dev_browsers-section-officially-supported-browsers'>Officially Supported Browsers</a><ol>\n<li>\n<a href='#!/guide/dev_browsers-section-internet-explorer-8.0-and-9.0'>Internet Explorer 8.0 and 9.0</a></li>\n<li>\n<a href='#!/guide/dev_browsers-section-internet-explorer-9.0-quirks-and-compatibility-modes'>Internet Explorer 9.0 Quirks and Compatibility Modes</a></li>\n<li>\n<a href='#!/guide/dev_browsers-section-a-word-about-mobile-environments'>A Word About Mobile Environments</a></li>\n</ol>\n<li>\n<a href='#!/guide/dev_browsers-section-unsupported-environments'>Unsupported Environments</a></li>\n<li>\n<a href='#!/guide/dev_browsers-section-accessibility-support'>Accessibility Support</a></li>\n<li>\n<a href='#!/guide/dev_browsers-section-further-reading'>Further Reading</a></li></ol>\n</div>\n\n<p>The CKEditor core team uses an approach of <a href=\"http://en.wikipedia.org/wiki/Progressive_enhancement\">progressive enhancement</a> to provide the best possible experience in modern, fully capable browsers as well as in older browsers or browsers which do not support all required features. Therefore, the general level of support differs depending on an environment used and some features may not be available in certain cases.</p>\n\n<h2 id='dev_browsers-section-officially-supported-browsers'>Officially Supported Browsers</h2>\n\n<p>The list of officially supported browsers contains those which the CKEditor core team actively uses during the development and testing. CKEditor may also work in browsers which are not included in the following list, because due to the huge number of various environments available the team is not able to check all of them.</p>\n\n<ul>\n<li><strong>Desktop environments</strong>:\n\n<ul>\n<li><strong>Internet Explorer</strong>:\n\n<ul>\n<li>8.0 and 9.0 &ndash; close to full support,</li>\n<li>10 and 11 &ndash; full support,</li>\n<li>9.0 Quirks Mode and 9.0 Compatibility Mode &ndash; limited support.</li>\n</ul>\n</li>\n<li><strong>Firefox, Chrome, Safari, Microsoft Edge, Opera</strong>:\n\n<ul>\n<li>Latest stable release &ndash; full support.</li>\n</ul>\n</li>\n</ul>\n</li>\n<li><strong>Mobile environments</strong>:\n\n<ul>\n<li><strong>Safari</strong> (iOS 6+) &ndash; close to full support,</li>\n<li><strong>Chrome</strong> (Android) &ndash; close to full support,</li>\n<li><strong>Internet Explorer Mobile</strong> (Windows Phone 8.1+) &ndash; support under evaluation.</li>\n</ul>\n</li>\n</ul>\n\n\n<p><strong>Note:</strong> All browsers are to be supported for web pages that work in Standards Mode (i.e. with a valid Document Type Declaration, for example of HTML5 (<code>&lt;!DOCTYPE html&gt;</code>)) except for IE 9 Quirks that will support unknown doctypes.</p>\n\n<h3 id='dev_browsers-section-internet-explorer-8.0-and-9.0'>Internet Explorer 8.0 and 9.0</h3>\n\n<p>These browsers are generally fully compatible with CKEditor with just a few exceptions. Namely:</p>\n\n<ul>\n<li>There is no support for uploading pasted and dropped files (required APIs are not available).</li>\n<li>In Internet Explorer 8.0 the <a href=\"https://ckeditor.com/cke4/addon/mathjax\">Mathematical Formulas</a>, <a href=\"https://ckeditor.com/cke4/addon/codesnippet\">Code Snippet</a>, <a href=\"https://ckeditor.com/cke4/addon/embed\">Embed</a> and <a href=\"https://ckeditor.com/cke4/addon/embedsemantic\">Semantic Embed</a> widgets are not supported.</li>\n<li>In Internet Explorer 8.0 there is no support for nested <a href=\"#!/guide/dev_widgets\">widgets</a>.</li>\n<li>In Internet Explorer 8.0 there is no support for <a href=\"#!/guide/dev_accessibility_checker\">Accessibility Checker</a>.</li>\n</ul>\n\n\n<h3 id='dev_browsers-section-internet-explorer-9.0-quirks-and-compatibility-modes'>Internet Explorer 9.0 Quirks and Compatibility Modes</h3>\n\n<p>Using both modes is highly unrecommended, because they put the browser in legacy modes which are very limited. Hence, the level of support is limited, too:</p>\n\n<ul>\n<li>In Quirks Mode most of the new features are not going to work including <a href=\"#!/guide/dev_inline\">inline editing</a>, <a href=\"#!/guide/dev_widgets\">widgets</a> and <a href=\"#!/guide/dev_accessibility_checker\">Accessibility Checker</a>. We recommend checking whether particular features work acceptably prior to incorporating them.</li>\n<li>In Compatibility Mode most features work similarly to Internet Explorer 8.0, but the user interface looks worse.</li>\n</ul>\n\n\n<h3 id='dev_browsers-section-a-word-about-mobile-environments'>A Word About Mobile Environments</h3>\n\n<p>CKEditor is generally compatible with <strong>iOS 6+</strong> (iPhone and iPad), though we still think that the usability aspect needs further development.</p>\n\n<p>CKEditor is also compatible with <strong>Chrome for Android</strong> (which is preinstalled on a growing number of Android devices), though in this case usability also needs to be improved.</p>\n\n<p class=\"tip\">Prior to version 4.5 CKEditor was disabled in some versions of Chrome for Android and may be disabled in Internet Explorer Mobile for Windows Phone. You can, however, <a href=\"#!/guide/dev_unsupported_environments\">change CKEditor settings to accept any mobile environment</a> (at your own risk).</p>\n\n\n<p>Full mobile support will be introduced in <strong>CKEditor 5</strong>. We aim to have perfect CKEditor support for most popular mobile platforms, so if you encountered an issue with an environment that is unsupported as of now, please report it on our <a href=\"https://github.com/ckeditor/ckeditor-dev/issues\">GitHub issues page</a>.</p>\n\n<h2 id='dev_browsers-section-unsupported-environments'>Unsupported Environments</h2>\n\n<p>Please note that Internet Explorer 6 is not supported for CKEditor 4. CKEditor 4.1.3 is the last release to support Internet Explorer 7 and Firefox 3.6.</p>\n\n<p>When CKEditor detects an unsupported environment, it will simply not load and the user will be presented with a standard editable element (for example a <code>&lt;textarea&gt;</code> or <code>&lt;div&gt;</code>) that the editor was supposed to replace.</p>\n\n<p>It is possible to skip the compatibility check and <a href=\"#!/guide/dev_unsupported_environments\">enable CKEditor in all environments</a>, including the unsupported ones, but this is an experimental feature aimed at more advanced developers and is highly unrecommended in production environments.</p>\n\n<p><strong>Note:</strong> Prior to version 4.5 CKEditor was disabled in all environments which were not recognized as compatible. Since CKEditor 4.5 the whitelist was changed to a blacklist, so currently CKEditor is only disabled in environments which it is known to be incompatible with (for example Internet Explorer 7 and below and Firefox 3.6 and below). Read more in <a href=\"#!/api/CKEDITOR.env-property-isCompatible\" rel=\"CKEDITOR.env-property-isCompatible\" class=\"docClass\">CKEDITOR.env.isCompatible</a>.</p>\n\n<h2 id='dev_browsers-section-accessibility-support'>Accessibility Support</h2>\n\n<p>Besides the browser support described above we are also introducing the following table to define the list of browsers and assistive technologies supported by CKEditor:</p>\n\n<ul>\n<li><a href=\"http://www.freedomscientific.com/products/fs/JAWS-product-page.asp\">JAWS</a> Latest Stable:\n\n<ul>\n<li><strong>Firefox</strong> Latest Stable on Windows 7, 8 or 10</li>\n</ul>\n</li>\n<li>High Contrast:\n\n<ul>\n<li><strong>Firefox</strong> Latest Stable on Windows 7, 8 or 10</li>\n<li><strong>Internet Explorer</strong> 9+ on Windows 7, 8 or 10</li>\n<li><strong>Microsoft Edge</strong> Latest Stable on Windows 10</li>\n</ul>\n</li>\n</ul>\n\n\n<p>Refer to the <a href=\"#!/guide/dev_a11y\">Accessibility Support in CKEditor</a> article to learn about CKEditor compliance with some well-known accessibility standards and why we treat Firefox + JAWS as our reference environment for testing. You will also get an overview of available accessibility-related features there.</p>\n\n<h2 id='dev_browsers-section-further-reading'>Further Reading</h2>\n\n<p>Refer to the following resources for more information about related features:</p>\n\n<ul>\n<li>The <a href=\"#!/guide/dev_a11y\">Accessibility Support in CKEditor</a> article explains CKEditor compliance with some well-known accessibility standards and gives an overview of available accessibility-related features.</li>\n<li>The <a href=\"#!/guide/dev_unsupported_environments\">Enabling CKEditor in Unsupported Environments (CKEditor &lt;4.4.8)</a> article shows how to make CKEditor &lt;4.4.8 work in any environment that was not listed as officially supported (warning: experimental feature!).</li>\n</ul>\n\n","title":"Browser Compatibility","meta_description":"Browser compatibility and licensing information.","meta_keywords":"ckeditor, editor, wysiwyg, compatibility, browser, support, license, licensing, open, source, premium, free"});