package cpit305_project;

import java.net.Socket;
import java.util.ArrayList;

public class bookingThread extends Thread {

    static ArrayList<Supplies> table = new ArrayList<>();
    static ArrayList<Supplies> speaker = new ArrayList<>();
    static ArrayList<Supplies> chair = new ArrayList<>();
    static String name, price, quantity;

    static private Socket incoming;

    public bookingThread(Socket incoming, ArrayList<Supplies> table1, ArrayList<Supplies> chair1, ArrayList<Supplies> speaker1) {
        this.incoming = incoming;
        table = table1;
        speaker = speaker1;
        chair = chair1;
    }

//
}
