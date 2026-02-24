package com.example.application

import io.ktor.server.application.* 
import io.ktor.server.routing.* 
import com.example.routes.users
import com.example.routes.orders
import com.example.controllers.UserController
import com.example.controllers.LoginController
import com.example.controllers.OrderController

fun Application.configureRouting(userController: UserController, loginController: LoginController, orderController:OrderController) { 
    routing { 
        users(userController, loginController)
        orders(orderController)
    } 
}