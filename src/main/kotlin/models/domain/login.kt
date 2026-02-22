package com.example.models.domain

data class LoginResult(
    val id: String?,
    val email: String,
    val name: String,
    val passwordHash: String,
    val role: String
)