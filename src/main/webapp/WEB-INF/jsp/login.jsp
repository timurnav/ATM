<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="<c:url value="/webjars/jquery/2.2.0/jquery.min.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/webjars/noty/2.3.7/js/noty/packaged/jquery.noty.packaged.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/particles/particles.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/particles/app.js"/>"></script>

    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.1.0/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.1.0/css/bootstrap-theme.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/webjars/animate.css/3.3.0/animate.min.css"/>'>
    <link rel="stylesheet" media="screen" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>">


    <title>ATM</title>
</head>
<body>

<div id="particles-js"></div>

<div class="container" id="thin">

    <div class="main_frame">
        <input type="hidden" name="username" id="error" value="">

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
                    checkCardNumber();
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

    function checkCardNumber() {
        $.ajax({
            type: "POST",
            url: "card",
            data: 'card=' + $hiddenCardField.val(),
            success: function () {
                changeInputFields();
                clearField();
            },
            error: function () {
                funnyNoty('I can\'t find your card number in our warehouse');
            }
        });
    }

    function funnyNoty(message) {
        var text = '<h3>' + message + '</h3>' +
                '<img src="<c:url value="/resources/images/ok.jpg"/>" class="img-rounded" alt="OKAY" width="320" height="320">';
        noty({
            layout: 'center',
            text: text,
            theme: 'relax',
            maxVisible: 1,
            modal: true,
            animation: {
                open: 'animated zoomIn', // Animate.css class names
                close: 'animated zoomOut', // Animate.css class names
                easing: 'swing', // unavailable - no need
                speed: 300 // unavailable - no need
            }
        });
    }

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
            $.ajax({
                type: "POST",
                url: "spring_security_check",
                data: $("#form").serialize(),
                success: function () {
                    location.href = 'cabinet';
                },
                error: function (data) {
                    funnyNoty(data);
                }
            });
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

</body>

</html>
