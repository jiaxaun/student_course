<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2020/6/16
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            font-family: "Microsoft YaHei UI Light";
        }

        html, body {
            background-image: url("/images/demo-bg.jpg");
            background-size: 100% 100%;
            height: 100%;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .login {
            position: absolute;
            background-color: rgba(255, 255, 255, .8);
            top: 25%;
            left: 70%;
            right: 5%;
            bottom: 10%;
            border-radius: 5px;
        }

        .title, .u, .p, .c, .l, .s, .tips {
            position: absolute;
            width: 100%;
        }

        .uname,.pwd {
            height: 40px;
            border: 0;
            border-radius: 5px;
            width: 80%;
            padding-left: 35px;
            box-sizing: border-box;
            font-size: 16px;
            font-weight: bold;
        }
        .code{
            height: 40px;
            border: 0;
            border-radius: 5px;
            width: 50%;
            box-sizing: border-box;
            font-size: 15px;
            text-indent:20px;
            letter-spacing:1px;
            position: absolute;
        }

        .uname {
            background-image: url("images/login_user.png");
            background-size: 18px 18px;
            background-repeat: no-repeat;
            background-position: 8px 8px;
            background-color: #f2f2fa;
        }

        .pwd {
            background-image: url("images/login_pwd.png");
            background-size: 18px 18px;
            background-repeat: no-repeat;
            background-position: 8px 8px;
            background-color: #f2f2fa;
        }

        .btn {
            background-color: #467FE6;
            height: 35px;
            width: 40%;
            border: 0;
            border-radius: 8px;
            color: #fff;
            font-size: 16px;
        }
        .reg {
            background-color: #467FE6;
            height: 35px;
            width: 40%;
            border: 0;
            border-radius: 8px;
            color: #fff;
            font-size: 16px;
        }

        select {
            width: 80%;
            height: 35px;
            border-radius: 4px;
            border: 1px solid #e1e1e1;
            font-weight: bold;
            line-height: 35px;
            padding-left: 5px;
        }

        .title {
            top: 3%;
            bottom: 80%;
            text-align: center;
            font-size: 40px;
            font-weight: bold;
            padding-top: 10px;
            box-sizing: border-box;
            font-family: 华文彩云,"Microsoft YaHei UI",华文宋体;
        }

        .u {
            top: 20%;
            bottom: 60%;
            left: 10%;
        }

        .p {
            top: 35%;
            bottom: 40%;
            left: 10%;
        }
        .c{
            top:48%;
            bottom: 41%;
            left: 10%;
        }

        .s {
            top: 60%;
            bottom: 25%;
            left: 10%;
        }

        .l {
            top: 70%;
            bottom: 20%;
            left: 10%;
        }

        .tips {
            top: 85%;
            font-size: 14px;
            color: red;
            text-align: center;
        }
        img{
            position: absolute;
            margin-left: 52%;
        }
    </style>
    <link rel="stylesheet" href="${basePath}css/styles.css" />
    <link rel="stylesheet" href="${basePath}css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script src="${basePath}js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("imageCode").onclick=function () {
                this.src = "validatecode.jsp?"+Math.random();
            }
        }
    </script>
</head>
<body>
<div class="login">
    <div class="title">
        学生选课系统
    </div>
    <form action="login" method="post">
        <div class="u">
            <input type="text" class="uname" name="userName" value="2020001">
        </div>
        <div class="p">
            <input type="password" class="pwd" name="password" value="123">
        </div>
        <div class="c">
            <input type="text"  class="code"  name="vcode" placeholder="请输入验证码" />
            <img id="imageCode" src="validatecode.jsp">
        </div>
        <div class="s">
            <select name="type" id="type">
                <option value="">请选择登录类型</option>
                <option selected="selected" value="0">学生</option>
                <option value="1">老师</option>
                <option value="2">管理员</option>
            </select>
        </div>
        <div class="l">
            <input type="submit" value="登录" class="btn">
            <button type="button" class="reg">注册</button>
        </div>
    </form>
    <div class="tips">
        ${error}
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $(".l .reg").click(function () {
            if ($("#type").val() == "0") {
                //学生注册
                window.location.href = "page/reg/stuRegister.jsp";
            } else if ($("#type").val() == "1") {
                //老师注册
                window.location.href = "page/reg/tRegister.jsp";
            } else {
                //管理员登录验证
                window.location.href = "page/reg/register.jsp";
            }
        });
    })
</script>
</body>
</html>
