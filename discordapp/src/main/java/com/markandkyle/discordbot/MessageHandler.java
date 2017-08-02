package com.markandkyle.discordbot;

import com.fasterxml.jackson.module.afterburner.util.ClassName;
import com.markandkyle.discordbot.dataaccess.SessionDAO;
import com.markandkyle.discordbot.models.commands.Command;
import com.markandkyle.discordbot.models.commands.VoteCommand.*;
import org.sqlite.core.DB;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;


import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageHandler {
    private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );
    private IDiscordClient client;
    private SessionDAO sessionDAO;
    
    public MessageHandler(IDiscordClient client) {
        this.client = client;
    }
	
	@EventSubscriber
	public void OnMesageEvent(MessageReceivedEvent event) throws DiscordException, MissingPermissionsException{
        //Process Message
        IMessage message = event.getMessage();
        String msg = message.getContent().toLowerCase();
        System.out.println(message.getAuthor().getLongID());

        //Get and Execute Command:
        DB127 db127 = DB127.getInstance();
        Command command = db127.getCommandFactory().build(msg, this, event);
        command.execute(msg);
	}
	
	public void sendMessage(String message, MessageReceivedEvent event) throws DiscordException, MissingPermissionsException{
		new MessageBuilder(this.client).appendContent(message).withChannel(event.getMessage().getChannel()).build();
	}

	public void directMessage(String message,  MessageReceivedEvent event) throws DiscordException, MissingPermissionsException {
        new MessageBuilder(this.client).appendContent(message).withChannel(event.getMessage().getAuthor().getOrCreatePMChannel()).build();
    }
}
