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
<table class="table">
    <jstl:forEach var="permission" items="${users}">
        <tr>
            <td>
                ${permission.id}
            </td>
            <td>
                ${permission.name}
            </td>
            <td>
                ${permission.userGroup}
            </td>
        </tr>
    </jstl:forEach>
</table>
<br>
<a href="users/new">Add new user</a>
</body>
</html>
