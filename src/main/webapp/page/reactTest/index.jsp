<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/tag.jsp"%>

<!--   <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
  <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
   -->

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
			<meta name="description" content="">
				<meta name="author" content="">

					<title>React Test</title>
					
					  <script  type="text/javascript" src="https://unpkg.com/react@16/umd/react.production.min.js" crossorigin></script>
<script  type="text/javascript" src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js" crossorigin></script>
<script  type="text/javascript" src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
					
</head>

<body>




	<div id="pmain"></div>


	<!-- 模态框（Modal） -->

	<jsp:include page="form.jsp"></jsp:include>

	<script   type="text/babel"
		src="${basePath}/page/reactTest/myip.js"></script>


	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.plugin.js"></script>



</body>
</html>