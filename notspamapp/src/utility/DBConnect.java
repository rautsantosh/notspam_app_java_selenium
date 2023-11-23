/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rakeshv
 */
public class DBConnect {

    private static final String mysqlHost = "localhost";
    private static final String mysqlUser = "root";
    //private static final String mysqlPass = "Toothpaste123!";
    private static final String mysqlPort = "3309";
    private static final String mysqlPass = "swineflu2009";
    //private static final String mysqlPort = "3309";

    public static Connection mysqlConnection() throws Exception {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionString;
            connectionString = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/notspam_hot";
            con = (Connection) DriverManager.getConnection(connectionString, mysqlUser, mysqlPass);
        } catch (Exception e) {
            throw e;
        }
        return con;
    }

    public static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}
