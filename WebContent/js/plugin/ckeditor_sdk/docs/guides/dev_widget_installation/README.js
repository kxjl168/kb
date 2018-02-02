Ext.data.JsonP.dev_widget_installation({"guide":"<!--\nCopyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.\nFor licensing, see LICENSE.md.\n-->\n\n\n<h1 id='dev_widget_installation-section-installing-widgets'>Installing Widgets</h1>\n<div class='toc'>\n<p><strong>Contents</strong></p>\n<ol>\n<li><a href='#!/guide/dev_widget_installation-section-online-builder-installation'>Online Builder Installation</a><ol>\n<li>\n<a href='#!/guide/dev_widget_installation-section-through-ckbuilder'>Through CKBuilder</a></li>\n<li>\n<a href='#!/guide/dev_widget_installation-section-through-add-on-repository'>Through Add-on Repository</a></li>\n</ol>\n<li>\n<a href='#!/guide/dev_widget_installation-section-manual-installation'>Manual Installation</a></li></ol>\n</div>\n\n<p class=\"requirements\">\n    This feature was introduced in <strong>CKEditor 4.3</strong>. It is provided through optional plugins that are not included in the CKEditor presets available from the <a href=\"https://ckeditor.com/ckeditor-4/download/\">Download</a> site and <a href=\"#!/guide/dev_widget_installation\">need to be added to your custom build</a> with <a href=\"https://ckeditor.com/cke4/builder\">CKBuilder</a>.\n</p>\n\n\n<h2 id='dev_widget_installation-section-online-builder-installation'>Online Builder Installation</h2>\n\n<p>All widget plugins are optional. If you want to add them to your CKEditor 4.3+ build, you can do it in two ways.</p>\n\n<h3 id='dev_widget_installation-section-through-ckbuilder'>Through CKBuilder</h3>\n\n<p>The easiest method is to visit the <a href=\"https://ckeditor.com/cke4/builder\">CKBuilder</a> page and find the widget plugins in the <strong>Available Plugins</strong> list on the right. The following widgets were introduced in CKEditor 4.3 and above:</p>\n\n<ul>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/image2\">Enhanced Image</a></strong> &ndash; A plugin that lets you add captioned images with \"click and drag\" resizing.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/mathjax\">Mathematical Formulas</a></strong> &ndash; A plugin that lets you add mathematical formulas written in TeX.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/placeholder\">Placeholder</a></strong> &ndash; A plugin that lets you create non-editable text fragments.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/codesnippet\">Code Snippet</a></strong> &ndash; A plugin that lets you insert rich code snippets with syntax highlighting into editor content.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/codesnippetgeshi\">Code Snippet GeSHi</a></strong> &ndash; A plugin that lets you insert rich code snippets with GeSHi syntax highlighting engine integrated.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/embed\">Media Embed</a></strong> &ndash; A plugin that lets you embed media resources (videos, images, tweets, etc.) hosted by other services directly in the editor.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/embedsemantic\">Semantic Media Embed</a></strong> &ndash; A plugin that lets you embed media resources with semantic output (videos, images, tweets, etc.) hosted by other services directly in the editor.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/uploadimage\">Upload Image</a></strong> &ndash; A plugin that enables support for uploading images that were dropped or pasted into the editor.</li>\n<li><strong><a href=\"https://ckeditor.com/cke4/addon/uploadwidget\">Upload Widget</a></strong> &ndash; A plugin that implements a base class for creating non-blocking, live upload of files while the user is editing content.</li>\n</ul>\n\n\n<p><img src=\"guides/dev_widget_installation/add_widget_ckbuilder_3.png\" alt=\"Selected Plugins and Available Plugins lists in CKBuilder\" width=\"786\" height=\"491\"></p>\n\n<p>Drag the widget plugin that you want to add to the <strong>Selected Plugins</strong> list on the left. All plugin dependencies will be resolved automatically for you and the required plugins will be added by the builder. When you are happy with your configuration, click the <strong>Download</strong> button at the bottom of the CKBuilder page to download your custom build with widget plugins included.</p>\n\n<p>When you install your custom build, you will see that the additional widget plugins (in this example: <a href=\"https://ckeditor.com/cke4/addon/mathjax\">Mathematical Formulas</a>) are available in your CKEditor.</p>\n\n<p><img src=\"guides/dev_widget_installation/add_widget_ckbuilder_4.png\" alt=\"A custom CKEditor build with the Mathematical Formulas widget\" width=\"528\" height=\"218\"></p>\n\n<h3 id='dev_widget_installation-section-through-add-on-repository'>Through Add-on Repository</h3>\n\n<p>Visit the widget plugin page in the <a href=\"https://ckeditor.com/cke4/addons/plugins/all\">Add-ons Repository</a> and click the \"<strong>Add to my editor</strong>\" button.</p>\n\n<p><img src=\"guides/dev_widget_installation/add_widget_ckbuilder_1.png\" alt=\"Adding a widget plugin to the editor build\" width=\"420\" height=\"186\"></p>\n\n<p>When you are ready, click the \"<strong>Build my editor</strong>\" button on the right to go to CKBuilder. The plugin that you have just added will be counted as \"selected\".</p>\n\n<p><img src=\"guides/dev_plugins/add_plugin_ckbuilder_2.png\" alt=\"Building a custom CKEditor version\" width=\"159\" height=\"234\"></p>\n\n<p>Please note that in CKBuilder all plugin dependencies will be resolved automatically for you. You can fine-tune your build and when you are happy with your configuration, click the <strong>Download</strong> button at the bottom of the CKBuilder page to download your custom build with widget plugins included.</p>\n\n<h2 id='dev_widget_installation-section-manual-installation'>Manual Installation</h2>\n\n<p>Last but not least, you can <a href=\"#!/guide/dev_plugins-section-3\">add widget plugins manually</a> by downloading the <code>.zip</code> packages,  adding them to an existing CKEditor 4.3+ installation, and modifying the editor configuration manually. This is not a recommended solution, though, and should only be used by experienced CKEditor developers. Be mindful of widget dependencies!</p>\n\n<p class=\"tip\">\n    To avoid the manual installation process <a href=\"https://ckeditor.com/cke4/add/plugin\">submit your widget plugins to the Add-ons Repository</a> and encourage third-party developers to do so. In this way you will be able to both give something to the community and get valuable feedback on your work.\n</p>\n\n","title":"Installing Widgets","meta_description":"How to download, install, and use CKEditor widgets.","meta_keywords":"ckeditor, editor, widgets, widget, download, install, installation"});