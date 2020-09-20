<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jstl" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家登陆</title>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../js/Ajax_merchant.js"></script>
<link href="../js/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="../js/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
<style type="text/css">
.error{
   color: red;
}
</style>
</head>
<body>
<div class="container">

<div class="container col-lg-7"></div>

<div id="login_registered" class="container  col-lg-5">
   <div class="row">
      <div id="z" class="btn btn-default col-lg-6">注册</div>
      <div id="d" class="btn btn-default col-lg-6">登陆</div>
   </div>
   <div class="row">
	   <div class="error">
	      <c:out value="${sessionScope.message}" default=""/>
	      <c:remove var="message" scope="session"/>
	   </div>
   </div>
   <div class="row" id="login">
	   <form action="../servlet_merchant/merchant_login" method="post">
	        <input type="hidden" name="mark" value="login">
			<div class="form-horizontal">
			   <div class="form-group">
			      <label class="control-label col-lg-3">账号</label>
			      <div class="col-lg-5">
			         <input type="text" name="number" class="form-control" placeholder="请输入用户名" value=""/>
			      </div>
			   </div>
			   <div class="form-group">
			      <label class="control-label col-lg-3">密码</label>
			      <div class="col-lg-5">
			         <input type="password" name="password" class="form-control" placeholder="请输入密码" value=""/>
			      </div>
			   </div>
			   <div class="form-group">
			      <label class="control-label col-lg-3">验证码</label>
			      <div class="col-lg-5">
			         <input type="text" name="yzm" id="yzm" class="form-control" placeholder="请输入验证码" value=""/>
			      </div>
			      <a href="javascript:changImg()"><img id="servletImg1" src="/kc_shopping/image/image_servlet" /></a>
			   </div>
			</div>
			<div class="col-lg-12 col-lg-push-3">
			   <input id="denglu" type="submit" class="btn btn-default" value="提交">
			</div>
	   </form>
   </div>


   <div style="display: none;" id="registered">
	   <form  action="../servlet_merchant/merchant_login" method="post">
	       <input type="hidden" name="mark" value="zhuce">
			<div class="form-horizontal">
			   <div class="form-group">
			      <span class="error">*</span><label class="control-label col-lg-3">账号</label>
			      <div class="col-lg-5">
			         <input type="text" name="number" id="txtUserName" class="form-control" placeholder="请输入用户名" value=""/>
			      </div>
			      <span class="span1"></span>
			   </div>
			   <div class="form-group">
			      <span class="error">*</span><label class="control-label col-lg-3">密码</label>
			      <div class="col-lg-5">
			         <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" value=""/>
			         <div id="m1" class="col-lg-1">弱</div><div id="m2" class="col-lg-1">中</div><div id="m3" class="col-lg-1">强</div>
			      </div>
			      <span class="span2"></span>
			   </div>
			   <div class="form-group">
			      <span class="error">*</span><label class="control-label col-lg-3">确认密码</label>
			      <div class="col-lg-5">
			         <input type="password" id="password1" class="form-control" placeholder="请再次输入密码" value=""/>
			      </div>
			      <span class="span3"></span>
			   </div>
			   <div class="form-group">
			      <span class="error">*</span><label class="control-label col-lg-3">电子邮箱</label>
			      <div class="col-lg-5">
			         <input type="text" name="email" id="txtUseremail" class="form-control" placeholder="请输入邮箱" value=""/>        
			      </div>
			      <span class="span4"></span>
			   </div>
			   <div class="form-group">
			      <span class="error">*</span><label class="control-label col-lg-3">手机号码</label>
			      <div class="col-lg-5">
			         <input type="text" name="telephone" id="txtUsertelephone" class="form-control" placeholder="请输入手机号码" value=""/>
			      </div>
			      <span class="span5"></span>
			   </div>
			   <div class="form-group">
			      <span class="error">*</span><label class="control-label col-lg-3">验证码</label>
			      <div class="col-lg-5">
			         <input type="text" name="yzm" id="yzm" class="form-control" placeholder="请输入验证码" value=""/>
			      </div>
			      <span class="span6"></span>
			      <a href="javascript:changImg()"><img id="servletImg2" src="/kc_shopping/image/image_servlet" /></a>
			   </div>
			</div>
			
			<div class="col-lg-12 col-lg-push-3">
			    <input id="zhuce" type="submit" class="btn btn-default" value="提交">
			</div>
	   </form>
   </div>
</div>


</div>


 <script type="text/javascript">
	  function changImg(){
	   var img1 = document.getElementById("servletImg1");
	   var img2 = document.getElementById("servletImg2");
	   var d = new Date();
	   var time = d.getTime();//如果没有这个
	   //下面这一句不会起作用，因为浏览器的缓存技术，图片并不会刷新
	   //img.src="/myHelloWeb/servlet/ImageServlet";
	   img1.src="/kc_shopping/image/image_servlet?"+time;
	   img2.src="/kc_shopping/image/image_servlet?"+time;
	   //?号后面的东西是通过get方式传递的
	  }
 </script>


<script src="..js/bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="..js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>