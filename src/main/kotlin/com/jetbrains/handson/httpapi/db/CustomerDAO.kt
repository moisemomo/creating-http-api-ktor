package com.jetbrains.handson.httpapi.db

import com.jetbrains.handson.httpapi.db.model.CustomerDBItem
import com.jetbrains.handson.httpapi.mappers.CustomerMapper
import com.jetbrains.handson.httpapi.mappers.toCustomer
import com.jetbrains.handson.httpapi.model.Customer
import org.jetbrains.exposed.sql.transactions.transaction


object CustomerDAO {

    fun createCustomer(customer: Customer): Customer {
        transaction {
                CustomerDBItem.new {
                    this.firstName = customer.firstName
                    this.lastName = customer.lastName
                    this.email = customer.email
                }
        }
        return customer
    }

    fun getAllCustomers() = transaction {  CustomerDBItem.all().sortedBy { it.lastName }.map(CustomerMapper()::map) }

    fun getCustomerById(customerId: Int) = transaction { CustomerDBItem.findById(customerId)?.toCustomer() }

    fun deleteCustomerById(customerId: Int): Boolean {
        var response = false
         transaction {
            val customer = CustomerDBItem.findById(customerId)

            customer?.let {
                it.delete()
                response = true
            } ?: kotlin.run { response =  false }

        }
        return response
    }
}