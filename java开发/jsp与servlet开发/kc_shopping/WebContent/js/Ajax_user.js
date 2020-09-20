$(document).ready(function(){
	
	//用户注册验证
	$("#txtUserName").blur(function(){
		$.ajax({
			url:"/kc_shopping/servlet_customer/user_number",
			type:"post",
			data: {"number":$("#txtUserName").val()},
			dataType:"json",
			timeout:1000,
			error:function(){
				$(".span1").html("<b class=\"error\">没有获取到数据<b>");
			},
			success:function(text){
				if(text.result=="ok"){
					$(".span1").html("<img src=\"/kc_shopping/image/通过验证.png\">");
				}else{
					$(".span1").html("<b class=\"error\">"+text.result+"</b>");
				}
			}
		})
	});
	$("#password").blur(function(){
		$.ajax({
			url:"/kc_shopping/servlet_customer/user_password",
			type:"post",
			data: {"password":$("#password").val()},
			dataType:"json",
			timeout:1000,
			error:function(){
				$(".span2").html("<b class=\"error\">没有获取到数据<b>");
			},
			success:function(text){
				if(text.result=="rmm"){
					$(".span2").html("");
					$("#m1").css({"background":"green"});
				}else if(text.result=="zmm"){
					$(".span2").html("");
					$("#m1").css({"background":"green"});
					$("#m2").css({"background":"green"});
				}else if(text.result=="qmm"){
					$(".span2").html("");
					$("#m1").css({"background":"green"});
					$("#m2").css({"background":"green"});
					$("#m3").css({"background":"green"});
				}else{
					$("#m1").css({"background":"white"});
					$("#m2").css({"background":"white"});
					$("#m3").css({"background":"white"});
					$(".span2").html("<b class=\"error\">"+text.result+"</b>");
				}
			}
		})
	});
	$("#password1").blur(function(){
		var p=$("#password").val();
		var p1=$("#password1").val();
		if(p==""||p1==""){
			$(".span3").html("<b class=\"error\">密码为空</b>");
		}else if(p!=p1){
			$(".span3").html("<b class=\"error\">密码不一致</b>");
		}else{
			$(".span3").html("<img src=\"/kc_shopping/image/通过验证.png\">");
		}
	});
	$("#txtUseremail").blur(function(){
		$.ajax({
			url:"/kc_shopping/servlet_customer/user_email",
			type:"post",
			data: {"email":$("#txtUseremail").val()},
			dataType:"json",
			timeout:1000,
			error:function(){
				$(".span4").html("<b class=\"error\">没有获取到数据<b>");
			},
			success:function(text){
				if(text.result=="ok"){
					$(".span4").html("<img src=\"/kc_shopping/image/通过验证.png\">");
				}else{
					$(".span4").html("<b class=\"error\">"+text.result+"</b>");
				}
			}
		})
	});
	$("#txtUsertelephone").blur(function(){
		$.ajax({
			url:"/kc_shopping/servlet_customer/user_telephone",
			type:"post",
			data: {"telephone":$("#txtUsertelephone").val()},
			dataType:"json",
			timeout:1000,
			error:function(){
				$(".span5").html("<b class=\"error\">没有获取到数据<b>");
			},
			success:function(text){
				if(text.result=="ok"){
					$(".span5").html("<img src=\"/kc_shopping/image/通过验证.png\">");
				}else{
					$(".span5").html("<b class=\"error\">"+text.result+"</b>");
				}
			}
		})
	});
	


	//表单切换
	$("#z").click(function(){
		$("#login").css({"display":"none"});
		$("div.error").text("");
		$("#registered").css({"display":"block"});
	});
	$("#d").click(function(){
		$("#registered").css({"display":"none"});
		$("div.error").text("");
		$("#login").css({"display":"block"});
	});
})