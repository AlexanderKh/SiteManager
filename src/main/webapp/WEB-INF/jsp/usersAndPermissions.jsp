<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <jsp:include page="includeBootstrap.jsp"/>
</head>
<body class="container">
<jsp:include page="header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="sidebar.jsp"/>
    </div>
    <div class="col-sm-9 col-md-10">
        <table class="table table-condensed table-bordered">
            <tr>
                <th>User</th>
                <th>Page</th>
                <th>Permission</th>
            </tr>
            <jstl:forEach var="permission" items="${permissions}">
                <tr>
                    <td>${permission.user}</td>
                    <td>${permission.page}</td>
                    <td>${permission.type}</td>
                </tr>
            </jstl:forEach>
        </table>
    </div>
</div>
</body>
</html>
