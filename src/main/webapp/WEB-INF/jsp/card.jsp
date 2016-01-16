<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="resources/css/common.css">
    <title>PIN</title>
</head>
<body>

<div class="container">

    <form class="form-signin" action="pin" method="POST">
        <h2 class="form-signin-heading">Enter pin</h2>
        <input name="card" type=hidden value="${card}">
        <input name="pin" type=password value="1234" pattern="[0-9]{4}" id="pin" class="form-control" placeholder="Card number" required autofocus>
        <div class="checkbox">

        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">OK</button>
    </form>

</div>

</body>
</html>
