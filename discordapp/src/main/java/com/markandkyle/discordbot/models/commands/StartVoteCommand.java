package com.markandkyle.discordbot.models.commands;

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

        msgHandler.directMessage("Hello there! You have Started a new Voting session. To end it use the vote end command:\n" +
                "vote end <session_id>\nYour vote session id is: 12345", event);
    }

    public String removeStartCommand(String msg){
        return removeVoteCommand(msg).replaceFirst("start","");
    }

    private String[] optionSelectionParser(String msg) {
        String[] selections = msg.trim().split(",");
        for(int s=0; s < selections.length; s++) {
            selections[s] = selections[s].trim();
        }
        return selections;
    }
}
