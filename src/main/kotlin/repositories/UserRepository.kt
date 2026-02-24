package com.example.repositories

import com.example.models.domain.User
import com.example.models.domain.LoginResult
import com.example.models.domain.Order

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun registerUserDB(user: User): User
    suspend fun findByEmail(email: String): LoginResult?
    suspend fun findById(id:String): User?
    suspend fun createOrder(order: Order): Order
}
