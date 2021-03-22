package com.jetbrains.handson.httpapi.route

import com.jetbrains.handson.httpapi.model.orderStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.listOrdersRouting() {
        get("/order") {
            if (orderStorage.isNotEmpty()) {
                call.respond(orderStorage)
            } else {
                call.respondText("No Order Found", status = HttpStatusCode.NotFound)
            }
        }
}

fun Route.getOrderRoute() {
    get("/order/{id}") {
        val id = call.parameters["id"] ?: return@get call
            .respondText("Bad request", status = HttpStatusCode.BadRequest)

        val order = orderStorage.find { it.number == id } ?: return@get call
            .respondText("No order with id $id", status = HttpStatusCode.NotFound)
        call.respond(order)
    }
}

fun Route.totalOrderRoute() {
    get("/order/{id}/total") {
        val id = call.parameters["id"] ?: return@get call
            .respondText("Bad Request", status = HttpStatusCode.BadRequest)

        val order = orderStorage.find { it.number == id } ?: return@get call
            .respondText("No order with id $id", status = HttpStatusCode.NotFound)

        val total = order.contents.map { it.price * it.amount }.sum()
        call.respond(total)
    }
}

fun Application.registerOrdersModule() {
    routing {
        listOrdersRouting()
        getOrderRoute()
        totalOrderRoute()
    }
}