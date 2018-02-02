Ext.data.JsonP.CKEDITOR_plugins_notification_area({"tagname":"class","name":"CKEDITOR.plugins.notification.area","autodetected":{},"files":[{"filename":"plugin.js","href":"plugin29.html#CKEDITOR-plugins-notification-area"}],"since":"4.5","private":true,"members":[{"name":"_changeBuffer","tagname":"property","owner":"CKEDITOR.plugins.notification.area","id":"property-_changeBuffer","meta":{"private":true}},{"name":"_notificationMargin","tagname":"property","owner":"CKEDITOR.plugins.notification.area","id":"property-_notificationMargin","meta":{"private":true}},{"name":"_notificationWidth","tagname":"property","owner":"CKEDITOR.plugins.notification.area","id":"property-_notificationWidth","meta":{"private":true}},{"name":"_uiBuffer","tagname":"property","owner":"CKEDITOR.plugins.notification.area","id":"property-_uiBuffer","meta":{"private":true}},{"name":"editor","tagname":"property","owner":"CKEDITOR.plugins.notification.area","id":"property-editor","meta":{"readonly":true}},{"name":"element","tagname":"property","owner":"CKEDITOR.plugins.notification.area","id":"property-element","meta":{"readonly":true}},{"name":"notifications","tagname":"property","owner":"CKEDITOR.plugins.notification.area","id":"property-notifications","meta":{"readonly":true}},{"name":"constructor","tagname":"method","owner":"CKEDITOR.plugins.notification.area","id":"method-constructor","meta":{}},{"name":"_attachListeners","tagname":"method","owner":"CKEDITOR.plugins.notification.area","id":"method-_attachListeners","meta":{"private":true}},{"name":"_createElement","tagname":"method","owner":"CKEDITOR.plugins.notification.area","id":"method-_createElement","meta":{"private":true}},{"name":"_layout","tagname":"method","owner":"CKEDITOR.plugins.notification.area","id":"method-_layout","meta":{"private":true}},{"name":"_removeListeners","tagname":"method","owner":"CKEDITOR.plugins.notification.area","id":"method-_removeListeners","meta":{"private":true}},{"name":"add","tagname":"method","owner":"CKEDITOR.plugins.notification.area","id":"method-add","meta":{}},{"name":"remove","tagname":"method","owner":"CKEDITOR.plugins.notification.area","id":"method-remove","meta":{}}],"alternateClassNames":[],"aliases":{},"id":"class-CKEDITOR.plugins.notification.area","short_doc":"Notification area is an area where all notifications are put. ...","component":false,"superclasses":[],"subclasses":[],"mixedInto":[],"mixins":[],"parentMixins":[],"requires":[],"uses":[],"html":"<div><pre class=\"hierarchy\"><h4>Files</h4><div class='dependency'><a href='source/plugin29.html#CKEDITOR-plugins-notification-area' target='_blank'>plugin.js</a></div></pre><div class='doc-contents'><div class='rounded-box private-box'><p><strong>NOTE:</strong> This is a private utility class for internal use by the framework. Don't rely on its existence.</p></div><p>Notification area is an area where all notifications are put. The area is laid out dynamically.\nWhen the first notification is added, the area is shown and all listeners are added.\nWhen the last notification is removed, the area is hidden and all listeners are removed.</p>\n        <p>Available since: <b>4.5</b></p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-_changeBuffer' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-property-_changeBuffer' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-property-_changeBuffer' class='name expandable'>_changeBuffer</a> : Object<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'><p>Event buffer object for editor change events to optimize performance.</p>\n</div><div class='long'><p>Event buffer object for editor change events to optimize performance.</p>\n</div></div></div><div id='property-_notificationMargin' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-property-_notificationMargin' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-property-_notificationMargin' class='name expandable'>_notificationMargin</a> : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Notification margin. ...</div><div class='long'><p>Notification margin. Cached for performance reasons.</p>\n</div></div></div><div id='property-_notificationWidth' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-property-_notificationWidth' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-property-_notificationWidth' class='name expandable'>_notificationWidth</a> : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Notification width. ...</div><div class='long'><p>Notification width. Cached for performance reasons.</p>\n</div></div></div><div id='property-_uiBuffer' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-property-_uiBuffer' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-property-_uiBuffer' class='name expandable'>_uiBuffer</a> : Object<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'><p>Event buffer object for UI events to optimize performance.</p>\n</div><div class='long'><p>Event buffer object for UI events to optimize performance.</p>\n</div></div></div><div id='property-editor' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-property-editor' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-property-editor' class='name expandable'>editor</a> : <a href=\"#!/api/CKEDITOR.editor\" rel=\"CKEDITOR.editor\" class=\"docClass\">CKEDITOR.editor</a><span class=\"signature\"><span class='readonly' >readonly</span></span></div><div class='description'><div class='short'><p>The editor instance.</p>\n</div><div class='long'><p>The editor instance.</p>\n</div></div></div><div id='property-element' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-property-element' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-property-element' class='name expandable'>element</a> : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='readonly' >readonly</span></span></div><div class='description'><div class='short'>Notification area DOM element. ...</div><div class='long'><p>Notification area DOM element. This element is created when the area object is created. It will be attached to the document\nwhen the first notification is added and removed when the last notification is removed.</p>\n</div></div></div><div id='property-notifications' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-property-notifications' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-property-notifications' class='name expandable'>notifications</a> : Array<span class=\"signature\"><span class='readonly' >readonly</span></span></div><div class='description'><div class='short'><p>The array of added notifications.</p>\n</div><div class='long'><p>The array of added notifications.</p>\n</div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-constructor' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-method-constructor' target='_blank' class='view-source'>view source</a></div><strong class='new-keyword'>new</strong><a href='#!/api/CKEDITOR.plugins.notification.area-method-constructor' class='name expandable'>CKEDITOR.plugins.notification.area</a>( <span class='pre'>editor</span> ) : <a href=\"#!/api/CKEDITOR.plugins.notification.area\" rel=\"CKEDITOR.plugins.notification.area\" class=\"docClass\">CKEDITOR.plugins.notification.area</a><span class=\"signature\"></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>editor</span> : <a href=\"#!/api/CKEDITOR.editor\" rel=\"CKEDITOR.editor\" class=\"docClass\">CKEDITOR.editor</a><div class='sub-desc'><p>The editor instance.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.plugins.notification.area\" rel=\"CKEDITOR.plugins.notification.area\" class=\"docClass\">CKEDITOR.plugins.notification.area</a></span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-_attachListeners' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-method-_attachListeners' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-method-_attachListeners' class='name expandable'>_attachListeners</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Attaches listeners to the notification area. ...</div><div class='long'><p>Attaches listeners to the notification area.</p>\n</div></div></div><div id='method-_createElement' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-method-_createElement' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-method-_createElement' class='name expandable'>_createElement</a>( <span class='pre'></span> ) : <a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Creates the notification area element. ...</div><div class='long'><p>Creates the notification area element.</p>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/CKEDITOR.dom.element\" rel=\"CKEDITOR.dom.element\" class=\"docClass\">CKEDITOR.dom.element</a></span><div class='sub-desc'><p>Notification area element.</p>\n</div></li></ul></div></div></div><div id='method-_layout' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-method-_layout' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-method-_layout' class='name expandable'>_layout</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Sets the position of the notification area based on the editor content, toolbar as well as\nviewport position and dime...</div><div class='long'><p>Sets the position of the notification area based on the editor content, toolbar as well as\nviewport position and dimensions.</p>\n</div></div></div><div id='method-_removeListeners' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-method-_removeListeners' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-method-_removeListeners' class='name expandable'>_removeListeners</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>Detaches listeners from the notification area. ...</div><div class='long'><p>Detaches listeners from the notification area.</p>\n</div></div></div><div id='method-add' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-method-add' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-method-add' class='name expandable'>add</a>( <span class='pre'>notification</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Adds the notification to the notification area. ...</div><div class='long'><p>Adds the notification to the notification area. If it is the first notification, the area will also be attached to\nthe document and listeners will be attached.</p>\n\n<p>Note that the proper way to show a notification is to call the <a href=\"#!/api/CKEDITOR.plugins.notification-method-show\" rel=\"CKEDITOR.plugins.notification-method-show\" class=\"docClass\">CKEDITOR.plugins.notification.show</a> method.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>notification</span> : <a href=\"#!/api/CKEDITOR.plugins.notification\" rel=\"CKEDITOR.plugins.notification\" class=\"docClass\">CKEDITOR.plugins.notification</a><div class='sub-desc'><p>Notification to add.</p>\n</div></li></ul></div></div></div><div id='method-remove' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.plugins.notification.area'>CKEDITOR.plugins.notification.area</span><br/><a href='source/plugin29.html#CKEDITOR-plugins-notification-area-method-remove' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.plugins.notification.area-method-remove' class='name expandable'>remove</a>( <span class='pre'>notification</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Removes the notification from the notification area. ...</div><div class='long'><p>Removes the notification from the notification area. If it is the last notification, the area will also be\ndetached from the document and listeners will be detached.</p>\n\n<p>Note that the proper way to hide a notification is to call the <a href=\"#!/api/CKEDITOR.plugins.notification-method-hide\" rel=\"CKEDITOR.plugins.notification-method-hide\" class=\"docClass\">CKEDITOR.plugins.notification.hide</a> method.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>notification</span> : <a href=\"#!/api/CKEDITOR.plugins.notification\" rel=\"CKEDITOR.plugins.notification\" class=\"docClass\">CKEDITOR.plugins.notification</a><div class='sub-desc'><p>Notification to remove.</p>\n</div></li></ul></div></div></div></div></div></div></div>","meta":{"private":true}});