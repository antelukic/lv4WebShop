package com.webshop.shopitems.repository

import com.webshop.core.BaseResponse
import com.webshop.shopitems.requests.PurchaseItemRequest
import com.webshop.shopitems.service.PurchaseService

internal class PurchaseRepositoryImpl(private val purchaseService: PurchaseService): PurchaseRepository {

    override suspend fun purchaseItems(request: PurchaseItemRequest): BaseResponse<Any> = kotlin.runCatching {
        return if(purchaseService.purchaseItems(request)) BaseResponse.SuccessResponse(data = true)
        else BaseResponse.SuccessResponse(data = false)
    }.getOrElse {exception ->
        exception.printStackTrace()
        return BaseResponse.ErrorResponse(message = exception.localizedMessage)
    }
}