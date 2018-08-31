/**
 * 
 */



function getSelectIds(){
	//permission ids;
	var menuids="";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	if(zTree!=null)
		{
	//var nodes = zTree.getSelectedNodes();
		var nodes = zTree.getCheckedNodes();
	for ( var i = 0, l = nodes.length; i < l; i++) {		
		menuids+=nodes[i].id+"";

	}
		}
	
	return menuids;
}


function loadMenuTree(role_id)
{
	
	var setting = {
			isSimpleData : true, // 数据是否采用简单 Array 格式，默认false
			treeNodeKey : "id", // 在isSimpleData格式下，当前节点id属性
			treeNodeParentKey : "pId", // 在isSimpleData格式下，当前节点的父节点id属性
			
			check : {
				autoCheckTrigger : false,
				chkboxType : {"Y": "ps", "N": "ps"},
				chkStyle : "checkbox",
				enable : true,
				nocheckInherit : false,
				radioType : "level"
				},
			view : {
				showLine : true, // 是否显示节点间的连线
			//	addHoverDom : addHoverDom, // 增加节点 点击新增
			//	removeHoverDom : removeHoverDom,
				selectedMulti : false
			},
			edit : {
				enable : false,
				editNameSelectAll : false,
			//	renameTitle : renameTitle, // 编辑按钮说明文字
			//	removeTitle : removeTitle, // 删除按钮说明文字
			//	showRemoveBtn : showRemoveBtn, // 是否显示移除按钮
			//	showRenameBtn : showRenameBtn
			// 是否显示编辑按钮
			},
			callback : {
				onClick:function(){
					hideMenu();
				}
			//	beforeDrag : beforeDrag,
			//	beforeEditName : beforeEditName, // 编辑节点
			//	beforeRemove : beforeRemove, // 删除节点
			//	beforeRename : beforeRename,
			//	onRemove : onRemove,
			//	onRename : onRename,
			//	beforeDrop : beforeDrop,
			//	beforeClick : beforeClick,
			//	onCheck : onCheck
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPID : 0
				}
			}
		};
	
	
	var user_id=role_id;
	
	// 防止页面乱码现象
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		data : {
			user_id : user_id
		},
		dataType : "json",
		url :  basePath+"/Privilege/getRoleTree.action",// 请求的action路径
		error : function() {// 请求失败处理函数
			error('请求失败');
		},
		success : function(data) { // 请求成功后处理函数
			var data1 = eval('[' + data + ']');
//			if (typeof (data1.name) == undefined) {
//				data1.name = '';
//			}
			zNodes = data1; // 把后台封装好的简单Json格式赋给treeNodes

			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		}
	});


	
	
}