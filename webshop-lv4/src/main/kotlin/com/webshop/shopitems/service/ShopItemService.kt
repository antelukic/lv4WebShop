package com.webshop.shopitems.service

import com.webshop.shopitems.model.ShopItem

interface ShopItemService {

    suspend fun addItem(shopItem: ShopItem): ShopItem?

    suspend fun getItems(): List<ShopItem?>

    suspend fun item(itemId: Int): ShopItem?
}