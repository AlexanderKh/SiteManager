<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h3>
  ${page.title}
</h3>

<hr>

<form action="/userPages/${page.id}/edit" method="post" class="form-group">
  <textarea name="content" class="form-control">${page.content}</textarea>
  <button type="submit"
          class="btn btn-warning">
    Update contents
  </button>
</form>