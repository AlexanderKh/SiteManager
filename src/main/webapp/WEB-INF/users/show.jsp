<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-condensed table-bordered">
    <tr>
        <th>Page</th>
        <th>Access</th>
    </tr>
    <jstl:forEach var="permission" items="${permissions}">
        <tr>
            <td>${permission.page}</td>
            <td>${permission.type}</td>
        </tr>
    </jstl:forEach>
</table>
