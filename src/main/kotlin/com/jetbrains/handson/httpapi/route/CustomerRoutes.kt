package com.jetbrains.handson.httpapi.route

import com.jetbrains.handson.httpapi.db.CustomerDAO
import com.jetbrains.handson.httpapi.model.Customer
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.customerRouting() {
    route("/customer") {
        get {
            if (CustomerDAO.getAllCustomers().isNotEmpty()) {
                call.respond(CustomerDAO.getAllCustomers())
            } else {
                call.respondText("No customers Found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call
                .respondText("Missing or malformed id", status = HttpStatusCode.BadRequest)

            val customer = CustomerDAO.getCustomerById(id.toInt()) ?: return@get call
                .respondText("No customer with id $id", status = HttpStatusCode.NotFound)

            call.respond(customer)
        }

        post {
            val customer = call.receive<Customer>()
            try {
                val created = CustomerDAO.createCustomer(customer)
                call.respond("Customer stored correctly $created")
            } catch (e: Throwable) {

                call.respondText("Error, ${e.message}", status = HttpStatusCode.Conflict)
            }
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (CustomerDAO.deleteCustomerById(id.toInt())) {
                call.respondText("Customer removed correctly")
            } else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun Application.registerCustomerRoute() {
    routing {
        customerRouting()
    }
}