const OK = 200;
const BAD_REQUEST = 400;
const UNAUTHORIZED = 401;
const NOT_FOUND = 404;
const INTERNAL_SERVER_ERROR = 500;

let loginContentDivEl;
let registerContentDivEl;
let profileContentDivEl;
let customerHomeContentDivEl;
let customerReservationContentDivEl;
let customerCartContentDivEl;
let customerProfileContentDivEl;
let restaurantHomeContentDivEl;
let restaurantOrdersContentDivEl;
let restaurantEmployeesContentDivEl;
let logoutContentDivEl;

function newInfo(targetEl, message) {
    newMessage(targetEl, 'info', message);
}

function newError(targetEl, message) {
    newMessage(targetEl, 'error', message);
}

function newMessage(targetEl, cssClass, message) {
    clearMessages();

    const pEl = document.createElement('p');
    pEl.classList.add('message');
    pEl.classList.add(cssClass);

    targetEl.appendChild(pEl);
}

function clearMessages() {
    const messageEls = document.getElementsByClassName('message');
    for (let i = 0; i < messageEls.length; i++) {
        const messageEl = messageEls[i];
        messageEl.remove();
    }
}

function showContents(ids) {
    const contentEls = document.getElementsByClassName('content');
    for (let i = 0; i < contentEls.length; i++) {
        const contentEl = contentEls[i];
        if (ids.includes(contentEl.id)) {
            contentEl.classList.remove('hidden');
        } else {
            contentEl.classList.add('hidden');
        }
    }
}

function removeAllChildren(el) {
    while (el.firstChild) {
        el.removeChild(el.firstChild);
    }
}

function onNetworkError(response) {
    document.body.remove();
    const bodyEl = document.createElement('body');
    document.appendChild(bodyEl);
    newError(bodyEl, 'Network error, please try reloaing the page');
}

function onOtherResponse(targetEl, xhr) {
    if (xhr.status === NOT_FOUND) {
        newError(targetEl, 'Not found');
        console.error(xhr);
    } else {
        const json = JSON.parse(xhr.responseText);
        if (xhr.status === INTERNAL_SERVER_ERROR) {
            newError(targetEl, `Server error: ${json.message}`);
        } else if (xhr.status === UNAUTHORIZED || xhr.status === BAD_REQUEST) {
            newError(targetEl, json.message);
        } else {
            newError(targetEl, `Unknown error: ${json.message}`);
        }
    }
}

function hasAuthorization() {
    return localStorage.getItem('user') !== null;
}

function setAuthorization(user) {
    return localStorage.setItem('user', JSON.stringify(user));
}

function getAuthorization() {
    return JSON.parse(localStorage.getItem('user'));
}

function setUnauthorized() {
    return localStorage.removeItem('user');
}

function onLoad() {
    loginContentDivEl = document.getElementById('login-content');
    registerContentDivEl = document.getElementById('register-content');
    profileContentDivEl = document.getElementById('profile-content');
    customerHomeContentDivEl = document.getElementById('customer-home-content');
    customerReservationContentDivEl = document.getElementById('customer-order-content');
    customerProfileContentDivEl = document.getElementById('customer-profile-content');
    customerCartContentDivEl = document.getElementById('customer-cart-content');
    restaurantHomeContentDivEl = document.getElementById('restaurant-home-content');
    restaurantOrdersContentDivEl = document.getElementById('restaurant-orders-content');
    restaurantEmployeesContentDivEl = document.getElementById('restaurant-employees-content');
    logoutContentDivEl = document.getElementById('logout-content');
    const loginButtonEl = document.getElementById('login-button');
    loginButtonEl.addEventListener('click', onLoginButtonClicked);
    const registrationButtonEl = document.getElementById('registration-button');
    registrationButtonEl.addEventListener('click', onRegistrationButtonClicked);
    const backtologinButtonEl = document.getElementById('back-to-login-button');
    backtologinButtonEl.addEventListener('click', onBackToLoginButtonClicked);
    const personalDatasButtonEl = document.getElementById('my-personal-datas-button');
    personalDatasButtonEl.addEventListener('click', onPersonalDatasSettingButtonClicked);
    const shippingAddressesButtonEl = document.getElementById('shipping-address-button');
    shippingAddressesButtonEl.addEventListener('click', onShippingAddressSettingButtonClicked);
    const deleteProfileButtonEl = document.getElementById('delete-registration-button');
    deleteProfileButtonEl.addEventListener('click', onDeleteRegistrationButtonClicked);
    const goToProfileButtonEl = document.getElementById('go-to-profile-button');
    goToProfileButtonEl.addEventListener('click', onProfilePageButtonClicked);
    const logoutButtonEl = document.getElementById('logout-button');
    logoutButtonEl.addEventListener('click', onLogoutButtonClicked);
    const goToCartButtonEl = document.getElementById('go-to-cart-button');
    goToCartButtonEl.addEventListener('click',onCartClicked);
    const goToEmpleyeesButtonEl = document.getElementById('show-employees');
    goToEmpleyeesButtonEl.addEventListener('click',onGoToEmplyeesClicked);
    const updateShippingAddressButtonEl = document.getElementById('update-shipping-address-button');
    updateShippingAddressButtonEl.addEventListener('click',getShippingAddress);


    function getAuthorization() {
        return JSON.parse(localStorage.getItem('user'));
    }


    if (hasAuthorization()) {
        onCustomerProfileLoad(getAuthorization());
    }
}

function createButtonTd(name, value) {
    const buttonEl = document.createElement('input');
    buttonEl.setAttribute('type', 'button');
    buttonEl.setAttribute('name', name);
    buttonEl.setAttribute('value', value);

    const tdEl = document.createElement('td');
    tdEl.appendChild(buttonEl);
    return tdEl;
}


function createButtonTr(name, value) {
    const buttonEl = document.createElement('input');
    buttonEl.classList.add("add-to-cart-button");
    buttonEl.setAttribute('type', 'button');
    buttonEl.setAttribute('name', name);
    buttonEl.setAttribute('value', value);

    const trEl = document.createElement('tr');
    trEl.classList.add("add-to-cart-tr");
    trEl.appendChild(buttonEl);
    return trEl;
}


document.addEventListener('DOMContentLoaded', onLoad);
