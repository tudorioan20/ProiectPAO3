package db;

import java.sql.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Connection connection;

    private Db() {

    }

    public static Connection getInstance() throws SQLException {
        if(connection == null) {
            String url = "jdbc:mysql://localhost:3306/pao_stomatologie";
            String user = "root";
            String password = "1234";

            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
//public class Db {
//
//    private static Connection dbConnection = null;
//    private static Db dbInstance = null;
//
//    private Db() {
//        String url = "jdbc:mysql://localhost:3306/pao_stomatologie";
//        String user = "root";
//        String password = "1234";
//        try {
//            dbConnection = DriverManager.getConnection(url, user, password);
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public static Db getInstanta() {
//        if (dbInstance == null) {
//            dbInstance = new Db();
//        }
//        return dbInstance;
//    }
//}