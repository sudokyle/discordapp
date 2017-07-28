package com.markandkyle.discordbot.dataaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by CptAmerica on 7/27/17.
 */
public class DataAccess {
    protected Connection connection;

    DataAccess(String db_name) {
        this.connection = ConnectionFactory.getConnection(db_name);
    }

    List<Map<String, Object>> fetch(String fetchQuery) {
        List<Map<String, Object>> resultList = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(fetchQuery);
            resultList = new ArrayList<Map<String, Object>>();
            Map<String, Object> row = null;

            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (rs.next()) {
                row = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }

    Object fetchFromResult(String key, List<Map<String, Object>> result) {
        int i = 0;
        boolean isMatch = false;
        while( (i < result.size()) && !isMatch) {
            if(result.get(i).get(key) != null) isMatch = true;
            if(!isMatch) i++;
        }
        if(i >= result.size()) return null;
        return result.get(i).get(key);
    }

    @Override
    public void finalize() {
        try {
            if(this.connection != null) {
                this.connection.close();
            }
            super.finalize();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
