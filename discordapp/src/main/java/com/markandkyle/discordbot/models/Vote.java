package com.markandkyle.discordbot.models;

/**
 *
 * @author mhilliker
 */
public class Vote {
    /*
        vote_id int primary key,
        public_session_id string, 
        timestamp DateTime,   
        selections blob,     
    */
    
    public int primaryKey;
    
    public String publicSessionID;
    
    public String timestamp;
    
    public String selections;
}
