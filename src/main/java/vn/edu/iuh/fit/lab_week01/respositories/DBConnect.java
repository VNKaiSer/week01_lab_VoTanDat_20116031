package vn.edu.iuh.fit.lab_week01.respositories;

import vn.edu.iuh.fit.lab_week01.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect implements DBConfig{
    private static DBConnect insance;
    private Connection conn;
//    private
    public DBConnect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(
                DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
    }

    public static DBConnect getInsance() throws Exception {
        if(insance == null){
            insance = new DBConnect();
        }
        return insance;
    }

    public Connection getConn() {
        return conn;
    }

//    public static void main(String[] args) throws Exception {
//        DBConnect.getInsance().getConn();
//    }
}
