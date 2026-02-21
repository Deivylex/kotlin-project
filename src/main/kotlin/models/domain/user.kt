package com.example.models.domain

data class User(
    val id: String?,
    val email: String,
    val name: String,
    val passwordHash: String,
    val role: String
)

//por que tuve que hacer esto aca 