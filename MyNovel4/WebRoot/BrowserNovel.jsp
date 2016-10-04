<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>浏览小说界面</title>
<link rel="stylesheet" type="text/css" href="css/Iframe.css" />
<link rel="stylesheet" href="utilLib/bootstrap.min.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="./jquery/jquery-2.1.1.js"></script>
<style type="text/css">
		body{
			background: url(images/bg-Pic2.jpg);
		}
		a:hover{color: white;}
		.n td{
			color: white;
		}
		.tb_title td{
			color: black;
		}
		</style>
</head>

<body>
	<div class="table_con">
		<table>
			<tr class="tb_title">
				<td width="20%">小说名</td>
				<td width="20%">类别</td>
				<td width="20%">点击量</td>
				<td width="20%">进度</td>
				<td width="10%">价格</td>
				<td></td>
			</tr>

			<c:forEach items="${novelList}" var="novel">
				<tr class="n">
					<td width="20%">${novel.novelName}</td>
					<td width="20%">${novel.category}</td>
					<td width="20%">${novel.clickCount}</td>
					<td width="20%">${novel.progress}</td>
					<td width="10%">${novel.price}</td>
					<td><a href="ReadNovelServlet?novelID=${novel.novelID}">阅读
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
