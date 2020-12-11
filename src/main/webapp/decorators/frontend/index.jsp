<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tag.jsp"%>


<html>
<head>


 <link rel="icon" type="image/x-icon" href="/kb/favicon.ico" /> 
<link rel="shortcut icon" type="image/x-icon" href="/kb/favicon.ico?11" />

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1">
<meta name="fragment" content="!">
<meta name="keywords" content="KxのBook,256kb,野生的喵喵,个人站点">
<meta name="description"
	content="KxのBook -256kb.cn | 野生的喵喵 的个人站点 | 分享工作及生活的点滴" />
<meta name="author" content="ZHANG JIE">
<title id="t1"><sitemesh:write property='title' /></title>
<title id="t2">KxのBook -256kb.cn | 野生的喵喵 的个人站点 | 分享工作及生活的点滴</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">



<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script>



<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular.min.js"></script>

<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular-sanitize.min.js"></script>

<script src="${basePath}/js/own/menu.js"></script>
<script src="${basePath}/js/own/loading.js"></script>

<link rel="stylesheet"
	href="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">

<link rel="stylesheet" href="${basePath}/js/own/kpro.css"></link>



<script type="text/javascript"
	src="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>

<sitemesh:write property='head' />


<link rel="stylesheet" href="${basePath}/css/kCommon.css">
<link rel="stylesheet" href="${basePath}/css/common.css">

<link rel="stylesheet" href="${basePath}/css/k2020.css">


</head>
<body class="k2020" id="ngSection" ng-app="myApp" ng-controller="eduCtrl">
	<script type="text/javascript" src="${basePath}/js/own/kpro.js"></script>


	<%@include file="../../public/phead.jsp"%>


	<div class="" id="content" style="">



		<div class=" row row-margin-top-70">

			<div class=" pall " style="min-height: 500px;">

				<div id="pgdiv" name="pgdiv"
					class="col-sm-9 col-xs-12 pleft rightline">
					<sitemesh:write property='body' />

				</div>

				<div class="col-sm-3 col-xs-12 pright earth">

					<%@include file="../../public/index/pright.jsp"%>


					<div class="row col-xs-12 ">
						<script type="text/javascript"
							src="//ra.revolvermaps.com/0/0/8.js?i=0hklog811es&amp;m=0&amp;c=ff0000&amp;cr1=ffffff&amp;f=arial&amp;l=33"
							async="async"></script>
					</div>

				</div>

			</div>

		</div>

		<%@include file="adialog.jsp"%>

		<%@include file="../../public/pfoot.jsp"%>
</body>

<script>

function otherScroll3(){
	  var scrollTop = document.documentElement.scrollTop + document.body.scrollTop;
	  var page=$(".earth").offset().top;
	  var height=$(".earth").height();
	  if(scrollTop>page+height+20){
		  //mobile, declare in other common js
		  if(browser&&!browser.mobile)
			  {
			  $(".panel.card").addClass('fixed2');
			 // $(".panel.siderabout").addClass('fixed');
			  }
		  
	  }
	  else{
		  $(".panel.card").removeClass('fixed2');
		 // $(".panel.siderabout").removeClass('fixed');
	  }
}
</script>


<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap/js/bootstrap.min.js"></script>




<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}/js/own/kvalidate.js"></script>




<!-- <script async
	src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
 -->

<div id='googleadcode_bottom' style='display: none'>


	<ins class="adsbygoogle bottom_detail_big" style="display: block"
		data-ad-client="ca-pub-4823081228661631" data-ad-slot="2310500588"
		data-ad-format="auto" data-full-width-responsive="true"></ins>
	<script>
		//(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
</div>


<div id='googleadcode_d_right' style='display: none'>


	<ins class="adsbygoogle" style="display: block"
		data-ad-client="ca-pub-4823081228661631" data-ad-slot="2310500588"
		data-ad-format="auto" data-full-width-responsive="true"></ins>
	<script>
		//(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
</div>

<div id='googleadcode3' style='display: none'>


	<ins class="adsbygoogle" style="display: block"
		data-ad-client="ca-pub-4823081228661631" data-ad-slot="2310500588"
		data-ad-format="auto" data-full-width-responsive="true"></ins>
	<script>
		//(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
</div>

<div id='googleadcode_link' style='display: none'>


	<ins class="adsbygoogle" style="display: block"
		data-ad-client="ca-pub-4823081228661631" data-ad-slot="2310500588"
		data-ad-format="auto" data-full-width-responsive="true"></ins>
	<script>
		//(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
</div>

<script language='javascript'>
function include_js(jspath,callback) {
    var _doc = document.getElementsByTagName('head')[0];
    var js = document.createElement('script');
    js.setAttribute('type', 'text/javascript');
    js.setAttribute('src', jspath);
    _doc.appendChild(js);
    if (document.all) {
        js.onreadystatechange = function() {
            if (js.readyState == 'loaded' || js.readyState == 'complete') {
            	if(typeof(callback)=='function')
            	callback();// alert('IE6、IE7 support js.onreadystatechange');
            }
        }
    } else {
        js.onload = function() {
            //alert('Firefox、chrome and others support js.onload');
        	if(typeof(callback)=='function')
            	callback();
        }
    }
}

$(document).ready(function(){
	
	var googleJs = "//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js";

	//loadads();
	return;
	include_js(googleJs);
	
	function loadads(){
		
		
		/*  (adsbygoogle = window.adsbygoogle || []).push({
			            google_ad_client: "ca-pub-4823081228661631",  
			             enable_page_level_ads: true 
			         
			         }); 
			return ;     */     

		
		
		if (document.all.item('googlead3') != null) {
			googlead3.innerHTML = googleadcode3.innerHTML;
		}

		if (document.all.item('googlead2') != null) {
			googlead2.innerHTML = googleadcode_d_right.innerHTML;
		}

		if (document.all.item('googlead') != null) {
			googlead.innerHTML = googleadcode_bottom.innerHTML;
		}

		if (document.all.item('googlead_link') != null) {
			googlead_link.innerHTML = googleadcode_link.innerHTML;
		}

		(adsbygoogle = window.adsbygoogle || []).push({});
		(adsbygoogle = window.adsbygoogle || []).push({});
		(adsbygoogle = window.adsbygoogle || []).push({});
		(adsbygoogle = window.adsbygoogle || []).push({});
	}

});

	
</script>


<!-- Global site tag (gtag.js) - Google Analytics -->
<!-- <script async src="https://www.googletagmanager.com/gtag/js?id=UA-126706596-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-126706596-1');
</script> -->

</html>



