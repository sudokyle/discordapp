package com.markandkyle.discordbot.dataaccess;

import com.markandkyle.discordbot.models.Session;

import java.sql.*;
import java.util.TreeMap;
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
            if(rs.next()) {
                result.publicSessionID = rs.getString("public_session_id");
                result.privateSessionID = rs.getString("private_session_id");
                result.name = rs.getString("name");
                result.startTime = rs.getString("start_timestamp");
                result.endTime = rs.getString("end_timestamp");
                result.options = rs.getString("options");
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // get the corresponding votes
        try {
            Statement stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        

        return result;
    }
    
    public String getPrivateId(String publicId) {

        String query = "SELECT private_session_id,start_timestamp FROM session"
                + " WHERE public_session_id=" + publicId + "" // add limit to 24 hrs
                + " ORDER BY start_timestamp DESC";
        
        String key = null;
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                key  = rs.getString("private_session_id");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return key;
    }
    
    @Override
    public void finalize() {
        try {
            if(this.connection != null) {
                this.connection.close();
            }
            super.finalize();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
