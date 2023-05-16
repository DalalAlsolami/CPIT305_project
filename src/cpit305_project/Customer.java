
package cpit305_project;

import java.util.ArrayList;

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


}
