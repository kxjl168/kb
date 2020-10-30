<%@ page language="java" contentType="text/html; charset=UTF-8"%>



<div class="row kfootpanel">


	<div class="col-xs-12 row kfoot  ">



		<ul class="nopaddding">
			<li><a href="http://www.256kb.cn"><i class="fa fa-home"></i>&nbsp;首页</a></li>

			<li><a href="http://www.256kb.cn/public/search/"><i class="glink fa fa-google"></i>&nbsp;Google搜索</a></li>
			<!-- <li><a href="http://www.256kb.cn/public/wuliu/"><i class="fa fa-truck"></i>&nbsp;快递查询</a></li> -->
			<li><a href="http://kg.256kb.cn/s/search/"><i class="fa fa-link"></i>&nbsp;个站搜索</a></li>
			<li><a href="http://kg.256kb.cn/s/url/"><i class="fa fa-link"></i>&nbsp;常用链接</a></li>
			<li><a href="http://www.256kb.cn/rss"><i class="fa fa-rss"></i>&nbsp;订阅RSS</a></li>
			<li><a href="http://www.256kb.cn/public/about/"><i class="fa fa-info-circle"></i>&nbsp;关于</a></li>



		</ul>

	</div>
</div>


<script>
$(function(){
	$("#right_boot").html("KxのBook@Copyright 2017-"+new Date().getFullYear()+" All Rights Reserved");
})
</script>


<div class="col-xs-12 row kfootend">
	<span id="right_boot">KxのBook@Copyright 2017- All Rights Reserved</span> <br> <span>Designed
		and themed by <a href="https://256kb.cn/">野生的喵喵</a>
	</span> &nbsp;<span><span
		class=" " title="总访问量"><span class=" glyphicon glyphicon-eye-open"></span>&nbsp;<span class="pagevisit">${visitdata.pageVisitNum }</span></span>
		&nbsp;<span class=" " title="访客数"><i class=" fa fa-user"></i>&nbsp;<span class="uservisit">${visitdata.userVisitNum }</span></span>
	</span>

</div>


<script type="text/javascript" src="${basePath}/js/own/sprite.js?a=1"></script>



