function onGoToOrdersClicked(){
    clearMessages();
    checkRoleToViewOrders();
}
function checkRoleToViewOrders(){
    if(user.role=="MANAGER"){
        getOrders();
        showContents(['header-content','customer-header-content','restaurant-orders-content','manager-header','employee-header']);
    } else if(user.role=="EMPLOYEE"){
        getOrders();
        showContents(['header-content','customer-header-content','restaurant-orders-content','employee-header']);

    }
    else {
        alert("You are not working in this restaurant");
    }
}


function listOrdersResponse() {
    if (this.status === OK) {
        console.log(JSON.parse(this.responseText));
        listOrders(JSON.parse(this.responseText));
    } else{
        onOtherResponse(customerCartContentDivEl,this);
    }
}
function listOrders(orders) {
    document.getElementById("orders").innerHTML = "";
    for (let i = 0; i < orders.length; i++) {
        let orderObject = orders[i];
        console.log(orderObject);
        let listItem = document.createElement("li");
        const user = orderObject.name + " " +orderObject.address + " " +orderObject.orderedFood + " " +orderObject.price + " " + orderObject.isFinished;
        listItem.innerHTML = user;
        const button = createFinishButton(orderObject.orderId);
        const breakLine = '</br>';
        document.getElementById("orders").append(listItem);
        document.getElementById("orders").append(button);
        //document.getElementById("orders").append(breakLine);
    }
}

function createFinishButton(orderId) {
    const promoteButton = document.createElement("input");
    promoteButton.type = "button";
    promoteButton.value = "Finish order";
    promoteButton.onclick = function () { changeOrderStatus(orderId) };
    return promoteButton;

}

function changeOrderStatus(orderId) {
    const status = confirm("Are you sure about that?");
    const params = new URLSearchParams();
    params.append('orderId', orderId);
    if (status == true){
        const xhr = new XMLHttpRequest();
        xhr.addEventListener('load', userRoleChangeResponse);
        xhr.addEventListener('error', onNetworkError);
        xhr.open('PUT', 'protected/orders?' + params.toString());
        xhr.send(params);
    }

}

function userRoleChangeResponse() {
    if (this.status === OK) {
        clearMessages();
        const orderId = JSON.parse(this.responseText);
        alert("Order with " + orderId + "id is now finished");
    } else{
        onOtherResponse(customerCartContentDivEl,this);
    }

}

function getOrders() {
    clearMessages();
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', listOrdersResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/orders');
    xhr.send();
}

function sendOrders() {
    clearMessages();
    const user = getAuthorization();
    const params = new URLSearchParams();
    const userId = user.id;
    params.append('userId', userId);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', listOrdersResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/orders?' + params.toString());
    xhr.send();
}
