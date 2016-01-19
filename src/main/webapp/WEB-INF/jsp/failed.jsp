<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="resources/css/common.css">
    <title>Something is wrong</title>
</head>
<body>
<jsp:include page="fragments/logout_header.jsp"/>
<div class="container">
    ${message}
    <br>

    <button type="button" class="btn btn-info btn-xs" onclick="window.history.back()">Back</button>
</div>
</body>
</html>
