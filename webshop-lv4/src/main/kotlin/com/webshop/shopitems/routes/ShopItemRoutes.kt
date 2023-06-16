package com.webshop.shopitems.routes

import com.webshop.shopitems.repository.ShopItemRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getShopItems(repository: ShopItemRepository) {
    get("shop/items") {
        val response = repository.getItems()
        call.respond(response.statusCode, response)
    }
}

/*fun Route.addShopItems(repository: ShopItemRepository) {
    put("shop/add/items") {
        val request = call.receive<AddShopItemRequest>()
        val response = repository.addItem(request)
        call.respond(response.statusCode, response)
    }
}*/

fun Application.shopItemRoutes() {
    val repository by inject<ShopItemRepository>()

    routing {
        getShopItems(repository)
    }
}
