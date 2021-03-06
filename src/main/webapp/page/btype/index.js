var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', [ "ngResource", "xeditable" ]);
app.run([ 'editableOptions', function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
} ]);
app.controller('eduCtrl', function($scope, eduSrv) {

});

/*
 * document.addEventListener("deviceready", function() {
 * console.log("deviceready2=======: "); init(); });
 */

$(function() {
	/*
	 * if (clinetType == "Android" || clinetType == "http") {
	 * console.log("deviceready1=======: ");
	 * 
	 *  }
	 */

	init();
	
	loadMenuTree("");
	
	
	$("#dict_level").change(function(){
		
		changeParentList();
	});

	

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_dict_key : {
			required : true,
		// version4: true,
		// minlength:4,
		},
		s_dict_name : "required",
		s_sort : {
			required : true,
		},
		menuParentid : {
			required : true,
		},
		oldname:"required",
		
	}, {
		s_dict_key : {
			required : "请输入KEY",
		// version4: "版本号格式错误*.*.*.*",
		// minlength:"至少4个字符"
		},
		menuParentid : {
			required : "选择父分类",
		},
		s_dict_name : "请输入NAME",
		s_sort : {
			required : "请输入排序号",
		},
		oldname:"请选择图标文件",
	}, $scope.doupdate, "");
	
	
	$("#q_pid").click(function(){
		showMenu();
	})
	
	$("#cleanselect").click(function(){
		var menuids="";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.cancelSelectedNode();
	$("q_pid").val("");
	hideMenu();
	})
	

});


function showMenu() {
	//var cityObj = $("#q_pid");
	//var cityOffset = $("#q_pid").offset();
	//$("#menuContent").slideDown("fast");
	$("#menuContent").show();

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	
	var menuids="";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	if(zTree!=null)
		{
	var nodes = zTree.getSelectedNodes();
	for ( var i = 0, l = nodes.length; i < l; i++) {		
		menuids+=nodes[i].name+"";

	}
		}
	
	$("#q_pid").val(menuids);
	
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}


function loadMenuTree(role_id)
{
	
	var setting = {
			isSimpleData : true, // 数据是否采用简单 Array 格式，默认false
			treeNodeKey : "id", // 在isSimpleData格式下，当前节点id属性
			treeNodeParentKey : "pId", // 在isSimpleData格式下，当前节点的父节点id属性
			
			check : {
				autoCheckTrigger : false,
				chkboxType : {"Y": "ps", "N": "ps"},
				chkStyle : "checkbox",
				enable : false,
				nocheckInherit : false,
				radioType : "level"
				},
			view : {
				showLine : true, // 是否显示节点间的连线
			//	addHoverDom : addHoverDom, // 增加节点 点击新增
			//	removeHoverDom : removeHoverDom,
				selectedMulti : false
			},
			edit : {
				enable : false,
				editNameSelectAll : true,
			//	renameTitle : renameTitle, // 编辑按钮说明文字
			//	removeTitle : removeTitle, // 删除按钮说明文字
			//	showRemoveBtn : showRemoveBtn, // 是否显示移除按钮
			//	showRenameBtn : showRenameBtn
			// 是否显示编辑按钮
			},
			callback : {
				onClick:function(){
					hideMenu();
				}
			//	beforeDrag : beforeDrag,
			//	beforeEditName : beforeEditName, // 编辑节点
			//	beforeRemove : beforeRemove, // 删除节点
			//	beforeRename : beforeRename,
			//	onRemove : onRemove,
			//	onRename : onRename,
			//	beforeDrop : beforeDrop,
			//	beforeClick : beforeClick,
			//	onCheck : onCheck
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPID : 0
				}
			}
		};
	
	
	//var role_id=$("#role_id").val();
	
	// 防止页面乱码现象
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		data : {
			//role_id : role_id
		},
		dataType : "json",
		url :  basePath + '/blogtype/getTree.do',// 请求的action路径
		error : function() {// 请求失败处理函数
			error('请求失败');
		},
		success : function(data) { // 请求成功后处理函数
			var data1 = eval('[' + data + ']');
//			if (typeof (data1.name) == undefined) {
//				data1.name = '';
//			}
			zNodes = data1; // 把后台封装好的简单Json格式赋给treeNodes

			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		}
	});

	//$("#selectAll").bind("click", selectAll);
	$("#treeDemo a").each(
			function() {
	
			});	
	
	
}


function changerows(option) {
	var num = $(option).val();
	// msg(num);

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
}


function changeParentList(successCallback){
	var type=$("#dict_level").val()-1;
	
	var data="dict_level="+type+"&pageSize=40&pageCount=40";
	$.ajax({
		type : "post",
		url : basePath+"/blogtype/listparent.action",
		data : data,
		async : false,
		dataType : "json",
		success : function(data) {

			var lst= data.rows;
			
			var html="";
			if(lst.length)
			{
			$.each(lst,function(index,item){
				if(index==0)
				html+=	' <option value="'+item.id+'"  selected="selected" >'+item.dict_name+'</option> ';
				else
					html+=	' <option value="'+item.id+'"  >'+item.dict_name+'</option> ';
				
			});
				}
			else
				{
				html+=	' <option value="-1"  selected="selected" >根节点</option> ';
				}
			$("#menuParentid").html(html);
			
			
			if(typeof(successCallback)=='function')
				{
				successCallback();
				}
			
		}
	});
}


app.filter("fmt", ['$sce', function($sce) {
    return function(htmlCode) {
    	if(htmlCode==1)
    		return  $sce.trustAsHtml("<span class='text-success'>启用</span>");
    	else
    		return  $sce.trustAsHtml("<span class='text-danger'>禁用</span>");
    			
       // return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
    }
}]);


function init() {

	initmenu($("#menuul"), "page/btype/");

	$('#collapseOne').on(
			'shown.bs.collapse',
			function() {
				// 执行一些动作...
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
		$(this).css("overflow-y", "scroll"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	});

	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {

				$scope.checkcity = function(index, data) {
					// alert(index+"/"+data);
					// alert($scope.datalist[index].city);

					var obj = {};
					obj.recordid = $scope.datalist[index].recordid;

					obj.city = data;// $("#s_city").val(); //$scope.s_city;
					$scope.doupdate(this, obj);

				}

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

				// $("#myModal").draggable();
				// $("#myModal").resizable();
				$("#cpType").select2({

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
				});

				$scope.load = function(type) {

					window.location.href = "../../page/" + type;
				};

				$scope.del = function(item) {
					if (item == null)
						return;

					var id = item.id;

					$("#myModal3").modal("show");
					$("#btnconfirm").one(
							"click",
							function() {

								var obj = {};

								obj.recordid = id;

								SZUMWS(http + "blogtype/del.action", JSON
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
											// $("#myModal2").modal('hide');

										}, false, false

								);

								// $("#btnconfirm").unbind("click",delfun(id));

							});

				}
				
				$scope.selectfile = function () {
					$("#myModal1").modal('show');
				}

				$scope.addOrModify = function(item) {
					kvalidate.resetForm("#fm");
					if (item != null) {
						$scope.edit = "编辑";
						$scope.s_recordid = item.id;
						$scope.s_dict_key = item.dict_key;
						$scope.s_dict_name = item.dict_name;

						$scope.s_sort = parseInt( item.sort);
						
						$scope.desc_info=item.desc_info;
						$scope.url = item.val1;
						$scope.oldname = item.val1;
						$scope.fullurl = item.val2+item.val1;
						$scope.val2 = item.val2;
						
						$("#s_dict_key").attr('disabled','');
					
						$("#dict_level").val(item.dict_level);
						changeParentList(function(){
							$('#menuParentid').val(item.parent_id);// get(0).selectedIndex = 1;
						});

					} else {
						$scope.edit = "新增";
						$("#s_dict_key").removeAttr('disabled');
						$scope.s_recordid = "";
						$scope.s_dict_key = "";
						$scope.s_dict_name = "";
						
						$scope.url = "";
						$scope.desc_info="";
						$scope.oldname="";						
						$scope.s_sort = "";
						$scope.fullurl = "";
						changeParentList(function(){
							$('#menuParentid').get(0).selectedIndex = 1;
						});
					}

					
					
					
				
					$("#myModal2").modal('show');
				}
				var http = getImUrl();// "";

				$scope.doupdate = function(fm, value) {

					var obj = {};

					if (typeof (value) == "undefined") {
						obj.recordid = $scope.s_recordid;
						obj.key = $scope.s_dict_key;
						obj.name = $scope.s_dict_name;

						obj.sort =1;// $scope.s_sort;
						obj.desc_info = $scope.desc_info;
						
						obj.val1 = $("#url").val();
						
						obj.enable = $("#enable").val();
						obj.parent_id = $("#menuParentid").val();
						obj.dict_level = $("#dict_level").val();
						
						
					} else {
						obj = value;
					}

					SZUMWS(http + "blogtype/addOrUpdate.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							$("#myModal2").modal('hide');

							$scope.getList();
							setTimeout(function() {
								$scope.s_recordid = "";
								$scope.s_dict_key = "";
								$scope.s_dict_name = "";

								$scope.s_sort = "";

								$scope.$apply();
							}, 10);

						} else {
							msg(message);
						}
						/*
						 * if (fucOnFinished != null) fucOnFinished();
						 */

					}, function error(data) {
						msg("网络异常!");
						// $("#myModal2").modal('hide');

						/*
						 * if (fucOnFinished != null) fucOnFinished();
						 */

					}, false, false

					);

				}

				$scope.update = function() {

					/*
					 * if($scope.fm.s_account.$error.required) {
					 * error("用户ID必须要填写"); return; }
					 * if($scope.fm.s_pass.$error.required) { error("密码必须要填写");
					 * return; }
					 * 
					 * if($scope.fm.s_company.$error.required) {
					 * error("公司必须要填写"); return; }
					 * 
					 * if($scope.fm.s_ip_refresh.$invalid) { error("刷新时间为数字");
					 * return; }
					 */

					kvalidate.validate("#fm");

				}

				$scope.citys_select = [ '上海', '南京', 'nanjing', '扬州', '苏州' ];

			
				// $scope.getcompayList();

				$scope.title = "文章类型管理";
				// $scope.curpage=1;
				$scope.page = 1;
				$scope.rows = 20;

				$scope.rows_select = [ 5, 10, 20,30 ];
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

	

					var http = getImUrl();// "";

					var obj = new Object();
					
					var menuids="";
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					if(zTree!=null)
						{
					var nodes = zTree.getSelectedNodes();
					for ( var i = 0, l = nodes.length; i < l; i++) {		
						menuids+=nodes[i].id+"";

					}
						}
					
					obj.parent_id=menuids;
					
					obj.name = $scope.q_name;// "12345678";

					obj.page = $scope.page;// 1;// "12345678";
					obj.rows = $scope.rows;// 10;// "12345678";
					SZUMWS(
							http + "blogtype/getInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
								// var json = JSON.parse(decryData);
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

									$scope.datalist = eval(json.datalist);
									$scope.val2 = json.val2;
									
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
									/*
									 * for(var i =1;i <= $scope.pageNum;i++){
									 * if(i<$scope.page)
									 * $scope.pageDataPre.push(i); else
									 * if(i>$scope.page)
									 * $scope.pageDataAft.push(i); };
									 */

									$scope.$apply();

									console.log('-----guideList -OK= ');

								} else {
									msg(message);
								}

								if (fucOnFinished != null)
									fucOnFinished();

								// $('#refresh').removeClass('visible');
								// $('#refresh2').removeClass('visible');

							}, function error(data) {
								msg("网络异常!");

								if (fucOnFinished != null)
									fucOnFinished();

								// $("#refresh").removeClass('visible');
								// $('#refresh2').removeClass('visible');

							}, false, false

					);

				}
				// $scope.apply();

				$scope.getList();

				return;

				

			});

}

// filter
app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
// service
app
		.factory(
				'eduSrv',
				function($resource) {
					if (devMode == 1) {
						return $resource(
								"/edu",
								{
									id : "@id"
								},
								{
									edu_guideTypes : {
										url : "/huimin/Code/testdata/tpl/education/edu_guideTypes.json",
										method : "GET"
									},
									edu_guideList : {
										url : "/huimin/Code/testdata/tpl/education/edu_guideList.json",
										method : "GET"
									}
								});
					} else if (devMode == 2) {
						return $resource(appConfig.backendUrl + "/edu/:id", {
							id : "@id"
						}, {

						});
					}
					return null;
				});

var slideNumber = 1, swiperList, swiperMenu;
function initMenuSwiper(seletor) {
	swiperMenu = new Swiper(seletor, {
		slidesPerView : '3'

	});
}

var refreshHeight = 50;
function initSwiper(seletor) {
	var vh = $(window).height();

	var holdPosition, holdPositionBottom;
	// console.log(vh);
	$(seletor).height(vh - 10);
	swiperList = new Swiper(seletor, {
		slidesPerView : 'auto',
		mode : 'vertical',
		watchActiveIndex : true,
		onResistanceBefore : function(s, pos) {
			holdPosition = pos;
			// console.log("onResistanceBefore:", holdPosition);

		},
		onResistanceAfter : function(s, pos) {
			holdPositionBottom = pos;
			// console.log("onResistanceAfter:", pos);
			if (pos > 100) {
				// pullUp(swiperList);
			}
			// popupAlert(holdPositionBottom);
		},
		onTouchMove : function(swiper) {
			// popupAlert(holdPositionBottom);

			if (holdPosition > refreshHeight) {
				$('#pullDown').addClass('visible');
			} else {
				$('#pullDown').removeClass('visible');
			}

			if (holdPositionBottom > refreshHeight) {
				$('#pullUp').addClass('visible');
			} else {
				$('#pullUp').removeClass('visible');
			}

		},
		onTouchStart : function() {

			holdPosition = 0;
			holdPositionBottom = 0;
		},
		onTouchEnd : function() {
			$('#pullDown').removeClass('visible');
			$('#pullUp').removeClass('visible');
			// console.log("onTouchEnd:", holdPosition);
			if (holdPosition > refreshHeight) {

				pullDown(swiperList);
			}

			if (holdPositionBottom > refreshHeight) {

				pullUp(swiperList);
			}
		}
	});
}
var hisDatas;

function clearSwiperData(swiper) {

	swiper.removeAllSlides();
}

function addSwiperData(swiper, datas, isApp, nodeType) {

	if (swiper && datas) {
		$.each(datas, function(i, e) {
			if (isApp)
				swiper.appendSlide(getItem(e, nodeType));
			else
				swiper.prependSlide(getItem(e, nodeType));
		});

		swiper.reInit();
	}
}
// Load slides
function pullDown(swiper) {

	// setTimeout(function(){
	// $('#refresh').removeClass('visible');
	// swiper.setWrapperTranslate(0, 0, 0);
	// swiper.params.onlyExternal = false;
	// swiper.updateActiveSlide(0);
	//		   
	// },2000);
	//	
	reload(null);

}
function pullUp(swiper) {

	// Hold Swiper in required position

	var curNum = swiper.slides.length;
	var ht = 0;
	for ( var i = 0; i < swiper.slides.length; i++) {
		ht += swiper.slides[i].clientHeight;
	}
	var num = parseInt(ht / swiper.height);

	var scroll = ht - num * swiper.height + 200;
	if (num == 0)
		scroll = 0;

	// swiper.setWrapperTranslate(0, -scroll, 0);
	// Dissalow futher interactions
	swiper.params.onlyExternal = true;
	// Show loader
	$('#refresh2').addClass('visible');

	// setTimeout(function(){
	// $('#refresh2').removeClass('visible');
	// // swiper.setWrapperTranslate(0, 0, 0);
	// swiper.params.onlyExternal = false;
	// swiper.updateActiveSlide(0);
	//   
	// },2000);

	getMoreList(swiper, function() {

		$('#pullUp').removeClass('visible');
		$('#refresh2').removeClass('visible');

		// swiper.setWrapperTranslate(0, -scroll, 0);
		swiper.params.onlyExternal = false;
		swiper.swipeTo(curNum - 1, 1000, false);

	});
}
function reload(id) {

	// Hold Swiper in required position
	swiperList.setWrapperTranslate(0, refreshHeight, 0)
	// Dissalow futher interactions
	swiperList.params.onlyExternal = true;
	// Show loader
	$('#refresh').addClass('visible');

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.page = 1;
		$scope.getList(id, function() {

			$('#pullDown').removeClass('visible');

			swiperList.setWrapperTranslate(0, 0, 0);
			swiperList.params.onlyExternal = false;
			swiperList.updateActiveSlide(0);

		});
	});
}

function getMoreList(swiper, loadDone) {
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {

		if (swiper.slides.length < $scope.Total) {
			$scope.page++;
			$scope.getList(null, loadDone, false);
		} else {
			loadDone();
		}

	});
}

function keyword(id, url, source, serviceid, appName, appType, AppUrl,
		AppProcess, NeddParam) {
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {

		$scope.openKey(id, url, source, serviceid, appName, appType, AppUrl,
				AppProcess, NeddParam);
	});
}

function openList(id) {
	var location = "http://www.szzfcg.cn/viewer.do?id=36364830";
	// alert(location)
	// window.location.href=location;
	open_without_referrer(location);
	// open_new_window(location);
}

function open_new_window_cool(full_link) {
	window.open('javascript:window.name;', '<script>location.replace("'
			+ full_link + '")<\/script>');
}

function open_new_window(full_link) {
	window.open('javascript:window.name;', '<script>location.replace("'
			+ full_link + '")<\/script>');
}

function open_without_referrer(link) {
	document.body.appendChild(document.createElement('iframe')).src = 'javascript:"<script>top.location.replace(\''
			+ link + '\')<\/script>"';
}

function getItem(obj, nodeType) {
	if (!obj)
		return;
	var itemHtml = "";
	if (nodeType == 0) {
		itemHtml = "<li class='swiper-slide'>"
				+ "<a class='ts' href='javascript:void(0);' onclick='reload("
				+ obj.Id + ")'>" + "<div class='tet'>" + "<span>" + obj.Name
				+ "</span>" + "</div>" + "</a>" + "</li>";
	} else if (nodeType == 1) {
		// itemHtml = "<div class='item swiper-slide ng-scope'>"
		// + "<div class='title' title='" + obj.Title + "'>"
		// + "<a onclick='openDetail(\"" + obj.Id
		// + "\")' href='javascript:void(0);'>" + obj.Title + "</a>"
		// + "</div>" + "<div class='time'>" + obj.UpdateDate + "</div>";

		// obj.Id=8;

		/*
		 * onclick='open_new_window(\"" + encodeURI( obj.URL)
		 */

		itemHtml = "	<a class=\"list-group-item\"  "
				+ "\")' rel='noreferrer' href='"
				+ obj.URL
				+ "'> <span "
				+ " class=\"rightico pull-right\"> <span "
				+ " class=\"glyphicon glyphicon-chevron-right line-height-4  \"></span></span> "
				+ " <h5 class=\"list-group-item-header\">"
				+ obj.Title
				+ "</h5> "
				+ "  <h5 class=\"list-group-item-header margin-top-5 \"><span class=\"small\">"
				+ "" + "</span></h5> "
				+ " <p class=\"list-group-item-text small time\"><span>" + ""
				+ "</span>&nbsp;" + obj.UpdateDate + "</p> "

				+ " </a> ";

		if (obj.Keys) {
			itemHtml += "<div class='kws clearfix'>";
			$
					.each(
							obj.Keys,
							function(i, e) {
								itemHtml += "<div class='kw'><a href='javascript:void(0);' onclick='keyword(\""
										+ e.Id
										+ "\",\""
										+ e.HttpUrl
										+ "\",\""
										+ e.Source
										+ "\",\""
										+ e.AppServiceID
										+ "\",\""
										+ e.AppName
										+ "\",\""
										+ e.AppType
										+ "\",\""
										+ e.AppUrl
										+ "\",\""
										+ e.AppProcess
										+ "\",\""
										+ e.NeedParam
										+ "\""
										+

										")' > <span> "
										+ e.Name
										+ "</span></a></div>";
							});

			itemHtml += "</div>";
		}
		itemHtml += "</div>";
	}

	return itemHtml;
}
