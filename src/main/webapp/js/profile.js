function onCustomerProfileLoad(user) {
    clearMessages();
    const profileToUserFistName = document.getElementById('user-first-name');
    profileToUserFistName.textContent = user.firstname;
    showAllFood();
}

function onProfilePageButtonClicked(user) {
    clearMessages();
    onPersonalDatasSettingButtonClicked()
}

function onPersonalDatasSettingButtonClicked() {
    clearMessages();
    user = getAuthorization();
    if(user.role=="MANAGER"){
        showContents(['header-content','customer-header-content','customer-profile-content','manager-header','employee-header','my-personal-datas-content']);
    }
    else if(user.role=="EMPLOYEE") {
        showContents(['header-content','customer-header-content','customer-profile-content','employee-header','my-personal-datas-content']);
    }
    else {
        showContents(['header-content','customer-header-content','customer-profile-content','my-personal-datas-content']);
    }
    const profileSettingsBoxHeaderTitleEl = document.getElementById('profile-settings-header-title');

    profileSettingsBoxHeaderTitleEl.textContent = 'Personal Datas';
}
function onShippingAddressSettingButtonClicked() {
    clearMessages();
    if(user.role=="MANAGER"){
        showContents(['header-content','customer-header-content','customer-profile-content','manager-header','employee-header','shipping-address-content']);
    }
    else if(user.role=="EMPLOYEE") {
        showContents(['header-content','customer-header-content','customer-profile-content','employee-header','shipping-address-content']);
    }
    else {
        showContents(['header-content','customer-header-content','customer-profile-content','shipping-address-content']);
    }

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

function deleteUser() {
    if (confirm("Are you sure about that?")) {
        const params = new URLSearchParams();
        const user = getAuthorization();
        const id = user.id;
        params.append('userId',id);
        const xhr = new XMLHttpRequest();
        xhr.addEventListener('load', onLogoutButtonClicked);
        xhr.addEventListener('error', onNetworkError);
        xhr.open('DELETE', 'protected/profile?' + params.toString());
        xhr.send();
    } else {
        alert("Then why did you click on it?");
    }
}
