package com.example.controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.example.services.UserService
import com.example.models.dto.RegisterRequest
import com.example.models.dto.UserResponse

class UserController(private val service: UserService) {

    suspend fun getUsers(call: ApplicationCall) {
        val users = service.getUsers()
        call.respond(users)
    }

    suspend fun register(call: ApplicationCall) {
        val request = call.receive<RegisterRequest>()
        val user = service.registerUser(request)
        val userResponse = UserResponse(
            id = user.id!!,
            email = user.email,
            name = user.name
        )
        call.respond(HttpStatusCode.Created, userResponse)
    }
}
