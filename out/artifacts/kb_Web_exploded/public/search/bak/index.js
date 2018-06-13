
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

	initmenu_p($("#menuul"), "public/search/");

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
			

				$scope.init=function(){
					$("#srzt").on("click","a", function(e) {
						
						var url=e.currentTarget.href;
						if(url.indexOf("javascript")>-1)
							{
							
							}
						else
							{
					//	msg(url);
						$scope.stopDefault(e);
						$scope.search(url);
							}

				});
				};
				$scope.init();

				$scope.replay = function(x) {

					kvalidate.validate("#fm");

				};
				
				
				$scope.title = "KxのBOOK";
				
				$scope.page = 1;
				$scope.rows = 10;

				
				var title="GOOGLE SEARCH"+"-"+$scope.title;
				$("#title").html(title);

				document.title=title;
				

			/*	var meta = document.getElementsByTagName('meta');
				meta["keywords"].setAttribute('content',$scope.x.tags);
				*/
				$("#kwd").bind('keypress', function(event) {
					if (event.keyCode == "13") {
						$scope.search();
					}
				});
				
				
				
				$scope.init = function(url) {

					var obj = {};

				
					SZUMWS(http + "search/init.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {
							
				

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("net work error！");
						

					}, false, false

					);

				};
				$scope.init();
				
				
				$scope.lastData=[];
				$scope.back=function()
				{
					var last="";
					if($scope.lastData.length>0)
						{
						last=$scope.lastData.pop();
						
						$("#srzt").html(last);
						}
						
					else
						{
						msg("已经无法后退啦~");
						}
					
				};
				
				$scope.search = function(url) {

					var obj = {};

					
					if(typeof(url)=="undefined")
					obj.keyword = $scope.kwd;
					else
						obj.url = url;
				

					SZUMWS(http + "search/dosearch.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {
							
							

							var rst=$(json.html).find("#content_left").html();
							var rstpage=$(json.html).find("#page").html();
							
					
							var rcnt=$(json.html).find("#rcnt").html();
							
							if(typeof(rst)!='undefined')
							$("#srzt").html(rst+rstpage);
							else if(typeof(rcnt)!="undefined")
								{
								$("#srzt").html(json.html);
								//google
								$("#srzt").find("#searchform").addClass("hide");
								$("#srzt").find("#footcnt").addClass("hide");
								
								if($scope.lastData.length<10)
								$scope.lastData.push($("#srzt").html());
								
								}
								
							else
								$("#srzt").html(json.html);
								

							
								$scope.$apply();
						
						

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("net work error！");
						

					}, true, false

					);

				};
				
				$scope.stopDefault=function( e ) 
				 {       
				 if ( e && e.preventDefault )          
				     e.preventDefault();      
				 else          
				 
				 window.event.returnValue = false;              
				 return false;  }  ;
				
			

				
				
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
				
			});
				

};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
