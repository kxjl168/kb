var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});


function getRPath() {
	var http = basePath ;

	return http;
}



function test(){
	
	
	var url = getRPath()+"/manager/rsspagelist/test";
	
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

function InitSiteQuery(){
	var site = GetQueryString("s");
	var siteName = GetQueryString("sn");
	
	
	
	  $("#q_rss_m_id").html("");
      if( site)
		   {
	   var option = new Option(siteName,site, true, true);
	   
	   
	   
	    $("#q_rss_m_id").append($(option)).trigger('change');

	    // manually trigger the `select2:select` event
	    $("#q_rss_m_id").trigger({
	        type: 'select2:select',
	        params: {
	            data: {text:siteName, id:site}
	        }
	    });
		   }
      
	
}

$(function() {
	
	initRssSelect();
	
	InitSiteQuery();
	
	
	
	InitQuery_item();
	

	
	initmenu($("#menuul"), "manager/rsspagelist/manager/");
	
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

		var url = getRPath()+"/manager/rsspagelist/saveOrUpdate";

		if (flag) {
			var data = $("#mform_item").serialize();

			/**/

			$.ajax({
				type : "post",
				url : url,
				data : data,
				async : false,
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



function InitQuery_item() {
	// 初始化Table
	$('#table_list_item').bootstrapTable({
		url : getRPath()+'/manager/rsspagelist/rsspagelistList.do', // 请求后台的URL（*）
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
		pageSize : 20, // 每页的记录行数（*）
		pageList : [ 10,20,30 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

		// showColumns: true, //是否显示所有的列
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		// queryParamsType : "limit",
		queryParams : function queryParams(params) { // 设置查询参数
			
			var cids = $("#q_rss_m_id").val();
			var id=(cids == null || cids.length == 0) ? ""
					: cids.toString();
			
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sortName : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				
				isRead : $('#isRead').is(":checked")?"1":"",
				rssManagerId:id,
				title : $("#q_title").val(),
				link : $("#q_link").val(),
				
				
			};
			return param;
		},
		rowStyle: function (row, index) {
	             var style = "";     
	             if(row.isRead==1)
	                 style='readdone';             
	             return { classes: style }
	         } ,
		columns : [ {
			field : 'id',
			visible : false
		},
		 {
				field : 'name',
				title : '订阅站点',
				align : 'center',
				valign : 'middle',
				   
				
			},
		 {
				field : 'title',
				title : '文章标题',
				align : 'center',
				valign : 'middle',
				formatter : modifyAndDeleteButton_item,
				events : PersonnelInformationEvents_item   
				
			},
	
		 {
				field : 'context',
				title : '文章内容',
				align : 'center',
				valign : 'middle',
				 formatter: function (value, row, index) {
		             return value.substring(0,50)+"...";
		         }  
				
			},
			
			 {
				field : 'link',
				title : '文章链接',
				align : 'center',
				visible : false,
				valign : 'middle',
				 formatter: function (value, row, index) {
		             return "<a target='_blank' class='rsslinklist' href='"+value+"'>"+value+"</a>";
		         }     
				
			},
		 {
				field : 'isRead',
				title : '状态',
				align : 'center',
				valign : 'middle',
				visible : false,
				 formatter: function (value, row, index) {
		            if(value==1)
					 return "已读";
		            else
		            	return "未读";
		         }   
				
			},
		 {
				field : 'updateDate',
				title : '更新时间',
				align : 'center',
				valign : 'middle',
				   
		 formatter: function (value, row, index) {
            // return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
			 return value.substr(0,value.length-2);
         }
				
			},
		
		
		
		

		]
	});
}

function modifyAndDeleteButton_item(value, row, index) {
	return [ '<div class="">'
		+value+"&nbsp;"
		//	+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">修改</i> </button>&nbsp;'
			+ '<button id = "detail" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-book">详情</i> </button>'
			+ '</div>' ].join("");
}




window.PersonnelInformationEvents_item = {
	"click #detail" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url :getRPath()+ '/manager/rsspagelist/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {
				
			   $("#mform_item").fill(response);
			     
			   
			   $("#mform_item #title").html(response.title);
			   $("#mform_item #link").html(response.link);
			   $("#mform_item #link").attr('href',response.link);
			   
			   
			   $("#mform_item #updateDate").html(response.updateDate);
			   $("#mform_item #context").html(response.context);
	
			   
			   $("#myModal_item_title").html("查看");
			   
				$("#myModal_item").modal();
				
				query();
				
			
			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = getRPath()+"/manager/rsspagelist/delete";
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

function doSearch_item() {
	
	
	
	var opt = {
		silent : true
	};
	$("#table_list_item").bootstrapTable('refresh', opt);
	
	//success("test");
}

