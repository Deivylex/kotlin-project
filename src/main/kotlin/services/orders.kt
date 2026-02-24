package com.example.services

import com.example.repositories.UserRepository
import com.example.models.domain.Order
import com.example.models.dto.CreateOrderRequest

class OrderService(private val repo: UserRepository) {

    suspend fun createOrder(request: CreateOrderRequest): Order? {
        val checkUser = repo.findById(request.userId) ?: return null
        val order = Order (
            id = null,
            customerName = request.customerName,
            phone = request.phone,
            company= request.company,
            tortillaType = request.tortillaType, 
            productType= request.productType, 
            size = request.size,
            quantity= request.quantity,
            notes = request.notes,
            userId = request.userId
        )
        val savedOrder = repo.createOrder(order)
        return savedOrder;
    }
}


//por user y no loginResult