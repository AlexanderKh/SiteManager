<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add permission</title>
</head>
<a href="/permissions" class="btn btn-primary">Back to index</a>
<body class="container">
<form action="/permissions" method="post">
    <table width=100%>
        <tr>
            <td>
                User
            </td>
            <td>
                <select name="user">
                    <jstl:forEach var="user" items="${users}">
                        <option value="${user.id}">${user.name}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Page
            </td>
            <td>
                <select name="page">
                    <jstl:forEach var="page" items="${pages}">
                        <option value="${page.id}">${page.title}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Type
            </td>
            <td>
                <select name="type">
                    <jstl:forEach var="type" items="${permissionTypes}">
                        <option value="${type.name()}">${type.name()}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add permission">
</form>
</body>
</html>
