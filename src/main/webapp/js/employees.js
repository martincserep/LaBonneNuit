function onGoToEmplyeesClicked(){
    clearMessages();
    checkRoleToViewEmployees();
    listEmployees();
}
function checkRoleToViewEmployees(){
    user = getAuthorization();
    if(user.role=="MANAGER"){
        showContents(['header-content','customer-header-content','restaurant-employees-content','manager-header','employee-header']);
    }
    else {
        alert("You are not the manager");
    }
}

function listEmployees() {
    
}
