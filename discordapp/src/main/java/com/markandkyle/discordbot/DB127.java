package com.markandkyle.discordbot;

import com.markandkyle.discordbot.models.commands.CommandFactory;
import com.markandkyle.discordbot.models.commands.CommandRegistry;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/28/17.
 */
public class DB127 {

    private CommandFactory commandFactory;
    public MessageHandler msgHandler;

    private static DB127 ourInstance = new DB127();

    public static DB127 getInstance() {
        return ourInstance;
    }

    private DB127() {
        this.commandFactory = new CommandFactory();
    }

    public void registerCommand(CommandFactory commandFactory) {
        this.commandFactory.registerCommand(commandFactory);
        this.commandFactory.setMessageHandler(this.msgHandler);
    }


    public CommandFactory getCommandFactory() {
        return this.commandFactory;
    }

    public void setMessagehandler(MessageHandler handler) {
        this.msgHandler = handler;
        this.commandFactory.setMessageHandler(handler);
    }

}
