<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OPERATIONS</title>
</head>
<body>
Please select your operation
<form method="post" action="balance">
    <input name="card" type=hidden value="1111111111111111">
    <button type="submit">BALANCE</button>
</form>

<form method="post" action="withdraw">
    <input name="card" type=hidden value="1111111111111111">
    <button type="submit">WITHDRAW</button>
</form>

<form method="post" action="exit">
    <input name="card" type=hidden value="${card}">
    <button type="submit">EXIT</button>
</form>

${message}

</body>
</html>
