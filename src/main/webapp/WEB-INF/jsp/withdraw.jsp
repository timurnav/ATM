<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <title>WITHDRAW</title>
</head>
<body>
Please enter the amount you wanted to withdraw

<form name="withdraw" method="post" action="withdraw_result">
    <input name="card" type=hidden value="1111111111111111">
    <input name="amount" type=number pattern="[0-9]{4}">
    <button type="reset">CLEAR</button>
    <button type="submit">OK</button>
</form>

<form method="post" action="exit">
    <input name="card" type=hidden value="${card}">
    <button type="submit">EXIT</button>
</form>

${message}

</body>
</html>
