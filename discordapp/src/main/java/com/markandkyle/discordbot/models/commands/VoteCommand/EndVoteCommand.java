package com.markandkyle.discordbot.models.commands.VoteCommand;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.commands.VoteCommand.VoteCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class EndVoteCommand extends VoteCommand {

    private String sessionId;

    EndVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        interpret(message);
        msgHandler.sendMessage("End Vote Command Created! Private sessionid: " + this.sessionId, event);
    }

    @Override
    public void interpret(String message) {
        this.sessionId = sessionIdParser(message);
    }

    private void closeSession() {}
}
