package com.markandkyle.discordbot.models.commands;

import com.markandkyle.discordbot.MessageHandler;
import com.markandkyle.discordbot.models.Session;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.UUID;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class StartVoteCommand extends VoteCommand {

    StartVoteCommand(MessageHandler handler, MessageReceivedEvent event) {
        super(handler, event);
    }

    @Override
    public void execute(String message) {
        String content = removeStartCommand(message);
        String name = getSessionName(content);
        content = removeName(name, content);
        String[] options = optionSelectionParser(content);
        Session sesh = buildVotingSesh(name, options);

        //Message Handling
        //Public Message:
        msgHandler.sendMessage(publicMessage(sesh), event);
        //Private Message:
        msgHandler.directMessage(privateMessage(sesh), event);
    }

    public String publicMessage(Session sesh) {
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

    public String privateMessage(Session sesh) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < sesh.getOptionList().length; i++) {
            sb.append(sesh.getOptionList()[i]+": "+(i+1)+"\n");
        }
        return "Your \""+sesh.name+"\" session has started!\n" +
                "Private session id: "+sesh.privateSessionID+"\n\n" +
                "To end the vote and get results, use the vote end command:\n" +
                "vote end <private_session_id>";

    }

    public String removeStartCommand(String msg){
        return removeVoteCommand(msg).replaceFirst("start","").trim();
    }

    private String removeName(String name, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(name);
        sb.append("\"");
        return msg.replaceFirst(sb.toString(), "");
    }

    public String getSessionName(String msg){
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
        String[] selections = msg.trim().split(",");
        for(int s=0; s < selections.length; s++) {
            selections[s] = selections[s].trim();
        }
        return selections;
    }

    private Session buildVotingSesh(String name, String[] options) {
        //get time
        Session sesh = new Session();
        sesh.startTime = ""+System.currentTimeMillis();
        UUID public_id = UUID.randomUUID();
        UUID private_id = UUID.randomUUID();
        sesh.name = name;
        sesh.publicSessionID = public_id.toString().substring(0,5);
        sesh.privateSessionID = private_id.toString().substring(0,5);
        sesh.setOptionsFromList(options);
        return sesh;
    }
}
