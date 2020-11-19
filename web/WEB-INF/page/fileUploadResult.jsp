<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>文件上传结果页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->

</head>

<body>

<h2>文件上传结果</h2>
<%
    String result = (String) request.getAttribute("fileUploadError");
    if (null == result || "".equals(result)) {
        out.println("【恭喜】文件上传成功！");
    } else {
        out.println("很抱歉，文件上传失败！失败原因：" + result);
    }
%>
<h2><font color="#a52a2a" size="4">图片显示</font></h2>

<img src="img/${newFileName}" width="200px" height="180px">
<br>
<h4><font color="#a52a2a" size="4">文件名: ${newFileName}</font></h4>
<h2><a href="sc?method=upload">返回上传页面</a></h2>
</body>
</html>
