package com.webshop.db

import org.jetbrains.exposed.sql.Table

object PurchaseTable: Table("purchase") {
    val id = integer("id").autoIncrement()
    val itemId = integer("item_id").references(ShopItemsTable.id)
    val buyersName = text("buyers_name")
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}