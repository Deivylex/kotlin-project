package com.example.models.mappers

import com.example.models.dto.UserResponse
import com.example.models.domain.User

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id!!,
        email = this.email,
        name = this.name,
        role = this.role
    )
}