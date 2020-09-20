<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jstl" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#add").click(function(){
		$.ajax({
			url:"/kc_shopping/servlet_merchant/addshop",
			type:"post",
			data: {"name":$("#name").val(),"price":$("#price").val(),"kc":$("#kc").val()},
			dataType:"json",
			timeout:1000,
			error:function(){
				alert("错误");
			},
			success:function(text){
					alert(text.result);
			}
		})
	});
})
</script>
<link href="../js/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="../js/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">

<from action="servlet_merchant/allshopping" method="post">
<div class="form-horizontal">
   <div class="form-group">
      <label class="control-label col-lg-3">商品名</label>
      <div class="col-lg-5">
         <input type="text" id="name" name="name" class="form-control" placeholder="只能由数字字母汉字组成" value=""/>
      </div>
   </div>
   <div class="form-group">
      <label class="control-label col-lg-3">价格</label>
      <div class="col-lg-5">
         <input type="text" id="price" name="price" class="form-control" placeholder="只能输入数字" value=""/>
      </div>
   </div>
   <div class="form-group">
      <label class="control-label col-lg-3">库存</label>
      <div class="col-lg-5">
         <input type="text" name="kc" id="kc" class="form-control" placeholder="只能输入整数" value=""/>
      </div>
   </div>
</div>
<div class="col-lg-12 col-lg-push-3">
   <input id="add" type="submit" class="btn btn-default" value="提交">
</div>
</from>

<a href="/kc_shopping/servlet_merchant/allshopping">查看所有商品</a>




</div>
<script src="../js/bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="../js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>