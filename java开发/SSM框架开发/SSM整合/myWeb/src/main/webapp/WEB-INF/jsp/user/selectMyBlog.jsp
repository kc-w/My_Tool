<%--
  Created by IntelliJ IDEA.
  User: Wkc23
  Date: 2018/7/2
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>博客记录</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="css/jquery-ui.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

<div class="panel panel-danger">
    <div class="panel-heading">已发表的文章列表</div>
    <div class="panel-body">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>文章标题</th>
                <th>发表时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${blogs}" var="blog" >
            <tr>
                <td>${blog.blog_title}</td>
                <td>${blog.blog_time}</td>
                <td><a class="btn btn-default" href="/selectBlog?id=${blog.blog_id}" role="button">查看</a><a class="btn btn-default" href="/deleteBlog?id=${blog.blog_id}" role="button">删除</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="panel-footer"></div>
</div>




</div>
</body>
</html>
