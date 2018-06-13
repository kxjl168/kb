$(function() {
	
	initReplayModel();
	
	
	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_uid : {
			required : true,
			maxlength : 40,

		},
		s_ublog : {
			required : false,
			maxlength : 100,

		},
		s_text : {
			required : true,
			minlength : 10,
			maxlength : 500,
		}

	}, {
		s_uid : {
			required : "大侠请留名！",
			maxlength : "字数有点太多了-_-！",
		},
		s_ublog : {

			maxlength : "字数有点太多了-_-！",
		},
		s_text : {
			required : "写几个字吧 ",
			minlength : "再多写两个字呗 ",
			maxlength : "字数有点太多了-_-！",

		}
	}, $scope.doupdate, "", "");

	
});

function initReplayModel() {
	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {
				$scope.replay = function(x) {

					kvalidate.validate("#fm");

				};

				var http = getImUrl();
				
				$scope.doupdate = function(fm) {

					var obj = {};

					if (typeof ($scope.preplay) == "undefined") {

					} else {
						obj.pid = $scope.preplay.recordid;
					}
					
					if (typeof ($scope.preplay_main) == "undefined") {

					} else {
						obj.ppid = $scope.preplay_main.recordid;
					}

					obj.imei = $scope.x.imei;
					obj.userid = escape($scope.s_uid);
					obj.context = escape($scope.s_text);
					obj.ublog = $scope.s_ublog ? escape($scope.s_ublog) : "";

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
								$scope.s_ublog = "";
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

				$scope.canc = function(event, x) {
					var row = $("#rpdiv");

					kvalidate.resetForm("#fm");
					$("#rdivc").after(row);
					$("#cbtn").addClass("hide");
				};

				$scope.beginReplay = function(event, x,t) {
					$scope.preplay = x;
					$scope.preplay_main = t;
					var row = $("#rpdiv");

					($(event.target).parent()).after(row);

					$("#cbtn").removeClass("hide");

				};

				$scope.getReplayList = function() {

					var http = getImUrl();

					var obj = new Object();

					obj.imei = $scope.x.imei;

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
									$scope.rnum = $scope.rdatalist.length;

									$
											.each(
													$scope.rdatalist,
													function(index, item) {
														$scope.rdatalist[index].content = unescape(item.content);
														$scope.rdatalist[index].userid = unescape(item.userid);
														$scope.rdatalist[index].user_blog = item.user_blog ? unescape(item.user_blog)
																: "";

														$
																.each(
																		$scope.rdatalist[index].reback,
																		function(
																				index2,
																				item2) {
																			$scope.rdatalist[index].reback[index2].content = unescape(item2.content);
																			$scope.rdatalist[index].reback[index2].userid = unescape(item2.userid);
																			$scope.rdatalist[index].reback[index2].user_blog = item2.user_blog ? unescape(item2.user_blog)
																					: "";
																			$scope.rdatalist[index].reback[index2].tuid = unescape(item2.tuid);
																			$scope.rdatalist[index].reback[index2].tuser_blog = item2.tuser_blog ? unescape(item2.tuser_blog)
																					: "";

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
	

}
