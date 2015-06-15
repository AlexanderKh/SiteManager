<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add permission</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body class="container">
<jsp:include page="header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="sidebar.jsp"/>
    </div>
    <div class="col-sm-9 col-md-10">
        <form action="/permissions" method="post">
            <table width=100%>
                <tr>
                    <td>User</td>
                    <td>
                        <select name="user">
                            <jstl:forEach var="user" items="${users}">
                                <option value="${user.id}">${user.name}</option>
                            </jstl:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Page</td>
                    <td>
                        <select name="page">
                            <jstl:forEach var="page" items="${pages}">
                                <option value="${page.id}">${page.title}</option>
                            </jstl:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Type</td>
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
    </div>
</div>
</body>
</html>
