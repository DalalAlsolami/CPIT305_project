
package cpit305_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

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
        // TODO code application logic here
            // (1) Create Socket obj   
        Socket c = new Socket("127.0.0.1",8800);
        try {
         
         // (2) Create input stream obj (Scanner)
        Scanner in = new Scanner(c.getInputStream());
        
        // (3) Create output stream obj (PrintWriter)
         PrintWriter out = new PrintWriter(c.getOutputStream(),true);

        Scanner userInput = new Scanner(System.in);
        
        
        System.out.println("Server says: " + in.nextLine());
        String str;
        int i = 1;
        String ch= null;
        while (true){
            str = in.nextLine();
            System.out.println(str);
            if(str.equals("Select from the menu: ")){
                System.out.println("done");
                ch = userInput.nextLine();
                 System.out.print(ch);
                out.print(ch);
                break;
            }
        }
        
        System.out.print("what ");
        
        
        while (true) {
            if (ch.equals("1")) {
                System.out.print("what ");
                while (in.hasNextLine()){
                    str = in.nextLine();
                    System.out.println(str);
                }
            } else if (ch.equals(1)) {
                PartySuppRental.updateSupplies();
                break;
            } else if (ch.equals(1)) {
                break;
            } else if (ch.equals(1)) {
                PartySuppRental.serviceRating();
                break;
            } else {
                System.out.println("Invalid choice .. Try again ");
                break;
            }
        }
        }
         // (5) close the socket c.close();
         
        catch (UnknownHostException e) {
            System.err.println("Host not found");
        } catch (java.net.ConnectException e) {
            System.err.println("There are no connection at this port");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        c.close();
    }
    



}
