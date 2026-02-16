package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    private static final String DB_URL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {

//        String DB_URL = null, DB_USERNAME = null, DB_PASSWORD = null;

        FileInputStream fis;
        Properties prop = new Properties();

//        try {
//            fis = new FileInputStream("src/main/resources/config.properties");
//            prop.load(fis);
//            DB_URL = prop.getProperty("db.url");
//            DB_USERNAME = prop.getProperty("db.username");
//            DB_PASSWORD = prop.getProperty("db.password");
//            fis.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        Connection connect = null;
        connect = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return connect;
    }

}