<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="webjars/jquery/2.2.0/jquery.min.js"></script>

    <script src="resources/js/particles/particles.js"></script>
    <script src="resources/js/particles/app.js"></script>
    <link rel="stylesheet" media="screen" href="resources/css/style.css">

    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="resources/css/common.css">

    <title>ATM</title>
</head>
<body>

<div id="particles-js"></div>

<div class="container" id="thin">

    <div class="main_frame">

        <h2 class="form-signin-heading">Enter card number</h2>
        <form id="form" class="form-signin" action="pin_enter" method="POST">
            <input type="hidden" name="card" id="hidden_field">
            <input type=text id="visible_field" class="form-control"
                   placeholder="0000-0000-0000-0000" required readonly>
        </form>
        <jsp:include page="fragments/keypad.jsp"/>
    </div>
</div>



<script type="text/javascript">

    var $field = $('#visible_field');
    var $hiddenField = $('#hidden_field');
    var $keys = $('.keys button');
    maxCount = 16;
    fieldMask = '____-____-____-____';

    $keys.on('click', function () {
        var val = this.textContent;
        switch (val) {
            case "C":
                clearField();
                break;
            case "Ok":
                sendForm();
                break;
            default:
                addNumberIfNecessary(val);
        }
    });

    function clearField() {
        $field.val('');
        $hiddenField.val('');
    }

    function addNumberIfNecessary(val) {
        switch ($('#hidden_field').val().length) {
            case maxCount:
                break;
            case 0:
                $field.val(fieldMask);
            default:
                $field.val($field.val().replace("_", val));
                $hiddenField.val($hiddenField.val() + val);
        }
    }

    function sendForm() {
        if ($('#hidden_field').val().length == maxCount) {
            $('#form').submit();
        } else {
            alert('Expected 16 numbers');
        }
    }

</script>

</body>

</html>
