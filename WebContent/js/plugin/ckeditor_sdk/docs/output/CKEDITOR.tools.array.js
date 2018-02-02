Ext.data.JsonP.CKEDITOR_tools_array({"tagname":"class","name":"CKEDITOR.tools.array","autodetected":{},"files":[{"filename":"tools.js","href":"tools.html#CKEDITOR-tools-array"}],"since":"4.6.1","members":[{"name":"every","tagname":"method","owner":"CKEDITOR.tools.array","id":"method-every","meta":{}},{"name":"filter","tagname":"method","owner":"CKEDITOR.tools.array","id":"method-filter","meta":{}},{"name":"forEach","tagname":"method","owner":"CKEDITOR.tools.array","id":"method-forEach","meta":{}},{"name":"indexOf","tagname":"method","owner":"CKEDITOR.tools.array","id":"method-indexOf","meta":{}},{"name":"isArray","tagname":"method","owner":"CKEDITOR.tools.array","id":"method-isArray","meta":{}},{"name":"map","tagname":"method","owner":"CKEDITOR.tools.array","id":"method-map","meta":{}},{"name":"reduce","tagname":"method","owner":"CKEDITOR.tools.array","id":"method-reduce","meta":{}}],"alternateClassNames":[],"aliases":{},"id":"class-CKEDITOR.tools.array","component":false,"superclasses":[],"subclasses":[],"mixedInto":[],"mixins":[],"parentMixins":[],"requires":[],"uses":[],"html":"<div><pre class=\"hierarchy\"><h4>Files</h4><div class='dependency'><a href='source/tools.html#CKEDITOR-tools-array' target='_blank'>tools.js</a></div></pre><div class='doc-contents'><p>The namespace with helper functions and polyfills for arrays.</p>\n        <p>Available since: <b>4.6.1</b></p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-every' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.tools.array'>CKEDITOR.tools.array</span><br/><a href='source/tools.html#CKEDITOR-tools-array-method-every' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.tools.array-method-every' class='name expandable'>every</a>( <span class='pre'>array, fn, [thisArg]</span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'>Tests whether all elements in an array pass the test implemented by the provided function. ...</div><div class='long'><p>Tests whether all elements in an array pass the test implemented by the provided function.\nReturns <code>true</code> if the provided array is empty.</p>\n\n<pre><code>var every = this.array.every( [ 11, 22, 33, 44 ], function( value ) {\n    return value &gt; 10;\n} );\nconsole.log( every );\n// Logs: true\n</code></pre>\n        <p>Available since: <b>4.8.0</b></p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>array</span> : Array<div class='sub-desc'>\n</div></li><li><span class='pre'>fn</span> : Function<div class='sub-desc'><p>A function that gets called with each <code>array</code> item.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>value</span> : Mixed<div class='sub-desc'><p>The currently iterated array value.</p>\n</div></li><li><span class='pre'>index</span> : Number<div class='sub-desc'><p>The index of the currently iterated value in an array.</p>\n</div></li><li><span class='pre'>array</span> : Array<div class='sub-desc'><p>The original array passed as the <code>array</code> variable.</p>\n</div></li></ul></div></li><li><span class='pre'>thisArg</span> : Mixed (optional)<div class='sub-desc'><p>A context object for <code>fn</code>.</p>\n<p>Defaults to: <code>undefined</code></p></div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p>Information whether all elements pass the test.</p>\n</div></li></ul></div></div></div><div id='method-filter' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.tools.array'>CKEDITOR.tools.array</span><br/><a href='source/tools.html#CKEDITOR-tools-array-method-filter' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.tools.array-method-filter' class='name expandable'>filter</a>( <span class='pre'>array, fn, [thisArg]</span> ) : Array<span class=\"signature\"></span></div><div class='description'><div class='short'>Returns a copy of array filtered using the fn function. ...</div><div class='long'><p>Returns a copy of <code>array</code> filtered using the <code>fn</code> function. Any elements that the <code>fn</code> will return <code>false</code> for\nwill get removed from the returned array.</p>\n\n<pre><code>var filtered = this.array.filter( [ 0, 1, 2, 3 ], function( value ) {\n    // Leave only values equal or greater than 2.\n    return value &gt;= 2;\n} );\nconsole.log( filtered );\n// Logs: [ 2, 3 ]\n</code></pre>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>array</span> : Array<div class='sub-desc'>\n</div></li><li><span class='pre'>fn</span> : Function<div class='sub-desc'><p>A function that gets called with each <code>array</code> item. Any item that <code>fn</code>\nreturned a <code>false</code>-alike value for will be filtered out of the <code>array</code>.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>value</span> : Mixed<div class='sub-desc'><p>The currently iterated array value.</p>\n</div></li><li><span class='pre'>index</span> : Number<div class='sub-desc'><p>The index of the currently iterated value in an array.</p>\n</div></li><li><span class='pre'>array</span> : Array<div class='sub-desc'><p>The original array passed as the <code>array</code> variable.</p>\n</div></li></ul></div></li><li><span class='pre'>thisArg</span> : Mixed (optional)<div class='sub-desc'><p>A context object for <code>fn</code>.</p>\n<p>Defaults to: <code>undefined</code></p></div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Array</span><div class='sub-desc'><p>The filtered array.</p>\n</div></li></ul></div></div></div><div id='method-forEach' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.tools.array'>CKEDITOR.tools.array</span><br/><a href='source/tools.html#CKEDITOR-tools-array-method-forEach' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.tools.array-method-forEach' class='name expandable'>forEach</a>( <span class='pre'>array, fn, [thisArg]</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Iterates over every element in the array. ...</div><div class='long'><p>Iterates over every element in the <code>array</code>.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>array</span> : Array<div class='sub-desc'><p>An array to be iterated over.</p>\n</div></li><li><span class='pre'>fn</span> : Function<div class='sub-desc'><p>The function called for every <code>array</code> element.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>value</span> : Mixed<div class='sub-desc'><p>The currently iterated array value.</p>\n</div></li><li><span class='pre'>index</span> : Number<div class='sub-desc'><p>The index of the currently iterated value in an array.</p>\n</div></li><li><span class='pre'>array</span> : Array<div class='sub-desc'><p>The original array passed as an <code>array</code> variable.</p>\n</div></li></ul></div></li><li><span class='pre'>thisArg</span> : Mixed (optional)<div class='sub-desc'><p>The context object for <code>fn</code>.</p>\n<p>Defaults to: <code>undefined</code></p></div></li></ul></div></div></div><div id='method-indexOf' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.tools.array'>CKEDITOR.tools.array</span><br/><a href='source/tools.html#CKEDITOR-tools-array-method-indexOf' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.tools.array-method-indexOf' class='name expandable'>indexOf</a>( <span class='pre'>array, value</span> ) : Number<span class=\"signature\"></span></div><div class='description'><div class='short'>Returns the index of an element in an array. ...</div><div class='long'><p>Returns the index of an element in an array.</p>\n\n<pre><code>var letters = [ 'a', 'b', 0, 'c', false ];\nalert( <a href=\"#!/api/CKEDITOR.tools-method-indexOf\" rel=\"CKEDITOR.tools-method-indexOf\" class=\"docClass\">CKEDITOR.tools.indexOf</a>( letters, '0' ) );        // -1 because 0 !== '0'\nalert( <a href=\"#!/api/CKEDITOR.tools-method-indexOf\" rel=\"CKEDITOR.tools-method-indexOf\" class=\"docClass\">CKEDITOR.tools.indexOf</a>( letters, false ) );      // 4 because 0 !== false\n</code></pre>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>array</span> : Array<div class='sub-desc'><p>The array to be searched.</p>\n</div></li><li><span class='pre'>value</span> : Object/Function<div class='sub-desc'><p>The element to be found. This can be an\nevaluation function which receives a single parameter call for\neach entry in the array, returning <code>true</code> if the entry matches.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Number</span><div class='sub-desc'><p>The (zero-based) index of the first entry that matches\nthe entry, or <code>-1</code> if not found.</p>\n</div></li></ul></div></div></div><div id='method-isArray' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.tools.array'>CKEDITOR.tools.array</span><br/><a href='source/tools.html#CKEDITOR-tools-array-method-isArray' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.tools.array-method-isArray' class='name expandable'>isArray</a>( <span class='pre'>object</span> ) : Boolean<span class=\"signature\"></span></div><div class='description'><div class='short'>Checks if an object is an Array. ...</div><div class='long'><p>Checks if an object is an Array.</p>\n\n<pre><code>alert( <a href=\"#!/api/CKEDITOR.tools-method-isArray\" rel=\"CKEDITOR.tools-method-isArray\" class=\"docClass\">CKEDITOR.tools.isArray</a>( [] ) );      // true\nalert( <a href=\"#!/api/CKEDITOR.tools-method-isArray\" rel=\"CKEDITOR.tools-method-isArray\" class=\"docClass\">CKEDITOR.tools.isArray</a>( 'Test' ) );  // false\n</code></pre>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>object</span> : Object<div class='sub-desc'><p>The object to be checked.</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Boolean</span><div class='sub-desc'><p><code>true</code> if the object is an Array, otherwise <code>false</code>.</p>\n</div></li></ul></div></div></div><div id='method-map' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.tools.array'>CKEDITOR.tools.array</span><br/><a href='source/tools.html#CKEDITOR-tools-array-method-map' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.tools.array-method-map' class='name expandable'>map</a>( <span class='pre'>array, fn, [thisArg]</span> ) : Array<span class=\"signature\"></span></div><div class='description'><div class='short'>Applies a function to each element of an array and returns the array of results in the same order. ...</div><div class='long'><p>Applies a function to each element of an array and returns the array of results in the same order.\nNote the order of the parameters.</p>\n        <p>Available since: <b>4.6.2</b></p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>array</span> : Array<div class='sub-desc'><p>An array of elements that <code>fn</code> is applied on.</p>\n</div></li><li><span class='pre'>fn</span> : Function<div class='sub-desc'><p>A function with the signature <code>a -&gt; b</code>.</p>\n</div></li><li><span class='pre'>thisArg</span> : Mixed (optional)<div class='sub-desc'><p>The context object for <code>fn</code>.</p>\n<p>Defaults to: <code>undefined</code></p></div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Array</span><div class='sub-desc'><p>An array of mapped elements.</p>\n</div></li></ul></div></div></div><div id='method-reduce' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='CKEDITOR.tools.array'>CKEDITOR.tools.array</span><br/><a href='source/tools.html#CKEDITOR-tools-array-method-reduce' target='_blank' class='view-source'>view source</a></div><a href='#!/api/CKEDITOR.tools.array-method-reduce' class='name expandable'>reduce</a>( <span class='pre'>array, fn, initial, [thisArg]</span> ) : Mixed<span class=\"signature\"></span></div><div class='description'><div class='short'>Applies a function against each value in an array storing the result in an accumulator passed to the next iteration. ...</div><div class='long'><p>Applies a function against each value in an array storing the result in an accumulator passed to the next iteration.\nNote the order of the parameters.</p>\n        <p>Available since: <b>4.6.2</b></p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>array</span> : Array<div class='sub-desc'><p>An array of elements that <code>fn</code> is applied on.</p>\n</div></li><li><span class='pre'>fn</span> : Function<div class='sub-desc'><p>A function with the signature <code>(accumulator, a, index, array) -&gt; b</code>.</p>\n</div></li><li><span class='pre'>initial</span> : Mixed<div class='sub-desc'><p>Initial value of the accumulator.</p>\n</div></li><li><span class='pre'>thisArg</span> : Mixed (optional)<div class='sub-desc'><p>The context object for <code>fn</code>.</p>\n<p>Defaults to: <code>undefined</code></p></div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'>Mixed</span><div class='sub-desc'><p>The final value of the accumulator.</p>\n</div></li></ul></div></div></div></div></div></div></div>","meta":{}});