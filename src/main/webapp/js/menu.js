function showAppertizers() {
    clearMessages();

    showContents(['header-content','customer-header-content','customer-appetizers-content']);
}

function showSoups() {
    clearMessages();

    showContents(['header-content','customer-header-content','customer-soups-content']);
}

function showMainDishes() {
    clearMessages();

    showContents(['header-content','customer-header-content','customer-main-dishes-content']);
}

function showDesserts() {
    clearMessages();

    showContents(['header-content','customer-header-content','customer-desserts-content']);
}

function listAllFood(foods)
    showContents(['header-content','customer-header-content','customer-home-content']);
    foodsTabEl = document.getElementById('menu-table');
    removeAllChildren(foodsTabEl);
    for (let i = 0; i < foods.length; i++){
        let food = foods[i];
        trEl = document.createElement('tr');
        imgTdEl = generateImageTdEl(food);
        tdDetailEl = generateDetailTdElement(food);
        trEl.appendChild(imgTdEl);
        trEl.appendChild(tdDetailEl);
        foodsTabEl.appendChild(trEl);
    }

function allFoodLoadResponse(){
    if (this.status === OK){
        clearMessages();
        listAllFood(JSON.parse(this.responseText));
    } else{
        onOtherResponse(customerHomeContentDivEl,this)
    }
}

function showAllFood() {
    clearMessages();
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', allFoodLoadResponse)
    xhr.addEventListener('error', onNetworkError)
    xhr.open('GET','protected/allfoods?')
    xhr.send();

}

function generateImageTdEl(food){
    tdImgEl = document.createElement('td');
    img = createImgElement(food.pictureURL,200,200)
    tdImgEl.appendChild(img);
    return tdImgEl;
}
