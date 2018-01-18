
function initQuery() {

	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {

				$scope.detail = function(x, id) {
					
					window.location.href = basePath + "/public/detail/?i="
							+ x.imei + "#" + id;
				};
				
				//for spider
				$scope.preurl="http://www.256kb.cn";

				var http = getImUrl();

				$scope.title = "KxのBOOK";
			
				$scope.page = 1;
				$scope.rows = 10;

				$scope.rows_select = [ 5, 10, 20 ];
				setTimeout(function() {
					$("div.tablefoot select").val($scope.rows);
				}, 50);
				$scope.pageData = [];

				$scope.clean = function() {
					$scope.blog_type = "";
					$scope.month = "";
					$scope.blog_tag = "";
				};

				$scope.getList = function(id, fucOnFinished, clear) {

					$scope.page = (id != null) ? id : 1;

					if ($scope.page > $scope.pageNum)
						$scope.page = $scope.page - 1;

					if ($scope.page <= 0)
						$scope.page = 1;

					

					var http = getImUrl();

					var obj = new Object();

					var index = GetQueryString("i");
					if (index != null) {
						$scope.clean();
						
						$scope.page = index;
						if ($scope.page > $scope.pageNum)
							$scope.page = $scope.page - 1;

						if ($scope.page <= 0)
							$scope.page = 1;
						
					
					}
					
					
					var blog_type = GetQueryString("bt");
					if (blog_type != null) {
						$scope.clean();
						$scope.blog_type = blog_type;
					}

					var month = GetQueryString("h");
					if (month != null) {
						$scope.clean();
						$scope.month = month;
					}

					var blog_tag = GetQueryString("tg");
					if (blog_tag != null) {
						$scope.clean();
						$scope.blog_tag = blog_tag;
					}

					obj.blog_type = $scope.blog_type;
					obj.month = $scope.month;
					obj.blog_tag = $scope.blog_tag;

					obj.page = $scope.page;
					obj.rows = $scope.rows;

					SZUMWS(
							http + "blog/getInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
							
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

									if (typeof (window.pageYOffset) != 'undefined')
										window.pageYOffset = 0;
									if (typeof (document.documentElement.scrollTop) != 'undefined')
										document.documentElement.scrollTop = 0;
									if (typeof (document.body.scrollTop) != 'undefined')
										document.body.scrollTop = 0;

									$scope.total = json.total;
									$scope.pageDataPre = [];
									$scope.pageDataAft = [];
									$scope.pageNum = Math.ceil($scope.total
											/ $scope.rows);
									
									if($scope.pageNum<$scope.page)
										{
										$scope.page=$scope.pageNum;
										History.replaceState(null,null,basePath+'/public/index/'); 
										$scope.getList($scope.page);
										return;
										}
									
									
									$scope.datalist = eval(json.datalist);

									$
											.each(
													$scope.datalist,
													function(index, item) {
														$scope.datalist[index].context = unescape(item.content);

													});
									

								
									
									for ( var i = $scope.page - 3; i < $scope.page; i++) {
										if (i > 0)
											$scope.pageDataPre.push(i);
									}
									for ( var i = $scope.page + 1; i < $scope.page + 3; i++) {
										if (i <= $scope.pageNum)
											$scope.pageDataAft.push(i);
									}
								

									$scope.$apply();
									
									setTimeout(function(){
										//hljs.initHighlightingOnLoad();
										 $('pre code').each(function(i, block) {
									         hljs.highlightBlock(block);
									     });
										 
									},100);
									  
										

									console.log('-----guideList -OK= ');

								} else {
									msg(message);
								}

								

							}, function error(data) {
								msg("网络异常!");

							
							}, false, false

					);

				};
				
				$scope.getList();

				return;

			});

};