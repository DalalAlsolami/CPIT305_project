package cpit305_project;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PartySuppRental extends JFrame implements ActionListener {
    
   
    static JButton button ,button1;
    static JLabel label;
    static JButton button3 ,button4;
    static JLabel label2,label3;
    static JTextField input,input2;
    public PartySuppRental(){
       
       JFrame frame = new JFrame();
       frame.setTitle("register"); 
       frame.setSize(400, 300);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       frame.setResizable(false);
       frame.setLayout(null);
       
      
       button = new JButton("Manager");
       button1 = new JButton("customer");
       label = new JLabel("register as a manager or a customer:");
       
       frame.add(button);
       frame.add(button1);
       frame.add(label);
        
        button.setBounds(100, 100, 150, 40);
        button1.setBounds(100, 150, 150, 40);
        label.setBounds(70, 50, 250, 40);
        
         button.addActionListener(this);
         button1.addActionListener(this);
         
         
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        new  PartySuppRental();
         
        ArrayList<Supplies> table = new ArrayList<>();
        ArrayList<Supplies> speaker = new ArrayList<>();
        ArrayList<Supplies> chair = new ArrayList<>();
      //  DataInputStream tables = new DataInputStream(new FileInputStream("tables.txt"));
        DataInputStream din2 = new DataInputStream(new FileInputStream("input.txt"));
       // DataInputStream chairs = new DataInputStream(new FileInputStream("chair.txt"));
      //  DataInputStream speakers = new DataInputStream(new FileInputStream("speaker.txt"));
        Scanner r = new Scanner(System.in);

        System.out.println("------------------------------");
        while (din2.available() > 0) {
            System.out.println(din2.readLine());
        }
        System.out.println("------------------------------");
       // try {
//            while (tables.available() > 0) {
//                String name = tables.readLine();
//                String price = tables.readLine();
//                String quantity = tables.readLine();
//                //table.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
//            }

//            while (chairs.available() > 0) {
//                String name = chairs.readLine();
//                String price = chairs.readLine();
//                String quantity = chairs.readLine();
//                chair.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
//            }
//            while (speakers.available() > 0) {
//                String name = speakers.readLine();
//                String price = speakers.readLine();
//                String quantity = speakers.readLine();
//                speaker.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
//            }
            
//        } catch (EOFException e) {
//            System.out.println("End of file reached");
//        }

        System.out.print("Select from the menu: ");
        int ch = r.nextInt();

        while (true) {
            if (ch == 1) {
                rentProudct(table, chair, speaker);
                break;
            } else if (ch == 2) {
                updateSupplies(table, chair, speaker);
                break;
            } else if (ch == 3) {
                break;
            } else if (ch == 4) {
                serviceRating();
                break;
            } else {
                System.out.println("Invalid choice .. Try again ");
                break;
            }
        }

    }

    private static void rentProudct(ArrayList<Supplies> table, ArrayList chair, ArrayList speaker) throws FileNotFoundException, IOException {
            System.out.println("--------Rent product--------");
            Scanner r = new Scanner(System.in);
            System.out.println(" 1.Tables  \n 2.Chairs  \n 3.Speakers \n---------------------------- ");
            System.out.print("select from menu: ");
            int answer = r.nextInt();
             String name = null, price = null,quantity = null;
            switch (answer) {
                case 1: {
                      DataInputStream tables = new DataInputStream(new FileInputStream("tables.txt"));
                     while (tables.available() > 0) {
                      name = tables.readLine();
                      price = tables.readLine();
                      quantity = tables.readLine();
                      table.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
                     }
                                     
                    System.out.println("-------Available tables-----");
                    for (int i = 0; i < table.size(); i++) {
                        System.out.println(i + 1 + "." + table.get(i).toString());
                    }
                    System.out.println("----------------------------");
                    System.out.print("choose table Id: ");
                    int chTable = r.nextInt();

                    System.out.print("How many table do you want? ");
                    int numTable = r.nextInt();

                    System.out.print("How many days do you need it? ");
                    int dayTable = r.nextInt();
                    
                     Supplies s = new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity));
                     bookingThread t1 = new bookingThread(s,"shomokh ",numTable,chTable,dayTable);
                     bookingThread t2 = new  bookingThread(s,"shrooge ",10,1,1);
                     t1.start();
                     t2.start();

                    break;

                }
                case 2: {
                     DataInputStream chairs = new DataInputStream(new FileInputStream("chair.txt"));
                     while (chairs.available() > 0) {
                        name = chairs.readLine();
                        price = chairs.readLine();
                        quantity = chairs.readLine();
                     chair.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
            }
                    System.out.println("-------Available chairs-----");
                    for (int i = 0; i < chair.size(); i++) {
                        System.out.println(i + 1 +"." + chair.get(i).toString());
                    }
                    System.out.println("----------------------------");
                    
                    System.out.print("choose chair Id: ");
                    int chTable = r.nextInt();
       
                    System.out.print("How many chair do you want? ");
                    int numTable = r.nextInt();
                    
                    System.out.print("How many days do you need it? ");
                    int dayTable = r.nextInt();
                    
                     Supplies s = new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity));
                     bookingThread t1 = new bookingThread(s,"shomokh ",numTable,chTable,dayTable);
                     bookingThread t2 = new  bookingThread(s,"shrooge ",30,1,1);
                     t1.start();
                     t2.start();
                    break;

                }
                case 3: {
                     DataInputStream speakers = new DataInputStream(new FileInputStream("speaker.txt"));
                      while (speakers.available() > 0) {
                      name = speakers.readLine();
                      price = speakers.readLine();
                      quantity = speakers.readLine();
                speaker.add(new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity)));
            }
                    System.out.println("-------Available speakers-----");
                    for (int i = 0; i < speaker.size(); i++) {
                        System.out.println(i+ 1 +"." +speaker.get(i).toString());
                    }
                    System.out.println("----------------------------");

                    System.out.print("choose speaker Id: ");
                    int chTable = r.nextInt();

                    System.out.print("How many chair do you want? ");
                    int numTable = r.nextInt();
                    System.out.print("How many days do you need it? ");
                    int dayTable = r.nextInt();
                     Supplies s = new Supplies(name, Integer.parseInt(price), Integer.parseInt(quantity));
                     bookingThread t1 = new bookingThread(s,"shomokh ",numTable,chTable,dayTable);
                     bookingThread t2 = new  bookingThread(s,"shrooge ",30,1,1);
                     t1.start();
                     t2.start();
                    break;
                }
            }
        
    }

    private static void updateSupplies(ArrayList<Supplies> table, ArrayList<Supplies> chair, ArrayList<Supplies> speaker) {
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

    private static void serviceRating() {
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
    
    private static void register() {
        //PartySuppRental pr = new PartySuppRental();
        JFrame frame2 = new JFrame();
       frame2.setTitle("manager register"); 
       frame2.setSize(400, 300);
       frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame2.setVisible(true);
       frame2.setResizable(false);
       frame2.setLayout(null);
       
       button3 = new JButton("login");
       button4 = new JButton("exit");
       label2 = new JLabel("enter user name:");
       label3 = new JLabel("enter password:");
       input = new JTextField(10);
       input2 = new JTextField(10);
       frame2.add(button3);
       frame2.add(button4);
       frame2.add(label2);
       frame2.add(label3);
       frame2.add(input);
       frame2.add(input2);
        
        button3.setBounds(50, 150, 100, 40);
        button4.setBounds(200, 150, 100, 40);
        label2.setBounds(50, 30, 250, 40);
        label3.setBounds(50, 70, 250, 40);
        input.setBounds(150, 45, 150, 20);
        input2.setBounds(150, 85, 150, 20);
         //button2.addActionListener(pr);
         //button12.addActionListener(pr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button ){
            register();
        }
        
    }
}
