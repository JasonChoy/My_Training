<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table width="100%" border=1>
    <tr>
        <td>id</td>
        <td>用户名</td>
        <td>性别</td>
        <td>出生日期</td>
        <td>地址</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${userList}" var="user">
    <tr>
        <td><input name="items_id" type="checkbox" value="${user.id}"/></td>
        <td>${user.username}</td>
        <td>${user.sex}</td>
        <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${user.address}</td>
        <td><a href="${pageContext.request.contextPath}/items/editItem.action?id=${item.id}">修改</a></td>
    </tr>
    </c:forEach>
</body>
</html>
