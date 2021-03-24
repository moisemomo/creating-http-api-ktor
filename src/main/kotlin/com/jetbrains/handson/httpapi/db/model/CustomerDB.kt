package com.jetbrains.handson.httpapi.db.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object CustomerDB : IntIdTable() {
    val firstName = varchar("firstName", 50)
    val lastName = varchar("lastName", 50)
    val email = varchar("email", 70).uniqueIndex()
}

class CustomerDBItem(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CustomerDBItem>(CustomerDB)
    var firstName by CustomerDB.firstName
    var lastName by CustomerDB.lastName
    var email by CustomerDB.email
}