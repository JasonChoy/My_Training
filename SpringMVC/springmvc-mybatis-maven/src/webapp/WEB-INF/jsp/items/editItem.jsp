<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/items/updateItem.action" method="post">
    <table>
        <input id="id" name="id" value="${item.id}" style="display: none">
        <tr><td>商品名称:<input id="name" name="name" value="${item.name}"></td></tr>
        <tr><td>商品价格:<input id="price" name="price" value="${item.price}"></td></tr>
        <tr><td>生产日期:<input id="createtime" name="createtime" value="<fmt:formatDate value="${item.createtime}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td></tr>
        <tr><td>商品描述:<input id="detail" name="detail" value="${item.detail}"></td></tr>
        <tr><td><input type="submit" name="保存"></td></tr>
    </table>
    </form>
</body>
</html>
