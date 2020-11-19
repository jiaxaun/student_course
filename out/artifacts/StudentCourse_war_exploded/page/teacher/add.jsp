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
                    tName:{required:true},
                    userName:"required",
                    pwd:{
                        required:true,
                        rangelength:[6,15]
                    }
                },messages:{
                    tName:{required:"姓名不能为空！"},
                    userName:"账号不能为空！",
                    pwd:{
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
    <form id="addForm" action="${basePath}teacher?method=add" method="post">
    <table class="table_add">
        <tr>
            <td>姓名</td>
            <td style="color: red;font-style:italic"><input type="text" name="tName"></td>
        </tr>
        <tr>
            <td>账号</td>
            <td style="color: red;font-style:italic"><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td style="color: red;font-style:italic"><input type="password" name="pwd" value="123456">
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
