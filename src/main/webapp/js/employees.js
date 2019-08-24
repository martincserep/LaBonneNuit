function onGoToUsersClicked(){
    clearMessages();
    checkRoleToViewEmployees();
}
function checkRoleToViewEmployees(){
     if(user.role=="MANAGER"){
        getUsers();
        showContents(['header-content','customer-header-content','restaurant-employees-content','manager-header','employee-header']);
    }
    else {
        alert("You are not the manager");
    }
}


function listUsersResponse() {
    if (this.status === OK) {
        console.log(JSON.parse(this.responseText));
        listUsers(JSON.parse(this.responseText));
    } else{
        onOtherResponse(customerCartContentDivEl,this);
    }
}
function listUsers(users) {
    console.log(users);
    document.getElementById("users").innerHTML = "";
    for (let i = 0; i < users.length; i++) {
        let userObject = users[i];
        console.log(userObject);
        let listItem = document.createElement("li");
        const user = userObject.firstname + " " + userObject.lastname + " " +userObject.phonenumber + " " +userObject.email + " " +userObject.city + " " +userObject.postalCode + " " +userObject.address + " " +userObject.role + " "+ createButton(userObject.id);
        listItem.innerHTML = user;
        document.getElementById("users").append(listItem);
    }
}

function createButton(userId) {
    var promoteButton = document.createElement("input");
    promoteButton.type = "button";
    promoteButton.value = "Change Role";
    //promoteButton.onclick = onclick = function () { changeUserRole(userId) };
    return promoteButton;

}

function changeUserRole(userId) {
    var role = confirm("Do you want to promote him/her?");
    const params = new URLSearchParams();
    params.append('userId', userId);
    params.append('role', 'false');
    if (role == true){
        params.append('role', 'true');
    }
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', userRoleChangeResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('PUT', 'protected/profile?' + params.toString());
    xhr.send(params);
}

function userRoleChangeResponse() {
    if (this.status === OK) {
        clearMessages();
        var currUser = JSON.parse(this.responseText);
        alert("is now " + currUser[1]);
        localStorage.setItem('user', JSON.stringify(user));
    } else{
        onOtherResponse(customerCartContentDivEl,this);
    }

}

function getUsers() {
    clearMessages();
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', listUsersResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/profile');
    xhr.send();
}
