package vn.edu.iuh.fit.lab_week01.config;

public interface DBConfig {
    String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    String DB_URL = "jdbc:mariadb://localhost:3307/mydb";

    //  Database credentials
    String USER = "root";
    String PASS = "rôt";
}
