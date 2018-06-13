
var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});



$(function() {
	
	init();
	
	 hljs.initHighlightingOnLoad();
	
	

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_uid : {
			required : true,
			maxlength:40,

		},
		s_ublog : {
			required : false,
			maxlength:100,
	
		},
		s_text : {required : true,
			minlength:10,
			maxlength:500,
		}
		
	}, {
		s_uid : {
			required : "大侠请留名！",
			maxlength : "字数有点太多了-_-！",
		},
		s_ublog : {
		
			maxlength : "字数有点太多了-_-！",
		},
		s_text :  {required :  "写几个字吧 ",
			minlength : "再多写两个字呗 ",
			maxlength : "字数有点太多了-_-！",
			
		}
	}, $scope.doupdate, "","");

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

	initmenu_p($("#menuul"), "pown/url/");

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
			

				
				
				$scope.title = "KxのBOOK";
				
				$scope.page = 1;
				$scope.rows = 10;

				
				var title="常用链接"+"-"+$scope.title;
				$("#title").html(title);

				document.title=title;
				

				$scope.getList = function(id, fucOnFinished, clear) {

					$scope.page = (id != null) ? id : 1;

					if ($scope.page > $scope.pageNum)
						$scope.page = $scope.page - 1;

					if ($scope.page <= 0)
						$scope.page = 1;

					

					var http = getImUrl();

					var obj = new Object();


					obj.blog_type = $scope.blog_type;
					obj.month = $scope.month;
					obj.blog_tag = $scope.blog_tag;
					
					obj.show ="-1"; //自己查看全部
					obj.page = $scope.page;
					obj.rows = $scope.rows;

					SZUMWS(
							http + "kurl/getShowInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
							
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

								
									
									
									$scope.datalist = eval(json.datalist);
									
									$.each( $scope.datalist ,function(index,item){
										item.val=eval(item.val);
										$scope.datalist[index]=item;
									});
									
									
									$scope.$apply();
								

								} else {
									msg(message);
								}

								

							}, function error(data) {
								msg("网络异常!");

							
							}, false, false

					);

				};
				
				$scope.getList();
				

			});
};



app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
