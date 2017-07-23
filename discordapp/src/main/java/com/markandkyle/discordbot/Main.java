/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.markandkyle.discordbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

import java.io.File;
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
