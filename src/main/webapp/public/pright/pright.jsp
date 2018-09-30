<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="rblock">
	<div class=" panel panel-success card">
		<div class="panel-heading" title="" data-toggle="collapse"
			data-parent="#accordion" href="#collapseOner11">
			<div class="row">
				<h3 class="panel-title  col-xs-10">文章分类</h3>



				<span id="titlepic" class="hide" data-toggle=""
					data-parent="#accordion" href="#collapseOner11"
					class="glyphicon glyphicon-chevron-up pull-right "></span>
			</div>
		</div>
		<div id="collapseOner11" class="panel-collapse collapse in ">
			<div class="panel-body">

				<div id="tpdiv" class="container">

					<!-- <div ng-cloak ng-repeat="x in tplist">
						<div class="row">
							<a ng-href="{{preurl}}/public/index/bt/{{x.blog_type}}.html">
								<img class="nopaddding img-responsive col-xs-2"
								style="width: 20px; height: 20px;" title="{{x.blog_type_name}}"
								ng-src="{{x.blog_type_url}}"> {{x.blog_type_name}}&nbsp; <span>({{x.view_nums}})</span>
							</a> <a ng-href="{{preurl}}/public/index/bt/{{x.blog_type}}.html"
								style="display: none;" style="" class="for spider">
								{{x.blog_type_name}}</a>
						</div>
					</div> -->
					
						<c:forEach items="${tplist }" var="x">
					<div  >
						<div class="row">
							<a href="${preurl}/public/index/bt/${x.blog_type}.html">
								<img class="nopaddding img-responsive col-xs-2"
								style="width: 20px; height: 20px;" title="${x.blog_type_name}"
								src="${x.blog_type_url}"> ${x.blog_type_name}&nbsp; <span>(${x.view_nums})</span>
							</a>
								${x.blog_type_name}</a>
						</div>
					</div>
					</c:forEach>
					
				</div>



			</div>
		</div>
	</div>

	<div class=" panel panel-success">
		<div class="panel-heading" title="" data-toggle="collapse"
			data-parent="#accordion" href="#collapseOner2">
			<div class="row">
				<h3 class="panel-title col-xs-10">文章归档</h3>



				<span id="titlepic" class="hide" data-toggle=""
					data-parent="#accordion" href="#collapseOner2"
					class="glyphicon glyphicon-chevron-up pull-right "></span>
			</div>
		</div>
		<div id="collapseOner2" class="panel-collapse collapse in ">
			<div class="panel-body">

				<div id="mdiv" class="container">

				<!-- 	<div ng-cloak ng-repeat="x in hlist">
						<div class="row">
							<a ng-href="{{preurl}}/public/index/h/{{x.month}}.html">
								{{x.month}}&nbsp;<span>({{x.view_nums}})</span>
							</a> <a ng-href="{{preurl}}/public/index/h/{{x.month}}.html"
								style="display: none;" style="" class="for spider">{{x.month}}</a>
						</div>
					</div> -->
					
						<c:forEach items="${hlist }" var="x">
						<div  >
						<div class="row">
							<a href="${preurl}/public/index/h/${x.month}.html">
								${x.month}&nbsp;<span>(${x.view_nums})</span>
							</a> 
						</div>
					</div>
					
						</c:forEach>
					
					
				</div>



			</div>
		</div>
	</div>


	<div class=" panel panel-success">
		<div class="panel-heading " title="" data-toggle=""
			data-parent="#accordion" href="#collapseOner3">
			<div class="row">
				<h3 class="panel-title col-xs-10  ">标签</h3>



				<span id="titlepic" class="hide" data-toggle="collapse"
					data-parent="#accordion" href="#collapseOner3"
					class="glyphicon glyphicon-chevron-up pull-right "></span>
			</div>
		</div>
		<div id="collapseOner3" class=" panel-collapse collapse in ">
			<div class="panel-body ">

				<div id="tgdiv" class="container ">


					<!-- <a ng-cloak ng-repeat="x in tglist"
						ng-href="{{preurl}}/public/index/yq/{{x.tags}}.html">
						{{x.tags}}&nbsp;, </a> <a ng-cloak ng-repeat="x in tglist"
						ng-href="{{preurl}}/public/index/yq/{{x.tags}}.html"
						style="display: none;" style="" class="for spider">{{x.tags}}</a> -->


<c:forEach items="${tglist }" var="x">
<a 
						href="${preurl}/public/index/tg/${x.tags}.html">
						${x.tags}&nbsp;, </a> 
</c:forEach>


				</div>



			</div>
		</div>
	</div>


	<div class=" panel panel-success">
		<div class="panel-heading " title="" data-toggle=""
			data-parent="#accordion" href="#collapseOner3">
			<div class="row">
				<h3 class="panel-title col-xs-10  ">友情链接&nbsp;<a class="yl" href="${basePath}/public/about#faq">申请友链</a></h3>



				<span id="titlepic" class="hide" data-toggle="collapse"
					data-parent="#accordion" href="#collapseOner3"
					class="glyphicon glyphicon-chevron-up pull-right "></span>
			</div>
		</div>
		<div id="collapseOner3" class=" panel-collapse collapse in ">
			<div class="panel-body ">

				<div class="container " id="yqdiv">


					<!-- <a ng-cloak ng-repeat="x in yqlist" ng-href="{{x.url_val}}"
						onclick="return gourl(this)">
						<div class="row col-xs-6">
							<span class="glyphicon glyphicon-hand-right"></span>
							{{x.url_name}}&nbsp;
						</div>
					</a> -->


<c:forEach items="${yqlist }" var="x">
<a  href="${x.url_val}"
						onclick="return gourl(this)">
						<div class="row col-xs-6">
							<span class="glyphicon glyphicon-hand-right"></span>
							${x.url_name}&nbsp;
						</div>
					</a>
</c:forEach>


				</div>



			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${basePath}/js/plugin/tagcloud.js"></script>

<script type="text/javascript"
	src="${basePath}/public/pright/pright_t_h.js"></script>



<div class=" panel panel-success">
		<div class="panel-heading " title="" data-toggle=""
			data-parent="#accordion" href="#collapseOner3">
			<div class="row">
				<h3 class="panel-title col-xs-10  ">可能喜欢&nbsp;</h3>



				<span id="titlepic" class="hide" data-toggle="collapse"
					data-parent="#accordion" href="#collapseOner3"
					class="glyphicon glyphicon-chevron-up pull-right "></span>
			</div>
		</div>
		<div id="collapseOner3" class=" panel-collapse collapse in ">
			<div class="panel-body ">

				<div class="container ">


			 <div id='googlead2'>

				</div>



			</div>
		</div>
	</div>


<div  class=" row col-xs-12">

 </div>
</div>

