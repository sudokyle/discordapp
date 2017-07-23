package com.markandkyle.discordbot.models;

import java.util.ArrayList;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class VoteCommandFactory {

    public static final String START = "start";
    public static final String CAST = "cast";
    public static final String END = "end";

    public static VoteCommand createCommand(String subCommand) {
        VoteCommand voteCommand = null;
        if(subCommand.equalsIgnoreCase(START)) voteCommand = new StartVoteCommand();
        else if(subCommand.equalsIgnoreCase(CAST)) voteCommand = new CastVoteCommand();
        else if(subCommand.equalsIgnoreCase(END)) voteCommand = new EndVoteCommand();
        return voteCommand;
    }
}
