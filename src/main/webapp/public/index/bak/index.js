

var app = angular.module('myApp', [  "ngSanitize"]);
//var app = angular.module('myApp', [ ]);	
app.controller('eduCtrl', function($window,$scope) {
	

});
	

function a(){
	var $scope = angular.element(ngSection).scope();
	// $scope.$digest();
	 
	// msg($w.scrollTop());
	//alert($(window).scrollTop()+'/'+$('html').scrollTop()+'/'+window.pageYOffset+'/'+ document.documentElement.scrollTop);
}


function initCal(){
	
	  $("#nDate").calendar({
	        /*
	         * 传入今天的时间
	         * 默认：客户端时间
	         * 可传入一个服务器的时间
	         */
	        date: new Date(),
	        width: 800,
	        height: 400,
	        /* 左右框显示的比例 */
	        rate: 0.69,
	        /*
	         * 休假和加班设置
	         * JSON格式：Y加年-M加月-D加日
	         * 0表示休假 1表示加吧
	         */
	        week: false, // 是否开启单双休
	        week_walue: "2016/9/17", // 双休对应的周六
	        isclick: false,
	        configDay: {}, // 系统配置
	        left:true,
	    });
}


$(function() {
	
	
	initCal();
	

	init();
	 $('pre code').each(function(i, block) {
         hljs.highlightBlock(block);
     });
	 
	
	// WdatePicker({eCont:'nDate'});
	 
	

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_title : {
			required : true,
		
		},
		s_context : "required",
		
	}, {
		s_title : {
			required : "标题必须填写",
	
		},

		s_context : "请输入内容",
		
	}, $scope.doupdate, "");

});

function changerows(option) {
	var num = $(option).val();
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
};

function init() {

	initmenu_p($("#menuul"), "public/index/");


	$('.modal').on('show.bs.modal', function() {
		$(this).draggable();
		$(this).css("overflow-y", "scroll"); 
	});
	
	initQuery();

	

};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		
		// return $sce.trustAsHtml(input.replace(urlRegex,'<a href="$1"' + targetHTML + '>$1</a>'));
		//不替换emoji表情
		return htmlCode ? $kchar.angularSce(htmlCode,$sce) : "";
	}
} ]);


