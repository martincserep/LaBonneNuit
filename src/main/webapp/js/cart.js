function onCartClicked(){
  const xhr = new XMLHttpRequest();
  xhr.addEventListener('load', onCartResponse);
  xhr.addEventListener('error', onNetworkError);
  xhr.open('GET','protected/cart');
  xhr.send()
}

function onCartResponse(){
  if (this.status === OK) {
    clearMessages();
    user = getAuthorization();
    if(user.role=="MANAGER"){
      showContents(['header-content','customer-header-content','manager-header','employee-header','customer-cart-content']);
    }
    else if(user.role=="EMPLOYEE") {
      showContents(['header-content','customer-header-content','employee-header','customer-cart-content']);
    }
    else {
      showContents(['header-content','customer-header-content','customer-cart-content']);
    }
    showCart(JSON.parse(this.responseText));
  } else{
    onOtherResponse(customerCartContentDivEl,this);
  }
}

function showCart(cartDto){

  console.log(cartDto.cartItems);
  const result = cartDto.cartItems.reduce( (acc, o) => (acc[o.foodName] = (acc[o.foodName] || 0)+1, acc), {} );
  Object.keys(result).map(function(key, index) {
    console.log(Object.keys(result)[index], result[Object.keys(result)[index]])
  });
    const cartItems = cartDto.cartItems;
  const totalPrice = cartDto.price;

  //removeAllChildren(cartTableBodyEl);
  document.getElementById("order-button").disabled = true;
  let message = "Empty";
  document.getElementById("summary").innerHTML = "";
  if (cartItems){
    Object.keys(result).map((key, index) => {
      console.log(Object.keys(result)[index], result[Object.keys(result)[index]]);
      let listItem = document.createElement("li");
      let foodName = Object.keys(result)[index];
      let counter = result[Object.keys(result)[index]];
      listItem.innerHTML = foodName + ", " + counter;
      document.getElementById("summary").append(listItem);
    });
    }
    message = "Price: " +  totalPrice;
    document.getElementById("order-button").disabled = false;
  newInfo(customerCartContentDivEl, message);
}
