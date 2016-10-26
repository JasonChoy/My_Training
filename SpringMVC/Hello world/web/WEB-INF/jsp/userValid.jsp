<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: cjs
  Date: 2016/9/20
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表单的验证</title>
</head>
<body>
    <form:form action="add" method="post" modelAttribute="user">
        id:<form:input path="id"/><form:errors path="id"/><br>
        name:<form:input path="name"/><form:errors path="name"/><br>
        birth:<form:input path="birth"/><form:errors path="birth"/><br>
        <input type="submit" value="submit">
    </form:form>
</body>
</html>
