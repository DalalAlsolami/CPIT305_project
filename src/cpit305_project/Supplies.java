
package cpit305_project;

class Supplies {    
    int itemID;
    int numberOfItems;
    int numberOfDays;
    String name;
    int price;
    boolean inStock; 
    int quantity;

    
    Supplies( String shomokh_, int numTable, int chTable, int dayTable) {
     this.numberOfItems=numTable;
     this.name = shomokh_;
    
     this.itemID = chTable;
     this.numberOfDays= dayTable;
    }
    
    Supplies( int chTable, int numTable, int dayTable) { 
       this.itemID= chTable;
       this.numberOfDays= dayTable;
       this.numberOfItems= numTable;
      
    }

    Supplies(String name, int P, int quantity) {
        this.name = name;
        this.price = P;
        this.quantity = quantity;
        
    }

    Supplies() {
      
    }

    Supplies(String shrooge_, int i) {
       
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int num) {
        this.quantity = quantity - num;
    }
    

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

  
//    
//    void Tablereservation(){
//         double Totalp=0;
//                if( itemID == 1){
//                   Totalp =numberOfItems* price *numberOfDays ; 
//                }else if(itemID == 2){
//                  Totalp = numberOfItems* price *numberOfDays;
//                }else{
//                    System.out.println("Invalid choice .. Try again! ");
//                }
//                 System.out.println("Total price for "+ numberOfItems +" tables "+ Totalp +"$");
//                 System.out.println("Thank you  :) ");
//    }



//    void chairReservation() {
//         double Totalp = numberOfItems* 40 *numberOfDays ; 
//                  System.out.println("Total price for "+ numberOfItems +" chairs "+ Totalp +"$");
//                  System.out.println("Thank you  :) ");
//    }
//
//    void speakerRes() {
//         double Totalp=0;
//                if( itemID == 1){
//                   Totalp = numberOfItems* price *numberOfDays ;    
//                }else if(itemID == 2){
//                   Totalp = numberOfItems* price*numberOfDays ;   
//                }else{
//                    System.out.println("Invalid choice .. Try again! ");
//                }
//                 System.out.println("Total price for "+ numberOfItems +" tables "+ Totalp +"$");
//                 System.out.println("Thank you  :) ");
//   
//     
//    }
   @Override
     public String toString(){
        return "item Name: "+name + " price: "+ price + " Available Quantity of the item: " + quantity;
    }
     
     

 
   public synchronized void bookItem(String name ,int numberOfItem, int numOfdays, int itemID) {  
       double Totalp=0;
       if( quantity >=  numberOfItem  ) {
           
                   Totalp =numberOfItem*price *numOfdays ; 
                   System.out.println(Thread.currentThread().getName()+" Customer "+name +": "+ numberOfItem+" items booked successfully with " + Totalp + "$");
                    quantity = quantity - numberOfItem;      
           
        }else{
            System.out.println(Thread.currentThread().getName()+" Customer "+name+"Sorry the qountity of your order not avaliable ");       
        }
    }

}