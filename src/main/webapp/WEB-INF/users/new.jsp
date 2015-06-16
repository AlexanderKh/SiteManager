<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/users" method="post">
    <table class="table">
        <tr>
            <td>Name</td>
            <td>
                <input name="name" value="">
            </td>
        </tr>
        <tr>
            <td>User Group</td>
            <td>
                <select name="usergroup">
                    <jstl:forEach var="userGroup" items="${userGroups}">
                        <option value="${userGroup.name()}">${userGroup.name()}</option>
                    </jstl:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add user">
</form>
