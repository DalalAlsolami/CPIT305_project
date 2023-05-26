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
       
        ServerSocket s = new ServerSocket(8800);
        System.out.println("Server waiting Connection...");
        try{
            DataInputStream tables = new DataInputStream(new FileInputStream("tables.txt"));
            DataInputStream din2 = new DataInputStream(new FileInputStream("input.txt"));
            DataInputStream chairs = new DataInputStream(new FileInputStream("chair.txt"));
            DataInputStream speakers = new DataInputStream(new FileInputStream("speaker.txt"));
        
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
        while (true) {
              incoming = s.accept();
              System.out.println("Client connect via: " + incoming.getLocalAddress());
              Runnable run = new bookingThread(incoming,table,chair,speaker);
              Thread t = new Thread(run);
              t.start();
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
