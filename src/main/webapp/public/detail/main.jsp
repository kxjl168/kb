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

<input type="hidden" value="${curBlog.tags}" id="tagstrval"/>
<input type="hidden" value="${curBlog.imei}" id="imei"/>


	<div class="" id="content" style="">

			<div class="wrapper col-xs-12  nopaddding">

				<div id="pleft" ng-cloak>
					<div>

						<div class="row col-xs-12  nopaddding">

							<div class="nopadding-left">
								<img class="nopaddding img-responsive col-xs-2"
									style="width: 25px; height: 25px;" title="${curBlog.blog_type_name}"
									src="${curBlog.blog_type_url}">
									<div class="col-sm-8 col-xs-11 ptitle  ">${curBlog.title}</div>




									<div class="col-sm-3 col-xs-12 text-right">
										<i class="fa fa-tags"></i> 
											
											
											
											<c:forEach items="${curBlog.tagStrs }" var="tag">
											<a 
											title="${tag}" href="${basePath}/public/index/tg/${tag}.html">${tag},</a>
											</c:forEach>
											
											
									</div> <span ng-click="ff()" style=""
									class="ctrl nopaddding img-responsive col-xs-2 fa fa-exchange"
									title="隐藏/打开侧边栏"></span>
							</div>
						</div>
						
						<div  class="postdiv row col-xs-12 margin-top-5 ">
								发布于${curBlog .create_date}&nbsp; <span>已有<span id="rdnum">${curBlog .view_nums}</span>人围观&nbsp;
								</span> <span> <span id="rpnum">${curBlog .replay_nums}</span>人吐槽&nbsp;</span>
							</div>
						

						<div class="margin-top-10 row col-xs-12"></div>
						<div class="hide row col-xs-12">
							<hr></hr>
						</div>

						<div class="col-xs-12 row nopaddding">
							<div class="">
								<div class=" pageText "
									>${curBlog .content}</div>
							</div>


						</div>

						<br>
							

							<div class="row col-xs-12">
								<hr></hr>
							</div>
							
							   <div  class="postdiv row col-xs-12 margin-top-5 left ">
								最后更新于${curBlog .update_date}&nbsp; </span>
							</div>
					</div>


					<div class="  col-xs-12 row tablefoot">
						<ul class="pagination pull-left">
							<li>
							<c:if test="${preBlog!=null }">
								<a
								href="${basePath}/public/html/${preBlog.showdate}/${preBlog.imei}.html"
								style="" class="for spider">${preBlog.title}</a>
								</c:if>
								</li>
						</ul>
						<ul class="pagination pull-right">
							<li>
								<c:if test="${nextBlog!=null }">
								<a
								href="${basePath}/public/html/${nextBlog.showdate}/${nextBlog.imei}.html"
								style="" class="for spider">${nextBlog.title}</a>
								</c:if>
							</li>
						</ul>


	               

					</div>




					<div class="row col-xs-12 ">

						<div class="pageText lincense">

							<div id="license_information">

								<p>
									本文 [${curBlog.title}]基于<a href="https://mit-license.org/"
										title="MIT License " target="_blank">MIT License </a>
									许可协议发布,作者：野生的喵喵<a href="http://www.256kb.cn/">http://www.256kb.cn/</a>
								</p>
							</div>


							<p>
								文章固定链接： <a
									href="${preurl}/public/html/${curBlog.showdate}/${curBlog.imei}.html">${preurl}/public/html/${curBlog.showdate}/${curBlog.imei}.html</a>
								转载请注明
							</p>
							<p></p>


						</div>
					</div>


					<div class="row col-xs-12 margin-top-10">
						<div class="good">
							<button type="button " class="btn btn-info   " ng-click="good() ">
								<i class="fa fa-heart-o"></i> 赞一个 <span>&nbsp;<span
									id="gdnum" ng-bind-html="goodnum|sanitize">${goodnum}</span></span>
							</button>
							<button type="button " class="pbtn btn btn-warning   "
								ng-click="pay() ">
								<i class="fa fa-handshake-o"></i> 赏一个
							</button>
						</div>
					</div>





					<div class="row col-xs-12 margin-top-10">
					 <div id='googlead'>
					 </div>
					</div>

					<div class="row col-xs-12">
						<hr></hr>
					</div>
					<div class="row  col-xs-12 ">
						<p>可能感兴趣的文章:</p>
						<div class="nopadding-left row relatedLink">

							<ul class="col-xs-12 ">
							
							<c:forEach items="${relatedBLogs }" var="relateblog">
							<li >

									<img class="nopaddding img-responsive col-xs-2"
									style="width: 25px; height: 25px;"
									title="${relateblog.blog_type_name}"
									src="${relateblog.blog_type_url}"> <a
										href="${basePath}/public/html/${relateblog.showdate}/${relateblog.imei}.html"
										class="col-sm-8 col-xs-11 ">${relateblog.title}</a>
								</li>
							</c:forEach>
							
							
								
							</ul>


						</div>
					</div>
					
					<div class="row col-xs-12 margin-top-10">
					 <div id='googlead_link'>
					 </div>
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
												<li><label><input class='paytype'
														name='paytype' checked="checked" for="#pp1" type='radio'>微信</label></li>
												<li><label><input class='paytype'
														name='paytype' for="#pp2" type='radio'>支付宝</label></li>
											</ul>


											<div class="tab-content paycontent">
												<div class="row  col-xs-12 tab-pane fade in active "
													id="pp1">
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





	<script type="text/javascript" src="${basePath}/public/detail/index.js"></script>
	<script type="text/javascript"
		src="${basePath}/public/detail/replay.js"></script>


	<script type="text/javascript"
		src="${basePath}/js/own/automenu/jquery.autoMenu.js"></script>









</body>

</html>