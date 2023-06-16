package com.webshop.shopitems.repository

import com.webshop.core.BaseResponse
import com.webshop.shopitems.requests.AddShopItemRequest

interface ShopItemRepository {

    suspend fun addItem(shopItem: AddShopItemRequest): BaseResponse<Any>

    suspend fun getItems(): BaseResponse<Any>
}