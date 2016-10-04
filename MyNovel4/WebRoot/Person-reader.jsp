<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	import="bean.ReaderBean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ReaderRegisterSuccess</title>
</head>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/bg.css" />
		<script src="js/jquerymini/jquery-2.1.1.min.js" type="text/javascript" charset="utf-8"></script>
		
<body>
<div id="bg" >
			<img class="bg" id="bg_01" src="images/bg-Pic3.jpg" >
			<img class="bg" id="bg_01" src="images/bg-Pic1.jpg" >
			<img class="bg" id="bg_01" src="images/bg-Pic2.jpg" >
		</div>
	<%
		if (session.getAttribute("readerBean") == null){
	%>
	<%
		return;}
	%>
	<jsp:useBean id="readerBean" class="bean.ReaderBean" scope="session" />
	注册成功
	<ul class="RegisterSuccess">
		<li>您的读者名：${readerBean.readerName}</li>
		<li>您的用户名：${readerBean.userName}</li>
		<li><a href="login.jsp">前去登录页面</a></li>
	</ul>
	<script src="js/common1.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
