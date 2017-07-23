package com.markandkyle.discordbot.models;

import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class StartVoteCommand extends VoteCommand {

    StartVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        msgHandler.sendMessage("Start Vote Command Created! Content: " + removeStartCommand(message), event);
    }

    public String removeStartCommand(String msg){
        return removeVoteCommand(msg).replaceFirst("start","");
    }
}
