package com.webshop.shopitems.requests

import com.webshop.shopitems.model.ShopItem

data class AddShopItemRequest(
    val image: String,
    val price: Int,
    val description: String,
    val name: String
)

fun AddShopItemRequest.toShopItem() =
    ShopItem(
        image = image,
        price = price,
        description = description,
        name = name,
    )
