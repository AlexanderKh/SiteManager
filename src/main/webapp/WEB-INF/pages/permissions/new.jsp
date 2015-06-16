<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Add new permission for page: ${page.title}</h3>
<hr>

<form:form action="/pages/${page.id}/new" method="post"
           modelAttribute="permission"
           commandName="permission"
           class="form-horizontal">

    <form:hidden path="page"/>

    <form:select path="user" cssClass="form-control">
        <jstl:forEach var="user" items="${users}">
            <form:option value="${user}">${user.name}</form:option>
        </jstl:forEach>
    </form:select>

    <form:select path="type" cssClass="form-control" items="${types}"/>

    <form:button type="submit" class="btn btn-success">Add permission</form:button>

</form:form>