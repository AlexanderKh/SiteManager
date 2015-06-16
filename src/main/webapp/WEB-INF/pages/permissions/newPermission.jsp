<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/permissions" method="post">
    <table class="table">
        <tr>
            <td>User</td>
            <td>
                <select name="user">
                    <jstl:forEach var="user" items="${users}">
                        <option value="${user.id}">${user.name}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Page</td>
            <td>
                <select name="page">
                    <jstl:forEach var="page" items="${pages}">
                        <option value="${page.id}">${page.title}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Type</td>
            <td>
                <select name="type">
                    <jstl:forEach var="type" items="${permissionTypes}">
                        <option value="${type.name()}">${type.name()}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add permission">
</form>