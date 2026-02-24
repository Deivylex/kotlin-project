package com.example.application.module.order

import com.example.application.MongoCollections
import com.example.repositories.MongoUserRepository
import com.example.services.OrderService
import com.example.controllers.OrderController

fun configureOrderModule(db: MongoCollections): OrderController {
    val userRepository = MongoUserRepository(db.users)
    val orderService = OrderService(userRepository)
    val orderController = OrderController(orderService)

    return orderController
}