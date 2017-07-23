package com.markandkyle.discordbot.dataaccess;

import com.markandkyle.discordbot.models.Session;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: make queries safely
 * @author mhilliker
 */
public class SessionDAO {
        
    private Connection connection;
    
    public SessionDAO() {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public void saveSession(Session session) {
        final String sessionQuery = "INSERT INTO session (public_session_id,"
                + "private_session_id,name,options) VALUES ("+session.publicSessionID
                +","+session.privateSessionID+","+session.name+","+session.options+");";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sessionQuery);
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Session getSession(String sessionID) {
        Session result = new Session();
        final String sessionQuery = "SELECT public_session_id,private_session_id,"
                + "name,start_timestamp,end_timestamp,options FROM session WHERE private_session_id=" + sessionID + ";";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sessionQuery);
            result.publicSessionID = rs.getString("public_session_id");
            result.privateSessionID = rs.getString("private_session_id");
            result.name = rs.getString("name");
            result.startTime = rs.getString("start_timestamp");
            result.endTime = rs.getString("end_timestamp");
            result.options = rs.getString("options");

            // TODO: get votes from the session
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
}
