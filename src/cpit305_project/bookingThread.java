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
             while (in.hasNext()) {
                 ch = in.nextInt();
                 if (ch == 1) {
                     rentProudct(in, out);
                 }
                 else if (ch == 2) {
                     updateSupplies(in, out);
                 }
             }

        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
     }
    public static void rentProudct(Scanner in,PrintWriter out) throws FileNotFoundException, IOException {
            out.println("--------Rent product--------");
            //Scanner r = new Scanner(System.in);
            out.println(" 1.Tables  \n 2.Chairs  \n 3.Speakers \n---------------------------- ");
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
                    out.println("choose product Id: ");
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
                        out.println("choose product Id: ");
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
                        out.println("choose product Id: ");
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
        }
        
   } 
    public static void updateSupplies(Scanner in,PrintWriter out)throws FileNotFoundException, IOException {
        while (true) {
            out.println("--------Update Supplies--------");
            
            out.println("choose collection that you want to update or exit:\n 1.Tables  \n2.Chairs  \n3.Speakers  \n4.Exit \nSelect from the menu: ");
            int collection = in.nextInt();
            out.println("Choose what do you want to update: \n Change info. \n "
                    + " 1. Rename an item \n  2. Change price \n  3. Change quantity"
                    + "\n------------------------------------------------\n"
                    + "  5. Add new item \n  6. Delete item"
                    + "\n------------------------------------------------" + "\nSelect from the menu: ");
            int choose = in.nextInt();
            int item = 0;
            switch (collection) {
                case 1: {
                    if (choose == 1 || choose == 2) {
                        for (int i = 0; i < table.size(); i++) {
                            out.println(i + 1 + "." + table.get(i).toString());
                        }
                        out.println("choose product Id: ");
                        item = in.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            out.println("Enter new name:");
                            String name = in.next();
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
                            out.println("Enter a name for new item:");
                            String name = in.next();
                            out.println("Enter a price for new item:");
                            int Price = in.nextInt();
                            out.println("Enter the  quantity of the new item:");
                            int quantity = in.nextInt();
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
                            out.println(i + 1 + "." + chair.get(i).toString());
                        }
                        out.println("Enter item number:");
                        item = in.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            out.println("Enter new name:");
                            String name = in.next();
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
                            table.get(item).setQuantity(newQuantity);
                            break;
                        }
                        case 4: {
                            out.println("Enter a name for new item:");
                            String name = in.next();
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
                    if (choose == 1 || choose == 2) {
                        for (int i = 0; i < speaker.size(); i++) {
                            out.println(i + 1 + "." + table.get(i).toString());
                        }
                        out.println("Enter item number:");
                        item = in.nextInt();
                        item = item - 1;
                    }
                    switch (choose) {
                        case 1: {
                            out.println("Enter new name:");
                            String name = in.next();
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
                            table.get(item).setQuantity(newQuantity);
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
}
