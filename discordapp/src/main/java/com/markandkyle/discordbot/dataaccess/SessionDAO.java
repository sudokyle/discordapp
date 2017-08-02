package com.markandkyle.discordbot.dataaccess;

import com.markandkyle.discordbot.models.Session;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionDAO extends DataAccess {

    private final String PUBLIC_SESSION_ID = "public_session_id";
    private final String PRIVATE_SESSION_ID = "private_session_id";
    private final String NAME = "name";
    private final String START_TIMESTAMP = "start_timestamp";
    private final String END_TIMESTAMP = "end_timestamp";
    private final String OPTIONS = "options";

    public SessionDAO() {
        super(Databases.VOTE_COMMAND_SESSION);
    }

    public void saveSession(Session session) {
        final String sessionQuery = "INSERT INTO `session` (public_session_id,"
                + "private_session_id,name,options) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sessionQuery);
            pstmt.setString(1, session.publicSessionID);
            pstmt.setString(2, session.privateSessionID);
            pstmt.setString(3, session.name);
            pstmt.setString(4, session.options);
            int rowsEffected = pstmt.executeUpdate();
            Logger.getLogger(DataAccess.class.getName()).log(Level.INFO, "rowsEffected: "+rowsEffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateSession(Session session) {
        final String sessionQuery = "UPDATE `session` SET public_session_id = ?, private_session_id = ?, name = ?,start_timestamp = ?,end_timestamp = ?,options = ? WHERE private_session_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sessionQuery);
            pstmt.setString(1, session.publicSessionID);
            pstmt.setString(2, session.privateSessionID);
            pstmt.setString(3, session.name);
            pstmt.setString(4, session.startTime);
            pstmt.setString(5, session.endTime);
            pstmt.setString(6, session.options);
            pstmt.setString(7, session.privateSessionID);

            int rowsEffected = pstmt.executeUpdate();
            Logger.getLogger(DataAccess.class.getName()).log(Level.INFO, "rowsEffected: "+rowsEffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Session getSession(String sessionID) {
        Session result = new Session();
        final String sessionQuery = "SELECT public_session_id,private_session_id,"
                + "name,start_timestamp,end_timestamp,options FROM session WHERE private_session_id='" + sessionID +
                "' OR public_session_id='"+sessionID+"';";

        List<Map<String, Object>> qResult = fetch(sessionQuery);
        result.privateSessionID = String.valueOf(fetchFromResult(PRIVATE_SESSION_ID, qResult));
        result.publicSessionID = String.valueOf(fetchFromResult(PUBLIC_SESSION_ID, qResult));
        result.name = String.valueOf(fetchFromResult(NAME, qResult));
        result.startTime = String .valueOf(fetchFromResult(START_TIMESTAMP, qResult));
        result.endTime = String.valueOf(fetchFromResult(END_TIMESTAMP, qResult));
        result.options = String.valueOf(fetchFromResult(OPTIONS, qResult));
        return result;
    }


    public String getPrivateId(String publicId) {

        String query = "SELECT private_session_id,start_timestamp FROM session"
                + " WHERE public_session_id=" + publicId + "" // add limit to 24 hrs
                + " ORDER BY start_timestamp DESC";

        List<Map<String, Object>> qResult = fetch(query);
        return String.valueOf(fetchFromResult(PRIVATE_SESSION_ID, qResult));
    }
    
}
