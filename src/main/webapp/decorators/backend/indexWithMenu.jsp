<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tag.jsp"%>


<html>
<head>


<link rel="bookmark" type="image/x-icon" href="/kb/favicon.ico" />
<link rel="shortcut icon" href="/kb/favicon.ico" />

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1">
<meta name="fragment" content="!">
<meta name="keywords" content="KxのBook,256kb,野生的喵喵,个人站点">
<meta name="description" content="KxのBook -256kb.cn | 野生的喵喵 的个人站点 | 分享工作及生活的点滴" />
<meta name="author" content="ZHANG JIE">
<title><sitemesh:write property='title' /></title>
<title>后台管理-KxのBook -256kb.cn | 野生的喵喵 的个人站点</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">



<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script>



<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular.min.js"></script>

<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular-sanitize.min.js"></script>

<script src="${basePath}/js/own/menu.js"></script>
<script src="${basePath}/js/own/loading.js"></script>

<link rel="stylesheet"
	href="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">

<link  rel="stylesheet"
	href="${basePath}/js/own/kpro.css"></link>
	
		<link rel="stylesheet"
	href="${basePath}/js/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
	
<link rel="stylesheet"
	href="${basePath}/js/plugin/bootstrap-datepicker/css/bootstrap-datepicker.min.css">
	
<%-- 
<link rel="stylesheet" href="${basePath }/js/plugin/bootstrap-table/css/bootstrap-table.min.css"></script>
 --%>
	<script type="text/javascript"
		src="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
	
	
	
		<link rel="stylesheet"
	href="${basePath}/js/plugin/select2/css/select2.min.css">
	<link rel="stylesheet"
	href="${basePath}/js/plugin/select2/css/select2.bootstrap.css">
	
	
	<script src="${basePath}/js/plugin/tippyjs/popper.min.js"></script>
	<script src="${basePath}/js/plugin/tippyjs/tippy.js"></script>
	
	
	
<sitemesh:write property='head' />


<link rel="stylesheet" href="${basePath}/css/kCommon.css">
<link rel="stylesheet" href="${basePath}/css/common.css">
<link rel="stylesheet" href="${basePath}/css/k2020.css">


</head>
<body  class="k2020"  id="ngSection" ng-app="myApp" class="zz" ng-controller="eduCtrl" >
 <script type="text/javascript" src="${basePath}/js/own/kpro.js"></script>
	

	<%@include file="../../page/phead.jsp"%>
	   		 							 

	<div class="bodyDiv" id="content" style="">



		<div class=" row col-xs-12 ">

		<div class=" pbackall  " style="min-height: 500px;">


	<div 
				class=" panel-left col-sm-2 col-menu-left  ">
				<%@include file="left_navi.jsp"%>

			</div>


			<div id="pgdiv" name="pgdiv"
				class=" main-content col-sm-10 col-xs-12  ">
				<sitemesh:write property='body' />

			</div>

			

		</div>

<%@include file="../frontend/adialog.jsp"%>

	
</body>


<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap/js/bootstrap.min.js"></script>




<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}/js/own/kvalidate.js"></script>

	<script type="text/javascript" src="${basePath }/js/plugin/bootstrap-table/js/bootstrap-table.min.js"></script>
				<script type="text/javascript" src="${basePath }/js/plugin/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
				
				<script type="text/javascript" src="${basePath }/js/plugin/bootstrapValidator/js/bootstrapValidator.js"></script>
<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>



<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>



	<script type="text/javascript"
	src="${basePath}/js/plugin/select2/js/select2.min.js"></script>

	

				
<script>
				var modal_counter = 0;
$(document).ready(function () {
        $('.modal').on('shown.bs.modal', function () {
            modal_counter++;
        });
        $('.modal').on('hidden.bs.modal', function () {
            modal_counter--;
            if(modal_counter){
                $('body').addClass('modal-open');
            }
            else{
                $('body').removeClass('modal-open');
            }
        });
})

		
		
		</script>

</html>



