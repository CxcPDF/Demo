<%--
  Created by IntelliJ IDEA.
  User: 小巷有狗
  Date: 2017/12/27
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员界面</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
%>
<h1 align="center">管理员界面</h1>

<form action="userTest.jsp" method="post">
    <input type="submit" value="返回">
</form>

<h1 align="center">发布求租订单审核</h1>
<form action="/PermitOrder" method="post">
    管理员名称：<input type="text" name="adminName"><br>
    订单状态码：<input type="text" name="statusCode"><br>
    用户名：<input type="text" name="userName"><br>
    车辆名：<input type="text" name="carName"><br>
    <input type="submit" value="通过">
</form>

<h1 align="center">还车订单审核</h1>
<form action="/PermitReturnCar" method="post">
    用户名：<input type="text" name="userName"><br>
    车辆名：<input type="text" name="carName"><br>
    车辆ID：<input type="text" name="ID"><br>
    <input type="submit" value="通过审核">
</form>

<h1 align="修改用户账户状态"></h1>
<form action="/ChangeUserAccountStatus" method="post">
    管理员名称：<input type="text" name="adminName"><br>
    请输入要修改的用户名：<input type="text" name="userName"><br>
    <input type="submit" value="修改">
</form>
</body>
</html>
