<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add page</title>
    <jsp:include page="../includeBootstrap.jsp"/>
</head>
<body class="container">
<jsp:include page="../header.jsp"/>
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <jsp:include page="../sidebar.jsp"/>
    </div>
    <div class="col-sm-9 col-md-10">
        <form action="/pages" method="post">
            <table width=100%>
                <tr>
                    <td>Title</td>
                    <td>
                        <input name="title" value="">
                    </td>
                </tr>
            </table>
            <button type="submit" class = "btn">Add page</button>
        </form>
    </div>
</div>
</body>
</html>
