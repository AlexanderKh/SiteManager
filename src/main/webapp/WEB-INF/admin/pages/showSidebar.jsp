<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="/pages" class="text-center list-group-item">Back to list</a>

<a href="/pages/${page.id}/new"
   class="text-center list-group-item list-group-item-success">
    Add permission
</a>

<form action="/pages/${page.id}/delete"
      method="post">
    <button type="submit"
            class="btn-block list-group-item list-group-item-danger"
            onclick="return confirm('Are you sure?')">
        Delete page
    </button>
</form>