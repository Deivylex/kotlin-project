package com.example.models.dto

data class RegisterRequest (
    val email: String,
    val name: String,
    val password: String,
    val role: String? = null 
)

data class UserResponse( 
    val id: String, 
    val email: String, 
    val name: String 
)