package com.markandkyle.discordbot.models.commands;

import java.util.HashMap;

public class CommandRegistry {

    protected HashMap<String, CommandFactory> registry;

    public CommandRegistry() {
        registry = new HashMap<>();
    }

    public void registerCommand(CommandFactory commandFactory) {
        registry.put(commandFactory.command_key, commandFactory);
    }

    public CommandFactory getCommandFactory(String command_key) {
        return this.registry.get(command_key);
    }


}
