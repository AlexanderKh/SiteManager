<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <tiles:insertAttribute name="include"/>
</head>
<body class="container">
<tiles:insertAttribute name="header" />
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="list-group">
                <tiles:insertAttribute name="sidebar" />
            </div>
            <hr>
            <div class="list-group">
                <tiles:insertAttribute name="active" />
            </div>
        </div>
        <div class="col-sm-9 col-md-10">
            <tiles:insertAttribute name="body" />
        </div>
    </div>
</body>
</html>