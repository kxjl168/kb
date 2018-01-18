
var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});



$(function() {
	
	init();
	
	 hljs.initHighlightingOnLoad();
	
	

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

	initmenu_p($("#menuul"), "pulbic/detail/");

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
			

				
				$scope.title = "KxのBOOK Kx的个人站点";
				
				$scope.page = 1;
				$scope.rows = 10;

				$scope.rows_select = [ 5, 10, 20 ];
				setTimeout(function() {
					$("div.tablefoot select").val($scope.rows);
				}, 50);
				$scope.pageData = [];

				$scope.getList = function(imei, fucOnFinished, clear) {

					
					
					$scope.page =1;

					$scope.pre=$scope.next=null;
					
					var obj = new Object();
					if(typeof(imei)=="undefined")
					obj.i =GetQueryString("i");
					else
						obj.i =imei;
					

					obj.page = $scope.page;
					obj.rows = $scope.rows;
					SZUMWS(
							http + "blog/getDetailList.action",
							JSON.stringify(obj),
							function succsess(json) {
								
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

									$scope.datalist = eval(json.datalist);

									if($scope.datalist.length>0)
										{
										$scope.x=$scope.datalist[0];
										
											$scope.x.context= unescape($scope.x.content);
											$("#title").html($scope.x.title);

											document.title=$scope.x.title+"-"+$scope.title;
											
											
											
											var meta = document.getElementsByTagName('meta');
											meta["keywords"].setAttribute('content',$scope.x.tags);
										}
									
									if($scope.datalist.length>1)
										{
										if($scope.datalist[1].recordid>$scope.x.recordid)
											$scope.next=$scope.datalist[1];
										else
											$scope.pre=$scope.datalist[1];
										}
									if($scope.datalist.length>2)
										{
										
											$scope.pre=$scope.datalist[2];
										
										}
										
									
								
									
									
									$scope.total = json.total;
									$scope.pageDataPre = [];
									$scope.pageDataAft = [];
									$scope.pageNum = Math.ceil($scope.total
											/ $scope.rows);

									for ( var i = $scope.page - 3; i < $scope.page; i++) {
										if (i > 0)
											$scope.pageDataPre.push(i);
									}
									for ( var i = $scope.page + 1; i < $scope.page + 3; i++) {
										if (i <= $scope.pageNum)
											$scope.pageDataAft.push(i);
									}
								

									$scope.$apply();
									
									$scope.getReplayList();

									 $('pre code').each(function(i, block) {
							                hljs.highlightBlock(block);
							            });
									 
									
									console.log('-----guideList -OK= ');

								} else {
									msg(message);
								}



							}, function error(data) {
								msg("net work error！");

								if (fucOnFinished!= null)
									fucOnFinished();

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
