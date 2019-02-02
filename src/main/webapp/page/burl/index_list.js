
var app = angular.module('myApp', [  ]);
/*app.run([ 'editableOptions', function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
} ]);*/
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

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_dict_key : {
			required : true,
		// version4: true,
		// minlength:4,
		},
		s_dict_name : "required",
		s_sort : {
			required : true,
		},
		/*s_type:"required", */
	/*	oldname:"required",*/
		
	}, {
		s_dict_key : {
			required : "请输入KEY",
		// version4: "版本号格式错误*.*.*.*",
		// minlength:"至少4个字符"
		},
		s_type:"请输入分类",
		s_dict_name : "请输入NAME",
		s_sort : {
			required : "请输入排序号",
		},
		/*oldname:"请选择图标文件",*/
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



function inittable(){
// 初始化Table
$('#table_detail').bootstrapTable({
    url:http + "statistics/GetUserVisitDetailList.action", // 请求后台的URL（*）
    method: 'post', // 请求方式（*）
    contentType: 'application/x-www-form-urlencoded',
    toolbar: '#toolbar', // 工具按钮用哪个容器

    showHeader: true,
    searchAlign: 'left',
    buttonsAlign: 'left',
    searchOnEnterKey: true,
    striped: true, // 是否显示行间隔色
    cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true, // 是否显示分页（*）
    sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
    pageNumber: 1, // 初始化加载第一页，默认第一页
    pageSize: 20, // 每页的记录行数（*）
    pageList: [10,20,30], // 可供选择的每页的行数（*）
    search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    detailView: false,
    // showColumns: true, //是否显示所有的列
    uniqueId: "id", // 每一行的唯一标识，一般为主键列
    // queryParamsType : "limit",
    queryParams: function queryParams(params) { // 设置查询参数
    	
    	var rows= params.limit; // 每页要显示的数据条数
       var offset= params.offset; // 每页显示数据的开始行号
       

    	var page=1+ offset/rows;
 
    	var sname= params.sort; // 要排序的字段
    	if(sname=='blogname')
    		sname="blog_id";
    	
        var param = {
        		pageCount: params.limit, // 每页要显示的数据条数
            offset: params.offset, // 每页显示数据的开始行号
            page:page,
            sortName: sname, // 要排序的字段
            sortOrder:  params.order,
            time1 : $("#effectDate").val(),
            time2: $("#effectDate2").val(),
            date_type : $("#dateType").val(),
         // qName : $("#s_type ").find("option:selected").text(),
         // date:cdate,
            userid:$("#userid").val(),
        };
        return param;
    },
    columns: [
    	{
            field: 'id',
            title: '订单编号',
            align: 'center',
            valign: 'middle',
            visible:false
        }, 
        {
            field: 'blogname',
            title: '文章',
            align: 'left',
            valign: 'middle',
            sortable : false,
            formatter: function (value, row, index) {
            	if(value==undefined)
            		value="";
                return "<a href='"+basePath+"/public/detail/?i="+row.blog_id+"'>"+value+"</a>";
            }
        },
      
      
        {
        field: 'userid',
        title: '访问ip',
        align: 'center',
        sortable : false,
        valign: 'middle' ,
        formatter: function (value, row, index) {
            return "<a href='javascript:void()' onclick='showdetail(\""+value+"\")'>"+value+"(访问详情)</a>";
        }
        }, 
       
        {
        field: 'city',
        title: '访问地市',
        align: 'center',
        valign: 'middle'
        }, 
        {
        field: 'action_date',
        title: '时间',
        align: 'center',
        valign: 'middle',
        sortable : false,
        },
        {
        	 field: 'referer',
        title: '来源',
        align: 'center',
        sortable : false,
        formatter: function (value, row, index) {
        	if(value&&value.length>50)
        		{
        		return "<a href='javascript:void()' onclick='showdetail(\""+value+"\")'>"+value.substr(0,50)+"(访问详情)</a>";
        		}
        	else
        		return "<a href='javascript:void()' onclick='showdetail(\""+value+"\")'>"+value+"(访问详情)</a>";
        		
            
        }
      
        }
    ],
});

}


function init() {

	initmenu($("#menuul"), "page/burl/index_list.jsp");

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

				

				$scope.load = function(type) {

					window.location.href = "../../page/" + type;
				};
				

				$scope.rss = function(item) {

					var url = http+"manager/rssmanager/saveOrUpdate";

					
						var data = "link="+item.url_val+"/feed";

						/**/

						$.ajax({
							type : "post",
							url : url,
							data : data,
							async : true,
							dataType : "json",
							success : function(response) {
								// debugger;
								if (response.bol) {
									//$('#myModal_item').modal("hide");
									//doSearch_item();
									success("操作成功！");
								} else {
									error( response.message);
								}
							}
						});
					
				};

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

				$scope.addOrModify = function(item) {
					kvalidate.resetForm("#fm");
					if (item != null) {
						$scope.edit = "编辑";
						$scope.s_recordid = item.id;
						$scope.s_dict_key = item.url_val;
						$scope.s_dict_name = item.url_name;
						$scope.s_type = item.url_type;

						$scope.s_sort = parseInt( item.sort);
						
						$scope.desc_info=item.desc_info;
						$scope.url = item.val1;
						$scope.oldname = item.val1;
						$scope.fullurl = item.val2+item.val1;
						$scope.val2 = item.val2;
						
						//$scope.isshow = item.isshow;
						$("#isshow").val(item.isshow);
						$scope.icon = item.icon;
						$scope.url = item.icon;
						$scope.fullurl = item.val2+item.icon;
						//$("#s_dict_key").attr('disabled','');
						$("#s_dict_key").removeAttr('disabled');

					} else {
						$scope.edit = "新增";
						$("#s_dict_key").removeAttr('disabled');
						$scope.s_recordid = "";
						$scope.s_dict_key = "";
						$scope.s_dict_name = "";
						$scope.s_type = "1";
						
						$scope.url = "";
						$scope.desc_info="";
						$scope.oldname="";						
						$scope.s_sort = "";
						$scope.fullurl = "";
						
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
						
						obj.isshow=$("#isshow").val();
						obj.icon=$("#url").val();
						
						
						
						//obj.val1 = $("#url").val();
						
						
					} else {
						obj = value;
					}
					
					
					obj.val1="2";//友情
					

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

			
				
				
				// $scope.getcompayList();

				$scope.title = "友情链接管理";
				// $scope.curpage=1;
				$scope.page = 1;
				$scope.rows = 20;

				$scope.rows_select = [ 10, 20, 30 ];
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

		

					var http = getImUrl();// "";

					var obj = new Object();
					obj.name = $scope.q_name;// "12345678";

					obj.page = $scope.page;// 1;// "12345678";
					obj.rows = $scope.rows;// 10;// "12345678";
					
					
					SZUMWS(
							http + "kurl/getYQListBack.action",
							JSON.stringify(obj),
							function succsess(json) {
								// var json = JSON.parse(decryData);
								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								console.log('-----return -code= ' + code
										+ ';message= ' + message);
								if (code == 200) {

									$scope.datalist = eval(json.datalist);
									$scope.val2 = json.val2;
									
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

								//	console.log('-----guideList -OK= ');

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
			

};

//filter
app.filter("ft", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ==1?"可见":"不可见";
	}
}]);

// filter
app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
