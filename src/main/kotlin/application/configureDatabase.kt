package com.example.application

import io.ktor.server.application.*
import io.github.cdimascio.dotenv.dotenv 
import org.litote.kmongo.KMongo 
import com.mongodb.client.MongoCollection 
import org.bson.Document

data class MongoCollections(
    val users: MongoCollection<Document>
)

    fun Application.configureDatabase(): MongoCollections {

    val config = environment.config

    val uri = config.property("ktor.mongo.uri").getString()
    val dbName = config.property("ktor.mongo.database").getString()

    val client = KMongo.createClient(uri)
    val db = client.getDatabase(dbName)

    return MongoCollections(
        users = db.getCollection("users"),
    )
}
