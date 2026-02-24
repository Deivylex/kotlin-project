package com.example.models.domain

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Order(
    val id: String?,
    val customerName: String,
    val phone: String,
    val company: String,
    val tortillaType: String,
    val productType: String,
    val size: String,
    val quantity: Int,
    val orderDate: Long = System.currentTimeMillis(),
    val status: String = "pending",
    val notes: String? = null,
    val userId: String
)
