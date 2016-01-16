<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="resources/css/jumbotron-narrow.css">
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

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="#">Balance</a></li>
                <li role="presentation"><a href="#">Withdraw</a></li>
                <li role="presentation"><a href="#">Exit</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">Please choose operations</h3>
    </div>

    <div class="jumbotron">
        <img src="resources/images/atm_img.png" class="img-rounded" alt="Cinque Terre" width="304" height="236">
    </div>

${message}

</body>
</html>
