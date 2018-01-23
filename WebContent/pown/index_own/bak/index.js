

var app = angular.module('myApp', [  "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});
		


$(function() {
	
	

	

	init();
	 $('pre code').each(function(i, block) {
         hljs.highlightBlock(block);
     });
	 
	
	 WdatePicker({eCont:'nDate'});
	 
	

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

	initmenu_p($("#menuul"), "public/index_own/");


	$('.modal').on('show.bs.modal', function() {
		$(this).draggable();
		$(this).css("overflow-y", "scroll"); 
	});
	
	

	
	initQuery();

	

};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);


