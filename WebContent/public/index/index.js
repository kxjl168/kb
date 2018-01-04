
		
		
	

var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', [ "ngResource", "xeditable" ,"ngSanitize"]);
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
	
	

	

	init();
	 $('pre code').each(function(i, block) {
         hljs.highlightBlock(block);
     });
	 
	 //setTimeFormat("effectDate", null, null);
	
	 $("#div1").datepicker();
	 // WdatePicker({eCont:'nDate2'});
	  //WdatePicker({eCont:'div1',onpicked:function(dp){alert('你选择的日期是:'+dp.cal.getDateStr())}})
	 
	//$('#s_context').ckeditor();


	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_title : {
			required : true,
		// version4: true,
		// minlength:4,
		},
		s_context : "required",
		
	}, {
		s_title : {
			required : "标题必须填写",
		// version4: "版本号格式错误*.*.*.*",
		// minlength:"至少4个字符"
		},

		s_context : "请输入内容",
		
	}, $scope.doupdate, "");

});

function changerows(option) {
	var num = $(option).val();
	// msg(num);

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
}

function init() {

	initmenu_p($("#menuul"), "pulbic/index/");


	$('.modal').on('show.bs.modal', function() {
		$(this).draggable();
		$(this).css("overflow-y", "scroll"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	});
	
	initQuery();

	

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
