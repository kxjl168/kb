Ext.data.JsonP.CKEDITOR_ui_handlerDefinition({"tagname":"class","name":"CKEDITOR.ui.handlerDefinition","autodetected":{},"files":[{"filename":"ui.js","href":"ui.html#CKEDITOR-ui-handlerDefinition"}],"members":[{"name":"contentsElement","tagname":"property","owner":"CKEDITOR.ui.handlerDefinition","id":"property-contentsElement","meta":{"readonly":true}},{"name":"create","tagname":"method","owner":"CKEDITOR.ui.handlerDefinition","id":"method-create","meta":{}}],"alternateClassNames":[],"aliases":{},"id":"class-CKEDITOR.ui.handlerDefinition","short_doc":"Virtual class which just illustrates the features of handler objects to be\npassed to the CKEDITOR.ui.addHandler funct...","component":false,"superclasses":[],"subclasses":["CKEDITOR.ui.button.handler","CKEDITOR.ui.panel.handler","CKEDITOR.ui.richCombo.handler"],"mixedInto":[],"mixins":[],"parentMixins":[],"requires":[],"uses":[],"html":"<div><pre class=\"hierarchy\"><h4>Subclasses</h4><div class='dependency'><a href='#!/api/CKEDITOR.ui.button.handler' rel='CKEDITOR.ui.button.handler' class='docClass'>CKEDITOR.ui.button.handler</a></div><div class='dependency'><a href='#!/api/CKEDITOR.ui.panel.handler' rel='CKEDITOR.ui.panel.handler' class='docClass'>CKEDITOR.ui.panel.handler</a></div><div class='dependency'><a href='#!/api/CKEDITOR.ui.richCombo.handler' rel='CKEDITOR.ui.richCombo.handler' class='docClass'>CKEDITOR.ui.richCombo.handler</a></div><h4>Files</h4><div class='dependency'><a href='source/ui.html#CKEDITOR-ui-handlerDefinition' target='_blank'>ui.js</a></div></pre><div class='doc-contents'><p>Virtual class which just illustrates the features of handler objects to be\npassed to the <a href=\"#!/api/CKEDITOR.ui-method-addHandler\" rel=\"CKEDITOR.ui-method-addHandler\" class=\"docClass\">CKEDITOR.ui.addHandler</a> function.\nThis class is not really a part of the API, so do not call its constructor.</p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-contentsElement' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.ui.handlerDefinition'>CKEDITOR.ui.handlerDefinition</span><br/><a href='source/ui.html#CKEDITOR-ui-handlerDefinition-property-contentsElement' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.handlerDefinition-property-contentsElement' class='name expandable'>contentsElement</a> : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='readonly' >readonly</span></span></div><div class='description'><div class='short'>The element in the host page&#39;s document that contains the editor content. ...</div><div class='long'><p>The element in the <a href=\"#!/api/CKEDITOR-property-document\" rel=\"CKEDITOR-property-document\" class=\"docClass\">host page&#39;s document</a> that contains the editor content.\nIf the <a href=\"#!/guide/dev_uitypes-section-fixed-user-interface\">fixed editor UI</a> is used, then it will be set to\n<code>editor.ui.space( 'contents' )</code> &mdash; i.e. the <code>&lt;div&gt;</code> which contains the editor <code>&lt;iframe&gt;</code> (in case of classic editor)\nor <a href=\"#!/api/CKEDITOR.editable\" rel=\"CKEDITOR.editable\" class=\"docClass\">CKEDITOR.editable</a> (in case of inline editor). Otherwise it is set to the <a href=\"#!/api/CKEDITOR.editable\" rel=\"CKEDITOR.editable\" class=\"docClass\">CKEDITOR.editable</a> itself.</p>\n\n<p>Use the position of this element if you need to position elements placed in the host page's document relatively to the\neditor content.</p>\n\n<pre><code>var editor = CKEDITOR.instances.editor1;\nconsole.log( editor.ui.contentsElement.getName() ); // 'div'\n</code></pre>\n        <p>Available since: <b>4.5</b></p>\n</div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-create' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.ui.handlerDefinition'>CKEDITOR.ui.handlerDefinition</span><br/><a href='source/ui.html#CKEDITOR-ui-handlerDefinition-method-create' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.handlerDefinition-method-create' class='name expandable'>create</a>( <span class='pre'>definition</span> ) : Object<span class=\"signature\"></span></div><div class='description'><div class='short'>Transforms an item definition into a UI item object. ...</div><div class='long'><p>Transforms an item definition into a UI item object.</p>\n\n<pre><code>editorInstance.ui.addHandler( <a href=\"#!/api/CKEDITOR-property-UI_BUTTON\" rel=\"CKEDITOR-property-UI_BUTTON\" class=\"docClass\">CKEDITOR.UI_BUTTON</a>, {\n    create: function( definition ) {\n        return new <a href=\"#!/api/CKEDITOR.ui.button\" rel=\"CKEDITOR.ui.button\" class=\"docClass\">CKEDITOR.ui.button</a>( definition );\n    }\n} );\n</code></pre>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>definition</span> : Object<div class='sub-desc'><p>The item definition.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Object</span><div class='sub-desc'><p>The UI element.\nWe lack the \"UI element\" abstract super class.</p>\n</div></li></ul></div></div></div></div></div></div></div>","meta":{}});