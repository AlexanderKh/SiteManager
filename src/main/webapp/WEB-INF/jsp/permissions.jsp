<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Permissions</title>
</head>
<body>
<a href="/">Back to index</a>
<table border=1>
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
