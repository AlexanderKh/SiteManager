<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="<c:url value="/login?logout"/>" class="text-center list-group-item list-group-item-warning">Logout</a>
<a href="<c:url value="/userPages"/>" class="text-center list-group-item">Pages index</a><td>
<c:if test="${user.userGroup == 'ADMIN'}">
    <a href="<c:url value="/admin"/>" class="text-center list-group-item">Admin Controls</a>
</c:if>
</td>