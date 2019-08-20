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

    const pdname = document.getElementById('pd-name');
    pdname.textContent = user.firstname + " " + user.lastname;

    const pdemail = document.getElementById('pd-email');
    pdemail.textContent = user.email;

    const pdphone = document.getElementById('pd-phone');
    pdphone.textContent = user.phonenumber;
}
function onShippingAddressSettingButtonClicked() {
    clearMessages();
    if(user.role=="MANAGER"){
        showContents(['header-content','customer-header-content','customer-profile-content','manager-header','employee-header','shipping-addresses-content']);
    }
    else if(user.role=="EMPLOYEE") {
        showContents(['header-content','customer-header-content','customer-profile-content','employee-header','shipping-addresses-content']);
    }
    else {
        showContents(['header-content','customer-header-content','customer-profile-content','shipping-addresses-content']);
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


function getShippingAddress() {
    const user = getAuthorization();

    const userId = user.id;
    console.log(userId);
    const params = new URLSearchParams();

    params.append('userId', userId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', hasShippingAddress);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/profile?');
    xhr.send(params);
}


function hasShippingAddress() {
        const userHasShippingAddress = JSON.parse(this.responseText);
        const shippingFormEl = document.forms['shipping-form'];

        const user = getAuthorization();

        const userId = user.id;
        const cityInputEl = shippingFormEl.querySelector('input[name="city"]');
        const addressInputEl = shippingFormEl.querySelector('input[name="address"]');
        const postalCodeInputEl = shippingFormEl.querySelector('input[name="postal-code"]');

        const city = cityInputEl.value;
        const address = addressInputEl.value;
        const postalCode = postalCodeInputEl.value;

        const params = new URLSearchParams();
        params.append('city', city);
        params.append('address', address);
        params.append('postalCode', postalCode);
        params.append('userId', userId);

        const xhr = new XMLHttpRequest();

        if(userHasShippingAddress){
            xhr.addEventListener('load', onShippingAddressResponse);
            xhr.addEventListener('error', onNetworkError);
            xhr.open('PUT', 'protected/profile?');
            xhr.send(params);
        } else {
            xhr.addEventListener('load', onShippingAddressResponse);
            xhr.addEventListener('error', onNetworkError);
            xhr.open('POST', 'protected/profile?');
            xhr.send(params);
        }
}
function onShippingAddressResponse() {
    if (this.status === OK) {
        console.log("Shipping address modification is successfull!");
    } else {
        onOtherResponse(customerProfileContentDivEl, this);
    }
}
