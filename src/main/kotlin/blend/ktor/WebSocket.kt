package blend.ktor

import blend.ktor.data.Response
import blend.util.Constants
import io.ktor.server.websocket.DefaultWebSocketServerSession
import io.ktor.websocket.CloseReason
import io.ktor.websocket.close
import io.ktor.websocket.send
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object WebSocket {

    private val scope = CoroutineScope(Dispatchers.Default)
    private var websocket: DefaultWebSocketServerSession? = null

    fun init(websocket: DefaultWebSocketServerSession) {
        this.websocket = websocket
    }
    suspend fun close() {
        websocket?.close(CloseReason(CloseReason.Codes.GOING_AWAY, "Server closing"))
    }

    fun send(content: String) {
        scope.launch {
            websocket?.send(content)
        }
    }

    inline fun <reified T: Response> send(response: T) {
        send(Constants.json.encodeToString(response))
    }

}