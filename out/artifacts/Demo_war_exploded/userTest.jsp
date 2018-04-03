<%--
  Created by IntelliJ IDEA.
  User: 小巷有狗
  Date: 2017/12/7
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试页面</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
%>
<p>车辆测试页面</p>
<form action="carTest.jsp" method="post">
    <input type="submit" value="车辆测试">
</form>
<p>停车位测试页面</p>
<form action="parkTest.jsp" method="post">
    <input type="submit" value="停车位测试">
</form>

<form action="adminTest.jsp" method="post">
    <input type="submit" value="管理员">
</form>

<form action="test.jsp" method="post">
    <input type="submit" value="返回">
</form>



<h1 align="center">用户登录</h1>
<form action="/UserLogin" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="提交">
</form>

<h1 align="center">添加用户测试</h1>
<form action="/UserAdd" name="test" method="post">
    姓名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    性别：<input type="text" name="sex"><br>
    出生年月：<input type="text" name="birthday"><br>
    钱包余额：<input type="text" name="money"><br>
    账户状态：<input type="text" name="accountStatus"><br>
    <input type="submit" value="提交">
</form>


<h1 align="center">修改用户名测试</h1>
<form action="/UpdateUserInfo" method="post">
    修改后的密码：<input type="text" name="password"><br>
    修改后的性别：<input type="text" name="sex"><br>
    修改后的出生日期：<input type="text" name="birthday"><br>
    <input type="submit" value="提交">
</form>

<h1 align="center">充值</h1>
<form action="/ChargeMoney" method="post">
    充值金额：<input type="text" name="money"><br>
    姓名：<input type="text" name="userName"><br>
    <input type="submit" value="充值">
</form>

<h1 align="center">车辆搜索</h1>
<form action="/CarSearch" method="post">
    请输入要搜索的内容：<input type="text" name="keyWord"><br>
    <input type="submit" value="搜索">
</form>

<h1 align="center">模糊搜索</h1>
<form action="/Search" method="post">
    名字:<input type="text" name="userName"><br>
    品牌：<input type="text" name="brand"><br>
    拥有者：<input type="text" name="owner"><br>
    车的状态：<input type="text" name="carStatus"><br>
    车的类型：<input type="text" name="type"><br>
    车的排量：<input type="text" name="displacement"><br>
    车龄：<input type="text" name="age"><br>
    里程：<input type="text" name="mileage"><br>
    租借者：<input type="text" name="renter"><br>
    最低价格：<input type="text" name="lowPrice"><br>
    最高价格：<input type="text" name="highPrice"><br>
    状态码：<input type="text" name="StatusCode"><br>
    <input type="submit" value="搜索">
</form>
</body>
</html>
