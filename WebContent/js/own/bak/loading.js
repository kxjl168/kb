function msg(data) {
	// 匿名提示
	noty({
		text : data,
		layout : "center",
		timeout : 1000,
		type : "success"
	});
}

function error(data) {
	// 匿名提示
	noty({
		text : data,
		layout : "center",
		timeout : 1000,
		type : "error"
	});
}

function GetQueryString(name) {

	
	 var index=window.location.href.lastIndexOf("/");
	 var indexj=window.location.href.lastIndexOf("#");

	 //最后一个/开始 截取#前面的，兼容history.js html4 url
		var searchpath= window.location.href.substr(index+1);
		if(indexj>0)
			searchpath= window.location.href.substr(index+1,indexj-index-1);

		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$|#)", "i");



		var r = searchpath.substr(1).match(reg);



		if (r != null)

			return decodeURI(r[2]);

		return null;
}


function cancelBubble(e) { 
    var evt = e ? e : window.event; 
        if (evt.stopPropagation) {        //W3C 
            evt.stopPropagation(); 
        }else {       //IE      
       evt.cancelBubble = true; 
       }  
}

function setTimeFormat(id,min,max) {
	var datetype = $("#dateType").val();

	var startDate = '%yyyy-%MM-%dd %HH';
	var dateFmt = 'yyyy-MM-dd HH';
	
	// startDate = '%HH:%mm';
	// dateFmt = 'HH:mm';
	
	datetype="DAY";

	if (datetype == "HOUR") {
		startDate = '%yyyy-%MM-%dd %HH';
		dateFmt = 'yyyy-MM-dd HH';
	} else if (datetype == "DAY") {
		startDate = '%yyyy-%MM-%dd';
		dateFmt = 'yyyy-MM-dd';
	} else if (datetype == "MONTH") {
		startDate = '%yyyy-%MM';
		dateFmt = 'yyyy-MM';
	}else if(datetype == "TIME")
		{
		 startDate = '%HH:%mm:%ss';
		 dateFmt = 'HH:mm:ss';
		}
		
	var param={
		startDate : startDate,
		dateFmt : dateFmt,
		el:id
	};
	if(min!=null)
		param.minDate='#F{$dp.$D(\''+min+'\')}';
	if(max!=null)
		param.maxDate='#F{$dp.$D(\''+max+'\')}';
	

		WdatePicker(param);	

}


function getNowFormatDate() {
    var curDate = new Date(); 
    
     
   //var date=new Date( curDate.getTime() + 24*60*60*1000);
    var date=new Date( curDate.getTime() );
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
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
            //+ " " + date.getHours() + seperator2 + date.getMinutes()
            //+ seperator2 + date.getSeconds();
    return currentdate;
} 


function getImUrl() {
	var http =basePath+"/";// "${pageContext.request.contextPath}";
	
	if (window.location.href.indexOf('58.67.201.8') > 0)
		http = "http://58.67.201.8:8083/gserver/";
	else if(window.location.href.indexOf('127.0.0.1') > 0)
		http='http://127.0.0.1:8080/kb/';
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
function SZUMWS(url, sendData, onsuccess, onfail, showProgress ,datatype         ) {

	console.log("sendData: " + sendData);
	var ecStr = sendData;// window.HMClient.AESEncrypt(sendData);
	// console.log("ecCrypt result: " + ecStr);
	console.log("url: " + url);

	var sdata={data:sendData};
	
	if(datatype&&datatype=="json")
		sdata=JSON.parse(sendData);
	
	var http_url = url;// url.substring(0, url.lastIndexOf('.')) +
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
