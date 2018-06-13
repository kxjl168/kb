

var app = angular.module('myApp', [ ]);
/*app.run([ 'editableOptions', function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
} ]);*/
app.controller('eduCtrl', function($scope) {
	
});
		

/*
 * document.addEventListener("deviceready", function() {
 * console.log("deviceready2=======: "); init(); });
 */

$(function() {
	
	

	

	init();
	/*
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
		
	}, $scope.doupdate, "");*/

});


function init() {

	initmenu_p($("#menuul"), "page/set/");


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


