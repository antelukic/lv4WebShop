package com.webshop.shopitems.repository

import com.webshop.core.BaseResponse
import com.webshop.shopitems.requests.AddShopItemRequest
import com.webshop.shopitems.requests.toShopItem
import com.webshop.shopitems.service.ShopItemService

internal class ShopItemRepositoryImpl(private val shopItemService: ShopItemService): ShopItemRepository {

    override suspend fun addItem(shopItem: AddShopItemRequest): BaseResponse<Any> = kotlin.runCatching {
        val result = shopItemService.addItem(shopItem.toShopItem())
        return if(result == null) BaseResponse.ErrorResponse(message = "An error occured with adding shop item")
        else  BaseResponse.SuccessResponse(data = result)
    }.getOrElse {exception: Throwable ->
        exception.printStackTrace()
        return BaseResponse.ErrorResponse(message = exception.localizedMessage)
    }

    override suspend fun getItems(): BaseResponse<Any> = kotlin.runCatching {
        val result = shopItemService.getItems()
        return BaseResponse.SuccessResponse(data = result)
    }.getOrElse {exception ->
        exception.printStackTrace()
        return BaseResponse.ErrorResponse(message = exception.localizedMessage)
    }
}