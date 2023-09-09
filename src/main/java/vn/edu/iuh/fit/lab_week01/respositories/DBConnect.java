package vn.edu.iuh.fit.lab_week01.respositories;

import vn.edu.iuh.fit.lab_week01.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect implements DBConfig{
    private static DBConnect insance;
    private Connection conn = null;
//    private
    public DBConnect() {

    }

    public void connection() throws Exception {
        if(insance == null){
            insance = new DBConnect();
        }
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(
                DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
    }

    public static DBConnect getInsance() {
        return insance;
    }
}
