package com.webshop.shopitems.repository

import com.webshop.core.BaseResponse
import com.webshop.shopitems.requests.PurchaseItemRequest

interface PurchaseRepository {

    suspend fun purchaseItems(request: PurchaseItemRequest): BaseResponse<Any>
}