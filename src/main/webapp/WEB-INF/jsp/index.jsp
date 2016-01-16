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
            <input type="hidden" name="card" id="hidden_field" value="${cardNumber}">
            <input type=text id="visible_field" class="form-control"
                   placeholder="0000-0000-0000-0000" required readonly pattern="[0-9]{16}">
        </form>
        <jsp:include page="fragments/keypad.jsp"/>
    </div>
</div>

</body>
<script type="text/javascript">

    var $field = $(document).find('#visible_field');
    var $hiddenField = $(document).find('#hidden_field');
    var $keys = $('.keys button');
    var count = 0;

    $keys.on('click', function () {
        var val = this.textContent;

        switch (val) {
            case "Clear":
                $field.val('');
                $hiddenField.val('');
                count = 0;
                break;
            case "Ok":
                sentForm();
                break;
            default:
                switch (count) {
                    case 16:
                        break;
                    case 4:
                    case 8:
                    case 12:
                        $field.val($field.val() + '-');
                    default:
                        $field.val($field.val() + val);
                        $hiddenField.val($hiddenField.val() + val);
                        count++;
                }
        }
    });

    function sentForm() {
        if (count == 16) {
            $('#form').submit();
        } else {
            alert('Input 16 numbers');
        }
    }

</script>
</html>
