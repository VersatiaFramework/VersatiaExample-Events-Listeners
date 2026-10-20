package com.github.marcoral.versatia.example.listeners

import com.github.marcoral.versatia.di.LocalBean
import java.util.logging.Logger
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

@LocalBean
class Greeter(logger: Logger) {
    init {
        logger.info("Greeter created")
    }

    fun greeting(player: Player) = "Welcome, ${player.name}!"
}

/** Not a bean: created like one, with `Greeter` injected, and registered because it is a `Listener`. */
class JoinListener(private val greeter: Greeter, logger: Logger) : Listener {
    init {
        logger.info("JoinListener created, not a bean, Greeter injected")
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) = event.player.sendMessage(greeter.greeting(event.player))
}

/** A bean that is a listener is registered with the bean instance; other beans may inject it. */
@LocalBean
class QuitListener(private val logger: Logger) : Listener {
    init {
        logger.info("QuitListener created as a bean")
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) = logger.info("${event.player.name} left")
}
