<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/3/23
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统主页</title>
</head>
当前用户：${u_session.username}
<a href="${pageContext.request.contextPath}/logout">退出登录</a>
</body>
</html>