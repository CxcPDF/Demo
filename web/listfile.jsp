<%--
  Created by IntelliJ IDEA.
  User: 小巷有狗
  Date: 2017/12/9
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载文件显示界面</title>
</head>
<body>
<!-- 遍历Map集合 -->
<c:forEach var="me" items="${fileNameMap}">
    <c:url value="/servlet/DownLoadServlet" var="downurl">
        <c:param name="filename" value="${me.key}"></c:param>
    </c:url>
    ${me.value}<a href="${downurl}">下载</a>
    <br/>
</c:forEach>
</body>
</html>
