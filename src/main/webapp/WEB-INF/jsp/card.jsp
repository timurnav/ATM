<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CARD</title>
</head>
<body>
Enter pin code for your card
<form method="post" action="pin">
    <input name="card" type=hidden value="${card}">
    <input name="pin" type=password pattern="[0-9]{4}">
    <button type="submit">OK</button>
</form>
${message}

</body>
</html>
