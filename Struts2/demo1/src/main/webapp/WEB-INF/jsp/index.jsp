<%--
  Created by IntelliJ IDEA.
  User: cjs
  Date: 2017/3/22
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input name="user.userName"/></td>
        </tr>
        <tr>
            <td>年龄:</td>
            <td><input name="user.age" type="number"/></td>
        <tr>
            <td>备注:</td>
            <td><textarea name="mark" style="height: 190px;"></textarea></td>
        </tr>
    </table>
    <input type="submit"/>
</form>

</body>
</html>
