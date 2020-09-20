<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jstl" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>

<link href="../js/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="../js/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">

<a href="addshop.jsp">添加商品</a>


<table class="table table-hover">
   <thead>
      <tr>
         <th>商品名</th> 
         <th>价格</th> 
         <th>库存</th>
         <th>上架时间</th> 
      </tr>
   </thead>
<c:forEach items="${sessionScope.all}" var="sp">
   <tr>
         <td>${sp.getPname()}</td>
         <td>${sp.getPprice()}</td>
         <td>${sp.getPstock()}</td>
         <td>${sp.getPtime()}</td>
   </tr>
</c:forEach>
</table>



</div>
<script src="../js/bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="../js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>