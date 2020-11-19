<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2020/6/20
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增</title>
    <link rel="stylesheet" href="${basePath}css/styles.css" />
    <link rel="stylesheet" href="${basePath}css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script src="${basePath}js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <script src="${basePath}js/jquery-validation-1.19.1/jquery.validate.js" type="text/javascript"></script>
    <script src="${basePath}js/jquery-validation-1.19.1/localization/messages_zh.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("#addForm").validate({
                rules:{
                    stuNo:{
                        required:true,
                        digits:true},
                    stuName:"required",
                    stuPwd:{
                        required:true,
                        rangelength:[6,15]
                    }
                },messages:{
                    stuNo:{
                        required:"学号不能为空！",
                        digits:"只能输入数字！"
                    },
                    stuName:"姓名不能为空！",
                    stuPwd:{
                        required:"密码不能为空！",
                        rangelength:"请输入长度在 6 到 15 之间的字符串 "
                    }
                }
            });
        });
    </script>
</head>
<body>
<div class="add">
    <form id="addForm" action="${basePath}student?method=add" method="post">
    <table class="table_add">
        <tr>
            <td>学号</td>
            <td style="color: red;font-style:italic"><input type="text" name="stuNo"></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td style="color: red;font-style:italic"><input type="text" name="stuName"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td style="color: red;font-style:italic"><input type="password" name="stuPwd" value="123456">
                初始密码为123456
            </td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <button class="back" type="button" onclick="window.history.back(-1);">
                    <i class="fa fa-arrow-left"></i>
                    返回
                </button>
                <button class="submit" type="submit">
                    <i class="fa fa-save"></i>
                    提交
                </button>
            </td>
        </tr>

    </table>
    </form>
</div>
</body>
</html>
