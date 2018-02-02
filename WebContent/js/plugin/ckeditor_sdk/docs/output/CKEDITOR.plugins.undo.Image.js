Ext.data.JsonP.CKEDITOR_plugins_undo_Image({"tagname":"class","name":"CKEDITOR.plugins.undo.Image","autodetected":{},"files":[{"filename":"plugin.js","href":"plugin44.html#CKEDITOR-plugins-undo-Image"}],"private":true,"members":[{"name":"bookmarks","tagname":"property","owner":"CKEDITOR.plugins.undo.Image","id":"property-bookmarks","meta":{"readonly":true}},{"name":"contents","tagname":"property","owner":"CKEDITOR.plugins.undo.Image","id":"property-contents","meta":{"readonly":true}},{"name":"constructor","tagname":"method","owner":"CKEDITOR.plugins.undo.Image","id":"method-constructor","meta":{}},{"name":"equalsContent","tagname":"method","owner":"CKEDITOR.plugins.undo.Image","id":"method-equalsContent","meta":{}},{"name":"equalsSelection","tagname":"method","owner":"CKEDITOR.plugins.undo.Image","id":"method-equalsSelection","meta":{}}],"alternateClassNames":[],"aliases":{},"id":"class-CKEDITOR.plugins.undo.Image","component":false,"superclasses":[],"subclasses":[],"mixedInto":[],"mixins":[],"parentMixins":[],"requires":[],"uses":[],"html":"<div><pre class=\"hierarchy\"><h4>Files</h4><div class='dependency'><a href='source/plugin44.html#CKEDITOR-plugins-undo-Image' target='_blank'>plugin.js</a></div></pre><div class='doc-contents'><div class='rounded-box private-box'><p><strong>NOTE:</strong> This is a private utility class for internal use by the framework. Don't rely on its existence.</p></div><p>Contains a snapshot of the editor content and selection at a given point in time.</p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-bookmarks' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.undo.Image'>CKEDITOR.plugins.undo.Image</span><br/><a href='source/plugin44.html#CKEDITOR-plugins-undo-Image-property-bookmarks' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.undo.Image-property-bookmarks' class='name expandable'>bookmarks</a> : Object[]<span class=\"signature\"><span class='readonly' >readonly</span></span></div><div class='description'><div class='short'>Bookmarks representing the selection in an image.Array of bookmark2 objects, see CKEDITOR.dom.range.createBookmark2 f...</div><div class='long'><p>Bookmarks representing the selection in an image.Array of bookmark2 objects, see <a href=\"#!/api/CKEDITOR.dom.range-method-createBookmark2\" rel=\"CKEDITOR.dom.range-method-createBookmark2\" class=\"docClass\">CKEDITOR.dom.range.createBookmark2</a> for definition.</p>\n</div></div></div><div id='property-contents' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.undo.Image'>CKEDITOR.plugins.undo.Image</span><br/><a href='source/plugin44.html#CKEDITOR-plugins-undo-Image-property-contents' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.undo.Image-property-contents' class='name expandable'>contents</a> : String<span class=\"signature\"><span class='readonly' >readonly</span></span></div><div class='description'><div class='short'><p>Editor content.</p>\n</div><div class='long'><p>Editor content.</p>\n</div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-constructor' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.undo.Image'>CKEDITOR.plugins.undo.Image</span><br/><a href='source/plugin44.html#CKEDITOR-plugins-undo-Image-method-constructor' target='_blank' class='view-source'>view source</a></div><strong class='new-keyword'>new</strong><a href='#!/api/CKEDITOR.plugins.undo.Image-method-constructor' class='name expandable'>CKEDITOR.plugins.undo.Image</a>( <span class='pre'>editor, [contentsOnly]</span> ) : <a href=\"#!/api/CKEDITOR.plugins.undo.Image\" rel=\"CKEDITOR.plugins.undo.Image\" class=\"docClass\">CKEDITOR.plugins.undo.Image</a><span class=\"signature\"></span></div><div class='description'><div class='short'>Creates an Image class instance. ...</div><div class='long'><p>Creates an Image class instance.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>editor</span> : <a href=\"#!/api/CKEDITOR.editor\" rel=\"CKEDITOR.editor\" class=\"docClass\">CKEDITOR.editor</a><div class='sub-desc'><p>The editor instance on which the image is created.</p>\n</div></li><li><span class='pre'>contentsOnly</span> : Boolean (optional)<div class='sub-desc'><p>If set to <code>true</code>, the image will only contain content without the selection.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.plugins.undo.Image\" rel=\"CKEDITOR.plugins.undo.Image\" class=\"docClass\">CKEDITOR.plugins.undo.Image</a></span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-equalsContent' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.undo.Image'>CKEDITOR.plugins.undo.Image</span><br/><a href='source/plugin44.html#CKEDITOR-plugins-undo-Image-method-equalsContent' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.undo.Image-method-equalsContent' class='name expandable'>equalsContent</a>( <span class='pre'>otherImage</span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>otherImage</span> : <a href=\"#!/api/CKEDITOR.plugins.undo.Image\" rel=\"CKEDITOR.plugins.undo.Image\" class=\"docClass\">CKEDITOR.plugins.undo.Image</a><div class='sub-desc'><p>Image to compare to.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p>Returns <code>true</code> if content in <code>otherImage</code> is the same.</p>\n</div></li></ul></div></div></div><div id='method-equalsSelection' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.undo.Image'>CKEDITOR.plugins.undo.Image</span><br/><a href='source/plugin44.html#CKEDITOR-plugins-undo-Image-method-equalsSelection' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.undo.Image-method-equalsSelection' class='name expandable'>equalsSelection</a>( <span class='pre'>otherImage</span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>otherImage</span> : <a href=\"#!/api/CKEDITOR.plugins.undo.Image\" rel=\"CKEDITOR.plugins.undo.Image\" class=\"docClass\">CKEDITOR.plugins.undo.Image</a><div class='sub-desc'><p>Image to compare to.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p>Returns <code>true</code> if selection in <code>otherImage</code> is the same.</p>\n</div></li></ul></div></div></div></div></div></div></div>","meta":{"private":true}});