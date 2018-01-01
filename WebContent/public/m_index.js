
function initQuery() {

	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {

				$scope.detail = function(x, id) {
					// alert(x.context);
					window.location.href = basePath + "/public/detail/?i="
							+ x.imei + "#" + id;
				}

				var http = getImUrl();// "";

				$scope.title = "KxのBOOK";
				// $scope.curpage=1;
				$scope.page = 1;
				$scope.rows = 10;

				$scope.rows_select = [ 5, 10, 20 ];
				setTimeout(function() {
					$("div.tablefoot select").val($scope.rows);
				}, 50);
				$scope.pageData = [];

				$scope.clean = function() {
					$scope.blog_type = "";
					$scope.month = "";// "12345678";
					$scope.blog_tag = "";// "12345678";
				}

				$scope.getList = function(id, fucOnFinished, clear) {

					$scope.page = (id != null) ? id : 1;

					if ($scope.page > $scope.pageNum)
						$scope.page = $scope.page - 1;

					if ($scope.page <= 0)
						$scope.page = 1;

					// alert($scope.etId);

					// if ($scope.lastSecondID != $scope.etId) {

					// $scope.page = 1;// // 1;// "12345678";
					// 10;// "12345678";
					// }

					// $scope.lastSecondID = $scope.etId;

					var http = getImUrl();// "";

					var obj = new Object();

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

					obj.blog_type = $scope.blog_type;// "12345678";
					obj.month = $scope.month;// "12345678";
					obj.blog_tag = $scope.blog_tag;// "12345678";

					obj.page = $scope.page;// 1;// "12345678";
					obj.rows = $scope.rows;// 10;// "12345678";

					SZUMWS(
							http + "blog/getInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
								// var json = JSON.parse(decryData);
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

									$scope.datalist = eval(json.datalist);

									$
											.each(
													$scope.datalist,
													function(index, item) {
														$scope.datalist[index].context = unescape(item.content);

													});

									$scope.total = json.total;
									$scope.pageDataPre = [];
									$scope.pageDataAft = [];
									$scope.pageNum = Math.ceil($scope.total
											/ $scope.rows);// +
									// ($scope.total%$scope.rows)>0?1:0;

									for ( var i = $scope.page - 3; i < $scope.page; i++) {
										if (i > 0)
											$scope.pageDataPre.push(i);
									}
									for ( var i = $scope.page + 1; i < $scope.page + 3; i++) {
										if (i <= $scope.pageNum)
											$scope.pageDataAft.push(i);
									}
									/*
									 * for(var i =1;i <= $scope.pageNum;i++){
									 * if(i<$scope.page)
									 * $scope.pageDataPre.push(i); else
									 * if(i>$scope.page)
									 * $scope.pageDataAft.push(i); };
									 */

									$scope.$apply();

									console.log('-----guideList -OK= ');

								} else {
									msg(message);
								}

								// $('#refresh').removeClass('visible');
								// $('#refresh2').removeClass('visible');

							}, function error(data) {
								msg("网络异常!");

								// $("#refresh").removeClass('visible');
								// $('#refresh2').removeClass('visible');

							}, false, false

					);

				}
				// $scope.apply();

				$scope.getList();

				return;

			});

}