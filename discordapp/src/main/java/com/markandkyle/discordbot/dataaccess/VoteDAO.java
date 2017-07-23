package com.markandkyle.discordbot.dataaccess;

import java.sql.Connection;

/**
 *
 * @author mhilliker
 */
public class VoteDAO {
    
    private Connection connection;
    
    public VoteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }
    
    // TODO: Add classes for storing and getting session
    
}
