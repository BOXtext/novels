<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加小说</title>
<link rel="stylesheet" type="text/css" href="css/Iframe.css" />
</head>

<body style="background:#fff">

	<div class="FORM_Content">
		<span class="FORM_title">发布小说</span>
		<hr class="FORM_HR" />
		<div>
			<form action="AddNovelServlet" method="post"
				enctype='multipart/form-data'>
				<table>
					<tr>
						<td>小说名</td>
						<td><input class="FORM_input" type="text" placeholder="小说名"
							name="novelName" /><span>${addNovel.errors.novelName }</span></td>
					</tr>
					<tr>
						<td>小说图片</td>
						<td><input class="FORM_input" type="file" name="pic" /></td>
					</tr>
					<tr>
						<td>类别</td>
						<td><select name="category" class="FORM_input">
								<option value="玄幻">玄幻</option>
								<option value="武侠">武侠</option>
								<option value="修真">修真</option>
								<option value="爱情">爱情</option>
						</select></td>
					</tr>
					<tr>
						<td>内容简介</td>
						<td><textarea class="FORM_Area" rows="50" cols="50"
								name="content">          		
             				</textarea><span>${addNovel.errors.content }</span>
             			</td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="添加" class="submit"
							style="float:left;width:150px; margin:0 15px;height:40px; background:#0c89ff; 
							color:#fff; border:0; font-size:18px; margin-top:18px; cursor:pointer;" />
							<input type="reset" value="重置" class="reset"
							style="float:left;width:150px; margin:0 15px;height:40px; background:#0c89ff; 
							color:#fff; border:0; font-size:18px; margin-top:18px; cursor:pointer;" /></td>
					</tr>
				</table>
			</form>
		</div>

	</div>

</body>
</html>