package com.markandkyle.discordbot.models;

import java.util.ArrayList;
import java.util.Random;

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
