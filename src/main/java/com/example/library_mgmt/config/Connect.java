package com.example.library_mgmt.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Connect {
    private static Connection con;
    private static Statement st;
    public static boolean init(){
        // creates a connection and statement
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connect.con = DriverManager.getConnection("jdbc:mysql://localhost:4500/library?autoReconnect=true&useSSL=false", "root", "admin");
            Connect.st=con.createStatement();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static Connection getConnection(){
        if(Connect.init())
            return con;
        else return null;
    }

    public static boolean execute(String query) throws SQLException {
        // returns true if success else false
        if(Connect.init()){
            Connect.st.executeUpdate(query);
            return true;
        }

        else return false;
    }

    public static ResultSet executeSelect(String query) throws SQLException {
        // returns true if success else false
        ResultSet rs;
        if(Connect.init()) {
            rs = Connect.st.executeQuery(query);
            return rs;
        }
        else return null;
    }

    public static boolean executeTransaction(String queries[]) throws SQLException {
        Connect.con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        try {
            for (String i : queries) {
                stmt.executeUpdate(i);
            }
            con.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            con.rollback();
            throw e;
        }
        finally {
            con.setAutoCommit(true);
        }
        return true;
    }

    public static ResultSet read_books(){
        String getBooks = "call get_books()";
        ResultSet rs = null;
        try {
            rs = Connect.executeSelect(getBooks);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
}
