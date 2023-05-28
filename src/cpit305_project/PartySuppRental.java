package cpit305_project;

import java.io.*;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;

public class PartySuppRental {

    static ArrayList<Supplies> table = new ArrayList<>();
    static ArrayList<Supplies> speaker = new ArrayList<>();
    static ArrayList<Supplies> chair = new ArrayList<>();
    static String name, price, quantity;
    static Socket incoming;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        ServerSocket s = new ServerSocket(8800);
        System.out.println("Server waiting Connection...");
        try {
            DataInputStream tables = new DataInputStream(new FileInputStream("tables.txt"));
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
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        while (true) {
            incoming = s.accept();
            new NewJFrame(table, chair, speaker);
            System.out.println("Client connect via: " + incoming.getLocalAddress());
            Thread t = new Thread();
            t.start();
        }
    }

}
