package com.jetbrains.handson.httpapi.mappers

import com.jetbrains.handson.httpapi.db.model.CustomerDBItem
import com.jetbrains.handson.httpapi.model.Customer

fun CustomerDBItem.toCustomer() = Customer(id.value, firstName,lastName, email)

class CustomerMapper {
    fun map(customerDb: CustomerDBItem): Customer {
        return Customer(customerDb.id.value, customerDb.firstName, customerDb.lastName, customerDb.email)
    }
}