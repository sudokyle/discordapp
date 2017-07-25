package com.markandkyle.discordbot.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author mhilliker
 */
public class Session {
     /*
        public_session_id string,
        private_session_id string,
        name string,
        start_timestamp DateTime,
        end_timestamp DateTime,
        options blob,
    */
    
    public String publicSessionID;
    
    public String privateSessionID;
    
    public String name;
   
    public String startTime;
    
    // populated after session is complete
    public String endTime;
    
    public String options;
    
    // populated after session is complete
    public ArrayList<Vote> votes;

    public Session() {

    }

    /** Creates a New Session
     * Creates a new vote session
     * @param name of the voting session
     * @param options an optional set of selections voters must choose from.
     */
    public Session(String name, String[] options) {
        this.startTime = ""+System.currentTimeMillis();
        this.publicSessionID = UUID.randomUUID().toString().substring(0,5);
        this.privateSessionID = UUID.randomUUID().toString().substring(0,5);
        this.name = name;
        this.setOptionsFromList(options);
    }

    /**
     * Creates a Session object from database given a valid session id.
     * @param session_id a public or private session id.
     */
    public Session(String session_id) {

    }

    public String[] getOptionList() {
        String[] optionList = options.split(",");
        for(int i=0; i<optionList.length; i++){
            optionList[i] = optionList[i].trim();
        }
        
        return optionList;
    }
    
    public void setOptionsFromList(String[] list) {
        options = String.join(",", list);
    }
}
