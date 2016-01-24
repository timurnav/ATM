<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Operations</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <script src="webjars/jquery/2.2.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="resources/css/common.css">
</head>
<body>
<jsp:include page="fragments/head.jsp"/>
<div class="main_frame">
    <div class="container">
        <br>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#home_tab">Home</a></li>
            <li><a data-toggle="tab" href="#balance_tab" id="balance-tab-button">Balance</a></li>
            <li><a data-toggle="tab" href="#withdraw_tab">Withdraw</a></li>
        </ul>

        <div class="tab-content">
            <div id="home_tab" class="tab-pane fade in active">
                <div class="left-block">
                    <h3>Добро пожаловать в интернет-банкинг вашего любимого банка</h3>
                    <p>Мы с большим удовольствием наживаемся на ваших деньгах изо дня в день, спасибо что вы выбрали
                        именно нас!</p>
                    <p>Для того чтобы воспользоваться нашими услугами придется поводить мышкой по экрану и покликать.
                        Чтобы проверить насколько жалок баланс вашего счета вам нужно перейти на вкладку Balance, чтобы
                        снять наличность с карты придется перейти на вкладку Withdraw</p>
                    <p align="right">Приятного дня!</p>
                </div>
                <div class="right-block">
                    <img src="resources/images/atm_img.png" class="img-rounded" alt="Cinque Terre" width="450"
                         height="320">
                </div>
            </div>
            <div id="balance_tab" class="tab-pane fade">
                <div class="left-block">
                    <button type="button" id="show_balance_button" class="btn btn-info btn-lg image">Show account info
                    </button>
                    <div class="account-info" hidden>
                        <button type="button" id="show_image" class="btn btn-info btn-lg account_info">
                            Return that beautiful picture
                        </button>
                    </div>
                </div>
                <div class="right-block">
                    <div class="image">
                        <img src="resources/images/atm_img.png" class="img-rounded" alt="Cinque Terre" width="450"
                             height="320">
                    </div>
                    <div class="account-info" hidden>
                        <%--<jsp:useBean id="account" type="ru.simplewebapp.model.Account" scope="request"/>--%>
                        <div class="account_info">
                            Your card number is
                            <span id="number"></span><br>
                            Last transaction time is
                            <span id="datetime"></span><br>
                            Your balance is
                            <span id="balance"></span><br>
                        </div>
                    </div>
                </div>
            </div>
            <div id="withdraw_tab" class="tab-pane fade">
                <div class="left-block">
                    To withdraw cash input required value in the input field on right
                    or click a corresponding number below
                    <c:forEach var="num" items="<%=new int[]{20, 50, 100, 200, 500, 1000}%>">
                        <div class="keys">
                            <button type="button" class="btn btn-info btn-lg">${num}</button>
                        </div>
                    </c:forEach>
                </div>
                <div class="right-block">
                    <form id="form" class="form-signin" action="withdraw" method="POST">
                        <input type="hidden" name="card" value="${card}">
                        <input type="hidden" id="hidden_field" name="amount">
                        <input type=text id="visible_field" class="form-control"
                               placeholder="0" required readonly>
                    </form>
                    <jsp:include page="fragments/keypad.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Withdraw report</h2>
            </div>
            <div class="modal-body">
                <span id="number_report"></span><br>
                Transaction time is
                <span id="datetime_report"></span><br>
                Withdrawn from your account
                <span id="withdrawn_report"></span><br>
                Your balance is
                <span id="balance_report"></span><br>
            </div>
        </div>
    </div>
</div>

</body>
<script>

    var $field = $('#visible_field');
    var $hiddenField = $('#hidden_field');
    var $keys = $('.keys button');
    maxCount = 9;
    var isInfoShown = false;

    $('#show_balance_button').on('click', function () {
        updateBalanceInfo();
    });

    $('#balance-tab-button').on('click', function () {
        if (isInfoShown) {
            updateBalanceInfo();
        }
    });

    function updateBalanceInfo() {
        $.ajax({
            type: "GET",
            url: "balance",
            success: function (data) {
                isInfoShown = true;
                $('.image').hide();
                $('.account-info').show();
                $('#number').text(data.number);
                $('#datetime').text(data.dateTime);
                $('#balance').text('$' + data.balance / 100);
            }
        });
    }

    $('#show_image').on('click', function () {
        $('.image').show();
        $('.account-info').hide();
        isInfoShown = false;
    });

    $keys.on('click', function () {
        var val = this.textContent;
        switch (val) {
            case "C":
                clearField();
                break;
            case "Ok":
                withdrawMoney($hiddenField.val());
                break;
            case "20":
            case "50":
            case "100":
            case "200":
            case "500":
            case "1000":
                clearField();
                addNumberIfNecessary(val);
                withdrawMoney(val);
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
            case 3:
            case 6:
                $field.val($field.val() + ' ');
            default:
                $field.val($field.val() + val);
                $hiddenField.val($hiddenField.val() + val);
        }
    }

    function withdrawMoney() {
        var val = $('#hidden_field').val();
        if (val % 10 == 0) {
            $.ajax({
                url: "withdraw",
                type: 'POST',
                data: 'amount=' + val + '00',
                success: function () {
                    showResultOfWithdraw(val);
                }
            });
        } else {
            alert('There are no such denominations in ATM.\nThe smallest denomination is $10');
        }
    }

    function showResultOfWithdraw(val) {
        $.ajax({
            type: "GET",
            url: "balance",
            success: function (data) {
                $('#editRow').modal();
                $('#number_report').text(data.number);
                $('#datetime_report').text(data.dateTime);
                $('#balance_report').text('$' + data.balance / 100);
                $('#withdrawn_report').text('$' + val + '.00');
            }
        });
    }

</script>
</html>
