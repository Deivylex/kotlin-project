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

    val env = dotenv { ignoreIfMissing = false } // only for dev
    val client = KMongo.createClient(env["MONGO_URI"])
    val db = client.getDatabase(env["MONGO_DB"])

    return MongoCollections(
        users = db.getCollection("users"),
    )
}
