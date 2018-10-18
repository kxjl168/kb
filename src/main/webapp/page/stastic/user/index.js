var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', ["ngResource"]);
app.controller('eduCtrl', function ($scope, eduSrv) {

});

/*
 * document.addEventListener("deviceready", function() {
 * console.log("deviceready2=======: "); init(); });
 */

var cuserid="";
function showdetail(userid){
	
	cuserid=userid;
	$("#myModal_detail").modal('show');
	 $("#table_detail2").bootstrapTable('refresh', null);
}

function query(){
	   $("#table_detail").bootstrapTable('refresh', null);
}


function initDetailTable() {
	
	var http = getImUrl();// "";

	
	

    // 初始化Table
    $('#table_detail').bootstrapTable({
        url:http + "statistics/GetUserVisitDetailList.action", // 请求后台的URL（*）
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
        	if(sname=='blogname')
        		sname="blog_id";
        	
            var param = {
            		pageCount: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                page:page,
                sortName: sname, // 要排序的字段
                sortOrder:  params.order,
                time1 : $("#effectDate").val(),
                time2: $("#effectDate2").val(),
                date_type : $("#dateType").val(),
             // qName : $("#s_type ").find("option:selected").text(),
             // date:cdate,
                userid:$("#userid").val(),
            };
            return param;
        },
        columns: [
        	{
                field: 'id',
                title: '订单编号',
                align: 'center',
                valign: 'middle',
                visible:false
            }, 
            {
                field: 'blogname',
                title: '文章',
                align: 'left',
                valign: 'middle',
                sortable : true,
                formatter: function (value, row, index) {
                	if(value==undefined)
                		value="";
                    return "<a href='"+basePath+"/public/detail/?i="+row.blog_id+"'>"+value+"</a>";
                }
            },
          
          
            {
            field: 'userid',
            title: '访问ip',
            align: 'center',
            sortable : true,
            valign: 'middle' ,
            formatter: function (value, row, index) {
                return "<a href='javascript:void()' onclick='showdetail(\""+value+"\")'>"+value+"(访问详情)</a>";
            }
            }, 
           
            {
            field: 'city',
            title: '访问地市',
            align: 'center',
            valign: 'middle'
            }, 
            {
            field: 'action_date',
            title: '时间',
            align: 'center',
            valign: 'middle',
            sortable : true,
            },
            {
            	 field: 'type_second',
            title: '分类',
            align: 'center',
            sortable : true,
          
            }
        ],
    });
    
    
    
    
    
    // 初始化Table
    $('#table_detail2').bootstrapTable({
        url:http + "statistics/GetUserVisitAllList.action", // 请求后台的URL（*）
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
           

       	var sname= params.sort; // 要排序的字段
       	if(sname=='blogname')
       		sname="blog_id";
       	
        	var page=1+ offset/rows;
     
            var param = {
            		pageCount: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                page:page,
                sortName: sname, // 要排序的字段
                sortOrder:  params.order,
             
                userid:cuserid,
            };
            return param;
        },
        columns: [
        	{
                field: 'id',
                title: '订单编号',
                align: 'center',
                valign: 'middle',
                visible:false
            }
        	,
        	 {
                field: 'action_date',
                title: '时间',
                align: 'center',
                valign: 'middle',
                sortable : true
            }, 
            {
               
            	 field: 'type_first',
            	 title: '分类一',
                align: 'center',
          
            },
            {
            	  field: 'type_second',
                title: '分类二',
              
                align: 'center',
           
            },
            {
            	field: 'blog_id',
            	title: '额外数据',
                align: 'center',
             
            },
            {
                field: 'blogname',
                title: '文章',
                align: 'center',
                sortable : true,
                valign: 'middle',
                formatter: function (value, row, index) {
                	if(value==undefined)
                		value="";
                    return "<a href='"+basePath+"/public/detail/?i="+row.blog_id+"'>"+value+"</a>";
                }
            }
        
        ],
    });
}


$(function () {
	
	initmenu($("#menuul"),"statistics/userPage");
	
	initDateSelect();
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function () {

		
		$scope.date_type2 = [ {
			"value" : "HOUR",
			"name" : "小时"
		}, {
			"value" : "DAY",
			"name" : "天"
		}, {
			"value" : "MONTH",
			"name" : "月"
		}];
		  setTimeout(function(){
			  $("#dateType").get(0).selectedIndex=2;
			   $("#dateType").trigger('change');
			   
			   setTimeout(function(){
				   initDetailTable();	   
			   },200);
		  }, 50);
		  
		  
			
			
			
	});
	 


});


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
				enable : true,
				nocheckInherit : false,
				radioType : "level"
				},
			view : {
				showLine : true, // 是否显示节点间的连线
			// addHoverDom : addHoverDom, // 增加节点 点击新增
			// removeHoverDom : removeHoverDom,
				selectedMulti : false
			},
			edit : {
				enable : false,
				editNameSelectAll : true,
			// renameTitle : renameTitle, // 编辑按钮说明文字
			// removeTitle : removeTitle, // 删除按钮说明文字
			// showRemoveBtn : showRemoveBtn, // 是否显示移除按钮
			// showRenameBtn : showRenameBtn
			// 是否显示编辑按钮
			},
			callback : {
			// beforeDrag : beforeDrag,
			// beforeEditName : beforeEditName, // 编辑节点
			// beforeRemove : beforeRemove, // 删除节点
			// beforeRename : beforeRename,
			// onRemove : onRemove,
			// onRename : onRename,
			// beforeDrop : beforeDrop,
			// beforeClick : beforeClick,
			// onCheck : onCheck
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
	
	
	// var role_id=$("#role_id").val();
	
	// 防止页面乱码现象
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		data : {
			role_id : role_id
		},
		dataType : "json",
		url :  basePath + '/Privilege/getMenuTree.do',// 请求的action路径
		error : function() {// 请求失败处理函数
			error('请求失败');
		},
		success : function(data) { // 请求成功后处理函数
			var data1 = eval('[' + data + ']');
// if (typeof (data1.name) == undefined) {
// data1.name = '';
// }
			zNodes = data1; // 把后台封装好的简单Json格式赋给treeNodes

			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		}
	});

	$("#selectAll").bind("click", selectAll);
	$("#treeDemo a").each(
			function() {
	
			});	
	
	
}



function changerows(option) {
	var num = $(option).val();
	// msg(num);

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function () {
		$scope.rows = num;
		$scope.getList();
	});
}

function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	
	var ck= $("#selectAll").get(0).checked;
	// alert(ck);
	zTree.checkAllNodes(ck);
}


function init() {

	initmenu($("#menuul"),"statistics/userPage");
	var http = getImUrl();// "";

	
	$('#collapseOne').on('shown.bs.collapse', function () {
		// 执行一些动作...
		$("#titlepic").attr("class", "glyphicon glyphicon-chevron-up pull-right");
	});

	$('#collapseOne').on('hidden.bs.collapse', function () {
		$("#titlepic").attr("class", "glyphicon glyphicon-chevron-down pull-right");
	});
	

	$('.modal').on('show.bs.modal', function () {
		  $(this).draggable();
		    $(this).css("overflow-y", "scroll");   // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	});
	
	
	

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function () {

		
		$scope.date_type2 = [ {
			"value" : "HOUR",
			"name" : "小时"
		}, {
			"value" : "DAY",
			"name" : "天"
		}, {
			"value" : "MONTH",
			"name" : "月"
		}];
		 // setTimeout(function(){
			   $("#dateType").get(0).selectedIndex=1;
			   $("#dateType").trigger('change');
		 // }, 50);

		

		
		$scope.getcompayList = function (id, fucOnFinished, clear) {

			var http = getImUrl();// "";

			var obj = new Object();

			// obj.deviceid = $scope.id;// "12345678";
			// obj.ip = $scope.ip;
			// obj.compy_name = $scope.compy_name;
			obj.page = 1;// 1;// "12345678";
			obj.rows = 40;// 10;// "12345678";

			SZUMWS(http + "company/getInfoList.action", JSON
				.stringify(obj), function succsess(json) {

					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
						+ message);
					if (code == 200) {


						$scope.compays_select = eval(json.datalist);

						// $scope.total = json.total;

						$scope.$apply();

						console.log('-----guideList -OK= ');

					} else {
						msg(message);
					}

					if (fucOnFinished != null)
						fucOnFinished();


				}, function error(data) {
					msg("网络异常!");

					if (fucOnFinished != null)
						fucOnFinished();

				}, false, false

			);

		}


		$scope.getList = function (id, fucOnFinished, clear) {

			$scope.page = (id != null) ? id : 1;


			if ($scope.page > $scope.pageNum)
				$scope.page = $scope.page - 1;

			if ($scope.page <= 0)
				$scope.page = 1;


			// alert($scope.etId);

			// if ($scope.lastSecondID != $scope.etId) {

			// $scope.page = 1;// // 1;// "12345678";
			// 10;// "12345678";
			// }

			// $scope.lastSecondID = $scope.etId;

			var http = getImUrl();// "";

			var obj = new Object();
			obj.role_id = $scope.role_id;// "12345678";
			obj.role_name = $scope.role_name;// "12345678";

			obj.page = $scope.page;// 1;// "12345678";
			obj.rows = $scope.rows;// 10;// "12345678";
			SZUMWS(http + "Privilege/getRoleList.action", JSON
				.stringify(obj), function succsess(json) {
					// var json = JSON.parse(decryData);
					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
						+ message);
					if (code == 200) {


						$scope.datalist = eval(json.rows);

						$scope.total = json.total;
						$scope.pageDataPre = [];
						$scope.pageDataAft = [];
						$scope.pageNum = Math.ceil($scope.total / $scope.rows);// +
																				// ($scope.total%$scope.rows)>0?1:0;

						for (var i = $scope.page - 3; i < $scope.page; i++) {
							if (i > 0)
								$scope.pageDataPre.push(i);
						}
						for (var i = $scope.page + 1; i < $scope.page + 3; i++) {
							if (i <= $scope.pageNum)
								$scope.pageDataAft.push(i);
						}
				
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

				}, false, "json"

			);

		}
		// $scope.apply();

		// $scope.getList();

		return;


		$scope.type = getGuideTypeId();// window.HMClient.getGuideTypeId();

		$("#sTitle").html(getGuideName());// window.HMClient.getGuideName());
		// $(document).attr("title", getGuideName());//
		// window.HMClient.getGuideName());
		$scope.page = 1;
		$scope.numPerpage = 10;
		$scope.Total = 0;

		$scope.Type = ""; // 类型

		$scope.etId = "";
		$scope.lastSecondID = 1;
		$scope.respData = {
			"ResponseCode": 200,
			"ResponseMsg": "OK",
			"GuideTypes": [{
				"Name": "全部",
				"Id": "1"
			}, {
				"Name": "国家级",
				"Id": "2"
			}, {
				"Name": "省级",
				"Id": "3"
			}, {
				"Name": "地市级",
				"Id": "3"
			}, {
				"Name": "区县级",
				"Id": "4"
			}, {
				"Name": "国家级2",
				"Id": "5"
			}, {
				"Name": "省级3",
				"Id": "6"
			}, {
				"Name": "地市级4",
				"Id": "7"
			}, {
				"Name": "区县级5",
				"Id": "8"
			}]
		};
		$scope.respData2 = {
			"ResponseCode": "200",
			"GuideList": [{
				"Id": "8",
				"Time": "2016-05-12",
				"Person": "刘卡",
				"UpdateDate": "2016-05-12",
				"Type": "2",
				"Title": "深交罚决字第Z005139722",
				"Title2": "刘卡未取得相应从业资格证件，驾驶道路路客货运输"
			}, {
				"Id": "9",
				"Time": "2016-05-02",
				"Person": "刘卡",
				"Type": "2",
				"UpdateDate": "2016-05-12",
				"Title": "深交许可字第Z0051397",
				"Title2": "刘卡未取得相应从业资格证件，驾驶道路路客货运输"
			}, {
				"Id": "10",
				"Time": "2016-04-22",
				"Person": "张三",
				"Type": "1",
				"UpdateDate": "2016-05-12",
				"Title": "交通许可许可字第Z0051397",
				"Title2": "测试申请通过从业资格证件 "
			}, {
				"Id": "11",
				"Person": "刘卡",
				"UpdateDate": "2016-05-12",
				"Time": "2016-04-22",
				"Type": "1",
				"Title": "许可xxxxZ0051397",
				"Title2": "教师许可从业资格证件"
			}],
			"ResponseMsg": "OK",
			"Total": 3
		};

		initMenuSwiper('#swiper_menu');
		initSwiper('#swiper_list');

		$scope.btnP = function () {

			if (devMode == 0) {
				// $scope.guideList = $scope.respData2.GuideList;

				$scope.guideList = [];

				$.each($scope.respData2.GuideList, function (i, e) {
					if (e.Type == 1)
						$scope.guideList.push(e);
				});

				$("#menuBtn").click();
				clearSwiperData(swiperList);
				swiperList.setWrapperTranslate(0, 0, 0);
				addSwiperData(swiperList, $scope.guideList, true, 1);
				$scope.apply();
			} else {
				$scope.Type = 1;
				$scope.getList();
			}
		};

		$scope.Search = function () {

			$scope.Key = $("#key").val();

			if (devMode == 0) {
				// $scope.guideList = $scope.respData2.GuideList;

				$scope.guideList = [];

				$.each($scope.respData2.GuideList, function (i, e) {
					if (e.Title.indexOf($scope.Key) > 0 || e.Title2.indexOf($scope.Key) > 0)
						$scope.guideList.push(e);
				});

				$("#menuBtn").click();
				clearSwiperData(swiperList);
				swiperList.setWrapperTranslate(0, 0, 0);
				addSwiperData(swiperList, $scope.guideList, true, 1);
				$scope.apply();
			} else {

				$scope.getList();

				$("#menuBtn").click();
			}
		};

		$scope.btnF = function () {

			if (devMode == 0) {
				// $scope.guideList = $scope.respData2.GuideList;

				$scope.guideList = [];

				$.each($scope.respData2.GuideList, function (i, e) {
					if (e.Type == 2)
						$scope.guideList.push(e);
				});


				$("#menuBtn").click();

				clearSwiperData(swiperList);
				swiperList.setWrapperTranslate(0, 0, 0);
				addSwiperData(swiperList, $scope.guideList, true, 1);
				$scope.apply();
			} else {
				$scope.Type = 2;
				$scope.getList();
			}
		};



		if (devMode == 0) {
			$scope.guideTypes = $scope.respData.GuideTypes;
			$scope.guideList = $scope.respData2.GuideList;
			initMenuSwiper('#swiper_menu');
			addSwiperData(swiperMenu, $scope.guideTypes, true, 0);
			initSwiper('#swiper_list');
			addSwiperData(swiperList, $scope.guideList, true, 1);
			hisDatas = $scope.guideList;

		} else {

			// $scope.guideTypes = $scope.respData.GuideTypes;
			// $scope.guideTypes = $scope.respData.GuideTypes;
			// $scope.$apply();

			$scope.first = true;

			$scope.getList();

		}

		$scope.openKey = function (id, url, source, servcieid, appName, appType,
			AppUrl, AppProcess, NeddParam) {
			if (source == '1' && servcieid != "") {

				if (appName == "咨询投诉")
					openApp(servcieid, appName);
				else {
					if (appType == "0") // web
					{
						var c_type = getIsAnonymous();
						if (c_type == "2") {
							// 匿名提示
							noty({
								text: "请实名登录后查看!",
								layout: "center",
								timeout: 1000,
								type: "error"
							});
						} else {
							// 实名打开页面-不带订阅
							// ?Token=5B53A93BE629EF1C530A391CB406CA9A&LoginId=test001&AppId=1&Name=%E7%87%83%E6%B0%94%E6%9F%A5%E8%AF%A2
							var newurl = AppUrl + "?Token=" + getToken()
								+ "&LoginId=" + getLoginId() + "&AppId="
								+ servcieid + "&Name=" + getName() + ""
								+ (getSex() == "1" ? "先生" : "女士");
							newurl = encodeURI(newurl);
							console.log("newurl: " + newurl);
							openUrl(newurl);
						}
					} else if (appType == "1") // native
						openApp(servcieid, AppProcess);
				}
			} else if (source == '2' && url != "")
				openUrl(url);

		}

	});

}

// filter
app.filter("sanitize", ['$sce', function ($sce) {
	return function (htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
}]);
// service
app
	.factory(
	'eduSrv',
	function ($resource) {
		if (devMode == 1) {
			return $resource(
				"/edu",
				{
					id: "@id"
				},
				{
					edu_guideTypes: {
						url: "/huimin/Code/testdata/tpl/education/edu_guideTypes.json",
						method: "GET"
					},
					edu_guideList: {
						url: "/huimin/Code/testdata/tpl/education/edu_guideList.json",
						method: "GET"
					}
				});
		} else if (devMode == 2) {
			return $resource(appConfig.backendUrl + "/edu/:id", {
				id: "@id"
			}, {

				});
		}
		return null;
	});

var slideNumber = 1, swiperList, swiperMenu;
function initMenuSwiper(seletor) {
	swiperMenu = new Swiper(seletor, {
		slidesPerView: '3'

	});
}

var refreshHeight = 50;
function initSwiper(seletor) {
	var vh = $(window).height();

	var holdPosition, holdPositionBottom;
	// console.log(vh);
	$(seletor).height(vh - 10);
	swiperList = new Swiper(seletor, {
		slidesPerView: 'auto',
		mode: 'vertical',
		watchActiveIndex: true,
		onResistanceBefore: function (s, pos) {
			holdPosition = pos;
			// console.log("onResistanceBefore:", holdPosition);

		},
		onResistanceAfter: function (s, pos) {
			holdPositionBottom = pos;
			// console.log("onResistanceAfter:", pos);
			if (pos > 100) {
				// pullUp(swiperList);
			}
			// popupAlert(holdPositionBottom);
		},
		onTouchMove: function (swiper) {
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
		onTouchStart: function () {

			holdPosition = 0;
			holdPositionBottom = 0;
		},
		onTouchEnd: function () {
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
		$.each(datas, function (i, e) {
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
	for (var i = 0; i < swiper.slides.length; i++) {
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

	getMoreList(swiper, function () {

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
	$scope.$apply(function () {
		$scope.page = 1;
		$scope.getList(id, function () {

			$('#pullDown').removeClass('visible');

			swiperList.setWrapperTranslate(0, 0, 0);
			swiperList.params.onlyExternal = false;
			swiperList.updateActiveSlide(0);

		});
	});
}

function getMoreList(swiper, loadDone) {
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function () {

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
	$scope.$apply(function () {

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
	window.open('javascript:window.name;', '<script>location.replace("' + full_link + '")<\/script>');
}

function open_new_window(full_link) {
	window.open('javascript:window.name;', '<script>location.replace("' + full_link + '")<\/script>');
}

function open_without_referrer(link) {
	document.body.appendChild(document.createElement('iframe')).src = 'javascript:"<script>top.location.replace(\'' + link + '\')<\/script>"';
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
			+ "\")' rel='noreferrer' href='" + obj.URL + "'> <span "
			+ " class=\"rightico pull-right\"> <span "
			+ " class=\"glyphicon glyphicon-chevron-right line-height-4  \"></span></span> "
			+ " <h5 class=\"list-group-item-header\">"
			+ obj.Title
			+ "</h5> "
			+ "  <h5 class=\"list-group-item-header margin-top-5 \"><span class=\"small\">"
			+ "" + "</span></h5> "
			+ " <p class=\"list-group-item-text small time\"><span>"
			+ "" + "</span>&nbsp;" + obj.UpdateDate + "</p> "

			+ " </a> ";


		if (obj.Keys) {
			itemHtml += "<div class='kws clearfix'>";
			$
				.each(
				obj.Keys,
				function (i, e) {
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
