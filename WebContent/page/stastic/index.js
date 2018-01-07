
var app = angular.module('myApp', [  ]);
app.controller('eduCtrl', function($scope) {

});

/*document.addEventListener("deviceready", function() {
	console.log("deviceready2=======: ");
	init();
});*/

$(function() {
	/*if (clinetType == "Android" || clinetType == "http") {
		console.log("deviceready1=======: ");
		

	}*/
	initDateSelect();
	
	init();
	
	

});

function getDetailList(page,date){
	
	 var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.rows2=10;
			$scope.page2=page;
			$scope.getDetailList(page,date);
		});
}

function changerows2(option)
{
	var num= $(option).val();
	//msg(num);
	
		 var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.rows2=num;
			$scope.getDetailList();
		});
}


function changerows(option)
{
	var num= $(option).val();
	//msg(num);
	
		 var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.rows=num;
			$scope.getList();
		});
}



function init() {
	
	initmenu($("#menuul"),"page/stastic/");
	
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {

		$scope.effectDate = getNowFormatDate();
		$scope.effectDate2 = getNowFormatDate();
		
		
		$('#collapseOne').on('shown.bs.collapse', function () {
		    // 执行一些动作...
			$("#titlepic").attr("class", "glyphicon glyphicon-chevron-up pull-right"); 
		});
		
		$('#collapseOne').on('hidden.bs.collapse', function () {
			$("#titlepic").attr("class", "glyphicon glyphicon-chevron-down pull-right"); 
	});
		
		
		$scope.date_type2 = [ {
			"value" : "HOUR",
			"name" : "小时"
		}, {
			"value" : "DAY",
			"name" : "天"
		}, {
			"value" : "MONTH",
			"name" : "月"
		}];
		  setTimeout(function(){
			   $("#dateType").get(0).selectedIndex=1;
			   $("#dateType").trigger('change');
		   }, 50);
	
		
		

		$scope.title="访问统计";
		//$scope.curpage=1;
		$scope.page=1;
		$scope.rows=10;
		
		$scope.rows_select=[5,10,20];
		  setTimeout(function(){
			   $("div.tablefoot select").val($scope.rows);
		   }, 50);
		$scope.pageData=[];
		
	
		$scope.getTypeList = function (id, fucOnFinished, clear) {

			var http = getImUrl();// "";

			var obj = new Object();

			//obj.deviceid = $scope.id;// "12345678";
			//obj.ip = $scope.ip;
			//obj.compy_name = $scope.compy_name;
			obj.page = 1;// 1;// "12345678";
			obj.rows = 40;// 10;// "12345678";

			SZUMWS(http + "statistics/getTypeInfoList.action", JSON
				.stringify(obj), function succsess(json) {

					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
						+ message);
					if (code == 200) {


						$scope.types_select = eval(json.datalist);

						 setTimeout(function(){
								
							  // $('#p_proxyserver_id').find("option:selected").attr("selected", false);
							   $('#s_type').get(0).selectedIndex=1;
							   // alert($("#p_proxyserver_id").val());
							    $scope.s_type=$("#s_type").val();
							  
							    
							    $scope.getList();
							    
						   }, 30);
						

						$scope.$apply();

						console.log('-----guideList -OK= ');

					} else {
						msg(message);
					}

					


				}, function error(data) {
					msg("网络异常!");

				

				}, false, false

			);

		}
		$scope.getTypeList();
		//$scope.getcompayList();
		
		
		
		$scope.getDetailList=function(id,date){
			
			$("#myModal1").modal('show');
			
	    $scope.page2 = (id != null) ? id :1;
			
			
			if($scope.page2>$scope.pageNum2)
				$scope.page2=$scope.page2-1;
			
			if($scope.page2<=0)
				$scope.page2=1;
			
			

			var http = getImUrl();// "";

			if(typeof(date)!="undefined")
				{
				$scope.detaildate=date;
				}
			
			
			
			var obj = new Object();
			obj.date =$scope.detaildate;// "12345678";
			//obj.date2 = $("#effectDate2").val();// "12345678";
			obj.date_type = $("#dateType").val();
			obj.qName = $("#s_type ").find("option:selected").text();
			
			obj.qType =$("#s_type").val();
			//obj.showall = $scope.showall?"false":"true";
			
			//msg(obj.showall);
			
			
			obj.page = $scope.page2;// 1;// "12345678";
			obj.rows = $scope.rows2;// 10;// "12345678";
			SZUMWS(http + "statistics/getDetailList.action", JSON
					.stringify(obj), function succsess(json) {
				// var json = JSON.parse(decryData);
				var code = json.ResponseCode;
				var message = json.ResponseMsg;
				console.log('-----return -code= ' + code + ';message= '
						+ message);
				if (code == 200) {

			
					$scope.detaillist =eval(json.datalist);
					
					//setchartdata(json.datalist);

					$scope.total2 = json.total;
					$scope.pageDataPre2=[];
					$scope.pageDataAft2=[];
					$scope.pageNum2=Math.ceil( $scope.total2/$scope.rows2);// + ($scope.total%$scope.rows)>0?1:0;
					
					for(var i =$scope.page2-3;i<$scope.page2;i++){
						if(i>0)
						   $scope.pageDataPre2.push(i);
					}
					for(var i =$scope.page2+1;i<$scope.page2+3;i++){
						if(i<=$scope.pageNum2)
						   $scope.pageDataAft2.push(i);
					}
				
						
						$scope.$apply();
						
					console.log('-----guideList -OK= ');

				} else {
					msg(message);
				}

			

				//$('#refresh').removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, function error(data) {
				msg("网络异常!");

			
			//	$("#refresh").removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, false, "json"

			);
		}
		
		$scope.getList = function(id, fucOnFinished, clear) {

			
			
			
			$scope.page = (id != null) ? id :1;
			
			
			if($scope.page>$scope.pageNum)
				$scope.page=$scope.page-1;
			
			if($scope.page<=0)
				$scope.page=1;
			
			

			var http = getImUrl();// "";

			var obj = new Object();
			obj.date1 = $("#effectDate").val();// "12345678";
			obj.date2 = $("#effectDate2").val();// "12345678";
			obj.date_type = $("#dateType").val();
			obj.qName = $("#s_type ").find("option:selected").text();
			
			obj.qType =$("#s_type").val();
			//obj.showall = $scope.showall?"false":"true";
			
			//msg(obj.showall);
			
			
			obj.page = $scope.page;// 1;// "12345678";
			obj.rows = $scope.rows;// 10;// "12345678";
			SZUMWS(http + "statistics/getAppStaticData.action", JSON
					.stringify(obj), function succsess(json) {
				// var json = JSON.parse(decryData);
				var code = json.ResponseCode;
				var message = json.ResponseMsg;
				console.log('-----return -code= ' + code + ';message= '
						+ message);
				if (code == 200) {

			
					$scope.datalist =eval(json.rows);
					
					setchartdata(json.rows);

					$scope.total = json.total;
					$scope.pageDataPre=[];
					$scope.pageDataAft=[];
					$scope.pageNum=Math.ceil( $scope.total/$scope.rows);// + ($scope.total%$scope.rows)>0?1:0;
					
					for(var i =$scope.page-3;i<$scope.page;i++){
						if(i>0)
						   $scope.pageDataPre.push(i);
					}
					for(var i =$scope.page+1;i<$scope.page+3;i++){
						if(i<=$scope.pageNum)
						   $scope.pageDataAft.push(i);
					}
				
						
						$scope.$apply();
						
					console.log('-----guideList -OK= ');

				} else {
					msg(message);
				}

			

				//$('#refresh').removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, function error(data) {
				msg("网络异常!");

			
			//	$("#refresh").removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, false, "json"

			);

		}
		//$scope.apply();
		
		//$scope.getList();
		
		
		return;
		

	});

}

// filter
app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
