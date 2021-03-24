package com.jetbrains.handson.httpapi.model

import kotlinx.serialization.Serializable


@Serializable
data class Customer(var id: Int? = null, val firstName: String, val lastName: String, val email: String)

