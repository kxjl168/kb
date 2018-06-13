
$(function(){
	$("#uploadState").hide();
	$("#uploadSub").bind("click", function(e) {
		uploadfile();
	});
	
	/*$("#closeDiv").bind("click", function(e) {
		topWin[0].divdlg.close();
	});*/
	

});

/**
 * 上传文件
 */
var sh = null;
function uploadfile(){
	
	//error('up');
	
	var fileName = $("#fileUploadURL").val();
	if (fileName != null && "" != fileName) {
		var fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
		if ("exe" == fileExtension
				|| "EXE" == fileExtension
				|| "zip" == fileExtension
				|| "ZIP" == fileExtension || "apk" == fileExtension.toLowerCase()|| "ipa" == fileExtension.toLowerCase()) {
			
			fileform.action=basePath+"/UploadFile.action";
			fileform.submit();
			
			 ajaxLoading();
			 
			 
			 
			 
			 
			
		//	$("#uploadSub").hide(); //隐藏上传文件按钮
		//	$("#uploadState").show(); //显示上传文件后面的图片
		//	$("#closeDiv").hide(); //隐藏关闭按钮
			
			if(sh==null){
				sh=setInterval(lookupUploadUrl,500);
			}
		}else{
			ajaxLoadEnd();
			error("请上传ipa,zip,apk格式的文件！");
			 //ajaxLoadEnd();
			//$("#uploadState").hide();
		}
	}else{
		ajaxLoadEnd();
		error("请选择上传文件！");
		//$("#uploadState").hide();
	}
}
//获得上传文件后返回的数据
function lookupUploadUrl() {
	var obj = document.getElementById("fileUploadFrame").contentWindow;
	var uploadUrl = null;
	var fileMd5 = null;
	if(obj.document.getElementById("uploadUrl")!=null)
		uploadUrl = obj.document.getElementById("uploadUrl").value;
	
	if(obj.document.getElementById("returnMsg")!=null)
		returnMsg = obj.document.getElementById("returnMsg").value;
	
	
	if(uploadUrl != null && uploadUrl != ""){
		if(uploadUrl == '202') {
			error("请选择上传文件！");
		} else if(uploadUrl == '203') {
			error("文件上传失败！");
		} else if(uploadUrl == '204') {
			error("请选择要上传的文件！");
		} else if(uploadUrl =='205') {
		//	error("文件上传失败，文件大小超过30M！");
			//alert("文件上传失败，文件大小超过30M！");
			error(returnMsg);
		} else if(uploadUrl == '206'){
			error("文件已经存在！");
		} else{
			$("#URL").val(uploadUrl);
			if(obj.document.getElementById("fileMd5")!=null)
				fileMd5 = obj.document.getElementById("fileMd5").value;
			//$("#md5").val(fileMd5);
			//$("#newFileName").val(obj.document.getElementById("newFileName").value);
			
			
			$("#md5").val(fileMd5);
			$("#oldname").val(obj.document.getElementById("oldName").value);
			$("#url").val(uploadUrl);
			
			//重新验证
			kvalidate.validate("#fm");
			
			//obj.document.getElementById("uploadUrl").value = "";
			//obj.document.getElementById("fileMd5").value = "";
			//obj.document.getElementById("newFileName").value="";
			//$("#bodyContent").show();
			//$("#uploadFileDiv").hide();
			
			$("#myModal1").modal("hide");
			
			msg("文件上传成功！");
		}
		
		clearInterval(sh);
		sh = null;
		ajaxLoadEnd();
		
	//	topWin[0].divdlg.close();
		
		//$("#uploadSub").show(); //显示上传文件按钮
		//$("#uploadState").hide(); //隐藏上传文件后面的图片
		//$("#closeDiv").show(); //显示关闭按钮
		return;
	}
}
