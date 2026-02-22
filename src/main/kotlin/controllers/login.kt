package com.example.controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.example.models.dto.LoginRequest
import com.example.models.dto.LoginResponse
import com.example.services.LoginService

class LoginController(private val service: LoginService) {
    suspend fun login(call: ApplicationCall) {
        val request = call.receive<LoginRequest>()
        val user = service.loginUser(request)
        if (user == null) {
            return call.respond(HttpStatusCode.Unauthorized, request)
        }
        val response = LoginResponse(
            id = user.id!!,
            email = user.email,
            name = user.name,
            role = user.role
        )
        call.respond(HttpStatusCode.OK, response)
    }
}