package com.markandkyle.discordbot.models.commands.VoteCommand;

import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/24/17.
 */
public class HelpVoteCommand extends VoteCommand {
    HelpVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }

    @Override
    public void execute(String message) {

    }

    @Override
    public void interpret(String message) {
        
    }
}
