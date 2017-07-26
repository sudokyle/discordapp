package com.markandkyle.discordbot.models.commands.VoteCommands;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.Session;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.Arrays;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class CastVoteCommand extends VoteCommand {

    private String sessionId;
    private String[] options;

    CastVoteCommand(MessageHandler handler, MessageReceivedEvent event){
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        interpret(message);
        //TODO: get session from database:
        Session session = new Session();
        msgHandler.sendMessage("Cast Vote Command Created! " +
                "\nid: " + this.sessionId + "" +
                "\noptions: "+ Arrays.toString(this.options), event);
    }

    @Override
    public void interpret(String message) {
        this.sessionId = sessionIdParser(message);
        this.options = optionsParser(message);
    }

    /**
     * Parses the Command message to grab the options that the user voted for.
     * @param message the Command message to parse.
     * @return the list of options that was voted for.
     */
    private String[] optionsParser(String message) {
        String command = message;
        String[] cmdChunks = command.trim().split(" ");
        command = command.replaceFirst(cmdChunks[0].trim(), "");
        command = command.replaceFirst(cmdChunks[1].trim(), "");
        command = command.replaceFirst(cmdChunks[2].trim(), "");
        String[] options = command.trim().split(",");
        for(int o=0; o < options.length; o++) {
            options[o] = options[o].trim();
        }
        return options;
    }
}
