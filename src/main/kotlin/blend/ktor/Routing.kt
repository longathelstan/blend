package blend.ktor

import blend.Client
import blend.module.ModuleManager
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        allowHost("localhost:5173")
    }
    configureRouting()
}

fun Application.configureRouting() {
    routing {

        staticResources(
            "/",
            "/assets/static/"
        )

        route("/api") {
            route("/client") {
                get {
                    call.respond(ClientInfo(Client.NAME, Client.VERSION))
                }
            }
            route("/modules") {
                get {
                    call.respond(ModuleManager.modules.map { it.info })
                }
            }
        }

    }
}