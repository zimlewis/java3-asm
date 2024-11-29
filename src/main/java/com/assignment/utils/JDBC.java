package com.assignment.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost;databaseName=Java4Asm;encrypt=false";
    static String username = "Zimlewis";
    static String password = "Daylazim123!";

    static {
        try { // nạp driver
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**Mở kết nối*/
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }
    /**Thao tác dữ liệu*/
    public static int executeUpdate(String sql, Object... values) throws SQLException {
        try  {
            PreparedStatement stmt = JDBC.createPreStmt(sql, values);
            return stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    /**Truy vấn dữ liệu*/
    public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
        try {
            PreparedStatement stmt = JDBC.createPreStmt(sql, values);
            return stmt.executeQuery();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**Tạo PreparedStatement làm việc với SQL hoặc PROC*/
    private static PreparedStatement createPreStmt(String sql, Object... values) throws SQLException {
        try {
            Connection connection = JDBC.getConnection();
            PreparedStatement stmt = null;
            if(sql.trim().startsWith("{")) {
                stmt = connection.prepareCall(sql);
            }
            stmt = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                stmt.setObject(i + 1, values[i]);
            }
            return stmt;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}