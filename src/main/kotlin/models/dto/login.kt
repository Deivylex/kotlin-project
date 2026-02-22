package com.example.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest (
    val email: String,
    val password: String
)

@Serializable
data class LoginResponse (
    val id: String,
    val email: String,
    val name: String,
    val role: String
)