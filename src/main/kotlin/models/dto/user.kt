package com.example.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest (
    val email: String,
    val name: String,
    val password: String,
    val role: String? = null 
)

@Serializable
data class UserResponse( 
    val id: String, 
    val email: String, 
    val name: String,
    val role: String
)