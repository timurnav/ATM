<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="resources/css/signin.css">
    <title>ATM</title>
</head>
<body>

<div class="container">

    <form class="form-signin" action="cards" method="POST">
        <h2 class="form-signin-heading">ATM machine</h2>
        <input name="card" type=text value="1111111111111111" pattern="[0-9]{16}" id="card" class="form-control" placeholder="Card number" required autofocus>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Trust me I know what I'm doing
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div>

</body>
</html>
