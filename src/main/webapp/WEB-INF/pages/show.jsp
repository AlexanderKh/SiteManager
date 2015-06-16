<h3>
    ${page.title}
</h3>

<hr>

<form action="/pages/${page.id}" method="post" class="form-group">
    <textarea name="content" class="form-control">${page.content}</textarea>
    <input type="submit" value="Update contents">
</form>