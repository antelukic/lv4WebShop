package com.webshop.shopitems.service

import com.webshop.db.DatabaseFactory.dbQuery
import com.webshop.db.ShopItemsTable
import com.webshop.db.ShopItemsTable.description
import com.webshop.db.ShopItemsTable.price
import com.webshop.shopitems.model.ShopItem
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement

internal class ShopItemServiceImpl : ShopItemService {

    override suspend fun addItem(shopItem: ShopItem): ShopItem? {
        var statement: InsertStatement<Number>? = null

        dbQuery {
            statement = ShopItemsTable.insert {
                it[description] = shopItem.description
                it[image] = shopItem.image
                it[name] = shopItem.name
                it[price] = shopItem.price
            }
        }

        return rowToShopItem(statement?.resultedValues?.first())
    }

    override suspend fun getItems(): List<ShopItem?> {
        var items: List<ShopItem?>? = null

        dbQuery {
            items = ShopItemsTable.selectAll()
                .map(::rowToShopItem)
        }

        return items ?: emptyList()
    }

    override suspend fun item(itemId: Int): ShopItem? {
        var item: ShopItem? = null


        dbQuery {
            item = ShopItemsTable.select {
                ShopItemsTable.id eq itemId
            }.map(::rowToShopItem).firstOrNull()
        }

        return item
    }

    private fun rowToShopItem(row: ResultRow?): ShopItem? =
        row?.let {
            ShopItem(
                id = row[ShopItemsTable.id],
                name = row[ShopItemsTable.name],
                image = row[ShopItemsTable.image],
                description = row[description],
                price = row[price],
            )
        }
}