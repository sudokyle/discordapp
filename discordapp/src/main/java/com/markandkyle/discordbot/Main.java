package com.markandkyle.discordbot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import java.io.IOException;

/**
 *
 * @author mhilliker
 */
public class Main {
     public static void main(String[] args) throws IOException {
        IDiscordClient client = ClientFactory.createClient(true); // Gets the client object (from the first example)
        EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(new MessageHandler(client)); // Registers the IListener example class from above
    }
}
