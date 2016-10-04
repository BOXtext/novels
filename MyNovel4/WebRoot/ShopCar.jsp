<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物车</title>
<style type="text/css">
		body{
			background: url(images/bg-Pic2.jpg);
		}
		</style>

</head>

<body>
	<div>
		<h1>购物车</h1>
		<hr />
		<table border="0" cellpadding="2">
			<tr>
				<th>小说名</th>
				<th>类型</th>
				<th>点击量</th>
				<th>价格</th>
				<th></th>
			</tr>
			<c:forEach items="${cartNovelList}" var="novel">
				<tr>
					<td>${novel.novelName }</td>
					<td>${novel.category }</td>
					<td>${novel.clickCount }</td>
					<td>${novel.price }</td>
					<td><a href="RemoveNovelServlet?NovelID=${novel.novelID}">删除
					</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>总价</td>
				<td colspan="4">${total}
				<td>
			<tr>
		</table>
	</div>
</body>
</html>
