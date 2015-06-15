<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Page Edit</title>
    <jsp:include page="includeBootstrap.jsp"/>
</head>
<body class="container">
<jsp:include page="header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="sidebar.jsp"/>
    </div>
    <div class="col">
        <form action="${page.id}" method="post">
            <textarea name="content">${page.content}</textarea>
            <input type="submit" value="Save changes">
        </form>
    </div>
</div>

</body>
</html>
