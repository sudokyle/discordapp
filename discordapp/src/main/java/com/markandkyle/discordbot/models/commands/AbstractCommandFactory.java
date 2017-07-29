package com.markandkyle.discordbot.models.commands;

import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/28/17.
 */
public interface AbstractCommandFactory {
    Command build(String command, MessageHandler handler, MessageReceivedEvent event);
    Command build(String command, MessageReceivedEvent event);
    Command build(MessageReceivedEvent event);
}
