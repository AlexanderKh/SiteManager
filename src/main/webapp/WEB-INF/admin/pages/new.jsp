<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>
    New page
</h3>
<hr>

<form:form action="/pages/new" method="post"
           modelAttribute="page"
           cssClass="form-horizontal">

    <form:input path="title" value=""
                placeholder="Title"
                cssClass="form-control"/>

    <form:button type="submit" class = "btn btn-success">Add page</form:button>

</form:form>