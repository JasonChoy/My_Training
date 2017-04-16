<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/14
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="webRoot" />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="myform" action="${webRoot}/user/saveUser.ac" method="get">
        name:<input name="name"/></br>
        <input type="submit">
    </form>
</body>
</html>
