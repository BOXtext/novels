<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
		<style type="text/css">
		body{
			background: url(images/bg-Pic2.jpg);
		}
		</style>
	</head>

	<body>
		<div class=" _form_login" >
			<form class="form-horizontal" action="LoginServlet" method="post">
				<span style="margin-left: 215px;margin-bottom: 4px; font-size: 20px;">欢迎登陆</span>
				<div class="form-group">
					<label for="test1" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input name="username" type="text" class="form-control" id="test1" placeholder="用户名" onblur="show()">
						<div id="_alert"></div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input name="password" type="password" class="form-control" id="inputPassword3" placeholder="密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label class="_radio">
         读者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="state" value="reader" > 
        				</label>
							<label>
         <label class="_radio">
        作者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="state" value="writter" > 
        				</label>
        				<label class="_radio">
        管理员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="state" value="manager"> 
        				</label>
						</div>
					</div>
				</div>
				<div class="form-group ">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">登陆</button>
						<a href="ReaderRegister.jsp" class="_a">读者注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="AuthorRegister.jsp" class="_a">作者注册</a>
					</div>
				</div>
			</form>
		</div>
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			function show(){
				var v=$("#inputEmail3").val();
				if(v == ""){
					$("#_alert").css("display","block");
				}
			}
		})
		
		</script>
	</body>

</html>
