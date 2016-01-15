<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BALANCE</title>
</head>
<body>
Your card number is XXXX-XXXX-XXXX-XXXX
Current date is DATE
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
