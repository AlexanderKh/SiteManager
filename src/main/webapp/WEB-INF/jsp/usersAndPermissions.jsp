<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<a href="/" class="btn btn-primary">Back to index</a>
<table class="table table-condensed table-bordered">
    <tr>
        <th>User</th>
        <th>Page</th>
        <th>Permission</th>
    </tr>
    <jstl:forEach var="permission" items="${permissions}">
        <tr>
            <td>
                ${permission.user}
            </td>
            <td>
                ${permission.page}
            </td>
            <td>
                ${permission.type}
            </td>
        </tr>
    </jstl:forEach>
</table>
<br>
</body>
</html>
