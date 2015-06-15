<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="container">
<tiles:insertAttribute name="header" />
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <tiles:insertAttribute name="sidebar" />
            <hr>
            <tiles:insertAttribute name="activeSidebar" />
        </div>
        <div class="col-sm-9 col-md-10">
            <tiles:insertAttribute name="body" />
        </div>
    </div>
</body>
</html>