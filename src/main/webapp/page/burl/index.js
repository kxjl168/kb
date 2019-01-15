
var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});



$(function() {
	
	init();
	
	// hljs.initHighlightingOnLoad();
	
	

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

	//initmenu_p($("#menuul"), "page/burl/");
	//initmenu($("#menuul"), "page/burl/index_list.jsp");
	initmenu($("#menuul"), "page/burl/");

	
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
			

				
				
				$scope.title = "KxのBOOK";
				
				$scope.page = 1;
				$scope.rows = 10;

				
				var title="常用链接"+"-"+$scope.title;
				$("#title").html(title);

				document.title=title;
				

				$scope.getList = function(id, fucOnFinished, clear) {

					$scope.page = (id != null) ? id : 1;

					if ($scope.page > $scope.pageNum)
						$scope.page = $scope.page - 1;

					if ($scope.page <= 0)
						$scope.page = 1;

					

					var http = getImUrl();

					var obj = new Object();


					obj.blog_type = $scope.blog_type;
					obj.month = $scope.month;
					obj.blog_tag = $scope.blog_tag;
					
					obj.show ="-1"; //自己查看全部
					obj.page = $scope.page;
					obj.rows = $scope.rows;

					SZUMWS(
							http + "kurl/getShowInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {
							
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

								
									
									
									$scope.datalist = eval(json.datalist);
									
									$.each( $scope.datalist ,function(index,item){
										item.val=eval(item.val);
										$scope.datalist[index]=item;
									});
									
									
									$scope.$apply();
								

								} else {
									msg(message);
								}

								

							}, function error(data) {
								msg("网络异常!");

							
							}, false, false

					);

				};
				
				$scope.getList();
				

				
				
				

				$scope.del = function(item) {
					if (item == null)
						return;

					var id = item.id;

					$("#myModal3").modal("show");
					$("#btnconfirm").one(
							"click",
							function() {

								var obj = {};

								obj.recordid = id;

								SZUMWS(http + "kurl/del.action", JSON
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
				
				$scope.selectfile = function () {
					$("#myModal1").modal('show');
				}

				$scope.addOrModify = function(item,typename) {
					kvalidate.resetForm("#fm");
					if (item != null) {
						$scope.edit = "编辑";
						$scope.s_recordid = item.id;
						$scope.s_dict_key = item.url_val;
						$scope.s_dict_name = item.url_name;
						$scope.s_type = item.url_type;

						$scope.s_sort = parseInt( item.sort);
						
						$scope.desc_info=item.desc_info;
						$scope.url = item.icon;
						$scope.oldname = item.val1;
						$scope.fullurl = item.val2+item.icon;
						$scope.val2 = item.val2;
						
						//$scope.isshow = item.isshow;
						$("#isshow").val(item.isshow);
						$scope.icon = item.icon;
						
						
						//$("#s_dict_key").attr('disabled','');
						$("#s_dict_key").removeAttr('disabled');

					} else {
						$scope.edit = "新增";
						$("#s_dict_key").removeAttr('disabled');
						$scope.s_recordid = "";
						$scope.s_dict_key = "";
						$scope.s_dict_name = "";
						$scope.s_type = "";
						if(typeof (typename)!='undefinded')
							$scope.s_type = typename;
						
						$scope.url = "";
						$scope.desc_info="";
						$scope.oldname="";						
						$scope.s_sort = "";
				
						
						$("#isshow").val("1");
						$("#url").val("");
						$scope.fullurl = "";
					}

				
					$("#myModal2").modal('show');
				}
				var http = getImUrl();// "";

				$scope.doupdate = function(fm, value) {

					var obj = {};

					if (typeof (value) == "undefined") {
						obj.recordid = $scope.s_recordid;
						obj.key = $scope.s_dict_key;
						obj.name = $scope.s_dict_name;
						obj.utype = $scope.s_type;

						obj.sort = $scope.s_sort;
						obj.desc_info = $scope.desc_info;
						
						obj.val1 = $("#url").val();
						
						obj.isshow=$("#isshow").val();
						obj.icon=$("#url").val();
						
						
					} else {
						obj = value;
					}
					
					obj.val1="1";//友情

					SZUMWS(http + "kurl/addOrUpdate.action", JSON
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
				
				
			});
	
	
	
};

//filter
app.filter("ft", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ==1?"可见":"不可见";
	}
}]);

//filter
app.filter("ftc", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ==1?"text-success":"text-danger";
	}
}]);

app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
