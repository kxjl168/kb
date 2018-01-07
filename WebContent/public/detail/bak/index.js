
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
			

				

				

			

				$scope.replay = function(x) {

					
					
					kvalidate.validate("#fm");

				};

				$scope.doupdate = function(fm) {

					var obj = {};

					if (typeof ($scope.preplay) == "undefined") {

					} else {
						obj.pid = $scope.preplay.recordid;
					}
					
					obj.imei = $scope.x.imei;
					obj.userid =escape($scope.s_uid);
					obj.context= escape($scope.s_text);
					obj.ublog=$scope.s_ublog? escape($scope.s_ublog):"";
					


					SZUMWS(http + "replay/addOrUpdate.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							
							setTimeout(function() {
								$scope.s_uid = "";
								$scope.s_text = "";
								$scope.s_ublog="";
								$scope.canc();
							
								$scope.$apply();
							}, 10);

							$scope.getReplayList();
							
						

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("net work error！");
						

					}, false, false

					);

				};
				
				
				$scope.title = "KxのBOOK";
				
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
				
				$scope.canc=function(event,x){
					var row=	$("#rpdiv");

					kvalidate.resetForm("#fm");
						$("#rdivc").after(row);
						$("#cbtn").addClass("hide");	
				};
				

				$scope.beginReplay=function(event,x){
					$scope.preplay=x;
					var row=	$("#rpdiv");
				
					
					($(event.target).parent()).after(row);

					$("#cbtn").removeClass("hide");
	
				};
				
				
$scope.getReplayList = function() {

					
				


					var http = getImUrl();

				
					var obj = new Object();
				
						obj.imei =$scope.x.imei;
					

					obj.page = $scope.rpage;
					obj.rows = $scope.rrows;
					SZUMWS(
							http + "replay/getInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
								
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

									$scope.rdatalist = eval(json.datalist);
									$scope.rnum =$scope.rdatalist.length;

									$.each($scope.rdatalist,function(index,item){
										$scope.rdatalist[index].content= unescape( item.content);
										$scope.rdatalist[index].userid= unescape( item.userid);
										$scope.rdatalist[index].user_blog=  item.user_blog?unescape( item.user_blog):"";

										$.each($scope.rdatalist[index].reback,function(index2,item2){
											$scope.rdatalist[index].reback[index2].content= unescape( item2.content);
											$scope.rdatalist[index].reback[index2].userid= unescape( item2.userid);
											$scope.rdatalist[index].reback[index2].user_blog=  item2.user_blog?unescape( item2.user_blog):"";
											$scope.rdatalist[index].reback[index2].tuid= unescape( item2.tuid);
											$scope.rdatalist[index].reback[index2].tuser_blog=item2.tuser_blog? unescape( item2.tuser_blog):"";
											
										});

									});
									$scope.$apply();

									
									
								

								} else {
									msg(message);
								}

							

							}, function error(data) {
								msg("net work error！");


							}, false, false

					);

				};

				

				return;

				
			});

};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);