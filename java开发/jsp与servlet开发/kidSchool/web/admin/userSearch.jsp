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
    <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/change.js" type="text/javascript"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="../css/jquery-ui.min.css" rel="stylesheet"/>
    <link href="../css/all.css" rel="stylesheet"/>
</head>
<body>
<div class="modal" id="b" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/UserServlet" method="post">
            <input type="hidden" name="mark" value="change"/>
            <input type="hidden" name="changeId" id="changeId" value=""/>
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">信息更改</h4>
                </div>
                <div class="modal-body">
                    
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-lg-3">用户权限</label>
                            <div class="col-lg-5">
                                <input type="text" name="role" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-3">用户姓名</label>
                            <div class="col-lg-5">
                                <input type="text" name="name" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-3">用户密码</label>
                            <div class="col-lg-5">
                                <input type="password" name="pwd" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-3">用户电话</label>
                            <div class="col-lg-5">
                                <input type="text" name="tel" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-3">用户地址</label>
                            <div class="col-lg-5">
                                <input type="text" name="address" class="form-control"/>
                            </div>
                        </div>
                    </div>
                
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="modal" id="a" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/UserServlet" method="post">
            <input type="hidden" name="mark" value="add"/>
            <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">信息增加</h4>
            </div>
            <div class="modal-body">
                
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-lg-3">用户权限</label>
                        <div class="col-lg-5">
                            <input type="text" name="role" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">用户姓名</label>
                        <div class="col-lg-5">
                            <input type="text" name="name" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">用户密码</label>
                        <div class="col-lg-5">
                            <input type="password" name="pwd" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">用户电话</label>
                        <div class="col-lg-5">
                            <input type="text" name="tel" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">用户地址</label>
                        <div class="col-lg-5">
                            <input type="text" name="address" class="form-control"/>
                        </div>
                    </div>
                </div>
            
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        </form>
    </div>
</div>
<div class="container">
    <ol class="nav nav-tabs nav-justified">
        <li><a href="/KidServlet">查看所有幼儿列表</a></li>
        <li><a href="/TeacherServlet">查看所有教师列表</a></li>
        <li><a href="/TeachingServlet">查看所有课程信息列表</a></li>
        <li><a href="/UserServlet">查看所有用户列表</a></li>
    </ol>
    管理员:${sessionScope.user.name}
    <button type="button" data-toggle="modal" data-target="#a" class="btn btn-info">增加</button>
    <div class="panel panel-danger">
        <div class="panel-heading">用户列表</div>
        <div class="panel-body">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>用户编号</th>
                    <th>用户权限类型</th>
                    <th>用户名</th>
                    <th>用户密码</th>
                    <th>用户电话</th>
                    <th>用户地址</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.userList}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.role}</td>
                    <td>${u.name}</td>
                    <td>${u.pwd}</td>
                    <td>${u.tel}</td>
                    <td>${u.address}</td>
                    <td>
                        <button data-target="#b" class="btn btn-warning" id="change" value="${u.id}">修改</button>
                        <button class="btn btn-danger"><a href="/UserServlet?mark=delete&id=${u.id}">删除</a></button>
                    </td>
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