<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div id="ftop" class="navbar-fixed-top ">
	<nav class="top3 navbar  navbar-kindex" role="navigation">

		<div class="navbar-header public">
			<button type="button" id="menuBtn" class="navbar-toggle"
				data-toggle="collapse" data-target="#menuItem">
				<span class="sr-only"></span><span class="fa fa-navicon  "></span> <span class=""></span>
			</button>
			<a  class="pc navbar-brand  toptitle " href="#" ng-cloak>KxのBOOK</a>
			<a id="toptitle" class="mobile navbar-brand  toptitle " href="#" ng-cloak>野生的喵喵's BOOK</a>



		</div>
		<div>
			<div class="collapse navbar-collapse  " id="menuItem">
				<ul class="hide nav navbar-nav ">

				</ul>
				<form class="hide navbar-form navbar-left " role="search"></form>

				<ul class="nav navbar-nav " id="menuul">


				</ul>


			</div>
		</div>
	</nav>


</div>
<div class="thead">
	<div class=" sidebar-container ">



		<div id="mhead" class="short-about">

			<img class="phead" src="${httppath}${head.http_relative_path }"> 
			
			<div class="hgroup">
			<a class="navbar-brand toptitle"
				href="${basePath}/public/about/">野生的喵喵’s BOOK</a>
			<p>${sign }</p>
			</div>
		</div>


	</div>
</div>
<nav class="hide top2 navbar navbar-default margin-bottom-0 "
	role="navigation">
	<div style="background-color: red; border: 1px solid #777;"></div>

</nav>

<script>
  $(function(){
	 if($("#t1").html()=="")
		 $("#t1").html($("#t2").html());
  });
</script>