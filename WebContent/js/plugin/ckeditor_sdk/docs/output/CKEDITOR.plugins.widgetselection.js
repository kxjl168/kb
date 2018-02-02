Ext.data.JsonP.CKEDITOR_plugins_widgetselection({"tagname":"class","name":"CKEDITOR.plugins.widgetselection","alternateClassNames":[],"members":[{"name":"endFiller","tagname":"property","owner":"CKEDITOR.plugins.widgetselection","id":"property-endFiller","meta":{"private":true}},{"name":"fillerAttribute","tagname":"property","owner":"CKEDITOR.plugins.widgetselection","id":"property-fillerAttribute","meta":{"private":true}},{"name":"fillerContent","tagname":"property","owner":"CKEDITOR.plugins.widgetselection","id":"property-fillerContent","meta":{"private":true}},{"name":"fillerTagName","tagname":"property","owner":"CKEDITOR.plugins.widgetselection","id":"property-fillerTagName","meta":{"private":true}},{"name":"startFiller","tagname":"property","owner":"CKEDITOR.plugins.widgetselection","id":"property-startFiller","meta":{"private":true}},{"name":"addFillers","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-addFillers","meta":{}},{"name":"addSelectAllIntegration","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-addSelectAllIntegration","meta":{"private":true}},{"name":"cleanPasteData","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-cleanPasteData","meta":{"private":true}},{"name":"createFiller","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-createFiller","meta":{"private":true}},{"name":"createFillerRegex","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-createFillerRegex","meta":{"private":true}},{"name":"hasFiller","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-hasFiller","meta":{"private":true}},{"name":"isWholeContentSelected","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-isWholeContentSelected","meta":{"private":true}},{"name":"removeFiller","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-removeFiller","meta":{"private":true}},{"name":"removeFillers","tagname":"method","owner":"CKEDITOR.plugins.widgetselection","id":"method-removeFillers","meta":{}}],"aliases":{},"files":[{"filename":"","href":""}],"component":false,"superclasses":[],"subclasses":[],"mixedInto":[],"mixins":[],"parentMixins":[],"requires":[],"uses":[],"html":"<div><div class='doc-contents'>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-endFiller' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-property-endFiller' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-property-endFiller' class='name expandable'>endFiller</a> : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'><p>The end filler element reference.</p>\n</div><div class='long'><p>The end filler element reference.</p>\n</div></div></div><div id='property-fillerAttribute' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-property-fillerAttribute' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-property-fillerAttribute' class='name expandable'>fillerAttribute</a> : String<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>An attribute which identifies the filler element. ...</div><div class='long'><p>An attribute which identifies the filler element.</p>\n<p>Defaults to: <code>&#39;data-cke-filler-webkit&#39;</code></p></div></div></div><div id='property-fillerContent' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-property-fillerContent' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-property-fillerContent' class='name expandable'>fillerContent</a> : String<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>The default content of the filler element. ...</div><div class='long'><p>The default content of the filler element. Note: The filler needs to have <code>visible</code> content.\nUnprintable elements or empty content do not help as a workaround.</p>\n<p>Defaults to: <code>&#39;&amp;nbsp;&#39;</code></p></div></div></div><div id='property-fillerTagName' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-property-fillerTagName' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-property-fillerTagName' class='name expandable'>fillerTagName</a> : String<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Tag name which is used to create fillers. ...</div><div class='long'><p>Tag name which is used to create fillers.</p>\n<p>Defaults to: <code>&#39;div&#39;</code></p></div></div></div><div id='property-startFiller' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-property-startFiller' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-property-startFiller' class='name expandable'>startFiller</a> : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'><p>The start filler element reference.</p>\n</div><div class='long'><p>The start filler element reference.</p>\n</div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-addFillers' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-addFillers' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-addFillers' class='name expandable'>addFillers</a>( <span class='pre'>editable</span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'>Adds a filler before or after a non-editable element at the beginning or the end of the editable. ...</div><div class='long'><p>Adds a filler before or after a non-editable element at the beginning or the end of the <code>editable</code>.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>editable</span> : <a href=\"#!/api/CKEDITOR.editable\" rel=\"CKEDITOR.editable\" class=\"docClass\">CKEDITOR.editable</a><div class='sub-desc'>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-addSelectAllIntegration' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-addSelectAllIntegration' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-addSelectAllIntegration' class='name expandable'>addSelectAllIntegration</a>( <span class='pre'>editor</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Adds an integration for the Select All plugin to the given editor. ...</div><div class='long'><p>Adds an integration for the <a href=\"https://ckeditor.com/cke4/addon/selectall\">Select All</a> plugin to the given <code>editor</code>.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>editor</span> : <a href=\"#!/api/CKEDITOR.editor\" rel=\"CKEDITOR.editor\" class=\"docClass\">CKEDITOR.editor</a><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-cleanPasteData' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-cleanPasteData' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-cleanPasteData' class='name expandable'>cleanPasteData</a>( <span class='pre'>data</span> ) : String<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Removes fillers from the paste data. ...</div><div class='long'><p>Removes fillers from the paste data.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>data</span> : String<div class='sub-desc'>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>String</span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-createFiller' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-createFiller' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-createFiller' class='name expandable'>createFiller</a>( <span class='pre'>[onEnd]</span> ) : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Creates a filler element. ...</div><div class='long'><p>Creates a filler element.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>onEnd</span> : Boolean (optional)<div class='sub-desc'><p>If filler will be placed on end or beginning of the content.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a></span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-createFillerRegex' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-createFillerRegex' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-createFillerRegex' class='name expandable'>createFillerRegex</a>( <span class='pre'>[onEnd]</span> ) : RegExp<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Creates a regular expression which will match the filler HTML in the text. ...</div><div class='long'><p>Creates a regular expression which will match the filler HTML in the text.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>onEnd</span> : Boolean (optional)<div class='sub-desc'><p>Whether a regular expression should be created for the filler at the beginning or\nthe end of the content.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>RegExp</span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-hasFiller' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-hasFiller' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-hasFiller' class='name expandable'>hasFiller</a>( <span class='pre'>editable</span> ) : Boolean<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Checks if there is any filler element in the given editable. ...</div><div class='long'><p>Checks if there is any filler element in the given editable.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>editable</span> : <a href=\"#!/api/CKEDITOR.editable\" rel=\"CKEDITOR.editable\" class=\"docClass\">CKEDITOR.editable</a><div class='sub-desc'>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-isWholeContentSelected' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-isWholeContentSelected' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-isWholeContentSelected' class='name expandable'>isWholeContentSelected</a>( <span class='pre'>editable</span> ) : Boolean<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Checks if the entire content of the given editable is selected. ...</div><div class='long'><p>Checks if the entire content of the given editable is selected.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>editable</span> : <a href=\"#!/api/CKEDITOR.editable\" rel=\"CKEDITOR.editable\" class=\"docClass\">CKEDITOR.editable</a><div class='sub-desc'>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-removeFiller' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-removeFiller' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-removeFiller' class='name expandable'>removeFiller</a>( <span class='pre'>filler, editable</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Removes the specific filler element from the given editable. ...</div><div class='long'><p>Removes the specific filler element from the given editable. If the filler contains any content (typed or pasted),\nit replaces the current editable content. If not, the caret is placed before the first or after the last editable\nelement (depends if the filler was at the beginning or the end).</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>filler</span> : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><div class='sub-desc'>\n</div></li><li><span class='pre'>editable</span> : <a href=\"#!/api/CKEDITOR.editable\" rel=\"CKEDITOR.editable\" class=\"docClass\">CKEDITOR.editable</a><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-removeFillers' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.widgetselection'>CKEDITOR.plugins.widgetselection</span><br/><a href='source/plugin85.html#CKEDITOR-plugins-widgetselection-method-removeFillers' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.widgetselection-method-removeFillers' class='name expandable'>removeFillers</a>( <span class='pre'>editable</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Removes filler elements or updates their references. ...</div><div class='long'><p>Removes filler elements or updates their references.</p>\n\n<p>It will <strong>not remove</strong> filler elements if the whole content is selected, as it would break the\nselection.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>editable</span> : <a href=\"#!/api/CKEDITOR.editable\" rel=\"CKEDITOR.editable\" class=\"docClass\">CKEDITOR.editable</a><div class='sub-desc'>\n</div></li></ul></div></div></div></div></div></div></div>","meta":{}});