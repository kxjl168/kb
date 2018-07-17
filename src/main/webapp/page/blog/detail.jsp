<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>修改</title>
		<!-- 	<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css"> -->

   <link rel="stylesheet" href="${basePath}/js/own/FileUploadMuti.css">

 
  <style >
/*宽度全屏*/
/* .modal-dialog{position:absolute;width:auto;margin:2.5vh auto;left:0;right:0;} */

  </style>

		</head>

		<body >


			<div class="" id="content" style="">

					<%@include file="../phead.jsp"%>




<div class="row  col-xs-12 nopadding row-margin-top-70"  >
					<form name="fm" id="fm" class="form-horizontal " style="margin:20px!important;">
				

					<input   type="text " class="hide form-control " name="s_recordid" id="s_recordid" ng-model="s_recordid" placeholder=" ">
					
					
					<div class="form-group   " style="margin-top:50px;" >
							<div class="control-label padding-top-0 col-sm-3 col-xs-12 ">标题：</div>
							<div class="col-sm-9 col-xs-12 text-right ">
							 <input required  type="text" class="form-control " name="s_title" id="s_title" ng-model="s_title" placeholder=" ">
							 </div>
							 <div class="col-xs-12 col-xs-offset-4 row ">
						
							</div>
						</div>
						
						<div class="form-group   ">
						
						<div class=" nopadding">
							 <div class="control-label padding-top-0 col-sm-3 col-xs-12 ">文章类型：</div>
							<div class="col-sm-9 col-xs-12 text-right ">
							<select class="form-control " id="s_type" ng-model="s_type">
								<option  ng-repeat="x in dicts " value="{{x.dict_key}}">{{x.dict_name}}</option>
								</select>
							 </div>
							 <div class="col-xs-12 col-xs-offset-4 row ">
							
							</div>
						</div>
						
						</div>
					<div class="form-group   ">
						
						<div class=" nopadding">
						   	<div class="control-label padding-top-0 col-sm-3 col-xs-12  ">是否可见：</div>
							<div class="col-sm-9 col-xs-12  text-right ">
							<select class="form-control " id="en_type" ng-model="en_type">
								<option  ng-repeat="x in enables " value="{{x.value}}">{{x.desc}}</option>
								</select>
							 </div>
							 <div class="col-xs-12 col-xs-offset-4 row ">
					
							</div>
						</div>
						
							
						</div>
						
						<input type="hidden" id="httppath" value="${httppath } ">
						<div id="upimgs" class="hide"></div>
					
					
					<div class="form-group   ">
							<div class="control-label padding-top-0 col-sm-3 col-xs-12 ">文章标签：</div>
							<div class="col-sm-9 col-xs-12 text-right ">
						 <input required  type="text" class="form-control " name="s_tags" id="s_tags" ng-model="s_tags" placeholder=" ">
							</div>
							 <div class="col-xs-12 col-xs-offset-4 row ">
					
							</div>
						</div>

						<div class=" form-group   ">
								<div class="control-label padding-top-0 col-sm-3 col-xs-12 ">内容：</div>
								<div class="col-sm-9 col-xs-12 text-right ">
								 <textarea   id="s_context" name= "s_context" ng-model="s_context"  placeholder=" ">{{s_context}}</textarea>
							</div>
							 <div class="col-xs-12 col-xs-offset-4 row ">
							
							</div>
							</div>
					


						
					

					
					</form>
					</div>
		
		
				<div class="modal-footer row nopadding">
					<button type="button " class="btn btn-default btn-warning " ng-click="cancel()">取消
					</button>
					                 <button type="button " class="btn btn-primary " ng-click="update() "> 确定 </button>
				</div>
	



				

			</div>







		



		
		

<%-- 
			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

	<script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script>


			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

		


			<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>

<script type="text/javascript" src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>



			<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	

			<script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script>
			<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script> --%>

<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>		
			<script type="text/javascript" src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>
			<script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script>
			
			 <script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/ckeditor.js"></script> 
	<script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script> 


	<script type="text/javascript"
	src="${basePath}/js/own/FileUploadMuti.js"></script>


			<script type="text/javascript" src="detail.js"></script>


			


</body >
</html >