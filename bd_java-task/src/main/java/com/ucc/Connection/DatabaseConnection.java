package com.ucc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/bdtrabajo";
    private static String user = "root";
    private static String password = "puG281c3";
    
    private static Connection myConn;

    public static Connection getInstanceConnection() throws SQLException{
        if(myConn == null){
            myConn = DriverManager.getConnection(url, user, password);
        }
        return myConn;
    }

}
