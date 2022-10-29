<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="resources/js/topjava.common.js"></script>
<<nav class="navbar navbar-expand-md navbar-dark bg-dark py-0">
<div class="container">
    <a href="meals" class="navbar-brand"><img src="resources/images/icon-meal.png"> Подсчет калорий</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form id="login_form" class="form-inline my-2" action="spring_security_check" method="post">
                    <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                    <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                    <button class="btn btn-success" type="submit">
                        <span class="fa fa-sign-in"></span>
                    </button>
                    <div>
                        <input type="hidden" name="_csrf" value="0e7cb3ac-d6c2-4bae-ba58-6df7157fb4e7">
                    </div></form>

            </li>
            <li class="nav-item dropdown show">
                <a class="dropdown-toggle nav-link my-1 ml-2" data-toggle="dropdown" aria-expanded="true">ru</a>
                <div class="dropdown-menu show">
                    <a  class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
                    <a  class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a>
                </div>
            </li>
        </ul>
    </div>
</div>
</nav>

