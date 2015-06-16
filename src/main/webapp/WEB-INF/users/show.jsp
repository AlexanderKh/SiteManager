<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>
    ${user.name}
    <span class="pull-right">${user.userGroup}</span>
</h3>
<hr>
<table class="table table-condensed table-bordered">
    <tr>
        <th>Page</th>
        <th>Access</th>
    </tr>
    <jstl:forEach var="permission" items="${permissions}">
        <tr>
            <td>${permission.page}</td>
            <td>${permission.type}</td>
            <td>
                <form action="/users/${user.id}/${permission.id}/delete"
                      style="margin-bottom: 0px"
                      method="post">
                    <button type="submit"
                            class="btn btn-xs btn-danger"
                            onclick="return confirm('Are you sure?')">
                        Delete
                    </button>
                </form>
            </td>
        </tr>
    </jstl:forEach>
</table>
