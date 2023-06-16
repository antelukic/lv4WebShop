package com.webshop.plugins


import com.webshop.shopitems.di.shopItemModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(
            listOf(
                shopItemModule
            )
        )
    }
}
