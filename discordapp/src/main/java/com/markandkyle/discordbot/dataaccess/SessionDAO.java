package com.markandkyle.discordbot.dataaccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mhilliker
 */
public class SessionDAO {
        
    private Connection connection;
    
    public SessionDAO() {
        this.connection = ConnectionFactory.getConnection();
    }
    
    // TODO: Add classes for storing and getting session
    
}
