package blend.ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClientInfo(
    @SerialName("name")
    val name: String,
    @SerialName("version")
    val version: String
)

@Serializable
data class ModuleInfo(
    val names: Array<String>,
    val description: String,
    val category: String,
    val enabled: Boolean,
    val canBeEnabled: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ModuleInfo) return false

        if (enabled != other.enabled) return false
        if (canBeEnabled != other.canBeEnabled) return false
        if (!names.contentEquals(other.names)) return false
        if (description != other.description) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = enabled.hashCode()
        result = 31 * result + canBeEnabled.hashCode()
        result = 31 * result + names.contentHashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
}