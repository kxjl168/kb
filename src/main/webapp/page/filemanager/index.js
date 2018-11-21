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
		oldname:"required",
		
	}, {
		s_dict_key : {
			required : "请输入KEY",
		// version4: "版本号格式错误*.*.*.*",
		// minlength:"至少4个字符"
		},

		s_dict_name : "请输入NAME",
		s_sort : {
			required : "请输入排序号",
		},
		oldname:"请选择图标文件",
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

	initmenu($("#menuul"), "page/filemanager/");

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

				
				$scope.load = function(type) {

					window.location.href = "../../page/" + type;
				};
				$scope.update=function(){
					$("#myModal2").modal('hide');
					$scope.getList();
				};
				
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
					

					} else {
						$scope.edit = "新增";
					/*	$("#s_dict_key").removeAttr('disabled');
						$scope.s_recordid = "";
						$scope.s_dict_key = "";
						$scope.s_dict_name = "";
						
						$scope.url = "";
						$scope.desc_info="";
						$scope.oldname="";						
						$scope.s_sort = "";
						$scope.fullurl = "";*/
						
						
						
						
						$kfile.init({
							fileUploadname:"fileUploadURL", //上传文件的name
							httppath:$scope.prepath,  //img -static目录前缀
							isimg:false,
							filesufix:'',
							maxFileSize:30*1024*1024,//30M
							maximgupload : 1,//最多可上传图片数量
							uploadurl:http + 'UploadFileXhr.action',//上传图片action url
							container:$("body").find('#filedv'), //图片容器
						});
						$kfile.get("filedv").initFile(function(){
							/*if(json.fileinfo)
								{
								var finfo =json.fileinfo;
							$kfile.get("filedv").addFile(finfo.id,json.httppath+finfo.http_relative_path);
								}*/
						});
						
					}

				
					$("#myModal2").modal('show');
				}

				$scope.del = function(item) {
					if (item == null)
						return;

					var id = item.id;

					$("#myModal3").modal("show");
					$("#btnconfirm").one(
							"click",
							function() {

								var obj = {};

								obj.id = id;

								SZUMWS(http + "filemanager/del.action", JSON
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

										}, false, "json"

								);

								// $("#btnconfirm").unbind("click",delfun(id));

							});

				}
				
				$scope.selectfile = function () {
					$("#myModal1").modal('show');
				}

				
				var http = getImUrl();// "";

				

				// $scope.getcompayList();

				$scope.title = "附件管理";
				// $scope.curpage=1;
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

		
					var http = getImUrl();// "";

					var obj = new Object();
					obj.name = $scope.q_name;// "12345678";

					obj.page = $scope.page;// 1;// "12345678";
					obj.rows = $scope.rows;// 10;// "12345678";
					SZUMWS(
							http + "filemanager/getInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
								// var json = JSON.parse(decryData);
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

									$scope.datalist = eval(json.datalist);
									$scope.prepath = json.prepath;
									
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

								

								} else {
									msg(message);
								}

								if (fucOnFinished != null)
									fucOnFinished();

			

							}, function error(data) {
								msg("网络异常!");

								if (fucOnFinished != null)
									fucOnFinished();

							}, false, "json"

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
