package cpit305_project;


public class bookingThread extends Thread{
    private int numberOfTables;
    private String username;
    private Supplies s;
    private int itemID;
    private int numOfdays;
    
   

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
     public void run(){
       s.bookItem(username,numberOfTables, numOfdays,itemID) ;
     }     
}
