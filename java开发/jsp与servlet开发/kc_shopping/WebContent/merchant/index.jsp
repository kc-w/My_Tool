<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jstl" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺管理</title>
</head>
<body>
<c:out value="${sessionScope.merchant.getSname()}"></c:out>
<br>
<a href="/kc_shopping/servlet_merchant/allshopping">查看所有商品列表</a>
<br>
<a href="/kc_shopping/merchant/addshop.jsp">增加商品</a>


</body>
</html>