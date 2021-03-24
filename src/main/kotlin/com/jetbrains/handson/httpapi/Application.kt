package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.db.DbConfig
import com.jetbrains.handson.httpapi.route.registerCustomerRoute
import com.jetbrains.handson.httpapi.route.registerOrdersModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*


fun main(args: Array<String>) {
    DbConfig.db
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
    }
    registerCustomerRoute()
    registerOrdersModule()
}
