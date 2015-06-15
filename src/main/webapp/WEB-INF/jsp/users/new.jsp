<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../includeBootstrap.jsp"/>
</head>
<body class="container">
<jsp:include page="../header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="../sidebar.jsp"/>
    </div>
    <div class="col-sm-9 col-md-10">

        <form action="/users" method="post">
            <table width=100%>
                <tr>
                    <td>Name</td>
                    <td>
                        <input name="name" value="">
                    </td>
                </tr>
                <tr>
                    <td>User Group</td>
                    <td>
                        <select name="usergroup">
                            <jstl:forEach var="userGroup" items="${userGroups}">
                                <option value="${userGroup.name()}">${userGroup.name()}</option>
                            </jstl:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Add user">
        </form>
    </div>
</div>
</body>
</html>
