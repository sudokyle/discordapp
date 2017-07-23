package com.markandkyle.discordbot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

public class MessageHandler {

    private IDiscordClient client;
    
    public MessageHandler(IDiscordClient client) {
        this.client = client;
    }
	
	@EventSubscriber
	public void OnMesageEvent(MessageReceivedEvent event) throws DiscordException, MissingPermissionsException{
		IMessage message = event.getMessage();
		if(message.getContent().startsWith("!modulemessage")){
			sendMessage("Message send! Module is working.", event);
		}
	}
	
	public void sendMessage(String message, MessageReceivedEvent event) throws DiscordException, MissingPermissionsException{
		new MessageBuilder(this.client).appendContent(message).withChannel(event.getMessage().getChannel()).build();
	}

}
