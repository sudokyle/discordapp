/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.markandkyle.discordbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author mhilliker
 */
public class ClientFactory {
    public static IDiscordClient createClient(boolean login) throws IOException { // Returns a new instance of the Discord client
        ObjectMapper mapper = new ObjectMapper();
        Config config = mapper.readValue(new File("discordapp/src/config.json"), Config.class);
        String token = config.getToken();
        System.out.print(token);
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }
}
