<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function() {
        $('div.list-group').find('a[href="' + location.pathname + '"]')
                .closest('a').addClass('list-group-item active');
    });
</script>

<a href="<c:url value="/login?logout"/>" class="text-center list-group-item list-group-item-warning">Logout</a>
<a href="<c:url value="/userPages"/>" class="text-center list-group-item">My Pages</a>
<a href="<c:url value="/users"/>" class="text-center list-group-item">Users</a>
<a href="<c:url value="/pages"/>" class="text-center list-group-item">Pages</a>