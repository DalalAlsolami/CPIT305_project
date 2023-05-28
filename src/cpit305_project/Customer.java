
package cpit305_project;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



class Customer {
    String username;
    String password;
 
  
    Customer(String str, String str2) {
      this.username = str;
      this.password = str2;
     
    }
    Customer() {
       
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static void main(String[] args) throws UnknownHostException, IOException  {
        Socket c = new Socket("127.0.0.1",8800);
        c.close();
    }
    
}