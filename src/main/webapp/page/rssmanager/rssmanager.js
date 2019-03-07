var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});


function getRPath() {
	var http = basePath ;

	return http;
}



function test(){
	
	
	var url = getRPath()+"/manager/rssmanager/test";
	
	$.ajax({
		type : "post",
		url : url,
		//data : data,
		async : false,
		dataType : "json",
		success : function(response) {
			success("操作成功！");
		}
	});
}

$(function() {
	InitQuery_item();


	initmenu($("#menuul"), "manager/rssmanager/manager/");
	
	$("#btnAdd_item").click(function() {

	
		  $('#mform_item')[0].reset();
		  
		  $('#mform_item').find("#id").val("");
		  
		$("#myModal_item_title").html("添加");
		
		
		
		$("#myModal_item").modal();
	});

	
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_item').on('hide.bs.modal', function(e) {
		
		if($(e.target).attr("type")) //日期选择等弹出框
			return;
		
		  $('#mform_item')[0].reset();

		$("#mform_item").data('bootstrapValidator').resetForm();

	});


	$("#btnSubmit_item").click(function() {
		
		
		
		$("#mform_item").data('bootstrapValidator').resetForm();
		
		
		 
		    // var bool2 = bv.isValid();
		$("#mform_item").data("bootstrapValidator").validate();
		// flag = true/false
		var flag = $("#mform_item").data("bootstrapValidator").isValid();

		var url = getRPath()+"/manager/rssmanager/saveOrUpdate";

		if (flag) {
			var data = $("#mform_item").serialize();

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
						$('#myModal_item').modal("hide");
						doSearch_item();
						success("操作成功！");
					} else {
						error( response.message);
					}
				}
			});
		}
	});

	initValidate_item();
	




});




function initValidate_item() {
	$("#mform_item").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

				name : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
			
		

		}
		

	
	});

}
//两个时间相差天数 兼容firefox chrome
function datedifference(sDate1, sDate2) {    //sDate1和sDate2是2006-12-18格式  
    var dateSpan,
        tempDate,
        iDays;
    sDate1 = Date.parse(sDate1);
    sDate2 = Date.parse(sDate2);
    dateSpan = sDate2 - sDate1;
    dateSpan = Math.abs(dateSpan);
    iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
    return iDays
};

function daybefore(sDate1) {    //sDate1和sDate2是2006-12-18格式  
    var dateSpan,
        tempDate,
        iDays;
    sDate1 = Date.parse(sDate1);
    sDate2 = new Date();
    dateSpan = sDate2 - sDate1;
    dateSpan = Math.abs(dateSpan);
    iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
    return iDays
};


function InitQuery_item() {
	// 初始化Table
	$('#table_list_item').bootstrapTable({
		url : getRPath()+'/manager/rssmanager/rssmanagerList', // 请求后台的URL（*）
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
		sortable : true, // 是否启用排序
	
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 30, // 每页的记录行数（*）
		pageList : [ 10,20 ,30 ,40], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

		// showColumns: true, //是否显示所有的列
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		// queryParamsType : "limit",
		queryParams : function queryParams(params) { // 设置查询参数
			
			
			var sname= params.sort; // 要排序的字段
			if(sname)
			{
        	if(sname=='noread')
        		sname="tp2.noread";
        	else if(sname=="hasError")
        		sname="c.has_error";
        	else if(sname=="createDate")
        		sname="c.create_date";
        	else 
        		sname="c."+sname;
			}
			else
				sname="";
        	
			
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sortName :sname, // 要排序的字段
				sortOrder : params.order, // 排序规则
				
				
				
				name : $("#q_name").val(),
				link : $("#q_link").val(),
			
				hasError: $("#has_error").val(),
				noread: $('#noread').is(":checked")?"1":"",
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		},
		 {
				field : 'name',
				title : '站点名称',
				align : 'center',
				valign : 'middle',
				 formatter: function (value, row, index) {
					 var add="";
					 if(row.mName)
					  add=  "("+row.mName+")";
			       return "<a target='_blank' title='"+row.remark+"' href='"+row.page_link+"'>"+value+add+"</a>";
				 }
				
			},
			
			 {
				field : 'noread',
				title : '新文章',
				align : 'center',
				  sortable : true,
				valign : 'middle',
				 formatter: function (value, row, index) {
			          if(value>0)
			        	  return "<a href='"+basePath+"/manager/rsspagelist/manager/?s="+row.id+"&sn="+row.name+"'><span class='text-info'><b>"+value+"</b></span></a>";
			          else 
			        	  return value;
			         }
				
			},
		 {
				field : 'link',
				title : 'Rss链接',
				align : 'center',
				valign : 'middle',
				 formatter: function (value, row, index) {
					 var addtime="";
					 var days=0;
					 var cls="text-success bold";
					 if(row.lastRssPageDate)
						 {
						 addtime=row.lastRssPageDate.substr(0,row.lastRssPageDate.length-11);
						 days =daybefore(addtime);
						 if(days>10)
							 cls='';
						 addtime= "<span class='"+cls+"'>"+days+"天前</span>更新";
						 }
					
					 
				       return "<a target='_blank' href='"+value+"' >"+value+"</a><div class='small rsspagetime'>"+addtime+"</div>";
					 }   
				
			},
		
		 {
				field : 'mRemark',
				title : '备注',
				align : 'center',
				valign : 'middle',
				 formatter: function (value, row, index) {
			          if(row.hasError=="1")
			        	  return "<span class='breakall' title='"+row.remark+"'>"+row.remark.substring(0,30)+"</span>";
			          else 
			        	  return value;
			         } 
				
			},
		 {
				field : 'autoRss',
				title : '订阅类型',
				align : 'center',
				valign : 'middle',
				 formatter: function (value, row, index) {
		          if(value==1)
		        	  return "自动";
		          else 
		        	  return "手动";
		         }
				
			},
		 {
				field : 'hasError',
				title : '订阅状态',
				sortable : true,
				align : 'center',
				valign : 'middle',
				 formatter: function (value, row, index) {
			          if(value==1)
			        	  return "<span class='text-danger'>失败</span>";
			          else 
			        	  return "<span class='text-success'>成功</span>";
			         } 
				
			},
		 {
				field : 'createDate',
				title : 'rss最后获取时间',
				align : 'center',
				valign : 'middle',
				sortable : true,
		 formatter: function (value, row, index) {
             //return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
			
			 return "<div>创建:<span class='small text-success'>"+row.createDate.substr(0,value.length-2)+ "</span></div>"
			 + "<div>更新:<span class='small text-success'>"+row.lastRssDate.substr(0,value.length-2)+ "</span></div>";
         }
				
			},
		
		
		
		{
			title : '操作',
			field : 'vehicleno',
			align : 'center',
			formatter : modifyAndDeleteButton_item,
			events : PersonnelInformationEvents_item
		}

		]
	});
}

function modifyAndDeleteButton_item(value, row, index) {
	return [ '<div class="">'
			+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">修改</i> </button>&nbsp;'
			+ '<button id = "readAllRss" type = "button" class = "btn btn-success"><i class="glyphicon glyphicon-refresh">全部已读</i> </button>&nbsp;'
			+ '<button id = "updateRss" type = "button" class = "btn btn-warning"><i class="glyphicon glyphicon-refresh">立即更新</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>' ].join("");
}




window.PersonnelInformationEvents_item = {
	"click #update" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url :getRPath()+ '/manager/rssmanager/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {
				
			   $("#mform_item").fill(response);
			     
	
			   
			   $("#myModal_item_title").html("编辑");
			   
				$("#myModal_item").modal();
				
			
			}
		});

	},
	
	"click #readAllRss" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url :getRPath()+ '/manager/rssmanager/readAllRss',
			data : {
				id : row.id
			},
			async : true,
			dataType : "json",
			success : function(response) {
				
			  info('成功!');
			  
			  doSearch_item();
			}
		});

	},

	"click #updateRss" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url :getRPath()+ '/manager/rssmanager/rssfresh',
			data : {
				id : row.id
			},
			async : true,
			dataType : "json",
			success : function(response) {
				
			  info('刷新成功!');
			  
			  doSearch_item();
			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = getRPath()+"/manager/rssmanager/delete";
		cconfirm2(msg,function() {
			$.ajax({
				type : "post",
				url : url,
				data : {
					"id" : row.id
				},
				success : function(response) {
					if (response.bol) {
						success("删除成功！");
						doSearch_item();
					} else {
						error(""+response.message);
					}
				}
			});
		});
		
	}
};

function refreshAll(){

	
		$.ajax({
			type : "post",
			url :getRPath()+ '/manager/rssmanager/rssfreshAll',
			data : {
				
			},
			async : true,
			dataType : "json",
			success : function(response) {
				
			  info('已开始刷新');
			}
		});

	
}

function doSearch_item() {
	
	
	
	var opt = {
		silent : true
	};
	$("#table_list_item").bootstrapTable('refresh', opt);
	
	//success("test");
}

