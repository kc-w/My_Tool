<%--
  Created by IntelliJ IDEA.
  User: kc-w
  Date: 2018/4/13
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
  <head>
    <title>JSON交互</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function a() {
            //获取输入的用户名和密码
            var username=$("#username").val();
            var password=$("#password").val();
            $.ajax({
                url : "/testJson",
                type : "post",
                //发送的数据
                data : JSON.stringify({username:username,password:password}),
                //定义发送请求的数据格式为JSON字符串
                contentType : "application/json;charset=UTF-8",
                //定义回调响应的数据格式为JSON字符串,该属性可以省略
                dataType : "json",
                //成功响应的结果
                success : function(data) {
                    if(data!=null){
                        alert("输入的用户名为:"+data.username+" 密码为:"+data.password);
                    }
                }
            });
        }
    </script>
  </head>
  <body>

  <form>
    用户名:<input type="text"  id="username"/>
    <br>
    密码:<input type="password" id="password"/>
    <br>
    <input id="buttom" type="button" value="交互" onclick="a()"/>
  </form>

  </body>
</html>
