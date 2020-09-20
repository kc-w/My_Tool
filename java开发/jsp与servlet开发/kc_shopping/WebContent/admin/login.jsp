<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jstl" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登陆</title>
</head>
<body>
    <form action="j_security_check" method="post">
       <input type="text" name="j_username" value=""/>
       <input type="password" name="j_password" value=""/>
       <input type="submit" value="提交"/>
    </form>
</body>
</html>