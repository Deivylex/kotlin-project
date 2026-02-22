package com.example.application

import io.ktor.server.application.*
import com.example.application.configurePlugins 
import com.example.application.configureRouting 
//import com.example.application.database.configureDatabase
import com.example.application.configureDatabase
import com.example.application.module.user.configureUserModule
import com.example.application.module.user.configureLoginModule


fun Application.module() {
    configurePlugins()

    val db = configureDatabase()

    val userController = configureUserModule(db)
    val loginController = configureLoginModule(db)

    configureRouting(userController, loginController)
}