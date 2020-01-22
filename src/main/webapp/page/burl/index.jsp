<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>
<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width,user-scalable=no, initial-scale=1">
		<title id="title">常用链接,KxのBook</title>
		<meta name="keywords" content="常用链接 KxのBook">
			<meta name="robots" content="noindex,nofollow">

				<meta name="description" content="常用链接- KxのBook Kx的个人站点" />
				<meta name="author" content="ZHANGJIE">
				
</head>

<body>


	<div class="" id="content" style="">




		<div class=" row row-margin-top-70">




			<div class="c ">


				<div class="">






					<div class="" id="srzt" style="min-height: 400px;">



						
							<div class="pull-left" style="margin-top:5px;">分类：</div>
						<div class="row col-lg-3 col-xs-6  margin-bottom-5 ">
					
						<select
						class=" col-xs-12 form-control txt-select-building in-line selectdist "
						id="q_type" name="q_type">

					</select> 
					</div>
					
					<div class="row col-xs-4 col-lg-3 margin-bottom-5 ">

							<button type="button" ng-click="addOrModify()"
								class="btn btn-info btn-block   ">新增链接</button>
						</div>

						<div class="row col-xs-12">
							<div ng-repeat="items in datalist" class="pgdiv" ng-cloak>
								<div class="panel panel-success urldv">
									<div class="panel-heading urlhead" title="" href="#collapseOne">
										<div class="row">
											<!-- <h3 class="panel-title col-xs-12 " id="{{$index}}"> -->
											<h3 class="panel-title col-xs-12 " id="{{items.name}}">
												{{items.name}} &nbsp;<span><a href="javascript:void(0);" class="urladd"
													ng-click="addOrModify(null,items.name)">新增本类链接</a></span>
											</h3>

										</div>
									</div>
									<div id="collapseOne" class="panel-collapse collapse in ">
										<div class="panel-body urlbd nopaddding">

											<div class="container">



												<div class="row  form-group margin-bottom-5">

													<div ng-repeat="x in items.val" class="pgdiv" ng-cloak>

														<div id="{{x.url_name}}" class="col-sm-3 col-xs-6  padding5">
															<div class='durl'>
																<div class="row">
																	<div class="col-sm-2 col-xs-3">
																		<img ng-if="x.icon!=null" style="max-width: 30px;"
																			class="pull-left img-responsive ulricon"
																			src="{{x.val2}}{{x.icon}}"></img>
																	</div>
																	<div class="col-sm-10 col-xs-9 padding5">

																		<a class="clear row title" ng-href={{x.url_val}}
																			target="_blank"><span title="{{x.url_name}}"
																			class='kutitle'>{{x.url_name}}</span></a>



																		<div class="row computerEdit">
																			<div class="pull-left  {{x.isshow|ftc}}">{{x.isshow|ft}}</div>
																			<div class="">
																				【<a href="javascript:void(0)" class="text-info"
																					ng-click="addOrModify(x)"><i title="修改" class="fa fa-edit"></i></a> <a
																					href="javascript:void(0)" class="text-info"
																					ng-click="rss(x)"><i title="订阅" class="fa fa-rss"></i></a>
<a
																					href="javascript:void(0)" class="text-info"
																					ng-click="img(x)"><i title="获取icon" class="fa fa-image text-success"></i></a>
<a
																					href="javascript:void(0)" class="text-warning"
																					ng-click="del(x)"><i title="删除" class="fa fa-trash  text-danger"></i></a>】
																			</div>

																		</div>

																	</div>
																	
																	<div class="row mobileEdit">
																			<div class="pull-left  {{x.isshow|ftc}}">{{x.isshow|ft}}</div>
																			<div class="">
																				【<a href="javascript:void(0)" class="text-info"
																					ng-click="addOrModify(x)"><i title="修改" class="fa fa-edit"></i></a> <a
																					href="javascript:void(0)" class="text-info"
																					ng-click="rss(x)"><i title="订阅" class="fa fa-rss"></i></a>
<a
																					href="javascript:void(0)" class="text-info"
																					ng-click="img(x)"><i title="获取icon" class="fa fa-image text-success"></i></a>
<a
																					href="javascript:void(0)" class="text-warning"
																					ng-click="del(x)"><i title="删除" class="fa fa-trash text-error"></i></a>】
																			</div>

																		</div>
																</div>



																<div class="udesc">{{x.desc_info}}</div>


															</div>
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>






					</div>


				</div>


				<div class="hide col-sm-3 col-xs-12">





					<div class=" panel panel-success">
						<div class="panel-heading" title="点击显示/隐藏查询条件"
							data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne2">
							<div class="row">
								<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">面板2</h3>



								<span id="titlepic" data-toggle="collapse"
									data-parent="#accordion" href="#collapseOne"
									class="glyphicon glyphicon-chevron-up pull-right "></span>
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











	<div class="modal fade" data-backdrop="static" id="myModal2"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">{{edit}}链接</h4>
				</div>



				<div class="modal-body container margin-top-10  ">
					<div class="row ">
						<form name="fm" id="fm"
							class="form-horizontal col-xs-12 col-lg-12 "
							style="min-width: 150px;">


							<input type="text " class="hide form-control " name="s_recordid"
								id="s_recordid" ng-model="s_recordid" placeholder=" ">


								<div class="form-group col-xs-12 row ">
									<div class="control-label padding-top-0 col-xs-4 ">URL分类：</div>
									<div class="col-xs-7 text-right ">
										<input required type="text" class="form-control "
											name="s_type" id="s_type" ng-model="s_type" placeholder=" ">
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
									<div class="control-label padding-top-0 col-xs-4 ">URL名称：</div>
									<div class="col-xs-7 text-right ">
										<input required type="text" class="form-control "
											name="s_dict_name" id="s_dict_name" ng-model="s_dict_name"
											placeholder=" ">
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
									<div class="control-label padding-top-0 col-xs-4 ">URL：</div>
									<div class="col-xs-7 text-right ">
										<input required disabled type="text" class="form-control "
											name="s_dict_key" id="s_dict_key" ng-model="s_dict_key"
											placeholder=" ">
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
									<div class="control-label padding-top-0 col-xs-4 ">排序：</div>
									<div class="col-xs-7 text-right ">


										<input required type="number" ng-pattern="/^\d*$/" min="1"
											max="1000" type="text" class="form-control" name="s_sort"
											id="s_sort" ng-model="s_sort" placeholder=" ">
									</div>
									<div class="col-xs-12 col-xs-offset-4 row ">
										<!--  <span ng-show="fm.s_pass.$error.required ">
							<span style="color:red " title="密码必须填写 ">
							*密码必须填写&nbsp;
							</span> 
							 </span> -->
									</div>
								</div>
								
								
							
						<div class="form-group col-xs-12 row  ">
							<div class="control-label padding-top-0 col-xs-4 ">可见：</div>
						
							<div class="col-xs-7 text-right ">
						
								<select class="form-control " id="isshow" >
									<option  value="1">可见</option>
									<option  value="0">不可见</option>
								</select>
							</div>
							
							
							
						</div>
								
								
								
								

								<div class="form-group col-xs-12 row ">
									<div class="control-label padding-top-0 col-xs-4 ">图标：</div>
									<div class="col-xs-5 text-right ">


										<input readonly="readonly" class=" form-control" type="text"
											name="oldname" id="oldname" ng-model="oldname"
											placeholder=" "> <span
											class="hide glyphicon glyphicon-remove form-control-feedback"
											aria-hidden="true"></span>
									</div>
									<div class="col-xs-2 text-right ">
										<button type="button" class="btn btn-primary"
											ng-click="selectfile()">选择</button>
									</div>

									<div class="col-xs-12 col-xs-offset-4 row margin-top-5">
										<img id="fullurl" ng-src="{{fullurl}}" class="col-xs-2"></img>
										<!--  <span ng-show="fm.s_pass.$error.required ">
							<span style="color:red " title="密码必须填写 ">
							*密码必须填写&nbsp;
							</span> 
							 </span> -->
									</div>
								</div>




								<div class="form-group col-xs-12 row ">
									<div class="control-label padding-top-0 col-xs-4 ">描述：</div>
									<div class="col-xs-7 text-right ">
										<input class="form-control" id="desc_info" name="desc_info"
											ng-model="desc_info" placeholder=" ">
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
										<select class="form-control " id="s_company" name="s_company"
											ng-model="s_company">
											<option ng-repeat="x in compays_select "
												value="{{x.accountid}} ">{{x.company_name}}</option>
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
					<button type="button " class="btn btn-info  "
						data-dismiss="modal">取消</button>
					<button type="button " class="btn btn-warning "
						ng-click="update() ">确定</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade " id="myModal1" tabindex="-1 "
		data-backdrop="static" role="dialog " aria-labelledby="myModalLabel ">
		<div class="modal-dialog " style="width: 350px;">
			<div class="modal-content ">
				<div class="modal-header ">
					<button type="button " class="close hide " data-dismiss="modal "
						aria-hidden="true ">&times;</button>
					<h4 class="modal-title " id="myModalLabel ">选择文件</h4>
				</div>

				<input type="hidden" id="fileSvrPath" ng-model="fileSvrPath" /> <input
					id="md5" name="md5" ng-model="md5" type="hidden" /> <input
					id="url" name="url" value="{{url}}" type="hidden" /> <input
					id="val2" value="{{val2}}" name="val2" type="hidden" />


				<iframe id="fileUploadFrame" name="fileUploadFrame" src=""
					frameborder="0" hspace="0" height="0" width="0"></iframe>




				<div class="modal-body container margin-top-10 ">

					<form method="post" id="fileform" name="fileform"
						target="fileUploadFrame" enctype="multipart/form-data">

						<div class="row col-xs-12">
							<div class="control-label padding-top-0  ">图标：</div>
						</div>
						<div class="row col-xs-11">

							<div class="col-xs-10 text-right ">
								<input required class=" form-control" type="file"
									name="fileUploadURL" id="fileUploadURL"
									ng-model="fileUploadURL" placeholder=" ">
							</div>
							<div class="col-xs-2 text-right ">
								<button type="button " class="btn btn-primary" name="uploadSub"
									id="uploadSub" ng-click="upload">上传</button>
							</div>

						</div>

						<div class="row col-xs-12 hide">
							<img
								src="<c:out value="${basePath}"/>/images/versionmanage/busy.gif"
								id="uploadState" />
						</div>

					</form>

				</div>

				<div class="modal-footer ">
					<button type="button " class="btn btn-default btn-warning "
						data-dismiss="modal">取消</button>
					<button type="button " class=" hide btn btn-primary ">
						确定</button>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade " id="myModal3" tabindex="-1 " role="dialog "
		aria-labelledby="myModalLabel " aria-hidden="true ">
		<div class="modal-dialog " style="width: 250px;">
			<div class="modal-content ">
				<div class="modal-header ">
					<button type="button " class="close " data-dismiss="modal"
						aria-hidden="true ">&times;</button>
					<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
				</div>



				<div class="modal-body container margin-top-10 ">
					<div class="row ">

						<p class="col-xs-10 ">确认执行操作吗？</p>

					</div>
				</div>
				<div class="modal-footer ">
					<button type="button " class="btn btn-default btn-warning "
						data-dismiss="modal ">取消</button>
					<button id="btnconfirm" type="button " class="btn btn-primary ">
						确定</button>
				</div>
			</div>
		</div>
	</div>



	<%@include file="../../public/pfoot.jsp"%>




	<%-- 
			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			


			<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>


			<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>
			
			<script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script>
			
			
<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

			

			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	

			
				<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>
 --%>


	<script type="text/javascript" src="index.js"></script>
	<script type="text/javascript" src="urlSelect2.js"></script>
	<script type="text/javascript" src="upload.js"></script>




</body>
</html>
