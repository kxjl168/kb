var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', [  "xeditable" ]);
app.run([ 'editableOptions', function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
} ]);
app.controller('eduCtrl', function($scope) {

});



$(function() {
	
	

	//initCKPlugin();
	
	init();

	initDetailTable();

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_title : {
			required : true,
	
		},
		s_context : "required",
		
	}, {
		s_title : {
			required : "标题必须填写",

		},

		s_context : "请输入内容",
		
	}, $scope.doupdate, "");
	


});


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
						
						$scope.getList();
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
	
	var pluginname2="kuploadTFile";
	var cmd_name2="cmd_upload";
	CKEDITOR.plugins.add( pluginname2, {
		 
	    init: function( editor ) {
	        editor.addCommand( cmd_name2, {
	            exec: function( editor ) {
	            	
	          	var $scope = angular.element(ngSection).scope();
	          	
	            	$scope.$apply(function() {
	            	
	            	$scope.save(null,null,function(){
						
						$scope.getList();
						msg("保存成功！");
					});
	            	});	
	            }
	        });
	        editor.ui.addButton( 'btn_kupload', {
	            label: '上传图片',
	            command:cmd_name2,
	            toolbar: 'insert',
	            icon: basePath+'/images/logo2.png',
	        });
	    }
	});


	CKEDITOR.morePluginnames=pluginname+","+pluginname2;
	$("#s_context").ckeditor();
	//CKEDITOR.config.extraPlugins= pluginname+',codesnippet,colorbutton,font,liststyle,copyformatting';
	/*CKEDITOR.replace( 's_context', {
		extraPlugins: pluginname+',codesnippet,colorbutton,font,liststyle,copyformatting',
	});*/
	
	
};


function query(){
	   $("#table_detail").bootstrapTable('refresh', null);
}


function initDetailTable() {
	
	var http = getImUrl();// "";

	



    // 初始化Table
    $('#table_detail').bootstrapTable({
        url:http + "blog/getInfoList2.action", // 请求后台的URL（*）
        method: 'post', // 请求方式（*）
        contentType: 'application/x-www-form-urlencoded',
        toolbar: '#toolbar', // 工具按钮用哪个容器
    
        showHeader: true,
        searchAlign: 'left',
        buttonsAlign: 'left',
        searchOnEnterKey: true,
        striped: true, // 是否显示行间隔色
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, // 是否显示分页（*）
        sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 10, // 每页的记录行数（*）
        pageList: [10, 25], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        detailView: false,
        // showColumns: true, //是否显示所有的列
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        // queryParamsType : "limit",
        queryParams: function queryParams(params) { // 设置查询参数
        	
        	var rows= params.limit; // 每页要显示的数据条数
           var offset= params.offset; // 每页显示数据的开始行号
           
    
        	var page=1+ offset/rows;
     
        	var sname= params.sort; // 要排序的字段
        	if(sname=='showdesc')
        		sname="showflag";
        	
        	
        //	obj.blog_title = $scope.q_name;
        //	obj.blog_tag=$scope.q_tags;

        /*	obj.show="-1";
        	obj.page = $scope.page;
        	obj.rows = $scope.rows;
        	*/
            var param = {
            		pageCount: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                page:page,
                rows:rows,
                show:"-1",
                sortName: sname, // 要排序的字段
                sortOrder:  params.order,
                blog_title : $("#q_name").val(),
                blog_tag: $("#q_tags").val(),
            };
            return param;
        },
        columns: [
        	{
                field: 'title',
                title: '文章标题',
                align: 'left',
                valign: 'middle',
                visible:true
            }, 
            {
                field: 'action',
                title: '操作',
                align: 'left',
                valign: 'middle',
                visible:true,
                formatter: modifyAndDeleteButton,
                events: PersonnelInformationEvents,
               
               
            }, 
            {
                field: 'showdesc',
                title: '可见',
                align: 'left',
                valign: 'middle',
                sortable : true
              
            },
            {
                field: 'blog_type_name',
                title: '文章类型',
                align: 'left',
                valign: 'middle',
              
            },
            {
                field: 'tags',
                title: '标签',
                align: 'left',
                valign: 'middle',
              
            },
            {
                field: 'view_nums',
                title: '点击次数',
                align: 'left',
                valign: 'middle',
                sortable : true
              
            },
            {
                field: 'spider_nums',
                title: '爬取次数',
                align: 'left',
                valign: 'middle',
                sortable : true
              
            },
            {
                field: 'replay_nums',
                title: '评论次数',
                align: 'left',
                valign: 'middle',
                sortable : true
              
            },
            {
                field: 'create_date',
                title: '发布日期',
                align: 'left',
                valign: 'middle',
                sortable : true
              
            },
            
        ],
    });
    
    
    
}


function modifyAndDeleteButton(value, row, index) {
	 var html='<a href="javascript:void(0)" id="modify" class="text-info" >修改</a>';
     html+='<a href="javascript:void(0)"  id="delete" class="text-warning" >删除</a>';
     
    return html;
};

window.PersonnelInformationEvents = {
    "click  #modify" : function (e, value, row, index) {
    	addOrModify(row);
    },
    "click  #delete" : function (e, value, row, index) {
    	del(row);
    },
};


function del(row)
{
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.del(row);
	});
	
}

function addOrModify(row)
{
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.addOrModify(row);
	});
	
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
								//changetype();
						
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

			
				/*$("#cpType").select2({

					"placeholder" : "请选择类型",
					"allowClear" : false,
					"minimumResultsForSearch" : Infinity,
					"data" : $scope.cpTypes
				});

				$("#cpType2").select2({

					"placeholder" : "请选择类型",
					"allowClear" : false,
					"minimumResultsForSearch" : Infinity,
					"data" : $scope.cpTypes
				});*/

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
					var http2 = getImUrl();
					var imei=item==null?"":item.imei;
					window.location.href=http2+"page/blog/detail?imei="+imei;
					return;
					
					
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
										
									
										item=$scope.detailItem;

										$scope.edit = "编辑";
									
										$scope.s_recordid =item.recordid;
										$scope.s_title=item.title;
										
										setTimeout(function() {
											 $("#s_type").val(item.blog_type);	
											 $("#en_type").val(item.showflag);	
										}, 30);
										
										$scope.s_tags =item.tags;
										$scope.s_context =unescape(item.context);
										
									
										
										$scope.$apply();
										
									

										$("#myModal2").modal('show');

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
						
						}, 30);
						
						
						
						$scope.s_tags ="";
						$scope.s_context ="";
						
						$("#myModal2").modal('show');
					}

				};
				
				var http = getImUrl();

				$scope.doupdate = function(fm, value) {

					$scope.save(fm,value,function(){
						$("#myModal2").modal('hide');

						$scope.getList();
						setTimeout(function() {
							$scope.s_recordid = "";
							$scope.s_dict_key = "";
							$scope.s_dict_name = "";

							$scope.s_sort = "";

							$scope.$apply();
						}, 10);
					});

				};
				
				$scope.save=function(fm, value,callback){
					var obj = {};

					if (value==null||typeof (value) == "undefined") {
						obj.recordid = $scope.s_recordid;
						obj.title = $scope.s_title;
						obj.blog_type =$("#s_type").val();
						obj.tags =$scope.s_tags;
						obj.show =$("#en_type").val();
						
						var num=$($("#s_context"  ).val()).find(".pct").length;
						var ct=$("<div class='pct'>"+$("#s_context"  ).val() +"</div>");
						if(num>0)
							ct=$($("#s_context"  ).val());
						
						
						$.each(ct.find("img"),function(index,item){
							$(item).removeAttr("style");
							$(item).removeAttr("class");
							$(item).attr("class","img-responsive");
						});
						
						
						obj.context= escape(ct[0].outerHTML);
						

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

							callback();

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("网络异常!");
					

					}, false, false

					);
				};

				$scope.update = function() {

				
					kvalidate.validate("#fm");

				};

				$scope.citys_select = [ '上海', '南京', 'nanjing', '扬州', '苏州' ];

				$scope.getdictList = function(id, fucOnFinished, clear) {

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

						
							
							setTimeout(function() {

						
								$('#q_type').get(0).selectedIndex = 1;
							
								$scope.q_type = $("#q_type").val();
							
							}, 30);

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
				$scope.getdictList();

				$scope.title = "文章管理";
			
				$scope.page = 1;
				$scope.rows = 20;

				$scope.rows_select = [ 5, 10, 20 ];
				setTimeout(function() {
					$("div.tablefoot select").val($scope.rows);
				}, 50);
				$scope.pageData = [];

				$scope.getList = function(id, fucOnFinished, clear) {

					
					query();
					
					return;
					
					$scope.page = (id != null) ? id : 1;

					if ($scope.page > $scope.pageNum)
						$scope.page = $scope.page - 1;

					if ($scope.page <= 0)
						$scope.page = 1;

					var http = getImUrl();

					var obj = new Object();
					obj.blog_title = $scope.q_name;
					obj.blog_tag=$scope.q_tags;

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
			
				//$scope.getList();

				return;

			});		

};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
