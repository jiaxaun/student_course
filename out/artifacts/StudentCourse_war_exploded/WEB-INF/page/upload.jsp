<%--
  Created by IntelliJ IDEA.
  User: Liang
  Date: 2020/10/5
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传头像</title>
</head>
<body>
<a href="test" id="wc">点我有惊喜！</a>
<br/>
<a href="test1?username=lsc">用户名</a>
<form action="test2" method="post">
    用户名:<input type="text" value="" name="username"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="提交">
</form>

<h1>上传头像</h1>

<h2>一、单文件上传</h2>
<form action="uploadFile" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username" /><br />
    <input type="file" name="attach" accept="image/*"/><br />
    <input type="submit" value="上传">
</form>

<style>
    #wc {
        border-radius: 5px;
    }
</style>
<script language="javascript">
  function changeColor(){
    var color="#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray";
    color=color.split("|");
    document.getElementById("wc").style.color=color[parseInt(Math.random() * color.length)];
  }
  setInterval("changeColor()",200);
</script>
</body>
</html>
