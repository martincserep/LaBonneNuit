function onCustomerProfileLoad(user) {
    clearMessages();
    showContents(['header-content','customer-header-content','customer-home-content']);

    const userUsernameSpanEl = document.getElementById('user-username');
    const userPasswordSpanEl = document.getElementById('user-password');

    userUsernameSpanEl.textContent = user.username;
    userPasswordSpanEl.textContent = user.password;
}
function onRestaurantProfileLoad(user) {
    clearMessages();
    showContents(['header-content','restaurant-header-content','restaurant-home-content']);

    const userUsernameSpanEl = document.getElementById('user-username');
    const userPasswordSpanEl = document.getElementById('user-password');

    userUsernameSpanEl.textContent = user.username;
    userPasswordSpanEl.textContent = user.password;
}
