<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function() {
        $('div.list-group').find('a[href="' + location.pathname + '"]')
                .closest('a').addClass('list-group-item active');
    });
</script>

<sec:authorize access="isAuthenticated()">
    <a href="<c:url value="/login?logout"/>" class="text-center list-group-item list-group-item-warning">Logout</a>
</sec:authorize>
<sec:authorize access="isAnonymous()">
    <a href="<c:url value="/login"/>" class="text-center list-group-item">Login</a>
</sec:authorize>

<a href="<c:url value="/users"/>" class="text-center list-group-item">Users</a>
<a href="<c:url value="/pages"/>" class="text-center list-group-item">Pages</a>