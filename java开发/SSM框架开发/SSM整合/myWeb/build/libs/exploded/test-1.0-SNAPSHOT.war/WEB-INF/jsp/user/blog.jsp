<%--
  Created by IntelliJ IDEA.
  User: Wkc23
  Date: 2018/7/4
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${blog.blog_title}</title>
</head>
<body>
<h3 style="margin: 0 auto">标题</h3>
<h4 style="margin: 0 auto">${blog.blog_title}</h4>
<h4 style="margin: 0 auto">发表日期${blog.blog_time}</h4>
<h3 style="margin: 0 auto">内容</h3>
<h4 style="margin: 0 auto">${blog.blog_body}</h4>
</body>
</html>
