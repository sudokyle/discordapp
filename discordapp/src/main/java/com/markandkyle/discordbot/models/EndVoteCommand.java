package com.markandkyle.discordbot.models;

import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class EndVoteCommand extends VoteCommand {

    EndVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        msgHandler.sendMessage("End Vote Command Created! Content: " + removeEndCommand(message), event);
    }

    public String removeEndCommand(String msg){
        return removeVoteCommand(msg).replaceFirst("end","");
    }
}
