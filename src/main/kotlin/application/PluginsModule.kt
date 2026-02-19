package com.example.application

import io.ktor.server.application.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configurePlugins() {
    install(ContentNegotiation) {
        json()
    }
}