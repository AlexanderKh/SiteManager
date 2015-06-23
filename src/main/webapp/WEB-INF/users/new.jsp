<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/users/new" method="post"
           modelAttribute="user"
           cssClass="form-group">

    <form:input path="name" value=""
                placeholder="Username"
                cssClass="form-control"/>

    <form:input path="password"
                placeholder="Password"
                cssClass="form-control"/>

    <form:select path="userGroup"
                 cssClass="form-control">
        <jstl:forEach var="userGroup" items="${userGroups}">
            <form:option value="${userGroup}">${userGroup.name()}</form:option>
        </jstl:forEach>
    </form:select>

    <form:button type="submit" class = "btn btn-success">Add user</form:button>

</form:form>