package com.example

import com.example.application.module
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.MapApplicationConfig
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.junit.jupiter.api.BeforeEach
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import org.bson.Document
import com.example.helpers.registerTestUser

fun ApplicationTestBuilder.TestConfig() {
    environment {
        config = MapApplicationConfig(
            "ktor.mongo.uri" to System.getenv("MONGO_URI_TEST"),
            "ktor.mongo.database" to System.getenv("MONGO_DB_TEST")
        )
    }
}

fun cleanTestDatabase() {
    val uri = System.getenv("MONGO_URI_TEST")
    val dbName = System.getenv("MONGO_DB_TEST")

    val client = KMongo.createClient(uri)
    val database = client.getDatabase(dbName)

    database.listCollectionNames().forEach { name ->
        database.getCollection<Document>(name).deleteMany(Document())
    }

    client.close()
}

class ApplicationTest {
    @BeforeTest
    fun setup() {
        cleanTestDatabase()
    }

    @Test
    fun `testing home pag`() = testApplication {
        TestConfig()
        application {
            module()
        }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("dev app!!", response.bodyAsText())
    }
    
    @Test
    fun `testRegisterUser`() = testApplication {
        TestConfig()
        application {
            module()
        }
        val response = client.post("/register") {
            contentType(ContentType.Application.Json)
            setBody("""
                {
                    "email": "test123@example.com",
                    "name": "TestUser",
                    "password": "password123",
                    "role": "user"
                }
            """.trimIndent())
        }
        println("STATUS = ${response.status}")
        println("BODY = ${response.bodyAsText()}")
        assertEquals(HttpStatusCode.Created, response.status)
        
        val responseBody = response.bodyAsText()
        val json = Json.parseToJsonElement(responseBody).jsonObject
        
        assertEquals("test123@example.com", json["email"]?.toString()?.trim('"'))
        assertEquals("TestUser", json["name"]?.toString()?.trim('"'))
        assertEquals("user", json["role"]?.toString()?.trim('"'))
        assertNotNull(json["id"])
    }

    @Test
    fun `testLoginUser invalid account` () = testApplication {
        TestConfig()
        application {
            module()
        }
        val response = client.post("/login") {
        contentType(ContentType.Application.Json)
        setBody("""
            {
                "email": "test123@example.com",
                "password": "password123"
            }
        """.trimIndent())
        }
        assertEquals(HttpStatusCode.Unauthorized, response.status)
    }

    @Test
    fun `testLoginUser valid account` () = testApplication {
        TestConfig()
        application {
            module()
        }
        registerTestUser(client, "test123@example.com", "password123")
        val response = client.post("/login") {
        contentType(ContentType.Application.Json)
        setBody("""
            {
                "email": "test123@example.com",
                "password": "password123"
            }
        """.trimIndent())
        }
        println("STATUS = ${response.status}")
        println("BODY = ${response.bodyAsText()}")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}