package com.markandkyle.discordbot.models.commands;

import com.markandkyle.discordbot.DB127;
import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/28/17.
 */
public class CommandFactory implements AbstractCommandFactory {

    private CommandRegistry commandRegistry;
    public String command_key;
    protected MessageHandler handler;

    public CommandFactory() {
        commandRegistry = new CommandRegistry();
        this.handler = handler;
    }


    public void registerCommand(CommandFactory commandFactory) {
        commandFactory.setMessageHandler(this.handler);
        commandRegistry.registerCommand(commandFactory);
    }

    protected CommandRegistry getCommandRegistry() {
        return this.commandRegistry;
    }

    public void setMessageHandler(MessageHandler handler) {
        this.handler = handler;
    }


    public Command build(String command, MessageHandler handler, MessageReceivedEvent event) {
        String[] commands = command.split(" ");
        System.out.println(commands[0]);
        CommandFactory factory = this.commandRegistry.getCommandFactory(commands[0]);
        System.out.println(commands[1]);
        return factory.build(commands[1], handler, event);
    }

    public Command build(String command, MessageReceivedEvent event) {
        String[] commands = command.split(" ");
        CommandFactory factory = this.commandRegistry.getCommandFactory(commands[0]);
        return factory.build(commands[1], this.handler, event);
    }

    public Command build(MessageReceivedEvent event) {
        return null;
    }
}
