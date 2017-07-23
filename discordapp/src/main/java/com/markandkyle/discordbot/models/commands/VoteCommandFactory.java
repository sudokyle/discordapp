package com.markandkyle.discordbot.models.commands;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.commands.CastVoteCommand;
import com.markandkyle.discordbot.models.commands.EndVoteCommand;
import com.markandkyle.discordbot.models.commands.StartVoteCommand;
import com.markandkyle.discordbot.models.commands.VoteCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class VoteCommandFactory {

    public static final String START = "start";
    public static final String CAST = "cast";
    public static final String END = "end";

    public static VoteCommand createCommand(String subCommand, MessageHandler handler, MessageReceivedEvent event) {
        VoteCommand voteCommand = null;
        if(subCommand.equalsIgnoreCase(START)) voteCommand = new StartVoteCommand(handler, event);
        else if(subCommand.equalsIgnoreCase(CAST)) voteCommand = new CastVoteCommand(handler, event);
        else if(subCommand.equalsIgnoreCase(END)) voteCommand = new EndVoteCommand(handler, event);
        return voteCommand;
    }
}
