package com.markandkyle.discordbot;

import com.markandkyle.discordbot.models.commands.CommandFactory;

public class DB127 {

    private CommandFactory commandFactory;

    private static DB127 ourInstance = new DB127();

    public static DB127 getInstance() {
        return ourInstance;
    }

    private DB127() {
        this.commandFactory = new CommandFactory();
    }

    public void registerCommand(CommandFactory commandFactory) {
        this.commandFactory.registerCommand(commandFactory);
    }


    public CommandFactory getCommandFactory() {
        return this.commandFactory;
    }

}
