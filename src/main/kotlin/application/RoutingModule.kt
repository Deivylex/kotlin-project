package com.example.application

import io.ktor.server.application.* 
import io.ktor.server.routing.* 
import com.example.routes.users
import com.example.controllers.UserController
import com.example.controllers.LoginController

fun Application.configureRouting(userController: UserController, loginController: LoginController) { 
    routing { 
        users(userController, loginController) 
    } 
}