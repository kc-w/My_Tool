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
    <script src="../js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src=",,/js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="../css/jquery-ui.min.css" rel="stylesheet"/>
    <link href="../css/all.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
用户:${sessionScope.user.name}
<div class="panel panel-danger">
    <div class="panel-heading">课程信息列表</div>
    <div class="panel-body">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>课程编号</th>
                <th>幼儿编号</th>
                <th>教师编号</th>
                <th>上课时间</th>
                <th>上课内容</th>
                <th>上课效果</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.teachingList}" var="teaching">
                <tr>
                    <td>${teaching.id}</td>
                    <td>${teaching.kidId}</td>
                    <td>${teaching.teacherId}</td>
                    <td>${teaching.teachDate}</td>
                    <td>${teaching.content}</td>
                    <td>${teaching.effect}</td>
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