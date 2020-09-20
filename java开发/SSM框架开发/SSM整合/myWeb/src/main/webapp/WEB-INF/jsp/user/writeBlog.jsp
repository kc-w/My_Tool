<%--
  Created by IntelliJ IDEA.
  User: Wkc23
  Date: 2018/7/2
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>写博客</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="css/jquery-ui.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/uploadBlog.js"></script>
</head>
<body>
<div class="container">
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
                    <li><a href="/selectSelf">${sessionScope.user.user_name}</a></li>
                </ul>
            </div>
        </div>
    </div>


    <div class="panel panel-default">
        <div class="panel-heading"></div>
        <div class="panel-body">

            <div style="margin: 0 auto">
                <label>标题</label>
            </div>
            <div>
                <input type="text" id="title" class="form-control" placeholder="标题栏">
            </div>

            <div style="margin: 0 auto">
                <label>正文内容</label>
            </div>
            <div>
                <textarea id="body" rows="20" cols="150" value="限1000字内"></textarea>
            </div>




        </div>
        <div id="submit" class="panel-footer"><button type="button" class="">提交</button></div>
    </div>





</div>
</body>
</html>
