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
    <c:url value="js/register.js" var="registerScriptUrl"/>
    <c:url value="js/profile.js" var="profileScriptUrl"/>
    <c:url value="js/menu.js" var="menuScriptUrl"/>
    <c:url value="js/logout.js" var="logoutScriptUrl"/>
    <c:url value="js/cart.js" var="cartScriptUrl"/>
    <c:url value="js/employees.js" var="employeesScriptUrl"/>
    <c:url value="js/orders.js" var="ordersScriptUrl"/>


    <script src="${authScriptUrl}"></script>
    <script src="${indexScriptUrl}"></script>
    <script src="${loginScriptUrl}"></script>
    <script src="${registerScriptUrl}"></script>
    <script src="${profileScriptUrl}"></script>
    <script src="${menuScriptUrl}"></script>
    <script src="${logoutScriptUrl}"></script>
    <script src="${cartScriptUrl}"></script>
    <script src="${employeesScriptUrl}"></script>
    <script src="${ordersScriptUrl}"></script>

</head>
<body>
<!-- Headers -->
<div id="header-content" class="hidden content">
    <div class="header-child">
        <div class="title">
            <a onclick="showAllFood();"><h1>La Bonne Nuit</h1></a>
        </div>
        <div id="customer-header-content" class="content">
            <div class="customer-header-content">
                <div class="dropdown">
                    <a href="javascript:void(0);" onclick="showAllFood();"><button>Menu</button></a>
                    <div class="dropdown-menu">
                        <a href="javascript:void(0);" onclick="showAppertizers();"><button id="go-to-appertizers-button">Appertizers</button></a>
                        <a href="javascript:void(0);" onclick="showSoups();"><button id="go-to-soups-button">Soups</button></a></a>
                        <a href="javascript:void(0);" onclick="showMainDishes();"><button id="go-to-main-dishes-button">Main Dishes</button></a>
                        <a href="javascript:void(0);" onclick="showDesserts();"><button id="go-to-desserts-button">Desserts</button></a>
                    </div>
                </div>
                <div class="cart">
                    <button id="go-to-cart-button"><i class="fas fa-shopping-cart fa-1x" aria-hidden="true"></i>Cart(<span id="cart-count">0</span>)</button>
                </div>
                <button id="go-to-profile-button"><span id="user-first-name">Profile</span></button>
                <div id="manager-header" class="hidden content">
                    <button id="show-employees">Employees</button>
                    <button id="add-food-to-menu">Add food</button>

                </div>
                <div id="employee-header" class="hidden content">
                    <button id="show-orders">Orders</button>
                </div>
                <button id="logout-button">Logout</button>
            </div>
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
            <button onclick="onGoToRegisterButtonClicked();">Register</button>
        </form>
    </div>
</div>
<!-- Register -->
<div id="register-content" class="hidden content">
    <div class="register-child">
        <form class="login-register-form" id="registration-form" onsubmit="return false;">
                <img src="images/logo.png">
                <h1>Sign Up</h1>
                <input type="text" name="firstname" id="firstname" placeholder="First Name">
                <input type="text" name="lastname" id="lastname" placeholder="Last Name">
                <input type="text" name="phonenumber" id="phonenumber" placeholder="Phone Number">
                <input type="text" name="email" id="email" placeholder="E-Mail">
                <input type="text" name="username" id="regusername" placeholder="Username">
                <input type="password" name="password" id="regpassword" placeholder="Password">
                <input type="password" name="repassword" id="regrepassword" placeholder="Re-Password">
                <button id="registration-button">Sign Up</button>
                <button id="back-to-login-button">Back To Login</button>
            </form>
    </div>
</div>
<!-- Customer sections -->
<div id="customer-home-content" class="hidden content">
    <div class="menu-table">
        <table id="menu-table">

        </table>
    </div>
</div>
<div id="customer-profile-content" class="hidden content">
    <div class="profile">
        <div class="profile-menu-box">
            <button id="my-personal-datas-button">My personal datas</button>
            <button id="shipping-address-button">Shipping addresses</button>
            <a href="javascript:void(0);" onclick="deleteUser();"><button id="delete-registration-button">Delete my registration</button></a>
        </div>
        <div class="profile-settings-box">
            <div class="profile-settings-box-header">
                <span id="profile-settings-header-title"></span>
            </div>
            <div class="profile-settings-box-content">
                <div id="my-personal-datas-content" class="content">
                    Name: <span id="pd-name"></span></br>
                    E-mail: <span id="pd-email"></span></br>
                    Phone: <span id="pd-phone"></span></br>
                </div>
                <div id="shipping-addresses-content" class="hidden content">
                    <div class="shipping">
                        <form class="shipping-form" id="shipping-address-form" onsubmit="return false;">
                            <div class="city">
                                <i class="fas fa-city fa-1x" aria-hidden="true"></i>
                                <input type="text" name="city" id="city" placeholder="City">
                            </div>
                            <div class="address">
                                <i class="fas fa-map fa-1x" aria-hidden="true"></i>
                                <input type="text" name="address" id="address" placeholder="Address">
                            </div>
                            <div class="postal-code">
                                <i class="fas fa-mail-bulk fa-1x" aria-hidden="true"></i>
                                <input type="text" name="postal-code" id="postal-code" placeholder="Postal code">
                            </div>
                            <button id="update-shipping-address-button">Send</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<div id="customer-cart-content" class="hidden content">
    <ul id="summary">

    </ul>
    <button id="order-button">Order</button>
</div>
<!-- Restaurant sections -->
<div id="restaurant-home-content" class="hidden content">
    <div class="add-food-to-menu">
        <form class="shipping-form" id="add-food" onsubmit="return false;">
            <div class="city">
                <i class="fas fa-city fa-1x" aria-hidden="true"></i>
                <input type="text" name="name" id="name" placeholder="City">
            </div>
            <div class="address">
                <i class="fas fa-map fa-1x" aria-hidden="true"></i>
                <input type="text" name="image" id="image" placeholder="Address">
            </div>
            <div class="postal-code">
                <i class="fas fa-mail-bulk fa-1x" aria-hidden="true"></i>
                <input type="text" name="category" id="category" placeholder="Postal code">
            </div>
            <div class="postal-code">
                <i class="fas fa-mail-bulk fa-1x" aria-hidden="true"></i>
                <input type="text" name="price" id="price" placeholder="Postal code">
            </div>
            <button id="">Add</button>
        </form>
    </div>
</div>
<div id="restaurant-orders-content" class="hidden content">
    <ul id="orders">

    </ul>
</div>
<div id="restaurant-employees-content" class="hidden content">
    <ul id="users">

    </ul>
</div>
<div id="restaurant-add-food-content" class="hidden content">
    <form class="shipping-form" id="add-food-to-menu-form" onsubmit="return false;">
        <div class="city">
            <i class="fas fa-city fa-1x" aria-hidden="true"></i>
            <input type="text" name="foodname" id="foodname" placeholder="Name">
        </div>
        <div class="address">
            <i class="fas fa-map fa-1x" aria-hidden="true"></i>
            <input type="number" name="foodprice" id="foodprice" min="1" max="100000">
        </div>
        <div class="postal-code">
            <i class="fas fa-mail-bulk fa-1x" aria-hidden="true"></i>
            <input type="text" name="foodimage" id="foodimage" placeholder="Food Image URL">
        </div>
        <div class="postal-code">
            <i class="fas fa-mail-bulk fa-1x" aria-hidden="true"></i>
            <select name="foodcategory" id="foodcategory">
                <option value="appetizers">appetizers</option>
                <option value="soups">soups</option>
                <option value="main-dishes">main-dishes</option>
                <option value="desserts">desserts</option>
            </select>
        </div>
        <button id="insert-food-to-menu-button">Add</button>
    </form>
</div>
</body>
</html>
