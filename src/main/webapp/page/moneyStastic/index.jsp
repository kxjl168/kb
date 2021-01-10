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

					<title>收支管理</title>
</head>

<body>




	<div class="" id="content" style="">

<input type="hidden" id="httppath" value="${httppath }" /> 

		<div class=" row row-margin-top-70"></div>


		<div class="col-xs-12 row">
			<div class="panel panel-success pshodow" >
				<div class="panel-heading" title="点击显示/隐藏查询条件"
					data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
					aria-expanded="true">
					<div class="row">
						<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">收支统计</h3>



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
										class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">日期:</div>
									<div class="col-md-6 col-xs-8 text-right ">
										<input
											class="form-control ng-pristine ng-untouched ng-valid ng-empty"
											id="q_month" type="text" name="q_month"
											placeholder="">
									</div>




								</div>
								<div class=" col-md-6  col-xs-12   ">
									<div
										class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">包括房子:</div>
									<div class="col-md-6 col-xs-8 text-right ">
										<input id="q_house" name="q_house" type="checkbox"
											class="mcheck form-control">
									</div>




								</div>


								<div class=" col-md-6  col-xs-12  hide ">
									<div
										class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">年度查询:</div>
									<div class="col-md-6 col-xs-8 text-right ">
											<input id="q_year" name="q_year" checked="checked"
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
	
	</div>


		<div class="col-xs-12 row nopaddding">

			<div id="sdata" class=" col-xs-4  margin-bottom-10 padding-right-0 ">
			<div class='yearMoneyChart'><i class="detail fa fa-pie-chart"></i> <a class="" href="javascript:void(0)" onclick="showYearDetail()">年度详细</a></span></div>

			</div>
			
			<div
				class=" hide col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
				<button type="button" class=" btn btn-primary btn-add btn-block"
					id="btnAdd_item"><i class="fa fa-edit"></i>记一笔</button>


			</div>
<div
				class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
				<button type="button" onclick="doSearch_item()"
					class="btn btn-primary btn-query btn-block   "><i class="fa fa-refresh"></i>刷新</button>


			</div>
		</div>



		<div class="col-xs-12 row nopaddding">


			 <div id="pchart" style="min-height: 300px;"></div>

		</div>




	</div>
	</div>


	<!-- 模态框（Modal） -->

	<jsp:include page="form.jsp"></jsp:include>
 <script src="${basePath}/js/plugin/echart/echart4/echarts.min.js"></script>
 
		
	<script
		src="${basePath}/page/moneyStastic/money.js"></script>

		<script
		src="${basePath}/page/moneyStastic/chartMuti.js"></script>
		

	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.plugin.js"></script>



</body>
</html>