(function(window){
	function checkBrower() {
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    if (window.ActiveXObject) {
        Sys.ie = ua.match(/msie ([\d.]+)/)[1];
        //获取版本
        var ie_version = 6;
        if (Sys.ie.indexOf("7") > -1) {
        	Sys.ie_version = 7;
        }
        if (Sys.ie.indexOf("8") > -1) {
        	Sys.ie_version = 8;
        }
        if (Sys.ie.indexOf("9") > -1) {
        	Sys. ie_version = 9;
        }
        if (Sys.ie.indexOf("10") > -1) {
        	Sys.ie_version = 10;
        }
        if (Sys.ie.indexOf("11") > -1) {
        	Sys.ie_version = 11;
        }
    }
    else if (ua.indexOf("firefox") > -1)
        Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];
    else if (ua.indexOf("chrome") > -1)
        Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1];
    else if (window.opera)
        Sys.opera = ua.match(/opera.([\d.]+)/)[1];
    else if (window.openDatabase)
        Sys.safari = ua.match(/version\/([\d.]+)/)[1];
    
    return Sys;
	}
	
	window.checkBrower=function(){
		return checkBrower();
	}
	
	
	var Sys= checkBrower();
	if(Sys.ie&&Sys.ie_version <9)
		{
		//alert("抱歉，本站暂不支持IE9以下浏览器，请升级您的浏览器获取最新体验");
		window.location.href="/kb/ie8.jsp";
		return;
		}

    
})(window);