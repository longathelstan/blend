@file:Suppress("unused") // none will be used
package blend.ktor.data

import blend.ktor.WebSocket
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
sealed class Request {
    abstract fun handle()
}

@Serializable
@SerialName("info")
data object ClientInfoRequest: Request() {
    override fun handle() {
        WebSocket.send(WSClientInfo.get())
    }
}
