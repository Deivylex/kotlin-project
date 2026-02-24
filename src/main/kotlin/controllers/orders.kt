package com.example.controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.example.models.dto.CreateOrderRequest
import com.example.models.dto.OrderResponse
import com.example.services.OrderService

class OrderController( private val service: OrderService) {
    
    suspend fun create(call: ApplicationCall) {
        val request = call.receive<CreateOrderRequest>()
        val order = service.createOrder(request)
        if (order == null) {
            call.respond(HttpStatusCode.BadRequest)
            return
        }
        val response = OrderResponse(
            id = order.id ?: "",
            userId = order.userId, 
        )
        call.respond(HttpStatusCode.OK, order)
    }
}

//porque             id = order.id ?: "",
