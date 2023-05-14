
package cpit305_project;

class Supplies {
    
   int itemID;
   int numberOfItems;
   int numberOfDays;
    String name;
    int price;
    boolean inStock; 
    String Arraycontent;
    int quantity;


    
    
    
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

  
    
    void Tablereservation(){
         double Totalp=0;
                if( itemID == 1){
                   Totalp =numberOfItems* 200 *numberOfDays ; 
                }else if(itemID == 2){
                  Totalp = numberOfItems* 150 *numberOfDays;
                }else{
                    System.out.println("Invalid choice .. Try again! ");
                }
                 System.out.println("Total price for "+ numberOfItems +" tables "+ Totalp +"$");
                 System.out.println("Thank you  :) ");
    }



    void chairReservation() {
         double Totalp = numberOfItems* 40 *numberOfDays ; 
                  System.out.println("Total price for "+ numberOfItems +" chairs "+ Totalp +"$");
                  System.out.println("Thank you  :) ");
    }

    void speakerRes() {
         double Totalp=0;
                if( itemID == 1){
                   Totalp = numberOfItems* 150 *numberOfDays ;    
                }else if(itemID == 2){
                   Totalp = numberOfItems* 80 *numberOfDays ;   
                }else{
                    System.out.println("Invalid choice .. Try again! ");
                }
                 System.out.println("Total price for "+ numberOfItems +" tables "+ Totalp +"$");
                 System.out.println("Thank you  :) ");
   
     
    }
   @Override
     public String toString(){
        return "item Name: "+name + " price: "+ price + " Available Quantity of the item: " + quantity;
    }

}
