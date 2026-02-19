package com.example.application

import io.ktor.server.application.* 
import io.ktor.server.routing.* 
import com.example.routes.users
import com.example.controllers.UserController

fun Application.configureRouting(userController: UserController) { 
    routing { 
        users(userController) 
    } 
}