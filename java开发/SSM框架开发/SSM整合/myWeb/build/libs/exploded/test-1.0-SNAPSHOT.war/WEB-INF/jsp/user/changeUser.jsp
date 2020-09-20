<%--
  Created by IntelliJ IDEA.
  User: Wkc23
  Date: 2018/7/2
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="css/jquery-ui.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/userInfoChange.js"></script>
</head>
<body>
<div class="container">

    <div class="modal panel-footer" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">信息修改</h4>
                </div>

                <form id="form1" action="/changeUser" enctype="multipart/form-data" method="post" onsubmit="return userInfoChange()">
                    <div class="modal-body">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-lg-2">头像</label>
                                <div class="col-lg-4">
                                    选择要上传的头像<input type="file" name="fileList" multiple="multiple"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-2">昵称</label>
                                <div class="col-lg-4">
                                    <input type="password" name="user_name" id="name" class="form-control" placeholder="请输入昵称" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-2">邮箱</label>
                                <div class="col-lg-4">
                                    <input type="text" name="user_email" id="email" class="form-control" placeholder="请输入邮箱" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-2">密码</label>
                                <div class="col-lg-4">
                                    <input type="text" name="user_password" id="psssword" class="form-control" placeholder="请输入密码" value=""/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">提交</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div class="row"><!--行标签-->
        <div class="navbar navbar-inverse">
            <div class="navbar-header">
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <b><a href="/index.jsp" class="navbar-brand">Kc</a></b>
            </div>
            <div class="collapse navbar-collapse navbar-responsive-collapse">

                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" class="form-control input-sm" placeholder="搜索"/>
                    </div>
                    <button type="button" class="btn btn-info btn-sm ">搜文章</button>
                </form>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">视频</a></li>
                    <li><a href="#">图片</a></li>
                    <li><a href="#">写博客</a></li>
                    <c:if test="${sessionScope.user==null}">
                        <li><a data-toggle="modal" href="#mymodal-link">登录/注册</a></li>
                    </c:if>
                    <c:if test="${sessionScope.user!=null}">
                        <li><a href="/selectSelf">${sessionScope.user.user_name}</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-lg-8">
            <div class="panel panel-info">
                <div class="panel-heading">个人信息</div>
                <div class="panel-body">
                    <table class="table table-bordered table-hover">
                        <tr>
                            <td>头像</td>
                            <td>昵称</td>
                            <td>账号</td>
                            <td>级别</td>
                            <td>邮箱</td>
                            <td>注册时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><img style="width: 100px;height: 110px" src="image/${sessionScope.user.user_image}" alt="没有头像,上传一个吧"></td>
                            <td>${sessionScope.user.user_name}</td>
                            <td>${sessionScope.user.user_number}</td>
                            <td>${sessionScope.user.user_level}</td>
                            <td>${sessionScope.user.user_email}</td>
                            <td>${sessionScope.user.user_register}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="panel-footer"><button class="btn btn-primary" type="button" data-toggle="modal" data-target="#mymodal">更改信息</button></div>

            </div>
        </div>

        <div class="col-lg-5">

        </div>
        <div class="col-lg-2">

        </div>

    </div>

</div>
</body>
</html>
