var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', ["ngResource","xeditable"]);
app.run(['editableOptions', function(editableOptions) {
	  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
	}]);
app.controller('eduCtrl', function ($scope, eduSrv) {

});

/*document.addEventListener("deviceready", function() {
	console.log("deviceready2=======: ");
	init();
});*/

$(function () {
	/*if (clinetType == "Android" || clinetType == "http") {
		console.log("deviceready1=======: ");
		

	}*/

	
	loadMenuTree("");
	
	init();
	
	var $scope = angular.element(ngSection).scope();
	kvalidate.init(
			$("#fm"),
			{
				s_account: {
					required: true,
				//	version4: true,
					//minlength:4,
				},
				user_name:"required",
				s_pass:{
					required: true,
				},
				desc_info:{
					required: true,
					/*digits:true,
					min:1,
					max:1000,*/
				}
			},
			{
				s_account: {
					required: "用户ID必须填写",
				//	version4: "版本号格式错误*.*.*.*",
					//minlength:"至少4个字符"
				},
				
				user_name:"请输入密码",
				s_pass:
					{required: "请输入密码",},
			desc_info:{
					required: "请输入描述",
					/*digits:"请输入数字",
					min:"最小为1",
					max:"最大为1000",*/
				}
			},
			$scope.doupdate,
			""
		);

});



function changerows(option) {
	var num = $(option).val();
	//msg(num);

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function () {
		$scope.rows = num;
		$scope.getList();
	});
}


function init() {

	initmenu($("#menuul"),"page/user/");
	
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


		$scope.checkcity=function(index,data)
		{
			//alert(index+"/"+data);
			//alert($scope.datalist[index].city);
			
			var obj={};
			obj.recordid=$scope.datalist[index].recordid;
		
			obj.city= data;// $("#s_city").val();  //$scope.s_city;
			$scope.doupdate(this,obj);
			
		}
		
		
		$scope.cpTypes = [{
			"id": 0,
			"text": "江苏"
		}, {
			"id": 2,
			"text": "浙江",
			"selected": true
		}, {
			"id": 3,
			"text": "山东"

		}, {
			"id": 4,
			"text": "安徽"

		}];

		$scope.selType = 1;

		//$("#myModal").draggable();
		//	$("#myModal").resizable();
		$("#cpType").select2({

			"placeholder": "请选择类型",
			"allowClear": false,
			"minimumResultsForSearch": Infinity,
			"data": $scope.cpTypes
		});

		$("#cpType2").select2({

			"placeholder": "请选择类型",
			"allowClear": false,
			"minimumResultsForSearch": Infinity,
			"data": $scope.cpTypes
		});

		$scope.load = function (type) {

			window.location.href = "../../page/" + type;
		};

		
		
		$scope.del=function(item)
		{
			if(item==null)
				return;
			
			var id= item.recordid;
			
			
			
		$("#myModal3").modal("show");
		$("#btnconfirm").one("click",function ()
		{
		
			
			
			
			var obj={};
			
			obj.recordid=id;
			
				SZUMWS(http + "user/del.action", JSON
						.stringify(obj), function succsess(json) {

					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
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
					//$("#myModal2").modal('hide');
					
					

				}, false, false

				);
				
				//$("#btnconfirm").unbind("click",delfun(id));
				
				
		});
		
		}
		
		$scope.addOrModify=function(item)
		{
			kvalidate.resetForm("#fm");
			if(item!=null)
				{
				$scope.edit="编辑";
				$scope.s_account=item.accountid;
				$scope.s_pass=item.pass;
				$scope.user_name=item.user_name;
				$scope.desc_info=item.desc_info;
				$scope.s_recordid=item.recordid;
				//$scope.s_ip_refresh=parseInt(item.ip_refresh_interval);
				
				loadMenuTree(item.recordid);
				
				
				$("#s_account").attr('disabled','');
				
				}
			else
				{
				$scope.edit="新增";
				$("#s_account").removeAttr('disabled');
				
				loadMenuTree("");
				
				
				$scope.s_account="";
				$scope.s_pass="";
				$scope.desc_info="";
				$scope.user_name="";
				
				//$("#s_company").val("");
				
				$scope.s_recordid="";
				
				}
			
			
			$scope.getcompayList();
			$("#myModal2").modal('show');
		}
		var http = getImUrl();// "";

		
		$scope.doupdate=function(fm,value){
			
			var obj={};
			
			if(typeof(value)=="undefined")
			{
			obj.recordid=$scope.s_recordid;
			obj.accountid=$scope.s_account;
			obj.pass=$scope.s_pass;
			//obj.company_userid= $("#s_company").val();   //$scope.s_company;
			//obj.city=  $("#s_city").val();  //$scope.s_city;
			obj.desc_info=$scope.desc_info;
			obj.user_name=$scope.user_name;
			}
			else
				{
				obj=value;
				}

		
			
			var roleids= getSelectIds();
			
			obj.roleids=roleids;
			
			
			SZUMWS(http + "user/addOrUpdate.action", JSON
					.stringify(obj), function succsess(json) {

				var code = json.ResponseCode;
				var message = json.ResponseMsg;
				console.log('-----return -code= ' + code + ';message= '
						+ message);
				if (code == 200) {
		
					
					
					$("#myModal2").modal('hide');
						
					$scope.getList();
					setTimeout(function(){
					$scope.s_account="";
					$scope.s_pass="";
					$scope.s_company="";
				
					$scope.s_ip_refresh="";
					
					$scope.$apply();
					},10);

				} else {
					msg(message);
				}
/*
				if (fucOnFinished != null)
					fucOnFinished();*/
				
			

			}, function error(data) {
				msg("网络异常!");
				//$("#myModal2").modal('hide');
				
			/*	if (fucOnFinished != null)
					fucOnFinished();*/

			}, false, 'json'

			);
			
			
		}
		
		$scope.update=function()
		{
			
			/*if($scope.fm.s_account.$error.required)
				{
				error("用户ID必须要填写");
				return;
				}
			if($scope.fm.s_pass.$error.required)
			{
				error("密码必须要填写");
			return;
			}
			
			if($scope.fm.s_company.$error.required)
			{
				error("公司必须要填写");
			return;
			}

			if($scope.fm.s_ip_refresh.$invalid)
			{
				error("刷新时间为数字");
			return;
			}*/
			
			kvalidate.validate("#fm");
			
		
		}



		$scope.citys_select = ['上海', '南京', 'nanjing', '扬州', '苏州'];

		$scope.getcompayList = function (id, fucOnFinished, clear) {

			var http = getImUrl();// "";

			var obj = new Object();

			//obj.deviceid = $scope.id;// "12345678";
			//obj.ip = $scope.ip;
			//obj.compy_name = $scope.compy_name;
			obj.page = 1;// 1;// "12345678";
			obj.rows = 40;// 10;// "12345678";

			SZUMWS(http + "user/getInfoList.action", JSON
				.stringify(obj), function succsess(json) {

					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
						+ message);
					if (code == 200) {


						$scope.compays_select = eval(json.datalist);

						 setTimeout(function(){
								
							  // $('#p_proxyserver_id').find("option:selected").attr("selected", false);
							   $('#s_company').get(0).selectedIndex=1;
							   // alert($("#p_proxyserver_id").val());
							    $scope.s_company=$("#s_company").val();
							  // $scope.apply();
						   }, 30);
						

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
		//$scope.getcompayList();



		$scope.title = "用户账号管理";
		//$scope.curpage=1;
		$scope.page = 1;
		$scope.rows = 10;

		$scope.rows_select = [5, 10, 20];
		  setTimeout(function(){
			   $("div.tablefoot select").val($scope.rows);
		   }, 50);
		$scope.pageData = [];



		$scope.getList = function (id, fucOnFinished, clear) {

			$scope.page = (id != null) ? id : 1;


			if ($scope.page > $scope.pageNum)
				$scope.page = $scope.page - 1;

			if ($scope.page <= 0)
				$scope.page = 1;


			// alert($scope.etId);

			//if ($scope.lastSecondID != $scope.etId) {

			//	$scope.page = 1;// // 1;// "12345678";
			// 10;// "12345678";
			//	}

			//	$scope.lastSecondID = $scope.etId;

			var http = getImUrl();// "";

			var obj = new Object();
			obj.city = $scope.city;// "12345678";
			obj.id = $scope.id;// "12345678";
			obj.ip = $scope.ip;
			obj.compy_name = $scope.compy_name;



			obj.page = $scope.page;// 1;// "12345678";
			obj.rows = $scope.rows;// 10;// "12345678";
			SZUMWS(http + "user/getInfoList.action", JSON
				.stringify(obj), function succsess(json) {
					// var json = JSON.parse(decryData);
					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
						+ message);
					if (code == 200) {


						$scope.datalist = eval(json.datalist);

						$scope.total = json.total;
						$scope.pageDataPre = [];
						$scope.pageDataAft = [];
						$scope.pageNum = Math.ceil($scope.total / $scope.rows);// + ($scope.total%$scope.rows)>0?1:0;

						for (var i = $scope.page - 3; i < $scope.page; i++) {
							if (i > 0)
								$scope.pageDataPre.push(i);
						}
						for (var i = $scope.page + 1; i < $scope.page + 3; i++) {
							if (i <= $scope.pageNum)
								$scope.pageDataAft.push(i);
						}
						/*
						for(var i =1;i <= $scope.pageNum;i++){
							if(i<$scope.page)
							   $scope.pageDataPre.push(i);
							else if(i>$scope.page)
								$scope.pageDataAft.push(i);
							};*/


						$scope.$apply();

						console.log('-----guideList -OK= ');

					} else {
						msg(message);
					}

					if (fucOnFinished != null)
						fucOnFinished();

					//$('#refresh').removeClass('visible');
					//$('#refresh2').removeClass('visible');

				}, function error(data) {
					msg("网络异常!");

					if (fucOnFinished != null)
						fucOnFinished();

					//	$("#refresh").removeClass('visible');
					//$('#refresh2').removeClass('visible');

				}, false, false

			);

		}
		//$scope.apply();

		$scope.getList();

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
	//alert(location)
	//window.location.href=location;
	open_without_referrer(location);
	//open_new_window(location);
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


		/*onclick='open_new_window(\""
			   + encodeURI( obj.URL)*/


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
