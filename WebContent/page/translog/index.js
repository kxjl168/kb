var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', [  ]);
app.controller('eduCtrl', function($scope ) {

});

/*document.addEventListener("deviceready", function() {
	console.log("deviceready2=======: ");
	init();
});*/

$(function() {
	/*if (clinetType == "Android" || clinetType == "http") {
		console.log("deviceready1=======: ");
		

	}*/
	
	initDateSelect();
	
	
	init();

});

function changerows(option)
{
	var num= $(option).val();
	//msg(num);
	
		 var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.rows=num;
			$scope.getList();
		});
}



function init() {
	
	initmenu($("#menuul"),"page/translog/");
	
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {

		
		$('#collapseOne').on('shown.bs.collapse', function () {
		    // 执行一些动作...
			$("#titlepic").attr("class", "glyphicon glyphicon-chevron-up pull-right"); 
		});
		
		$('#collapseOne').on('hidden.bs.collapse', function () {
			$("#titlepic").attr("class", "glyphicon glyphicon-chevron-down pull-right"); 
	});
		
		
		
	
		$scope.load = function(type) {

			window.location.href="../../page/"+type;
		};
		
		
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
			   $("#dateType").get(0).selectedIndex=1;
			   $("#dateType").trigger('change');
			   
				$scope.getList();
		   }, 50);
	

		$scope.title="爬虫日志";
		//$scope.curpage=1;
		$scope.page=1;
		$scope.rows=10;
		
		$scope.rows_select=[5,10,20];
		  setTimeout(function(){
			   $("div.tablefoot select").val($scope.rows);
		   }, 50);
		$scope.pageData=[];
		
	
		
		$scope.getList = function(id, fucOnFinished, clear) {

			
			
			
			$scope.page = (id != null) ? id :1;
			
			
			if($scope.page>$scope.pageNum)
				$scope.page=$scope.page-1;
			
			if($scope.page<=0)
				$scope.page=1;
			
			
			// alert($scope.etId);

			//if ($scope.lastSecondID != $scope.etId) {

			//	$scope.page = 1;// // 1;// "12345678";
				// 10;// "12345678";
		//	}

		//	$scope.lastSecondID = $scope.etId;

			var http = getImUrl();// "";

			var obj = new Object();
			obj.url = $scope.url;// "12345678";
			obj.head = $scope.head;// "12345678";
			
			obj.date_type = $("#dateType").val();
			obj.starttime = $("#effectDate").val();
			obj.endtime = $("#effectDate2").val();
			//obj.showall = $scope.showall?"false":"true";
			
			//msg(obj.showall);
			
			
			obj.page = $scope.page;// 1;// "12345678";
			obj.rows = $scope.rows;// 10;// "12345678";
			SZUMWS(http + "translog/getInfoList.action", JSON
					.stringify(obj), function succsess(json) {
				// var json = JSON.parse(decryData);
				var code = json.ResponseCode;
				var message = json.ResponseMsg;
				console.log('-----return -code= ' + code + ';message= '
						+ message);
				if (code == 200) {

			
					$scope.datalist =eval(json.datalist);

					$scope.total = json.total;
					$scope.pageDataPre=[];
					$scope.pageDataAft=[];
					$scope.pageNum=Math.ceil( $scope.total/$scope.rows);// + ($scope.total%$scope.rows)>0?1:0;
					
					for(var i =$scope.page-3;i<$scope.page;i++){
						if(i>0)
						   $scope.pageDataPre.push(i);
					}
					for(var i =$scope.page+1;i<$scope.page+3;i++){
						if(i<=$scope.pageNum)
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
			"ResponseCode" : 200,
			"ResponseMsg" : "OK",
			"GuideTypes" : [ {
				"Name" : "全部",
				"Id" : "1"
			}, {
				"Name" : "国家级",
				"Id" : "2"
			}, {
				"Name" : "省级",
				"Id" : "3"
			}, {
				"Name" : "地市级",
				"Id" : "3"
			}, {
				"Name" : "区县级",
				"Id" : "4"
			}, {
				"Name" : "国家级2",
				"Id" : "5"
			}, {
				"Name" : "省级3",
				"Id" : "6"
			}, {
				"Name" : "地市级4",
				"Id" : "7"
			}, {
				"Name" : "区县级5",
				"Id" : "8"
			} ]
		};
		$scope.respData2 = {
			"ResponseCode" : "200",
			"GuideList" : [ {
				"Id" : "8",
				"Time" : "2016-05-12",
				"Person" : "刘卡",
				"UpdateDate" : "2016-05-12",
				"Type" : "2",
				"Title" : "深交罚决字第Z005139722",
				"Title2" : "刘卡未取得相应从业资格证件，驾驶道路路客货运输"
			}, {
				"Id" : "9",
				"Time" : "2016-05-02",
				"Person" : "刘卡",
				"Type" : "2",
				"UpdateDate" : "2016-05-12",
				"Title" : "深交许可字第Z0051397",
				"Title2" : "刘卡未取得相应从业资格证件，驾驶道路路客货运输"
			}, {
				"Id" : "10",
				"Time" : "2016-04-22",
				"Person" : "张三",
				"Type" : "1",
				"UpdateDate" : "2016-05-12",
				"Title" : "交通许可许可字第Z0051397",
				"Title2" : "测试申请通过从业资格证件 "
			}, {
				"Id" : "11",
				"Person" : "刘卡",
				"UpdateDate" : "2016-05-12",
				"Time" : "2016-04-22",
				"Type" : "1",
				"Title" : "许可xxxxZ0051397",
				"Title2" : "教师许可从业资格证件"
			} ],
			"ResponseMsg" : "OK",
			"Total" : 3
		};

		initMenuSwiper('#swiper_menu');
		initSwiper('#swiper_list');

		$scope.btnP = function() {

			if (devMode == 0) {
				// $scope.guideList = $scope.respData2.GuideList;

				$scope.guideList = [];

				$.each($scope.respData2.GuideList, function(i, e) {
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
		
		$scope.Search = function() {

			$scope.Key = $("#key").val();
			
			if (devMode == 0) {
				// $scope.guideList = $scope.respData2.GuideList;

				$scope.guideList = [];

				$.each($scope.respData2.GuideList, function(i, e) {
					if (e.Title.indexOf($scope.Key) >0||e.Title2.indexOf($scope.Key) >0)
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

		$scope.btnF = function() {

			if (devMode == 0) {
				// $scope.guideList = $scope.respData2.GuideList;

				$scope.guideList = [];

				$.each($scope.respData2.GuideList, function(i, e) {
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

		$scope.getList = function(id, fucOnFinished, clear) {

			$scope.etId = (id != null) ? id : $scope.etId;
			// alert($scope.etId);

			if ($scope.lastSecondID != $scope.etId) {

				$scope.page = 1;// // 1;// "12345678";
				// 10;// "12345678";
			}

			$scope.lastSecondID = $scope.etId;

			var http = getImUrl();// "";

			var obj = new Object();
			obj.GuideType = $scope.type;// "12345678";
			obj.SecondType = $scope.etId;// "12345678";

			obj.client_type = getClient_type();

			obj.Type = $scope.Type;
			obj.Key =$("#key").val();
			obj.cgtype =1;
			obj.srctype =1;
			
			
			obj.Index = $scope.page;// 1;// "12345678";
			obj.PageNum = $scope.numPerpage;// 10;// "12345678";
			SZUMWS(http + "Sw/SearchCg.action", JSON
					.stringify(obj), function succsess(json) {
				// var json = JSON.parse(decryData);
				var code = json.ResponseCode;
				var message = json.ResponseMsg;
				console.log('-----return -code= ' + code + ';message= '
						+ message);
				if (code == 200) {

					if (clear == null)
						// 清空
						clearSwiperData(swiperList);

					$scope.guideList = json.GuideList;

					$scope.Total = json.Total;

					if ($scope.page == 1)
						swiperList.setWrapperTranslate(0, 0, 0);
					addSwiperData(swiperList, $scope.guideList, true, 1);
					hisDatas = $scope.guideList;

					console.log('-----guideList -OK= ');

				} else {
					popupAlert(message);
				}

				if (fucOnFinished != null)
					fucOnFinished();

				$('#refresh').removeClass('visible');
				$('#refresh2').removeClass('visible');

			}, function error(data) {
				popupAlert("网络异常!");

				if (fucOnFinished != null)
					fucOnFinished();

				$("#refresh").removeClass('visible');
				$('#refresh2').removeClass('visible');

			}, true, false

			);

		}

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

		$scope.openKey = function(id, url, source, servcieid, appName, appType,
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
								text : "请实名登录后查看!",
								layout : "center",
								timeout : 1000,
								type : "error"
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
app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
