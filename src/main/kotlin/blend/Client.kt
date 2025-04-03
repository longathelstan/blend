package blend

import blend.command.CommandManager
import blend.config.ConfigManager
import blend.handler.ChatInputHandler
import blend.handler.KeybindHandler
import blend.ktor.KtorApplication
import blend.module.ModuleManager
import blend.util.extensions.mc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock.System
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.until
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

object Client {

    const val NAME = "Blend"
    const val VERSION = "6.0"
    val logger: Logger = LoggerFactory.getLogger(NAME)
    val scope = CoroutineScope(Dispatchers.Main)
    val folder = File(mc.runDirectory, NAME.lowercase()).also {
        if (!it.exists())
            it.mkdir()
    }

    fun init() {
        val preInitTime = System.now()

        arrayOf(
            ModuleManager,
            CommandManager
        ).forEach { it.init() }

        arrayOf(
            KeybindHandler,
            ChatInputHandler
        ).forEach { it.init() }

        scope.launch(Dispatchers.IO) {
            if (!ConfigManager.loadAll())
                ConfigManager.save()
        }

        KtorApplication.init()

        logger.info("Initialized $NAME v$VERSION in ${preInitTime.until(System.now(), DateTimeUnit.MILLISECOND)}ms")
    }

    fun close() {
        KtorApplication.close()
        ConfigManager.save()
        logger.info("Closed $NAME")
    }

}