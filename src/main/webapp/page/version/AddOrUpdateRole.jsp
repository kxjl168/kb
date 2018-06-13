<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 ransitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tag.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>角色管理</title>

<script type="text/javascript">

</script>

<style type="text/css">
</style>

<!-- dialog子窗体使用 -->
<script type="text/javascript"
	src="<c:out value="${basePath}"/>/jsp/privilege/role_action.js"></script>


<link href="<c:out value="${basePath}"/>/css/dept/mystyle.css" type="text/css"
	rel="stylesheet" />
<link href="<c:out value="${basePath}"/>/js/zTree/css/demo.css" type="text/css"
	rel="stylesheet" />
<link href="<c:out value="${basePath}"/>/js/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet" />
<link href="<c:out value="${basePath}"/>/css/themes/icon.css" rel="stylesheet" />

<script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/zTree/js/jquery.ztree.exedit-3.5.js"></script>



</head>

<body class="frameBd">

	<script type="text/javascript">
	
	
	
	
	</script>


	<div class="contentMain">


		<div id="divTitle"></div>


		<p class="currentPosition">首页&nbsp;&nbsp;&gt;&nbsp;&nbsp;系统管理&nbsp;&nbsp;&gt;&nbsp;&nbsp;角色管理&nbsp;&nbsp;&gt;&nbsp;&nbsp;{{label}}角色</p>

		<div class="tbldiv">
			<p>
					<input type="button" class="xbutton save btnHeader" value="返回" onclick="javascript:history.go(-1)" />
			</p>
			<div class="contentMiddle">
				<div class="contentTitle">
					<table class="theadTbl">
						<thead>
							<tr>
								<td width="100%"
									style="padding-left: 30px; text-align: left; font-size: 16px;">{{label}}角色</td>
							</tr>
						</thead>
					</table>
				</div>

				<div>
					<form id="fm">
						<div id="bodyContent">
							<div class="contentNew">
							
							<div id='addtip' class='tip'>小提示:当添加的角色已存在时，会直接更新该角色信息</div>
							
								<table id="tb_data" border="0" rules="none" width="100%">
									<!-- 			<td class="searchTitle" colspan="4"> -->
									<!-- 				<span >应用信息</span> -->
									<!-- 		</td> -->


									<%-- <tr>
										<td style="width: 25%;">Banner名称:</td>

										<td style="width: 25%;"><input type="text"
											class="easyui-validatebox inputTxt"
											data-options="required:true,validType:'maxLength[30]'"
											id="banner_name" name="banner_name" value="${Banner.banner_name}" />
											<span class="must">*</span></td>
									</tr> --%>
									
									<tr>
										<td style="width: 25%;">角色ID:</td>

										<td ><input type="text"
											class="easyui-validatebox inputTxt"
											data-options="required:true,validType:'maxLength[20]'"
											id="role_id" name="role_id" value="<c:out value="${bean.role_en}"/>" />
											<span class="must">*</span></td>
									</tr>
									
										<tr>
										<td style="width: 25%;">角色名称:</td>

										<td ><input type="text"
											class="easyui-validatebox inputTxt"
											data-options="required:true,validType:'maxLength[20]'"
											id="role_zh" name="role_id" value="<c:out value="${bean.role_zh}"/>" />
											<span class="must">*</span></td>
									</tr>
									
									<tr>
										<td colspan="4">描述:(100字符以内)<span class="must">*</span></td>
									</tr>
									<tr>
										<td colspan="4" style="width: 100%"><textarea
												id="desc" class="inputArea" name="desc" cols="80"
												rows="5" style="width: 80%; height: 70px"><c:out value="${bean.role_desc}"/></textarea>
										</td>
									</tr>
									
	<tr>
										<td style="width: 25%;vertical-align: top;">可见菜单项:</td>

										<td id="td_menu">	
										
										 <span style="margin: 5px;"> 
                 <input id="selectAll"  name="app" type="checkbox"
              
                  attr="" value="" class="r_hide">
                 <span style=";margin-top:5px; vertical-align: middle;" class="WellCheckBox WellCheckBoxH"></span>
			     <label  style="margin-left:5px; margin-right:5px;" class="WellCheckname ">全选</label>
										
										<div id="treeDemo" style="width: 90%; height: 80%" class="ztree"></div>	
		</td>
									</tr>
									
									
								
									


								</table>


								<table id='sysAttributeTab' class='sysAttributeTab'
									cellpadding="0" cellspacing="1"
									style="width: 90%; background: #f0f0f0;">

								</table>

								<table border="0" rules="none" width="100%"
									style="margin-top: 25px;">
									<tr id="actionRow">
										<td colspan="4" style="text-align: left;" class="btn"><input
											id="btnOk" type="button" style="margin-left: 60px;"
											class="xbutton save" onclick="{{OkFunction}}();" value="确定" /> <input
											class="xbutton cancel" type="button" value="取消"
											onclick="isCannel();" /></td>
									</tr>
								</table>

								<br>

							</div>
						</div>
					</form>

					<div id="winDept"></div>


				</div>
			</div>
		</div>
	</div>
	<%-- <script src="${webctx }/js/common/childfunction.js"></script> --%>

</body>
</html>
