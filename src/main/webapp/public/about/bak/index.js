

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
	 $scope.x={};
	 $scope.x.imei="about";
		$scope.title = sitetitle;
		  document.title = "关于-"+ $scope.title;
		setTimeout(function() {
			 $scope.getReplayList();
		}, 300);
	

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

	initmenu_p($("#menuul"), "public/about/");


	$('.modal').on('show.bs.modal', function() {
		$(this).draggable();
		$(this).css("overflow-y", "scroll"); 
	});
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.blog_type = 'about';
	
	});

	initQuery();


SZUMWS(http + "blog/sabout.action","",
			function succsess(json) {
			}, function error(data) {
			}, false, false

	);
};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);


