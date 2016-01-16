<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <title>WITHDRAW REPORT</title>
</head>
<body>
Your card number is XXXX-XXXX-XXXX-XXXX
Current date is DATE
You took YYY USD from your account
Your balance is XXX USD

<form method="post" action="">
    <input name="card" type=hidden value="${card}">
    <button onclick="window.history.back()">BACK</button>
</form>

<form method="post" action="exit">
    <input name="card" type=hidden value="${card}">
    <button type="submit">EXIT</button>
</form>

${message}

</body>
</html>
