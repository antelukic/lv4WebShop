package com.webshop.shopitems.requests

import com.webshop.shopitems.model.PurchaseItem

data class PurchaseItemRequest(
    val items: List<PurchaseItem>
)
