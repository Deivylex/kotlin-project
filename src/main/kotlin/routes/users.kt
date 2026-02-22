package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import com.example.controllers.UserController
import com.example.controllers.LoginController



fun Route.users(controller: UserController, loginController: LoginController) {

    post("/register") {
        controller.register(call)
    }

    get("/users") {
        controller.getUsers(call)
    }

    get("/") {
        call.respondText("dev app!!")
    }

    post("/login") {
        loginController.login(call)
    }
}
