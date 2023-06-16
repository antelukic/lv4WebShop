package com.webshop.shopitems.model

data class ShopItem(
    val id: Int = Int.MAX_VALUE,
    val name: String,
    val description: String,
    val price: Int,
    val image: String
)