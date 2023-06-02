package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    public Connection connection;
    public DBContext()
    {
        try {
            //Change the username password and url to connect your own database
            // We get these information from environment variable so that it 
            // can be changed without recompiling our code
            String username = System.getenv("CSUSERNAME");
            String password = System.getenv("CSPASSWORD");
            String url = System.getenv("CS");
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Properties properties = new Properties();
            properties.setProperty("user", username);
            properties.setProperty("password", password);
            properties.setProperty("sendTimeAsDatetime", "false"); // Set sendTimeAsDatetime to false
            connection = DriverManager.getConnection(url, properties);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
