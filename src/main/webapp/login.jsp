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
<div class="container" id="thin">
    <div class="main_frame">

        <h2 class="form-signin-heading">Enter card number</h2>
        <form id="form" class="form-signin" action="spring_security_check" method="POST">
            <input type="hidden" name="username" id="hidden_card_field">
            <input type="hidden" name="password" id="hidden_pin_field">
            <input type=text id="visible_card_field" class="form-control"
                   placeholder="0000-0000-0000-0000" required readonly>
            <input type=text id="visible_pin_field" class="form-control" style="display: none;"
                   placeholder="0000" required readonly>
        </form>
        <jsp:include page="fragments/keypad.jsp"/>
    </div>
</div>

</body>
<script type="text/javascript">

    var $cardField = $('#visible_card_field');
    var $pinField = $('#visible_pin_field');
    var $hiddenCardField = $('#hidden_card_field');
    var $hiddenPinField = $('#hidden_pin_field');
    var $keys = $('.keys button');
    var $buttonClear = $('#button_clear');

    clearField();

    var readyToSend = false;

    maxCardCount = 16;
    maxPinCount = 4;
    cardFieldMask = '____-____-____-____';
    pinFieldMask = '____';

    $keys.on('click', function () {
        var val = this.textContent;
        switch (val) {
            case "Back":
                changeInputFields();
                break;
            case "C":
                clearField();
                break;
            case "Ok":
                if (readyToSend) {
                    sendForm();
                } else {
                    changeInputFields();
                    clearField();
                }
                break;
            default:
                if (readyToSend) {
                    addPinNumberIfNecessary(val);
                } else {
                    addCardNumberIfNecessary(val);
                }
        }
    });

    function changeInputFields() {
        if (readyToSend) {
            $cardField.show();
            $pinField.hide();
            $buttonClear.html('C');
            readyToSend = false;
        } else {
            if ($('#hidden_card_field').val().length == maxCardCount) {
                $cardField.hide();
                $pinField.show();
                $buttonClear.html('Back');
                readyToSend = true;
            }
        }
    }

    function clearField() {
        if (readyToSend) {
            $pinField.val('');
            $hiddenPinField.val('');
            $buttonClear.html('Back');
        } else {
            $cardField.val('');
            $hiddenCardField.val('');
        }
    }

    function sendForm() {
        if ($hiddenPinField.val().length == maxPinCount) {
            $('#form').submit();
        } else {
            alert('Expected 4 numbers');
        }
    }

    function addCardNumberIfNecessary(val) {
        switch ($('#hidden_card_field').val().length) {
            case maxCardCount:
                break;
            case 0:
                $cardField.val(cardFieldMask);
            default:
                $cardField.val($cardField.val().replace("_", val));
                $hiddenCardField.val($hiddenCardField.val() + val);
        }
    }

    function addPinNumberIfNecessary(val) {
        switch ($('#hidden_pin_field').val().length) {
            case maxPinCount:
                break;
            case 0:
                $buttonClear.html('C');
                $pinField.val(pinFieldMask);
            default:
                $pinField.val($pinField.val().replace("_", "○"));
                $hiddenPinField.val($hiddenPinField.val() + val);
        }
    }

</script>
</html>
