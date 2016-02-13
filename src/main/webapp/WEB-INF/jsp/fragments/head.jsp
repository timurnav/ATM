<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">

        <div class="navbar-header navbar-brand"><spring:message key="app.title"/> </div>

        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <a class="btn btn-primary" role="button" href="logout"><spring:message key="app.logout"/></a>
            </form>
        </div>
    </div>
</div>