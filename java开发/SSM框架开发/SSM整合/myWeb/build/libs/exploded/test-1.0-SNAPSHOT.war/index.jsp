<%--
  Created by IntelliJ IDEA.
  User: Wkc23
  Date: 2018/6/5
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.wan.date.Time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Kc的个人空间</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="css/jquery-ui.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/userLogin.js"></script>
    <link href="css/all.css" rel="stylesheet"/>
  </head>
<body>
<!-- 模态弹出窗内容:登陆注册 -->
<div class="modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="mymodal-link">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 id="title" class="modal-title">用户登录</h4>
      </div>

      <form id="form1" action="/login" method="post" onsubmit="return form1()">
        <div class="modal-body">
          <div class="form-horizontal">
            <div class="form-group">
              <label class="control-label col-lg-2">账号</label>
              <div class="col-lg-4">
                <input type="text" name="user_number" id="txtUserName1" class="form-control" placeholder="请输入用户名" value=""/>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-lg-2">密码</label>
              <div class="col-lg-4">
                <input type="password" name="user_password" id="txtpassword1" class="form-control" placeholder="请输入密码" value=""/>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">提交</button>
          <button type="button" class="btn btn-default" id="register1">注册一下</button>
        </div>
      </form>


      <form id="form2" action="/register" method="post" onsubmit="return form2()">
        <div class="modal-body">
          <div class="form-horizontal">
            <div class="form-group">
              <label class="control-label col-lg-2">账号</label>
              <div class="col-lg-4">
                <input type="text" name="user_number" id="txtUserName2" class="form-control" placeholder="请输入用户名" value=""/>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-lg-2">密码</label>
              <div class="col-lg-4">
                <input type="password" name="user_password" id="txtpassword2" class="form-control" placeholder="请输入密码" value=""/>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-lg-2">确认密码</label>
              <div class="col-lg-4">
                <input type="password" id="txtpassword3" class="form-control" placeholder="请再次输入密码" value=""/>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-lg-2">邮箱</label>
              <div class="col-lg-4">
                <input type="text" name="user_email" id="txtEmail" class="form-control" placeholder="请输入邮箱进行验证" value=""/>
              </div>
              <button type="button" class="btn btn-primary" id="testEmail">发送验证码</button>
            </div>
            <div class="form-group">
              <label class="control-label col-lg-2">验证码</label>
              <div class="col-lg-4">
                <input type="text" id="test" class="form-control" placeholder="请输入验证码" value=""/>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">注册</button>
          <button type="button" class="btn btn-default" id="register2">已有账号,进行登陆</button>
        </div>
      </form>

    </div>
  </div>
</div>



<div class="container"><!--有其特有边距，不会充斥全屏,将其变为container-full可充斥全屏-->
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
          <li><a href="/writeBlog">写博客</a></li>
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

  <div style="margin: 0 auto;color: red">${msg}</div>

  <div class="row">
    <div   class="panel-group col-lg-2" id="accordion">
      <div class="panel panel-info">
        <div class="panel-heading">
          <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">博客列表</a></h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse"><!--"in"默认打开显示内容-->
          <div class="panel-body"><a href="#">评论过的博客</a></div>
          <div class="panel-body"><a href="/selectBlogList">发表过的博客</a></div>
        </div>
      </div>

      <div style="height: 30px"></div>
      <!--data-interval设置时间-->
      <div id="myCarousel" class="carousel slide" data-ride="carousel" data-wrap="true" data-interval="3000">
        <!-- 设置图片轮播的顺序 -->
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
          <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>
        <!-- 设置轮播图片 -->
        <div class="carousel-inner">
          <div class="item active">
            <a href="##"><img src="image/1.jpg" alt=""></a>
            <div class="carousel-caption">
              <h3></h3>
              <p></p>
            </div>
          </div>
          <div class="item">
            <a href="##"><img src="image/2.jpg" alt=""></a>
            <div class="carousel-caption">
              <h3></h3>
              <p></p>
            </div>
          </div>
          <div class="item">
            <a href="##"><img src="image/3.jpg" alt=""></a>
            <div class="carousel-caption">
              <h3></h3>
              <p></p>
            </div>
          </div>
          <div class="item">
            <a href="##"><img src="image/4.jpg" alt=""></a>
            <div class="carousel-caption">
              <h3>图4</h3>
              <p>描述</p>
            </div>
          </div>
        </div>
        <a class="left carousel-control " href="#myCarousel" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
      </div>


    </div>

    <div class="col-lg-8">


    </div>
  </div>

</div>


<div style="width: 100px;height: 110px" id="draggable" class="ui-widget-content">
  <img style="width: 100px;height: 110px" src="image/付款码.jpg" alt="">
</div>
<div style="width: 1px" id="droppable" class="ui-widget-header">

</div>
</body>
</html>
