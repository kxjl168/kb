

$(function() {
	
	
	
	

var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
	
		var http = getImUrl();// "";
		
		//for spider
		$scope.preurl="https://www.256kb.cn";

		
		$scope.showtp=function(x){
			
			if(window.location.href.indexOf('index')>0)
			{
				//msg(1);
				History.replaceState(null,null,basePath+'/public/index/'); // logs {}, '', "?state=4"
				//msg(2);
			//	History.replaceState(null, null, '?q='+1);
			//history.pushState({},0,basePath+'/public/index/');
			$scope.clean();
			$scope.blog_type=x.blog_type;
			
			$scope.getList();
		}
		else{
			window.location.href=basePath+"/public/index?bt="+x.blog_type;
		}
		};
		
	  $scope.showh=function(x){
		if(window.location.href.indexOf('index')>0)
		{
		//window.history.pushState({},0,basePath+'/public/index/');
			History.pushState(null, null,basePath+'/public/index/'); // logs {}, '', "?state=4"
		  $scope.clean();
			$scope.month=x.month;
			
			$scope.getList();
		}
		else{
			window.location.href=basePath+"/public/index?h="+encodeURI( x.month);
		}	
		};
	  $scope.showtg=function(x){
		  $scope.showtgs(x.tags);
		};
	  
	  $scope.showtgs=function(x){
			if(window.location.href.indexOf('index')>0)
			{
			//window.history.pushState({},0,basePath+'/public/index/');
				History.pushState({state:1}, "",basePath+'/'); // logs {}, '', "?state=4"
			  $scope.clean();
				$scope.blog_tag=x;
				
				$scope.getList();
			}
			else{
				window.location.href=basePath+"/public/index?tg="+encodeURI( x);
			}
			};
		
		$scope.getTpList=function(){
		
		

			var obj = new Object();
			SZUMWS(
					http + "blog/getTpList.action",
					JSON.stringify(obj),
					function succsess(json) {
						// var json = JSON.parse(decryData);
						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code
								+ ';message= ' + message);
						if (code == 200) {

							$scope.tplist = eval(json.datalist);

						

							$scope.$apply();

						

						} else {
							msg(message);
						}

					
					

					}, function error(data) {
						msg("网络异常!");

					

					}, false, false

			);
		};
		$scope.getTpList();
		
		$scope.getHList=function(){
			var obj = new Object();
			SZUMWS(
					http + "blog/getHList.action",
					JSON.stringify(obj),
					function succsess(json) {
						// var json = JSON.parse(decryData);
						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code
								+ ';message= ' + message);
						if (code == 200) {

							$scope.hlist = eval(json.datalist);

						

							$scope.$apply();

						

						} else {
							msg(message);
						}

					
					

					}, function error(data) {
						msg("网络异常!");

					

					}, false, false

			);
		};
		$scope.getHList();
		
		$scope.getTgList=function(){
			var obj = new Object();
			SZUMWS(
					http + "blog/getTgList.action",
					JSON.stringify(obj),
					function succsess(json) {
						// var json = JSON.parse(decryData);
						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code
								+ ';message= ' + message);
						if (code == 200) {

							$scope.tglist = eval(json.datalist);

						

							$scope.$apply();

						

						} else {
							msg(message);
						}

					
					

					}, function error(data) {
						msg("网络异常!");

					

					}, false, false

			);
		};
		$scope.getTgList();
		
		
	});
	
});