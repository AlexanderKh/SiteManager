<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Add new permission for page: ${page.title}</h3>
<hr>

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
            <td>Type</td>
            <td>
                <select name="type">
                    <jstl:forEach var="type" items="${types}">
                        <option value="${type.name()}">${type.name()}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add permission">
</form>