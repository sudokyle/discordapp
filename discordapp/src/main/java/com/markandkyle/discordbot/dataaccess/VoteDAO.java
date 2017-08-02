package com.markandkyle.discordbot.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoteDAO extends DataAccess {
    
    private Connection connection;
    
    public VoteDAO() {super(Databases.VOTE_COMMAND_SESSION);}

}
