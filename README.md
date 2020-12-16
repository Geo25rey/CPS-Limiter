# CPS Limiter

This is a Spigot Plugin which limits the rate a minecraft player can interact with the world to prevent cheating like using and auto clicker.

## Building

1) Open this project in IntelliJ IDEA
2) Run the `Download Spigot` Run Configuration (you only need to do this once)
3) Run the `Run Testing Server` Run Configuration (run this to start the Spigot server). The plugin's JAR file is located in `out/CPS-Limiter.jar`

Note: All the testing server files are in the `test-server` directory

## Plugin Configuration

In `plugins/CPS-Limiter/config.yml` from your Spigot server directory, there are the following options:

- CPS Limit: [a number] (default 20) - the amount of interactions a player can trigger every second
