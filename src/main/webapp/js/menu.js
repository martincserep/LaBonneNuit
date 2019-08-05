function showAppertizers() {
    clearMessages();
    showByCategory("appetizers");
}

function showByCategory(category) {
    clearMessages();
    const params = new URLSearchParams();;
    params.append('category',category);
    xhr.addEventListener('load', allFoodLoadResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET','protected/foodbycategory?' + params.toString());
    xhr.send(params);
}
function showSoups() {
    showByCategory("soups");

}

function showMainDishes() {
    showByCategory("main-dishes");
}

function showDesserts() {
    showByCategory("desserts");
}

function listAllFood(foods) {
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
        const deleteButton = createButtonTr('Add To Cart','Add to Cart');
        deleteButton.onclick = function () { onAddToCartButtonClicked(food.foodId) };
        trEl.appendChild(deleteButton);
        foodsTabEl.appendChild(trEl);
    }
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
    xhr.open('GET','protected/allfoods')
    xhr.send();

}

function generateImageTdEl(food){
    tdImgEl = document.createElement('td');
    img = createImgElement(food.pictureURL,200,200)
    tdImgEl.appendChild(img);
    return tdImgEl;
}

function createImgElement(url, width, height) {
    img = document.createElement('img');
    img.src = url;
    img.width = width;
    img.height = height;
    return img;
}

function generateDetailTdElement(food) {
    tdDetailEl = document.createElement('td');
    h3El = document.createElement('h3');
    h3El.innerHTML = food.name;

    ulEl = document.createElement('ul');
    ulEl.appendChild(createLiElement('Price: ' + food.price));
    ulEl.appendChild(createLiElement(food.category));

    tdDetailEl.appendChild(h3El);
    tdDetailEl.appendChild(ulEl);
    return tdDetailEl;
}

function createLiElement(text) {
    liEl = document.createElement('li');
    liEl.innerHTML = text;
    return liEl;
}

function onAddToCartButtonClicked(id) {
    alert(id);
}