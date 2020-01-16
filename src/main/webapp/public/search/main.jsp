<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>

<title>GOOGLE搜索,google镜像,google反向代理,翻墙,google-256kb.cn | 野生的喵喵
	的个人站点</title>

<meta name="keywords" content="GOOGLE 搜索代理转发 KxのBook">
	<meta name="description" content="GOOGLE搜索 代理转发- KxのBook Kx的个人站点" />
</head>

<body>


	<div class="c ">



		<div class="col-sm-12 col-xs-12 nopadding pleft">



			<div class="panel panel-top panel-success">
				<div class="panel-heading" title="" data-parent="#accordion"
					href="#collapseOne">
					<div class="row">
						<h3 class="panel-title col-xs-10 ">搜索 （搜索结果请右键新窗口打开查看）</h3>



						<span id="titlepic" data-toggle="collapse"
							data-parent="#accordion" href="#collapseOne" class=" "></span>
					</div>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in ">
					<div class="panel-body">

						<div class="container">
							<div class="row  form-group margin-bottom-10">
								<div class="text-warning row">
									<div class="col-xs-6 col-sm-2 nopaddding   ">本站已关闭访客搜索<br>请输入授权码:</div>
									<div class="col-xs-6 col-sm-2">
										<input id="gkey" name="gkey" class=" form-control"
											type="text">
									</div>
									<div class="col-xs-12 col-sm-8 nopaddding padding-top-5   ">
										没有授权码?-->请猛击<a class="text-success" href="javascript:void(0);"
											onclick="showApply()";>这里</a>
									</div>
								</div>
							</div>


							<div class="row  form-group margin-bottom-5">





								<div
									class=" col-sm-8  col-xs-12 nopaddding row mar margin-bottom-5">
									<div
										class="control-label nopaddding padding-top-0 col-xs-4 col-sm-3  ">GOOGLE：</div>
									<div class="  col-sm-9 col-xs-8 text-right ">
										<input id="kwd" value="${kwd }" placeholder="Google is cool!"
											name="kwd" ng-model="kwd" type="text" class=" form-control">
									</div>
								</div>

								<div
									class=" col-sm-4  col-xs-12 nopaddding row mar margin-bottom-5">

									<div
										class="control-label nopaddding padding-top-0 col-xs-2 col-sm-3  "
										style="lline-height: 30px;">
										<span class="glyphicon  glyphicon-arrow-left  " title="后退"
											ng-click="back();"></span> <span
											class="glyphicon  glyphicon-arrow-left  " title="后退"
											ng-click="back();"></span>
									</div>
									<div class="  col-sm-5 col-xs-10 text-right ">
										<button type="button" ng-click="search()"
											class="btn btn-info btn-block   ">搜索</button>
									</div>



								</div>
							</div>
						</div>

					</div>
				</div>
			</div>


			<div class="col-xs-12" id="srzt" style="min-height: 400px;"></div>


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
					<button id="btnconfirm " type="button " class="btn btn-primary ">
						确定</button>
				</div>
			</div>
		</div>
	</div>




	<!-- 模态框（Modal） -->
	<div class="modal fade" data-backdrop="static" id="myModalApply"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>


					<h4 class="modal-title" id="myModal_itemLabel">
						<span id="myModal_item_title">申请</span> google搜索授权码
					</h4>

				</div>

				<form id="mformApply" name="mformApply" class="form-horizontal"
					role="form" action="" method="post">

					<div class="modal-body">
						<div class="row">

							<div class="col-lg-12">


								<div class="form-group">
									<label for="name" class="col-lg-3 control-label">您的称呼:</label>

									<div class="col-lg-9">
										<input type="text" name="nickname" class="form-control"
											id="nickname" placeholder="称呼">
											<p class="help-block"></p>
									</div>
								</div>

								<div class="form-group">
									<label for="name" class="col-lg-3 control-label">您的邮箱:</label>

									<div class="col-lg-9">
										<input type="text" name="email" class="form-control"
											id="email" placeholder="邮箱">
											<p class="help-block"></p>
									</div>
								</div>


								<div class="form-group">
									<label for="name" class="col-lg-3 control-label">申请说明:</label>

									<div class="col-lg-9">
										<input type="text" name="context" class="form-control"
											id="context" placeholder="申请说明">
											<p class="help-block"></p>
									</div>
								</div>






							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-warning" id="btnApplyitem">
							确定</button>
					</div>
				</form>


			</div>
		</div>
	</div>




	<script type="text/javascript" src="index.js"></script>
</body>
</html>