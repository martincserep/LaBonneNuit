function onCustomerProfileLoad(user) {
    clearMessages();
    const profileToUserFistName = document.getElementById('user-first-name');
    profileToUserFistName.textContent = user.firstname;
    showContents(['header-content','customer-header-content','customer-home-content']);
}

function onProfilePageButtonClicked(user) {
    clearMessages();
    onPersonalDatasSettingButtonClicked()
}

function onPersonalDatasSettingButtonClicked() {
    clearMessages();
    showContents(['header-content','customer-header-content','customer-profile-content','"my-personal-datas-content']);

    const profileSettingsBoxHeaderTitleEl = document.getElementById('profile-settings-header-title');

    profileSettingsBoxHeaderTitleEl.textContent = 'Personal Datas';
}
function onShippingAddressSettingButtonClicked() {
    clearMessages();
    showContents(['header-content','customer-header-content','customer-profile-content','shipping-address-content']);

    const profileSettingsBoxHeaderTitleEl = document.getElementById('profile-settings-header-title');

    profileSettingsBoxHeaderTitleEl.textContent = 'Shipping Addresses';
}

function onDeleteRegistrationButtonClicked() {
    
}

function onRestaurantProfileLoad(user) {
    clearMessages();
    showContents(['header-content','restaurant-header-content','restaurant-home-content']);

    const userUsernameSpanEl = document.getElementById('user-username');
    const userPasswordSpanEl = document.getElementById('user-password');

    userUsernameSpanEl.textContent = user.username;
    userPasswordSpanEl.textContent = user.password;
}
