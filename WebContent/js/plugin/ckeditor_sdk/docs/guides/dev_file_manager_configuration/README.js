Ext.data.JsonP.dev_file_manager_configuration({"guide":"<!--\nCopyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.\nFor licensing, see LICENSE.md.\n-->\n\n\n<h1 id='dev_file_manager_configuration-section-advanced-file-manager-configuration'>Advanced File Manager Configuration</h1>\n<div class='toc'>\n<p><strong>Contents</strong></p>\n<ol>\n<li><a href='#!/guide/dev_file_manager_configuration-section-adding-file-manager-scripts-for-selected-dialog-windows'>Adding File Manager Scripts for Selected Dialog Windows</a></li>\n<li>\n<a href='#!/guide/dev_file_manager_configuration-section-file-manager-window-size'>File Manager Window Size</a></li>\n<li>\n<a href='#!/guide/dev_file_manager_configuration-section-further-reading'>Further Reading</a></li></ol>\n</div>\n\n<p class=\"requirements\">\n    CKEditor can be easily integrated with an external file manager (file browser/uploader) thanks to the <a href=\"https://ckeditor.com/cke4/addon/filebrowser\">File Browser</a> plugin which by default is included in every preset.\n</p>\n\n\n<p>As mentioned in the introductory <a href=\"#!/guide/dev_file_browse_upload\">File Manager Integration</a> article, CKEditor can not only be integrated with an external application providing file browser and uploader capabilities, but the extent of this integration can be fine-tuned to your needs.</p>\n\n<p>This article describes a few options available to adjust the file manager integration to your usage scenario.</p>\n\n<p class=\"tip\">\n    Please note that the names of the file browser and uploader scripts used in this guide are just an example and should be replaced with your custom scripts or the scripts coming from an external tool, like <a href=\"http://cksource.com/ckfinder\">CKFinder</a> or a third-party file manager.\n</p>\n\n\n<h2 id='dev_file_manager_configuration-section-adding-file-manager-scripts-for-selected-dialog-windows'>Adding File Manager Scripts for Selected Dialog Windows</h2>\n\n<p>It is possible to set a separate URL for a selected dialog window (like <strong>Image</strong> or <strong>Link</strong>) by using the dialog window name in the file manager settings:\n<code>config.filebrowser<i>[dialogWindowName]</i>BrowseUrl</code> and <code>config.filebrowser<i>[dialogWindowName]</i>UploadUrl</code>.</p>\n\n<p>For example to set special browse and upload URLs for the <strong>Image Properties</strong> dialog window, use the <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserImageBrowseUrl\" rel=\"CKEDITOR.config-cfg-filebrowserImageBrowseUrl\" class=\"docClass\">config.filebrowserImageBrowseUrl</a> and <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserImageUploadUrl\" rel=\"CKEDITOR.config-cfg-filebrowserImageUploadUrl\" class=\"docClass\">config.filebrowserImageUploadUrl</a> properties, respectively:</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR-method-replace\" rel=\"CKEDITOR-method-replace\" class=\"docClass\">CKEDITOR.replace</a>( 'editor1', {\n    filebrowserBrowseUrl: '/browser/browse.php',\n    filebrowserImageBrowseUrl: '/browser/browse.php?type=Images',\n    filebrowserUploadUrl: '/uploader/upload.php',\n    filebrowserImageUploadUrl: '/uploader/upload.php?type=Images'\n});\n</code></pre>\n\n<p>In the example above, the <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserBrowseUrl\" rel=\"CKEDITOR.config-cfg-filebrowserBrowseUrl\" class=\"docClass\">config.filebrowserBrowseUrl</a> and <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserUploadUrl\" rel=\"CKEDITOR.config-cfg-filebrowserUploadUrl\" class=\"docClass\">config.filebrowserUploadUrl</a> settings will be used by default. In the <strong>Image Properties</strong> dialog window CKEditor will use the <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserImageBrowseUrl\" rel=\"CKEDITOR.config-cfg-filebrowserImageBrowseUrl\" class=\"docClass\">config.filebrowserImageBrowseUrl</a> and <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserImageUploadUrl\" rel=\"CKEDITOR.config-cfg-filebrowserImageUploadUrl\" class=\"docClass\">config.filebrowserImageUploadUrl</a> settings instead.</p>\n\n<h2 id='dev_file_manager_configuration-section-file-manager-window-size'>File Manager Window Size</h2>\n\n<p>The default width of the file manager window in CKEditor is set to 80% of the screen width, while the default height is set to 70% of the screen height.</p>\n\n<p>If for any reasons the default values are not suitable for you, you can adjust them by using <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserWindowWidth\" rel=\"CKEDITOR.config-cfg-filebrowserWindowWidth\" class=\"docClass\">config.filebrowserWindowWidth</a> to change the width and <a href=\"#!/api/CKEDITOR.config-cfg-filebrowserWindowHeight\" rel=\"CKEDITOR.config-cfg-filebrowserWindowHeight\" class=\"docClass\">config.filebrowserWindowHeight</a> to change the height of the file manager window.</p>\n\n<p>To specify the size of the file manager window in pixels, set it to a number (e.g. <code>\"800\"</code>). If you prefer to set the height and width of the window as a percentage value of the screen, do not forget to add the percent sign after the number (e.g. <code>\"60%\"</code>).</p>\n\n<p>The sample below shows some basic configuration code that can be used to insert a CKEditor instance with the file manager paths and window size configured.</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR-method-replace\" rel=\"CKEDITOR-method-replace\" class=\"docClass\">CKEDITOR.replace</a>( 'editor1', {\n    filebrowserBrowseUrl: '/browser/browse.php',\n    filebrowserUploadUrl: '/uploader/upload.php',\n    filebrowserWindowWidth: '640',\n    filebrowserWindowHeight: '480'\n});\n</code></pre>\n\n<h2 id='dev_file_manager_configuration-section-further-reading'>Further Reading</h2>\n\n<p>For more information on integrating CKEditor with a file manager refer to the following articles:</p>\n\n<ul>\n<li><a href=\"#!/guide/dev_file_browse_upload\">File Manager Integration</a></li>\n<li><a href=\"#!/guide/dev_ckfinder_integration\">CKFinder Integration</a></li>\n<li><a href=\"#!/guide/dev_file_browser_api\">File Browser API - Creating a Custom File Manager</a></li>\n<li><a href=\"#!/guide/dev_dialog_add_file_browser\">Adding the File Manager to Dialog Windows</a></li>\n<li><a href=\"#!/guide/dev_file_upload\">Uploading Pasted and Dropped Files</a></li>\n</ul>\n\n","title":"Advanced Configuration","meta_description":"How to configure CKEditor with a file browser and support file uploads.","meta_keywords":"ckeditor, editor, integrate, integration, configure, configuration, file, files, upload, uploading, manage, management, browse, browser, image, images"});