package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import com.example.controllers.OrderController

fun Route.orders(controller: OrderController)
{
    post("/order") {

        //val userId: String
        //userId = call.request.headers["userId"] ?: return@post call.respond(HttpStatusCode.Unauthorized)
        controller.create(call)
    }
}

//continuar con controller, service y repositorie