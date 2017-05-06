<%--
  Created by IntelliJ IDEA.
  User: cjs
  Date: 2017/5/5
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入装修标签库-->
<%@ taglib prefix="f" uri="/iMallTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="webRoot"></c:set>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${webRoot}/static/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<a href="/index.jsp?frameEdit=Y">开启装修模式</a>
<div class="frameEdit" frameInfo="AAA_BBB" style="width: 300px;height: 300px;background-color: #00cc05;"></div>
<f:FrameEditTag/><%--页面装修专用标签--%>
</body>
</html>
