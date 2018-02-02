Ext.data.JsonP.dev_uitypes({"guide":"<!--\nCopyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.\nFor licensing, see LICENSE.md.\n-->\n\n\n<h1 id='dev_uitypes-section-editor-user-interface-types'>Editor User Interface Types</h1>\n<div class='toc'>\n<p><strong>Contents</strong></p>\n<ol>\n<li><a href='#!/guide/dev_uitypes-section-fixed-user-interface'>Fixed User Interface</a><ol>\n<li>\n<a href='#!/guide/dev_uitypes-section-fixed-ui-for-inline-editor'>Fixed UI for Inline Editor</a></li>\n</ol>\n<li>\n<a href='#!/guide/dev_uitypes-section-floating-user-interface'>Floating User Interface</a></li>\n<li>\n<a href='#!/guide/dev_uitypes-section-user-interface-types-demo'>User Interface Types Demo</a></li>\n<li>\n<a href='#!/guide/dev_uitypes-section-related-features'>Related Features</a></li></ol>\n</div>\n\n<p>Due to inherent differences between two editor types (classic and inline) dedicated solutions for the editor user interface are required. CKEditor thus comes with two UI versions:</p>\n\n<ul>\n<li><strong>Fixed UI</strong> &ndash; a static user interface used by default in <a href=\"#!/guide/dev_framed\">classic editor</a>.</li>\n<li><strong>Floating UI</strong> &ndash; a dynamic user interface used by default by <a href=\"#!/guide/dev_inline\">inline editor</a>.</li>\n</ul>\n\n\n<h2 id='dev_uitypes-section-fixed-user-interface'>Fixed User Interface</h2>\n\n<p>In fixed UI, used by default in classic editor:</p>\n\n<ul>\n<li>The editor toolbar is always visible.</li>\n<li>The toolbar and bottom bar location is static and does not change.</li>\n<li>The editing area takes a predefined amount of space, regardless of the size of the content inside.</li>\n</ul>\n\n\n<p>The <strong>default behavior of an editor with fixed UI can be customized</strong>, though:</p>\n\n<ul>\n<li><a href=\"#!/guide/dev_toolbarlocation\">Toolbar position</a> can be configured with the <a href=\"#!/api/CKEDITOR.config-cfg-toolbarLocation\" rel=\"CKEDITOR.config-cfg-toolbarLocation\" class=\"docClass\">CKEDITOR.config.toolbarLocation</a> option.</li>\n<li>The editing area size can be adjusted automatically also when fixed UI is used &mdash; check the <a href=\"#!/guide/dev_autogrow\">Auto Grow</a> feature.</li>\n<li>The editor size can be controlled manually by using the resize handle located at the bottom of the interface. This feature is provided through the <a href=\"https://ckeditor.com/cke4/addon/resize\">Editor Resize</a> plugin and is available in Standard and Full editor distributions.</li>\n</ul>\n\n\n<p><img src=\"guides/dev_ckeditor_js_load/classic_example.png\" alt=\"Classic editor with fixed UI\"></p>\n\n<h3 id='dev_uitypes-section-fixed-ui-for-inline-editor'>Fixed UI for Inline Editor</h3>\n\n<p class=\"requirements\">\n    This feature is provided through an optional plugin that is not included in the CKEditor presets available from the <a href=\"https://ckeditor.com/ckeditor-4/download/\">Download</a> site and <a href=\"#!/guide/dev_plugins\">needs to be added to your custom build</a> with <a href=\"https://ckeditor.com/cke4/builder\">CKBuilder</a>.\n</p>\n\n\n<p>Although fixed UI is mostly used in conjunction with classic editor, it is also possible to implement it with inline editor. This is done through the optional <a href=\"https://ckeditor.com/cke4/addon/divarea\">Div Editing Area</a> plugin. <strong>Using this plugin has a few of drawbacks</strong>, though:</p>\n\n<ul>\n<li>Under the hood, the element replaced by CKEditor is hidden automatically and a <code>&lt;div&gt;</code>-based editor is created next to it. This might affect the styles of content inside the editor, e.g. because of the lack of original element ID attribute used by CSS selectors.</li>\n<li>The <code>&lt;div&gt;</code>-based editor is rendered using multiple nested <code>&lt;div&gt;</code> elements. This might affect editor content styles, too.</li>\n<li>Last but not least, the <a href=\"#!/guide/dev_autogrow\">Auto Grow</a> feature is not fully supported in this scenario &mdash; the value of the <a href=\"#!/api/CKEDITOR.config-cfg-autoGrow_minHeight\" rel=\"CKEDITOR.config-cfg-autoGrow_minHeight\" class=\"docClass\">CKEDITOR.config.autoGrow_minHeight</a> option is ignored.</li>\n</ul>\n\n\n<p>If you want to achieve a fixed UI experience with inline editor, consider using the <a href=\"#!/guide/dev_sharedspace\">Shared Toolbar and Bottom Bar</a> feature instead, where these limitations do not exist.</p>\n\n<h2 id='dev_uitypes-section-floating-user-interface'>Floating User Interface</h2>\n\n<p>In floating UI, used by default in inline editor:</p>\n\n<ul>\n<li>The editor toolbar is only shown when the editor is focused.</li>\n<li>The toolbar location changes dynamically to ensure that the toolbar is always available.</li>\n<li>The size of the editing area is determined by the size of the content inside.</li>\n</ul>\n\n\n<p class=\"note\">\n    Floating UI is not available for classic editor.\n</p>\n\n\n<p>If you open a page that contains an inline editor instance you will see that the toolbar is shown on demand and it moves dynamically on the page adjusting itself to the viewport and a page element that is focused.</p>\n\n<p><img src=\"guides/dev_ckeditor_js_load/inline_example.png\" alt=\"Inline editor with floating UI\"></p>\n\n<p>Due to the dynamic nature of floating UI, <strong>some editor features are unavailable</strong>:</p>\n\n<ul>\n<li><a href=\"https://ckeditor.com/cke4/addon/elementspath\">Elements Path</a> is not available &mdash; to use it in an inline editor you need to implement fixed UI with the <a href=\"https://ckeditor.com/cke4/addon/divarea\">Div Editing Area</a> plugin or the <a href=\"#!/guide/dev_sharedspace\">shared toolbar and bottom bar</a> that comes with the <a href=\"https://ckeditor.com/cke4/addon/sharedspace\">Shared Space</a> plugin.</li>\n<li><a href=\"https://ckeditor.com/cke4/addon/sourcearea\">Source Editing Area</a> is not available &mdash; an alternative solution using a <a href=\"https://ckeditor.com/cke4/addon/sourcedialog\">dialog window for source code editing</a> should be used. Refer to the <a href=\"#!/guide/dev_sourcearea\">Source Code Editing</a> documentation.</li>\n<li>The following configuration options that control the size of the editor are ignored: <a href=\"#!/api/CKEDITOR.config-cfg-height\" rel=\"CKEDITOR.config-cfg-height\" class=\"docClass\">CKEDITOR.config.height</a> and <a href=\"#!/api/CKEDITOR.config-cfg-width\" rel=\"CKEDITOR.config-cfg-width\" class=\"docClass\">CKEDITOR.config.width</a>.</li>\n</ul>\n\n\n<h2 id='dev_uitypes-section-user-interface-types-demo'>User Interface Types Demo</h2>\n\n<p>The following samples are available for two CKEditor user interface types:</p>\n\n<ul>\n<li>The <a href=\"../samples/fixedui.html\">Fixed User Interface</a> sample shows the implementation of fixed UI with both classic and inline editor.</li>\n<li>The <a href=\"../samples/floatingui.html\">Floating User Interface</a> sample shows an inline editor instance with its default floating UI.</li>\n</ul>\n\n\n<h2 id='dev_uitypes-section-related-features'>Related Features</h2>\n\n<ul>\n<li><a href=\"#!/guide/dev_sharedspace\">Shared Toolbar and Bottom Bar</a> &ndash; configuring multiple editor instances (both classic and inline) to share the same toolbar and bottom bar.</li>\n<li><a href=\"#!/guide/dev_toolbarlocation\">Toolbar Location</a> &ndash; changing toolbar position for fixed user interface.</li>\n</ul>\n\n","title":"UI Types","meta_description":"Editor user interface types: fixed vs. floating.","meta_keywords":"ckeditor, editor, wysiwyg, ui, interface, toolbar, floating, fixed, inline, classic, configure, configuration, setup, settings, options, customization, customize, customise, customisation, config, modification, modify, change"});