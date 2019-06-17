<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>La Bonne Nuit</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
    <script src="https://kit.fontawesome.com/bd224c8064.js"></script>
    <link rel="stylesheet" href="https://kit-free.fontawesome.com/releases/latest/css/free.min.css" media="all">
    <c:url value="js/auth.js" var="authScriptUrl"/>
    <c:url value="js/index.js" var="indexScriptUrl"/>
    <c:url value="js/login.js" var="loginScriptUrl"/>
    <c:url value="js/profile.js" var="profileScriptUrl"/>
    <c:url value="js/logout.js" var="logoutScriptUrl"/>

    <script src="${authScriptUrl}"></script>
    <script src="${indexScriptUrl}"></script>
    <script src="${loginScriptUrl}"></script>
    <script src="${profileScriptUrl}"></script>
    <script src="${logoutScriptUrl}"></script>
</head>
<body>
<!-- Headers -->
<div id="header-content" class="hidden content">
    <div class="header-child">
        <div class="title">
            <h1>La Bonne Nuit</h1>
        </div>
        <div id="customer-header-content" class="content">
            <button id="logout-button">Logout</button>
        </div>
        <div id="restaurant-header-content" class="hidden content">
        </div>
    </div>
</div>
<!-- Login -->
<div id="login-content" class="content">
    <div class="login-child">
        <form class="login-register-form" id="login-form" onsubmit="return false;">
            <img src="images/logo.png">
            <h1>Welcome</h1>
            <div class="username">
                <i class="fas fa-user fa-1x" aria-hidden="true"></i>
                <input type="text" name="username" id="username" placeholder="Username">
            </div>
            <div class="password">
                <i class="fas fa-lock fa-1x" aria-hidden="true"></i>
                <input type="password" name="password" id="password" placeholder="Password">
            </div>
            <button id="login-button">Login</button>
            <button id="register-button">Sign Up</button>
        </form>
    </div>
</div>
<!-- Register -->
<div id="register-content" class="hidden content">
    <div class="register-child">
        <form class="login-register-form">
                <img src="images/logo.png">
                <h1>Sign Up</h1>
                <input type="text" placeholder="First Name">
                <input type="text" placeholder="Last Name">
                <input type="text" placeholder="Phone Number">
                <input type="text" placeholder="E-Mail">
                <input type="text" placeholder="Username">
                <input type="password" placeholder="Password">
                <input type="password" placeholder="Re-Password">
                <button id="registration-button">Sign Up</button>
                <button id="back-to-login-button">Back To Login</button>
            </form>
    </div>
</div>
<!-- Customer sections -->
<div id="customer-home-content" class="hidden content">
    <span id="user-firstname"></span></p>
    <span id="user-lastname"></span></p>
    <span id="user-phonenumber"></span></p>
    <span id="user-email"></span></p>
    <span id="user-username"></span></p>
    <span id="user-password"></span></p>
</div>
<div id="customer-reservations-content" class="hidden content">
</div>
<div id="customer-profile-content" class="hidden content">
</div>
<!-- Restaurant sections -->
<div id="restaurant-home-content" class="hidden content">
</div>
<div id="restaurant-reservations-content" class="hidden content">
</div>
<div id="restaurant-employees-content" class="hidden content">
</div>
</body>
</html>
