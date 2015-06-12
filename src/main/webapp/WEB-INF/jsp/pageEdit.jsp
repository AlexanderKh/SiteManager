<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body class="container">
<a href="/pages"  class="btn btn-primary">Back to index</a><br>
<form action="${page.id}" method="post">
    <textarea name="content">${page.content}</textarea>
    <input type="submit" value="Save changes">
</form>
<br>
<a href="users/new" class="btn btn-success">New user</a>
</body>
</html>
