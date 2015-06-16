<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>
    ${page.title}
</h3>

<hr>

<form action="/pages/${page.id}" method="post" class="form-group">
    <textarea name="content" class="form-control">${page.content}</textarea>
    <button type="submit"
            class="btn btn-warning">
                Update contents
    </button>
</form>

<table class="table table-condensed table-bordered">
    <tr>
        <th>User</th>
        <th>Type</th>
    </tr>
    <jstl:forEach var="permission" items="${permissions}">
        <tr>
            <td>${permission.user}</td>
            <td>${permission.type}</td>
            <td>
                <form action="/pages/${page.id}/${permission.id}/delete"
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