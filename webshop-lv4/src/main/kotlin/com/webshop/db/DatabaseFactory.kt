package com.webshop.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val url = "jdbc:mysql://root:wUI5bSeNNF480rMfuT79@containers-us-west-60.railway.app:6964/railway"
        val user = "root"
        val password = "wUI5bSeNNF480rMfuT79"
        val driverClassName = "com.mysql.cj.jdbc.Driver"
        val defaultDatabase = "railway"
        val connectionPool = createHikariDataSource(
            url = "$url?user=$user&password=$password",
            driver = driverClassName,
        )
        val database = Database.connect(connectionPool)
        transaction(database) {
            SchemaUtils.create(ShopItemsTable)
        }
    }

    private fun createHikariDataSource(
        url: String,
        driver: String,
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 15
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        maxLifetime = 0
        validate()
    })

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}