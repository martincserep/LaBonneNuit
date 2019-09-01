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
    user = getAuthorization();
    if(user.role=="MANAGER"){
        showContents(['header-content','customer-header-content','customer-home-content','manager-header','employee-header']);
    }
    else if(user.role=="EMPLOYEE") {
        showContents(['header-content','customer-header-content','customer-home-content','employee-header']);

    }
    else {
        showContents(['header-content','customer-header-content','customer-home-content']);
    }

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
    tdDetailEl.classList.add("food-details");
    h3El = document.createElement('h3');
    h3El.innerHTML = food.name;

    ulEl = document.createElement('ul');
    ulEl.appendChild(createLiElement('Price: ' + food.price));
    ulEl.appendChild(createLiElement(food.category));
    ulEl.classList.add("food-details-li");

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
    const params = new URLSearchParams();
    params.append('foodId',id);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onOrderFoodResponse);
    xhr.addEventListener('error',onNetworkError);
    xhr.open('PUT','protected/cart?' + params.toString());
    xhr.send()

}
const items = [];
function onOrderFoodResponse() {
    if(this.status === OK){
        const cartItem = JSON.parse(this.responseText);
        console.log(this.responseText);
        items.push(cartItem);
        const cartItemCount = document.getElementById('cart-count');
        console.log(items);
        document.getElementById('cart-count').innerHTML = items.length;
    }else {
        onOtherResponse();
    }
}

function sendFoodToDatabase() {
    const shippingFormEl = document.forms['add-food-to-menu-form'];

    const nameInputEl = shippingFormEl.querySelector('input[name="foodname"]');
    const imageInputEl = shippingFormEl.querySelector('input[name="foodimage"]');
    const priceInputEl = shippingFormEl.querySelector('input[name="foodprice"]');
    const categoryInputEl = document.getElementById('foodcategory');

    const name = nameInputEl.value;
    const image = imageInputEl.value;
    const price = priceInputEl.value;
    const category = categoryInputEl.options[categoryInputEl.selectedIndex].text;


    const params = new URLSearchParams();
    params.append('name', name);
    params.append('image', image);
    params.append('price', price);
    params.append('category', category);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onAddedFoodToMenu);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/allfoods?' + params.toString());
    xhr.send();
}

function onAddedFoodToMenu() {
    if (this.status === OK){
        clearMessages();
        alert("Done");
    } else{
        onOtherResponse(customerHomeContentDivEl,this)
    }
}

function addFoodToMenu() {
    showContents(['header-content','customer-header-content','manager-header','employee-header','restaurant-add-food-content']);

}
