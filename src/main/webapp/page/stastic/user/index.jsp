<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>
<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width,user-scalable=no, initial-scale=1">
		<title>用户访问</title>

		<link rel="stylesheet"
			href="${basePath}/js/plugin/ztree/zTreeStyle.css">
</head>

<body>


	<div class="" id="content" style="">



		<div class=" row row-margin-top-70">

			<div class="menu hide">
				<div id="slides">
					<ul class="swiper-container" id="swiper_menu">
						<div class="swiper-wrapper"
							style="padding-top: 0px; padding-bottom: 0px; width: 320px; height: 50px; transform: translate3d(0px, 0px, 0px);">

						</div>
					</ul>
				</div>
			</div>






			<div class="col-xs-12">
				<div class="panel panel-success pshodow">
					<div class="panel-heading" title="点击显示/隐藏查询条件"
						data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne">
						<div class="row">
							<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">查询条件</h3>



							<span id="titlepic" data-toggle="collapse"
								data-parent="#accordion" href="#collapseOne"
								class="glyphicon glyphicon-chevron-up pull-right "></span>
						</div>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">

							<div class="container">

								<div class="row  form-group margin-bottom-5">
									<div class=" col-md-6  col-xs-12  ">
										<div
											class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4 ">时间维度：
											</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<select class="form-control " id="dateType"
												ng-model="dateType">
												<option ng-repeat="x in date_type2 " value="{{x.value}}">{{x.name}}</option>
											</select>
										</div>
									</div>

									<div class=" col-md-6  col-xs-12  ">
										<div
											class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4 ">用户ip：</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<input id="userid" name="userid" ng-model="userid"
												type="text" class=" form-control">
										</div>
									</div>
								</div>
								
								<div class="row  form-group margin-bottom-5">
									<div class=" col-md-6  col-xs-12  ">
										<div
											class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4 ">不显示我的访问：</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<input id="type_second" name="type_second"  checked="checked"
												type="checkbox" class="mcheck form-control">
										</div>
									</div>

									<div class=" col-md-6  col-xs-12  ">
										<div
											class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4 ">只显示详情页面访问：</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<input id="type_first" name="type_first"  checked="checked"
												type="checkbox" class="mcheck form-control">
										</div>
									</div>
								</div>
								

								<div class="row  form-group margin-bottom-5">
									<div class=" col-md-6  col-xs-12  ">
										<div
											class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4">开始时间：</div>
										<div class=" col-md-6  col-xs-8 text-right">
											<div class="input-group text-right  " style="">

												<input id="effectDate" readonly="readonly" name="effectDate"
													ng-model="effectDate" type="text" class=" form-control">
													<span class="input-group-addon "
													style="padding: 0px 10px 0px 10px;">
														<button id="effectDate_img" title="选择时间"
															class="glyphicon glyphicon-calendar   "></button>
												</span> </input>

											</div>
										</div>
									</div>

									<div class=" col-md-6  col-xs-12  ">
										<div
											class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4">结束时间：</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<div class="input-group text-right  " style="">

												<input id="effectDate2" readonly="readonly"
													name="effectDate2" ng-model="effectDate2" type="text"
													class=" form-control"> <span
													class="input-group-addon "
													style="padding: 0px 10px 0px 10px;">
														<button id="effectDate_img2" title="选择时间"
															class="glyphicon glyphicon-calendar   "></button>
												</span> </input>

											</div>
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
				<div
					class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
					<button type="button" onclick="query()"
						class="btn btn-primary btn-query btn-block   ">查询</button>
				</div>
			</div>




			<div class="col-xs-12 row ">
				<div class="table-responsive" style="margin: 10px;">
					<table id="table_detail" class=" "></table>
				</div>
			</div>






		</div>

	</div>





	<div class="modal fade" id="myModal_detail" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title " id="myModalLabel">用户详情</h4>
				</div>



				<div class="modal-body   container margin-top-10 ">
					<div class="row ">

						<div class="table-responsive" style="margin: 10px;">
							<table id="table_detail2" class="table-responsive table-hover"></table>
						</div>


					</div>

				</div>

				<div class="modal-footer">

					</button>
					<button type="button " class="btn btn-primary btn-warning "
						data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>







	<script type="text/javascript"
		src="${basePath}/js/plugin/swiper/idangerous.swiper.min.js"></script>


	<script type="text/javascript"
		src="${basePath}/js/plugin/ztree/jquery.ztree.all.min.js"></script>

	<script type="text/javascript"
		src="${basePath}/js/plugin/angular/angular-resource.min.js"></script>

	<script type="text/javascript"
		src="${basePath}/page/stastic/user/time.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/plugin/select2/select2.full.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/page/stastic/user/index.js"></script>




</body>
</html>