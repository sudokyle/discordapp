package com.markandkyle.discordbot.models.commands;

/**
 * Created by CptAmerica on 7/22/17.
 */
public interface Command {
    public void execute(String message);
    public void interpret(String message);
}
