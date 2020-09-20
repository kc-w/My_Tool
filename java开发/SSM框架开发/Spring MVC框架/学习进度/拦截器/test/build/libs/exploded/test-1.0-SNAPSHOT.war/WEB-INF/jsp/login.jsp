<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/3/23
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
${msg}
<form action="${pageContext.request.contextPath}/login" method="post">
    用户名：<input type="text" name="username"/>
    密码：<input type="password" name="password"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
