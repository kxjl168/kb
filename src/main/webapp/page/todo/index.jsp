<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>

<title>todo-256kb.cn | 野生的喵喵
	的个人站点</title>

<meta name="keywords" content="GOOGLE 搜索代理转发 KxのBook">
	<meta name="description" content="GOOGLE搜索 代理转发- KxのBook Kx的个人站点" />
	
	<%-- 
	<link  rel="stylesheet" href="${basePath }/js/plugin/bootstrap-table/css/bootstrap-table.min.css"></link>
	 --%>
</head>

<body>


	<div class="c ">


		<div class="col-sm-12 col-xs-12 nopadding pleft">



			<div class="panel panel-top panel-success">
				<div class="panel-heading" title="" data-toggle="collapse"
					data-parent="#accordion" href="#collapseOne">
					<div class="row">
						<h3 class="panel-title col-xs-10 ">增加代办事项</h3>



						<span id="titlepic" data-toggle="collapse"
							data-parent="#accordion" href="#collapseOne"
							class="hide glyphicon glyphicon-chevron-up pull-right "></span>
					</div>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in ">
					<div class="panel-body">

						<div class="container">



							<div class="row  form-group margin-bottom-5">





								<div
									class=" col-sm-8  col-xs-12 nopaddding row mar margin-bottom-5">
									<div
										class="control-label nopaddding padding-top-0 col-xs-2 col-sm-2  ">ToDO：</div>
									<div class="  col-sm-9 col-xs-10 text-right ">
										<input id="t_title" placeholder="待办事项" name="t_title"
											ng-model="t_title" type="text" class=" form-control">
									</div>
								</div>

								<div
									class=" col-sm-4  col-xs-12 nopaddding row mar margin-bottom-5">

									<div
										class="control-label nopaddding padding-top-0 col-xs-2 col-sm-3  "
										style="lline-height: 30px;">
										<span class="hide glyphicon  glyphicon-arrow-left  " title="后退"
											ng-click="back();">
											
											  
											
											</span>
									</div>
									<div class="  col-sm-5 col-xs-12 text-right ">
										<button type="button" ng-click="doupdate()"
											class="btn btn-primary btn-block   ">记录</button>
									</div>



								</div>
							</div>
						</div>

					</div>
				</div>
			</div>


<div class="col-xs-12 col-sm-2 row margin-bottom-5">
<select id="is_done" class="form-control" >
                                       <option value="">全部事项</option>  
					<option value="1">已完成</option> 
					<option value="0"  selected="selected">待办</option>
			</select>
</div>
			<div class="col-xs-12 row" style="min-height: 400px;">
			 <table id="todolist" class="table-responsive"></table>
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


	<script type="text/javascript" src="${basePath }/js/plugin/bootstrap-table/js/bootstrap-table.min.js"></script>
				<script type="text/javascript" src="${basePath }/js/plugin/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>

	<script type="text/javascript" src="index.js"></script>



</body>
</html>