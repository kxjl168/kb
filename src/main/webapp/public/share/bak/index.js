

var app = angular.module('myApp', [  "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});
		


$(function() {
	
	

	

	init();
	 $('pre code').each(function(i, block) {
         hljs.highlightBlock(block);
     });
	 
	

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

	initmenu_p($("#menuul"), "public/share/");


	$('.modal').on('show.bs.modal', function() {
		$(this).draggable();
		$(this).css("overflow-y", "scroll"); 
	});
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.blog_tag = 'share';
		
	});

	initQuery();



};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);


