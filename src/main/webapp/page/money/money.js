var app = angular.module('myApp', [ "ngSanitize" ]);

app.controller('eduCtrl', function($scope) {

});

function getRPath() {
	var http = basePath;

	return http;
}

function test() {

	var url = getRPath() + "/manager/money/test";

	$.ajax({
		type : "post",
		url : url,
		// data : data,
		async : false,
		dataType : "json",
		success : function(response) {
			success("操作成功！");
		}
	});
}

function initDatePicker(ele, type) {
	// $.fn.datepicker.defaults.format = "yyyy-mm-hh";

	var fmt = "yyyy-mm-dd";
	var mMode = "days";
	var date = new Date();

	if (type != null && type == 'month') {
		fmt = "yyyy-mm";
		mMode = "months";
	}

	$(ele).datepicker({
		language : "zh-CN",
		endDate : new Date(),
		format : fmt,
		autoclose : true,
		minViewMode : mMode,
		defaultDate : new Date(),
	});

	$(ele).val(
			date.getFullYear() + "-" + PrefixInteger((date.getMonth() + 1), 2)
					+ "-" + PrefixInteger(date.getDay(), 2));
	if (type != null && type == 'month') {
		$(ele).val(
				date.getFullYear() + "-"
						+ PrefixInteger((date.getMonth() + 1), 2));
	}
}

// num传入的数字，n需要的字符长度 ，批量添加房间数，房号计算，左加0
function PrefixInteger(num, n) {
	return (Array(n).join(0) + num).slice(-n);
}

$(function() {
	InitQuery_item();

	initTypeSelect();
	//modal select输入
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	

	initDatePicker("#mDate");
	initDatePicker("#q_month", "month");

	showTotal();
	

	$("#q_month").change(function(){
		doSearch_item();
	});
	

	
	initmenu($("#menuul"), "manager/money/manager/");

	$("#btnAdd_item").click(
			function() {

				$('#mform_item')[0].reset();

				$('#mform_item').find("#id").val("");

				$("#myModal_item_title").html("添加");

				var date = new Date();
				$('#mDate').val(
						date.getFullYear() + "-"
								+ PrefixInteger((date.getMonth() + 1), 2) + "-"
								+ PrefixInteger(date.getDay(), 2));
				$("#myModal_item").modal();
			});

	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_item').on('hide.bs.modal', function(e) {

		if ($(e.target).attr("type")) // 日期选择等弹出框
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

		var url = getRPath() + "/manager/money/saveOrUpdate";

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
						error(response.message);
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
			money : {
				validators : {
					notEmpty : {
						message : '不能为空'
					},
					regexp : {
						regexp : /([0-9]+|0)\.?([0-9]*)$/i,

						message : '金额异常'
					}
				}

			},
			mType : {
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
	$('#table_list_item')
			.bootstrapTable(
					{
						url : getRPath() + '/manager/money/moneyList', // 请求后台的URL（*）
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
						pageSize : 30, // 每页的记录行数（*）
						pageList : [ 10, 20, 30, 40 ], // 可供选择的每页的行数（*）
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
								mDate : $("#q_month").val(),

							};
							return param;
						},
						rowStyle : function(row, index) {
							var style = "";
							if (row.inOut == 1)
								style = 'shouru';
							else if (row.inOut == 2)
								style = 'zhichu';
							return {
								classes : style
							}
						},
						columns : [
								{
									field : 'id',
									visible : false
								},
								{
									field : 'mDate',
									title : '日期',
									align : 'center',
									valign : 'middle',
									formatter : function(value, row, index) {

										return "<div class='row moneydate'><div class=''>"
												+ value + "</div></div>";

									}

								},
								{
									field : 'typeName',
									title : '分类',
									align : 'left',
									valign : 'middle',
									formatter : function(value, row, index) {

										return "<div class='row moneytype'><img class='pull-left img-responsive' style='max-width:20px;' src='"
												+ $("#httppath").val()
												+ row.typeIcon
												+ "'><div class='text-left ttxt'>"
												+ value
												+ "</div></div>"
												+ "<div class='row mtip'>"
												+ row.remark + "</div>";

									}

								},
								{
									field : 'money',
									title : '金额',
									align : 'center',
									valign : 'middle',
									formatter : function(value, row, index) {
										var flag = "";
										if (value > 0)
											flag = "+";
										return "<div class='row moneyvalue'><div class=''>"
												+ flag + value + "</div></div>";

									}

								},

								{
									field : 'inOut',
									title : '收支,1收，2支',
									align : 'center',
									visible : false,
									valign : 'middle',

								},
								{
									field : 'spec',
									title : '特殊记录,1是，0否',
									align : 'center',
									visible : false,
									valign : 'middle',

								},
								{
									field : 'createUser',
									title : '记录人',
									align : 'center',
									visible : false,
									valign : 'middle',

								},
								{
									field : 'updateUser',
									title : '更新人',
									visible : false,
									align : 'center',
									valign : 'middle',

								},
								{
									field : 'remark',
									title : '备注',

									align : 'center',
									valign : 'middle',
									formatter : function(value, row, index) {

										return "<div class='row mtip2'><div class=''>"
												+ value + "</div></div>";

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
			url : getRPath() + '/manager/money/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {

				$("#mform_item").fill(response);

				$("#inOut").fill(response.inOut);

				var option = new Option(response.typeName, response.mType,
						true, true);

				$("#mType").append($(option)).trigger('change');

				// manually trigger the `select2:select` event
				$("#mType").trigger({
					type : 'select2:select',
					params : {
						data : {
							text : response.typeName,
							id : response.mType
						}
					}
				});

				$("#myModal_item_title").html("编辑");

				$("#myModal_item").modal();

			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = getRPath() + "/manager/money/delete";
		cconfirm2(msg, function() {
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
						error("" + response.message);
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

	showTotal();
	// success("test");
}

//刷新饼图
function refreshBChart() {
	var url=getRPath() + "/manager/money/zhichuStastic";
	$.ajax({
		type : "post",
		url : url,
		dataType : 'json',
		data : {
			mDate : $("#q_month").val(),
		},
		success : function(response) {
			if (response) {

				setchartdata(response,$("#q_month").val());

			} else {
				// error(""+response.message);
			}
		}
	});
}


function showTotal() {
	var url=getRPath() + "/manager/money/total";
	$.ajax({
		type : "post",
		url : url,
		dataType : 'json',
		data : {
			mDate : $("#q_month").val(),
		},
		success : function(response) {
			if (response) {

				$.each(response, function(index, item) {
					if (item.mType == "in") {
						$("#shouruspan").html("+" + item.money);
					}
					if (item.mType == "out") {
						$("#zhichuspan").html( item.money);
					}
					if (item.mType == "totol") {
						$("#totalspan").html( item.money>0?("+"+item.money):item.money);
						if(item.money>0)
							$("#totalspan").removeClass('shouruspan').removeClass('zhichuspan').addClass('shouruspan');
						else
							$("#totalspan").removeClass('shouruspan').removeClass('zhichuspan').addClass('zhichuspan');
					}
				});
				
				
				//刷新饼图
				refreshBChart();

			} else {
				// error(""+response.message);
			}
		}
	});
}
