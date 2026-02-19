package com.example.models.domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String?,
    val email: String,
    val name: String,
    val passwordHash: String,
    val role: String
)


//por que tuve que hacer esto aca 