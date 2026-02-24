package com.example.repositories

import com.example.models.domain.User
import com.example.models.domain.Order
import com.example.models.domain.LoginResult
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
            put("passwordhash", user.passwordHash)
            put("role", user.role)
        }

        collection.insertOne(doc)

        val id = doc.getObjectId("_id").toHexString()

        return user.copy(id = id)
    }

    override suspend fun findByEmail(email: String): LoginResult? {
        return collection
            .find(Document("email", email))
            .firstOrNull()
            ?.let { doc ->
                LoginResult (
                    id = doc.getObjectId("_id").toString(),
                    email = doc.getString("email"),
                    name = doc.getString("name"),
                    passwordHash = doc.getString("passwordhash"),
                    role = doc.getString("role")
                )
            }
    }

    override suspend fun findById(id: String): User? {
        return collection
            .find(Document("_id", id))
            .firstOrNull()
            ?.let { doc ->
                User (
                    id = doc.getObjectId("_id").toString(),
                    email = doc.getString("email"),
                    name = doc.getString("name"),
                    passwordHash = doc.getString("passwordhash"),
                    role = doc.getString("role")
                )
            }
    }

    override suspend fun createOrder(order: Order): Order {

        val doc = Document().apply {
            put("customerName", order.customerName)
            put("phone", order.phone)
            put("company", order.company)
            put("tortillaType", order.tortillaType)
            put("productType", order.productType)
            put("size", order.size)
            put("quantity", order.quantity)
            put("orderDate", order.orderDate)
            put("status", order.status)
            put("notes", order.notes)
            put("userId", order.userId)    
        }
        collection.insertOne(doc)
        val id = doc.getObjectId("_id").toHexString()
        return order.copy(id = id)
    }
}
