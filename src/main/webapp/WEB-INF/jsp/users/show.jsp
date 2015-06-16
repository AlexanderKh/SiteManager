<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../includeBootstrap.jsp"/>
</head>
<body class="container">
<jsp:include page="../header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="../sidebar.jsp"/>
        <hr>
        <ul class="nav sidebar-nav">
            <li>
                <a href="/users/new" class="btn btn-success"></a>
            </li>
        </ul>
    </div>
    <div class="col-sm-9 col-md-10">

    </div>
</div>
</body>
</html>
