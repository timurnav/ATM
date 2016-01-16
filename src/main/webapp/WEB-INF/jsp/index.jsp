<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="webjars/jquery/2.2.0/jquery.min.js"></script>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="resources/css/common.css">
    <title>ATM</title>
</head>
<body>

<div class="container">
    <div class="main_frame">

        <h2 class="form-signin-heading">Enter card number</h2>
        <form id="form" class="form-signin" action="cards" method="POST">
            <input name="card" type=text id="card" class="form-control"
                   placeholder="0000-0000-0000-0000" required readonly pattern="[0-9]{16}">
        </form>
        <div class="keypad">
            <div class="keys">
                <button type="button" class="btn btn-info btn-xs">7</button>
                <button type="button" class="btn btn-info btn-xs">8</button>
                <button type="button" class="btn btn-info btn-xs">9</button>
            </div>
            <div class="keys">
                <button type="button" class="btn btn-info btn-xs">4</button>
                <button type="button" class="btn btn-info btn-xs">5</button>
                <button type="button" class="btn btn-info btn-xs">6</button>
            </div>
            <div class="keys">
                <button type="button" class="btn btn-info btn-xs">1</button>
                <button type="button" class="btn btn-info btn-xs">2</button>
                <button type="button" class="btn btn-info btn-xs">3</button>
            </div>
            <div class="keys">
                <button type="button" class="btn btn-warning btn-xs">Clear</button>
                <button type="button" class="btn btn-info btn-xs">0</button>
                <button type="button" class="btn btn-success btn-xs">Ok</button>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">

    var $field = $(document).find('input');
    var $keys = $('.keys button');

    $keys.on('click', function () {
        var val = this.textContent;

        switch (val) {
            case "Clear":
                $field.val('');
                break;
            case "Ok":
                sentForm();
                break;
            default:
                $field.val($field.val() + val);
                $field.mask('0000-0000-0000-0000')
        }
    });

    function sentForm() {
        var val = $field.val();
        if (val.length == 16) {
            $('#form').submit();
        } else {
            alert('Input 16 numbers');
        }
    }

</script>
</html>
