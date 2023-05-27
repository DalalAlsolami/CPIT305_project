package cpit305_project;


import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class bookingThread extends Thread {
    private int numberOfTables;
    private String username;
    private Supplies s;
    private int itemID;
    private int numOfdays;
    
    static ArrayList<Supplies> table = new ArrayList<>();
    static ArrayList<Supplies> speaker = new ArrayList<>();
    static ArrayList<Supplies> chair = new ArrayList<>();
    static String name,price,quantity;
    
    
    
    static private Socket incoming;
    public bookingThread(Socket incoming,ArrayList<Supplies> table1,ArrayList<Supplies> chair1 ,ArrayList<Supplies> speaker1) {
        this.incoming = incoming;
        table = table1;
        speaker = speaker1;
        chair = chair1;
    }

    bookingThread(Supplies s, String shomokh_, int numTable, int chTable, int dayTable) {
     this.numberOfTables=numTable;
     this.username = shomokh_;
     this.s= s;
     this.itemID = chTable;
     this.numOfdays = dayTable;
    }

    bookingThread(Supplies s, String shomokh_, int numTable) {
       this.s= s;
       this.username = shomokh_;
       this.numberOfTables=numTable;
    }
     public void run() {
        
        
        Scanner r = new Scanner(System.in);
        
         try (Scanner in = new Scanner(incoming.getInputStream());
                 PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);
                 DataInputStream din2 = new DataInputStream(new FileInputStream("input.txt"));) {

             out.println("Welcome");
             out.println("------------------------------");
             while (din2.available() > 0) {
                 out.println(din2.readLine());
             }
             out.println("------------------------------");
             out.println("Select from the menu: ");

             int ch;
             while (in.hasNext()) {//مو لازم 
                 ch = in.nextInt();
                 switch (ch) {
                     case 1:
                         rentProudct(in, out);
                         break;
                     case 2:
                         updateSupplies(in, out);
                         break;
                     case 3:
                         serviceRating(in, out);
                         break;
                     case 4:
                         System.exit(0);
                     default:
                         break;
                 }
                 
             }

        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
     }
    public static void rentProudct(Scanner in,PrintWriter out) throws FileNotFoundException, IOException {
        new Rent_Supplies();
            out.println("--------Rent product--------");
            //Scanner r = new Scanner(System.in);
            out.println(" 1.Tables  \n 2.Chairs  \n 3.Speakers \n 4.Exit \n---------------------------- ");
            out.println("Select from the menu: ");
            int ch = in.nextInt();
            
            switch (ch) {
                case 1: {                                  
                    out.println("-------Available tables-----");
                    for (int i = 0; i < table.size(); i++) {
                        System.out.println(table.size());
                        out.println(i + 1 + "." + table.get(i).toString());
                    }
                    out.println("----------------------------");
                 
                    while(true){
                    out.println("Choose product Id: ");
                    int chTable = in.nextInt();
                        if(chTable == 1 || chTable == 2 ){
                            out.println("How many do you want? ");
                            int numTable = in.nextInt();
                            if(table.get(chTable).getQuantity() < numTable){
                                out.println("Sorry the qountity of your order not avaliable");
                                break;
                            }
                            out.println("How many days do you need it? ");
                            int dayTable = in.nextInt();
                            
                            double Totalp =numTable*table.get(chTable).getPrice()*dayTable ; 
                            
                            out.println(Thread.currentThread().getName()+" "+ numTable+" items booked successfully with " + Totalp + "$");
                            table.get(chTable).setQuantity(numTable);
                             break;
                               
                           }else{
                            out.println("Invalid ID choose again ");
                            break;
                         } 
                    }
                    break;
                }
                case 2: { 
                    out.println("-------Available chairs-----");
                    for (int i = 0; i < chair.size(); i++) {
                        out.println(i + 1 +"." + chair.get(i).toString());
                    }
                    out.println("----------------------------");
                       while(true){
                        out.println("Choose product Id: ");
                        int chChair = in.nextInt();
                           if(chChair == 1 || chChair ==2 || chChair==3 ){     
                           
                             out.println("How many do you want? ");
                             int numChair = in.nextInt();
                             out.println("How many days do you need it? ");
                             int dayChair = in.nextInt();

                            double Totalp =numChair*chair.get(chChair).getPrice()*dayChair ; 
                            
                            out.println(Thread.currentThread().getName()+" "+ numChair+" items booked successfully with " + Totalp + "$");
                            chair.get(chChair).setQuantity(numChair);
                             break;
                            
                           }else{
                            out.println("Invalid ID choose again ");  
                           }
                        }
                        break;
                    }
                case 3: {
                    
                    out.println("-------Available speakers-----");
                    for (int i = 0; i < speaker.size(); i++) {
                        out.println(i+ 1 +"." +speaker.get(i).toString());
                    }
                    out.println("----------------------------");
                    
                    while(true){
                        out.println("Choose product Id: ");
                        int chSpeaker = in.nextInt();  
                        if(chSpeaker== 1 || chSpeaker == 2 ){
                            out.println("How many do you want? ");
                            int numSpeaker = in.nextInt();
                            out.println("How many days do you need it? ");
                            int daySpeaker = in.nextInt();
                            
                            double Totalp =numSpeaker*chair.get(chSpeaker).getPrice()*daySpeaker ; 
                            
                            out.println(Thread.currentThread().getName()+" "+ numSpeaker+" items booked successfully with " + Totalp + "$");
                            chair.get(chSpeaker).setQuantity(numSpeaker);
                             break;
                             
                        }else{
                        out.println("Invalid ID choose again ");
                    }
                }
                break;
            }
                case 4: {
                    serviceRating(in, out);
                }
        }
        
   } 
    public static void updateSupplies(Scanner in,PrintWriter out)throws FileNotFoundException, IOException {
        while (true) {
            out.println("--------Update Supplies--------");
            
            out.println("pick collection that you want to update or exit:\n 1.Tables  \n2.Chairs  \n3.Speakers  \n4.Exit \nSelect from the menu: ");
            int collection = in.nextInt();
            
            int choose = -1;
            if(collection != 4){
            out.println("what do you want to update: \n Change info. \n "
                    + " 1. Rename an item \n  2. Change price \n  3. Change quantity"
                    + "\n------------------------------------------------\n"
                    + "  4. Add new item \n  5. Delete item"
                    + "\n------------------------------------------------" + "\nSelect from the menu: ");
            choose = in.nextInt();
            }
            
            int item = 0;
            switch (collection) {
                case 1: {
                    if (choose != 4) {
                        for (int i = 0; i < table.size(); i++) {
                            out.println(i + 1 + "." + table.get(i).toString());
                        }
                        out.println("Choose product Id: ");
                        item = in.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            in.nextLine();
                            out.println("Enter new name:");
                            String name = in.nextLine();
                            table.get(item).setName(name);
                            out.println("changed");
                            break;
                        }
                        case 2:
                            out.println("Enter new price:");
                            int newPrice = in.nextInt();
                            table.get(item).setPrice(newPrice);
                            break;
                        case 3:
                            out.println("Enter new quantity:");
                            int newQuantity = in.nextInt();
                            table.get(item).setQuantity(newQuantity);
                            break;
                        case 4: {
                            in.nextLine();
                            out.println("Enter a name for new item:");
                            String name = in.nextLine();
                            out.println("Enter a price for new item:");
                            int Price = in.nextInt();
                            out.println("Enter the  quantity of the new item:");
                            int quantity = in.nextInt();
                            table.add(new Supplies(name, Price, quantity));
                            break;
                        }
                        case 5:
                            table.remove(item);
                            out.println("Item deleted successfully. ");
                            break;
                        default:
                            out.println("The number you entered not in the range.");
                            break;
                    }
                    break;
                }
                case 2: {
                    if (choose != 4) {
                        for (int i = 0; i < chair.size(); i++) {
                            out.println(i + 1 + "." + chair.get(i).toString());
                        }
                        out.println("Enter item number:");
                        item = in.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            in.nextLine();
                            out.println("Enter new name:");
                            String name = in.nextLine();
                            chair.get(item).setName(name);
                            break;
                        }
                        case 2:{
                            out.println("Enter new price:");
                            int newPrice = in.nextInt();
                            chair.get(item).setPrice(newPrice);
                            break;
                        }
                        case 3:{
                            out.println("Enter new quantity:");
                            int newQuantity = in.nextInt();
                            chair.get(item).setQuantity(newQuantity);
                            break;
                        }
                        case 4: {
                            in.nextLine();
                            out.println("Enter a name for new item:");
                            String name = in.nextLine();
                            out.println("Enter a price for new item:");
                            int Price = in.nextInt();
                            out.println("Enter the quantity of the new item: ");
                            int quantity = in.nextInt();
                            chair.add(new Supplies(name, Price, quantity));
                            break;
                        }
                        case 5:
                            chair.remove(item);
                            out.println("Item deleted successfully. ");
                            break;
                        default:
                            out.println("The number you entered not in the range.");
                            break;
                    }
                    break;
                }
                case 3: {
                    if (choose != 4) {
                        for (int i = 0; i < speaker.size(); i++) {
                            out.println(i + 1 + "." + speaker.get(i).toString());
                        }
                        out.println("Enter item number:");
                        item = in.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            in.nextLine();
                            out.println("Enter new name:");
                            String name = in.nextLine();
                            speaker.get(item).setName(name);
                            break;
                        }
                        case 2: {
                            out.println("Enter new price:");
                            int newPrice = in.nextInt();
                            speaker.get(item).setPrice(newPrice);
                            break;
                        }
                        case 3: {
                            out.println("Enter new quantity:");
                            int newQuantity = in.nextInt();
                            speaker.get(item).setQuantity(newQuantity);
                            break;
                        }
                        case 4: {
                            out.println("Enter a name for new item: ");
                            String name = in.next();
                            out.println("Enter a price for new item: ");
                            int Price = in.nextInt();
                            out.println("Enter the quantity of the new item: ");
                            int quantity = in.nextInt();
                            speaker.add(new Supplies(name, Price, quantity));
                            break;
                        }
                        case 5:
                            speaker.remove(item);
                            out.println("Item deleted successfully. ");
                            break;
                        default:
                            out.println("The number you entered not in the range.");
                            break;
                    }
                    break;
                }
                case 4: {
                    System.exit(0);
                }
            }
        }
    }
    
    public static void serviceRating(Scanner in,PrintWriter out) {
        out.println("Thank you for visiting our store.\nPlease rate your experience on a scale from 1(unhappy) to 10(happy): ");

        int rating = in.nextInt();

        if (rating == 10) {
            out.println("thank you for rating.");
        } else if (rating < 10 || rating > 0) {
            in.nextLine();
            out.println("How can we make your experience even better?");
            String improve = in.nextLine();
            out.println("thank you for rating.");
        }

        System.exit(0);
    }
}
