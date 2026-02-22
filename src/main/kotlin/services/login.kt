package com.example.services

import com.example.models.domain.User
import com.example.models.dto.LoginRequest
import org.mindrot.jbcrypt.BCrypt
import com.example.repositories.UserRepository
import com.example.models.domain.LoginResult

class LoginService (private val repo: UserRepository) {

    suspend fun loginUser (request: LoginRequest): LoginResult? {
        val checkUser = repo.findByEmail(request.email) ?: return null
        val checkPassword = BCrypt.checkpw(request.password, checkUser.passwordHash)
        return if (checkPassword) {
            LoginResult (
            id = checkUser.id,
            email = checkUser.email,
            name = checkUser.name,
            passwordHash = "",
            role = checkUser.role
        )
        } else null
    }
}