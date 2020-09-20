<%--
  Created by IntelliJ IDEA.
  User: kc-w
  Date: 2018/4/11
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单</title>
</head>
<body>
${order.orderId}<br>${order.user.username}
<form action="/order" method="post">
    订单编号:<input type="text" name="orderId"/><br>
    所属用户:<input type="text" name="user.username"/><br>
    <input type="submit" value="提交"/>
</form>
<br>自定义格式转化
<form action="/mydate" method="post">
    时间:<input type="text" name="date"/><br>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
