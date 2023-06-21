// Get elements
const cartButton = document.querySelector(".cart-button");
const cartBadge = document.querySelector(".cart-badge");
const modal = document.querySelector(".modal");
const modalClose = document.querySelector(".close");
const buyButton = document.querySelector(".buy-btn");
const cartItemsList = document.querySelector(".cart-items");
const cartTotal = document.querySelector(".cart-total");
const itemsGrid = document.querySelector(".items-grid");
const itemsGridModal = document.querySelector(".items-grid-modal");
const addToCartButtons = document.querySelectorAll(".add-to-cart-btn");
const messageModal = document.querySelector(".message-modal");
const closeBtn = document.querySelector(".close-btn");
const select = document.querySelector(".select-sort");
const purchaseMessage = document.getElementById("purchase-message");
const itemModal = document.querySelector(".item-info-modal");
const itemModalContent = document.querySelector(".item-info-modal-content");

async function fetchData() {
  const response = await fetch("https://lv4webshop-production.up.railway.app/shop/items");
  const data = await response.json();
  items = await data.data;
}

let items = [];

let cart = [];

// An example function that creates HTML elements using the DOM.
async function fillItemsGrid(shouldLoadFromApi) {
  if (shouldLoadFromApi == true) {
    await fetchData();
  }
  for (const item of items) {
    let itemElement = document.createElement("div");
    itemElement.classList.add("item");
    itemElement.innerHTML = `
    <div class="item-card" onclick="itemInfoModal(${item.id})">
    <img src="${item.image}" alt="${item.name}">
    <h2>${item.name}</h2>
    <p>$${item.price}</p>
    <button class="add-to-cart-btn btn-small" data-id="${item.id}" onClick="{addToCartClicked(${item.id})}">Add to cart</button>
    </div>
            `;
    itemsGrid.appendChild(itemElement);
  }
}

// Adding the .show-modal class to an element will make it visible
// because it has the CSS property display: block; (which overrides display: none;)
// See the CSS file for more details.
function toggleModal() {
  modal.classList.toggle("show-modal");
  updateCartPrice();
  appendItemsGridModal();
}

function appendItemsGridModal() {
  const cartToDisplay = [...new Set(cart.map((item) => item[0]))];
  for (const item of cartToDisplay) {
    let itemElement = document.createElement("div");
    itemElement.classList.add("item");
    itemElement.innerHTML = `
        <img src="${item.image}" alt="${item.name}">
        <h2>${item.name}</h2>
        <p>Qty: ${updateCartItemQty(item)}</p>
        <p>$${updateCartItemPrice(item.id)}</p>
        <button class="btn-small" data-id="${
          item.id
        }" onClick="{removeItemFromCart(${item.id})}">Remove</button>
        `;
    itemsGridModal.appendChild(itemElement);
  }
}

function removeItemFromCart(itemId) {
  var index = cart.findIndex((item) => item[0].id == itemId);
  if (index !== -1) {
    cart.splice(index, 1);
  }
  itemsGridModal.innerHTML = "";
  updateCartBadgeValue();
  updateCartPrice();
  appendItemsGridModal();
  updateCartItemQty(cart.filter((cartItem) => cartItem.id == itemId));
  updateCartItemPrice(itemId);
}

function updateCartItemPrice(itemId) {
  var sum = 0;
  cart
    .filter((cartItem) => {
      if (cartItem[0].id == itemId) return true;
    })
    .forEach((cartItem) => {
      sum = sum + cartItem[0].price;
    });
  return sum;
}

function updateCartItemQty(item) {
  return cart.filter((cartItem) => {
    if (cartItem[0].id == item.id) return true;
  }).length;
}

function addToCartClicked(itemId) {
  cart.push(items.filter((item) => item.id == itemId));
  updateCartBadgeValue();
}

function updateCartBadgeValue() {
  document.getElementById("cart-badge").innerHTML = cart.length;
}

function updateCartPrice() {
  var sum = 0;
  cart.forEach((item) => (sum += item[0].price));
  document.getElementById("cart-total").innerHTML = `$${sum}`;
}

async function buyItems() {
  let items = [];
  cart.forEach((item) => {
    items.push({
      itemId: item[0].id,
      buyersName: "Ante Lukic",
    });
  });
  const body = { items: items };

  const requestOptions = {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  };
  const response = await fetch(
    "https://lv4webshop-production.up.railway.app/shop/purchase",
    requestOptions
  );
  const data = await response.json();
  return data.data;
}

async function toggleMessageModal() {
  const response = await buyItems();
  if (response == true) {
    purchaseMessage.textContent = "You have successfully purchased your items!";
  } else {
    purchaseMessage.textContent =
      "An error occurred with your purchase. Please try again later!";
  }
  messageModal.classList.toggle("show-modal");
  modal.classList.toggle("show-modal");
  cart = [];
  updateCartBadgeValue();
  updateCartPrice();
  itemsGridModal.innerHTML = "";
}

function sortItems() {
  if (select.value == "Sort price low to high") {
    items = items.sort((a, b) => a.price - b.price);
  }
  if (select.value == "Sort price high to low") {
    items = items.sort((a, b) => b.price - a.price);
  }
  itemsGrid.innerHTML = "";
  fillItemsGrid(false);
}

function itemInfoModal(itemId) {
  const item = items.filter((item) => item.id == itemId)[0];
  let itemElement = document.createElement("div");
  itemElement.classList.add("item");
  itemElement.innerHTML = `
            <img src="${item.image}" alt="${item.name}">
            <h2>${item.name}</h2>
            <p>$${item.price}</p>
            <p>${item.description}</p>
            <button class="add-to-cart-btn btn-small" data-id="${item.id}" onClick="{addToCartClicked(${item.id})}">Add to cart<button>
            <button class="close-btn-item-info btn-small" onClick="{closeItemInfoModal()}">Close</button>
            `;
  itemModalContent.appendChild(itemElement);
  itemModal.classList.toggle("show-modal");
}

function closeItemInfoModal() {
  itemModal.classList.toggle("show-modal");
  itemModalContent.innerHTML = "";
}

// Call fillItemsGrid function when page loads
fillItemsGrid(true);
updateCartBadgeValue();

// Example of DOM methods for adding event handling
cartButton.addEventListener("click", toggleModal);
modalClose.addEventListener("click", toggleModal);
buyButton.addEventListener("click", toggleMessageModal);
closeBtn.addEventListener("click", toggleMessageModal);
select.addEventListener("change", sortItems);
