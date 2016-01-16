<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>BALANCE</title>
</head>
<body>
<jsp:useBean id="account" type="ru.simplewebapp.model.Account" scope="request"/>
Your card number is ${account.number}
Current date is ${account.dateTime}
Your balance is ${account.amount} USD

<form method="post" action="">
    <input name="card" type=hidden value="${account.number}">
    <button onclick="window.history.back()">BACK</button>
</form>

<form method="post" action="exit">
    <input name="card" type=hidden value="${account.number}">
    <button type="submit">EXIT</button>
</form>

${message}

</body>
</html>
