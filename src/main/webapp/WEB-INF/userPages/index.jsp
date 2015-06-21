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
    <jstl:forEach var="permission" items="${permissions}">
        <tr>
            <td>${permission.page.id}</td>
            <td>${permission.page.title}</td>
            <td>
                <a href="<jstl:url value="/userPages/${permission.page.id}"/>" class="btn btn-xs btn-info">Show</a>
            </td>
            <td>
                <jstl:if test="${permission.type == 'EDIT'}">
                    <a href="<jstl:url value="/userPages/${permission.page.id}/edit"/>" class="btn btn-xs btn-info">Edit</a>
                </jstl:if>
            </td>
        </tr>
    </jstl:forEach>
</table>