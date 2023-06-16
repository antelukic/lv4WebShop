package com.webshop.shopitems.model

data class PurchaseItem(
    val id: Int = Int.MAX_VALUE,
    val buyersName: String,
    val itemId: Int,
)
