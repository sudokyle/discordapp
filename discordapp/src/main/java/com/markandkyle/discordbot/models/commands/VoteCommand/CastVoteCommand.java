package com.markandkyle.discordbot.models.commands.VoteCommand;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.Session;
import com.markandkyle.discordbot.models.commands.VoteCommand.VoteCommand;
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
        message = removeCastCommand(message);
        //TODO: get session from database:
        Session session = new Session();
        String sessionId = getSessionId(message);
        msgHandler.sendMessage("Cast Vote Command Created! Content: " + removeCastCommand(message) + "id: " + sessionId, event);
    }

    public String removeCastCommand(String msg){
        return removeVoteCommand(msg).replaceFirst("cast","");
    }
}
