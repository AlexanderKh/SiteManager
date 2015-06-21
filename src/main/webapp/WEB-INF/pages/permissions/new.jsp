<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Add new permission for page: ${page.title}</h3>
<hr>

<form action="/pages/${page.id}/new" method="post"
           class="form-horizontal">

    <select name="user" class="form-control">
        <jstl:forEach var="user" items="${users}">
            <option value="${user.id}">${user.name}</option>
        </jstl:forEach>
    </select>

    <select name="type" class="form-control">
        <jstl:forEach var="type" items="${types}">
            <option value="${type}">${type}</option>
        </jstl:forEach>
    </select>

    <button type="submit" class="btn btn-success">Add permission</button>

</form>