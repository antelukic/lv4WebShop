package com.webshop.shopitems.routes

import com.webshop.shopitems.repository.PurchaseRepository
import com.webshop.shopitems.requests.PurchaseItemRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.purchaseItem(repository: PurchaseRepository) {
    put("shop/purchase") {
        val request = call.receive<PurchaseItemRequest>()
        val response = repository.purchaseItems(request)
        call.respond(response.statusCode, response)
    }
}

fun Application.purchaseItemsRoutes() {
    val repository by inject<PurchaseRepository>()

    routing {
        purchaseItem(repository)
    }
}