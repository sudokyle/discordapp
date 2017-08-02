package com.markandkyle.discordbot.models.commands;

import com.markandkyle.discordbot.DB127;
import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class CommandFactory implements AbstractCommandFactory {

    private CommandRegistry commandRegistry;
    public String command_key;

    public CommandFactory() {
        commandRegistry = new CommandRegistry();
    }


    public void registerCommand(CommandFactory commandFactory) {
        commandRegistry.registerCommand(commandFactory);
    }

    protected CommandRegistry getCommandRegistry() {
        return this.commandRegistry;
    }


    public Command build(String command, MessageHandler handler, MessageReceivedEvent event) {
        String[] commands = command.split(" ");
        System.out.println(commands[0]);
        CommandFactory factory = this.commandRegistry.getCommandFactory(commands[0]);
        System.out.println(commands[1]);
        return factory.build(commands[1], handler, event);
    }

    public Command build(MessageHandler handler, MessageReceivedEvent event) {
        return null;
    }


}
