package com.example.repositories

import com.example.models.domain.User
import com.example.models.domain.LoginResult

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun registerUserDB(user: User): User
    suspend fun findByEmail(email: String): LoginResult?
}
