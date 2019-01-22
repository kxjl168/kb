var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});


function getRPath() {
	var http = basePath ;

	return http;
}



function test(){
	
	
	var url = getRPath()+"/manager/cclist/test";
	
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

	
	
	$kfile.init({
		
		httppath:$("#httppath").val(),  //img -static目录前缀
		isimg:true,
		filesufix:'png,jpg,gif,jpeg,',
		maxFileSize:5*1024*1024,//5M
		maximgupload : 1,//最多可上传图片数量
		uploadurl:basePath + '/UploadFileXhr.action',//上传图片action url
		container:$("body").find('#upimgs'), //图片容器
		cleanpic:false,//再次弹出时是否清除图片显示
		uploaddonecallback:function(obj){
			
			//var appEndData='<img src="'+obj.FileUrl+'" orisrc="'+obj.FileUrl2+'" fid="'+obj.fileid+'"  class="img-responsive " onclick="showOriImg()"  >';
			
			$("#icon").val(obj.relativeURL);
			//CKEDITOR.instances.s_context.insertElement(ele);
		}
	});


	initmenu($("#menuul"), "manager/cclist/manager/");
	
	$("#btnAdd_item").click(function() {

		$kfile.get("upimgs").initFile(function(){
			
		});
		
		
		
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

		var url = getRPath()+"/manager/cclist/saveOrUpdate";

		if (flag) {
			var data = $("#mform_item").serialize();

			  var imgs= eval($kfile.get("upimgs").getAllFileIds());
		         var md5=$("#icon").val();//imgs[0].id;
		         
		         data+="&icon="+md5;
			
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
		url : getRPath()+'/manager/cclist/cclistList', // 请求后台的URL（*）
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
				
				
				
				name : $("#q_name").val(),
				link : $("#q_link").val(),
				
				
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		},
		 {
				field : 'name',
				title : '许可名称',
				align : 'center',
				valign : 'middle',
				   
				
			},
		 {
				field : 'link',
				title : '许可链接',
				align : 'center',
				valign : 'middle',
				   
				
			},
		 {
				field : 'icon',
				title : '图标',
				align : 'center',
				valign : 'middle',
				   
				
			},
		 {
				field : 'remark',
				title : '备注',
				align : 'center',
				valign : 'middle',
				   
				
			},
		 {
				field : 'createDate',
				title : '新建时间',
				align : 'center',
				valign : 'middle',
				   
		 formatter: function (value, row, index) {
             return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
         }
				
			},
		 {
				field : 'updateDate',
				title : '更新时间',
				align : 'center',
				valign : 'middle',
				   
		 formatter: function (value, row, index) {
             return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
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
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>' ].join("");
}




window.PersonnelInformationEvents_item = {
	"click #update" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url :getRPath()+ '/manager/cclist/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {
				
			   $("#mform_item").fill(response);
			     
	
			   $kfile.get("upimgs").initFile(function(){
				   $kfile.get("upimgs").addFile(response.icon,$("#httppath").val() +"/"+ response.icon);	
				});
			   
			   
			   $("#myModal_item_title").html("编辑");
			   
				$("#myModal_item").modal();
				
			
			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = getRPath()+"/manager/cclist/delete";
		cconfirm(msg,function() {
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

