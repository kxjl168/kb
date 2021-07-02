var app = angular.module('myApp', [ "ngSanitize" ]);

app.controller('eduCtrl', function($scope) {

});

$(function() {

	init();

	hljs.initHighlightingOnLoad();

	var key = GetQueryString("keyword") || $("#kwd").val();
	if (key) {
		var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.kwd = key;
			$scope.search();
		});
	}
	;

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#mformApply"), {
		nickname : {
			required : true,
			maxlength : 40,

		},
		email : {
			email :true,
			required : true,
			maxlength : 100,

		},
		context : {
			required : true,
			minlength : 1,
			maxlength : 500,
		}

	}, {
		nickname : {
			required : "大侠请留名！",
			maxlength : "字数有点太多了-_-！",
		},
		email : {
			required : "不留下邮箱接收码嘛?！",
			maxlength : "邮箱用于接收搜索码通知-_-！",
		},
		context : {
			required : "不说点啥? ",
			minlength : "再多写两个字呗 ",
			maxlength : "字数有点太多了-_-！",

		}
	}, applyKey, "", "");

});

function changerows(option) {
	var num = $(option).val();

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
};

function showApply() {
	$("#myModalApply").modal("show");
}

function applyKeyValidate() {
	kvalidate.validate("#mformApply");
}

function applyKey() {
	
	var data = {};
	data.email=escape($("#email").val());
	data.context=escape( $("#context").val());
	data.nickname=escape($("#nickname").val());
	
	$.ajax({
		type : "post",
		url : getImUrl() + "search/applyKey",
	    data : data,
		//async : true,
		dataType : "json",
		success : function(response) {

			if (response.ResponseCode == 200) {
				$("#myModalApply").modal("hide");
				msg(response.ResponseMsg);
				
			} else {
				msg(response.ResponseMsg);
			}
		},
		error:function(a,b,c)
		{
			console.log(a);
		}
		
		
	});
};



//DES加密
function encryptByDES(message, key){
  var keyHex = CryptoJS.enc.Utf8.parse(key);
  var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
  });
  return encrypted.ciphertext.toString();
}
//DES解密
function decryptByDES(ciphertext, key){
  var keyHex = CryptoJS.enc.Utf8.parse(key);
  var decrypted = CryptoJS.DES.decrypt({
      ciphertext: CryptoJS.enc.Hex.parse(ciphertext)
  }, keyHex, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
  });
  var result_value = decrypted.toString(CryptoJS.enc.Utf8);
  return result_value;
}

function init() {

	$("#btnApplyitem").click(function() {
		applyKeyValidate();
	});

	initmenu_p($("#menuul"), "public/search/");

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

				var http = getImUrl();

				$scope.init = function() {
					$("#srzt").on("click", "a", function(e) {

						var url = e.currentTarget.href;
						if (url.indexOf("javascript") > -1) {

						} else {
							// msg(url);
							$scope.stopDefault(e);
							
							
							$scope.search(url);
						}

					});
				};
				$scope.init();

				$scope.replay = function(x) {

					kvalidate.validate("#fm");

				};

				$scope.title = siteurl;

				$scope.page = 1;
				$scope.rows = 10;

				var title = "GOOGLE SEARCH";
				$("#title").html(title);

				// document.title=title;*/

				/*
				 * var meta = document.getElementsByTagName('meta');
				 * meta["keywords"].setAttribute('content',$scope.x.tags);
				 */
				$("#kwd").bind('keypress', function(event) {
					if (event.keyCode == "13") {
						$scope.search();
					}
				});

				$scope.init = function(url) {

					var obj = {};

					SZUMWS(http + "search/init.action", JSON.stringify(obj),
							function succsess(json) {

								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								// console.log('-----return -code= ' + code +
								// ';message= '
								// + message);
								if (code == 200) {

								} else {
									msg(message);
								}

							}, function error(data) {
								msg("net work error！");

							}, false, false

					);

				};
				$scope.init();

				$scope.lastData = [];
				$scope.back = function() {
					var last = "";
					if ($scope.lastData.length > 0) {
						last = $scope.lastData.pop();

						$("#srzt").html(last);
					}

					else {
						msg("已经无法后退啦~");
					}

				};

				$scope.search = function(url) {

					var obj = {};
					obj.gkey=$("#gkey").val();

					if (typeof (url) == "undefined")
						obj.keyword = $scope.kwd;
					else
						{
						

						// var signurl =encryptByDES( url,"kxjl");
						 
						 obj.url = signurl;
						 
						}
						

					SZUMWS(http + "search/dosearch.action",
							JSON.stringify(obj), function succsess(json) {

								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								// console.log('-----return -code= ' + code +
								// ';message= '
								// + message);
								if (code == 200) {

									var rst = $(json.html)
											.find("#content_left").html();
									var rstpage = $(json.html).find("#page")
											.html();

									var rcnt = $(json.html).find("#rcnt")
											.html();

									if (typeof (rst) != 'undefined')
										$("#srzt").html(rst + rstpage);
									else if (typeof (rcnt) != "undefined") {
										try {
											$("#srzt").html(json.html);
										} catch (e) {
											// TODO: handle exception
										}
										;

										// google
										$("#srzt").find("#searchform")
												.addClass("hide");
										$("#srzt").find("#footcnt").addClass(
												"hide");
										$("#srzt").find(".hdtb-msb").addClass(
												"hide"); 
										$("#srzt").find("#hdtb-msb").children()
												.eq(1).hide();

										if ($scope.lastData.length < 10)
											$scope.lastData.push($("#srzt")
													.html());
									}

									else
										$("#srzt").html(json.html);

									$scope.$apply();

								} else {
									error(message);
								}

							}, function error(data) {
								msg("net work error！");

							}, true, false

					);

				};

				$scope.stopDefault = function(e) {
					if (e && e.preventDefault)
						e.preventDefault();
					else

						window.event.returnValue = false;
					return false;
				};

				$scope.canc = function(event, x) {
					var row = $("#rpdiv");

					kvalidate.resetForm("#fm");
					$("#rdivc").after(row);
					$("#cbtn").addClass("hide");
				};

				$scope.beginReplay = function(event, x) {
					$scope.preplay = x;
					var row = $("#rpdiv");

					($(event.target).parent()).after(row);

					$("#cbtn").removeClass("hide");

				};

			});

};

app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
