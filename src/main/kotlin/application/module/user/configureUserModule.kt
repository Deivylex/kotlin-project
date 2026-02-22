package com.example.application.module.user

import com.example.application.MongoCollections
import com.example.repositories.MongoUserRepository
import com.example.services.UserService
import com.example.controllers.UserController
import com.example.services.LoginService
import com.example.controllers.LoginController

fun configureUserModule(db: MongoCollections): UserController {

    val userRepository = MongoUserRepository(db.users)
    val userService = UserService(userRepository)
    val userController = UserController(userService)

    return userController
}

fun configureLoginModule(db: MongoCollections): LoginController {
    val userRepository = MongoUserRepository(db.users)
    val loginService = LoginService(userRepository)
    val loginController = LoginController(loginService)

    return loginController
}