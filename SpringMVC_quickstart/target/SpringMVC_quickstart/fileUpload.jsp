<%--
  Created by IntelliJ IDEA.
  User: MECHREV0
  Date: 2025/10/14
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传三要素</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data">
        名称：<input type="text" name="username"><br>
        文件：<input type="file" name="filePic"><br>
        <input type="submit" value="单文件上传">
    </form>

    <%--  多文件上传  --%>
    <form action="${pageContext.request.contextPath}/filesUpload" method="post" enctype="multipart/form-data">
        名称：<input type="text" name="username"><br>
        文件1：<input type="file" name="filePic"><br>
        文件2：<input type="file" name="filePic"><br>
        <input type="submit" value="多文件上传">
    </form>
</body>
</html>
