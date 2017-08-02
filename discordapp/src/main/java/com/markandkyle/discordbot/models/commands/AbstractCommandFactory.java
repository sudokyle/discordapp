package com.markandkyle.discordbot.models.commands;

import com.markandkyle.discordbot.MessageHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public interface AbstractCommandFactory {
    Command build(String command, MessageHandler handler, MessageReceivedEvent event);
    Command build(MessageHandler handler, MessageReceivedEvent event);

}
