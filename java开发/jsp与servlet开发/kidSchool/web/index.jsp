<%--
  Created by IntelliJ IDEA.
  User: Wkc23
  Date: 2018/7/4
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="css/jquery-ui.min.css" rel="stylesheet"/>
    <link href="css/all.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div><h1>社区幼儿学校管理系统</h1></div>
    <div class="row">
    
        <div class="col-lg-4">
    ${requestScope.msg}
        </div>
        
        <form action="/LoginServlet" method="post">
            <div class="form-horizontal col-lg-6">
                <div class="form-group">
                    <label class="control-label col-lg-2">用户名</label>
                    <div class="col-lg-3">
                        <input type="text" name="name" class="form-control" placeholder="请输入用户名" value=""/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2">密码</label>
                    <div class="col-lg-3">
                        <input type="password" name="pw" class="form-control" placeholder="请输入密码" value=""/>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-info">登录</button>
        </form>
        
    </div>

</div>
</body>
</html>