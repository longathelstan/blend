package blend.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object Constants {

    @OptIn(ExperimentalSerializationApi::class)
    val json = Json {
        ignoreUnknownKeys = true
        allowComments = true
        allowTrailingComma = true
        prettyPrint = true
        prettyPrintIndent = "    "
    }

}