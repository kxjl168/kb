/**
 * 
 */

/*
 * CKEDITOR.dialog.add( 'abbrDialog', function( editor ) { return { // Basic
 * properties of the dialog window: title, minimum size. title: 'Abbreviation
 * Properties', minWidth: 400, minHeight: 200, // Dialog window content
 * definition. contents: [ { // Definition of the Basic Settings dialog tab
 * (page). id: 'tab-basic', label: 'Basic Settings', // The tab content.
 * elements: [ { // Text input field for the abbreviation text. type: 'text',
 * id: 'abbr', label: 'Abbreviation', // Validation checking whether the field
 * is not empty. validate: CKEDITOR.dialog.validate.notEmpty( "Abbreviation
 * field cannot be empty." ) }, { // Text input field for the abbreviation title
 * (explanation). type: 'text', id: 'title', label: 'Explanation', validate:
 * CKEDITOR.dialog.validate.notEmpty( "Explanation field cannot be empty." ) } ] }, //
 * Definition of the Advanced Settings dialog tab (page). { id: 'tab-adv',
 * label: 'Advanced Settings', elements: [ { // Another text field for the abbr
 * element id. type: 'text', id: 'id', label: 'Id' } ] } ], // This method is
 * invoked once a user clicks the OK button, confirming the dialog. onOk:
 * function() { // The context of this function is the dialog object itself. //
 * https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_dialog.html var
 * dialog = this; // Create a new <abbr> element. var abbr =
 * editor.document.createElement( 'abbr' ); // Set element attribute and text by
 * getting the defined field values. abbr.setAttribute( 'title',
 * dialog.getValueOf( 'tab-basic', 'title' ) ); abbr.setText( dialog.getValueOf(
 * 'tab-basic', 'abbr' ) ); // Now get yet another field value from the Advanced
 * Settings tab. var id = dialog.getValueOf( 'tab-adv', 'id' ); if ( id )
 * abbr.setAttribute( 'id', id ); // Finally, insert the element into the editor
 * at the caret position. editor.insertElement( abbr ); } }; });
 */

CKEDITOR.dialog.add('k_mediadiag', function(editor) {
	return {
		title : '音频地址',
		minWidth : 400,
		minHeight : 200,
		contents : [ {
			id : 'tab-basic',
			label : 'Basic Settings',
			elements : [ {
				type : 'text',
				id : 'desc',
				label : '描述',
				setup : function(element) {
					this.setValue(element.getAttribute("title"));
				},

				commit : function(element) {
					if (element != null)
						element.setAttribute("title", this.getValue());
				}
			// validate: CKEDITOR.dialog.validate.notEmpty( "" )
			},

			{
				type : 'text',
				id : 'url',
				label : 'URL',
				validate : CKEDITOR.dialog.validate.notEmpty("url不能为空."),
				setup : function(element) {
					this.setValue(element.getAttribute("src"));
				},

				commit : function(element) {
					if (element != null)
						element.setAttribute("src", this.getValue());
				}
			}, {
				type : 'text',
				id : 'detailurl',
				label : '本文链接',
				setup : function(element) {

					var a = element.getAscendant('a', true);
					if (a != null)
						this.setValue(

						a.getAttribute("href")

						);
				},

				commit : function(element) {
					var a = element.getAscendant('a', true);
					if (a != null)
						a.setAttribute("href", this.getValue());
				}
			// validate: CKEDITOR.dialog.validate.notEmpty( "" )
			}, {
				type : 'text',
				id : 'detailtext',
				label : '链接文字',
				setup : function(element) {

					var a = element.getAscendant('a', true);
					if (a != null)
						this.setValue(

						a.getText()

						);
				},

				commit : function(element) {
					var a = element.getAscendant('a', true);
					if (a != null)
						a.setText(this.getValue());
				}
			}, ]
		},
		/*
		 * { id: 'tab-adv', label: 'Advanced Settings', elements: [ { type:
		 * 'text', id: 'id', label: 'Id' } ] }
		 */
		],
		/*
		 * contents: [ { type: 'text', id: 'desc', label: '描述', // validate:
		 * CKEDITOR.dialog.validate.notEmpty( "" ) }, { type: 'text', id: 'url',
		 * label: 'URL', validate: CKEDITOR.dialog.validate.notEmpty( "url不能为空." ) } ],
		 */

		onShow : function() {
			var selection = editor.getSelection();
			var element = selection.getStartElement();

			if (element)
				element = element.getAscendant('audio', true);

			if (!element || element.getName() != 'audio') {
				element = editor.document.createElement('audio');
				this.insertMode = true;
			} else
				this.insertMode = false;

			this.element = element;
			if (!this.insertMode)
				this.setupContent(this.element);
		},

		onOk : function() {
			var dialog = this;

			var ele = this.element;

			this.commitContent(ele);

			if (this.insertMode) {

				var desc = dialog.getValueOf('tab-basic', 'desc')
				var url = dialog.getValueOf('tab-basic', 'url');
				var detailurl = dialog.getValueOf('tab-basic', 'detailurl');
				var detailtext = dialog.getValueOf('tab-basic', 'detailtext');

				var p = editor.document.createElement('p');
				p.setAttribute('style', "display: flex;");

				ele = editor.document.createElement('audio');
				ele.setAttribute('src', url);
				ele.setAttribute('controls', "control");
				ele.setAttribute('title', desc);

				var a = editor.document.createElement('a');
				a.setAttribute('href', detailurl);
				a.setText(detailtext);

				a.appendTo(ele);
				ele.appendTo(p);

				CKEDITOR.instances.s_context.insertElement(p);
			}

		}
	};
});