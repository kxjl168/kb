<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/css/nav-left.css">



<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class=" sidebar lefturl" role="navigation">
	<div class="sidebar-nav navbar-collapse nopaddding">
		<ul class="nav-left " id="side-menu">



			<c:forEach items="${menus}" var="menu" varStatus="status">
				<li class='leftmenu'><a  href="#${menu.menuName}"
<c:choose> 
     	<c:when test="${menu.menuUrl!=null && menu.menuUrl!=''  }">   
     	onclick="javascript:loadmenu('${menu.menuUrl }')"  class="dropdown-toggle "
 		</c:when>      
     	<c:otherwise>  
     	class="dropdown-toggle"
    	data-toggle="collapse" data-target="#${menu.menuName }" aria-expanded="true"
  		</c:otherwise> 

</c:choose>
>
						<i class="${menu.menuICO}"></i> ${menu.menuName}<span
						
						<c:if test="${menu.menuUrl==null || menu.menuUrl==''  }">
						class=" "
						</c:if>
						
						
						></span>
				</a>
					<ul class="leftmenu-second collapse    " id="${menu.menuId }">



						<c:forEach items="${menu.childMenus}" var="item">
							<li><a href="javascript:void(0)" data-url="${item.menuUrl }"
								onclick="loadmenu('${item.menuUrl }')">${item.menuName}</a></li>
						</c:forEach>
					</ul></li>
			</c:forEach>

		

		</ul>
	</div>
</div>

<script>
	$(function() {
		var url = window.location;
		// var element = $('ul.nav a').filter(function() {
		//     return this.href == url;
		// }).addClass('active').parent().parent().addClass('in').parent();
		var element = $('.nav-left a').filter(
				function(index, item) {

					$(item).removeClass('active');

					var isin = false;
					if ($(item).attr("data-url") != null
							&& $(item).attr("data-url") != "")
						isin = url.href.indexOf($(item).attr("data-url")) > -1;
					else
						isin = false;

					return isin;
				}).addClass('active').parent();

		$.each(element, function(index, item) {
			if (!$(item).hasClass('leftmenu')) {
				var ul=$(item).parent().addClass('in');
				$(ul).parent().find(".dropdown-toggle").addClass("active");
			/* 	$(ul).parent().find("span").attr("class",
				"glyphicon glyphicon-chevron-up "); */
			}
		});

		
		
		$('.leftmenu-second').on(
				'shown.bs.collapse',
				function(e) {
					// 执行一些动作...
				/* 	$(e.target).parent().find("span").attr("class",
					"glyphicon glyphicon-chevron-up "); */
					
				});

		$('.leftmenu-second').on(
				'hidden.bs.collapse',
				function(e) {
					/* $(e.target).parent().find("span").attr("class",
							"glyphicon glyphicon-chevron-down pull-right"); */
				});

		
		

		otherScroll = function(){
			        var scrollTop = document.documentElement.scrollTop + document.body.scrollTop;
			        var leftTop= $(".lefturl.sidebar").scrollTop();
			       
			        if(scrollTop >=leftTop+50){
			        //	$(".lefturl.sidebar").width($(".lefturl.sidebar").width());
			        	 $(".lefturl.sidebar").addClass("fixed");
			        	 
			        	 var height = window.innerHeight
			        	    || document.documentElement.clientHeight
			        	    || document.body.clientHeight;
			        	 
			        	 $(".nav-left").height(height-100);
			            
			        }else{
			        	 $(".lefturl.sidebar").removeClass("fixed");
			        }
			       
			    };
			
			
			    
	})
	
	
	
	
</script>
