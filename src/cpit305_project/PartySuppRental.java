package cpit305_project;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;

public class PartySuppRental {
    
    static ArrayList<Supplies> table = new ArrayList<>();
    static ArrayList<Supplies> speaker = new ArrayList<>();
    static ArrayList<Supplies> chair = new ArrayList<>();
    static String name,price,quantity;
    static Socket incoming;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        try(DataInputStream tables = new DataInputStream(new FileInputStream("tables.txt"));
            //DataInputStream din2 = new DataInputStream(new FileInputStream("input.txt"));
            DataInputStream chairs = new DataInputStream(new FileInputStream("chair.txt"));
            DataInputStream speakers = new DataInputStream(new FileInputStream("speaker.txt"));)
        {
            while (tables.available() > 0) {
               name = tables.readLine();
                price = tables.readLine();
                quantity = tables.readLine();
                table.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
            }

            while (chairs.available() > 0) {
               name = chairs.readLine();
               price = chairs.readLine();
               quantity = chairs.readLine();
                chair.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
            }
            while (speakers.available() > 0) {
                name = speakers.readLine();
                price = speakers.readLine();
                quantity = speakers.readLine();
                speaker.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        ServerSocket s = new ServerSocket(8800);
        System.out.println("Server waiting Connection...");
        
        while (true) {
              incoming = s.accept();
              System.out.println("Client connect via: " + incoming.getLocalAddress());
              Runnable run = new bookingThread(incoming);
              Thread t = new Thread(run);
              t.start();
          }

        

    }

    public static void rentProudct(ArrayList<Supplies> table, ArrayList <Supplies> chair, ArrayList <Supplies> speaker,Scanner in,PrintWriter out) throws FileNotFoundException, IOException {
            out.println("--------Rent product--------");
            Scanner r = in;
            out.println(" 1.Tables  \n 2.Chairs  \n 3.Speakers \n---------------------------- ");
            out.print("select from menu: ");
            int answer = in.nextInt();
            int i=0;
            switch (answer) {
                case 1: {                                  
                    out.println("-------Available tables-----");
                    for (int j = 0; j < table.size(); j++) {
                        out.println(j + 1 + "." + table.get(j).toString()); //problem
                    }
                   out.println("----------------------------");
                 
                    while(true){
                    out.print("choose table Id: ");
                    int chTable = r.nextInt();
                        if(chTable == 1 || chTable == 2 ){
                            out.print("How many table do you want? ");
                            int numTable = r.nextInt();

                            out.print("How many days do you need it? ");
                            int dayTable = r.nextInt();
                            
                             //Supplies s = new Supplies(name, table.get(chTable-1).getPrice() ,table.get(chTable-1).getQuantity());
                             //bookingThread t1 = new bookingThread(s,"shomokh ",numTable,chTable,dayTable);
                             //bookingThread t2 = new  bookingThread(s,"shrooge ",10,1,1);
                             //t1.start();
                             //t2.start();
                             break;
                               
                           }else{
                            System.out.println("Invalid ID choose again ");  
                        }
                        
                    }
                    break;
                }
                case 2: { 
                    System.out.println("-------Available chairs-----");
                    for (int ii = 0; i < chair.size(); i++) {
                        System.out.println(ii + 1 +"." + chair.get(i).toString());
                    }
                    System.out.println("----------------------------");
                       while(true){
                        System.out.print("choose chair Id: ");
                        int chTable = r.nextInt();
                           if(chTable == 1 || chTable ==2 || chTable==3 ){     
                           
                             System.out.print("How many chair do you want? ");
                             int numTable = r.nextInt();
                             System.out.print("How many days do you need it? ");
                             int dayTable = r.nextInt();

                             Supplies s = new Supplies(name,chair.get(chTable-1).getPrice(), chair.get(chTable-1).getQuantity());
                             bookingThread t1 = new bookingThread(s,"shomokh ",numTable,chTable,dayTable);
                             bookingThread t2 = new  bookingThread(s,"shrooge ",30,1,1);
                             t1.start();
                             t2.start();
                            break; 
                           }else{
                            System.out.println("Invalid ID choose again ");  
                           }
                        }
                        break;
                    }
                case 3: {
                    
                    System.out.println("-------Available speakers-----");
                    for (int ii = 0; i < speaker.size(); i++) {
                        System.out.println(ii+ 1 +"." +speaker.get(i).toString());
                    }
                    System.out.println("----------------------------");
                    
                    while(true){
                        System.out.print("choose speaker Id: ");
                        int chTable = r.nextInt();  
                        if(chTable== 1 || chTable == 2 ){
                            System.out.print("How many chair do you want? ");
                            int numTable = r.nextInt();
                            System.out.print("How many days do you need it? ");
                            int dayTable = r.nextInt();
                            Supplies s = new Supplies(name,speaker.get(chTable-1).getPrice(), speaker.get(chTable-1).getQuantity());
                            bookingThread t1 = new bookingThread(s,"shomokh ",numTable,chTable,dayTable);
                            bookingThread t2 = new  bookingThread(s,"shrooge ",6,1,1);
                            t1.start();
                            t2.start();
                            break;
                        }else{
                        System.out.println("Invalid ID choose again ");
                    }
                }
                break;
            }
        }
        
   }

    public static void updateSupplies() {
        while (true) {
            System.out.println("--------Update Supplies--------");
            Scanner r = new Scanner(System.in);
            System.out.print("Enter collection that you want to update or exit:\n 1.Tables  \n2.Chairs  \n3.Speakers  \n4.Exit \nEnter number: ");
            int collection = r.nextInt();
            System.out.print("Choose what do you want to update: \n Change info. \n "
                    + " 1. Rename an item \n  2. Change price \n  3. Change quantity"
                    + "\n------------------------------------------------\n"
                    + "  5. Add new item \n  6. Delete item"
                    + "\n------------------------------------------------" + "\nEnter number: ");
            int choose = r.nextInt();
            int item = 0;
            switch (collection) {
                case 1: {
                    if (choose == 1 || choose == 2) {
                        for (int i = 0; i < table.size(); i++) {
                            System.out.println(i + 1 + "." + table.get(i).toString());
                        }
                        System.out.print("Enter item number :");
                        item = r.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            System.out.print("Enter new name:");
                            String name = r.next();
                            table.get(item).setName(name);
                            break;
                        }
                        case 2:
                            System.out.print("Enter new price:");
                            int newPrice = r.nextInt();
                            table.get(item).setPrice(newPrice);
                            break;
                        case 3:
                            System.out.print("Enter new quantity:");
                            int newQuantity = r.nextInt();
                            table.get(item).setQuantity(newQuantity);
                            break;
                        case 4: {
                            System.out.print("Enter a name for new item:");
                            String name = r.next();
                            System.out.print("Enter a price for new item:");
                            int Price = r.nextInt();
                            System.out.print("Enter the  quantity of the new item:");
                            int quantity = r.nextInt();
                            table.add(new Supplies(name, Price, quantity));
                            break;
                        }
                        case 5:
                            table.remove(item);
                            System.out.println("Item deleted successfully. ");
                            break;
                        default:
                            System.out.println("The number you entered not in the range.");
                            break;
                    }
                    break;
                }
                case 2: {
                    if (choose == 1 || choose == 2) {
                        for (int i = 0; i < chair.size(); i++) {
                            System.out.println(i + 1 + "." + chair.get(i).toString());
                        }
                        System.out.print("Enter item number:");
                        item = r.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            System.out.print("Enter new name:");
                            String name = r.next();
                            chair.get(item).setName(name);
                            break;
                        }
                        case 2:
                            System.out.print("Enter new price:");
                            int newPrice = r.nextInt();
                            chair.get(item).setPrice(newPrice);
                            break;
                        case 4: {
                            System.out.print("Enter a name for new item:");
                            String name = r.next();
                            System.out.print("Enter a price for new item:");
                            int Price = r.nextInt();
                            System.out.println("Enter the quantity of the new item: ");
                            int quantity = r.nextInt();
                            chair.add(new Supplies(name, Price, quantity));
                            break;
                        }
                        case 5:
                            chair.remove(item);
                            System.out.println("Item deleted successfully. ");
                            break;
                        default:
                            System.out.println("The number you entered not in the range.");
                            break;
                    }
                    break;
                }
                case 3: {
                    if (choose == 1 || choose == 2) {
                        for (int i = 0; i < speaker.size(); i++) {
                            System.out.println(i + 1 + "." + table.get(i).toString());
                        }
                        System.out.print("Enter item number:");
                        item = r.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            System.out.print("Enter new name:");
                            String name = r.next();
                            speaker.get(item).setName(name);
                            break;
                        }
                        case 2:
                            System.out.print("Enter new price:");
                            int newPrice = r.nextInt();
                            speaker.get(item).setPrice(newPrice);
                            break;
                        case 4: {
                            System.out.print("Enter a name for new item: ");
                            String name = r.next();
                            System.out.print("Enter a price for new item: ");
                            int Price = r.nextInt();
                            System.out.println("Enter the quantity of the new item: ");
                            int quantity = r.nextInt();
                            speaker.add(new Supplies(name, Price, quantity));
                            break;
                        }
                        case 5:
                            speaker.remove(item);
                            System.out.println("Item deleted successfully. ");
                            break;
                        default:
                            System.out.println("The number you entered not in the range.");
                            break;
                    }
                    break;
                }
                case 4: {
                    serviceRating();
                }
            }
        }
    }

    public static void serviceRating() {
        System.out.println("Thank you for visiting our store.\nPlease rate your experience on a scale from 1(unhappy) to 10(happy): ");

        Scanner scanner = new Scanner(System.in);
        int rating = scanner.nextInt();

        if (rating == 10) {
            System.out.println("thank you for rating.");
        } else if (rating < 10 || rating > 0) {
            scanner.nextLine();
            System.out.println("How can we make your experience even better?");
            String improve = scanner.nextLine();
            System.out.println("thank you for rating.");
        }

        System.exit(0);
    }
    
}
