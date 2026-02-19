package com.example.application.module.user

import com.example.application.MongoCollections
import com.example.repositories.MongoUserRepository
import com.example.services.UserService
import com.example.controllers.UserController

fun configureUserModule(db: MongoCollections): UserController {

    // 1. Crear Repository
    val userRepository = MongoUserRepository(db.users)

    // 2. Crear Service
    val userService = UserService(userRepository)

    // 3. Crear Controller
    val userController = UserController(userService)

    // 4. Devolver controller para usarlo en routing
    return userController
}
