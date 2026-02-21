package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import com.example.controllers.UserController


fun Route.users(controller: UserController) {

    post("/register") {
        controller.register(call)
    }

    get("/users") {
        controller.getUsers(call)
    }

    get("/") {
        call.respondText("dev app!!")
    }
}
