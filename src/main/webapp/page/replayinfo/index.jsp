<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/tag.jsp"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
			<meta name="description" content="">
				<meta name="author" content="">

					<title>评论管理</title>
</head>

<body>




	<div class="" id="content" style="">


		<div class=" row row-margin-top-70"></div>


		<div class="col-xs-12 row">
			<div class="panel panel-success pshodow" >
				<div class="panel-heading" title="点击显示/隐藏查询条件"
					data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
					aria-expanded="true">
					<div class="row">
						<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">评论管理 - 查询条件</h3>



						<span id="titlepic" data-toggle="collapse"
							data-parent="#accordion" href="#collapseOne"
							class="glyphicon glyphicon-chevron-up pull-right"
							aria-expanded="true"></span>
					</div>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in"
					aria-expanded="true" style="">
					<div class="panel-body">

						<div class="container">



  
							<div class=" col-md-6  col-xs-12  ">
								<div
									class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">回复内容:</div>
								<div class="col-md-6 col-xs-8 text-right ">
									<input
										class="form-control ng-pristine ng-untouched ng-valid ng-empty"
										id="q_content" type="text" name="q_content"
										placeholder="">
								</div>




							</div>
							 
							<div class=" col-md-6  col-xs-12  ">
								<div
									class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">用户ID:</div>
								<div class="col-md-6 col-xs-8 text-right ">
									<input
										class="form-control ng-pristine ng-untouched ng-valid ng-empty"
										id="q_userid" type="text" name="q_userid"
										placeholder="">
								</div>




							</div>
							  							<div class=" col-md-6  col-xs-12  ">
								<div
									class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">用户blog:</div>
								<div class="col-md-6 col-xs-8 text-right ">
									<input
										class="form-control ng-pristine ng-untouched ng-valid ng-empty"
										id="q_userBlog" type="text" name="q_userBlog"
										placeholder="">
								</div>




							</div>
							 							<div class=" col-md-6  col-xs-12  ">
								<div
									class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">用户Email:</div>
								<div class="col-md-6 col-xs-8 text-right ">
									<input
										class="form-control ng-pristine ng-untouched ng-valid ng-empty"
										id="q_email" type="text" name="q_email"
										placeholder="">
								</div>




							</div>
							 							<div class=" col-md-6  col-xs-12  ">
								<div
									class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">用户IP:</div>
								<div class="col-md-6 col-xs-8 text-right ">
									<input
										class="form-control ng-pristine ng-untouched ng-valid ng-empty"
										id="q_ip" type="text" name="q_ip"
										placeholder="">
								</div>




							</div>
							 

						</div>



					</div>
				</div>
			</div>

		</div>



		<div class="col-xs-12 row nopaddding">

			<div id="sdata" class=" col-xs-4  margin-bottom-10 padding-right-0 ">
			</div>
			<div
				class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
				<button type="button" onclick="query()"
					class="btn btn-primary btn-query btn-block   ">查询</button>


			</div>
			<div
				class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
				<button type="button" class="btn btn-primary btn-add btn-block"
					id="btnAdd_item">新增</button>


			</div>

		</div>




		<div class="col-xs-12 row nopaddding">

			<div class="table-responsive" style="margin: 1px;">
				<table id="table_list_item" class="table  table-hover table-striped"></table>
			</div>

		</div>




	</div>
	</div>


	<!-- 模态框（Modal） -->

	<jsp:include page="form.jsp"></jsp:include>

	<script
		src="${basePath}/page/replayinfo/replayinfo.js"></script>


	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.plugin.js"></script>



</body>
</html>