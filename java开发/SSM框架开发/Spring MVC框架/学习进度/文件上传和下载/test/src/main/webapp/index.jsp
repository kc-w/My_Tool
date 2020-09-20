<%--
  Created by IntelliJ IDEA.
  User: kc-w
  Date: 2018/4/29
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传</title>
    <script>
      //判断是否填写上传人并已经选择上传文件
      function check() {
          var name=document.getElementById("user").value;
          var file=document.getElementById("file").value;
          if(name==""){
              alert("填写上传人!");
              return false;
          };
          if(file.length==0||file==""){
              alert("请选择上传文件!");
              return false;
          };
          return true;
      }
    </script>
  </head>
  <body>

  <form action="/fileUpload" method="post" enctype="multipart/form-data" onsubmit="return check()">
    上传人:<input id="user" type="text" name="username"/><br>
    请选择文件:<input id="file" type="file" name="filename" multiple="multiple"/><br>
    <input type="submit" value="上传"/>
  </form>
<a href="/download?filename=1.jpg">文件下载</a>
  </body>
</html>
