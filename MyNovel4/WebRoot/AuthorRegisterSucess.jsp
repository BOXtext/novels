<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="bean.AuthorBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AuthorRegisterSuccess</title>
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
		if (session.getAttribute("author") == null) {
	%>
	<jsp:forward page="AuthorRegister.jsp" />
	<%
		return;
		}
	%>

	<jsp:useBean id="author" class="bean.AuthorBean" scope="session" />
	<h1>你的信息</h1>

	<ul class="RegisterSuccess">
		<li>你的姓名:${author.authorName}</li>
		<li><a href="login.jsp">前去登录页面</a></li>
	</ul>
	
	<script src="js/common1.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>