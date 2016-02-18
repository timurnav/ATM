<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><spring:message key="app.operations"/></title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.1.0/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.1.0/css/bootstrap-theme.min.css"/>'>
    <script src="<c:url value="/webjars/jquery/2.2.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.1.0/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="/webjars/noty/2.3.7/js/noty/packaged/jquery.noty.packaged.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/webjars/animate.css/3.3.0/animate.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/cabinet.css"/>">
</head>
<body>
<jsp:include page="fragments/head.jsp"/>
<div class="main_frame">
    <div class="container">
        <br>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#home_tab"><spring:message key="app.home"/> </a></li>
            <li><a data-toggle="tab" href="#balance_tab" id="balance-tab-button"><spring:message key="app.balance"/></a>
            </li>
            <li><a data-toggle="tab" href="#withdraw_tab"><spring:message key="app.withdraw"/> </a></li>
            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                   aria-expanded="false">
                    ${pageContext.response.locale} <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a onclick="show('en')">English</a></li>
                    <li><a onclick="show('ru')">Русский</a></li>
                </ul>
            </li>
        </ul>

        <div class="row">


            <div class="tab-content">
                <div id="home_tab" class="tab-pane fade in active">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="left-block col-md-6 col-sm-12">
                                <h3><spring:message key="app.welcome"/></h3>
                                <p><spring:message key="app.thanks"/></p>
                                <p><spring:message key="app.instructions"/></p>
                                <p align="right"><spring:message key="app.goodbye"/></p>
                            </div>
                            <div class="right-block col-md-6 col-sm-12">
                                <img src="<c:url value="/resources/images/atm_img.png"/>"
                                     class="img-rounded img-responsive"
                                     alt="Cinque Terre" width="450"
                                     height="320">
                            </div>
                        </div>
                    </div>
                </div>


                <div id="balance_tab" class="tab-pane fade">
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="left-block col-md-6 col-sm-12">
                                <div class="image">
                                    <button type="button" id="show_balance_button"
                                            class="btn btn-info btn-lg col-sm-12">
                                        <spring:message
                                                key="app.account_info"/>
                                    </button>
                                </div>
                                <div class="account-info" hidden>
                                    <button type="button" id="show_image" class="btn btn-info btn-lg btn-block">
                                        <spring:message key="app.picture"/>
                                    </button>
                                </div>
                            </div>
                            <div class="right-block col-md-6 col-sm-12">
                                <div class="image">
                                    <img src="<c:url value="/resources/images/atm_img.png"/>"
                                         class="img-rounded img-responsive"
                                         alt="Cinque Terre"
                                         width="450"
                                         height="320">
                                </div>


                                <div class="account-info" hidden>
                                    <div class="account_info">
                                        <spring:message key="app.card"/>
                                        <span id="number"></span><br>
                                        <spring:message key="app.transaction"/>
                                        <span id="datetime"></span><br>
                                        <spring:message key="app.balance_equals"/>
                                        <span id="balance"></span><br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div id="withdraw_tab" class="tab-pane fade">
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="left-block col-md-6 col-sm-12">
                                <spring:message key="app.withdraw_condition"/>
                                <c:forEach var="num" items="<%=new int[]{20, 50, 100, 200, 500, 1000}%>">
                                    <div class="keys col-md-12 col-sm-12">
                                        <button type="button"
                                                class="btn btn-info btn-lg col-md-12 col-sm-12">${num}</button>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="right-block col-md-6 col-sm-12">
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

        </div>
    </div>
    <div class="modal fade" id="report_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title"><spring:message key="app.report"/></h2>
                </div>
                <div class="modal-body">
                    <span id="number_report"></span><br>
                    <spring:message key="app.transaction_time"/>
                    <span id="datetime_report"></span><br>
                    <spring:message key="app.withdraw_from_account"/>
                    <span id="withdrawn_report"></span><br>
                    <spring:message key="app.balance_equals"/>
                    <span id="balance_report"></span><br>
                </div>
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
            default:
                $field.val($field.val() + val);
                $hiddenField.val($hiddenField.val() + val);
        }
    }

    function funnyNoty(message) {
        var text = '<h3>' + message + '</h3>' +
                '<img src="<c:url value="/resources/images/ok.jpg"/>" class="img-rounded" alt="OK" width="320" height="320">';
        noty({
            layout: 'center',
            text: text,
            theme: 'relax',
            maxVisible: 1,
            modal: true,
            animation: {
                open: 'animated rubberBand', // Animate.css class names
                close: 'animated hinge', // Animate.css class names
                easing: 'swing', // unavailable - no need
                speed: 300 // unavailable - no need
            }
        });
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
                },
                error: function () {
                    funnyNoty('You don\'t have enough money');
                }
            });
        } else {
            funnyNoty('There are no such denominations in ATM.\nThe smallest denomination is $10');
        }
    }

    function showResultOfWithdraw(val) {
        $.ajax({
            type: "GET",
            url: "balance",
            success: function (data) {
                $('#report_modal').modal();
                $('#number_report').text(data.number);
                $('#datetime_report').text(data.dateTime);
                $('#balance_report').text('$' + data.balance / 100);
                $('#withdrawn_report').text('$' + val + '.00');
            }
        });
    }

</script>

<script>
    function show(lang) {
        window.location.href = window.location.href.split('?')[0] + '?lang=' + lang;
    }
</script>
</html>
