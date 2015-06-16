<form action="/users" method="post">
    <table width=100%>
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
