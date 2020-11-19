<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2020/6/23
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>老师的课程</title>
    <link rel="stylesheet" href="${basePath}css/styles.css"/>
    <link rel="stylesheet" href="${basePath}css/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <script src="${basePath}js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<table class="table_list">
    <thead>
    <tr>
        <th>ID</th>
        <th>学生姓名</th>
        <th>学号</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="student">
        <tr>
            <td>${student.stuId}</td>
            <td>${student.stuName}</td>
            <td>${student.stuNo}</td>
        </tr>
    </c:forEach>
</table>
<%--<%@include file="../inc/page.jsp"%>--%>
</body>
</html>
