Ext.data.JsonP.CKEDITOR_ui_dialog_html({"tagname":"class","name":"CKEDITOR.ui.dialog.html","autodetected":{},"files":[{"filename":"plugin.js","href":"plugin3.html#CKEDITOR-ui-dialog-html"}],"extends":"CKEDITOR.ui.dialog.uiElement","members":[{"name":"eventProcessors","tagname":"property","owner":"CKEDITOR.ui.dialog.uiElement","id":"property-eventProcessors","meta":{}},{"name":"constructor","tagname":"method","owner":"CKEDITOR.ui.dialog.html","id":"method-constructor","meta":{}},{"name":"accessKeyDown","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-accessKeyDown","meta":{}},{"name":"accessKeyUp","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-accessKeyUp","meta":{}},{"name":"disable","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-disable","meta":{}},{"name":"enable","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-enable","meta":{}},{"name":"focus","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-focus","meta":{"chainable":true}},{"name":"getDialog","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-getDialog","meta":{}},{"name":"getElement","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-getElement","meta":{}},{"name":"getInputElement","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-getInputElement","meta":{}},{"name":"getValue","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-getValue","meta":{}},{"name":"isChanged","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-isChanged","meta":{}},{"name":"isEnabled","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-isEnabled","meta":{}},{"name":"isFocusable","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-isFocusable","meta":{}},{"name":"isVisible","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-isVisible","meta":{}},{"name":"registerEvents","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-registerEvents","meta":{"chainable":true}},{"name":"selectParentTab","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-selectParentTab","meta":{"chainable":true}},{"name":"setValue","tagname":"method","owner":"CKEDITOR.ui.dialog.uiElement","id":"method-setValue","meta":{"chainable":true}},{"name":"change","tagname":"event","owner":"CKEDITOR.ui.dialog.uiElement","id":"event-change","meta":{}}],"alternateClassNames":[],"aliases":{},"id":"class-CKEDITOR.ui.dialog.html","component":false,"superclasses":["CKEDITOR.ui.dialog.uiElement"],"subclasses":[],"mixedInto":[],"mixins":[],"parentMixins":[],"requires":[],"uses":[],"html":"<div><pre class=\"hierarchy\"><h4>Hierarchy</h4><div class='subclass first-child'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='docClass'>CKEDITOR.ui.dialog.uiElement</a><div class='subclass '><strong>CKEDITOR.ui.dialog.html</strong></div></div><h4>Files</h4><div class='dependency'><a href='source/plugin3.html#CKEDITOR-ui-dialog-html' target='_blank'>plugin.js</a></div></pre><div class='doc-contents'><p>A dialog window element made from raw HTML code.</p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-eventProcessors' class='member first-child inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-property-eventProcessors' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-property-eventProcessors' class='name expandable'>eventProcessors</a> : Object<span class=\"signature\"></span></div><div class='description'><div class='short'>The event processor list used by\ngetInputElement at UI element\ninstantiation. ...</div><div class='long'><p>The event processor list used by\n<a href=\"#!/api/CKEDITOR.ui.dialog.uiElement-method-getInputElement\" rel=\"CKEDITOR.ui.dialog.uiElement-method-getInputElement\" class=\"docClass\">getInputElement</a> at UI element\ninstantiation. The default list defines three <code>on*</code> events:</p>\n\n<ol>\n<li><code>onLoad</code> - Called when the element's parent dialog opens for the\n first time.</li>\n<li><code>onShow</code> - Called whenever the element's parent dialog opens.</li>\n<li><p><code>onHide</code> - Called whenever the element's parent dialog closes.</p>\n\n<p> // This connects the 'click' event in <a href=\"#!/api/CKEDITOR.ui.dialog.button\" rel=\"CKEDITOR.ui.dialog.button\" class=\"docClass\">CKEDITOR.ui.dialog.button</a> to onClick\n // handlers in the UI element's definitions.\n <a href=\"#!/api/CKEDITOR.ui.dialog.button-property-eventProcessors\" rel=\"CKEDITOR.ui.dialog.button-property-eventProcessors\" class=\"docClass\">CKEDITOR.ui.dialog.button.eventProcessors</a> = <a href=\"#!/api/CKEDITOR.tools-method-extend\" rel=\"CKEDITOR.tools-method-extend\" class=\"docClass\">CKEDITOR.tools.extend</a>( {},\n     CKEDITOR.ui.dialog.uiElement.prototype.eventProcessors,\n     { onClick : function( dialog, func ) { this.on( 'click', func ); } },\n     true\n );</p></li>\n</ol>\n\n</div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-constructor' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.ui.dialog.html'>CKEDITOR.ui.dialog.html</span><br/><a href='source/plugin3.html#CKEDITOR-ui-dialog-html-method-constructor' target='_blank' class='view-source'>view source</a></div><strong class='new-keyword'>new</strong><a href='#!/api/CKEDITOR.ui.dialog.html-method-constructor' class='name expandable'>CKEDITOR.ui.dialog.html</a>( <span class='pre'>dialog, elementDefinition, htmlList</span> ) : <a href=\"#!/api/CKEDITOR.ui.dialog.html\" rel=\"CKEDITOR.ui.dialog.html\" class=\"docClass\">CKEDITOR.ui.dialog.html</a><span class=\"signature\"></span></div><div class='description'><div class='short'>Creates a html class instance. ...</div><div class='long'><p>Creates a html class instance.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>dialog</span> : <a href=\"#!/api/CKEDITOR.dialog\" rel=\"CKEDITOR.dialog\" class=\"docClass\">CKEDITOR.dialog</a><div class='sub-desc'><p>Parent dialog window object.</p>\n</div></li><li><span class='pre'>elementDefinition</span> : <a href=\"#!/api/CKEDITOR.dialog.definition.uiElement\" rel=\"CKEDITOR.dialog.definition.uiElement\" class=\"docClass\">CKEDITOR.dialog.definition.uiElement</a><div class='sub-desc'><p>Element definition.\nAccepted fields:</p>\n\n<ul>\n<li><code>html</code> (Required) HTML code of this element.</li>\n</ul>\n\n</div></li><li><span class='pre'>htmlList</span> : Array<div class='sub-desc'><p>List of HTML code to be added to the dialog's content area.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.ui.dialog.html\" rel=\"CKEDITOR.ui.dialog.html\" class=\"docClass\">CKEDITOR.ui.dialog.html</a></span><div class='sub-desc'>\n</div></li></ul><p>Overrides: <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement-method-constructor\" rel=\"CKEDITOR.ui.dialog.uiElement-method-constructor\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement.constructor</a></p></div></div></div><div id='method-accessKeyDown' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-accessKeyDown' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-accessKeyDown' class='name expandable'>accessKeyDown</a>( <span class='pre'>dialog, key</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>The default handler for a UI element's access key down event, which\ntries to put focus to the UI element. ...</div><div class='long'><p>The default handler for a UI element's access key down event, which\ntries to put focus to the UI element.</p>\n\n<p>Can be overridded in child classes for more sophisticaed behavior.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>dialog</span> : <a href=\"#!/api/CKEDITOR.dialog\" rel=\"CKEDITOR.dialog\" class=\"docClass\">CKEDITOR.dialog</a><div class='sub-desc'><p>The parent dialog object.</p>\n</div></li><li><span class='pre'>key</span> : String<div class='sub-desc'><p>The key combination pressed. Since access keys\nare defined to always include the <code>CTRL</code> key, its value should always\ninclude a <code>'CTRL+'</code> prefix.</p>\n</div></li></ul></div></div></div><div id='method-accessKeyUp' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-accessKeyUp' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-accessKeyUp' class='name expandable'>accessKeyUp</a>( <span class='pre'>dialog, key</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>The default handler for a UI element's access key up event, which\ndoes nothing. ...</div><div class='long'><p>The default handler for a UI element's access key up event, which\ndoes nothing.</p>\n\n<p>Can be overridded in child classes for more sophisticated behavior.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>dialog</span> : <a href=\"#!/api/CKEDITOR.dialog\" rel=\"CKEDITOR.dialog\" class=\"docClass\">CKEDITOR.dialog</a><div class='sub-desc'><p>The parent dialog object.</p>\n</div></li><li><span class='pre'>key</span> : String<div class='sub-desc'><p>The key combination pressed. Since access keys\nare defined to always include the <code>CTRL</code> key, its value should always\ninclude a <code>'CTRL+'</code> prefix.</p>\n</div></li></ul></div></div></div><div id='method-disable' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-disable' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-disable' class='name expandable'>disable</a>( <span class='pre'></span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Disables a UI element. ...</div><div class='long'><p>Disables a UI element.</p>\n</div></div></div><div id='method-enable' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-enable' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-enable' class='name expandable'>enable</a>( <span class='pre'></span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Enables a UI element. ...</div><div class='long'><p>Enables a UI element.</p>\n</div></div></div><div id='method-focus' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-focus' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-focus' class='name expandable'>focus</a>( <span class='pre'></span> ) : <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a><span class=\"signature\"><span class='chainable' >chainable</span></span></div><div class='description'><div class='short'>Puts the focus to the UI object. ...</div><div class='long'><p>Puts the focus to the UI object. Switches tabs if the UI object isn't in the active tab page.</p>\n\n<pre><code>uiElement.focus();\n</code></pre>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a></span><div class='sub-desc'><p>this</p>\n</div></li></ul></div></div></div><div id='method-getDialog' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-getDialog' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-getDialog' class='name expandable'>getDialog</a>( <span class='pre'></span> ) : <a href=\"#!/api/CKEDITOR.dialog\" rel=\"CKEDITOR.dialog\" class=\"docClass\">CKEDITOR.dialog</a><span class=\"signature\"></span></div><div class='description'><div class='short'>Gets the parent dialog object containing this UI element. ...</div><div class='long'><p>Gets the parent dialog object containing this UI element.</p>\n\n<pre><code>var dialog = uiElement.getDialog();\n</code></pre>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.dialog\" rel=\"CKEDITOR.dialog\" class=\"docClass\">CKEDITOR.dialog</a></span><div class='sub-desc'><p>Parent dialog object.</p>\n</div></li></ul></div></div></div><div id='method-getElement' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-getElement' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-getElement' class='name expandable'>getElement</a>( <span class='pre'></span> ) : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"></span></div><div class='description'><div class='short'>Gets the root DOM element of this dialog UI object. ...</div><div class='long'><p>Gets the root DOM element of this dialog UI object.</p>\n\n<pre><code>uiElement.getElement().hide();\n</code></pre>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a></span><div class='sub-desc'><p>Root DOM element of UI object.</p>\n</div></li></ul></div></div></div><div id='method-getInputElement' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-getInputElement' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-getInputElement' class='name expandable'>getInputElement</a>( <span class='pre'></span> ) : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"></span></div><div class='description'><div class='short'>Gets the DOM element that the user inputs values. ...</div><div class='long'><p>Gets the DOM element that the user inputs values.</p>\n\n<p>This function is used by <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement-method-setValue\" rel=\"CKEDITOR.ui.dialog.uiElement-method-setValue\" class=\"docClass\">setValue</a>, <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement-method-getValue\" rel=\"CKEDITOR.ui.dialog.uiElement-method-getValue\" class=\"docClass\">getValue</a> and <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement-method-focus\" rel=\"CKEDITOR.ui.dialog.uiElement-method-focus\" class=\"docClass\">focus</a>. It should\nbe overrided in child classes where the input element isn't the root\nelement.</p>\n\n<pre><code>var rawValue = textInput.getInputElement().$.value;\n</code></pre>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a></span><div class='sub-desc'><p>The element where the user input values.</p>\n</div></li></ul></div></div></div><div id='method-getValue' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-getValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-getValue' class='name expandable'>getValue</a>( <span class='pre'></span> ) : Object<span class=\"signature\"></span></div><div class='description'><div class='short'>Gets the current value of this dialog UI object. ...</div><div class='long'><p>Gets the current value of this dialog UI object.</p>\n\n<pre><code>var myValue = uiElement.getValue();\n</code></pre>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'>Object</span><div class='sub-desc'><p>The current value.</p>\n</div></li></ul></div></div></div><div id='method-isChanged' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-isChanged' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-isChanged' class='name expandable'>isChanged</a>( <span class='pre'></span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'>Tells whether the UI object's value has changed. ...</div><div class='long'><p>Tells whether the UI object's value has changed.</p>\n\n<pre><code>if ( uiElement.isChanged() )\n    confirm( 'Value changed! Continue?' );\n</code></pre>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p><code>true</code> if changed, <code>false</code> if not changed.</p>\n</div></li></ul></div></div></div><div id='method-isEnabled' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-isEnabled' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-isEnabled' class='name expandable'>isEnabled</a>( <span class='pre'></span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'>Determines whether an UI element is enabled or not. ...</div><div class='long'><p>Determines whether an UI element is enabled or not.</p>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p>Whether the UI element is enabled.</p>\n</div></li></ul></div></div></div><div id='method-isFocusable' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-isFocusable' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-isFocusable' class='name expandable'>isFocusable</a>( <span class='pre'></span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'>Determines whether an UI element is focus-able or not. ...</div><div class='long'><p>Determines whether an UI element is focus-able or not.\nFocus-able is defined as being both visible and enabled.</p>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p>Whether the UI element can be focused.</p>\n</div></li></ul></div></div></div><div id='method-isVisible' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-isVisible' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-isVisible' class='name expandable'>isVisible</a>( <span class='pre'></span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'>Determines whether an UI element is visible or not. ...</div><div class='long'><p>Determines whether an UI element is visible or not.</p>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p>Whether the UI element is visible.</p>\n</div></li></ul></div></div></div><div id='method-registerEvents' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-registerEvents' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-registerEvents' class='name expandable'>registerEvents</a>( <span class='pre'>definition</span> ) : <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a><span class=\"signature\"><span class='chainable' >chainable</span></span></div><div class='description'><div class='short'>Registers the on* event handlers defined in the element definition. ...</div><div class='long'><p>Registers the <code>on*</code> event handlers defined in the element definition.</p>\n\n<p>The default behavior of this function is:</p>\n\n<ol>\n<li>If the on* event is defined in the class's eventProcesors list,\n then the registration is delegated to the corresponding function\n in the eventProcessors list.</li>\n<li>If the on* event is not defined in the eventProcessors list, then\n register the event handler under the corresponding DOM event of\n the UI element's input DOM element (as defined by the return value\n of <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement-method-getInputElement\" rel=\"CKEDITOR.ui.dialog.uiElement-method-getInputElement\" class=\"docClass\">getInputElement</a>).</li>\n</ol>\n\n\n<p>This function is only called at UI element instantiation, but can\nbe overridded in child classes if they require more flexibility.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>definition</span> : <a href=\"#!/api/CKEDITOR.dialog.definition.uiElement\" rel=\"CKEDITOR.dialog.definition.uiElement\" class=\"docClass\">CKEDITOR.dialog.definition.uiElement</a><div class='sub-desc'><p>The UI element\ndefinition.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a></span><div class='sub-desc'><p>this</p>\n</div></li></ul></div></div></div><div id='method-selectParentTab' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-selectParentTab' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-selectParentTab' class='name expandable'>selectParentTab</a>( <span class='pre'></span> ) : <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a><span class=\"signature\"><span class='chainable' >chainable</span></span></div><div class='description'><div class='short'>Selects the parent tab of this element. ...</div><div class='long'><p>Selects the parent tab of this element. Usually called by focus() or overridden focus() methods.</p>\n\n<pre><code>focus : function() {\n    this.selectParentTab();\n    // do something else.\n}\n</code></pre>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a></span><div class='sub-desc'><p>this</p>\n</div></li></ul></div></div></div><div id='method-setValue' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin52.html#CKEDITOR-ui-dialog-uiElement-method-setValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-method-setValue' class='name expandable'>setValue</a>( <span class='pre'>value, noChangeEvent</span> ) : <a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a><span class=\"signature\"><span class='chainable' >chainable</span></span></div><div class='description'><div class='short'>Sets the value of this dialog UI object. ...</div><div class='long'><p>Sets the value of this dialog UI object.</p>\n\n<pre><code>uiElement.setValue( 'Dingo' );\n</code></pre>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>value</span> : Object<div class='sub-desc'><p>The new value.</p>\n</div></li><li><span class='pre'>noChangeEvent</span> : Boolean<div class='sub-desc'><p>Internal commit, to supress <code>change</code> event on this element.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.ui.dialog.uiElement\" rel=\"CKEDITOR.ui.dialog.uiElement\" class=\"docClass\">CKEDITOR.ui.dialog.uiElement</a></span><div class='sub-desc'><p>this</p>\n</div></li></ul></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-event'>Events</h3><div class='subsection'><div id='event-change' class='member first-child inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/CKEDITOR.ui.dialog.uiElement' rel='CKEDITOR.ui.dialog.uiElement' class='defined-in docClass'>CKEDITOR.ui.dialog.uiElement</a><br/><a href='source/plugin3.html#CKEDITOR-ui-dialog-uiElement-event-change' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.ui.dialog.uiElement-event-change' class='name expandable'>change</a>( <span class='pre'>evt</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Fired when the value of the uiElement is changed. ...</div><div class='long'><p>Fired when the value of the uiElement is changed.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>evt</span> : <a href=\"#!/api/CKEDITOR.eventInfo\" rel=\"CKEDITOR.eventInfo\" class=\"docClass\">CKEDITOR.eventInfo</a><div class='sub-desc'>\n</div></li></ul></div></div></div></div></div></div></div>","meta":{}});