package com.example.repositories

import com.example.models.domain.User
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.bson.types.ObjectId
import org.litote.kmongo.eq

class MongoUserRepository(
    private val collection: MongoCollection<Document>
) : UserRepository {

    override suspend fun getAll(): List<User> {
        return collection.find().into(mutableListOf()).map { doc ->
            User(
                id = doc.getObjectId("_id").toHexString(),
                email = requireNotNull(doc.getString("email")),
                name = requireNotNull(doc.getString("name")),
                passwordHash = requireNotNull(doc.getString("passwordhash")),
                role = requireNotNull(doc.getString("role"))
            )
        }
    }

    override suspend fun registerUserDB(user: User): User {

        val doc = Document().apply {
            put("email", user.email)
            put("name", user.name)
            put("passwordHash", user.passwordHash)
            put("role", user.role)
        }

        collection.insertOne(doc)

        val id = doc.getObjectId("_id").toHexString()

        return user.copy(id = id)
    }
}
