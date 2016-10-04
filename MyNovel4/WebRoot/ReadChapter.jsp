<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="./jquery/jquery-1.3.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>章节阅读</title>
<style type="text/css">
		body{
			background: url(images/bg-Pic3.jpg);
			color:white;
		}
		a{color:white;text-decoration:none;}#b{margin-left:80%;}
		button{width:100px;height:20px;border-radius:25px;}
		#p{font-size:14px}
		</style>


</head>

<body>
	<h1 id="h">${chapterName }</h1>
	<p id="p">${chapterContent }</p>
	
	<a id="b" href="ReadNovel.jsp">返回章节目录</a><br /><br />
	
	<p>以下是关于缓存的操作：</p>
	<button type="button" onclick="sub();">缓存小说</button>	
	<button type="button" onclick="del();">删除小说</button>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a class="d" href="huancun.html">进入缓存界面</a>
   <script type="text/javascript">
	
function sub()
{
 $.ajax({
   type:"post",//请求方式
   dataType:"json",
  	url:"./Query",//发送请求地址
   //请求成功后的回调函数有两个参数
   success:function(data,textStatus){
    add(data);
   },
   error:function(XMLHttpRequest, textStatus, errorThrown){
   	console.log(errorThrown);
   }
   });
}
//打开数据库
var dbName = '小说数据库', storeName = '道果', dbVersion = 1;
    function openDB(callback){  
    if(openDB.db){  
        callback(openDB.db);  
        return;  
    }  
    var openRequest = indexedDB.open(dbName, dbVersion);  
   openRequest.onupgradeneeded = function(e) {
						var db = e.target.result;
						console.log("running onupgradeneeded--数据库发生了改变")
						if(!db.objectStoreNames.contains(storeName)) {  
            				var store = db.createObjectStore(storeName, {keyPath:"id", autoIncrement: true});  
            				//创建索引  
          					  store.createIndex("章", "章节", {unique: false});  
            					console.log("没有数据库，创建了新的数据库");
       						 }  
    	
						var thisDb = e.target.result;
						console.log(thisDb.version);
					}
					 openRequest.onsuccess = function(e) {  
        console.log('打开数据库操作成功');  
        if(callback){  
            openDB.db = e.target.result;  
            callback(e.target.result);  
        }  
    };  
}  


//添加操作
function add(data) {
				openDB(function(db) {
					var transaction = db.transaction(storeName, "readwrite");
					transaction.oncomplete = function(event) {
						console.log("transction complete!");
					};
					
					var store = transaction.objectStore(storeName);
					var nov = '{"章节":' + '"' + data.charptName + '"' + ',' + '"内容":' + '"' + data.inner + '"' + '}';
					var novel = JSON.parse(nov);
					console.log(novel);
					var objectStore = transaction.objectStore(storeName); //得到objectStore兑现
					objectStore.add(novel);
				});
			}
//删除操作
 			function del (){  
    				indexedDB.deleteDatabase(dbName);  
					}; 
  
  </script>
</body>
</html>
