package com.webshop.db

import org.jetbrains.exposed.sql.Table

object ShopItemsTable: Table("shop_items") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 256)
    val description = text("description")
    val price = integer("price")
    val image = varchar("image", 512)
    override val primaryKey = PrimaryKey(id)
}