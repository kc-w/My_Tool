<%--
  Created by IntelliJ IDEA.
  User: kc-w
  Date: 2018/4/10
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
${msg}
${pageContext.request.contextPath}
<form action="${pageContext.request.contextPath}/register" method="post">
    用户名:<input type="text" name="username"/><br>
    密码:<input type="password" name="password"/><br>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
