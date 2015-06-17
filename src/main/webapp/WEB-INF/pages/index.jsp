<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>
    Pages index
</h3>
<hr>

<table class="table table-condensed table-bordered">
    <tr>
        <th>ID</th>
        <th>Title</th>
    </tr>
    <jstl:forEach var="page" items="${pages}">
        <tr>
            <td>${page.id}</td>
            <td>${page.title}</td>
            <td>
                <a href="<jstl:url value="/pages/${page.id}"/>" class="btn btn-xs btn-info">Show</a>
            </td>
        </tr>
    </jstl:forEach>
</table>
