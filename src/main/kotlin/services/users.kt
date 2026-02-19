package com.example.services

import com.example.models.domain.User
import com.example.models.dto.RegisterRequest
import org.mindrot.jbcrypt.BCrypt
import com.example.repositories.UserRepository

class UserService(private val repo: UserRepository) {

    suspend fun getUsers(): List<User> {
        val users = repo.getAll()
        if (users.isEmpty()) {
            throw IllegalStateException("No users found")
        }
        return users
    }

    suspend fun registerUser(request: RegisterRequest): User {

        val salt = BCrypt.gensalt(10)
        val passwordHash = BCrypt.hashpw(request.password, salt)
        val user = User(
            id = null, 
            email = request.email, 
            name  = request.name,
            passwordHash = passwordHash,
            role = request.role ?: "user"
        )
        val savedUser = repo.registerUserDB(user) 
        return savedUser
    }
}
