package com.webshop.shopitems.di

import com.webshop.shopitems.repository.PurchaseRepository
import com.webshop.shopitems.repository.PurchaseRepositoryImpl
import com.webshop.shopitems.repository.ShopItemRepository
import com.webshop.shopitems.repository.ShopItemRepositoryImpl
import com.webshop.shopitems.service.PurchaseService
import com.webshop.shopitems.service.PurchaseServiceImpl
import com.webshop.shopitems.service.ShopItemService
import com.webshop.shopitems.service.ShopItemServiceImpl
import org.koin.dsl.module

val shopItemModule = module {

    single<ShopItemService> { ShopItemServiceImpl() }
    single<ShopItemRepository> { ShopItemRepositoryImpl(get()) }

    single<PurchaseService> { PurchaseServiceImpl() }
    single<PurchaseRepository> { PurchaseRepositoryImpl(get()) }
}