<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/23
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<s:debug></s:debug>
    <form action="/User_update">
        id:<input name="user.id" value="${model.id}"/><br/>
        用户名:<input name="user.userName" value="${model.userName}"/><br/>
        年龄:<input name="user.age" value="${model.age}"/><br/>
        <input type="submit">
    </form>
</body>
</html>
