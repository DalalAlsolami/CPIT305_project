
package cpit305_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Lo {

  
    public static void main(String[] args) throws SQLException {
    
       String url="jdbc:mysql://localhost:3306";
        String username = "root";
        String pass = "053183";
      
         Connection conn = DriverManager.getConnection(url, username,pass); 
         
         java.sql.Statement st = conn.createStatement();
         //st.executeUpdate("CREATE DATABASE login");
            if(conn != null){
             System.out.println("Connected to DB");
            }
            
    }
    
}
