<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Permissions</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<a href="/">Back to index</a>
<table class="table table-condensed table-bordered">
    <jstl:forEach var="permission" items="${permissions}">
        <tr>
            <td>
                ${permission.id}
            </td>
            <td>
                ${permission.user}
            </td>
            <td>
                ${permission.page}
            </td>
            <td>
                ${permission.type}
            </td>
            <td>
                <form action="/permissions/${permission.id}" method="post">
                    <input type="submit" value="Delete Permission">
                </form>
            </td>
        </tr>
    </jstl:forEach>
</table>

<a href="/permissions/new">new</a>
</body>
</html>
