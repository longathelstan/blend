package blend.ktor.data

import blend.Client
import kotlinx.serialization.Serializable

@Serializable
sealed interface Response

@Serializable
data class WSClientInfo(
    val name: String,
    val version: String
): Response {
    companion object {
        fun get() = WSClientInfo(Client.NAME, Client.VERSION)
    }
}
