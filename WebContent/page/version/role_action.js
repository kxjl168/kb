var divdlg;

function returnList() {
	parent.openNewPage(webctx + "/Privilege/InitRoleList.do");
}


$(function(){
	
	//loadPackageInfo();
	
	action_load();
});


	









var info =null;


function getStrLength(str) { 
    var len = str.length; 
    var a = "";
    var reg = new RegExp("\\[[\u4E00-\u9FA5]{2,4}\\]","g");
	a=str.replace(reg,"1");
    return a.length;    
}

function reloadHeight()
{
//	var ht=$(".contentMain").height();
//	if(ht<600)
//		ht=700;
	
	var ht=1200;
	
	$("#td_menu").height(600);
	
	$(".contentMain").height(ht);
	
	$(window.parent.document).find("iframe").height(ht);
	$(window.parent.document).height(ht+100);

// $(".theadTbl th").each(function(index,element){
// $(".tbodyTbl td").eq(index).width(this.offsetWidth);
// });
}

function loadMenuTree()
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
				editNameSelectAll : true,
			//	renameTitle : renameTitle, // 编辑按钮说明文字
			//	removeTitle : removeTitle, // 删除按钮说明文字
			//	showRemoveBtn : showRemoveBtn, // 是否显示移除按钮
			//	showRenameBtn : showRenameBtn
			// 是否显示编辑按钮
			},
			callback : {
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
	
	
	var role_id=$("#role_id").val();
	
	// 防止页面乱码现象
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		data : {
			role_id : role_id
		},
		dataType : "json",
		url :  basePath + '/Privilege/getMenuTree.do',// 请求的action路径
		error : function() {// 请求失败处理函数
			parent.gloabeAlert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数
			var data1 = eval('[' + data + ']');
//			if (typeof (data1.name) == undefined) {
//				data1.name = '';
//			}
			zNodes = data1; // 把后台封装好的简单Json格式赋给treeNodes

			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
//			if (loadcomplateCallBack != null)
//				loadcomplateCallBack();

		}
	});

	$("#selectAll").bind("click", selectAll);
	$("#treeDemo a").each(
			function() {
//				if ($(this).attr("title").indexOf(searchName) != -1
//						&& searchName.length > 0) {
//					$(this).css("background-color", "#FFF8DC");
//					$(this).bind("click", function() {
//						$(this).css("background-color", "");
//					});
//				}
			});	
	
	
}
function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	
	var ck= $("#selectAll").get(0).checked;
	//alert(ck);
	zTree.checkAllNodes(ck);
}


function action_load() {
	$(function() {

		loadMenuTree();
		
		reloadHeight();
		
		
		$("input[type=checkbox]").iSimulateCheckbox({
			switchClass : 'WellCheckBoxH'
		});

		
		var type = GetQueryString('type');
		var view = {
			label : "添加",
			OkFunction : "addInfo"
		};
		if (type == "add") {
			
			
			
		} else if (type == "edit") {
			var view = {
				label : "编辑",
				OkFunction : "updateInfo"
			};
			
			
			$("#addtip").hide();
			
			$('#role_id').attr("readonly","readonly")
			$('#role_id').attr("style","background:#999");
		}
		
		$(".currentPosition").html(Mustache.render($(".currentPosition").html(), view));
		$(".contentTitle").html(Mustache.render($(".contentTitle").html(), view));
		$("#actionRow").html(Mustache.render($("#actionRow").html(), view));
		

		$(function() {
			// showAddApp();

			// $("#showLogoDiv").bind("click", function(e) {
			// showLogoUploadDiv();
			// });
			// $("#closeLogoDiv").bind("click", function(e) {
			// closeUploadLogofile();
			
			
			
			
			
			// });
			//		
			// $("#showfileDiv").bind("click", function(e) {
			// showFileUploadDiv();
			// });
			// $("#closefileDiv").bind("click", function(e) {
			// closeUploadPackagefile();
			// });
			//		
			//		
			$("#showfileDiv").bind("click", function(e) {

				// $("#bodyContent").hide();
				// $("#uploadFileDiv").show();

				divdlg = new Dialog();
				divdlg.Title = "选择APP文件";
				divdlg.Width = 500;
				divdlg.Height = 180;
				divdlg.ShowButtonRow = true;
				divdlg.URL = basePath + '/jsp/app/fileAppUpload.jsp';
				divdlg.OnLoad = function() {
				};

				divdlg.OKEvent = function() {

				};
				divdlg.CancelEvent = function() {
					divdlg.close();
				};

				divdlg.ShowOKButton = false;
				divdlg.ShowCancelButton = false;

				divdlg.OKButtonTxt = "确 定";
				divdlg.CancelButtonTxt = "取消";

				divdlg.show();

			});
			$("#showLogoDiv").bind("click", function(e) {
				divdlg = new Dialog();
				divdlg.Title = "选择图标文件";
				divdlg.Width = 500;
				divdlg.Height = 180;
				divdlg.ShowButtonRow = true;
				divdlg.URL = basePath + '/app/initGroupLogoUpload.do';
				divdlg.OnLoad = function() {
				
				};

				divdlg.OKEvent = function() {

				};
				divdlg.CancelEvent = function() {
					divdlg.close();
				};

				divdlg.ShowOKButton = false;
				divdlg.ShowCancelButton = false;

				divdlg.OKButtonTxt = "确 定";
				divdlg.CancelButtonTxt = "取消";

				divdlg.show();
			});
			
			
		
			
			
			
			
			

		});
		
		
		

		function showLogoUploadDiv() {
			$("#bodyContent").hide();
			$("#uploadFileDiv").hide();
			$("#uploadLogoDiv").show();
		}

		function showFileUploadDiv() {
			$("#bodyContent").hide();
			$("#uploadFileDiv").show();
			$("#uploadLogoDiv").hide();
		}

		function showAddApp() {
			// alert(1);
			$("#uploadLogoState").hide();
			$("#uploadLogSub").show();
			$("#closeLogoDiv").show();
			$("#uploadfileState").hide();
			$("#uploadFileSub").show();
			$("#closefileDiv").show();

			$("#bodyContent").show();
			$("#uploadFileDiv").hide();
			$("#uploadLogoDiv").hide();
		}

		function closeUploadLogofile() {
			showAddApp();
		}

		function closeUploadPackagefile() {
			showAddApp();
		}

		function widthCheck(s, n) {
			var w = 0;
			for ( var i = 0; i < s.length; i++) {
				var c = s.charCodeAt(i);
				// 单字节加1
				if ((c >= 0x0001 && c <= 0x007e)
						|| (0xff60 <= c && c <= 0xff9f)) {
					w++;
				} else {
					w += 2;
				}
			}
			if (w > n) {
				return false;
			}
			return true;
		}

		/**
		 * 上传文件
		 */
		function uploadPackage() {
			showFileUploadDiv();
		}

		// 上传图标
		function uploadLogo() {
			showLogoUploadDiv();
		}

		/**
		 * 上传图标
		 */
		var sh = null;
		function uploadLogofile() {
			$("#uploadLogoState").show();
			var fileName = $("#logoUploadURL").val();
			if (fileName != null && "" != fileName) {
				var fileExtension = fileName.substring(fileName
						.lastIndexOf(".") + 1, fileName.length);
				if ("jpg" == fileExtension.toLowerCase()
						|| "png" == fileExtension.toLowerCase()
						|| "bmp" == fileExtension.toLowerCase()
						|| "jpeg" == fileExtension.toLowerCase()) {
					logoform.action = "logoUploadFile.do";
					logoform.submit();
					$("#uploadLogSub").hide(); // 隐藏上传文件按钮
					$("#uploadLogoState").show(); // 显示上传文件后面的图片
					$("#closeLogoDiv").hide(); // 隐藏关闭按钮

					if (sh == null) {
						sh = setInterval(lookupLogoUploadUrl, 500);
					}
				} else {
					$("#uploadLogSub").show();
					$("#uploadLogoState").hide();
					$("#closeLogoDiv").show();
					alert("请上传jpg,png,bmp,jpeg格式的文件！");
					$("#uploadLogoState").hide();
				}
			} else {
				alert("请选择上传图标！");
				$("#uploadLogoState").hide();
			}
		}

		/**
		 * 上传应用包
		 */
		function uploadPackagefile() {
			$("#uploadfileState").show();
			var fileName = $("#fileUploadURL").val();
			if (fileName != null && "" != fileName) {
				var fileExtension = fileName.substring(fileName
						.lastIndexOf(".") + 1, fileName.length);
				if ("apk" == fileExtension.toLowerCase()) {
					fileform.action = "versionUploadFile.do";
					fileform.submit();
					$("#uploadFileSub").hide(); // 隐藏上传文件按钮
					$("#uploadfileState").show(); // 显示上传文件后面的图片
					$("#closefileDiv").hide(); // 隐藏关闭按钮

					if (sh == null) {
						sh = setInterval(lookupPackageUploadUrl, 500);
					}
				} else {
					$("#uploadFileSub").show();
					$("#uploadfileState").hide();
					$("#closefileDiv").show();
					alert("请上传apk格式的应用包！");
					$("#uploadfileState").hide();
				}
			} else {
				alert("请选择上传图标！");
				$("#uploadfileState").hide();
			}
		}

		// 获得上传图标后返回的数据
		function lookupLogoUploadUrl() {
			var obj = document.getElementById("logoUploadFrame").contentWindow;
			var uploadUrl = null;
			var fileMd5 = null;
			if (obj.document.getElementById("uploadUrl") != null)
				uploadUrl = obj.document.getElementById("uploadUrl").value;
			if (uploadUrl != null && uploadUrl != "") {
				if (uploadUrl == '202') {
					alert("请选择上传文件！");
				} else if (uploadUrl == '203') {
					alert("文件上传失败！");
				} else if (uploadUrl == '204') {
					alert("请选择要上传的文件！");
				} else if (uploadUrl == '205') {
					alert("文件上传失败，文件大小超过30M！");
				} else if (uploadUrl == '206') {
					alert("文件已经存在！");
				} else {
					$("#logoUploadUrl1").val(uploadUrl);
					$("#logoWebUrl").val(
							obj.document.getElementById("newFileName").value);
					alert("文件上传成功！");
				}

				var imgsrc = "../../"
						+ obj.document.getElementById("newFileName").value;
				alert(imgsrc);
				$("#appIcoImg").attr("src", imgsrc);
				clearInterval(sh);
				sh = null;
				showAddApp();
				return;
			}
		}

		// 获得上传应用后返回的数据
		function lookupPackageUploadUrl() {
			var obj = document.getElementById("fileUploadFrame").contentWindow;
			var uploadUrl = null;
			var fileMd5 = null;
			if (obj.document.getElementById("uploadUrl") != null)
				uploadUrl = obj.document.getElementById("uploadUrl").value;
			if (uploadUrl != null && uploadUrl != "") {
				if (uploadUrl == '202') {
					alert("请选择上传的应用！");
				} else if (uploadUrl == '203') {
					alert("上传应用失败！");
				} else if (uploadUrl == '204') {
					alert("请选择要上传的应用！");
				} else if (uploadUrl == '205') {
					alert("应用应用失败，应用大小超过30M！");
				} else if (uploadUrl == '206') {
					alert("应用已经存在！");
				} else {
					$("#packageUpload").val(uploadUrl);
					$("#packageWebUrl").val(
							obj.document.getElementById("newFileName").value);
					$("#packagefilesize").val(
							obj.document.getElementById("filesize").value);
					alert("应用上传成功！");
				}

				clearInterval(sh);
				sh = null;
				showAddApp();
				return;
			}
		}

	});
}

// 添加信息
function addInfo(diag) {

	if (!$("#fm").form("validate"))
		return;

	var role_id = jQuery.trim($("#role_id").val());
	//var banner_name = jQuery.trim($("#banner_name").val());
	var role_name = jQuery.trim($("#role_zh").val());
	var role_name = jQuery.trim($("#role_zh").val());
	var desc=$("#desc").val();
	
	if(getStrLength(desc)>100)
		{
		parent.gloabeAlert('说明文字超长!');
		return ;
		}
	
	var menuids="";
	
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getCheckedNodes(true);
	for ( var i = 0, l = nodes.length; i < l; i++) {		
		menuids+=nodes[i].id+",";

	}
	
	ajaxLoading();
	

	$.ajax({
		url : basePath + "/Privilege/addOrUpdateRoleInfo.do",
		type : "post",
		data : {
			role_id : role_id,
			role_name : role_name,
			menuids:menuids,
			desc:desc
		},
		dataType : "json",
		error : function() {// 请求失败处理函数
			parent.gloabeAlert('请求失败');
			ajaxLoadEnd();
		},
		success : function(data) { // 请求成功后处理函数。
			
			ajaxLoadEnd();
			
			if (data.ResponseCode == 200) {
				parent.gloabeAlert("添加成功!");

				returnList();
				// divdlg.dialog('close');
				// divdlg
				// search();
				// window.returnValue=1;
				// window.close();
				// window.location.reload();
			} else if (data.ResponseCode == 201) {
				parent.gloabeAlert("添加失败！");
				return;
			} else if (data.ResponseCode == 202) {
				// parent.gloabeAlert("该版本号已存在，请重新输入！");
				return;
			} else {
				parent.gloabeAlert(data.ResponseMsg);
				// window.returnValue=1;
			}

		}
	});
}

function openBtn() {
}

function closeBtn() {
}


function isCannel() {
	history.go(-1);
}


function updateInfo() {
	if (!$("#fm").form("validate"))
		return;
	var role_id = jQuery.trim($("#role_id").val());
	//var banner_name = jQuery.trim($("#banner_name").val());
	var role_name = jQuery.trim($("#role_zh").val());
	var desc=$("#desc").val();
	if(getStrLength(desc)>100)
	{
	parent.gloabeAlert('说明文字超长!');
	return ;
	}
	var menuids="";
	
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getCheckedNodes(true);
	for ( var i = 0, l = nodes.length; i < l; i++) {		
		menuids+=nodes[i].id+",";

	}
	ajaxLoading();
	
	$.ajax({
		url : basePath + "/Privilege/addOrUpdateRoleInfo.do",
		type : "post",
		data : {
			role_id : role_id,
			role_name : role_name,
			menuids:menuids,
			desc:desc
		},
		dataType : "json",
		error : function() {// 请求失败处理函数
			parent.gloabeAlert('请求失败');
			ajaxLoadEnd();
		},
		success : function(data) { // 请求成功后处理函数。
			ajaxLoadEnd();
			if (data.ResponseCode == 200) {
				parent.gloabeAlert("修改成功!");

				returnList();
				// divdlg.dialog('close');

				// search();

				// window.returnValue=1;
				// window.close();
				// window.location.reload();
			} else if (data.ResponseCode == 201) {
				parent.gloabeAlert("修改失败！" + data.ResponseMsg);
				return;
			} else if (data.ResponseCode == 202) {
				// parent.gloabeAlert("该(版本/版本及补丁)号已存在，请重新输入！");
				return;
			} else {
				parent.gloabeAlert(data.ResponseMsg);
				// window.returnValue=1;
			}

			// divdlg

		}

		
	});

	
}
function widthCheck(s, n) {
	var w = 0;
	for ( var i = 0; i < s.length; i++) {
		var c = s.charCodeAt(i);
		// 单字节加1
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
			w++;
		} else {
			w += 2;
		}
	}
	if (w > n) {
		return false;
	}
	return true;
}
function GetQueryString(name) {

	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");

	var r = window.location.search.substr(1).match(reg);

	if (r != null)
		return unescape(r[2]);
	return null;

}

// 判断版本号格式是否为IP格式
function jsreg_verify(versionNum) {
	// var reg = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
	var reg = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
	if (reg.exec(versionNum) != null) {
		if (RegExp.$1 < 0 || RegExp.$1 > 255)
			return false;
		if (RegExp.$2 < 0 || RegExp.$2 > 255)
			return false;
		if (RegExp.$3 < 0 || RegExp.$3 > 255)
			return false;
		// if(RegExp.$4<0 || RegExp.$4>255) return false;
	} else {
		return false;
	}
	return true;
}


/*
 * 在页面上把添加的系统属性显示出来
 */
function showAppPackageInfo(info) {
	if (0 == info.clienttype) { // android
		addAndroidInfo(info);
	} else if (1 == info.clienttype) { // windows
		addPCInfo(info);
	} else if (2 == info.clienttype) { // IOS
		addIOSInfo(info);
	} else if (3 == info.clienttype) { // ipad
		addIpadInfo(info);
	}
}

/*
 * 添加IOS设置
 */
function addIOSInfo(info) {
	var newTr = topWin[0].sysAttributeTab.insertRow();
	newTr.style.height = '80px';
	var newTd0 = newTr.insertCell();

	var htmlValue = "适用平台: IOS <br/>";
	htmlValue = htmlValue + getHtmlVale(info);
	newTd0.innerHTML = htmlValue;

	var newTd1 = newTr.insertCell();
	htmlValue = getOperatorHtml(parseInt(info.maintenance), info.clienttype,
			info.apptype);
	newTd1.align = 'right';
	newTd1.innerHTML = htmlValue;
}

/*
 * 添加PC设置
 */
function addPCInfo(info) {
	var newTr = sysAttributeTab.insertRow();
	newTr.style.height = '80px';
	var newTd0 = newTr.insertCell();

	var htmlValue = "适用平台: Windows <br/>";
	htmlValue = htmlValue + getHtmlVale(info);
	newTd0.innerHTML = htmlValue;

	var newTd1 = newTr.insertCell();
	htmlValue = getOperatorHtml(parseInt(info.maintenance), info.clienttype,
			info.apptype);
	newTd1.align = 'right';
	newTd1.innerHTML = htmlValue;
}

/*
 * 添加Android设置
 */
function addAndroidInfo(info) {
	var newTr = topWin[0].sysAttributeTab.insertRow();
	newTr.style.height = '80px';
	var newTd0 = newTr.insertCell();

	var htmlValue = "适用平台: Android <br/>";
	htmlValue = htmlValue + getHtmlVale(info);
	newTd0.innerHTML = htmlValue;

	var newTd1 = newTr.insertCell();
	htmlValue = getOperatorHtml(parseInt(info.maintenance), info.clienttype,
			info.apptype);
	newTd1.align = 'right';
	newTd1.innerHTML = htmlValue;
}

/*
 * 根据App类型,获取系统属性显示信息
 */
function getHtmlVale(info) {
	var htmlValue = "";
	if (info.apptype == 0) { // webApp
		htmlValue = htmlValue + "类型: Web App";
		htmlValue = htmlValue + "<br/>";
		htmlValue = htmlValue + "URL: " + info.webUrl;
	} else if (info.apptype == 1) { // nativeApp
		htmlValue = htmlValue + "类型: Native App";
		htmlValue = htmlValue + "<br/>";
		htmlValue = htmlValue + "安装包URL: " + info.webUrl;
		htmlValue = htmlValue + "<br/>";
		htmlValue = htmlValue + "进程名: " + info.appProcess;
	} else if (info.apptype == 2) { // hybirdApp
		htmlValue = htmlValue + "类型: Hybird App";
		htmlValue = htmlValue + "<br/>";
		htmlValue = htmlValue + "版本: " + info.appVersion;
	} else if (info.apptype == 3) { // 内置app
		htmlValue = htmlValue + "类型: 内置应用";
		htmlValue = htmlValue + "<br/>";
		htmlValue = htmlValue + "类名: " + info.appProcess;
	}
	return htmlValue;
}

/*
 * 根据App类型，获取显示操作信息
 */
function getOperatorHtml(maintenance, clientType, appType) {
	var htmlValue = "";
	if (1 == maintenance) { // 为正常状态，显示暂停服务
		if (appType == 0 || appType == 2) { // webApp,hybirdApp
			htmlValue = '<a href="javascript:void(0);" onclick="stopService(this,';
			htmlValue = htmlValue + clientType;
			htmlValue = htmlValue + ",";
			htmlValue = htmlValue + appType;
			htmlValue = htmlValue + ');">暂停服务</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		}
	} else if (0 == maintenance) { // 为暂停服务状态，显示恢复服务与暂停服务说明
		if (appType == 0 || appType == 2) { // webApp,hybirdApp
			htmlValue = '<a href="javascript:void(0);" onclick="showExplain(this,';
			htmlValue = htmlValue + clientType;
			htmlValue = htmlValue + ",";
			htmlValue = htmlValue + appType;
			htmlValue = htmlValue + ');">暂停服务说明</a>&nbsp;&nbsp;&nbsp;&nbsp;';

			htmlValue = htmlValue
					+ '<a href="javascript:void(0);" onclick="resumeService(this,';
			htmlValue = htmlValue + clientType;
			htmlValue = htmlValue + ",";
			htmlValue = htmlValue + appType;
			htmlValue = htmlValue + ');">恢复服务</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		}
	}

	htmlValue = htmlValue
			+ '<a href="javascript:void(0);" onclick="modifySysAttr(this,';
	htmlValue = htmlValue + clientType;
	htmlValue = htmlValue + ",";
	htmlValue = htmlValue + appType;
	htmlValue = htmlValue + ');">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';

	htmlValue = htmlValue
			+ '<a href="javascript:void(0);" onclick="delSysAttr(this,';
	htmlValue = htmlValue + clientType;
	htmlValue = htmlValue + ",";
	htmlValue = htmlValue + appType;
	htmlValue = htmlValue + ');">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';

	return htmlValue;
}


