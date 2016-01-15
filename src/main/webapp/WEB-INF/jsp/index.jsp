<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ATM</title>
</head>
<body>
<section>
    <form method="post" action="/cards">
        <input type=text pattern="[0-9]{13,16}">
        <button type="submit">Выбрать</button>
    </form>
</section>
</body>
</html>
