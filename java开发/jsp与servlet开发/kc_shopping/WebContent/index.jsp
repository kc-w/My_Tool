<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jstl" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到我的商城</title>
</head>
<body>
<c:out value="${sessionScope.user.getUname()}" default=""/>
<a href="merchant/login.jsp">商家登陆</a>
<a href="login.jsp">用户登陆</a>
<a href="#">购物车</a>
<p>商品浏览</p>
</body>
</html>