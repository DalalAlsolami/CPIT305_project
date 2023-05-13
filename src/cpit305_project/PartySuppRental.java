package cpit305_project;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartySuppRental {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList table = new ArrayList();
        ArrayList speaker = new ArrayList();
        ArrayList chair = new ArrayList();
        DataInputStream tables = new DataInputStream(new FileInputStream("tables.txt"));
        DataInputStream din2 = new DataInputStream(new FileInputStream("input.txt"));
        DataInputStream chairs = new DataInputStream(new FileInputStream("chair.txt"));
        DataInputStream speakers = new DataInputStream(new FileInputStream("speaker.txt"));
        Scanner r = new Scanner(System.in);

        System.out.println("------------------------------");
        while (din2.available() > 0) {
            System.out.println(din2.readLine());
        }
        System.out.println("------------------------------");
        
        while (tables.available() > 0) {
            table.add(tables.readLine());
        }

        while (chairs.available() > 0) {
            chair.add(chairs.readLine());
        }
        while (speakers.available() > 0) {
            speaker.add(speakers.readLine());
        }
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

    private static void rentProudct(ArrayList table, ArrayList chair, ArrayList speaker) {
        while(true){
        System.out.println("--------Rent product--------");
        Scanner r = new Scanner(System.in);
        System.out.println(" 1.Tables  \n 2.Chairs  \n 3.Speakers \n---------------------------- ");
        System.out.print("select from menu: ");
        int answer = r.nextInt();
        switch (answer) {
            case 1: {
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
                Supplies s = new Supplies(chTable, numTable, dayTable);
                s.Tablereservation();
                break;

            }
            case 2: {
                System.out.println("-------Available chairs-----");
                for (int i = 0; i < chair.size(); i++) {
                    System.out.println(chair.get(i).toString());
                }
                System.out.println("----------------------------");
                System.out.print("choose chair Id: ");

                int chTable = r.nextInt();

                System.out.print("How many chair do you want? ");
                int numTable = r.nextInt();
                System.out.print("How many days do you need it? ");
                int dayTable = r.nextInt();
                Supplies s = new Supplies(chTable, numTable, dayTable);
                s.chairReservation();
                break;

            }
            case 3: {

                System.out.println("-------Available speakers-----");
                for (int i = 0; i < speaker.size(); i++) {
                    System.out.println(speaker.get(i).toString());
                }
                System.out.println("----------------------------");

                System.out.print("choose speaker Id: ");
                int chTable = r.nextInt();

                System.out.print("How many chair do you want? ");
                int numTable = r.nextInt();
                System.out.print("How many days do you need it? ");
                int dayTable = r.nextInt();
                Supplies s = new Supplies(chTable, numTable, dayTable);
                s.speakerRes();
                break;
            }
            }
        }
    }

    private static void updateSupplies(ArrayList<Supplies> table, ArrayList<Supplies> chair, ArrayList<Supplies> speaker) {
        while (true) {
            System.out.println("--------Update Supplies--------");
            Scanner r = new Scanner(System.in);
            System.out.print("Enter collection that you want to update or exit: 1.Tables  2.Chairs  3.Speakers  4.Exit \nEnter number: ");
             int collection= r.nextInt();
             System.out.print("Choose what do you want to update: \n Change info. \n "
                    + " 1. Rename an item \n  2. Change price \n  3. Change quantity \n  4. change days"
                    + "\n------------------------------------------------\n"+
                    "  5. Add new item \n  6. Delete item"
                    +  "\n------------------------------------------------"+"\nEnter number: ");
             int choose= r.nextInt();
             int item=0;
             switch (collection) {
                case 1:
                {
                    if(choose==1||choose==2||choose==4){
                        for(int i=0;i<table.size();i++){
                            System.out.println(i+1+"."+table.get(i).toString());
                        }
                     System.out.print("Enter item number :");
                        item= r.nextInt();
                     item=item-1;
                    }
                    if(choose==1){
                        System.out.print("Enter new name:");
                        String name=r.next();
                        table.get(item).setName(name);
                    }else if(choose==2){
                        System.out.print("Enter new price:");
                        int newPrice=r.nextInt();
                        table.get(item).setPrice(newPrice);
                    }else if(choose==3){
                       System.out.print("Enter new quantity:");
                        int newQuantity=r.nextInt();
                        table.get(item).setNumberOfItems(newQuantity);
                    }else if(choose==4){
                        System.out.print("Enter new days:");
                        int newDays=r.nextInt();
                        table.get(item).setNumberOfDays(newDays);
                    }else if(choose==5){
                        System.out.print("Enter a name for new item:");
                        String name=r.next();
                       System.out.print("Enter a price for new item:");
                        int Price=r.nextInt();
                        table.add(new Supplies(name,Price,0,0));
                    }else if(choose==6){
                        table.remove(item);
                        System.out.println("Item deleted successfully. ");
                    }else {
                        System.out.println("The number you entered not in the range.");
                    }
                    break;
                }
                case 2:
                {
                    if(choose==1||choose==2||choose==4){
                        for(int i=0;i<chair.size();i++){
                            System.out.println(i+1+"."+chair.get(i).toString());
                        }
                        System.out.print("Enter item number:");
                        item= r.nextInt();
                        item=item-1;
                    }
                    if(choose==1){
                        System.out.print("Enter new name:");
                        String name=r.next();
                        chair.get(item).setName(name);
                    }else if(choose==2){
                        System.out.print("Enter new price:");
                        int newPrice=r.nextInt();
                        chair.get(item).setPrice(newPrice);
                    }else if(choose==4){
                        System.out.print("Enter new days:");
                        int newDays=r.nextInt();
                        chair.get(item).setNumberOfDays(newDays);
                    }else if(choose==5){
                        System.out.print("Enter a name for new item:");
                        String name=r.next();
                        System.out.print("Enter a price for new item:");
                        int Price=r.nextInt();
                        chair.add(new Supplies(name,Price,0,0));
                  }else if(choose==6){
                        chair.remove(item);
                        System.out.println("Item deleted successfully. ");
                    }else {
                        System.out.println("The number you entered not in the range.");
                    }
                    break;
                }
                case 3:
                {
                    if(choose==1||choose==2||choose==4){
                        for(int i=0;i<speaker.size();i++){
                            System.out.println(i+1+"."+table.get(i).toString());
                        }
                        System.out.print("Enter item number:");
                        item= r.nextInt();
                        item=item-1;
                    }
                    if(choose==1){
                        System.out.print("Enter new name:");
                        String name=r.next();
                        speaker.get(item).setName(name);
                    }else if(choose==2){
                        System.out.print("Enter new price:");
                        int newPrice=r.nextInt();
                        speaker.get(item).setPrice(newPrice);
                    }else if(choose==4){
                        System.out.print("Enter new days:");
                        int newDays=r.nextInt();
                        speaker.get(item).setNumberOfDays(newDays);
                    }else if(choose==5){
                        System.out.print("Enter a name for new item:");
                        String name=r.next();
                        System.out.print("Enter a price for new item:");
                        int Price=r.nextInt();
                        speaker.add(new Supplies(name,Price,0,0));
                    }else if(choose==6){
                        speaker.remove(item);
                        System.out.println("Item deleted successfully. ");
                    }else {
                        System.out.println("The number you entered not in the range.");
                    }
                    break;
                }
                case 4:
                {
                    serviceRating();
                }
           }
        }
   }
    private static void serviceRating() {
         System.out.println("Thank you for visiting our store.\nPlease rate your experience on a scale from 1(unhappy) to 10(happy): ");
         
         Scanner scanner = new Scanner(System.in);
       int rating= scanner.nextInt();
       
       if(rating == 10){
           System.out.println("thank you for rating.");
       }
       else if(rating <10 || rating > 0){
           scanner.nextLine();
           System.out.println("How can we make your experience even better?");
           String improve = scanner.nextLine();
           System.out.println("thank you for rating.");
       }
        
       System.exit(0);
   }
    }

    


