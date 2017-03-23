<%--
  Created by IntelliJ IDEA.
  User: cjs
  Date: 2017/3/22
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--struts 利用属性驱动和模型驱动封装数据 在action层和页面传输是双向的--%>
用户:${user.userName}<t/>
年龄:${user.age}<t/>
备注:${mark}
登陆成功!!!
</body>
</html>
