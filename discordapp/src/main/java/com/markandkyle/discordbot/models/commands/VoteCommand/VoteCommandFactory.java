package com.markandkyle.discordbot.models.commands.VoteCommand;

import com.markandkyle.discordbot.DB127;
import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.commands.Command;
import com.markandkyle.discordbot.models.commands.CommandFactory;
import com.markandkyle.discordbot.models.commands.CommandRegistry;
import com.markandkyle.discordbot.models.commands.VoteCommand.subCommands.CastVoteCommand;
import com.markandkyle.discordbot.models.commands.VoteCommand.subCommands.EndVoteCommand;
import com.markandkyle.discordbot.models.commands.VoteCommand.subCommands.StartVoteCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class VoteCommandFactory extends CommandFactory {

    public VoteCommandFactory() {
        super();
        this.command_key = "!vote";
        this.registerCommand(new StartVoteCommandFactory());
        this.registerCommand(new CastVoteCommandFactory());
        this.registerCommand(new EndVoteCommandFactory());
    }

    @Override
    public Command build(String subCommand, MessageHandler handler ,MessageReceivedEvent event) {
        CommandFactory subCommandFactory = this.getCommandRegistry().getCommandFactory(subCommand);
        return subCommandFactory.build(event);
    }

    @Override
    public Command build(String subCommand, MessageReceivedEvent event) {
        CommandFactory subCommandFactory = this.getCommandRegistry().getCommandFactory(subCommand);
        return subCommandFactory.build(event);
    }

    private class StartVoteCommandFactory extends CommandFactory {

        StartVoteCommandFactory() {
            this.command_key = "start";
        }

        @Override
        public Command build(MessageReceivedEvent event) {
            return new StartVoteCommand(DB127.getInstance().msgHandler, event);
        }
    }
    private class CastVoteCommandFactory extends CommandFactory {
        CastVoteCommandFactory() {
            this.command_key = "cast";
        }
        @Override
        public Command build(MessageReceivedEvent event) {
            return new CastVoteCommand(DB127.getInstance().msgHandler, event);
        }
    }
    private class EndVoteCommandFactory extends CommandFactory {
        EndVoteCommandFactory () {
            this.command_key = "end";
        }
        @Override
        public Command build(MessageReceivedEvent event) {
            return new EndVoteCommand(DB127.getInstance().msgHandler, event);
        }
    }
}
