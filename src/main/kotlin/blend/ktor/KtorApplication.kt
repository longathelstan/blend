package blend.ktor

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.runBlocking

object KtorApplication {

    private val server = embeddedServer(
            factory = Netty,
            port = 6969,
            host = "0.0.0.0",
            module = Application::module
    )

    fun init() {
        server.start(wait = false)
    }

    fun close() {
        runBlocking {
            WebSocket.close()
        }
        server.stop()
    }

}