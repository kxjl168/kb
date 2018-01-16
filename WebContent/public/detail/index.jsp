<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title id="title">详细</title>
			<meta name="keywords" content="KxのBook">
<meta name="description" content="KxのBook 的个人BLOG" />
<meta name="author" content="Kx"> 
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
			<!-- <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">
 -->
			<link rel="stylesheet" href="../../css/kCommon.css">

			<!-- <link rel="stylesheet" href="../../css/zcfg.css"> -->
			<link rel="stylesheet" href="../../css/common.css">
<!-- 			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css"> -->

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

			<!-- <script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script> -->

	<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>
				
					
				<link rel="stylesheet" href="../../js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/default.css">
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>

		

		</head>

		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

			<%@include file="../phead.jsp"%>




				<div class=" row row-margin-top-70">

					






					


					<div class="c ">
					
					
						<div class="col-sm-9 col-xs-12 rightline pleft">
					
							<div ng-cloak>
								<div>
					
									<div class="row col-xs-12 ">
					
										<div class="nopadding-left">
											<img class="nopaddding img-responsive col-xs-2" style="width:25px; height:25px;" title="{{x.blog_type_name}}" src="{{x.blog_type_url}}">
											<div class="col-sm-8 col-xs-11 ptitle  ">{{x.title}}</div>
					
					
											<div class="col-sm-3 col-xs-12 text-right">
					
												<a ng-repeat="t in x.tagStrs" ng-click="showtgs(t)">{{t}},</a>
											</div>
										</div>
									</div>
					
									<div class="margin-top-10 row col-xs-12"></div>
									<div class="hide row col-xs-12">
										<hr></hr>
									</div>
					
									<div class="col-xs-12 row">
										<div class="">
											<div ng-cloak class=" pageText " ng-bind-html="x.context|sanitize">
											</div>
										</div>
					
					
									</div>
					
									<br>
									<div ng-cloak class="row col-xs-10 margin-top-5 ">post@{{x.create_date}}&nbsp;
										<span>阅读({{x.view_nums}})&nbsp;</span>
										<span>评论({{x.replay_nums}})&nbsp;</span>
									</div>
					
					
									<div class="row col-xs-12">
										<hr></hr>
									</div>
								</div>
					
					
								<div class="  col-xs-12 row tablefoot">
									<ul class="pagination pull-left">
										<li>
											<a href="#" ng-show="pre" ng-click="getList(pre.imei)">&laquo;上一篇</a>
												<a href="{{preurl}}/public/detail/{{pre.imei}}.html" style="display: none;" class="for spider" ></a>
										</li>
									</ul>
									<ul class="pagination pull-right">
										<li>
											<a href="#" ng-show="next" ng-click="getList(next.imei)">下一篇&raquo;</a>
											<a href="{{preurl}}/public/detail/{{next.imei}}.html" style="display: none;" class="for spider" ></a>
										</li>
									</ul>
					
					
					
									<ul class="hide pagination pull-right">
										<li>
											<a href="#" ng-click="getList(page-1)">&laquo;</a>
										</li>
										<li ng-repeat="x in pageDataPre">
					
											<a href="#" ng-click="getList(x)">{{x}}</a>
					
										</li>
										<li class="active">
											<a href="#" ng-click="getList(page)">{{page}}</a>
										</li>
										<li ng-repeat="x in pageDataAft">
					
											<a href="#" ng-click="getList(x)">{{x}}</a>
					
										</li>
										<li>
											<a href="#" ng-click="getList(page+1)">&raquo;</a>
										</li>
					
										<!-- li>
														<a href="#">&laquo;</a>
													</li>
													<li class="active">
														<a href="#">1</a>
													</li>
													<li class="disabled">
														<a href="#">2</a>
													</li>
													<li>
														<a href="#">3</a>
													</li>
													<li>
														<a href="#">&raquo;</a>
													</li> -->
									</ul>
					
									<select onchange="changerows(this)" class="hide pull-right">
										<option ng-repeat="x in rows_select">{{x}}</option>
									</select>
								</div>
					
							</div>
					
					
							
							
							<%@include file="comment.jsp" %>
					
					
					
					
						</div>
					
					
						<div class="col-sm-3 col-xs-12">
					
						 <%@include file="/public/pright/pright.jsp" %> 
	
					
					
							<div class=" panel panel-success">
								<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" href="#collapseOne2">
									<div class="row">
										<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">面板2</h3>
					
					
					
										<span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
									</div>
								</div>
								<div id="collapseOne2" class="panel-collapse collapse in">
									<div class="panel-body">
					
										<div class="container">
					
											<p>站位！！</p>
										</div>
					
					
					
									</div>
								</div>
							</div>
						</div>
					
					
					</div>
					
					
					
					
					
					
					
					
					
					
					
					
				</div>
					
			</div>







			



			



			<div class="modal fade " id="myModal3" tabindex="-1 " role="dialog " aria-labelledby="myModalLabel " aria-hidden="true ">
				<div class="modal-dialog " style="width: 250px; ">
					<div class="modal-content ">
						<div class="modal-header ">
							<button type="button " class="close " data-dismiss="modal" aria-hidden="true ">&times;</button>
							<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
						</div>



						<div class="modal-body container margin-top-10 ">
							<div class="row ">

								<p class="col-xs-10 ">确认执行操作吗？</p>

							</div>
						</div>
						<div class="modal-footer ">
							<button type="button " class="btn btn-default btn-warning " data-dismiss="modal ">取消
							</button>
							<button id="btnconfirm " type="button " class="btn btn-primary "> 确定 </button>
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


					


</body>
</html>