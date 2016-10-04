$(document).ready(function(){
		$(".xinxi").hover(function(){
			$(this).children(".caozuo").show(200,function(){
				$("this").children("li a").css({"color":"black","text-decoration":"underline"});
			});
		},function(){
			$(this).children(".caozuo").hide(200);
		});
		
		$(".bianqian_li").click(function(){
			$(this).children(".bianqian_li_ul").show(200,function(){
				$("this").children("li a").css("display","blocak");
			});
		})
		$(".bq").hover(function(){
			$(this).css("background","#008000");
		},function(){
			$(this).css("background","white");
		})
});

