package com.webshop.shopitems.service

import com.webshop.shopitems.requests.PurchaseItemRequest

interface PurchaseService {

    suspend fun purchaseItems(request: PurchaseItemRequest): Boolean
}