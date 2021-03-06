<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

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


	<div class="" id="content" style="">

			<div class="wrapper col-xs-12  nopaddding">

				<div id="pleft" ng-cloak>
					<div>

						<div class="row col-xs-12  nopaddding">

							<div class="nopadding-left">
								<img class="nopaddding img-responsive col-xs-2"
									style="width: 25px; height: 25px;" title="{{x.blog_type_name}}"
									ng-src="{{x.blog_type_url}}">
									<div class="col-sm-8 col-xs-11 ptitle  ">{{x.title}}</div>


									<div class="col-sm-3 col-xs-12 text-right">
										<i class="fa fa-tags"></i> <a ng-repeat="t in x.tagStrs"
											title="t" ng-click="showtgs(t)">{{t}},</a>
									</div> <span ng-click="ff()" style=""
									class="ctrl nopaddding img-responsive col-xs-2 fa fa-exchange"
									title="隐藏/打开侧边栏"></span>
							</div>
						</div>

						<div class="margin-top-10 row col-xs-12"></div>
						<div class="hide row col-xs-12">
							<hr></hr>
						</div>

						<div class="col-xs-12 row nopaddding">
							<div class="">
								<div ng-cloak class=" pageText "
									ng-bind-html="x.context|sanitize"></div>
							</div>


						</div>

						<br>
							<div ng-cloak class="row col-xs-10 margin-top-5 ">
								post@{{x.create_date}}&nbsp; <span>阅读(<span id="rdnum">{{x.view_nums}}</span>)&nbsp;
								</span> <span>评论(<span id="rpnum">{{x.replay_nums}}</span>
									)&nbsp;
								</span>
							</div>


							<div class="row col-xs-12">
								<hr></hr>
							</div>
					</div>


					<div class="  col-xs-12 row tablefoot">
						<ul class="pagination pull-left">
							<li><a
								ng-href="{{preurl}}/public/html/{{pre.showdate}}/{{pre.imei}}.html"
								ng-show="pre" ng-click="" title="上一篇:{{pre.title}}">&laquo;{{pre.title}}</a>
								<a
								ng-href="{{preurl}}/public/html/{{pre.showdate}}/{{pre.imei}}.html"
								style="display: none;" class="for spider">{{pre.title}}</a></li>
						</ul>
						<ul class="pagination pull-right">
							<li><a
								ng-href="{{preurl}}/public/html/{{next.showdate}}/{{next.imei}}.html"
								ng-show="next" ng-click="" title="下一篇:{{next.title}}">{{next.title}}
									&raquo;</a> <a
								ng-href="{{preurl}}/public/html/{{next.showdate}}/{{next.imei}}.html"
								style="display: none;" class="for spider">{{next.title}}</a></li>
						</ul>





						<select onchange="changerows(this)" class="hide pull-right">
							<option ng-repeat="x in rows_select">{{x}}</option>
						</select>
					</div>




					<div class="row col-xs-12 ">

						<div class="pageText lincense">

							<div id="license_information">

								<p>
									本文 [{{x.title}}]基于<a href="https://mit-license.org/"
										title="MIT License " target="_blank">MIT License </a>
									许可协议发布,作者：野生的喵喵<a href="http://www.256kb.cn/">http://www.256kb.cn/</a>
								</p>
							</div>


							<p>
								文章固定链接： <a
									ng-href="{{preurl}}/public/html/{{x.showdate}}/{{x.imei}}.html">{{preurl}}/public/html/{{x.showdate}}/{{x.imei}}.html</a>
								转载请注明
							</p>
							<p></p>


						</div>
					</div>


					<div class="row col-xs-12 margin-top-10">
						<div class="good">
							<button type="button " class="btn btn-info   " ng-click="good() ">
								<i class="fa fa-heart-o"></i> 赞一个 <span>&nbsp;<span
									id="gdnum" ng-bind-html="goodnum|sanitize">{{goodnum}}</span></span>
							</button>
							<button type="button " class="pbtn btn btn-warning   "
								ng-click="pay() ">
								<i class="fa fa-handshake-o"></i> 赏一个
							</button>
						</div>
					</div>





					<div id='googlead' class="row col-xs-12"></div>

					<div class="row col-xs-12">
						<hr></hr>
					</div>
					<div class="row  col-xs-12 ">
						<p>可能感兴趣的文章:</p>
						<div class="nopadding-left row relatedLink">

							<ul class="col-xs-12 ">
								<li ng-repeat="rpage in relatedlist">
									<!--   <a ng-href="{{preurl}}/public/html/{{next.showdate}}/{{next.imei}}.html" ng-show="next" ng-click="" title="下一篇:{{next.title}}">{{next.title}} &raquo;</a> -->

									<img class="nopaddding img-responsive col-xs-2"
									style="width: 25px; height: 25px;"
									title="{{rpage.blog_type_name}}"
									ng-src="{{rpage.blog_type_url}}"> <a
										ng-href="{{preurl}}/public/html/{{rpage.showdate}}/{{rpage.imei}}.html"
										class="col-sm-8 col-xs-11 ">{{rpage.title}}</a>
								</li>
							</ul>


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





	<script type="text/javascript" src="${basePath}/public/detail/index_angular.js"></script>
	<script type="text/javascript"
		src="${basePath}/public/detail/replay.js"></script>


	<script type="text/javascript"
		src="${basePath}/js/own/automenu/jquery.autoMenu.js"></script>






	<script async
		src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
		
		
<div id='googleadcode_bottom' style='display: none'>

	<!-- auto -->
	<ins class="adsbygoogle" style="display: block"
		data-ad-client="ca-pub-4546997533420825" data-ad-slot="3251638392"
		data-ad-format="auto" data-full-width-responsive="true"></ins>
	<script>
		(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
</div>


<div id='googleadcode_d_right' style='display: none'>

	<!-- auto -->
	<ins class="adsbygoogle" style="display: block"
		data-ad-client="ca-pub-4546997533420825" data-ad-slot="3316337130"
		data-ad-format="auto" data-full-width-responsive="true"></ins>
	<script>
		(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
</div>

<div id='googleadcode3' style='display: none'>
	
	<!-- auto -->
	<ins class="adsbygoogle" style="display: block"
		data-ad-client="ca-pub-4546997533420825" data-ad-slot="5098577692"
		data-ad-format="auto" data-full-width-responsive="true"></ins>
	<script>
		(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
</div>

<script language='javascript'>
	setTimeout(function() {
		if (document.all.item('googlead3') != null) {
			googlead3.innerHTML = googleadcode3.innerHTML;
		}

		if (document.all.item('googlead2') != null) {
			googlead2.innerHTML = googleadcode_d_right.innerHTML;
		}

		if (document.all.item('googlead') != null) {
			googlead.innerHTML = googleadcode_bottom.innerHTML;
		}
	}, 500);
</script>




</body>

</html>