# MinecraftServerDiscordBot
A simple discord bot that shows how many players are playing on a Minecraft Server in it's status

![Player Count](https://i.imgur.com/DIgtawX.png) ![ServerIP](https://i.imgur.com/28kzyNw.png)

## Features
* Goes online/offline with the server
* Shows the player count on the server
* Can alternate to show the server IP after player count

## Installation
1. Download the latest release on the right side of the page.
2. Download the `config.properties` file from the release page as well and place it in the same folder as the `.jar`.
3. Edit the `config.properties` file and insert your Discord bot token and Minecraft server IP there.
4. Run the `.jar` file you downloaded using the command `java -jar path_to_jar.jar`
5. To exit type `stop`.

**NOTE: `enable-query` in `server.properties` for the Minecraft server should be set to `true` in order for the Bot to work**

## Credits
* [jamietech's Minecraft Server ping API](https://github.com/jamietech/MinecraftServerPing)
