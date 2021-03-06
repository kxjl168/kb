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

					<title>Rss文章管理</title>
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
						<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">Rss文章管理 - 查询条件</h3>



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



							<div class="row  form-group margin-bottom-5">

								<div class=" col-md-6  col-xs-12  ">
									<div
										class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">文章标题:</div>
									<div class="col-md-6 col-xs-8  ">
										<input
											class="form-control ng-pristine ng-untouched ng-valid ng-empty"
											id="q_title" type="text" name="q_title"
											placeholder="">
									</div>




								</div>


								<div class=" col-md-6  col-xs-12  ">
									<div
										class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">订阅站点:</div>
									<div class="col-md-6 col-xs-8 text-right ">
												<select
						class=" col-xs-12 form-control txt-select-building in-line selectdist "
						id="q_rss_m_id" name="q_rss_m_id">
						</select>
									</div>




								</div>

							</div>
							
							<div class="row  form-group margin-bottom-5">

								<div class=" col-md-6  col-xs-12  ">
									<div
										class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">只显示未读:</div>
									<div class="col-md-6 col-xs-8 text-right ">
											<input id="isRead" name="isRead"  checked="checked"
												type="checkbox" class="mcheck form-control">
									</div>




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
				<button type="button" onclick="doSearch_item()"
					class="btn btn-primary btn-query btn-block   ">查询</button>


			</div>
			<div
				class="hide col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
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
		src="${basePath}/page/rsspagelist/rsspagelist.js"></script>

<script
		src="${basePath}/page/rsspagelist/rssSelect2.js"></script>
		
	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.plugin.js"></script>



</body>
</html>