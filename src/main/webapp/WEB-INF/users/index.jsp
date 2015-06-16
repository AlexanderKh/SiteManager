<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Users index</h3>
<hr>
<table class="table table-bordered">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Group</th>
    </tr>
    <jstl:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.userGroup}</td>
            <td><a href="<jstl:url value="/users/${user.id}"/>" class="btn btn-xs btn-info">Show</a></td>
        </tr>
    </jstl:forEach>
</table>
