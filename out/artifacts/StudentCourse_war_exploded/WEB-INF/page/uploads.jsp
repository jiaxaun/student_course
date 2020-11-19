<%--
  Created by IntelliJ IDEA.
  User: Liang
  Date: 2020/10/5
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>多文件上传</title>
</head>
<body>

<h2>多文件上传</h2>
<form action="uploadFiles" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"/><br/>
    <input type="file" name="attachs" required="required"/><br/>
    <input type="file" name="attachs" required="required"/><br/>
    <input type="submit">
</form>

</body>
</html>
