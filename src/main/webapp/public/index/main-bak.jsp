<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
		 <link rel="bookmark" type="image/x-icon" href="/favicon.ico" /> 
<link rel="shortcut icon" href="/favicon.ico" />
		
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<meta name="fragment" content="!">
<meta name="keywords" content="KxのBook 个人站点  个人BLOG">
<meta name="description" content="首页- Kx的个人站点" />
<meta name="author" content="ZHANG JIE"> 

			<title>KxのBook -256kb.cn | 野生的喵喵 的个人站点  </title>
			<link rel="stylesheet" type="text/css" media="screen" href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">
			 <link rel="stylesheet" type="text/css" media="screen" href="${basePath}/js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="${basePath}/css/kCommon.css">
	<link rel="stylesheet" href="${basePath}/css/common.css">

			<script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="${basePath}/js/plugin/bootstrap/js/bootstrap.min.js"></script>

		
	<script type="text/javascript" src="${basePath}/js/plugin/angular/angular.min.js"></script>
 
	<script type="text/javascript" src="${basePath}/js/plugin/angular/angular-sanitize.min.js"></script>

			
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
		
  
	
		

		</head>
		
		
		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">




 			 	<%@include file="../phead.jsp"%> 
 		
			<div class="" id="content" style="">

				

				<div  class="center row row-margin-top-70">

					<div class="  "  style="min-height: 500px;">


						<div id="pgdiv" name="pgdiv" class="col-sm-9 col-xs-12 pleft rightline">

						<div ng-cloak>
								
									<%@include file="../list.jsp"%> 

							</div> 


						</div>


						<div  id="pright" class="col-sm-3 col-xs-12">

							<%@include file="pright.jsp"%> 

							
						</div>


					</div>





					



					


				</div>

			</div>







			

 <%@include file="../pfoot.jsp" %> 

	


<script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery-ui.js"></script>
						

			<script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="${basePath}/js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="${basePath}/js/plugin/jquery/noty.themes.bootstrap.js"></script>


			    <script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	
	<script src="${basePath}/js/own/menu.js"></script>
<script src="${basePath}/js/own/loading.js"></script>

		
							
				 		<!-- <script type="text/javascript" src="${basePath}/js/plugin/select2/select2.full.min.js"></script>  -->
						<script type="text/javascript" src="${basePath}/public/index/index.js"></script>
							<script type="text/javascript" src="${basePath}/public/m_index.js"></script>
						<script type="text/javascript" src="${basePath}/public/pright/pright_t_h.js"></script>


				


</body>
</html>