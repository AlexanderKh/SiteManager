<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add page</title>
</head>
<body class="container">
<a href="/" class="btn btn-primary">Back to index</a>
<form action="/pages" method="post">
    <table width=100%>
        <tr>
            <td>
                Title
            </td>
            <td>
                <input name="title" value="">
            </td>
        </tr>
    </table>
    <input type="submit" value="Add page">
</form>
</body>
</html>
