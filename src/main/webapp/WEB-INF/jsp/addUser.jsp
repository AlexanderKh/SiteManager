<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add user</title>
</head>
<body class="container">
<a href="/users" class="btn btn-primary">Back to index</a>
<form action="/users" method="post">
    <table width=100%>
        <tr>
            <td>
                Name
            </td>
            <td>
                <input name="name" value="">
            </td>
        </tr>
        <tr>
            <td>
                User Group
            </td>
            <td>
                <select name="usergroup">
                    <option value="USER">User</option>
                    <option value="ADMIN">Admin</option>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add user">
</form>
</body>
</html>
