var tipTimeout = null;
function showPopover(target, msg, type) {

	clearTimeout(tipTimeout);

	target.attr("data-original-title", msg);
	$('[data-toggle="tooltip"]').tooltip();
	var tp = target.tooltip('show');

	$($(document.body).find(".tooltip")[0]).removeClass("success").removeClass(
			"error");
	if (typeof (type) != "undefined")
		$($(document.body).find(".tooltip")[0]).addClass(type);

	$($(document.body).find(".tooltip")[0]).css("z-index", 1100);

	target.focus();

	// 2秒后消失提示框
	tipTimeout = setTimeout(function() {
		 target.attr("data-original-title", "");
		 target.tooltip('hide');
	}, 2000);
};

function cconfirm(msg, donecallback, cancelcallback) {
	swal({
		title : '确定执行操作吗？',
		text : msg,// '你将无法恢复它！',
		type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : '确定',
		cancelButtonText : '取消',
		confirmButtonClass : 'btn btn-success',
		cancelButtonClass : 'btn btn-danger',
		buttonsStyling : true,
		allowOutsideClick : false,// 如果设置为false，用户无法通过点击弹窗外部关闭弹窗。
		allowEscapeKey : false, // 如果设置为false，用户无法通过按下Escape键关闭弹窗。
		allowEnterKey : false,
	}).then(function(rst) {

		if (rst.value) {
			if (typeof (donecallback) == "function")
				donecallback();
		}

		if (rst.dismiss) {
			if (typeof (cancelcallback) == "function")
				cancelcallback();
		}

		/*
		 * swal( '已删除！', '你的文件已经被删除。'+rst.dismiss, 'success' );
		 */
	})
};



function success(msg) {
	/*
	 * swal({ title: "操作成功", text: msg, timer: 500, type:"success",
	 * showConfirmButton: false });
	 * 
	 * return;
	 */

	// swal("干得漂亮！", "你点击了按钮！","success")
	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}

	showPopover($(target), msg, "success");
};

function msg(msg) {
	/*
	 * swal({ title : "提示信息", text : msg, // timer: 1000, type : "info",
	 * showConfirmButton : true });
	 * 
	 * return;
	 */

	success(msg);
};

function info(msg) {

	/*
	 * swal({ title : "提示信息", text : msg, // timer: 1000, type : "info",
	 * showConfirmButton : true });
	 * 
	 * return;
	 */

	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}
	;

	showPopover($(target), msg);
};

function error(msg) {
	/*
	 * swal({ title: "操作异常", text: msg, timer: 1000, type:"error",
	 * showConfirmButton: false });
	 * 
	 * return;
	 */

	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}
	;

	showPopover($(target), msg, "error");
};

gourl = function(e) {

	var url = $(e).attr("href");

	var row = $("#rpdiv");

	if (url.indexOf("http") > -1) {
		$("#myModal_outurl").modal("show");
		$("#btnconfirm_outurl").one("click", function() {
			$("#myModal_outurl").modal("hide");
			window.open(url, 'new', "");
		});
		return false;
	} else {

		return false;

	}
	;

};


function cconfirm2(msg, donecallback, cancelcallback) {
	
		
		$("#myModal_outurl").find (".msgtitle").html(msg);
		
		$("#myModal_outurl").modal("show");
		$("#btnconfirm_outurl").one("click", function() {

			$("#myModal_outurl").modal("hide");
			if(typeof(donecallback)=="function")
				donecallback();
		});
		return false;
	
}

function loadMobile(browser){
	
	
	
    var $overlay = $("#ftop");
    var $header = $(".mobile.navbar-brand.toptitle");
    
 
    window.onscroll = function(){
        var scrollTop = document.documentElement.scrollTop + document.body.scrollTop;
        if(!!browser.versions.mobile || $(window).width() < 800){
 	       
	    
        if(scrollTop >= 69){
            $overlay.addClass("fixed");
        }else{
            $overlay.removeClass("fixed");
        }
        if(scrollTop >= 160){
            $header.removeClass("hide").addClass("fixed");
        }else{
            $header.addClass("hide").removeClass("fixed");
        }
        } 
    };
}

$(function() {

	 var browser = {
		        versions: function() {
		        var u = window.navigator.userAgent;
		        return {
		            trident: u.indexOf('Trident') > -1, //IE内核
		            presto: u.indexOf('Presto') > -1, //opera内核
		            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
		            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
		            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
		            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
		            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
		            iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者安卓QQ浏览器
		            iPad: u.indexOf('iPad') > -1, //是否为iPad
		            webApp: u.indexOf('Safari') == -1 ,//是否为web应用程序，没有头部与底部
		            weixin: u.indexOf('MicroMessenger') == -1 //是否为微信浏览器
		            };
		        }()
		    };
	 
	 loadMobile(browser);

	
	var http = getImUrl();

	var obj = new Object();

	
	
	
	SZUMWS(http + "sysBaseInfo/getSysInfo.action", JSON.stringify(obj),
			function succsess(json) {

				var code = json.ResponseCode;
				var message = json.ResponseMsg;

				if (code == 200) {

					if (json.visitData) {
						var vdata =json.visitData;

						$(".pagevisit").html(vdata.pageVisitNum);
						$(".uservisit").html(vdata.userVisitNum);
					}

				

					if (json.fileinfo) {
						var finfo =json.fileinfo;
						$(".phead").attr("src",
								json.httppath + finfo.http_relative_path);
						
					}
					if(json.sign)
						$(".short-about p").html(json.sign);

				} else {
					msg(message);
				}

			}, function error(data) {
				//("网络异常!");

			}, false, "json"

	);
});


/** kwindow */
(function(window) {

	window.$w = {
		scrollTop : function(val) {
			if (typeof val == 'undefined') {
				return window.pageYOffset || document.documentElement.scrollTop
						|| document.body.scrollTop || 0;
			} else {
				if (typeof (window.pageYOffset) != 'undefined')
					window.pageYOffset = val;
				if (typeof (document.documentElement.scrollTop) != 'undefined')
					document.documentElement.scrollTop = val;
				if (typeof (document.body.scrollTop) != 'undefined')
					document.body.scrollTop = val;

			}
		}

	};

})(window);

function GetQueryString(name) {

	var index = window.location.href.lastIndexOf("/");
	var indexj = window.location.href.lastIndexOf("#");

	// 最后一个/开始 截取#前面的，兼容history.js html4 url
	var searchpath = window.location.href.substr(index + 1);
	if (indexj > 0)
		searchpath = window.location.href.substr(index + 1, indexj - index - 1);

	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$|#)", "i");

	var r = searchpath.substr(1).match(reg);

	if (r != null)

		return decodeURI(r[2]);

	return null;
}

function cancelBubble(e) {
	var evt = e ? e : window.event;
	if (evt.stopPropagation) { // W3C
		evt.stopPropagation();
	} else { // IE
		evt.cancelBubble = true;
	}
}

function setTimeFormat(id, min, max) {
	var datetype = $("#dateType").val();

	var startDate = '%yyyy-%MM-%dd %HH';
	var dateFmt = 'yyyy-MM-dd HH';

	// startDate = '%HH:%mm';
	// dateFmt = 'HH:mm';

	datetype = "DAY";

	if (datetype == "HOUR") {
		startDate = '%yyyy-%MM-%dd %HH';
		dateFmt = 'yyyy-MM-dd HH';
	} else if (datetype == "DAY") {
		startDate = '%yyyy-%MM-%dd';
		dateFmt = 'yyyy-MM-dd';
	} else if (datetype == "MONTH") {
		startDate = '%yyyy-%MM';
		dateFmt = 'yyyy-MM';
	} else if (datetype == "TIME") {
		startDate = '%HH:%mm:%ss';
		dateFmt = 'HH:mm:ss';
	}

	var param = {
		startDate : startDate,
		dateFmt : dateFmt,
		el : id
	};
	if (min != null)
		param.minDate = '#F{$dp.$D(\'' + min + '\')}';
	if (max != null)
		param.maxDate = '#F{$dp.$D(\'' + max + '\')}';

	WdatePicker(param);

}

function getNowFormatDate() {
	var curDate = new Date();

	// var date=new Date( curDate.getTime() + 24*60*60*1000);
	var date = new Date(curDate.getTime());
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1
			+ strDate;
	// + " " + date.getHours() + seperator2 + date.getMinutes()
	// + seperator2 + date.getSeconds();
	return currentdate;
}

var preurl = siteurl;// "https://www.256kb.cn";

function getImUrl() {
	var http = basePath + "/"; // "${pageContext.request.contextPath}";

	/*
	 * if (window.location.href.indexOf('58.67.201.8') > 0) http =
	 * "http://58.67.201.8:8083/gserver/"; else
	 * if(window.location.href.indexOf('127.0.0.1') > 0)
	 * http='http://127.0.0.1:8080/kb/';
	 */
	return http;
}

/**
 * http 非加密测试 同步
 * 
 * @param url
 * @param sendData
 * @param onsuccess
 * @param onfail
 */
function SZUMWS(url, sendData, onsuccess, onfail, showProgress, datatype) {

	// console.log("sendData: " + sendData);
	var ecStr = sendData; // window.HMClient.AESEncrypt(sendData);
	// console.log("ecCrypt result: " + ecStr);
	// console.log("url: " + url);

	var sdata = {
		data : sendData
	};

	if (datatype && datatype == "json")
		sdata = JSON.parse(sendData);

	var http_url = url; // url.substring(0, url.lastIndexOf('.')) +
	// '_test.action';
	if (showProgress)
		ajaxLoading();
	$.ajax({
		type : "POST",
		url : http_url,
		// 数据需要Aes加密
		data : sdata,
		dataType : "json",
		success : function(data) {

			ajaxLoadEnd();

			console.log("ajax return: " + data);
			// 数据需要Aes解密
			// var decryData =data.ResponseMsg;//
			// window.HMClient.AESDeCrypt(data.ResponseMsg);
			// var json = JSON.parse(decryData);
			onsuccess(data);

		},
		error : function(a, b, c) {

			ajaxLoadEnd();
			console.log("ajax error: " + a + b);
			onfail(a);
			// popupAlert("网络异常!");

		}
	});

}

function ajaxLoading() {
	var berthDocument = window.parent.document;
	// 创建背景层
	var bgDiv = $("<div id='bgDiv' class='ie'></div>");
	// 获取当前文档宽度作为背景层宽度
	var bdWidth = $(berthDocument).width();
	// 获取当前文档高度作为背景层高度
	var bdHeight = $(berthDocument).height();
	// 设置背景层样式
	bgDiv.css({
		'width' : bdWidth,
		'height' : bdHeight,
		'position' : "fixed",
		'top' : 0,
		'left' : 0,
		"z-index" : 100000,
		"background-color" : "#fff",
		"opacity" : "0.85",
		"-moz-opacity" : "0.85",

		'position' : "absolute"

	});

	var maskWidth = 200;
	var maskHeight = 90;

	// var loadingDiv = $('<div id="loadingDiv" style="border:1px;"><img
	// src="'+basePath+'/images/loading.gif" /><br/><a
	// style="font-size:14px;">正在加载数据，请稍候...</a></div>');

	// var url=getImUrl()+"szhmpt/android/images/loading.gif";

	var loadingDiv = $("<div class=\"datagrid-mask-msg ie \"></div>")
			.html(
					"<div class='loadimgdiv'><div src='1'  border='0' alt='' style='margin:10px 80px;' class='loadimg'></div></div><div style='color:#ddd;text-align:center;'>正在处理，请稍候...</div>")
			.css({
				display : "block",

				background : '#333',
				width : maskWidth,
				height : maskHeight,
				left : ($(document.body).outerWidth(true) - maskWidth) / 2,
				top : ($(window).height() - maskHeight) / 2
			});

	loadingDiv.css({

		'position' : "absolute",

		"z-index" : 999,
		"border-radius" : "5px",
		"-moz-border-radius" : "5px",
		"-webkit-border-radius" : "5px",
		"-moz-box-shadow" : "0 1px 2px rgba(0,0,0,0.5)",
		"-webkit-box-shadow" : "0 1px 2px rgba(0,0,0,0.5)",
		"text-shadow" : "0 -1px 1px rgba(0,0,0,0.25)",
		"border-bottom" : "1px solid rgba(0,0,0,0.25)"

	});
	// 将确认框添加到背景层中
	bgDiv.append(loadingDiv);
	// 将背景层添加 到页面中
	$(berthDocument).find("body").eq(0).append(bgDiv);
}

function ajaxLoadEnd() {
	$(window.parent.document).find("#bgDiv").remove();
}