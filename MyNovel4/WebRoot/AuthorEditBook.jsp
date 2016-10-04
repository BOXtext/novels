<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理小说</title>
<link rel="stylesheet" type="text/css" href="css/Iframe.css" />
<link rel="stylesheet" href="utilLib/bootstrap.min.css" type="text/css"
	media="screen" />
</head>

<body>
	<span class="cp_title">管理小说</span>
	<div class="add_cp">
		<a href="AuthorAddBook.jsp">+添加小说</a>
	</div>
	<div class="table_con">
		<table>
			<tr class="tb_title">
				<td width="10%">序号</td>
				<td width="12%">类别</td>
				<td width="20%">小说名</td>
				<td width="10%">字数</td>
				<td width="12%">点击量</td>
				<td width="10%">状态</td>
				<td width="16%">操作</td>
			</tr>

			<c:forEach items="${books}" var="mbook">
				<tr>
					<td width="10%">${mbook.xuhao}</td>
					<td width="12%">${mbook.category}</td>
					<td width="20%"><a
						href="ReadNovelServlet?novelID=${mbook.novelID}">${mbook.novelName}</a></td>
					<td width="10%">${mbook.wordCount}</td>
					<td width="12%">${mbook.clickCount}</td>
					<td width="10%">${mbook.progress}</td>
					<td width="16%"><a
						href="NovelEditServlet?novelID=${mbook.novelID}"><input
							class="bj_btn" type="button" value="编辑" /></a> <input
						class="del_btn" type="button" value="删除" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
