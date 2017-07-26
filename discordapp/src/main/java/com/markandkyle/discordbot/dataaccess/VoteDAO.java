package com.markandkyle.discordbot.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mhilliker
 */
public class VoteDAO {
    
    private Connection connection;
    
    public VoteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }
    
     @Override
    public void finalize() {
        try {
            this.connection.close();
            super.finalize();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
