
var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});



$(function() {
	initBtable();
	
	init();
	
	
	$("#is_done").change(function(){
		doquery();
	});
	
	
	
	// hljs.initHighlightingOnLoad();
	
	

	/*var $scope = angular.element(ngSection).scope();
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
	}, $scope.doupdate, "","");*/

});

function changerows(option) {
	var num = $(option).val();
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
};








function initBtable() {
	// 初始化Table
	$('#todolist').bootstrapTable({
		url :  basePath+'/todo/getInfoList.action', // 请求后台的URL（*）
		method : 'post', // 请求方式（*）
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toolbar', // 工具按钮用哪个容器
		showHeader : true,
		searchAlign : 'left',
		buttonsAlign : 'left',

		searchOnEnterKey : true,
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortName : 'id', // 排序字段
		sortOrder : "desc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

		// showColumns: true, //是否显示所有的列
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		// queryParamsType : "limit",
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sortName : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				isDone:$("#is_done").val(),
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		}, {
			field : 'title',
			title : '待办事项',
			align : 'left',
			width:'70%',
			valign : 'middle',
			formatter:function(value,row,index){
				var css="undone";
				if(row.isDone=='1')
					css="done";
				return '<div class="todothing  '+css+'">'+value+'</div><div class="thingtime">'+row.createDate+'</div>';
			}
		}

,
		
		{
			title : '操作',
			field : 'vehicleno',
			width:'30%',
			align : 'center',
			formatter : modifyAndDeleteButton,
			events : PersonnelInformationEvents
		}

		]
	});
}

function modifyAndDeleteButton(value, row, index) {
	var html=  '<div class="">';
	         
	         
	     	var css="undone";
			if(row.isDone==0)
				{
			html+='<button id = "update" type = "button" class = "btn btn-success"><i class="fa fa-check"></i> </button>&nbsp;';
				}
			html+= '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash"></i> </button>'
			+ '</div>' ;
			
			return html;
}

window.PersonnelInformationEvents = {
	"click #update" : function(e, value, row, index) {
		
		var id=row.id;
		var title="";
		update(id,title,1);//完成
		

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除事项吗？";
		var url = basePath+"/todo/delete";
		cconfirm2(msg,function() {
			$.ajax({
				type : "post",
				url : url,
				data : {
					"id" : row.id
				},
				dataType:'json',
				success : function(response) {
					if (response.ResponseCode=='200') {
						success("删除成功！");
						doquery();
					} else {
						error("删除失败！"+response.ResponseMsg);
					}
				}
			});
		});
		
	}
};

function doquery() {
	var opt = {
		url :  basePath+"/todo/getInfoList.action",
		silent : true
	};
	$("#todolist").bootstrapTable('refresh', opt);
	
	//success("test");
}

function update(id,title,is_done){
	
	if(id==null &&(title==null||title==""))
		{
		error("事项不能为空");
		return;
		}
	
	
	var http=getImUrl();
	
	var obj={};	
	obj.id=id;
	obj.title=title;
	obj.isDone=is_done;
	
	
	
	SZUMWS(http + "todo/saveOrUpdate.action", JSON
			.stringify(obj), function succsess(json) {

		var code = json.ResponseCode;
		var message = json.ResponseMsg;
	
		if (code == 200) {

			$("#t_title").val("");
			doquery();
			
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

	}, false,"json"

	);
}



function init() {

	initmenu_p($("#menuul"), "page/todo/");

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
			

			

				
			//	$scope.title = siteurl;
				
				$scope.page = 1;
				$scope.rows = 10;

				
				var title="TODO LIST!";
				//$("#title").html(title);

	
				$("#kwd").bind('keypress', function(event) {
					if (event.keyCode == "13") {
						$scope.search();
					}
				});
				
				$scope.doupdate = function(fm, value) {

					
					var title=$("#t_title").val();
					var is_done="0";
					update("",title,is_done);
					

				}
				
				$scope.add = function(url) {

					var obj = {};

				
					SZUMWS(http + "page/todo/init.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {
							
				

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("net work error！");
						

					}, false, false

					);

				};
			
				$scope.lastData=[];
				$scope.back=function()
				{
					
					
				};
				
				$scope.search = function(url) {

					var obj = {};

					
					if(typeof(url)=="undefined")
					obj.keyword = $scope.kwd;
					else
						obj.url = url;
				

					SZUMWS(http + "search/dosearch.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {
							
							

							var rst=$(json.html).find("#content_left").html();
							var rstpage=$(json.html).find("#page").html();
							
					
							var rcnt=$(json.html).find("#rcnt").html();
							
							if(typeof(rst)!='undefined')
							$("#srzt").html(rst+rstpage);
							else if(typeof(rcnt)!="undefined")
								{
								try {
									$("#srzt").html(json.html);
								} catch (e) {
									// TODO: handle exception
								};
								
								//google
								$("#srzt").find("#searchform").addClass("hide");
								$("#srzt").find("#footcnt").addClass("hide");
								$("#srzt").find(".hdtb-msb").addClass("hide");
								$("#srzt").find("#hdtb-msb").children().eq(1).hide();
								
								
								
								if($scope.lastData.length<10)
								$scope.lastData.push($("#srzt").html());
								}
								
							else
								$("#srzt").html(json.html);
								

							
								$scope.$apply();
						
						

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("net work error！");
						

					}, true, false

					);

				};
				
				$scope.stopDefault=function( e ) 
				 {       
				 if ( e && e.preventDefault )          
				     e.preventDefault();      
				 else          
				 
				 window.event.returnValue = false;              
				 return false;  }  ;
				
			

				
				
				$scope.canc=function(event,x){
					var row=	$("#rpdiv");

					kvalidate.resetForm("#fm");
						$("#rdivc").after(row);
						$("#cbtn").addClass("hide");	
				};
				

				$scope.beginReplay=function(event,x){
					$scope.preplay=x;
					var row=	$("#rpdiv");
				
					
					($(event.target).parent()).after(row);

					$("#cbtn").removeClass("hide");
	
				};
				
			});
				

};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
