<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<meta name="fragment" content="!">
<meta name="keywords" content="KxのBook 个人站点  个人BLOG">
<meta name="description" content="首页- Kx的个人站点" />
<meta name="author" content="ZHANG JIE"> 

			<title>KxのBook,Welcome~欢迎访问Kx的个人站点</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
			 <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">
	<link rel="stylesheet" href="../../css/common.css">

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

			<!-- <script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script> -->

	<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
 <!-- <script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script> -->
	<script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>
<!-- 			
				 -->
			
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
		
  
	
		

		</head>
		
		
		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

					<%@include file="../phead.jsp"%>

				<div class=" row row-margin-top-70">

					<div class="  ">


						<div id="pgdiv" name="pgdiv" class="col-sm-9 col-xs-12 pleft rightline">

							<div ng-cloak>
								
									<%@include file="../list.jsp"%> 

							</div>


						</div>


						<div class="col-sm-3 col-xs-12">

							<%@include file="pright.jsp"%> 

							
						</div>


					</div>





					



					


				</div>

			</div>







			

<%@include file="../pfoot.jsp" %>

	



<!-- <script type="text/javascript" src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>

 -->

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	
	<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>

			<!-- <script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script> -->
		

<%--  <script type="text/javascript"
	src="<c:out value="${basePath}" />/js/plugin/ckeditor4.8/ckeditor.js"></script>
						 <script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script>
  --%>

						

				 		<!-- <script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script>  -->
						<script type="text/javascript" src="index.js"></script>
							<script type="text/javascript" src="../m_index.js"></script>
						<script type="text/javascript" src="../pright/pright_t_h.js"></script>


				


</body>
</html>