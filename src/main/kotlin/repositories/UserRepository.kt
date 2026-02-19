package com.example.repositories

import com.example.models.domain.User

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun registerUserDB(user: User): User
}
