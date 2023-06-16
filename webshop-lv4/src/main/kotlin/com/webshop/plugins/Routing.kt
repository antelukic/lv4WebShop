package com.webshop.plugins

import com.webshop.shopitems.routes.purchaseItemsRoutes
import com.webshop.shopitems.routes.shopItemRoutes
import io.ktor.server.application.*

fun Application.configureRouting() {
    shopItemRoutes()
    purchaseItemsRoutes()
}
