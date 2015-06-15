<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pages</title>
    <jsp:include page="includeBootstrap.jsp"/>
</head>
<body class="container">
<jsp:include page="header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="sidebar.jsp"/>
    </div>
    <div class="col-sm-9 col-md-10">
        <table class="table table-condensed table-bordered">
            <jstl:forEach var="page" items="${pages}">
                <tr>
                    <td>${page.id}</td>
                    <td>${page.title}</td>
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
    </div>
</div>
</body>
</html>
