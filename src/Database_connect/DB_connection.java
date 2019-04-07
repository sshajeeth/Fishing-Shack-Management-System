package Database_connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_connection {
    public Connection dbconnect;

    public Connection getDB_connect() {
        String database_user_name = "root";//user name of the SQL database
        String database_password = "";//Password of the SQL database

        try {
            //Connecting to the SQL database
            dbconnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/fish_market?useSSL=false", database_user_name, database_password);

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return dbconnect;
    }
}
