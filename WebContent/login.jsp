<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/tag.jsp"%>


<html>
<head>

<title>管理后台</title>

<meta name="viewport"
	content="width=device-width, initial-sclae=1.0, maximun-scale=1.0, minimum-scale=1.0, user0scalable=yes">
<meta name="description" content="登录-欢迎访问-Kx的个人站点" />
<meta name="author" content="ZHANG JIE"> 
<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" href="${basePath}/css/kCommon.css">

		<link rel="stylesheet" href="${basePath}/css/zcfg.css">
			<link rel="stylesheet" href="${basePath}/css/common.css">
				<link rel="stylesheet"
					href="${basePath}/css/swiper_zcfg.css">
<script type="text/javascript" src="${basePath}/js/login.js"></script>
<script type="text/javascript" src="${basePath}/js/code.js"></script>
<script type="text/javascript" src="${basePath}/js/loading.js"></script>


<script type="text/javascript" src="${basePath}/js/plugin/unlock/S2Unlock.js"></script>
	<link rel="stylesheet" href="${basePath}/js/plugin/unlock/S2Unlock.css">
	
	<script type="text/javascript" src="${basePath}/js/plugin/nc/nc.js"></script>
	<link rel="stylesheet" href="${basePath}/js/plugin/nc/nc1.css">
		<%-- <link rel="stylesheet" href="${basePath}/js/plugin/nc/nc.css"> --%>

</head>
<body>


	<div class="container login_back" id="content">
	

		<nav class="navbar navbar-default navbar-fixed-top  navbar-inverse"
			role="navigation">
		<div class="navbar-header">
			<button type="button" id="menuBtn" class="navbar-toggle"
				data-toggle="collapse" data-target="#menuItem">
				<span class="sr-only"></span><span
					class="hide glyphicon glyphicon-search   "></span><span class=" hide icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">登录</a>
		</div>
		<div class="collapse navbar-collapse" id="menuItem"></div>
		</nav>


		<div class=" col-xs-12 column row row-margin-top-70">


			<div
				class="col-xs-4 col-md-3 col-lg-3 col-xs-offset-2  col-sm-offset-3 col-md-offset-4 text-center row-margin-top-70">


				<form class="form-horizontal" style="min-width: 180px;">
					<div class="form-group">

						<!-- 											<label class=" col-md-3 control-label">用户：</label> -->
						<div class="input-group col-xs-12  ">
							<span class=" input-group-addon   " title="用户"><i
								class="glyphicon glyphicon-user"></i></span><input type="text"
								name="username" id="username" class="form-control"
								placeholder="用户">
						</div>

					</div>

					<div class="form-group">

						<!-- 											<label class=" col-md-3 control-label">密码：</label> -->
						<div class="input-group col-xs-12 ">
							<span class=" input-group-addon  "><i
								class=" glyphicon glyphicon-lock"></i></span><input type="password"
								name="password" id="password" class="form-control"
								placeholder="密码">
						</div>

					</div>
					
				


					<div class=" form-group ">

						<div class=" ">
							<!-- 														<label class="control-label col-xs-4 ">验证码：</label> -->


							<div class="col-xs-8" style="padding-left: 0px;">
								<input class="form-control   radius" type="text"
									ng-model="valicode" placeholder="请输入验证码" name="title"
									class="code" id="code" name="code">
							</div>


							<div class=" input-group col-xs-3 pull-right   ">
								<span
									style="color: #fff; float: left; display: block; border: 1px solid #6699FF; background-color: #6699FF; text-align: center; font-size: 17px; width: 60px; height: 32px; line-height: 32px;"
									class="code" onclick="createCode();" id="checkCode"> </span>
							</div>
						</div>
						
					
					</div>


					<div class=" hide form-group form-inline">

						<div class="">

							<div class="input-group  col-xs-5 ">
								<div class="checkbox">
									<label class=""> <input type="checkbox">记住密码 </label>
								</div>
							</div>
							<div class="input-group  control-label col-xs-5   ">

								<a class="" href="#">忘记密码</a>

							</div>
						</div>



					</div>

					<div class="form-group">
	<div id="errorMsg" class="input-group error col-xs-12 pull-left text-left h2"></div>
</div>
					<div class="form-group row-margin-top-10 form-inline ">
						<!-- <div class="col-xs-offset-1 col-xs-10"> -->
						<div class="input-group col-xs-offset-0 col-xs-12 pull-left">
							<button type="button" onclick="checkLogin();"
								class="btn btn-primary btn-block  ">登录</button>
								
								
								
							
								
						
						</div>
						
						<div class="col-xs-12 row">
							<div class="unlock-wrap ul1">
							<div id="nc_1__bg" class="nc_bg" style="width: 0px;"></div>
			<div class="slide-btn"></div>
			<p class="hint">slide to unlock</p>
		</div>	
						</div>
						
						<div class="col-xs-12 row">
					<div id="dom_id" style="margin-bottom: 20px;" class="nc-container" data-nc-idx="1">
<div id="nc_1_wrapper" class="nc_wrapper">
<div id="nc_1_n1t" class="nc_scale">
<div id="nc_1__bg" class="nc_bg"></div>
<span id="nc_1_n1z" class="nc_iconfont btn_slide"></span>
<div id="nc_1__scale_text" class="scale_text slidetounlock"><span class="nc-lang-cnt" data-nc-lang="_startTEXT">请按住滑块，拖动到最右边</span></div>
<div id="nc_1_clickCaptcha" class="clickCaptcha">
<div class="clickCaptcha_text">
<b id="nc_1__captcha_text" class="nc_captch_text"></b>
<i id="nc_1__btn_2" class="nc_iconfont nc_btn_2 btn_refresh"></i>
</div>
<div class="clickCaptcha_img"></div>
<div class="clickCaptcha_btn"></div>
</div>
<div id="nc_1_imgCaptcha" class="imgCaptcha">
<div class="imgCaptcha_text"><input id="nc_1_captcha_input" maxlength="6" type="text" style="ime-mode:disabled"></div>
<div class="imgCaptcha_img" id="nc_1__imgCaptcha_img"></div>
<i id="nc_1__btn_1" class="nc_iconfont nc_btn_1 btn_refresh" onclick="document.getElementById('nc_1__imgCaptcha_img').children[0].click()"></i>
<div class="imgCaptcha_btn">
<div id="nc_1__captcha_img_text" class="nc_captcha_img_text"></div>
<div id="nc_1_scale_submit" class="nc_scale_submit"></div>
</div>
</div>
<div id="nc_1_cc" class="nc-cc"></div>
<i id="nc_1__voicebtn" tabindex="0" role="button" class="nc_voicebtn nc_iconfont" style="display:none"></i>
<b id="nc_1__helpbtn" class="nc_helpbtn"><span class="nc-lang-cnt" data-nc-lang="_learning">了解新功能</span></b>
</div>
<div id="nc_1__voice" class="nc_voice"></div>
</div>
</div>
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
	<div
		style="width: 100%; height: 48px; background: #e6edf0; position: relative; z-index: 999999; text-align: center; padding-top: 12px; border-top: 1px solid #fff; position: fixed; bottom: 0;">
		
		<p style="margin-top: 4px; color: #333;">
			@Copyright 2017</p>
	</div>


	<!-- 	<script type="text/javascript" -->
	<%-- 		src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script> --%>


	<script type="text/javascript"
		src="${basePath}/js/plugin/angular/angular.min.js"></script>




	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.noty.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/noty.layout.center.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/noty.themes.bootstrap.js"></script>


	<script src="${basePath}/js/loading.js"></script>


<script type="text/javascript">
	
	
		var s2u1 = new S2Unlock('.ul1', {
			slideButton: '.slide-btn',
			slideImgSrc: basePath+'/images/arrow.png',
		
		wrapImgSrc: basePath+'/images/bg.png',
			callback:function(){
			msg("done");
			},
		
		});
		
	
		
		
		
		var nc = new noCaptcha();
	var nc_appkey = 'FFFF00000000016E5A77';  // 应用标识,不可更改
	var nc_scene = 'message';  //场景,不可更改
	var nc_token = [nc_appkey, (new Date()).getTime(), Math.random()].join(':');
	var nc_option = {
		renderTo: '#dom_id',//渲染到该DOM ID指定的Div位置
		appkey: nc_appkey,
	    scene: nc_scene,
		//token: nc_token,
		callback: function (data) {// 校验成功回调
			//$('#a_session').val(data.csessionid);
			//$('#a_sig').val(data.sig);
			//$('#a_token').val(nc_token);
			//$('#a_scene').val(nc_scene);
			//$('#a_from').val(3);
		//	if(ncSuccessCb && typeof(ncSuccessCb) == 'function'){
			//	ncSuccessCb()
			//}
			msg("22");
		}
	};
	nc.init(nc_option);	
	
	
	
		var s2u1 = new S2Unlock('.nc-container', {
			slideButton: '.btn_slide',
			//slideImgSrc: basePath+'/images/arrow.png',
			//wrapImgSrc: basePath+'/images/bg.png',
			callback:function(){
			msg("done");
			},
		
		});
</script>

</body>
</html>
