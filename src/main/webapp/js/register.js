
function onRegisterResponse() {
    clearMessages();
    if (this.status === OK) {
    } else {
        onOtherResponse(registerContentDivEl, this);
    }
}

function onRegistrationButtonClicked() {
    const registrationFormEl = document.forms['registration-form'];

    const firstNameInputEl = registrationFormEl.querySelector('input[name="firstname"]');
    const lastNameInputEl = registrationFormEl.querySelector('input[name="lastname"]');
    const phoneNumberInputEl = registrationFormEl.querySelector('input[name="phonenumber"]');
    const emailInputEl = registrationFormEl.querySelector('input[name="email"]');
    const usernameInputEl = registrationFormEl.querySelector('input[name="username"]');
    const passwordInputEl = registrationFormEl.querySelector('input[name="password"]');
    const repasswordInputEl = registrationFormEl.querySelector('input[name="repassword"]');

    const firstName = firstNameInputEl.value;
    const lastName = lastNameInputEl.value;
    const phoneNumber = phoneNumberInputEl.value;
    const email = emailInputEl.value;
    const username = usernameInputEl.value;
    const password = passwordInputEl.value;
    const repassword = repasswordInputEl.value;

    if(password!=repassword){
        alert("Elbasztad")
    }

    const params = new URLSearchParams();
    params.append('firstname',firstName);
    params.append('lastname',lastName);
    params.append('phonenumber',phoneNumber);
    params.append('email',email);
    params.append('username', username);
    params.append('password', password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onRegisterResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'registration');
    xhr.send(params);
}

function onBackToLoginButtonClicked() {
    showContents(['login-content']);
}
