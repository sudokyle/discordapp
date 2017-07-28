package com.markandkyle.discordbot.models.commands.VoteCommand;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.dataaccess.SessionDAO;
import com.markandkyle.discordbot.models.Session;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class EndVoteCommand extends VoteCommand {

    private String sessionId;

    EndVoteCommand(MessageHandler handler, MessageReceivedEvent event) {super(handler, event);}

    @Override
    public void execute(String message) {
        interpret(message);
        session = sessionDAO.getSession(sessionId);

        // If session closing is truly authed by creator:
        if(session.privateSessionID.equals(sessionId)) {
            if(session.endTime != null) {
                msgHandler.sendMessage(authedSessionAlreadyClosedMessage(event),event);
            }else {
                closeSession(session);
                msgHandler.sendMessage(authedSessionCloseMessage(event),event);
            }
        }
        else {
            msgHandler.sendMessage(unAuthedSessionCloseMessage(event),event);
        }
    }

    @Override
    public void interpret(String message) {
        this.sessionId = sessionIdParser(message);
    }

    private String authedSessionCloseMessage(MessageReceivedEvent event) {
        return "@here, "+getUserTag()+" has closed the voting session!";
    }

    private String authedSessionAlreadyClosedMessage(MessageReceivedEvent event) {
        return ""+getUserTag()+" "+session.name+" has already been closed.";
    }

    private String unAuthedSessionCloseMessage(MessageReceivedEvent event) {
        return "@here, "+getUserTag()+" is a rebel scum for trying to close the voting session!";
    }


    private void closeSession(Session session) {
        session.endTime = ""+System.currentTimeMillis();
        sessionDAO.updateSession(session);
    }
}
