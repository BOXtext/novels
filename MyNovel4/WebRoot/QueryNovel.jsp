<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询小说</title>
<base href="<%=basePath%>">
	<style type="text/css">
		body{
			background: url(images/bg-Pic2.jpg);
		}
		</style>
</head>
<body>
	<div>
		<form action="QueryNovelServlet" method="post">
			<table width="100%">
				<tr>
					<td>小说名</td>
					<td><input type="text" name="novelName"
						value="${novelBean.novelName}" /> <input type="submit" value="查询" />
					</td>

					<td width="70%" align="right"><a href="ShopCarServlet">
							购物车${novelCount} </a></td>
				</tr>
			</table>
		</form>
	</div>
	<hr />
	<div>
		<table cellpadding="10">
			<tr>
				<th>小说名</th>
				<th>类型</th>
				<th>点击量</th>
				<th>进度</th>
				<th>价格</th>
				<th></th>
			</tr>
			<c:forEach items="${queryNovelList}" var="novel">
				<tr>
					<td>${novel.novelName }</td>
					<td>${novel.category }</td>
					<td>${novel.clickCount }</td>
					<td>${novel.progress }</td>
					<td>${novel.price }</td>
					<td><a href="BuyNovelServlet?NovelID=${novel.novelID}">购买
					</a></td>
				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>