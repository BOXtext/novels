<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/login_register.css" />
		<script src="js/jquery-2.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div id="bg">
			<img class="bg" id="bg_01" src="images/bg-Pic3.jpg">
			<img class="bg" id="bg_01" src="images/bg-Pic1.jpg">
			<img class="bg" id="bg_01" src="images/bg-Pic2.jpg">
		</div>
		<div class="_form">
			<ul>
				<li><img src="images/2016-08-09_092946.jpg" /></li>
				<li>
					<form class="form-horizontal" action="ReaderRegisterServlet" method="post">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label" >读者名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputEmail3" placeholder="读者名" name="ReaderName"><span>${readerRegisterBean.errors.readerName}</span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputPassword3" placeholder="用户名" name="UserName"><span>${DBMes}</span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="inputPassword3" placeholder="密码" name="Password"><span>${readerRegisterBean.errors.password}</span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="inputPassword3" placeholder="确认密码" name="Password2">${readerRegisterBean.errors.password2}</span>
							</div>
						</div>
						<div class="" style=" width: 200px; margin-left: 50px; height: 30px;">
							<input value="男" name="Sex" type="radio" style=" width: 20px;"/>&nbsp;&nbsp;&nbsp;&nbsp;男
							<input value="女" name="Sex" type="radio" style=" width: 20px; margin-left: 50px;"/>&nbsp;&nbsp;&nbsp;&nbsp;女
						</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-default" value="确定" style="width: 70px; margin-left: 10px;"/>
				<input type="reset" class="btn btn-default" value="重置" style="width: 70px; margin-left: 80px;"/>
			</div>
			
		</div>
		</form>
		</li>
		</ul>
		</div>

		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/common1.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>
