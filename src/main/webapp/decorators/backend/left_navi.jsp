<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/css/nav-left.css">



<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="  sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse nopaddding">
		<ul class="nav-left nav-sidebar " id="side-menu">



			<c:forEach items="${menus}" var="menu">
				<li class='leftmenu'><a data-url="${menu.menuUrl }" href="#"
<c:choose> 
     	<c:when test="${menu.menuUrl!=null && menu.menuUrl!=''  }">   
     	onclick="javascript:loadmenu('${menu.menuUrl }')"  class="dropdown-toggle "
 		</c:when>      
     	<c:otherwise>  
     	class="dropdown-toggle"
    	data-toggle="collapse" data-target="#${menu.menuId }" aria-expanded="true"
  		</c:otherwise> 

</c:choose>
>
						<i class="${menu.menuICO}"></i> ${menu.menuName}<span
						
						<c:if test="${menu.menuUrl==null || menu.menuUrl==''  }">
						class="fa fa-chevron-down "
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


	<span class="zzbutn"  data-tippy-content="隐藏菜单" ><i class="icon fa fa-backward"></i><div class="shape"></div></span>
	

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
				$(ul).parent().find("span").attr("class",
				"glyphicon glyphicon-chevron-up ");
			}
		});

		
		
		$('.leftmenu-second').on(
				'shown.bs.collapse',
				function(e) {
					// 执行一些动作...
					$(e.target).parent().find("span").attr("class",
					"glyphicon glyphicon-chevron-up ");
					
				});

		$('.leftmenu-second').on(
				'hidden.bs.collapse',
				function(e) {
					$(e.target).parent().find("span").attr("class",
							"glyphicon glyphicon-chevron-down pull-right");
				});
	
		
		
		   // 顶部菜单固定
	    $('.zzbutn').click(function () {


	    	  var fixednavbar = localStorage.getItem("fixednavbar");
	    	
		    
	    	    if (fixednavbar == 'on') {
	    	    	$("body").removeClass('zz');

	    			$(".zzbutn").attr('data-tippy-content','收起菜单');
	    	    	
	    	    	 if (localStorageSupport) {
			                localStorage.setItem("fixednavbar", 'off');
			            }
	    	    }
	    	    else{
	    	    	$("body").addClass('zz');
	    	    	$(".zzbutn").attr('data-tippy-content','展开菜单');
	    	    	
	    	    	 if (localStorageSupport) {
			                localStorage.setItem("fixednavbar", 'on');
			            }
		    	    }

	   
	    });

	        if (localStorageSupport) {
	            
	            var fixednavbar = localStorage.getItem("fixednavbar");
	          
	            if (fixednavbar == 'on') {
	                $('body').addClass("zz");
	                $(".zzbutn").attr('data-tippy-content','收起菜单');
	            }
	            else
		            {
	            	$('body').removeClass("zz");
	            	$(".zzbutn").attr('data-tippy-content','展开菜单');
	            	}
	        }
		
		
		
		
		
		
		
	})
	
	
		  //判断浏览器是否支持html5本地存储
			    function localStorageSupport() {
			        return (('localStorage' in window) && window['localStorage'] !== null)
			    }
	
</script>
