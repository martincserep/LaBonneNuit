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
    showContents(['header-content','customer-cart-content']);
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
  cartTableEl = document.getElementById("cart-table");
  cartTableBodyEl = cartTableEl.querySelector("tbody");
  const cartItems = cartDto.cartItems;
  const totalPrice = cartDto.price;

  removeAllChildren(cartTableBodyEl);
  document.getElementById("order-button").disabled = true;
  let message = "Empty";
  if (cartItems.length > 0){
    Object.keys(result).map((key, index) => {
      console.log(Object.keys(result)[index], result[Object.keys(result)[index]]);
      let listItem = document.createElement("li");
      let foodName = Object.keys(result)[index];
      let counter = result[Object.keys(result)[index]];
      listItem.innerHTML = foodName + ", " + counter;
      document.getElementById("ul#summary").append(listItem);
    });
    }
    message = "Price: " +  totalPrice;
    document.getElementById("order-button").disabled = false;
  newInfo(customerCartContentDivEl, message);
}
