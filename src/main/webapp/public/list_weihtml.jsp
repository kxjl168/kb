<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<div ng-repeat="x in datalist" class="pgdiv">

									<div class="row col-xs-12 ">

										<div class="nopadding-left">
											<img class="nopaddding img-responsive col-xs-2" style="width:25px; height:25px;" title="{{x.blog_type_name}}"   ng-src="{{x.blog_type_url}}">
											<div class="col-sm-8 col-xs-11  ptitle  "><a class="ptitle"  ng-href="{{preurl}}/public/detail/{{x.imei}}.html" >{{x.title}}</a>
											<a ng-href="{{preurl}}/public/detail/{{x.imei}}.html" style="display: none;" class="for spider" >{{x.title}}</a>
											</div>


											<div class="col-sm-3 col-xs-12  text-right">
										
											
											<a ng-repeat="t in x.tagStrs"  ng-click="showtgs(t)">{{t}},</a>
											</div>
										</div>
									</div>

									<div class="margin-top-10 row col-sm-10 col-xs-12"></div>
									<div class="hide row col-xs-12">
										<hr></hr>
									</div>


									<div class="col-xs-12 row">
										<div class="alldot">
											<div ng-cloak class=" pageText " ng-bind-html="x.context">
											</div>
										
										</div>
											<div class="more row col-xs-12"></div>
											<div>
												
												<a href="#" class="detailbtn h5 text-right pull-right text-info margin-right-20"  ng-href="{{preurl}}/public/detail/{{x.imei}}.html"  >详情</a>
												<a ng-href="{{preurl}}/public/detail/{{x.imei}}.html" style="display: none;" class="for spider" >{{x.title}}</a>

											</div>
									
									</div>
									<br>
									<div ng-cloak class="row  col-sm-10 col-xs-12 margin-top-5 ">post@{{x.create_date}}&nbsp;
										<span ><a ng-href="{{preurl}}/public/detail/{{x.imei}}.html">阅读</a>({{x.view_nums}})&nbsp;</span>
										<span ><a ng-href="{{preurl}}/public/detail/{{x.imei}}.html#rpdiv">评论</a>({{x.replay_nums}})&nbsp;</span>
									</div>


									<div class="row col-xs-12">
										<hr></hr>
									</div>
								</div>


<div class="  col-xs-12 row tablefoot" ng-show="total">


						<ul class="pagination pull-right">
							<li>
								<a href="#" ng-click="getList(1)">&laquo;</a>
								<a ng-href="{{preurl}}/public/index/i/1.html"  style="display: none;"  style="" class="for spider" >第一页</a>
							</li>
							<li ng-repeat="x in pageDataPre">

								<a href="#" ng-click="getList(x)">{{x}}</a>
<a ng-href="{{preurl}}/public/index/i/{{x}}.html" style="display: none;" class="for spider" >第{{x}}页</a>
							</li>
							<li class="active">
								<a href="#" ng-click="getList(page)">{{page}}</a>
							</li>
							<li ng-repeat="x in pageDataAft">

								<a href="#" ng-click="getList(x)">{{x}}</a>
<a ng-href="{{preurl}}/public/index/i/{{x}}.html" style="display: none;" class="for spider" >第{{x}}页</a>
							</li>
							<li>
								<a href="#" ng-click="getList(pageNum)">&raquo;</a>
								<a ng-href="{{preurl}}/public/index/i/{{pageNum}}.html" style="display: none;" class="for spider" >最后一页</a>
							</li>

						
						</ul>

						<select onchange="changerows(this)" class="hide pull-right">
							<option ng-repeat="x in rows_select">{{x}}</option>
						</select>
					</div>
					
			<div class=" col-xs-12">
				<div class=" col-xs-12 row panel panel-default pageText" >
					<div> 我有几张阿里云幸运券分享给你，用券购买或者升级阿里云相应产品会有特惠惊喜哦！
					<br>
					 把想要买的产品的幸运券都领走吧！快下手，马上就要抢光了。 <a href="https://promotion.aliyun.com/ntms/act/ambassador/sharetouser.html?userCode=2pgttaay&productCode=vm&utm_source=2pgttaay">点击领取</a>
					 </div>
					
					
				</div>
			</div>
				