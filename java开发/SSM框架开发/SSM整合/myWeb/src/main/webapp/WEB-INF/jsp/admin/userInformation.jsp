<%--
  Created by IntelliJ IDEA.
  User: Wkc23
  Date: 2018/6/16
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>

</head>
<body>

<table id="test" class="table table-striped">
    <thead>
    <tr>
        <th>用户id</th>
        <th>用户名称</th>
        <th>用户权限</th>
        <th>用户账号</th>
        <th>用户密码</th>
        <th>用户邮箱</th>
        <th>用户注册时间</th>
    </tr>
    </thead>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.user_id}</td>
            <td>${user.user_name}</td>
            <td>${user.user_level}</td>
            <td>${user.user_number}</td>
            <td>${user.user_password}</td>
            <td>${user.user_email}</td>
            <td>${user.user_register}</td>
        </tr>
    </c:forEach>
</table>





</body>
</html>
