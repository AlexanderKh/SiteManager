<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<a href="/">Back to index</a>
<table class="table table-condensed">
    <jstl:forEach var="user" items="${users}">
        <tr>
            <td>
                ${user.id}
            </td>
            <td>
                ${user.name}
            </td>
            <td>
                ${user.userGroup}
            </td>
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
        </tr>
    </jstl:forEach>
</table>
<br>
<a href="users/new">Add new user</a>
</body>
</html>
