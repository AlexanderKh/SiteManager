<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pages</title>
</head>
<body>
<a href="/">Back to index</a>
<table border=1>
    <jstl:forEach var="user" items="${pages}">
        <tr>
            <td>
                    ${user.id}
            </td>
            <td>
                    ${user.title}
            </td>
            <td>
                    ${user.content}
            </td>
        </tr>
    </jstl:forEach>
</table>
</body>
</html>
