<%--
  Created by IntelliJ IDEA.
  User: kc-w
  Date: 2018/4/13
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>复杂数据绑定</title>
</head>
<body>
<h1>绑定数组</h1>
${msg}
<form action="/deleteuser" method="post">
    <table width="20%" border="1">
        <tr>
            <td>选择</td>
            <td>用户名</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="id" value="莉可"></td>
            <td>莉可</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="id" value="朵拉"></td>
            <td>朵拉</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="id" value="海马"></td>
            <td>海马</td>
        </tr>
    </table>
    <input type="submit" value="删除">
</form>
<br>
<form action="/users" method="post">
    <table width="30%" border="1">
        <tr>
            <td>选择</td>
            <td>用户名</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="users[0].id" value="1"></td>
            <td><input type="text" name="users[0].username" value="tom"></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="users[1].id" value="2"></td>
            <td><input type="text" name="users[1].username" value="tom"></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="users[2].id" value="3"></td>
            <td><input type="text" name="users[2].username" value="tom"></td>
        </tr>
    </table>
    <input type="submit" value="提交">
</form>
${m}
</body>
</html>
