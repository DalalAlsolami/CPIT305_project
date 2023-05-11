
package cpit305_project;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartySuppRental {

   
    public static void main(String[] args) throws FileNotFoundException, IOException {
       ArrayList  table = new ArrayList ();
        ArrayList  speaker = new ArrayList();
        ArrayList  chair = new ArrayList();
        DataInputStream  tables= new DataInputStream (new FileInputStream("tables.txt"));
        DataInputStream din2 = new DataInputStream (new FileInputStream("input.txt"));
        DataInputStream chairs = new DataInputStream (new FileInputStream("chair.txt"));
        DataInputStream speakers = new DataInputStream (new FileInputStream("speaker.txt"));
        Scanner r = new Scanner (System.in);
        
       
         
        System.out.println("------------------------------");
        while(din2.available()>0) {
            System.out.println(din2.readLine());   
            }
        System.out.println("------------------------------");
        
        while(tables.available()>0) {
             table.add(tables.readLine());   
            }
        while(chairs.available()>0){
            chair.add(chairs.readLine());
        }
        while(speakers.available()>0){
            speaker.add(speakers.readLine());
        }
        System.out.print("Select from the menu: ");
        int ch = r.nextInt();
          
       while(true){
              if(ch==1){
              rentProudct(table,chair,speaker);   
              break;
              }else if(ch==2){
                updateSupplies(); 
                break;
              }else if(ch==3){
               break;
              }else if(ch==4){
                serviceRating();
                break;
              }else{
               System.out.println("Invalid choice .. Try again ");   
               break;
              }
        }
        
	
}

    private static void rentProudct(ArrayList table, ArrayList chair, ArrayList speaker) {
        System.out.println("--------Rent product--------");
        Scanner r = new Scanner (System.in);
        System.out.println(" 1.Tables  \n 2.Chairs  \n 3.Speakers \n---------------------------- ");
        System.out.print("select from menu: ");
            int answer = r.nextInt();
            switch (answer) {
                case 1:
                {
                System.out.println("-------Available tables-----");
                for(int i=0;i<table.size();i++){
                  System.out.println(i+1+"."+table.get(i).toString());
                }
                    System.out.println("----------------------------"); 
                System.out.print("choose table Id: ");
                int chTable = r.nextInt();
                
                System.out.print("How many table do you want? ");
                int numTable = r.nextInt();
               
                System.out.print("How many days do you need it? ");
                int dayTable = r.nextInt();
                Supplies s= new  Supplies(chTable,numTable,dayTable);
                s.Tablereservation();
                break;
                
                                
            } case 2:
            {
                System.out.println("-------Available chairs-----");
                for(int i=0;i<chair.size();i++){
                System.out.println(chair.get(i).toString());
                }
                System.out.println("----------------------------"); 
                System.out.print("choose chair Id: ");
                 
                 int chTable = r.nextInt();
              
                  System.out.print("How many chair do you want? ");
                  int numTable = r.nextInt();
                  System.out.print("How many days do you need it? ");
                  int dayTable = r.nextInt();
                   Supplies s= new  Supplies(chTable,numTable,dayTable);
                   s.chairReservation();
                   break;
                   
            } case 3: {
                
                System.out.println("-------Available speakers-----");
                for(int i=0;i<speaker.size();i++){
                System.out.println(speaker.get(i).toString());
            }
                System.out.println("----------------------------"); 
                
                 System.out.print("choose speaker Id: ");
                 int chTable = r.nextInt();
               
            
                  System.out.print("How many chair do you want? ");
                  int numTable = r.nextInt();
                  System.out.print("How many days do you need it? ");
                  int dayTable = r.nextInt();
                  Supplies s= new  Supplies(chTable,numTable,dayTable);
                  s.speakerRes();
                  break;
                     
            }
        }
    }
    private static void updateSupplies() {
        
    }

    private static void serviceRating() {
        
    }
    
}
    

