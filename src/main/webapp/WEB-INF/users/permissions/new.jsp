<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Add new permission for user: ${user.name}</h3>
<hr>

<form:form action="/users/${user.id}/new" method="post"
           modelAttribute="permission"
           commandName="permission"
           class="form-horizontal">

    <form:hidden path="user"/>

    <form:select path="page" cssClass="form-control">
        <jstl:forEach var="page" items="${pages}">
            <form:option value="${page}">${page.title}</form:option>
        </jstl:forEach>
    </form:select>

    <form:select path="type" cssClass="form-control" items="${types}"/>

    <form:button type="submit" class="btn btn-success">Add permission</form:button>

</form:form>