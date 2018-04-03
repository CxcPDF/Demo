<%--
  Created by IntelliJ IDEA.
  User: 小巷有狗
  Date: 2017/12/24
  Time: 20:20
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
<h1 align="center">网上租车测试系统</h1>
<p>首先请注册或登录</p>
<form action="userTest.jsp" method="post">
    <input type="submit" value="登录界面">
</form>
</body>
</html>
