package com.markandkyle.discordbot.models.commands.VoteCommand.subCommands;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.Session;
import com.markandkyle.discordbot.models.commands.VoteCommand.VoteCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class StartVoteCommand extends VoteCommand {
    private String name;
    private String[] options;

    public StartVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        interpret(message);
        this.session = new Session(name, options);
        storeNewSession(session);

        //Message Handling
        //Public Message:
        msgHandler.sendMessage(publicMessage(session), event);
        //Private Message:
        msgHandler.directMessage(privateMessage(session), event);
    }

    @Override
    public void interpret(String msg) {
        boolean EON;
        EON = false;
        int double_qoute_count = 0;
        int i=0;
        StringBuilder sb = new StringBuilder();
        while( (i < msg.length()) && !EON) {
            if(msg.charAt(i) == '"') {
                double_qoute_count++;
            } else if (double_qoute_count > 1) {
                EON = true;
            } else if(double_qoute_count > 0) {
                sb.append(Character.toString(msg.charAt(i)));
            }
            i++;
        }
        this.name = sb.toString();
        sb.setLength(0);
        String options = msg.substring(i);
        String[] selections = options.trim().split(",");
        for(int s=0; s < selections.length; s++) {
            selections[s] = selections[s].trim();
        }
        this.options = selections;
    }

    /**
     * The message to be sent in the channel where this command was called.
     * @param session the the new session made by this vote command.
     * @return
     */
    private String publicMessage(Session session) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < session.getOptionList().length; i++) {
            sb.append(session.getOptionList()[i]+": "+(i+1)+"\n");
        }
        return "@here \""+session.name+"\" starting!\n" +
                "Session id: "+session.publicSessionID+"\n\n" +
                "To vote, use the vote cast command:\n" +
                "vote cast <session_id> <option_id(s)>\n\n" +
                "Available options: <option> : <option_id>\n" +
                ""+sb.toString();

    }

    /**
     * Generates the private message to be sent to the user who sent the start vote command.
     * @param session the instance of the new voting session created.
     * @return the message to be sent to the user who sent the start vote command.
     */
    private String privateMessage(Session session) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < session.getOptionList().length; i++) {
            sb.append(session.getOptionList()[i]+": "+(i+1)+"\n");
        }
        return "Your \""+session.name+"\" session has started!\n" +
                "Private session id: "+session.privateSessionID+"\n\n" +
                "To end the vote and get results, use the vote end command:\n" +
                "vote end <private_session_id>";

    }

    /**
     * Writes the session to the database.
     * @param session session to store in db.
     */
    private void storeNewSession(Session session) {
        sessionDAO.saveSession(session);
    }
}
