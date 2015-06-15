<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Permissions</title>
    <jsp:include page="includeBootstrap.jsp"/>
</head>
<body class="container">
<jsp:include page="header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="sidebar.jsp"/>
    </div>
    <div class="col-sm-9 col-md-10">
        <jstl:if test="${message != null}">
            <hr>
            ${message}
            <hr>
        </jstl:if>

        <table class="table table-condensed table-bordered">
            <jstl:forEach var="permission" items="${permissions}">
                <tr>
                    <td>${permission.id}</td>
                    <td>${permission.user}</td>
                    <td>${permission.page}</td>
                    <td>${permission.type}</td>
                    <td>
                        <form action="/permissions/${permission.id}" method="post">
                            <input type="submit" value="Delete Permission">
                        </form>
                    </td>
                </tr>
            </jstl:forEach>
        </table>
        <a href="/permissions/new" class="btn btn-success">New permission</a>
    </div>
</div>
</body>
</html>
