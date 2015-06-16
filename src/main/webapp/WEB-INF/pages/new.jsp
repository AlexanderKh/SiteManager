<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>
    New page
</h3>
<hr>

<form:form action="/pages/new" method="post"
           modelAttribute="page">

    <form:input path="title" value=""
                placeholder="Title"
                cssClass="form-control"/>
    <button type="submit" class = "btn">Add page</button>

</form:form>