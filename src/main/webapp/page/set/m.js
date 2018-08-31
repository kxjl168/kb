
function changetype()
{
  var num=$("#q_type").find("option:selected").attr("num");
  $("#num").html(num+'条');
}

function initQuery() {
	
	

	
	

	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {

				

				var http = getImUrl();// "";

				$scope.title = "后台设置";
				// $scope.curpage=1;
				$scope.page = 1;
				$scope.rows = 10;

				$scope.rows_select = [ 5, 10, 20 ];
				setTimeout(function() {
					$("div.tablefoot select").val($scope.rows);
				}, 50);
				$scope.pageData = [];

				
				$scope.cleanHtml= function() {
					var http = getImUrl();// "";

					var obj = new Object();

					obj.type=$("#q_type").val();
					// obj.deviceid = $scope.id;// "12345678";
					// obj.ip = $scope.ip;
					// obj.compy_name = $scope.compy_name;
					obj.page = 1;// 1;// "12345678";
					obj.rows = 40;// 10;// "12345678";

					SZUMWS(http + "sysBaseInfo/removeStaticHtml.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							msg("操作完成");

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, false

					);
				};
				
								
				$scope.cleanData= function() {
					var http = getImUrl();// "";

					var obj = new Object();

					obj.type=$("#q_type").val();
					// obj.deviceid = $scope.id;// "12345678";
					// obj.ip = $scope.ip;
					// obj.compy_name = $scope.compy_name;
					obj.page = 1;// 1;// "12345678";
					obj.rows = 40;// 10;// "12345678";

					SZUMWS(http + "sysBaseInfo/cleanKdata.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							msg("操作完成");

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, false

					);
				}
				
				$scope.setpswd=function(){
					var http = getImUrl();// "";

					var obj = new Object();
				
						obj.opwd=$scope.opwd;
					 obj.npwd=$scope.npwd;
					
					obj.page = 1;// 1;// "12345678";
					obj.rows = 40;// 10;// "12345678";

					SZUMWS(http + "adminPwdSet.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (json == 200) {


							$scope.opwd="";
						 $scope.npwd="";
						 msg("修改完成！");
						  window.location.href=basePath+"/login.jsp";
							

							$scope.$apply();
							
							

							console.log('-----guideList -OK= ');

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, "json"

					);
				}
				
				
				$scope.getsysinfo=function(){
					var http = getImUrl();// "";

					var obj = new Object();
				
						obj.opwd=$scope.opwd;
					 obj.npwd=$scope.npwd;
					
					obj.page = 1;// 1;// "12345678";
					obj.rows = 40;// 10;// "12345678";

					SZUMWS(http + "sysBaseInfo/getSysInfo.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {


							
							
							$kfile.init({
								fileUploadname:"fileUploadURL", //上传文件的name
								httppath:json.httppath,  //img -static目录前缀
								isimg:true,
								filesufix:'png,jpg,gif,jpeg,',
								maxFileSize:1*1024*1024,//2M
								maximgupload : 1,//最多可上传图片数量
								uploadurl:http + 'UploadFileXhr.action',//上传图片action url
								container:$("body").find('#headimg'), //图片容器
							});
							$kfile.get("headimg").initFile(function(){
								if(json.fileinfo)
									{
									var finfo =json.fileinfo;
								$kfile.get("headimg").addFile(finfo.id,json.httppath+finfo.http_relative_path);
									}
							});
							
							
							$scope.s_sign=json.sign;
							
						
						 
							$scope.$apply();
							
						

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, "json"

					);
				}
				$scope.getsysinfo();
				
				
				$scope.setsysinfo=function(){
					var http = getImUrl();// "";

					var obj = new Object();
				
						obj.sign=$scope.s_sign;
					 obj.imgs= $kfile.get("headimg").getAllFileIds();
					
					obj.page = 1;// 1;// "12345678";
					obj.rows = 40;// 10;// "12345678";

					SZUMWS(http + "sysBaseInfo/setSysInfo.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							   msg("修改完成！");
								 
					
							$scope.getsysinfo();
						 

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, "json"

					);
				}
				

				$scope.getdictList = function(id, fucOnFinished, clear) {

					var http = getImUrl();// "";

					var obj = new Object();

					// obj.deviceid = $scope.id;// "12345678";
					// obj.ip = $scope.ip;
					// obj.compy_name = $scope.compy_name;
					obj.page = 1;// 1;// "12345678";
					obj.rows = 40;// 10;// "12345678";

					SZUMWS(http + "sysBaseInfo/getKdataList.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							$scope.dicts = eval(json.datalist);

						
							$.each($scope.dicts,function(index,item){
								$scope.dicts[index]=JSON.parse(item);
							});
							
							

							$scope.$apply();
							
							setTimeout(function() {

								// $('#p_proxyserver_id').find("option:selected").attr("selected",
								// false);
								$('#q_type').get(0).selectedIndex = 1;
								// alert($("#p_proxyserver_id").val());
								$scope.q_type = $("#q_type").val();
								changetype();
								// $scope.apply();
							}, 30);

							console.log('-----guideList -OK= ');

						} else {
							msg(message);
						}


					}, function error(data) {
						msg("网络异常!");

				

					}, false, false

					);

				}
				$scope.getdictList();
				

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