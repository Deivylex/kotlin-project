package com.example.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequest(
    val customerName: String,
    val phone: String,
    val company: String,
    val tortillaType: String,   // "white" | "blue"
    val productType: String,    // "tortilla" | "nacho"
    val size: String,           // "12" | "18"
    val quantity: Int,
    val notes: String? = null,
    val userId:String
)


@Serializable
data class OrderResponse (
    val id: String,
    val userId: String
)