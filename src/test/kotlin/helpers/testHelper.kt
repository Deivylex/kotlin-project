package com.example.helpers

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.client.*

suspend fun registerTestUser(client: HttpClient, email: String = "test123@example.com", password: String = "password123") {
    val response = client.post("/register") {
        contentType(ContentType.Application.Json)
        setBody("""
            {
                "email": "$email",
                "name": "TestUser",
                "password": "$password",
                "role": "user"
            }
        """.trimIndent())
    }
    assertEquals(HttpStatusCode.Created, response.status)
}