<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小说详情</title>
<script type="text/javascript" src="./jquery/jquery-1.3.1.js"></script>
<style type="text/css">
		body{
		color:white;
			background: url(images/bg-Pic2.jpg);
		}
		#h1{margin-left:50%;}
		p{margin-left:16%;}
		a{
		margin-left:20%;
			color:white;text-decoration:none;
		}
		#b{margin-left:80%;}
		</style>
</head>

<body>

	<h1 id="h1">${novelName}</h1>
	<p>章节列表:</p>
	<table>
		<dl>
			<c:forEach items="${chapterList}" var="chapter">
	
				<dd>
					<a id="aa" href="ReadChapterServlet?chapterID=${chapter.chapterID}">
							${chapter.chapterName}</a>
				</dd>
			</c:forEach>	
		</dl>
	</table>
	<a id="b" href="BrowserNovel.jsp">返回书籍列表</a>
</body>
</html>
