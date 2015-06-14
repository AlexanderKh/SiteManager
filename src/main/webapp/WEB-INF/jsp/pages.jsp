<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pages</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body class="container">
<a href="/" class="btn btn-primary">Back to index</a>
<table class="table table-condensed table-bordered">
    <jstl:forEach var="page" items="${pages}">
        <tr>
            <td>
                ${page.id}
            </td>
            <td>
                ${page.title}
            </td>
            <td>
                <form action="/pages/${page.id}" method="get">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="/pages/${page.id}/delete" method="post">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </jstl:forEach>
</table>
<a href="pages/new" class="btn btn-success">New Page</a>
</body>
</html>
