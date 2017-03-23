<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/23
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<s:debug/>
<table border="1">
    <tr>
        <th>用户id</th>
        <th>用户名</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>
    <s:iterator value="users" var="user">
    <tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.age}</td>
        <td>
            <a href="/User_del?id=${user.id}">删除</a>
            <a href="/User_edit?id=${user.id}">修改</a>
        </td>
    </tr>
    </s:iterator>
</table>
</body>
</html>
