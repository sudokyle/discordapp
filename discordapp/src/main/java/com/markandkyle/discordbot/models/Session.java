package com.markandkyle.discordbot.models;

/**
 *
 * @author mhilliker
 */
public class Session {
     /*
        public_session_id string,
        private_session_id string,
        start_timestamp DateTime,
        end_timestamp DateTime,
        options blob,
    */
    
    public String publicSessionID;
    
    public String privateSessionID;
   
    public String startTime;
    
    public String endTime;
    
    public String options;
}
