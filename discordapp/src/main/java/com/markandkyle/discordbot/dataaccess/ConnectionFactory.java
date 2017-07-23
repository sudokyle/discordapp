/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ConnectionFactory {

    private static final String CREATE_VOTE_TABLE = "CREATE TABLE IF NOT EXISTS vote (\n"
        + "	vote_id integer PRIMARY KEY,\n"
        + "     public_session_id string NOT NULL,\n"
        + "	timestamp integer(4) not null default (strftime('%s','now')),\n"
        + "	selections blob\n"
        + ");";

    private static final String CREATE_SESSION_TABLE = "CREATE TABLE IF NOT EXISTS session (\n"
        + "	private_session_id string PRIMARY KEY,\n"
        + "     public_session_id string NOT NULL,\n"
        + "	start_timestamp integer(4) not null default (strftime('%s','now')),\n"
        + "     end_timestamp integer(4),\n"
        + "	options blob\n"
        + ");";
    
    public static Connection getConnection() {
 
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "jdbc:sqlite:session.db";
        
        // try to create the database first if it does not exist
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");        
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // then create a connection to the database and return it
        Connection con = null;
        try{
            con = DriverManager.getConnection(url);
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate(CREATE_VOTE_TABLE);
            stmt.executeUpdate(CREATE_SESSION_TABLE);
            System.out.println("Created tables");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            con = null;
        }
        
        return con;
    }
}
