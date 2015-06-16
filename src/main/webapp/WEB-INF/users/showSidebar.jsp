<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="/users" class="text-center list-group-item">Back to list</a>

<a href="/users/${user.id}/new"
   class="text-center list-group-item list-group-item-success">
    Add permission
</a>

<form action="/users/${user.id}/delete"
      method="post">
    <button type="submit"
            class="btn-block list-group-item list-group-item-danger"
            onclick="return confirm('Are you sure?')">
        Delete user
    </button>
</form>