package dataBase;

import java.sql.*;

public abstract class Model {
    Connection conn;
    Statement stmt;

    public void connect(){
        try {
            conn=DriverManager.getConnection(Constants.CONNECTION_STRING,Constants.DB_USER,Constants.DB_PASSWORD);
            stmt=conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet select(String query){
        try {
            connect();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excuteDbOperation(String sql){
        try {
            connect();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print(String s){
        System.out.println(s);
    }


}
