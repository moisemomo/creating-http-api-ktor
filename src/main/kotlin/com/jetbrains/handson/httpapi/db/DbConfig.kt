package com.jetbrains.handson.httpapi.db

import org.jetbrains.exposed.sql.Database

object DbConfig {
    val db by lazy {
        Database.connect("jdbc:mysql://localhost:8889/kmpbdd", driver = "com.mysql.cj.jdbc.Driver", user = "root", password = "root")
    }
}