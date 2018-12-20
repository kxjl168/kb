<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>概览</title>
		<!-- 	<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css"> -->

  
  <style >
/*宽度全屏*/
 .modal-dialog{position:absolute;width:auto;margin:2.5vh auto;left:0;right:0;}

  </style>
 <link rel="stylesheet" type="text/css" href="${basePath}/js/plugin/select2/css/select2.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/js/plugin/select2/css/select2.bootstrap.css">
		</head>

		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

				

				<div class=" row row-margin-top-70">

					<div class="menu hide">
						<div id="slides">
							<ul class="swiper-container" id="swiper_menu">
								<div class="swiper-wrapper" style="padding-top: 0px; padding-bottom: 0px; width: 320px; height: 50px; transform: translate3d(0px, 0px, 0px);">

								</div>
							</ul>
						</div>
					</div>






					<div class="col-xs-12">
						<div class="panel panel-success">
							<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								<div class="row">
									<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">查询条件</h3>



									<span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse ">
								<div class="panel-body">

									<div class="container">



										<div class="row  form-group margin-bottom-5">


											<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">文章标题：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="q_name" ng-model="q_name" placeholder="">

												</div>
											</div>
											
											<div class=" hide col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">文章类型：</div>
												<div class="col-md-6 col-xs-8 text-right ">
						<select class="form-control " id="q_type" ng-model="q_type">
									<option  ng-repeat="x in dicts " value="{{x.dict_key}}">{{x.dict_name}}</option>
								</select>
												</div>
											</div>



										</div>
										<div class=" row  form-group margin-bottom-5">
											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">Tags：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="q_tags" ng-model="q_tags" placeholder="">

												</div>
											</div>

											
											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">关键字：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="q_keys" ng-model="q_keys" placeholder="">

												</div>
											</div>
										</div>





									</div>



								</div>
							</div>
						</div>
					</div>



	<div class="col-xs-12 row ">

				<div id="sdata" class=" col-xs-4  margin-bottom-10 padding-right-0 ">
				</div>
				<div class=" col-lg-2 col-md-2 col-xs-2 pull-right margin-bottom-10 padding-right-0 ">
										<button type="button" ng-click="addOrModify()" class="btn btn-primary btn-block   ">新增文章</button>
									</div>
									<div class=" col-lg-2 col-md-2 col-xs-2 pull-right margin-bottom-10 padding-right-0 ">
										<button type="button" ng-click="getList()" class="btn btn-primary btn-block   ">查询</button>
									</div>
			</div>
			
			<div class="col-xs-12 row ">
				<div class="table-responsive" style="margin: 10px;">
					<table id="table_detail" class=" "></table>
				</div>
			</div>

					



					


				</div>

			</div>







		



			<div class="modal fade active" id="myModal2" data-backdrop="static"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog " >
					<div class="modal-content ">
						<div class="modal-header nopadding row">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">{{edit}}文章</h4>
						</div>



						<div class="modal-body  nopadding row">
				<div class="row  col-xs-12 nopadding"  >
					<form name="fm" id="fm" class="form-horizontal " >
				

					<input   type="text " class="hide form-control " name="s_recordid" id="s_recordid" ng-model="s_recordid" placeholder=" ">
					
					
					<div class="form-group   ">
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
					
				</div>
				<div class="modal-footer row nopadding">
					<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
					</button>
					                 <button type="button " class="btn btn-primary " ng-click="update() "> 确定 </button>
				</div>
			</div>
		</div>
	</div>



<div class="modal fade " id="myModal3" data-backdrop="static"  tabindex="-1 " role="dialog " aria-labelledby="myModalLabel " aria-hidden="true ">
		<div class="modal-dialog " style="width: 250px; ">
			<div class="modal-content ">
				<div class="modal-header ">
					<button type="button " class="close " data-dismiss="modal" aria-hidden="true ">&times;</button>
					<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
				</div>



				<div class="modal-body container margin-top-10 " ">
							<div class="row ">

								<p class="col-xs-10">确认执行操作吗？</p>

							</div>
						</div>
						<div class="modal-footer ">
							<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
							</button>
							<button id="btnconfirm" type="button " class="btn btn-primary "> 确定 </button>
						</div>
					</div>
				</div>
			</div>

			</script>

			<%-- <script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

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
<script src="../../js/own/loading.js"></script>
 
 
	

			<script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script> --%>
			
				<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>		
			<script type="text/javascript" src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>
			<script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script>
			
		<%-- 	 <script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/ckeditor.js"></script> 
	<script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script> 
		
			 --%>
		 <script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.11/ckeditor.js"></script> 
	<script type="text/javascript"
	src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.11/adapters/jquery.js"></script> 
	
	
		
			
			<script type="text/javascript" src="index.js"></script>


			<script>
				
		
		
		</script>


</body >
</html >