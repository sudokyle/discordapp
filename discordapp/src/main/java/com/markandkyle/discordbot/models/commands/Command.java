package com.markandkyle.discordbot.models.commands;

public class Command implements AbstractCommand {

    protected String commmand_key;


    public String getCommandKey() {
        return this.commmand_key;
    }

    @Override
    public void execute(String message) {

    }

    @Override
    public void interpret(String message) {

    }
}
