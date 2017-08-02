package com.markandkyle.discordbot;

import com.markandkyle.discordbot.dataaccess.SessionDAO;
import com.markandkyle.discordbot.models.Session;
import com.markandkyle.discordbot.models.commands.HelpCommand.HelpCommand;
import com.markandkyle.discordbot.models.commands.HelpCommand.HelpCommandFactory;
import com.markandkyle.discordbot.models.commands.VoteCommand.VoteCommandFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import java.io.IOException;

public class Main {
     public static void main(String[] args) throws IOException {
        IDiscordClient client = ClientFactory.createClient(true); // Gets the client object (from the first example)
        EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
         MessageHandler handler = new MessageHandler(client);
        dispatcher.registerListener(handler); // Registers the IListener example class from above

        //Register commands in our bot:
        DB127 db127 = DB127.getInstance();
        db127.registerCommand(new VoteCommandFactory());
        db127.registerCommand(new HelpCommandFactory());
    }
}
