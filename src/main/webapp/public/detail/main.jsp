<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>


<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular-resource.min.js"></script>

<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular-sanitize.min.js"></script>


<link rel="stylesheet"
	href="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/default.css">
	<link rel="stylesheet"
		href="<c:out value='${basePath}'/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">

		<script type="text/javascript"
			src="<c:out value='${basePath}'/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
		<link rel="stylesheet"
			href="<c:out value='${basePath}'/>/js/own/automenu/jquery.autoMenu.css">
</head>

<body>

	<input type="hidden" value="${curBlog.tags}" id="tagstrval" />
	<input type="hidden" value="${curBlog.imei}" id="imei" />


	<div class="" id="content" style="">




		<div class="wrapper col-xs-12  nopaddding">

			<div id="pleft" ng-cloak>
				<div>

					<div class="row col-xs-12  nopaddding">

						<div class="nopadding-left">
							<img class="nopaddding img-responsive col-xs-2"
								style="width: 25px; height: 25px;"
								title="${curBlog.blog_type_name}" src="${curBlog.blog_type_url}">
								<div class="col-sm-8 col-xs-11 ptitle  ">${curBlog.title}</div>




								<div class="col-sm-12 col-xs-12 pagetagdv ">
									<i class="fa fa-tags"></i>



									<c:forEach items="${curBlog.tagStrs }" var="tag">
										<a title="${tag}"
											href="${basePath}/public/index/tg/${tag}.html"><span
											class="ant-tag ant-tag-has-color">${tag}</span> </a>

									</c:forEach>


								</div>
						</div>
					</div>


					<div class="postdiv row col-xs-12 margin-top-5 ">
						<span title="发布时间"><i class="fa fa-calendar"></i>&nbsp;${curBlog .create_date}</span>&nbsp;
						<span title="围观次数"> <i class="fa fa-child">&nbsp;</i><span
							id="rdnum">${curBlog .view_nums}</span>&nbsp;
						</span> <span title="吐槽次数"> <i class="fa fa-comments">&nbsp;</i><span
							id="rpnum">${curBlog .replay_nums}</span>&nbsp;
						</span> <span title="爬虫造访次数"><i class="fa fa-bug"></i>&nbsp;<span
							id="spidernum"></span></span> <i ng-click="ff()" style=""
							class="ctrl nopaddding img-responsive col-xs-2 fa fa-space-shuttle fa-rotate-180 pull-right"
							title="隐藏/打开侧边栏"></i>
					</div>


					<div class="margin-top-10 row col-xs-12"></div>
					<div class="hide row col-xs-12">
						<hr></hr>
					</div>



					<div id="timediv" class=" hide row col-xs-12 col-md-9 col-lg-9">

						<div class="pageText timeinfo">

							<div id="license_information">

								<p>
									<i class=" fa fa-exclamation-triangle"></i> 本文最后更新于<span
										id="days"></span>天前,文中介绍内容及环境可能已不适用.请谨慎参考.
								</p>
							</div>


							<p></p>


						</div>
					</div>



					<div class=" col-xs-12 row nopaddding">
						<div class="pageText">
							<div class="pctnew  ">${curBlog .content}</div>
						</div>


					</div>

					<br>
					<div class="postdiv row col-xs-12 margin-top-5 left ">
						<span title="最后更新于"><i class="fa fa-pencil-square "></i>&nbsp;${curBlog .update_date}&nbsp;
						</span>
					</div>

					<div class="row col-xs-12 margin-top-10">
						<div class="good">
							<span class="sout"> <span class="fa fa-stack fa-lg"
								ng-click="good() "> <i
									class="fa fa-circle fa-stack-2x topcircle2"></i> <i
									class=" zan fa fa-thumbs-up fa-stack-1x fa-inverse"
									title="写的不错"></i> <span>&nbsp;<span id="gdnum" class="goodnum"
										ng-bind-html="goodnum|sanitize">${goodnum}</span></span>

							</span> <span>赞</span>
							</span> <span class="sout"> <span class="fa fa-stack fa-lg"
								ng-click="pay() "> <i
									class="fa fa-circle fa-stack-2x topcircle3"></i> <i
									class="pay fa fa-dollar fa-stack-1x fa-inverse" title=" 我要打赏"></i>

							</span> <span>赏</span>
							</span>
						</div>
					</div>


					<div class="row col-xs-12">
						<hr></hr>
					</div>
				</div>


				<div class=" prenexlist col-xs-12 row tablefoot">
					<ul class="pagination pull-left">
						<li><c:if test="${preBlog!=null }">
								<a
									href="${basePath}/public/html/${preBlog.showdate}/${preBlog.imei}.html"
									title="上一篇" style="" class="for spider"><i class="fa fa-arrow-circle-left"></i>&nbsp;${preBlog.title}</a>
							</c:if></li>
					</ul>
					<ul class="pagination pull-right">
						<li><c:if test="${nextBlog!=null }">
								<a
									href="${basePath}/public/html/${nextBlog.showdate}/${nextBlog.imei}.html"
									title="下一篇" style="" class="for spider">${nextBlog.title} &nbsp;<i class="fa fa-arrow-circle-right"></i>&nbsp;</a>
							</c:if></li>
					</ul>




				</div>





				<div class="row col-xs-12 ">

					<div class=" lincense">

						<div id="license_information">

							<p>

								<i class="fa fa-hand-o-right"></i>&nbsp; 本文基于<a
									href="${curBlog.cclink}" title="${curBlog.ccname}"
									target="_blank">${curBlog.ccname}</a> 许可协议发布,作者：<a
									href="http://www.256kb.cn/" class="authorf">野生的喵喵</a>。 <span> 固定链接： <a class="pagelink"
									href="${preurl}/public/html/${curBlog.showdate}/${curBlog.imei}.html">【${curBlog.title}】</a>
									转载请注明
								</span>
							</p>
							<p></p>
						</div>


						<%-- <p>
								文章固定链接： <a
									href="${preurl}/public/html/${curBlog.showdate}/${curBlog.imei}.html">[${curBlog.title}]</a>
								转载请注明
							</p>
							<p></p> --%>


					</div>
				</div>







				<div class="row col-xs-12 margin-top-10">
					<div id='googlead'></div>
				</div>

				<div class="row col-xs-12">
					<hr></hr>
				</div>
				<div class="row  col-xs-12 ">
					<p><i class="fa fa-puzzle-piece"></i>&nbsp;相关文章:</p>
					<div class="nopadding-left row relatedLink">

						<ul class="col-xs-12 ">

							<c:forEach items="${relatedBLogs }" var="relateblog">
								<li><span class="col-xs-2"><img class="rimg nopaddding img-responsive "
									
									title="${relateblog.blog_type_name}"
									src="${relateblog.blog_type_url}"> 
									</span>
									<a
										href="${basePath}/public/html/${relateblog.showdate}/${relateblog.imei}.html"
										class="col-sm-8 col-xs-11 ">${relateblog.title}</a></li>
							</c:forEach>



						</ul>


					</div>
				</div>

				<div class="row col-xs-12 margin-top-10">
					<div id='googlead_link'></div>
				</div>


				<canvas id="myCanvas"></canvas>
				<div class="modal fade " id="payMd" data-backdrop="static"
					tabindex="-1 " role="dialog " aria-labelledby="myModalLabel "
					aria-hidden="true">
					<div class="modal-dialog paybody">
						<div class="modal-content ">
							<div class="modal-header " style="border-bottom: 0px">
								<button type="button " class="close " data-dismiss="modal"
									aria-hidden="true ">&times;</button>
								<h4 class="modal-title " id="myModalLabel ">打赏方式:</h4>
							</div>



							<div class="modal-body  ">
								<div class="col-xs-12 ">
									<div class='pcenter'>


										<ul class="pul">
											<li><label><input class='paytype' name='paytype'
													checked="checked" for="#pp1" type='radio'>微信</label></li>
											<li><label><input class='paytype' name='paytype'
													for="#pp2" type='radio'>支付宝</label></li>
										</ul>


										<div class="tab-content paycontent">
											<div class="row  col-xs-12 tab-pane fade in active " id="pp1">
												<div class='pbord'>


													<img class='img-responsive'
														src='${basePath }/public/detail/weixin.png'></img>

													<div class='ptip'>微信</div>
												</div>
											</div>
											<div class="row  col-xs-12 tab-pane fade in  " id="pp2">
												<div class='pbord alip'>


													<img class='img-responsive'
														src='${basePath }/public/detail/zhifub.png'></img>

													<div class='ptip'>支付宝</div>
												</div>
											</div>
										</div>

									</div>


								</div>

							</div>
						</div>

					</div>
				</div>



			</div>




			<%@include file="comment.jsp"%>

		</div>

	</div>



	<div class="result" id="outdiv">
		<div class="indiv">
			<img class="imgresult" id="bigimg" src="">
		</div>
	</div>




	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.lazyload.v.1.9.1.js"></script>


	<script type="text/javascript" src="${basePath}/public/detail/index.js"></script>
	<script type="text/javascript"
		src="${basePath}/public/detail/replay.js"></script>


	<script type="text/javascript"
		src="${basePath}/js/own/automenu/jquery.autoMenu.js"></script>









</body>

</html>