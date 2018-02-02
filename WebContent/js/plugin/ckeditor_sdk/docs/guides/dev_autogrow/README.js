Ext.data.JsonP.dev_autogrow({"guide":"<!--\nCopyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.\nFor licensing, see LICENSE.md.\n-->\n\n\n<h1 id='dev_autogrow-section-automatic-editor-height-adjustment-to-content'>Automatic Editor Height Adjustment to Content</h1>\n<div class='toc'>\n<p><strong>Contents</strong></p>\n<ol>\n<li><a href='#!/guide/dev_autogrow-section-minimum-and-maximum-height'>Minimum and Maximum Height</a></li>\n<li>\n<a href='#!/guide/dev_autogrow-section-height-adjustment-on-startup'>Height Adjustment on Startup</a></li>\n<li>\n<a href='#!/guide/dev_autogrow-section-stylistic-fine-tuning'>Stylistic Fine-tuning</a></li>\n<li>\n<a href='#!/guide/dev_autogrow-section-auto-grow-demo'>Auto Grow Demo</a></li>\n<li>\n<a href='#!/guide/dev_autogrow-section-related-features'>Related Features</a></li></ol>\n</div>\n\n<p class=\"requirements\">\n    This feature is provided through an optional plugin that is not included in the CKEditor presets available from the <a href=\"https://ckeditor.com/ckeditor-4/download/\">Download</a> site and <a href=\"#!/guide/dev_plugins\">needs to be added to your custom build</a> with <a href=\"https://ckeditor.com/cke4/builder\">CKBuilder</a>.\n</p>\n\n\n<p>The optional <a href=\"https://ckeditor.com/cke4/addon/autogrow\">Auto Grow</a> plugin makes it possible to configure CKEditor to automatically expand and shrink vertically depending on the amount and size of content entered in its editing area.</p>\n\n<h2 id='dev_autogrow-section-minimum-and-maximum-height'>Minimum and Maximum Height</h2>\n\n<p>It is possible to fine-tune the automatic editor height adjustment by setting the minimum and maximum height that the editor will shrink and expand to, respectively.</p>\n\n<ul>\n<li>The <a href=\"#!/api/CKEDITOR.config-cfg-autoGrow_minHeight\" rel=\"CKEDITOR.config-cfg-autoGrow_minHeight\" class=\"docClass\">CKEDITOR.config.autoGrow_minHeight</a> option defines the minimum height that the editor will always assume, no matter how much content it includes.</li>\n<li>The <a href=\"#!/api/CKEDITOR.config-cfg-autoGrow_maxHeight\" rel=\"CKEDITOR.config-cfg-autoGrow_maxHeight\" class=\"docClass\">CKEDITOR.config.autoGrow_maxHeight</a> option can be set in order to prevent the situation where huge amounts of content will cause the editor to expand infinitely.</li>\n</ul>\n\n\n<p>In the following example the editor will grow and shrink with the amount of content, but it will always be at least 250 pixels high and will never exceed the height of 600 pixels:</p>\n\n<pre><code>config.extraPlugins = 'autogrow';\nconfig.autoGrow_minHeight = 250;\nconfig.autoGrow_maxHeight = 600;\n</code></pre>\n\n<p>With just two short paragraphs of content the editor will assume the defined minimum auto grow height of 250 pixels, as visible below.</p>\n\n<p><p><img src=\"guides/dev_autogrow/autogrow_01.png\" alt=\"\" width=\"922\" height=\"360\"></p></p>\n\n<p>When you continue adding more text, the editor will start to expand to match the content.</p>\n\n<p><p><img src=\"guides/dev_autogrow/autogrow_02.png\" alt=\"\" width=\"923\" height=\"592\"></p></p>\n\n<p>When even more content is added, the editor will expand until it reaches the height of 600 pixels, which is the value set for the maximum auto grow height. For longer content scrollbars will appear.</p>\n\n<p><p><img src=\"guides/dev_autogrow/autogrow_03.png\" alt=\"\" width=\"923\" height=\"710\"></p></p>\n\n<p>At the same time, when you start deleting the content that you have just entered, you will see that the editor starts shrinking until it reaches the defined minimum auto grow height of 250 pixels.</p>\n\n<h2 id='dev_autogrow-section-height-adjustment-on-startup'>Height Adjustment on Startup</h2>\n\n<p>By default, the editor with the Auto Grow feature enabled will adjust its height once it gets into focus, so the page that includes it will be partly redrawn. You can, however, prevent this behavior by using the <a href=\"#!/api/CKEDITOR.config-cfg-autoGrow_onStartup\" rel=\"CKEDITOR.config-cfg-autoGrow_onStartup\" class=\"docClass\">CKEDITOR.config.autoGrow_onStartup</a> option to make the editor grow the moment it is created, i.e. on page startup.</p>\n\n<pre><code>config.autoGrow_onStartup = true;\n</code></pre>\n\n<p>This will ensure no page redrawing will be needed &mdash; until you start modifying the content.</p>\n\n<h2 id='dev_autogrow-section-stylistic-fine-tuning'>Stylistic Fine-tuning</h2>\n\n<p>An additional <a href=\"#!/api/CKEDITOR.config-cfg-autoGrow_bottomSpace\" rel=\"CKEDITOR.config-cfg-autoGrow_bottomSpace\" class=\"docClass\">CKEDITOR.config.autoGrow_bottomSpace</a> option lets you insert some extra space that will always be added between the content and the editor bottom bar. For example, you can set it to 50 pixels in order to prevent the editor from looking too cramped.</p>\n\n<pre><code>config.autoGrow_bottomSpace = 50;\n</code></pre>\n\n<p>With this setting in place, the 50-pixel-high space below the content will always be preserved. This is visible in the sample as well as the second image above.</p>\n\n<h2 id='dev_autogrow-section-auto-grow-demo'>Auto Grow Demo</h2>\n\n<p>See the <a href=\"../samples/autogrow.html\">working \"Automatic Editor Height Adjustment to Content\" sample</a> that shows how the editor can automatically expand and shrink vertically to fit the content.</p>\n\n<h2 id='dev_autogrow-section-related-features'>Related Features</h2>\n\n<p>Refer to the following resources for more information about editor resizing:</p>\n\n<ul>\n<li>The <a href=\"#!/guide/dev_size\">Setting Editor Size</a> article explains how to set the editor width and height.</li>\n<li>The <a href=\"#!/guide/dev_resize\">Editor Resizing Customization</a> article explains a number of options for classic editor resizing, including resizing the editor on the fly.</li>\n</ul>\n\n","title":"Editor Auto Grow","meta_description":"Automatic editor height adjustment to content.","meta_keywords":"ckeditor, editor, wysiwyg, ui, interface, height, expand, grow, shrink, autogrow, auto, adjustment, adjust, configure, configuration, setup, settings, options, customization, customize, customise, customisation, config, modification, modify, change"});