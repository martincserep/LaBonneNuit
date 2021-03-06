function onLoginResponse() {
    if (this.status === OK) {
        const user = JSON.parse(this.responseText);
        setAuthorization(user);
        onCustomerProfileLoad(user);
    } else {
        onOtherResponse(loginContentDivEl, this);
    }
}

function showHomePage() {
    clearMessages();
    if(user.role=="MANAGER"){
        showContents(['header-content','customer-header-content','customer-home-content','manager-header','employee-header']);
    }
    else if(user.role=="EMPLOYEE") {
        showContents(['header-content','customer-header-content','customer-home-content','employee-header']);

    }
    else {
        showContents(['header-content','customer-header-content','customer-home-content']);
    }
}

function onLoginButtonClicked() {
    const loginFormEl = document.forms['login-form'];

    const usernameInputEl = loginFormEl.querySelector('input[name="username"]');
    const passwordInputEl = loginFormEl.querySelector('input[name="password"]');

    const username = usernameInputEl.value;
    const password = passwordInputEl.value;

    const params = new URLSearchParams();
    params.append('username', username);
    params.append('password', password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoginResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'login');
    xhr.send(params);
}

function onGoToRegisterButtonClicked(){
    showContents(['register-content']);
}
