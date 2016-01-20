<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operations</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <script src="webjars/jquery/2.2.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="resources/css/common.css">
</head>
<body>
<jsp:include page="fragments/logout_header.jsp"/>
<div class="main_frame">
    <div class="container">
        <br>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
            <li><a data-toggle="tab" href="#balance">Balance</a></li>
            <li><a data-toggle="tab" href="#withdraw">Withdraw</a></li>
        </ul>

        <div class="tab-content">
            <div id="home" class="tab-pane fade in active">
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
            <div id="balance" class="tab-pane fade">
                <div class="left-block">
                    <button type="button" class="btn btn-info btn-xs" onclick="printBalance()">Show account info
                    </button>
                </div>
                <div class="right-block">
                    <div id="image">
                        <img src="resources/images/atm_img.png" class="img-rounded" alt="Cinque Terre" width="450"
                             height="320">
                    </div>
                </div>
            </div>
            <div id="withdraw" class="tab-pane fade">
                <div class="left-block">

                </div>
                <div class="right-block">
                    <form id="form" class="form-signin" action="cards" method="POST">
                        <input type="hidden" name="card" id="hidden_field">
                        <input type=text id="visible_field" class="form-control"
                               placeholder="0000-0000-0000-0000" required readonly>
                    </form>
                    <jsp:include page="fragments/keypad.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function printBalance() {
        $.ajax({
            type: "POST",
            url: "balance",
            data: "card=${card}",
            success: function (data) {
                $('#image').hide();
                alert(data);
            }
        });
    }
</script>
</html>
