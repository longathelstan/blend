package blend.ktor

import blend.ktor.data.Request
import blend.util.Constants
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import io.ktor.websocket.send
import java.lang.Exception
import kotlin.time.Duration.Companion.seconds

fun Application.module() {
    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
        contentConverter = KotlinxWebsocketSerializationConverter(Constants.json)
    }
    configureRouting()
}

fun Application.configureRouting() {
    routing {

        webSocket("/blend") {
            WebSocket.init(this)
            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        try {
                            val jsonData = frame.readText()
                            val request = Constants.json.decodeFromString<Request>(jsonData)
                            request.handle()
                        } catch (e: Exception) {
                            send("caught error")
                            send(e.message ?: "exception message turned out to be empty...")
                        }
                    }
                    is Frame.Close -> {
                        close(CloseReason(CloseReason.Codes.NORMAL, "Closed connected as requested"))
                    }
                    else -> {}
                }
            }
        }

        staticResources(
            "/",
            "/assets/static/"
        )

    }
}