<%--
  Created by IntelliJ IDEA.
  User: 小巷有狗
  Date: 2017/12/13
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>停车位测试</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
%>

<form action="userTest.jsp" method="post">
    <input type="submit" value="返回">
</form>

<h1 align="center">添加停车位测试</h1>
<form method="post" action="/ParkAdd">
    日租金：<input type="text" name="day_price"><br>
    小时租金：<input type="text" name="hour_price"><br>
    地址：<input type="text" name="location"><br>
    停车位ID：<input type="text" name="parkId"><br>
    <input type="submit" value="提交">
</form>

<h1 align="center">租停车位测试</h1>
<form action="/RentPark" method="post">
    租停车位用户名：<input type="text" name="userName"><br>
    所租停车位ID号：<input type="text" name="parkId"><br>
    <input type="submit" value="提交">
</form>

<h1 align="center">还停车位测试</h1>
<form action="/ReturnPark" method="post">
    还停车位的用户名：<input type="text" name="userName"><br>
    所还停车位的ID号：<input type="text" name="parkId"><br>
    <input type="submit" value="提交">
</form>

<p>上传图片</p>
<form action="pictureUpload.jsp">
    <input type="submit" value="上传">
</form>

</body>
</html>
