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
    public bookingThread(Socket incoming) {
        this.incoming = incoming;
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
        
        try(Scanner in = new Scanner(incoming.getInputStream());
            PrintWriter out = new PrintWriter(incoming.getOutputStream(),true);
            DataInputStream tables = new DataInputStream(new FileInputStream("tables.txt"));
            DataInputStream din2 = new DataInputStream(new FileInputStream("input.txt"));
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
            
        out.println("Welcome");
        out.println("------------------------------");
        while (din2.available() > 0) {
            out.println(din2.readLine());
        }
        out.println("------------------------------");
        out.println("Select from the menu: ");
        
        int ch;
        while(in.hasNext()) {
            ch = in.nextInt();
            if (ch==1){
                rentProudct(in,out);
            }
        }
        
        }
          
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
         
        //Scanner r = new Scanner(System.in);
        //String name = null,price = null,quantity = null;
        //System.out.println("------------------------------");
       //s.bookItem(username,numberOfTables, numOfdays,itemID);
     }
    public static void rentProudct(Scanner in,PrintWriter out) throws FileNotFoundException, IOException {
            out.println("--------Rent product--------");
            Scanner r = new Scanner(System.in);
            out.println(" 1.Tables  \n 2.Chairs  \n 3.Speakers \n---------------------------- ");
            out.println("Select from the menu: ");
            int ch = in.nextInt();
            
            switch (ch) {
                case 1: {                                  
                    out.println("-------Available tables-----");
                    for (int i = 0; i < table.size(); i++) {
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
                             //Supplies s = new Supplies(name, table.get(chTable-1).getPrice() ,table.get(chTable-1).getQuantity());
                             //bookingThread t1 = new bookingThread(s,"shomokh ",numTable,chTable,dayTable);
                             //bookingThread t2 = new  bookingThread(s,"shrooge ",10,1,1);
                             //t1.start();
                             //t2.start();
                             break;
                               
                           }else{
                            out.println("Invalid ID choose again ");  
                        }
                        
                    }
                    break;
                }
                case 2: { 
                    System.out.println("-------Available chairs-----");
                    for (int i = 0; i < chair.size(); i++) {
                        System.out.println(i + 1 +"." + chair.get(i).toString());
                    }
                    System.out.println("----------------------------");
                       while(true){
                        System.out.print("choose product Id: ");
                        int chTable = r.nextInt();
                           if(chTable == 1 || chTable ==2 || chTable==3 ){     
                           
                             System.out.print("How many do you want? ");
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
                    for (int i = 0; i < speaker.size(); i++) {
                        System.out.println(i+ 1 +"." +speaker.get(i).toString());
                    }
                    System.out.println("----------------------------");
                    
                    while(true){
                        System.out.print("choose product Id: ");
                        int chTable = r.nextInt();  
                        if(chTable== 1 || chTable == 2 ){
                            System.out.print("How many do you want? ");
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
}
