<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

    <%@include file="/common/tag.jsp"%>

        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
            <meta name="fragment" content="!">
            <title id="title">详细</title>
            <meta name="keywords" content="KxのBook">
            <meta name="description" content="Kx 的个人站点" />
            <meta name="author" content="ZHANG JIE">
            <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
  
            <link rel="stylesheet" href="../../css/kCommon.css">
<link rel="stylesheet" href="../../css/common.css"> 
            
            
        
            <script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

            <script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

         
            <script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
            <script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>
            <script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>


            <link rel="stylesheet" href="../../js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/default.css">
            <link rel="stylesheet" href="<c:out value='${basePath}'/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">

            <script type="text/javascript" src="<c:out value='${basePath}'/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>



        </head>

        <body id="ngSection" style="height: 100%;"  ng-app="myApp" ng-controller="eduCtrl">


 			<div id="d1" onclick="msg($(window).scrollTop()+'/'+$('html').scrollTop());" style="border: 1px solid red;height: 1500px">111</div>
 			
 		

        </body>


                <!-- <script type="text/javascript" src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>
 -->


                <script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
                <script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
                <script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


                <script type="text/javascript" src="../../js/plugin/jquery/jquery.validate.js"></script>

                <script type="text/javascript" src="${basePath}/js/own/kvalidate.js"></script>


                <!-- <script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script> -->
                <script src="../../js/own/menu.js"></script>
                <script src="../../js/own/loading.js"></script>

                <%-- 
<script type="text/javascript"
	src="<c:out value="${basePath}" />/js/plugin/ckeditor4.8/ckeditor.js"></script>
						<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script>

 --%>

                    <!-- <script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script> -->
                    <script type="text/javascript" src="index.js"></script>
                    <script type="text/javascript" src="replay.js"></script>
                    <script type="text/javascript" src="../pright/pright_t_h.js"></script>





        </html>