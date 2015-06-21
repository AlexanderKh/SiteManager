<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Add new permission for user: ${user.name}</h3>
<hr>

<form action="/users/${user.id}/new" method="post"
      class="form-horizontal">

    <select name="page" class="form-control">
        <jstl:forEach var="page" items="${pages}">
            <option value="${page.id}">${page.title}</option>
        </jstl:forEach>
    </select>

    <select name="type" class="form-control">
        <jstl:forEach var="type" items="${types}">
            <option value="${type}">${type}</option>
        </jstl:forEach>
    </select>

    <button type="submit" class="btn btn-success">Add permission</button>

</form>