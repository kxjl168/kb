Ext.data.JsonP.widget_sdk_tutorial_2({"guide":"<!--\nCopyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.\nFor licensing, see LICENSE.md.\n-->\n\n\n<h1 id='widget_sdk_tutorial_2-section-creating-a-simple-ckeditor-widget-%28part-2%29'>Creating a Simple CKEditor Widget (Part 2)</h1>\n<div class='toc'>\n<p><strong>Contents</strong></p>\n<ol>\n<li><a href='#!/guide/widget_sdk_tutorial_2-section-prerequisites'>Prerequisites</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-introduction'>Introduction</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-widget-plugin-files'>Widget Plugin Files</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-widget-source-code'>Widget Source Code</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-widget-dialog-window'>Widget Dialog Window</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-widget-dialog-window-definition'>Widget Dialog Window Definition</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-dialog-window-contents'>Dialog Window Contents</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-dialog-window-ui-elements'>Dialog Window UI Elements</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-setting-widget-data'>Setting Widget Data</a><ol>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-dialog-window'>Dialog Window</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-widget-styling'>Widget Styling</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-widget-plugin-file'>Widget Plugin File</a></li>\n</ol>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-full-source-code'>Full Source Code</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-working-example'>Working Example</a></li>\n<li>\n<a href='#!/guide/widget_sdk_tutorial_2-section-simple-box-widget-demo'>Simple Box Widget Demo</a></li></ol>\n</div>\n\n<p>The aim of this tutorial is to demonstrate <strong>how to extend an existing CKEditor widget with a dialog window</strong>. This dialog window will be used to insert a new widget into the editor or modify an existing one and set some basic properties (width, alignment) for it.</p>\n\n<h2 id='widget_sdk_tutorial_2-section-prerequisites'>Prerequisites</h2>\n\n<p><a href=\"#!/guide/dev_widgets\">Widgets are an innovative feature</a> that is available since <strong>CKEditor 4.3</strong>. In order to proceed with this tutorial and create your own widget you need the following:</p>\n\n<ul>\n<li>CKEditor 4.3 and above.</li>\n<li>The <a href=\"https://ckeditor.com/cke4/addon/widget\">Widget plugin</a> along with its dependencies.</li>\n</ul>\n\n\n<h2 id='widget_sdk_tutorial_2-section-introduction'>Introduction</h2>\n\n<p>Instead of creating a new plugin, this time we are going to expand on the functionality of the <a href=\"#!/guide/widget_sdk_tutorial_1\">Simple Box widget plugin created in the previous installment</a> of the widget tutorial series.</p>\n\n<p class=\"tip\">\n    We need to start where we previously left off. You can <a href=\"https://github.com/ckeditor/ckeditor-docs-samples/tree/master/tutorial-simplebox-1\">download the entire plugin folder</a> including the icon, the fully commented source code, and a working sample. If you have any doubts about the installation process, see the <a href=\"https://github.com/ckeditor/ckeditor-docs-samples/blob/master/README.md\">instructions</a>.\n</p>\n\n\n<p>Should you have any questions about the content of the existing plugin and its configuration, refer to the <a href=\"#!/guide/widget_sdk_tutorial_1\">Creating a Simple CKEditor Widget (Part 1)</a> tutorial.</p>\n\n<h2 id='widget_sdk_tutorial_2-section-widget-plugin-files'>Widget Plugin Files</h2>\n\n<p>We continue our work on the file structure that we created for the Simple Box widget plugin in the previous installment of the tutorial.</p>\n\n<p>CKEditor dialog windows can be defined in separate files and then loaded on demand, which improves the overall editor performance. We will name the dialog window file <code>simplebox.js</code> and place it in a newly added <code>dialogs</code> directory, creating the following directory structure for the widget:</p>\n\n<ul>\n<li><code>ckeditor root/</code>\n\n<ul>\n<li><code>plugins/</code>\n\n<ul>\n<li><code>simplebox/</code>\n\n<ul>\n<li><code>dialogs/</code>\n\n<ul>\n<li><code>simplebox.js</code></li>\n</ul>\n</li>\n<li><code>icons/</code>\n\n<ul>\n<li><code>simplebox.png</code></li>\n</ul>\n</li>\n<li><code>plugin.js</code></li>\n</ul>\n</li>\n</ul>\n</li>\n</ul>\n</li>\n</ul>\n\n\n<h2 id='widget_sdk_tutorial_2-section-widget-source-code'>Widget Source Code</h2>\n\n<p>With the following structure ready, it is time to open the <code>plugin.js</code> file in a text editor and to start modifying the source code of our sample widget.</p>\n\n<h2 id='widget_sdk_tutorial_2-section-widget-dialog-window'>Widget Dialog Window</h2>\n\n<p>We will start from registering the widget dialog window by calling the standard <code><a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a></code> method inside the <code><a href=\"#!/api/CKEDITOR.plugins.widget.definition-property-init\" rel=\"CKEDITOR.plugins.widget.definition-property-init\" class=\"docClass\">init</a></code> method of the widget plugin definition.</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR.plugins-method-add\" rel=\"CKEDITOR.plugins-method-add\" class=\"docClass\">CKEDITOR.plugins.add</a>( 'simplebox', {\n    init: function( editor ) {\n        // Existing code...\n\n        <a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a>( 'simplebox', this.path + 'dialogs/simplebox.js' );\n    }\n} );\n</code></pre>\n\n<p>This code registers the <code>simplebox</code> dialog window name, telling the editor to load the dialog window definition from the <code>dialogs/simplebox.js</code> file from the plugin installation directory (<code>this.path</code>).</p>\n\n<p>Additionally, to create the automatic binding between the widget and the dialog window (just like we did for the <a href=\"#!/guide/widget_sdk_tutorial_1-section-6\">widget button in part 1</a> of the tutorial), we need to define the <code><a href=\"#!/api/CKEDITOR.plugins.widget.definition-property-dialog\" rel=\"CKEDITOR.plugins.widget.definition-property-dialog\" class=\"docClass\">dialog</a></code> property of the widget definition inside the <code>editor.widgets.add</code> method. This will ensure that the <code>simplebox</code> dialog window will be opened when creating a new Simple Box widget or editing an existing one.</p>\n\n<pre><code>editor.widgets.add( 'simplebox', {\n    // Code defined before...\n\n    dialog: 'simplebox'\n} );\n</code></pre>\n\n<p>When you load the sample page (that we created in the previous installment of the tutorial) in the browser, you will see that after you click the Simple Box toolbar button nothing happens. The widget will not get inserted into the editor because we changed the widget definition, adding a dialog window to it, and Widget System API is built in a way that forces automatic opening of the widget dialog window if it exists. Since this dialog window has not been defined yet, the editor cannot open it, so we need to work on the <code>simplebox.js</code> file now.</p>\n\n<h2 id='widget_sdk_tutorial_2-section-widget-dialog-window-definition'>Widget Dialog Window Definition</h2>\n\n<p>Open the <code>dialogs/simplebox.js</code> file in a text editor now. For a start, we will repeat the <code><a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a></code> call, but this time we will return the dialog definition to the editor.</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a>( 'simplebox', function( editor ) {\n    return {\n        // Dialog window definition will be added here.\n    };\n} );\n</code></pre>\n\n<p class=\"tip\">\n    See the <a href=\"#!/api/CKEDITOR.dialog.definition\">CKEDITOR.dialog.definition</a> documentation for a full reference on defining editor dialog windows.\n</p>\n\n\n<p>In our case we will give the dialog window a name (<code>simplebox</code>) and use the <code><a href=\"#!/api/CKEDITOR.dialog.definition-property-title\" rel=\"CKEDITOR.dialog.definition-property-title\" class=\"docClass\">title</a></code>, <code><a href=\"#!/api/CKEDITOR.dialog.definition-property-minWidth\" rel=\"CKEDITOR.dialog.definition-property-minWidth\" class=\"docClass\">minWidth</a></code>, and <code><a href=\"#!/api/CKEDITOR.dialog.definition-property-minHeight\" rel=\"CKEDITOR.dialog.definition-property-minHeight\" class=\"docClass\">minHeight</a></code> parameters to define its title and minimum dimensions, respectively.</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a>( 'simplebox', function( editor ) {\n    return {\n        title: 'Edit Simple Box',\n        minWidth: 200,\n        minHeight: 100\n    };\n} );\n</code></pre>\n\n<h2 id='widget_sdk_tutorial_2-section-dialog-window-contents'>Dialog Window Contents</h2>\n\n<p>The dialog window should also contain some content, but in our case one page (tab) with <code><a href=\"#!/api/CKEDITOR.dialog.definition.content-property-id\" rel=\"CKEDITOR.dialog.definition.content-property-id\" class=\"docClass\">id</a></code> of <code>info</code> will be enough. This is added through the <code><a href=\"#!/api/CKEDITOR.dialog.definition-property-contents\" rel=\"CKEDITOR.dialog.definition-property-contents\" class=\"docClass\">contents</a></code> parameter of the dialog window definition. Note that by default CKEditor also adds the standard \"OK\" and \"Cancel\" buttons.</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a>( 'simplebox', function( editor ) {\n    return {\n        title: 'Edit Simple Box',\n        minWidth: 200,\n        minHeight: 100,\n        contents: [\n            {\n                id: 'info',\n                elements: [\n                    // Dialog window UI elements.\n                ]\n            }\n        ]\n    };\n} );\n</code></pre>\n\n<p>The result of this change can be seen immediately. Open the sample page in the browser and click the Simple Box toolbar button in order to open the newly created (and so far empty) \"Edit Simple Box\" dialog window.</p>\n\n<p><p><img src=\"guides/widget_sdk_tutorial_2/simplebox2_dialog_added.png\" alt=\"Edit Simple Box dialog window added\" width=\"230\" height=\"230\"></p></p>\n\n<h2 id='widget_sdk_tutorial_2-section-dialog-window-ui-elements'>Dialog Window UI Elements</h2>\n\n<p>User interface elements added to a dialog window tab are defined in the <code><a href=\"#!/api/CKEDITOR.dialog.definition.content-property-elements\" rel=\"CKEDITOR.dialog.definition.content-property-elements\" class=\"docClass\">elements</a></code> parameter, which is an array of <code><a href=\"#!/api/CKEDITOR.dialog.definition.uiElement\" rel=\"CKEDITOR.dialog.definition.uiElement\" class=\"docClass\">CKEDITOR.dialog.definition.uiElement</a></code> objects.</p>\n\n<p>Our dialog window will contain two UI elements that will let you define two widget properties: <strong>width</strong> and <strong>alignment</strong>.</p>\n\n<p>Since it might be useful to control the widget alignment in the document, we will use a selection field with three predefined options: left, right and center alignment (and an additional \"not set\" option). These are well-known editor alignment options, so we can use the common language strings for their labels which will simplify the internationalization of the widget.</p>\n\n<p>Another UI element will be a text field where you will be able to define the width of the widget.</p>\n\n<p class=\"tip\">\n    Since the width field will be using the <code>width</code> style property, you can use all CSS-allowed measurement units here: <code>px</code>, <code>%</code>, <code>in</code>, <code>cm</code>, <code>mm</code>, <code>em</code>, <code>ex</code>, <code>pt</code>, or <code>pc</code>.\n</p>\n\n\n<pre><code>elements: [\n    {\n        id: 'align',\n        type: 'select',\n        label: 'Align',\n        items: [\n            [ editor.lang.common.notSet, '' ],\n            [ editor.lang.common.alignLeft, 'left' ],\n            [ editor.lang.common.alignRight, 'right' ],\n            [ editor.lang.common.alignCenter, 'center' ]\n        ]\n    },\n    {\n        id: 'width',\n        type: 'text',\n        label: 'Width',\n        width: '50px'\n    }\n]\n</code></pre>\n\n<p>The reloaded sample page will now show you the complete widget dialog window.</p>\n\n<p><p><img src=\"guides/widget_sdk_tutorial_2/simplebox2_dialog_content.png\" alt=\"Widget Dialog Window complete\" width=\"222\" height=\"191\"></p></p>\n\n<p>You can now fill in the dialog window fields. When you click the \"OK\" button, the widget will be added to the document.</p>\n\n<h2 id='widget_sdk_tutorial_2-section-setting-widget-data'>Setting Widget Data</h2>\n\n<p>You might notice, however, that the values that you defined in the dialog window are not really applied to the widget. And when you double click the widget, both dialog window fields will be back at their default values.</p>\n\n<p>This is because our widget definition still misses the logic that would pass the data between the widget and the dialog window that creates and edits it. We will need to add this logic both in the dialog window definition and in the widget definition.</p>\n\n<h3 id='widget_sdk_tutorial_2-section-dialog-window'>Dialog Window</h3>\n\n<p>Let us start with the dialog window. We will need to define the <code>setup</code> and <code>commit</code> functions for both fields. The <code><a href=\"#!/api/CKEDITOR.dialog.definition.uiElement-property-setup\" rel=\"CKEDITOR.dialog.definition.uiElement-property-setup\" class=\"docClass\">setup</a></code> function is invoked when opening the widget dialog window and populating the values in the dialog window fields with data already present in the widget. It uses the <code><a href=\"#!/api/CKEDITOR.dom.element-method-setValue\" rel=\"CKEDITOR.dom.element-method-setValue\" class=\"docClass\">setValue</a></code> method to set the value of the field to the value present in widget data.</p>\n\n<p>The <code><a href=\"#!/api/CKEDITOR.dialog.definition.uiElement-property-commit\" rel=\"CKEDITOR.dialog.definition.uiElement-property-commit\" class=\"docClass\">commit</a></code> function is called when setting or changing the data in the widget, when a dialog window is being closed and the changes are confirmed by using the \"OK\" button. It uses the <code><a href=\"#!/api/CKEDITOR.plugins.widget-method-setData\" rel=\"CKEDITOR.plugins.widget-method-setData\" class=\"docClass\">widget.setData()</a></code> method to set the widget data to the value given in a dialog window field.</p>\n\n<p class=\"tip\">\n    Note that the dialog window and the widget definition must use the same data properties (in this case, <code>width</code> and <code>align</code> with the exact same values, i.e. <code>left</code>, <code>right</code>, and <code>center</code>)!\n</p>\n\n\n<pre><code>elements: [\n    {\n        id: 'align',\n        type: 'select',\n        label: 'Align',\n        items: [\n            [ editor.lang.common.notSet, '' ],\n            [ editor.lang.common.alignLeft, 'left' ],\n            [ editor.lang.common.alignRight, 'right' ],\n            [ editor.lang.common.alignCenter, 'center' ]\n        ],\n        setup: function( widget ) {\n            this.setValue( widget.data.align );\n        },\n        commit: function( widget ) {\n            widget.setData( 'align', this.getValue() );\n        }\n    },\n    {\n        id: 'width',\n        type: 'text',\n        label: 'Width',\n        width: '50px',\n        setup: function( widget ) {\n            this.setValue( widget.data.width );\n        },\n        commit: function( widget ) {\n            widget.setData( 'width', this.getValue() );\n        }\n    }\n]\n</code></pre>\n\n<p class=\"tip\">\n    You might notice that the Dialog Window API states that setup and commit functions need to be complemented by <code>setupContent</code> and <code>commitContent</code> dialog window methods. However, you do not need to add these manually to your code as the Widget API does that for you.\n</p>\n\n\n<p>The dialog window definition is now complete, so let us go back to the <code>plugin.js</code> file. Even though the values are not used by the widget after you insert it, when you double click it, the dialog window will show that the values were indeed saved.</p>\n\n<h3 id='widget_sdk_tutorial_2-section-widget-styling'>Widget Styling</h3>\n\n<p>As <a href=\"#!/guide/widget_sdk_tutorial_1-section-widget-styling\">explained in the first part of the tutorial</a>, widget styling <a href=\"#!/guide/plugin_sdk_styles\">needs to be tackled</a> according to your usage scenario (classic vs inline editor).</p>\n\n<p>To simplify the tutorial, let us assume you are using the <a href=\"#!/guide/dev_framed\">classic editor</a>. The new widget properties (width and alignment) will be using the <code>width</code> CSS style property and custom alignment classes. You need to add these classes to your default <code>contents.css</code> file.</p>\n\n<pre><code>.simplebox.align-right {\n    float: right;\n}\n.simplebox.align-left {\n    float: left;\n}\n.simplebox.align-center {\n    margin-left: auto;\n    margin-right: auto;\n}\n</code></pre>\n\n<h3 id='widget_sdk_tutorial_2-section-widget-plugin-file'>Widget Plugin File</h3>\n\n<p>To prevent the editor's content filtering system from removing the new widget properties we must also adjust the <code><a href=\"#!/api/CKEDITOR.plugins.widget.definition-property-allowedContent\" rel=\"CKEDITOR.plugins.widget.definition-property-allowedContent\" class=\"docClass\">allowedContent</a></code> definition:</p>\n\n<pre><code>editor.widgets.add( 'simplebox', {\n    allowedContent:\n        'div(!simplebox,align-left,align-right,align-center){width};' +\n        'div(!simplebox-content); h2(!simplebox-title)',\n} );\n</code></pre>\n\n<p>When a widget is being initialized we need to read its data (<code>align</code> and <code>width</code>) from DOM and set this data by using the <code>widget.setData()</code> method. This is done inside the <code>init</code> property of the widget definition in the <code>editor.widgets.add</code> method that may contain any code that needs to be executed when DOM is available.</p>\n\n<p>In this case we will get the value of the <code>width</code> CSS property from the widget element and if it exists, assign it to the <code>width</code> data property of the widget by using the <code>setData()</code> method. We will then check if the <code>align-left</code>, <code>align-right</code> or <code>align-center</code> classes exist for the widget element and if this is the case, assign appropriate <code>left</code>, <code>right</code> or <code>center</code> values to the <code>align</code> data property of the widget.</p>\n\n<pre><code>editor.widgets.add( 'simplebox', {\n    // Code defined before...\n\n    init: function() {\n        var width = this.element.getStyle( 'width' );\n        if ( width )\n            this.setData( 'width', width );\n        if ( this.element.hasClass( 'align-left' ) )\n            this.setData( 'align', 'left' );\n        if ( this.element.hasClass( 'align-right' ) )\n            this.setData( 'align', 'right' );\n        if ( this.element.hasClass( 'align-center' ) )\n            this.setData( 'align', 'center' );\n    }\n} );\n</code></pre>\n\n<p>This allows us to read the properties that are already in DOM and set them for an existing widget. We must now make sure we are also able to add a new widget or modify an existing one by setting its data and updating its view in the editor. We can do this in the dedicated <code><a href=\"#!/api/CKEDITOR.plugins.widget.definition-property-data\" rel=\"CKEDITOR.plugins.widget.definition-property-data\" class=\"docClass\">data</a></code> property of the widget definition inside the <code>editor.widgets.add</code> method.</p>\n\n<p>The function we are going to define in the <code>data</code> property of the widget definition will be executed every time the widget data is changed. Widget data will be changed by using the <code>widget.setData()</code> method, which we previously added to <code>commit</code> functions of the dialog window fields.</p>\n\n<p>First we will check whether the <code>width</code> widget data property was set. If it does not exist, we remove the <code>width</code> CSS style from the widget main element in the DOM (<code>div.simplebox</code>) by using the <code><a href=\"#!/api/CKEDITOR.dom.element-method-removeStyle\" rel=\"CKEDITOR.dom.element-method-removeStyle\" class=\"docClass\">removeStyle()</a></code> method. If the <code>width</code> data property was set, we assign its value to the <code>width</code> CSS style property of the widget main element by using the <code><a href=\"#!/api/CKEDITOR.dom.element-method-setStyle\" rel=\"CKEDITOR.dom.element-method-setStyle\" class=\"docClass\">setStyle()</a></code> method.</p>\n\n<p>As for alignment classes, the simplest approach will be to remove them from the main widget element by using the <code><a href=\"#!/api/CKEDITOR.dom.element-method-removeClass\" rel=\"CKEDITOR.dom.element-method-removeClass\" class=\"docClass\">removeClass()</a></code> method and set an appropriate one read from the widget <code>align</code> data property.</p>\n\n<pre><code>editor.widgets.add( 'simplebox', {\n    // Code defined before…\n    data: function() {\n\n        if ( this.data.width == '' )\n            this.element.removeStyle( 'width' );\n        else\n            this.element.setStyle( 'width', this.data.width );\n\n        this.element.removeClass( 'align-left' );\n        this.element.removeClass( 'align-right' );\n        this.element.removeClass( 'align-center' );\n        if ( this.data.align )\n            this.element.addClass( 'align-' + this.data.align );\n    }\n} );\n</code></pre>\n\n<p>This completes the changes that are needed for the updated widget to work.</p>\n\n<h2 id='widget_sdk_tutorial_2-section-full-source-code'>Full Source Code</h2>\n\n<p>The full contents of the <code>simplebox/plugin.js</code> file is as follows:</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR.plugins-method-add\" rel=\"CKEDITOR.plugins-method-add\" class=\"docClass\">CKEDITOR.plugins.add</a>( 'simplebox', {\n    requires: 'widget',\n\n    icons: 'simplebox',\n\n    init: function( editor ) {\n        <a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a>( 'simplebox', this.path + 'dialogs/simplebox.js' );\n\n        editor.widgets.add( 'simplebox', {\n\n            button: 'Create a simple box',\n\n            template:\n                '&lt;div class=\"simplebox\"&gt;' +\n                    '&lt;h2 class=\"simplebox-title\"&gt;Title&lt;/h2&gt;' +\n                    '&lt;div class=\"simplebox-content\"&gt;&lt;p&gt;Content...&lt;/p&gt;&lt;/div&gt;' +\n                '&lt;/div&gt;',\n\n            editables: {\n                title: {\n                    selector: '.simplebox-title',\n                    allowedContent: 'br strong em'\n                },\n                content: {\n                    selector: '.simplebox-content',\n                    allowedContent: 'p br ul ol li strong em'\n                }\n            },\n\n            allowedContent:\n                'div(!simplebox,align-left,align-right,align-center){width};' +\n                'div(!simplebox-content); h2(!simplebox-title)',\n\n            requiredContent: 'div(simplebox)',\n\n            dialog: 'simplebox',\n\n            upcast: function( element ) {\n                return element.name == 'div' &amp;&amp; element.hasClass( 'simplebox' );\n            },\n\n            init: function() {\n                var width = this.element.getStyle( 'width' );\n                if ( width )\n                    this.setData( 'width', width );\n                if ( this.element.hasClass( 'align-left' ) )\n                    this.setData( 'align', 'left' );\n                if ( this.element.hasClass( 'align-right' ) )\n                    this.setData( 'align', 'right' );\n                if ( this.element.hasClass( 'align-center' ) )\n                    this.setData( 'align', 'center' );\n            },\n\n            data: function() {\n\n                if ( this.data.width == '' )\n                    this.element.removeStyle( 'width' );\n                else\n                    this.element.setStyle( 'width', this.data.width );\n\n                this.element.removeClass( 'align-left' );\n                this.element.removeClass( 'align-right' );\n                this.element.removeClass( 'align-center' );\n                if ( this.data.align )\n                    this.element.addClass( 'align-' + this.data.align );\n            }\n        } );\n    }\n} );\n</code></pre>\n\n<p>This is the complete source code of the <code>simplebox/dialogs/simplebox.js</code> dialog window file:</p>\n\n<pre><code><a href=\"#!/api/CKEDITOR.dialog-static-method-add\" rel=\"CKEDITOR.dialog-static-method-add\" class=\"docClass\">CKEDITOR.dialog.add</a>( 'simplebox', function( editor ) {\n    return {\n        title: 'Edit Simple Box',\n        minWidth: 200,\n        minHeight: 100,\n        contents: [\n            {\n                id: 'info',\n                elements: [\n                    {\n                        id: 'align',\n                        type: 'select',\n                        label: 'Align',\n                        items: [\n                            [ editor.lang.common.notSet, '' ],\n                            [ editor.lang.common.alignLeft, 'left' ],\n                            [ editor.lang.common.alignRight, 'right' ],\n                            [ editor.lang.common.alignCenter, 'center' ]\n                        ],\n                        setup: function( widget ) {\n                            this.setValue( widget.data.align );\n                        },\n                        commit: function( widget ) {\n                            widget.setData( 'align', this.getValue() );\n                        }\n                    },\n                    {\n                        id: 'width',\n                        type: 'text',\n                        label: 'Width',\n                        width: '50px',\n                        setup: function( widget ) {\n                            this.setValue( widget.data.width );\n                        },\n                        commit: function( widget ) {\n                            widget.setData( 'width', this.getValue() );\n                        }\n                    }\n                ]\n            }\n        ]\n    };\n} );\n</code></pre>\n\n<p>The following are all styles needed by the widget that should be added to your <code>contents.css</code> file:</p>\n\n<pre><code>.simplebox {\n    padding: 8px;\n    margin: 10px;\n    background: #eee;\n    border-radius: 8px;\n    border: 1px solid #ddd;\n    box-shadow: 0 1px 1px #fff inset, 0 -1px 0px #ccc inset;\n}\n.simplebox-title, .simplebox-content {\n    box-shadow: 0 1px 1px #ddd inset;\n    border: 1px solid #cccccc;\n    border-radius: 5px;\n    background: #fff;\n}\n.simplebox-title {\n    margin: 0 0 8px;\n    padding: 5px 8px;\n}\n.simplebox-content {\n    padding: 0 8px;\n}\n.simplebox.align-right {\n    float: right;\n}\n.simplebox.align-left {\n    float: left;\n}\n.simplebox.align-center {\n    margin-left: auto;\n    margin-right: auto;\n}\n</code></pre>\n\n<p class=\"tip\">\n    You can also <a href=\"https://github.com/ckeditor/ckeditor-docs-samples/tree/master/tutorial-simplebox-2\">download the entire plugin folder</a> including the icon, the fully commented source code, and a working sample. If you have any doubts about the installation process, see the <a href=\"https://github.com/ckeditor/ckeditor-docs-samples/blob/master/README.md\">instructions</a>.\n</p>\n\n\n<h2 id='widget_sdk_tutorial_2-section-working-example'>Working Example</h2>\n\n<p>You can reload the sample page now. When you click the Simple Box toolbar button, the widget dialog window will be displayed. After you fill the fields and click \"OK\", a widget instance using the defined width and alignment values will be inserted into the document.</p>\n\n<p>If you double click an existing widget instance, the dialog window will open again and current width and alignment values will be shown. After you modify them and click \"OK\", the widget instance in the editing area will be updated.</p>\n\n<p><p><img src=\"guides/widget_sdk_tutorial_2/simplebox2_example1.png\" alt=\"Updated Simple Box widget\" width=\"968\" height=\"496\"></p></p>\n\n<h2 id='widget_sdk_tutorial_2-section-simple-box-widget-demo'>Simple Box Widget Demo</h2>\n\n<p>See the <a href=\"../samples/simplebox.html\">working \"SimpleBox (Creating a Custom Widget)\" sample</a> that shows the final version of the Simple Box widget integrated with an editor instance.</p>\n","title":"Widget Tutorial (Part 2)","meta_description":"CKEditor widget tutorial, part 2.","meta_keywords":"ckeditor, editor, wysiwyg, widgets, widget, plugin, plugins, tutorial, sample, example, sdk"});