<%--
  Created by IntelliJ IDEA.
  User: 小巷有狗
  Date: 2017/12/13
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车辆测试</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
%>
<form action="userTest.jsp" method="post">
    <input type="submit" value="返回">
</form>
<h1>测试</h1>
<form method="post" action="/CarAdd">
    <h1 align="center">车辆增加</h1>
    车的Id:<input type="text" name="carName"><br>
    车主姓名：<input type="text" name="userName"><br>
    车的天租金：<input type="text" name="day_price"><br>
    车的月租金：<input type="text" name="month_price"><br>
    车龄：<input type="text" name="age"><br>
    品牌：<input type="text" name="pingpai"><br>
    排量：<input type="text" name="pailiang"><br>
    里程：<input type="text" name="licheng"><br>
    类型：<input type="text" name="type"><br>
    取车地点：<input type="text" name="getCarLocation"><br>
    还车地点：<input type="text" name="returnCarLocation"><br>
    已经租借人数：<input type="text" name="rentedCarUser"><br>
    <input type="submit" value="提交">
</form>

<h1 align="center">删除车辆</h1>
<form method="post" action="/CarDelete">
    请输入你要删除的车的ID：<input type="text" name="carName"><br>
    <input type="submit" value="提交">
</form>

<h1 align="center">租车测试</h1>
<form action="/RentCar" method="post">
        车辆名字：<input type="text" name="carName"><br>
    车辆ID:<input type="text" name="ID">
        <br>租车用户名：<input type="text" name="username">

        <br><input type="submit" value="提交">
</form>

<h1 align="center">还车测试</h1>
<form action="/RenturnCar" method="post">
    还车用户名：<input type="text" name="userName"><br>
    所还车辆ID号：<input type="text" name="carName"><br>
    <input type="submit" value="提交">
</form>

<p>上传图片</p>
<form action="pictureUpload.jsp">
    <input type="submit" value="上传">
</form>
</body>
</html>
