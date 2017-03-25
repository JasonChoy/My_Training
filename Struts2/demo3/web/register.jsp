
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/25
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: center">
<s:form action="register_test">//服务端验证
    <s:textfield name="username" id="username" label="username"></s:textfield>
    <s:password name="password" id="password" label="password"></s:password>
    <s:password name="repassword" id="repassword" label="repassword"></s:password>
    <s:textfield name="age" id="age" label="age"></s:textfield>
    <s:textfield name="birthday" id="birthday" label="birthday"></s:textfield>
    <s:submit></s:submit>
    <s:reset></s:reset>
</s:form>

<s:form action="register" validate="true">//客户端验证  validate="true"(关键点)
    <s:textfield name="users" label="users" id="users"></s:textfield>
    <s:submit></s:submit>
</s:form>
</body>
</html>
