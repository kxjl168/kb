var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', [  "xeditable" ]);
app.run([ 'editableOptions', function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
} ]);
app.controller('eduCtrl', function($scope) {

});






$(function() {

	initCKPlugin();
	
	initTypeSelect();
	// alert( escape("$#x"));
	
	var smile='😀';
	
	// alert( $kchar.codePointAt(smile));
	
	/*
	 * var high=smile.charCodeAt(0); var low=smile.charCodeAt(1); //
	 * alert(CKEDITOR.tools.htmlEncode('😀'));
	 * 
	 * //alert(high+" "+low);
	 * 
	 * //d83d de00 //alert(high.toString(16)+" "+low.toString(16));
	 * 
	 * var MIN_SUPPLEMENTARY_CODE_POINT = 0x010000; var MIN_HIGH_SURROGATE =
	 * '\uD800'.charCodeAt(0); var MIN_LOW_SURROGATE = '\uDC00'.charCodeAt(0);
	 * var codept= ((high << 10) + low) + (MIN_SUPPLEMENTARY_CODE_POINT -
	 * (MIN_HIGH_SURROGATE << 10) - MIN_LOW_SURROGATE);
	 * 
	 * alert(codept.toString(16));
	 * 
	 *  // alert(unescape('😀'));
	 */

	var $scope = angular.element(ngSection).scope();
	
	init();

});


/**
 * 根据一组图片计算宽度比例
 * 
 * @param imgs
 * @returns
 */
function CacuwidthArray(ay,totalwidth){
	
	
	
	$.each(ay,function(index,item){

		// 整体占比
		item.flexgrow= Math.floor((item.hpw/totalwidth) * 10000) / 10000;
		// ay[index]=item;
	});

	return ay;
	
	
}


/**
 * 计算一个图片组中图片的缩放，保持高度一致
 */
function reCacuPicWidth(fid,width,height)
{
	var htmlData=CKEDITOR.instances.s_context.getData();
	var eleData=$("<div>"+ htmlData+"</div>");
	
	var imgs=$(eleData).find('img[fid="'+fid+'"]').parent().find("img[fid]")
	
	var  ay=[];
	var totalwidth=0;
	$.each(imgs,function(index,item){
		
		var data={};
		
		if($(item).attr("fid")==fid&&width!=0&&height!=0)
			{
			data.width=  width;
			data.height= height;
			}
		else
			{
			data.width=  item.naturalWidth;
			data.height= item.naturalHeight;
			}
			
		data.index=index;
		data.dataEle=item;
		
		
		// 自身宽高比-高度相同情况下，各自缩放比例
		data.hpw=  Math.floor((data.width/data.height) * 10000) / 10000;
		data.fid=$(item).attr('fid');
		
		
		
		totalwidth+=data.hpw;
		
		ay.push(data);
		
	});
	
	
	
	
	var ay= CacuwidthArray(ay ,totalwidth  );
	
	$.each(ay,function(index,item){
		
		$(eleData).find('img[fid="'+item.fid+'"]').css("display","flex");
		$(eleData).find('img[fid="'+item.fid+'"]').css("flex-grow",item.flexgrow);
		$(eleData).find('img[fid="'+item.fid+'"]').css("width",item.flexgrow+"px");
		$(eleData).find('img[fid="'+item.fid+'"]').attr("wd",item.flexgrow);
		
		$(eleData).find('img[fid="'+fid+'"]').parent().css("display","flex");
		
	})
	
	
	CKEDITOR.instances.s_context.setData(	$(eleData).html());
}



function initCKPlugin()
{
	var pluginname="savedata";
	var cmd_name="cmd_savedata";
	CKEDITOR.plugins.add( pluginname, {
	 
	    init: function( editor ) {
	        editor.addCommand( cmd_name, {
	            exec: function( editor ) {
	            	
	          	var $scope = angular.element(ngSection).scope();
	          	
	            	$scope.$apply(function() {
	            	
	            	$scope.save(null,null,function(){
						
						// $scope.getList();
						msg("保存成功！");
					});
	            	});	
	            }
	        });
	        editor.ui.addButton( 'btn_savedata', {
	            label: '保存数据',
	            command:cmd_name,
	            toolbar: 'insert',
	            icon: basePath+'/images/save.jpg',
	        });
	    }
	});
	
	
	
	
	
	$kfile.init({
		fileUploadname:"fileUploadURL", // 上传文件的name
		httppath:$("#httppath").val(),  // img -static目录前缀
		isimg:true,
		filesufix:'png,jpg,gif,jpeg,',
		maxFileSize:5*1024*1024,// 5M
		maximgupload : 1,// 最多可上传图片数量
		uploadurl:basePath + '/UploadFileXhr.action',// 上传图片action url
		container:$("body").find('#upimgs'), // 图片容器
		cleanpic:false,// 再次弹出时是否清除图片显示
		uploaddonecallback:function(obj,align,width,imgwidth,imgheight){
			var htmlData=CKEDITOR.instances.s_context.getData();
			var appEndData='<img src="'+obj.FileUrl+'" orisrc="'+obj.FileUrl2+'" fid="'+obj.fileid+'"  class="img-responsive " onclick="showOriImg()"  >';
			// var theData=htmlData+appEndData;
			 var ele=CKEDITOR.dom.element.createFromHtml(appEndData);
			
			CKEDITOR.instances.s_context.insertElement(ele);
			
			var fid=obj.fileid;
			
			setTimeout(function() {
				/*
				 * var htmlData=CKEDITOR.instances.s_context.getData(); var
				 * eleData=$("<div>"+ htmlData+"</div>");
				 * $(eleData).find('img[fid="'+fid+'"]').css("display","flex");
				 * $(eleData).find('img[fid="'+fid+'"]').css("flex-grow",width/100);
				 * $(eleData).find('img[fid="'+fid+'"]').css("width",width+"px");
				 * $(eleData).find('img[fid="'+fid+'"]').attr("wd",width);
				 * 
				 * $(eleData).find('img[fid="'+fid+'"]').parent().css("display","flex");
				 * 
				 * 
				 * CKEDITOR.instances.s_context.setData( $(eleData).html());
				 */
				
				reCacuPicWidth(fid,imgwidth,imgheight);
			}, 40);
			
		},
		okcallback:function(obj,align,width){
		/*
		 * var htmlData=CKEDITOR.instances.s_context.getData(); var eleData=$("<div>"+
		 * htmlData+"</div>");
		 * $(eleData).find('img[fid="'+$(obj).val()+'"]').css("display","flex");
		 * $(eleData).find('img[fid="'+$(obj).val()+'"]').css("flex-grow",width/100);
		 * $(eleData).find('img[fid="'+$(obj).val()+'"]').css("width",width+"px");
		 * $(eleData).find('img[fid="'+$(obj).val()+'"]').attr("wd",width);
		 * 
		 * $(eleData).find('img[fid="'+$(obj).val()+'"]').parent().css("display","flex");
		 * 
		 * 
		 * CKEDITOR.instances.s_context.setData( $(eleData).html());
		 */
		
		reCacuPicWidth($(obj).val(),0,0);
		
	}
	});
	$kfile.get("upimgs").initFile(function(){
		
	});
	
	
	var pluginname2="kuploadFile";
	var cmd_name2="cmd_upload";
	var btn_name2="btn_kupload";
	CKEDITOR.plugins.add( pluginname2, {
		 
	    init: function( editor ) {

	        editor.addCommand( cmd_name2, {
	            exec: function( editor ) {
	            	
	            	var selection = CKEDITOR.instances.s_context.getSelection();
	            	// if(selection.getType()==3){
	            	// var img=$( selection.getSelectedElement().$ );
	            	// $kfile.get("upimgs").showpre(img.attr("src"));
	            	// }
	            	
	            	$kfile.get("upimgs").uploadimg( $kfile.get("upimgs").container.find(".gdimg") );
	            }
	        });
	        editor.ui.addButton( btn_name2, {
	            label: '上传图片',
	            command:cmd_name2,
	            toolbar: 'insert',
	            icon: basePath+'/images/logo2.png',
	        });
	        editor.on("doubleclick", function(a) {
                var b = a.data.element;
                !b.is("img") || b.data("cke-realelement") || b.isReadOnly() || (
                	
                			
                		$kfile.get("upimgs").initFile(function(){
                		$kfile.get("upimgs").addFile($(b.$).attr("fid"), $(b.$).attr("src")),
                		$kfile.get("upimgs").uploadimg( $kfile.get("upimgs").container.find(".gdimg") )
                		})
                		
                	
                		
                				);
            },null, null, 1);// 优先级1
	        
	    }
	});
	
	
	
	
	
	

	var pluginname3="autoSave";
	var cmd_name3="cmd_autoSave";
	var btn_name3="btn_autosave";
	CKEDITOR.plugins.add( pluginname3, {
		 
	    init: function( editor ) {
	    	// editor.addContentsCss &&
			// editor.addContentsCss(basePath+"/css/kCommon.css");
	    	editor.addCommand( cmd_name3, {
	            exec: function( editor ) {
	            	
	            	if(autosavehander)
	            		{
	            		
	            		$(".cke_button_icon.cke_button__"+btn_name3+"_icon").attr("style","background-image:url('"+basePath+'/images/auto.jpg'+"?t=HBDG');background-position:0 undefinedpx;background-size:16px;");
	            		
	            		
	            		clearInterval(autosavehander);
	            		autosavehander=null;
	            		info("已关闭自动保存！");
	            		}
	            	else{
	            		autoSave();
	            		 
	            		$(".cke_button_icon.cke_button__"+btn_name3+"_icon").attr("style","background-image:url('"+basePath+'/images/auto2.jpg'+"?t=HBDG');background-position:0 undefinedpx;background-size:16px;");
	            		
	   	             
	            		info("已开启自动保存！");
	            	}
	            }
	        });
	        editor.ui.addButton( btn_name3, {
	            label: '自动保存',
	            command:cmd_name3,
	            toolbar: 'insert',
	            icon: basePath+'/images/auto.jpg',
	        });
	       
	        
	    }
	});
	
	
	var pluginname4="emojiPanel";
	var cmd_name4="cmd_emojiPanel";
	var btn_name4="btn_emojiPanel";
	CKEDITOR.plugins.add( pluginname4, {
		 
	    init: function( editor ) {

	    	renderEmojiPanel(function(htmlc){
	    		editor.ui.add( btn_name4, CKEDITOR.UI_PANELBUTTON, {
		        	 label: 'Emoji',
		    		title: 'Emoji',
		    		modes: { wysiwyg: 1 },
		    		editorFocus: 0,
		    		   toolbar: 'insert',
		    		   icon: basePath+'/images/teeth_smile.png',

		    		panel: {
		    		css: getImUrl()+"page/blog/emoji.css",
		    			attributes: { role: 'listbox', 'aria-label': 'Emoji' }
		    		},

		    		onBlock: function( panel, block ) {
		    			panelBlock = block;

		    		
		    			block.autoSize = true;
		    			block.element.addClass( 'cke_kemojipanel' );
		    			block.element.setHtml( htmlc);
		    			
		    			// block.element.getDocument().getBody().setStyle(
						// 'overflow', 'hidden' );

		    			CKEDITOR.ui.fire( 'ready', this );

		    		},

		    		refresh: function() {
		    			
		    		},

		    		// The automatic colorbox should represent the real color
					// (https://dev.ckeditor.com/ticket/6010)
		    		onOpen: function() {

		    		}
		    	} );
	    	

	    		
	    	})
	    	
	  
	        
	        function renderEmojiPanel(callback){
	        	var html='	<div  class="emjcontainer">';
	        	
	          	
	    	    var clickFn = CKEDITOR.tools.addFunction( function add(str ) {

	    	    			 var ele=CKEDITOR.dom.element.createFromHtml("<span>"+str+"</span>");
	    	    				
	    	    				CKEDITOR.instances.s_context.insertElement(ele);
	    	    		});	
	        	$.ajax({
	        		type : "post",
	        		url :getImUrl()+ "public/emoji.action",
	        		async : false,
	        		dataType : "json",
	        		success : function(response) {
	        			
	        	
	        			var htmlemoji="";
	        			
	        			if(response.rows)
	        				{
	        				$.each(response.rows,function(index,item){
	        					html+='	<div class="em_item catsay" 	 onclick="CKEDITOR.tools.callFunction('+ clickFn+',\''+item.str+'\');return false;"   title="'+item.name+'">'+item.str+'</div> ';
	        				});
	        				
	        			
	        				}
	        		}
	        	});
	        	html+='</div>';
	        	if(typeof(callback)=="function")
	        		callback(html);
	        };
	       
	        
	    }
	});
	
	
	
	
	var pluginnameMedia="kmedia";
	var cmd_nameMedia="cmd_media";
	var btn_nameMedia="btn_media";
	CKEDITOR.plugins.add( pluginnameMedia, {
		 
	    init: function( editor ) {

	      /*
			 * editor.addCommand( cmd_nameMedia, { exec: function( editor ) {
			 * 
			 * //CKEDITOR.dialog.add( 'k_mediadiag',this.path +
			 * "mediaPlugin.js");
			 * 
			 * new CKEDITOR.dialogCommand( 'k_mediadiag' ) } });
			 */
	        
	    	editor.addCommand(cmd_nameMedia, new CKEDITOR.dialogCommand( 'k_mediadiag' ) );
	        
	        
	        editor.ui.addButton( btn_nameMedia, {
	            label: '嵌入音频',
	            command:cmd_nameMedia,
	            toolbar: 'insert',
	            icon: basePath+'/images/gita.jpg',
	        });
	        
	        CKEDITOR.dialog.add( 'k_mediadiag','' );
	        
	     /*   editor.on("doubleclick", function(a) {
                var element = a.data.element;
                
               // if(element.getAscendant('audio', true)){
                	  return { abbrItem: CKEDITOR.TRISTATE_OFF };
               // }
	        });*/
	        
	    }
	});
	
	
	
	
	


	CKEDITOR.morePluginnames=pluginname+","+pluginname2+","+pluginname3+","+pluginname4+","+pluginnameMedia;
	CKEDITOR.removePlugins="image";
	$("#s_context").ckeditor();
	
/*
 * if(CKEDITOR.instances.s_context.addContentsCss) {
 * CKEDITOR.instances.s_context.addContentsCss(basePath+"/kb/css/KCommon.css"); }
 */

	// CKEDITOR.config.extraPlugins=
	// pluginname+',codesnippet,colorbutton,font,liststyle,copyformatting';
	/*
	 * CKEDITOR.replace( 's_context', { extraPlugins:
	 * pluginname+',codesnippet,colorbutton,font,liststyle,copyformatting', });
	 */
	
	
};


var autosavehander=null;
function autoSave(){
	var $scope = angular.element(ngSection).scope();
  	
	
		
	autosavehander=setInterval(function() {
		$scope.$apply(function() {
			
			if($("#s_context").val()!="")
				{
			$scope.save(null,null,function(){
				
				info("已自动保存成功！");
			});
				}
			});	
	}, 20000);
		


	
}
	
	

function changerows(option) {
	var num = $(option).val();

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
}

function init() {
	

	

	initmenu($("#menuul"), "page/blog/");

	$('#collapseOne').on(
			'shown.bs.collapse',
			function() {
				
				$("#titlepic").attr("class",
						"glyphicon glyphicon-chevron-up pull-right");
			});

	$('#collapseOne').on(
			'hidden.bs.collapse',
			function() {
				$("#titlepic").attr("class",
						"glyphicon glyphicon-chevron-down pull-right");
			});

	$('.modal').on('show.bs.modal', function() {
		$(this).draggable();
		$(this).css("overflow-y", "scroll"); 
	});

	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {
				
				
			
				
				
				
				$scope.getenableList = function(id, fucOnFinished, clear) {

					var http = getImUrl();

					var obj = new Object();

					obj.page = 1;
					obj.rows = 40;

					SZUMWS(http + "blog/getEnableList.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							$scope.enables = eval(json.datalist);

						
							$.each($scope.enables,function(index,item){
								$scope.enables[index]=JSON.parse(item);
							});
							
							

							$scope.$apply();
							
							setTimeout(function() {

								
								$('#en_type').get(0).selectedIndex = 1;
						
								$scope.en_type = $("#en_type").val();
								// changetype();
						
							}, 30);

						

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, false

					);

				};
				$scope.getenableList();
				

				$scope.checkcity = function(index, data) {
				
					var obj = {};
					obj.recordid = $scope.datalist[index].recordid;

					obj.city = data;
					$scope.doupdate(this, obj);

				};

				$scope.cpTypes = [ {
					"id" : 0,
					"text" : "江苏"
				}, {
					"id" : 2,
					"text" : "浙江",
					"selected" : true
				}, {
					"id" : 3,
					"text" : "山东"

				}, {
					"id" : 4,
					"text" : "安徽"

				} ];

				$scope.selType = 1;

			

				$scope.load = function(type) {

					window.location.href = "../../page/" + type;
				};

				$scope.del = function(item) {
					if (item == null)
						return;

					var id = item.recordid;

					$("#myModal3").modal("show");
					$("#btnconfirm").one(
							"click",
							function() {

								var obj = {};

								obj.recordid = id;

								SZUMWS(http + "blog/del.action", JSON
										.stringify(obj),
										function succsess(json) {

											var code = json.ResponseCode;
											var message = json.ResponseMsg;
											console.log('-----return -code= '
													+ code + ';message= '
													+ message);
											if (code == 200) {

												msg("删除成功！");

												$("#myModal3").modal("hide");

												$scope.getList();

											} else {
												msg(message);
											}

										}, function error(data) {
											msg("网络异常!");
										
										}, false, false

								);

							
							});

				};

				$scope.addOrModify = function(item) {
					kvalidate.resetForm("#fm");
					if (item != null) {
						
						var http = getImUrl();

						var obj = new Object();
						obj.i = item.imei;

						obj.page = $scope.page;
						obj.rows = $scope.rows;
						SZUMWS(
								http + "blog/getDetailInfo.action",
								JSON.stringify(obj),
								function succsess(json) {
									
									var code = json.ResponseCode;
									var message = json.ResponseMsg;
								
									if (code == 200) {

										$scope.detailItem =JSON.parse(json.datalist);

										$scope.detailItem.context= unescape( $scope.detailItem.content);
										CKEDITOR.instances.s_context.setData($scope.detailItem.context);
									
										item=$scope.detailItem;

										$scope.edit = "编辑";
									
										$scope.s_recordid =item.recordid;
										$scope.s_title=item.title;
										$scope.imei=item.imei;
										
										setTimeout(function() {
											 $("#s_type").val(item.blog_type);	
											 $("#en_type").val(item.showflag);	
											 $("#showtime").val(item.showtime);
											 $("#ccid").val(item.ccid);
											 
										}, 30);
										
										$scope.s_tags =item.tags;
										$scope.s_context =unescape(item.context);
										// $("#s_context").val(unescape(item.context)
										// );
										
										setTimeout(function() {
											$("#s_context").val(unescape(item.context) );	
										}, 250);
										
										
										// select2 类型
										var option = new Option(item.blog_type_name, item.blog_type,
												true, true);
										$("#mType").append($(option)).trigger('change');

										// manually trigger the `select2:select`
										// event
										$("#mType").trigger({
											type : 'select2:select',
											params : {
												data : {
													text : item.blog_type_name,
													id : item.blog_type
												}
											}
										});
										
									
										
										$scope.$apply();
										
									

									// $("#myModal2").modal('show');

									} else {
										msg(message);
									}

								}, function error(data) {
									msg("网络异常!");

								

								}, true, false

						);
						
				

					} else {
						$scope.edit = "新增";
					

						$scope.s_recordid ="";
						$scope.s_title="" ;
						
					
						setTimeout(function() {

							$("#s_type").get(0).selectedIndex = 1;
						
							$scope.s_type = $("#q_type").val();
							
							$("#en_type").get(0).selectedIndex = 1;
							$scope.en_type = $("#en_type").val();
							
							
							$("#ccid").get(0).selectedIndex = 1;
							$scope.ccid = $("#ccid").val();
						
						}, 30);
						
						
						
						$scope.s_tags ="";
						$scope.s_context ="";
						
						$("#myModal2").modal('show');
					}

				};
				
				
				$scope.cancel=function(){
					var http2 = getImUrl();
					
					window.location.href=http2+"page/blog/";
				};
				
				var http = getImUrl();

				$scope.doupdate = function(fm, value) {

					$scope.save(fm,value,function(){
						
						msg("操作成功");
						setTimeout(function() {
							var http2 = getImUrl();
							
							window.location.href=http2+"page/blog/";
							
						}, 200);
						// $("#myModal2").modal('hide');

						// $scope.getList();
						/*
						 * setTimeout(function() { $scope.s_recordid = "";
						 * $scope.s_dict_key = ""; $scope.s_dict_name = "";
						 * 
						 * $scope.s_sort = "";
						 * 
						 * $scope.$apply(); }, 10);
						 */
					});

				};
				
				$scope.save=function(fm, value,callback){
					var obj = {};

					if(!kvalidate.validate("#fm"))
						{
						error("字段验证失败!");
						return;
						}
						
					
					
					if (value==null||typeof (value) == "undefined") {
						obj.recordid = $scope.s_recordid;
						obj.title = $scope.s_title;
						// obj.blog_type =$("#s_type").val();
						
						obj.blog_type =$("#mType").val();
						
						
						obj.showtime =$("#showtime").val();
						
						obj.tags =$scope.s_tags;
						obj.showflag =$("#en_type").val();
						obj.ccid=$("#ccid").val();
						obj.imei=$scope.imei;
						
					
						var ct=$("<div class='pct'>"+$("#s_context"  ).val()+"</div>");
						ct.find(".pct").removeClass('pct');
						
						
						$.each(ct.find("img"),function(index,item){
							$(item).removeAttr("style");
							$(item).removeAttr("class");
							$(item).attr("class","img-responsive");
						});
						
						
						// var withemojihtml= $kchar.
						// replaceEmoji(ct[0].outerHTML);
						var withemojihtml= $kchar. replaceEmoji(CKEDITOR.instances.s_context.getData());
						
						
						obj.content= escape(withemojihtml);
						

					} else {
						obj = value;
					}

					SZUMWS(http + "blog/addOrUpdate.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							if(typeof(callback)=="function")
							callback();

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("网络异常!");
					

					}, false, 'json'

					);
				};

				$scope.update = function() {

					if(!kvalidate.validate("#fm"))
						{
						error("字段验证失败!");
						return;
						}
						
					$scope.doupdate();

				};

				$scope.citys_select = [ '上海', '南京', 'nanjing', '扬州', '苏州' ];

				$scope.getdictList = function(callback) {

					var http = getImUrl();

					var obj = new Object();

					obj.page = 1;
					obj.rows = 40;

					SZUMWS(http + "blogtype/getInfoList.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							$scope.dicts = eval(json.datalist);

							$scope.$apply();
							
						/*
						 * setTimeout(function() {
						 * 
						 * 
						 * $('#q_type').get(0).selectedIndex = 1;
						 * 
						 * $scope.q_type = $("#q_type").val();
						 *  }, 30);
						 */

						if(typeof(callback)=="function")
							{
							callback();
							}

						

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, false

					);

				};
				

				$scope.title = "文章管理";
			
				$scope.page = 1;
				$scope.rows = 10;

				$scope.rows_select = [ 5, 10, 20 ];
				setTimeout(function() {
					$("div.tablefoot select").val($scope.rows);
				}, 50);
				$scope.pageData = [];

				$scope.getList = function(id, fucOnFinished, clear) {

					$scope.page = (id != null) ? id : 1;

					if ($scope.page > $scope.pageNum)
						$scope.page = $scope.page - 1;

					if ($scope.page <= 0)
						$scope.page = 1;

					var http = getImUrl();

					var obj = new Object();
					obj.name = $scope.q_name;

					obj.show="-1";
					obj.page = $scope.page;
					obj.rows = $scope.rows;
					SZUMWS(
							http + "blog/getInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
							
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

									$scope.datalist = eval(json.datalist);

									
									$.each($scope.datalist,function(index,item){
										$scope.datalist[index].context= unescape( item.content);
									});
									
									
									
									$scope.total = json.total;
									$scope.pageDataPre = [];
									$scope.pageDataAft = [];
									$scope.pageNum = Math.ceil($scope.total
											/ $scope.rows);// +
															// ($scope.total%$scope.rows)>0?1:0;

									for ( var i = $scope.page - 3; i < $scope.page; i++) {
										if (i > 0)
											$scope.pageDataPre.push(i);
									}
									for ( var i = $scope.page + 1; i < $scope.page + 3; i++) {
										if (i <= $scope.pageNum)
											$scope.pageDataAft.push(i);
									}
						

									$scope.$apply();

									console.log('-----guideList -OK= ');

								} else {
									msg(message);
								}

							}, function error(data) {
								msg("网络异常!");

						

							}, false, false

					);

				};
				
				
				kvalidate.init($("#fm"), {
					s_title : {
						required : true,
				
					},
					s_context : "required",
					
					ccid : {
						required : true,
				
					},
					
					
				}, {
					s_title : {
						required : "标题必须填写",

					},
					ccid : {
						required : "许可协议必选",
				
					},
					s_context : "请输入内容",
					
				},null , "");
				// $scope.doupdate
				// $scope.getList();
				
				
				$scope.getdictList(function(){
					var imei=GetQueryStringO("imei");
					var object={};
					object.imei=imei;
					if(imei==null||imei=="")
						object=null;
					$scope.addOrModify(object);
					
				});
				
				

				return;

			});		

};




function GetQueryStringO(name) {

/*
 * var index = window.location.href.lastIndexOf("/"); var indexj =
 * window.location.href.lastIndexOf("#");
 *  // 最后一个/开始 截取#前面的，兼容history.js html4 url var searchpath =
 * window.location.href.substr(index + 1); if (indexj > 0) searchpath =
 * window.location.href.substr(index + 1, indexj - index - 1);
 */
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$|#)", "i");

    var r = window.location.search.substr(1).match(reg);

    if (r != null)

        return decodeURI(r[2]);

    return null;
}



app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);

