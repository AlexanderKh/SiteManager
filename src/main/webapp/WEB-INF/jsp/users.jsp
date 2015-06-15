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
        <table class="table table-condensed">
            <jstl:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.userGroup}</td>
                    <td>
                        <form action="/users/${user.id}" method="get">
                            <input type="submit" value="Show Visible Permissions">
                        </form>
                    </td>
                    <td>
                        <form action="/users/${user.id}/permissions" method="get">
                            <input type="submit" value="Own pages">
                        </form>
                    </td>
                    <td>
                        <form action="/users/${user.id}/delete" method="post">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </jstl:forEach>
        </table>
        <br>
        <a href="users/new" class="btn btn-success">New user</a>
    </div>
</div>

</body>
</html>
