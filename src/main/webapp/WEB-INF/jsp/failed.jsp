<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="../../resources/css/common.css">
    <title>Something is wrong</title>
</head>
<body>
<jsp:include page="fragments/head.jsp"/>

<div class="container" id="failure">
    <hr>
    <c:if test="${error}">
        <div class="error">
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="message">
            <fmt:message key="${message}"/>
        </div>
    </c:if>
    <hr>
    <button type="button" class="btn btn-info btn-lg" onclick="window.history.back()">Back</button>
</div>
</body>
</html>
