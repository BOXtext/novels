<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小说管理</title>

<link rel="stylesheet" href="css/index.css" type="text/css"
	media="screen" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/tendina.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</head>
<body>
	<!--顶部-->
	<div class="top">
		<div style="float: left">
			<span
				style="font-size: 16px;line-height: 45px;padding-left: 20px;color: #fff">小说管理中心
				</h1>
			</span>
		</div>
		<div id="ad_setting" class="ad_setting">
			<a class="ad_setting_a" href="javascript:; ">123456@qq.com</a>
			<ul class="dropdown-menu-uu" style="display: none" id="ad_setting_ul">
				<li class="ad_setting_ul_li"><a href="javascript:;"><i
						class="icon-user glyph-icon"></i>个人中心</a></li>
				<li class="ad_setting_ul_li"><a href="javascript:;"><i
						class="icon-cog glyph-icon"></i>设置</a></li>
				<li class="ad_setting_ul_li"><a href="login.jsp"><i
						class="icon-signout glyph-icon"></i> <span class="font-bold">退出</span>
				</a></li>
			</ul>
		</div>
	</div>
	<!--顶部结束-->
	<!--菜单-->
	<div class="left-menu">
		<ul id="menu">
			<li class="menu-list"><a style="cursor:pointer" class="firsta"><i
					class="glyph-icon xlcd"></i>基本管理<s class="sz"></s></a>
				<ul>
					<li><a href="AuthorRegisterSucess.jsp" target="menuFrame"><i
							class="glyph-icon icon-chevron-right1"></i>个人信息</a></li>
				</ul></li>
			<li class="menu-list"><a style="cursor:pointer" class="firsta"><i
					class="glyph-icon xlcd"></i>小说管理<s class="sz"></s></a>
				<ul>
					<li><a href="AuthorAddBook.jsp" target="menuFrame"><i
							class="glyph-icon icon-chevron-right1"></i>发布小说</a></li>
					<li><a href="MangerBookServlet" target="menuFrame">
					<i class="glyph-icon icon-chevron-right2"></i>管理小说</a></li>
				</ul></li>
		</ul>
	</div>

	<!--菜单右边的iframe页面-->
	<div id="right-content" class="right-content">
		<div class="content">
			<div id="page_content">
				<iframe id="menuFrame" name="menuFrame" src="QueryNovel.jsp"
					style="overflow:visible;" scrolling="yes" frameborder="no"
					width="100%" height="100%"></iframe>
			</div>
		</div>
	</div>
</body>

</html>
