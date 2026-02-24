package com.example.application

import io.ktor.server.application.*
import io.github.cdimascio.dotenv.dotenv 
import org.litote.kmongo.KMongo 
import com.mongodb.client.MongoCollection 
import org.bson.Document


data class MongoCollections ( 
    val orders: MongoCollection<Document>, 
    val users: MongoCollection<Document>
)

private fun Application.mongoDatabase(): com.mongodb.client.MongoDatabase {
    val config = environment.config

    val uri = config.property("ktor.mongo.uri").getString()
    val dbName = config.property("ktor.mongo.database").getString()

    val client = KMongo.createClient(uri)
    return client.getDatabase(dbName)
}

fun Application.configureDatabase(): MongoCollections {

    val db = mongoDatabase()

    return MongoCollections(
        users = db.getCollection("users"),
        orders = db.getCollection("orders")
    )
}

