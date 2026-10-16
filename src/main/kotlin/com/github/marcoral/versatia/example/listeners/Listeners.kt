package com.github.marcoral.versatia.example.listeners

import com.github.marcoral.versatia.di.LocalBean
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

@LocalBean
class Greeter(plugin: ListenersPlugin) {
    init {
        plugin.logger.info("Greeter created")
    }

    fun greeting(player: Player) = "Welcome, ${player.name}!"
}

/** Not a bean: created like one, with `Greeter` injected, and registered because it is a `Listener`. */
class JoinListener(private val greeter: Greeter, plugin: ListenersPlugin) : Listener {
    init {
        plugin.logger.info("JoinListener created, not a bean, Greeter injected")
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) = event.player.sendMessage(greeter.greeting(event.player))
}

/** A bean that is a listener is registered with the bean instance; other beans may inject it. */
@LocalBean
class QuitListener(private val plugin: ListenersPlugin) : Listener {
    init {
        plugin.logger.info("QuitListener created as a bean")
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) = plugin.logger.info("${event.player.name} left")
}
