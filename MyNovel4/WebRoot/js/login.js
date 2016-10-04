function show(){
				var name = $("#test1").val();
//				console.log(name);
				var div1= $("#_alert");
				if(name == ""){
					div1.html("<font style='font-size: 2;color: red;'>用户名不能为空</font>");
				}else if(name.length<6){
					div1.innerHTML = "<font style='font-size: 2;color: indianred;'>用户名不能少于六位</font>"
				}else {
					div1.innerHTML = "<font style='font-size: 2;color: greenyellow;'>恭喜可以注册</font>"
				}
				
			}
			
			function ok(){
				
				var pas1 = document.getElementById("pas1").value;
				var pas2= document.getElementById("pas2").value;
				var div1= document.getElementById("div1");
				if(pas1 == ""){
					div1.innerHTML = "<font style='font-size: 2;color: red;'>密碼不能为空</font>"
				}else if(pas1 != pas2){
					div1.innerHTML = "<font style='font-size: 2;color: indianred;'>兩次密碼不相同</font>"
				}else {
					div1.innerHTML = "<font style='font-size: 2;color: greenyellow;'>恭喜可以注册</font>"
				}
				
			}