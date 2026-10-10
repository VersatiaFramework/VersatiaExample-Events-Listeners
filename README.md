    # VersatiaExample-Events-Listeners

    Every class implementing `Listener` is registered when the plugin starts: a plain class gets its constructor parameters injected, a bean is registered with its bean instance.

    Part of the [Versatia examples](https://github.com/VersatiaFramework/VersatiaExamples); documentation: [Event listeners](https://versatiaframework.github.io/VersatiaDocs/docs/events).

    **Start with** [`Listeners.kt`](src/main/kotlin/com/github/marcoral/versatia/example/listeners/Listeners.kt).

    ## What the log shows

    ```
    [Greeter] created
[QuitListener] created as a bean
[JoinListener] created, not a bean, Greeter injected
(a player joins) Welcome, Steve!
(a player leaves) [QuitListener] Steve left
    ```


## Things to try

- Add `@DisableVersatiaFeatures(registerListeners = true)` to `ListenersPlugin`: the listeners are
  created no more and nobody greets anyone.
- Make `ListenersPlugin` implement `Listener` with an `@EventHandler`: the plugin instance is
  registered too.

## Building

Requires JDK 21 and VersatiaAPI in your local Maven repository (`./gradlew publishToMavenLocal`
in its checkout); running it requires a Paper 1.21.4 server with VersatiaCore installed.

```bash
./gradlew build
```

To have the JAR copied to your server after every build, copy `local.properties.example` to
`local.properties` and set `pluginsDir`. The generated descriptor is in
`build/generated/ksp/main/kotlin`; open it to see exactly what the runtime executes.
