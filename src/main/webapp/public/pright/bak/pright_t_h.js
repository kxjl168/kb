

	
	
$(function() {
	
	


var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
	
		var http = getImUrl();// "";
		
		//for spider
		$scope.preurl=siteurl;//"https://www.256kb.cn";

		
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
						//console.log('-----return -code= ' + code
						//		+ ';message= ' + message);
						if (code == 200) {

							$scope.tplist = eval(json.datalist);

							var html="";
							$.each($scope.tplist,function(index,item){
								
								html+='<div class="row"> ' 
									+' <a href="'+preurl+'/public/index/bt/'+item.blog_type+'.html"> ' 
								+' <img class="nopaddding img-responsive col-xs-2" style="width:20px; height:20px;" title="'+item.blog_type_name+'" src="'+item.blog_type_url+'"> '+item.blog_type_name+'&nbsp; '
								+' <span>('+item.view_nums+')</span> '
								 +'	 </a>'
								 +'</div>';
								
								
							});
							
							$("#tpdiv").html(html);
							
							

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
						//console.log('-----return -code= ' + code
						//		+ ';message= ' + message);
						if (code == 200) {

							$scope.hlist = eval(json.datalist);

							var html="";
							$.each($scope.hlist,function(index,item){
								
								html+='<div class="row"> '   
									+' <a href="'+preurl+'/public/index/h/'+item.month+'.html"> ' 
								 +'	'+item.month+'&nbsp;<span>('+item.view_nums+')</span> '
								 +'	 </a> '
								 +'</div>';
								
							});
							
							$("#mdiv").html(html);
							
							

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
						//console.log('-----return -code= ' + code
						//		+ ';message= ' + message);
						if (code == 200) {

							$scope.tglist = eval(json.datalist);
						

							$scope.$apply();
							
							var html="";
							$.each($scope.tglist,function(index,item){
								
								html+='  <a rel="'+item.page+'" href="'+preurl+'/public/index/tg/'+item.tags+'.html"> ' 
								 +'	'+item.tags+'&nbsp;, '
								 +'	 </a> ';
							});
							
							$("#tgdiv").html(html);
							$("#tgdiv a").tagcloud();
							 

						

						} else {
							msg(message);
						}

					
					

					}, function error(data) {
						msg("网络异常!");

					

					}, false, false

			);
		};
		$scope.getTgList();
		
		
		
		$scope.getYqList=function(){
			
			

			var obj = new Object();
			SZUMWS(
					http + "kurl/getYQList.action",
					JSON.stringify(obj),
					function succsess(json) {
						// var json = JSON.parse(decryData);
						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						//console.log('-----return -code= ' + code
						//		+ ';message= ' + message);
						if (code == 200) {

							$scope.yqlist = eval(json.datalist);

							
							var html="";
							$.each($scope.yqlist,function(index,item){
								html+=' <a   href="'+item.url_val+'"   onclick="return gourl(this)" >  '
								 +'    <div class="row col-xs-6"> '
								 +' <span class="glyphicon glyphicon-hand-right"></span>'+item.url_name+' &nbsp; '
								 +' </div> '
								 +' </a> ';
							});
							
							$("#yqdiv").html(html);
							
							 
							
							setTimeout(function() {
								var fixmeTop = $('#googlead2').offset().top;
								 $(window).scroll(function () {
								        var currentScroll = $(window).scrollTop();
								        if (currentScroll >= fixmeTop) {
								            $('#googlead2').css({
								                top: '100',
								                position: 'fixed',
								                width: 'inherit'
								            });
								          
								        } else {
								            $('#googlead2').css({
								                position: 'inherit',
								                width: 'inherit'
								            });
								           
								        }
								    });
							}, 3000);
							 
							
						

							$scope.$apply();

						

						} else {
							msg(message);
						}

					
					

					}, function error(data) {
						msg("网络异常!");

					

					}, false, false

			);
		};
		$scope.getYqList();
		
		
		
		
		
		
		
		
	});
	
});