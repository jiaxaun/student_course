<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>选课</title>
    <link rel="stylesheet" href="${basePath}css/styles.css"/>
    <link rel="stylesheet" href="${basePath}css/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <script src="${basePath}js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<c:if test="${param.msg eq 0}"><span style="color: red">操作失败！</span></c:if>
<c:if test="${param.msg eq 1}"><span style="color: green">操作成功！</span></c:if>
<form action="${basePath}sc?method=submit" method="post">
    <table class="table_list">
        <c:forEach items="${courses}" var="course">
            <tr>
                <td width="50px" align="center">
                        <%-- 选中的cId--%>
                    <input type="checkbox"
                    <c:forEach items="${scs}" var="sc">
                           <c:if test="${sc.cId eq course.cId}">checked="checked"</c:if>
                    </c:forEach>
                           name="cId" value="${course.cId}">
                </td>
                <td>
                        ${course.cName}
                </td>
                <td>
                        ${course.teacher.tName}
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <button class="myBtn">
        <i class="fa fa-save"></i>
        提交选课
    </button>
</form>
</body>
</html>
