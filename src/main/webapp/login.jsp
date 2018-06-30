<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

	<%@include file="/common/tag.jsp"%>


		<html>

		<head>

			<title>管理后台</title>

	
			<script type="text/javascript" src="${basePath}/js/own/login.js"></script>
			<!-- <script type="text/javascript" src="${basePath}/js/code.js"></script> -->
			<script type="text/javascript" src="${basePath}/js/own/loading.js"></script>

			<script type="text/javascript" src="${basePath}/js/plugin/unlock/unlock.js"></script>
			<link rel="stylesheet" href="${basePath}/js/plugin/unlock/unlock.css">


		</head>

		<body>


			<div class="container login_back" id="content">


				<nav class="hide navbar navbar-default navbar-fixed-top  navbar-inverse" role="navigation">
					<div class="navbar-header">
						<button type="button" id="menuBtn" class="navbar-toggle" data-toggle="collapse" data-target="#menuItem">
							<span class="sr-only"></span>
							<span class="hide glyphicon glyphicon-search   "></span>
							<span class=" hide icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">登录</a>
					</div>
					<div class="collapse navbar-collapse" id="menuItem"></div>
				</nav>


				<div class=" col-xs-12 column row row-margin-top-70">


<!-- 					<div class="col-xs-4 col-md-3 col-lg-3 col-xs-offset-2  col-sm-offset-3 col-md-offset-4 text-center row-margin-top-70"> -->

		<div class="col-xs-offset-3 col-xs-6  col-sm-offset-4 col-sm-4 col-lg-offset-4 col-lg-3    text-center row-margin-top-70">


						<form class="form-horizontal" style="min-width: 180px;">
							<div class="form-group">

								<!-- 											<label class=" col-md-3 control-label">用户：</label> -->
								<div class="input-group col-xs-12  ">
									<span class=" input-group-addon   " title="用户">
										<i class="glyphicon glyphicon-user"></i>
									</span>
									<input type="text" name="username" id="username" class="form-control" placeholder="用户">
								</div>

							</div>

							<div class="form-group">

								<!-- 											<label class=" col-md-3 control-label">密码：</label> -->
								<div class="input-group col-xs-12 ">
									<span class=" input-group-addon  ">
										<i class=" glyphicon glyphicon-lock"></i>
									</span>
									<input type="password" name="password" id="password" class="form-control" placeholder="密码">
								</div>

							</div>




							<div class=" form-group hide">

								<div class=" ">
									<!-- 														<label class="control-label col-xs-4 ">验证码：</label> -->


									<div class="col-xs-8" style="padding-left: 0px;">
										<input class="form-control   radius" type="text" ng-model="valicode" placeholder="请输入验证码" name="title" class="code" id="code"
										 name="code">
									</div>


									<div class=" input-group col-xs-3 pull-right   ">
										<span style="color: #fff; float: left; display: block; border: 1px solid #6699FF; background-color: #6699FF; text-align: center; font-size: 17px; width: 60px; height: 32px; line-height: 32px;"
										 class="code" onclick="createCode();" id="checkCode"> </span>
									</div>
								</div>


							</div>


							<div class=" hide form-group form-inline">

								<div class="">

									<div class="input-group  col-xs-5 ">
										<div class="checkbox">
											<label class="">
												<input type="checkbox">记住密码 </label>
										</div>
									</div>
									<div class="input-group  control-label col-xs-5   ">

										<a class="" href="#">忘记密码</a>

									</div>
								</div>



							</div>






							<div class="form-group ">
								<div id="errorMsg" class="input-group error col-xs-12 pull-left text-left h2"></div>
							</div>

							<div class="  form-group form-inline">
								<div class=" row  nopaddding col-xs-12">
									<div class="slidernc bar1 bar"></div>
								</div>
							</div>


							<div class="form-group row-margin-top-10 form-inline  hide">
								<!-- <div class="col-xs-offset-1 col-xs-10"> -->
								<div class="input-group col-xs-offset-0 col-xs-12 pull-left">
									<button type="button" onclick="checkLogin();" class="btn btn-primary btn-block  ">登录</button>
								</div>





								<div class=" hide input-group  col-xs-4  col-lg-4  ">

									<a class=" control-label " href="reg.jsp">
										<span class="unline">注册</span>
									</a>

								</div>
							</div>

						</form>



					</div>


				</div>

			</div>



			<!--底部 str-->
			<div style="width: 100%; height: 48px; background: #e6edf0; position: relative; z-index: 999999; text-align: center; padding-top: 12px; border-top: 1px solid #fff; position: fixed; bottom: 0;">

				<p style="margin-top: 4px; color: #333;">
					@Copyright 2017</p>
			</div>
			
			<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap/js/bootstrap.min.js"></script>

			
			

			<script type="text/javascript" src="${basePath}/js/plugin/angular/angular.min.js"></script>




			<script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="${basePath}/js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="${basePath}/js/plugin/jquery/noty.themes.bootstrap.js"></script>


			<script src="${basePath}/js/own/loading.js"></script>


			<script type="text/javascript">


				$('.bar1').slideToUnlock({
					bgColor: '#1e776d',
					progressColor: '#00675c',
					handleColor: '#00675c',
					//textColor: '#bf8d30',
						textColor: '#fff',
					succColor: '#00675c',
					succTextColor: '#bf8d30',
					succHanlderColor: '#bf8d30',
					text: '滑动方块登录',
           	 		succText: '验证成功!',
					successFunc: function (cb) {
						checkLogin(cb);
					}

				});


			</script>

		</body>

		</html>