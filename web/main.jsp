<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2020/6/23
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        html, body {
            background-image: url("images/welcome1.jpg");
            background-size: 100% 100%;
            height: 100%;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            overflow: hidden;
        }
        .welcome {
            position: absolute;
            top: 25%;
            left: 40%;
            right:7.5%;
            border-radius: 5px;
        }
        p{
            font-size: 40px;
            font-family: 华文中宋;

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
</head>
<body>
<div class="welcome" id="wc">
    <p>欢迎使用学生选课系统<br>
        请在左边的菜单栏里进行操作</p>
</div>
</body>
</html>
