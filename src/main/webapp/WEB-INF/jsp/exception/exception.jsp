<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Operations</title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.1.0/css/bootstrap.min.css"/>'>
    <script src="<c:url value="/webjars/jquery/2.2.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.1.0/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/cabinet.css"/>">
</head>
<body>

<div class="jumbotron">
    <div class="container">
        <br>
        <h4>Application error: </h4>
        <h2>${exception.message}</h2>
        <!--
<c:forEach items="${exception.stackTrace}" var="stackTrace">
    ${stackTrace}
</c:forEach>
-->
    </div>
</div>
</body>
</html>