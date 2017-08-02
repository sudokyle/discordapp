package com.markandkyle.discordbot.models.commands.VoteCommand.subCommands;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.commands.VoteCommand.VoteCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class HelpVoteCommand extends VoteCommand {
    public HelpVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }
}
