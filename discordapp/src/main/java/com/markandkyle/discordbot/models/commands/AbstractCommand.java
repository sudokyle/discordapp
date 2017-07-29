package com.markandkyle.discordbot.models.commands;

/**
 * Created by CptAmerica on 7/28/17.
 */
public interface AbstractCommand {
        /**
         * The Functionality of a Command to be executed.
         * @param message the command message to execute.
         */
        public void execute(String message);

        /**
         * A function which parses a command's input.
         * @param message the command message to be parsed.
         */
        public void interpret(String message);
}

