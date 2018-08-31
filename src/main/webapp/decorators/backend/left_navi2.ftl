<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">

 
 
 	 <c:forEach items ="${menus}"  var="menu" >
       <li>
                <a href="#"><i class="fa fa-user fa-fw"></i> ${menu.name}<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level ">
                  <c:forEach items ="${menu.menus}"  var="item" >
                        <li>

     	<a href="${item.url!""}">${item.name!""}</a>                       

                        </li>
                   </c:forEach>
                </ul>
            </li>
        </c:forEach>
 
       <%--  <#list menus as menu>
            <li>
                <a href="#"><i class="fa fa-user fa-fw"></i> ${menu.name}<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level ">
                    <#assign items = menu.items>
                    <#list items as item>
                        <li>

     <a href="${item.url!""}">${item.name!""}</a>                       

                        </li>
                    </#list>
                </ul>
            </li>
        </#list> --%>


        </ul>
    </div>
</div>
