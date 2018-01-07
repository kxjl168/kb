var devMode = 1, dataUrl = "http://10.10.4.2:8280/huimin/";
var app = angular.module('myApp', [  "xeditable" ]);
app.run([ 'editableOptions', function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
} ]);
app.controller('eduCtrl', function($scope) {

});

/*
 * document.addEventListener("deviceready", function() {
 * console.log("deviceready2=======: "); init(); });
 */

$(function() {
	/*
	 * if (clinetType == "Android" || clinetType == "http") {
	 * console.log("deviceready1=======: ");
	 * 
	 *  }
	 */

	init();
	
/*	var config = {
			extraPlugins: 'codesnippet',
			codeSnippet_theme: 'monokai_sublime',
			height: 356,
			uploadUrl:'/kb/UploadCKFile.action';
		};*/

		//CKEDITOR.replace( 's_context' );
	//CKEDITOR.replace( 's_context' );
		$("#s_context").ckeditor();


	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_title : {
			required : true,
		// version4: true,
		// minlength:4,
		},
		s_context : "required",
		
	}, {
		s_title : {
			required : "标题必须填写",
		// version4: "版本号格式错误*.*.*.*",
		// minlength:"至少4个字符"
		},

		s_context : "请输入内容",
		
	}, $scope.doupdate, "");

});

function changerows(option) {
	var num = $(option).val();
	// msg(num);

	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
}

function init() {

	initmenu($("#menuul"), "page/blog/");

	$('#collapseOne').on(
			'shown.bs.collapse',
			function() {
				// 执行一些动作...
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
		$(this).css("overflow-y", "scroll"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	});

	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {

				$scope.checkcity = function(index, data) {
					// alert(index+"/"+data);
					// alert($scope.datalist[index].city);

					var obj = {};
					obj.recordid = $scope.datalist[index].recordid;

					obj.city = data;// $("#s_city").val(); //$scope.s_city;
					$scope.doupdate(this, obj);

				}

				$scope.cpTypes = [ {
					"id" : 0,
					"text" : "江苏"
				}, {
					"id" : 2,
					"text" : "浙江",
					"selected" : true
				}, {
					"id" : 3,
					"text" : "山东"

				}, {
					"id" : 4,
					"text" : "安徽"

				} ];

				$scope.selType = 1;

				// $("#myModal").draggable();
				// $("#myModal").resizable();
				$("#cpType").select2({

					"placeholder" : "请选择类型",
					"allowClear" : false,
					"minimumResultsForSearch" : Infinity,
					"data" : $scope.cpTypes
				});

				$("#cpType2").select2({

					"placeholder" : "请选择类型",
					"allowClear" : false,
					"minimumResultsForSearch" : Infinity,
					"data" : $scope.cpTypes
				});

				$scope.load = function(type) {

					window.location.href = "../../page/" + type;
				};

				$scope.del = function(item) {
					if (item == null)
						return;

					var id = item.recordid;

					$("#myModal3").modal("show");
					$("#btnconfirm").one(
							"click",
							function() {

								var obj = {};

								obj.recordid = id;

								SZUMWS(http + "blog/del.action", JSON
										.stringify(obj),
										function succsess(json) {

											var code = json.ResponseCode;
											var message = json.ResponseMsg;
											console.log('-----return -code= '
													+ code + ';message= '
													+ message);
											if (code == 200) {

												msg("删除成功！");

												$("#myModal3").modal("hide");

												$scope.getList();

											} else {
												msg(message);
											}

										}, function error(data) {
											msg("网络异常!");
											// $("#myModal2").modal('hide');

										}, false, false

								);

								// $("#btnconfirm").unbind("click",delfun(id));

							});

				}

				$scope.addOrModify = function(item) {
					kvalidate.resetForm("#fm");
					if (item != null) {
						
						var http = getImUrl();// "";

						var obj = new Object();
						obj.i = item.imei;// "12345678";

						obj.page = $scope.page;// 1;// "12345678";
						obj.rows = $scope.rows;// 10;// "12345678";
						SZUMWS(
								http + "blog/getDetailInfo.action",
								JSON.stringify(obj),
								function succsess(json) {
									// var json = JSON.parse(decryData);
									var code = json.ResponseCode;
									var message = json.ResponseMsg;
									/*console.log('-----return -code= ' + code
											+ ';message= ' + message);*/
									if (code == 200) {

										$scope.detailItem =JSON.parse(json.datalist);

										$scope.detailItem.context= unescape( $scope.detailItem.content);
										
										//后台数据
										item=$scope.detailItem;

										$scope.edit = "编辑";
									
										$scope.s_recordid =item.recordid;
										$scope.s_title=item.title;
										
										setTimeout(function() {
											 $("#s_type").val(item.blog_type);						
										}, 30);
										
										$scope.s_tags =item.tags;
										$scope.s_context =unescape(item.context);
										
										
										$scope.$apply();

										$("#myModal2").modal('show');

									} else {
										msg(message);
									}

								}, function error(data) {
									msg("网络异常!");

								

								}, true, false

						);
						
						
						
						
						//$("#s_account").attr('disabled', '');

					} else {
						$scope.edit = "新增";
						//$("#s_account").removeAttr('disabled');

						$scope.s_recordid ="";
						$scope.s_title="" ;
						
					
						setTimeout(function() {

							$("#s_type").get(0).selectedIndex = 1// =$("#s_type").val();
						
							$scope.s_type = $("#q_type").val();
						
						}, 30);
						
						
						
						$scope.s_tags ="";
						$scope.s_context ="";
						
						$("#myModal2").modal('show');
					}

				//	$scope.getcompayList();
				
				}
				var http = getImUrl();// "";

				$scope.doupdate = function(fm, value) {

					var obj = {};

					if (typeof (value) == "undefined") {
						obj.recordid = $scope.s_recordid;
						obj.title = $scope.s_title;
						obj.blog_type =$("#s_type").val();
						obj.tags =$scope.s_tags;
						obj.context= escape($("#s_context"  ).val());
						

					} else {
						obj = value;
					}

					SZUMWS(http + "blog/addOrUpdate.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							$("#myModal2").modal('hide');

							$scope.getList();
							setTimeout(function() {
								$scope.s_recordid = "";
								$scope.s_dict_key = "";
								$scope.s_dict_name = "";

								$scope.s_sort = "";

								$scope.$apply();
							}, 10);

						} else {
							msg(message);
						}
						/*
						 * if (fucOnFinished != null) fucOnFinished();
						 */

					}, function error(data) {
						msg("网络异常!");
						// $("#myModal2").modal('hide');

						/*
						 * if (fucOnFinished != null) fucOnFinished();
						 */

					}, false, false

					);

				}

				$scope.update = function() {

					/*
					 * if($scope.fm.s_account.$error.required) {
					 * error("用户ID必须要填写"); return; }
					 * if($scope.fm.s_pass.$error.required) { error("密码必须要填写");
					 * return; }
					 * 
					 * if($scope.fm.s_company.$error.required) {
					 * error("公司必须要填写"); return; }
					 * 
					 * if($scope.fm.s_ip_refresh.$invalid) { error("刷新时间为数字");
					 * return; }
					 */

					kvalidate.validate("#fm");

				}

				$scope.citys_select = [ '上海', '南京', 'nanjing', '扬州', '苏州' ];

				$scope.getdictList = function(id, fucOnFinished, clear) {

					var http = getImUrl();// "";

					var obj = new Object();

					// obj.deviceid = $scope.id;// "12345678";
					// obj.ip = $scope.ip;
					// obj.compy_name = $scope.compy_name;
					obj.page = 1;// 1;// "12345678";
					obj.rows = 40;// 10;// "12345678";

					SZUMWS(http + "blogtype/getInfoList.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {

							$scope.dicts = eval(json.datalist);

						
							
							setTimeout(function() {

								// $('#p_proxyserver_id').find("option:selected").attr("selected",
								// false);
								$('#q_type').get(0).selectedIndex = 1;
								// alert($("#p_proxyserver_id").val());
								$scope.q_type = $("#q_type").val();
								// $scope.apply();
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
				$scope.getdictList();

				$scope.title = "文章管理";
				// $scope.curpage=1;
				$scope.page = 1;
				$scope.rows = 10;

				$scope.rows_select = [ 5, 10, 20 ];
				setTimeout(function() {
					$("div.tablefoot select").val($scope.rows);
				}, 50);
				$scope.pageData = [];

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
					obj.name = $scope.q_name;// "12345678";

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

									$scope.datalist = eval(json.datalist);

									
									$.each($scope.datalist,function(index,item){
										$scope.datalist[index].context= unescape( item.content);
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

								if (fucOnFinished != null)
									fucOnFinished();

								// $('#refresh').removeClass('visible');
								// $('#refresh2').removeClass('visible');

							}, function error(data) {
								msg("网络异常!");

								if (fucOnFinished != null)
									fucOnFinished();

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

// filter
app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);

