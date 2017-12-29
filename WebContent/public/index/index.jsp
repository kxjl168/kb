<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>概览</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css">

			<script type="text/javascript " src="../../js/plugin/jquery/jquery.v1.11.3.js "></script>

			<script type="text/javascript " src="../../js/plugin/bootstrap/js/bootstrap.min.js "></script>

			<script type="text/javascript " src="../../js/plugin/jquery/jquery-ui.js "></script>

	<script type="text/javascript " src="../../js/plugin/angular/angular.min.js "></script>
			<script type="text/javascript " src="../../js/plugin/angular/angular-resource.min.js "></script>
				<script type="text/javascript " src="../../js/plugin/angular/angular-sanitize.min.js "></script>
			
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>

	
		

		</head>

		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

					<%@include file="../phead.jsp"%>




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
						<div class="hide panel panel-success">
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
														<option ng-repeat="x in dicts " value="{{x.dict_key}}">{{x.dict_name}}</option>
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


					<div class="col-xs-12  ">


						<div id="pgdiv" name="pgdiv" class="col-xs-9 pleft rightline">

							<div ng-cloak>
								<div ng-repeat="x in datalist" class="pgdiv">

									<div class="row col-xs-12 ">

										<div class="nopadding-left">
											<img class="nopaddding img-responsive col-xs-2" style="width:25px; height:25px;" title="{{x.blog_type_name}}"   src="{{x.blog_type_url}}">
											<div class="col-xs-8  ptitle  "><a ng-click="detail(x)">{{x.title}}</a></div>


											<div class="col-xs-3 text-right">
											
											<a ng-repeat="t in x.tagStrs" ng-click=showtag(t)>{{t}},</a>
											</div>
										</div>
									</div>

									<div class="margin-top-10 row col-xs-12"></div>
									<div class="hide row col-xs-12">
										<hr></hr>
									</div>


									<div class="col-xs-12 row">
										<div class="alldot">
											<div ng-cloak class=" pageText " ng-bind-html="x.context">
											</div>
										</div>
											<div>
												
												<a href="#" class="detailbtn h5 text-right pull-right text-info margin-right-20" ng-click="detail(x)">详情</a>

											</div>
									
									</div>
									<br>
									<div ng-cloak class="row col-xs-10 margin-top-5 ">post@{{x.create_date}}&nbsp;
										<span ><a ng-click="detail(x)">阅读</a>({{x.view_nums}})&nbsp;</span>
										<span ><a ng-click="detail(x,'rpdiv')">评论</a>({{x.replay_nums}})&nbsp;</span>
									</div>


									<div class="row col-xs-12">
										<hr></hr>
									</div>
								</div>


<div class="  col-xs-12 row tablefoot" ng-show="total">


						<ul class="pagination pull-right">
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


						</div>


						<div class="col-xs-3">

							<%@include file="pright.jsp"%> 

							
						</div>


					</div>





					



					


				</div>

			</div>







			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">条件选择</h4>
						</div>



						<div class="modal-body">
							<div class="row" ng-show="selType==1">
								<div class="form-group col-xs-12">
									<div class="control-label col-xs-4">选择位置</div>
									<div class="col-xs-8 text-right">
										<select class="form-control" onchange="" ng-model="cpTypes" id="cpType" name="cpType">
										</select>
									</div>
								</div>
							</div>
							<div class="row" ng-show="selType==2">
								<div class="form-group col-xs-12">
									<div class="control-label col-xs-4">是否公开</div>
									<div class="col-xs-8 text-center">
										<div class="info_value">
											<input type="radio" value="1" onclick="selectPublic(1)" name="IsPublic" ng-model="cp.IsPublic" ng-checked="cp.IsPublic=='1'">是
											<input type="radio" value="0" onclick="selectPublic(0)" name="IsPublic" ng-model="cp.IsPublic" ng-checked="cp.IsPublic=='0'">否
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="row" ng-show="selType==3">
                    <div class="form-group col-xs-12">
                        <div class="control-label col-xs-4">部门</div>
                        <div class="col-xs-8 text-center">
                            <div class="info_value">
                                <div id="menuContent" class="menuContent">
                                    <ul id="depTree" class="ztree" style="margin-top:0; width:160px;"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-warning" data-dismiss="modal">取消
							</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal"> 确定 </button>
						</div>
					</div>
				</div>
			</div>



			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog ">
					<div class="modal-content " style="width:600px;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">{{edit}}文章</h4>
						</div>



						<div class="modal-body container margin-top-10  ">
							<div class="row ">
								<form name="fm" id="fm" class="form-horizontal col-xs-12 col-lg-12 " style="min-width: 150px; ">


									<input type="text " class="hide form-control " name="s_recordid" id="s_recordid" ng-model="s_recordid" placeholder=" ">


									<div class="form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-4 ">标题：</div>
										<div class="col-xs-7 text-right ">
											<input required type="text" class="form-control " name="s_title" id="s_title" ng-model="s_title" placeholder=" ">
										</div>
										<div class="col-xs-12 col-xs-offset-4 row ">
											<!--  <span ng-show="fm.s_account.$error.required ">
							<span style="color:red " title="用户ID必须填写 ">
							*用户ID必须填写&nbsp;
							</span> 
							 </span> -->
										</div>
									</div>

									<div class="form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-4 ">文章类型：</div>
										<div class="col-xs-7 text-right ">
											<select class="form-control " id="s_type" ng-model="s_type">
												<option ng-repeat="x in dicts " value="{{x.dict_key}}">{{x.dict_name}}</option>
											</select>
										</div>
										<div class="col-xs-12 col-xs-offset-4 row ">
											<!--  <span ng-show="fm.s_account.$error.required ">
							<span style="color:red " title="用户ID必须填写 ">
							*用户ID必须填写&nbsp;
							</span> 
							 </span> -->
										</div>
									</div>


									<div class="form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-4 ">文章标签：</div>
										<div class="col-xs-7 text-right ">
											<input required type="text" class="form-control " name="s_tags" id="s_tags" ng-model="s_tags" placeholder=" ">
										</div>
										<div class="col-xs-12 col-xs-offset-4 row ">
											<!--  <span ng-show="fm.s_pass.$error.required ">
							<span style="color:red " title="密码必须填写 ">
							*密码必须填写&nbsp;
							</span> 
							 </span> -->
										</div>
									</div>

									<div class=" form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-4 ">内容：</div>
										<div class="col-xs-7 text-right ">
											<textarea class="form-control" id="s_context" name="s_context" ng-model="s_context" placeholder=" "></textarea>
										</div>
										<div class="col-xs-12 col-xs-offset-4 row ">
											<!--  <span ng-show="fm.s_ip_refresh.$error.required ">
							<span style="color:red " title="刷新周期必须填写 ">
							<span class="glyphicon glyphicon-remove "></span>
							</span> 
							 </span> -->

											<!--   <span ng-show=" fm.s_ip_refresh.$invalid ">
							<span style="color:red " title="刷新周期为大于9的数字 ">
								*刷新周期为1-1000的数字&nbsp;
							</span> 
							 </span> -->
										</div>
									</div>



									<div class="form-group col-xs-12 row  hide">
										<div class="control-label padding-top-0 col-xs-4 ">选择位置：</div>

										<div class="col-xs-7 text-right ">

											<select class="form-control " id="s_city" ng-model="s_city">
												<option ng-repeat="x in citys_select ">{{x}}</option>
											</select>
										</div>



									</div>

									<div class="form-group col-xs-12 row  hide">
										<div class="control-label padding-top-0 col-xs-4 ">所属公司：</div>
										<div class="col-xs-7 text-right ">
											<select class="form-control " id="s_company" name="s_company" ng-model="s_company">
												<option ng-repeat="x in compays_select " value="{{x.accountid}} ">{{x.company_name}}</option>
											</select>
										</div>

										<div class="col-xs-12 col-xs-offset-4 row hide">
											<!--  <span ng-show="fm.s_company.$error.required ">
							<span style="color:red " title="公司必选填写 ">
							*公司必选填写&nbsp;
							</span> 
							 </span> -->
										</div>

									</div>



								</form>
							</div>

						</div>
						<div class="modal-footer ">
							<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
							</button>
							<button type="button " class="btn btn-primary " ng-click="update() "> 确定 </button>
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



						<div class="modal-body container margin-top-10 " ">
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

	


<script type="text/javascript " src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js "></script>



			<script type="text/javascript " src="../../js/plugin/jquery/jquery.noty.min.js "></script>
			<script type="text/javascript " src="../../js/plugin/jquery/noty.layout.center.js "></script>
			<script type="text/javascript " src="../../js/plugin/jquery/noty.themes.bootstrap.js "></script>


			    <script type="text/javascript "
	src="../../js/plugin/jquery/jquery.validate.js "></script> 

		<script type="text/javascript "
	src="${basePath}/js/kvalidate.js "></script>
	

			<script type="text/javascript " src="../../js/plugin/swiper/idangerous.swiper.min.js "></script>
			<script src="../../js/menu.js "></script>



<script type="text/javascript "
	src="<c:out value="${basePath}" />/js/plugin/ckeditor4.5.11/ckeditor.js"></script>
						<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.5.11/adapters/jquery.js"></script>


						<script src="../../js/loading.js "></script>

						<script type="text/javascript " src="../../js/plugin/select2/select2.full.min.js "></script>
						<script type="text/javascript " src="index.js "></script>
						<script type="text/javascript " src="../pright/pright_t_h.js "></script>


						<script>
							$(function () {
								$('#myModal').modal({
									keyboard: true
								})
							});


</body >
</html >