package blend.ktor

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object KtorApplication {

    private val serverScope = CoroutineScope(Dispatchers.Default)
    private val server = embeddedServer(
            factory = Netty,
            port = 6969,
            host = "0.0.0.0",
            module = Application::module
    )

    fun init() {
        serverScope.launch {
            server.start(true)
        }
    }

    fun close() {
        server.stop()
    }

}