package com.markandkyle.discordbot.models;

import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class CastVoteCommand extends VoteCommand {

    CastVoteCommand(MessageHandler handler, MessageReceivedEvent event){
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        msgHandler.sendMessage("Cast Vote Command Created! Content: " + removeCastCommand(message), event);
    }

    public String removeCastCommand(String msg){
        return removeVoteCommand(msg).replaceFirst("cast","");
    }
}
