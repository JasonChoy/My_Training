<%--
  Created by IntelliJ IDEA.
  User: brian
  Date: 2016/3/7
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>json交互测试</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.js"></script>
    <script type="text/javascript">
        //请求json，输出是json
        function requestJson(){

            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath }/jsonTest/requestJson.action',
                contentType:'application/json;charset=utf-8',
                //数据格式是json串，商品信息
                data:'{"name":"手机","price":999}',
                success:function(data){//返回json结果
                    $("#requestJson").html($("#requestJson").html()+data.toSource());
                    $("#requestJson").show();
                }

            });

        }
        //请求key/value，输出是json
        function responseJson(){

            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath }/jsonTest/responseJson.action',
                //请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
                //contentType:'application/json;charset=utf-8',
                //数据格式是json串，商品信息
                data:'name=手机&price=999',
                success:function(data){//返回json结果
                    $("#responseJson").html($("#responseJson").html()+data.toSource());
                    $("#responseJson").show();
                }

            });

        }


    </script>
</head>
<body>
<input type="button" onclick="requestJson()" value="请求json，输出是json"/>
<input type="button" onclick="responseJson()" value="请求key/value，输出是json"/>
<div id="requestJson" style="display: none">
    请求json，输出是json:<br/>
    请求{"name":"手机","price":999}<br/>
    输出
</div>
<div id="responseJson" style="display: none">
    请求key/value，输出是json<br/>
    请求'name=手机&price=999'<br/>
    输出
</div>
</body>