package com.markandkyle.discordbot.models.commands.VoteCommand;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.Session;
import com.markandkyle.discordbot.models.commands.VoteCommand.VoteCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class StartVoteCommand extends VoteCommand {
    private String name;
    private String[] options;

    StartVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        interpret(message);
        Session sesh = new Session(name, options);

        //Message Handling
        //Public Message:
        msgHandler.sendMessage(publicMessage(sesh), event);
        //Private Message:
        msgHandler.directMessage(privateMessage(sesh), event);
    }

    @Override
    public void interpret(String message) {
        this.name = sessionNameParser(message);
        this.options = optionSelectionParser(message);
    }

    private String publicMessage(Session sesh) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < sesh.getOptionList().length; i++) {
            sb.append(sesh.getOptionList()[i]+": "+(i+1)+"\n");
        }
        return "@here \""+sesh.name+"\" starting!\n" +
                "Session id: "+sesh.publicSessionID+"\n\n" +
                "To vote, use the vote cast command:\n" +
                "vote cast <session_id> <option_id(s)>\n\n" +
                "Available options: <option> : <option_id>\n" +
                ""+sb.toString();

    }

    private String privateMessage(Session sesh) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < sesh.getOptionList().length; i++) {
            sb.append(sesh.getOptionList()[i]+": "+(i+1)+"\n");
        }
        return "Your \""+sesh.name+"\" session has started!\n" +
                "Private session id: "+sesh.privateSessionID+"\n\n" +
                "To end the vote and get results, use the vote end command:\n" +
                "vote end <private_session_id>";

    }

    private String sessionNameParser(String msg) {
        boolean EON = false;
        int double_qoute_count = 0;
        int i=0;
        StringBuilder sb = new StringBuilder();
        while( (i < msg.length()) && !EON) {
            if(msg.charAt(i) == '"'){
                double_qoute_count++;
            } else if (double_qoute_count > 1) {
                EON = true;
            } else if(double_qoute_count > 0) {
                sb.append(Character.toString(msg.charAt(i)));
            }
            i++;
        }
        return sb.toString();
    }

    private String[] optionSelectionParser(String msg) {
        int i=0;
        int quotes=0;
        while( (i < msg.length()) && (quotes < 2)){
            if(msg.charAt(i) == '"') quotes++;
            i++;
        }
        String options = msg.substring(i);
        String[] selections = options.trim().split(",");
        for(int s=0; s < selections.length; s++) {
            selections[s] = selections[s].trim();
        }
        return selections;
    }
}
