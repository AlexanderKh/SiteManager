<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Pages index</h1>
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
                <form action="/pages/${page.id}" method="get">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="/pages/${page.id}/delete" method="post">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </jstl:forEach>
</table>
