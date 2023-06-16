package com.webshop.shopitems.service

import com.webshop.db.DatabaseFactory
import com.webshop.db.PurchaseTable
import com.webshop.shopitems.requests.PurchaseItemRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement

internal class PurchaseServiceImpl : PurchaseService {

    override suspend fun purchaseItems(request: PurchaseItemRequest): Boolean {
        val statement: MutableList<InsertStatement<Number>?> = mutableListOf()

        request.items.forEach { item ->
            DatabaseFactory.dbQuery {
                statement.add(PurchaseTable.insert {
                    it[id] = item.id
                    it[itemId] = item.itemId
                    it[buyersName] = item.buyersName
                })
            }
        }

        return statement.isEmpty().not()
    }
}