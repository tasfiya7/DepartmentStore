/**
 * Tasfiya Mubasshira
 * 114870281
 * CSE 214 sec 7
 * @author Tasfiya Mubasshira
 * 
 * This class contains the main method to test out all the options available to the user
 */

package hw2;

import java.util.Scanner;


public class DepartmentStore{
	
	
	/**
	 * The main method runs a menu that asks the user for input to execute an opperation. 
	 * @param args
	 * @throws IllegalSourceException this exception is thrown while running the menu if the source loaction is invalid when chnaging the location of an item in menu option M
	 */
	public static void main(String args[]) throws IllegalSourceException {

		final int RFID_TAG_NUMBER_LENGTH =9;
		
		final int ORIGINAL_LOCATION_LENGTH=6;
	
		final int CART_LOCATION_LENGTH=4;
		ItemList list=new ItemList();
		String input=null;
		
		
	/**
	 * prints the menu until programm is quitted by choose option Q
	 */
	do {
			
		mainMenu();
			System.out.println("Select an option: ");
			Scanner sc= new Scanner (System.in);
			input=sc.nextLine().toUpperCase(); // disregard case in user's inut
		
			
		switch(input) { // operates based on user's choice
		
		case "C": // items that are not in their original location are moved to it
			
			list.cleanStore();
			
            break;
        case "I":  //adds item to list and throws exception based on invalid inputs 
        	try {
        	System.out.println("Enter the name: ");
            String name= sc.nextLine();
            System.out.println("Enter the RFID: ");
            String rfid = sc.nextLine();
            if(rfid.length() != RFID_TAG_NUMBER_LENGTH){
    			throw new IllegalLengthException();
    		}
    		else if(!rfid.matches("^[0-9a-fA-F]+$") || rfid.contains(" ")){
    			throw new IllegalHexadecimalException();
    		}
            System.out.println("Enter the original location: ");
            String location = sc.nextLine();
            if (location.length() != ORIGINAL_LOCATION_LENGTH || !location.toLowerCase().startsWith("s") || !location.substring(1,6).matches("^[0-9]+$")) {
                throw new IllegalArgumentException();
        	}
            System.out.println("Enter the price: ");
            double price = sc.nextDouble();
            list.insertInfo(name, price, rfid, location);
        	}
        	
        	catch(IllegalLengthException ie) {
        		System.out.println("length of RFID Tag Number must be 9 ");
        	}
        	catch(IllegalHexadecimalException ie) {
        		System.out.println("RFID Tag Number must be a decimal");
        	}
        	catch (IllegalArgumentException ie) {
        		System.out.println("Original Shelf Location must start with s with 5 digits following");
        	}
        	
        	break;
        case "L": //prints all the items at a certain location
        	System.out.println("Enter the location: ");
            list.printByLocation(sc.nextLine());
        	
            break;
        case "M":  // moves item to new location, throws arguments where inputs are invalid
        	try {
        	 System.out.println("Enter the RFID: ");
             String rfid = sc.nextLine();
             if(rfid.length() != RFID_TAG_NUMBER_LENGTH){
     			throw new IllegalLengthException();
     		}
     		else if(!rfid.matches("^[0-9a-fA-F]+$") || rfid.contains(" ")){
     			throw new IllegalHexadecimalException();
     		}
             System.out.println("Enter the source location: ");
             String source = sc.nextLine();
             if(source.equalsIgnoreCase("out") ) {
     			throw new IllegalSourceException();
     		}

             System.out.println("Enter the destination location: ");
             String dest = sc.nextLine();
             boolean shelfLocation=false;
     		if (dest.length() == ORIGINAL_LOCATION_LENGTH && dest.toLowerCase().startsWith("s") && dest.substring(1,6).matches("^[0-9]+$")){
     			shelfLocation=true;
     		}
     		
     		boolean cartLocation=false;
     		if (dest.length() == CART_LOCATION_LENGTH && dest.toLowerCase().startsWith("c") && dest.substring(1,4).matches("^[0-9]+$")){
     			cartLocation=true;
     		}
     		
     		if (!shelfLocation && !cartLocation && !dest.toLowerCase().equals("out")) {
     			throw new IllegalArgumentException();
     		}
             
     		if(list.moveItem(rfid,source,dest))
                System.out.println("Item was moved."); 
     		
        	}
        	catch(IllegalLengthException ie) {
        		System.out.println("length of RFID Tag Number must be 9 ");
        	}
        	catch(IllegalHexadecimalException ie) {
        		System.out.println("RFID Tag Number must be a decimal");
        	}
        	catch (IllegalArgumentException ie) {
        		System.out.println("Destination Location is not on the shelf, in a cart, or out");
        	}

            break;
        case "O": // checks out items in a cart and returns cost, throwns exception if car number is invalid
        	try {
        		System.out.println("Enter the cart number: ");
                String cartNum = sc.nextLine();
                if (cartNum.length() != CART_LOCATION_LENGTH || !cartNum.toLowerCase().startsWith("c") || !cartNum.substring(1,4).matches("^[0-9]+$"))
                	throw new IllegalArgumentException();
                System.out.printf("The total cost of the items in the cart cart is $%.2f\n", list.checkOut(cartNum));
                System.out.println();
        	}
        	catch (IllegalArgumentException ie) {
        		System.out.println("Invalid cart number");
        	}
        	
            break;
        case "P": // prints all item in the list
        	list.printAll();
        	
            break;
        case "R":  // prints items that all have a specific RFID Tag Number, throws exception if RFID Tag Number is invalid
        	try {
        		System.out.println("Enter the RFID: ");
                String rfid = sc.nextLine();
                if(rfid.length() != RFID_TAG_NUMBER_LENGTH){
        			throw new IllegalLengthException();
        		}
        		else if(!rfid.matches("^[0-9a-fA-F]+$") || rfid.contains(" ")){
        			throw new IllegalHexadecimalException();
        		}
                list.printByRFID(sc.nextLine());
        	}
        	
        	catch(IllegalLengthException ie) {
        		System.out.println("length of RFID Tag Number must be 9 ");
        	}
        	catch(IllegalHexadecimalException ie) {
        		System.out.println("RFID Tag Number must be a decimal");
        	}
        	
            break;
        case "U": // removes items from inventory that were already purchased and prints those that are removed
        	System.out.println("the following items have been removed from inventory");
        	list.removeAllPurchased();
            break;
        case "Q": // stops program
            System.out.println("Program terminated.");
            System.exit(0);
            break;
        default: // incorrect input
            System.out.println("Not an Option. Try Again");
		
		} 
	
			
			
			
	} while(input !="Q"); // While "Q" is not inputed the menu will repeat
		
		
	}
		
		
	
	
	
	
	public static void mainMenu() {

        System.out.println("C) Clean Store");
        System.out.println("I) Insert an item into the list");
        System.out.println("L) Move an item in the store");
        System.out.println("O) Checkout");
        System.out.println("R) Print by RFID tag number");
        System.out.println("P) Print all items in store");
        System.out.println("U) Update inventory system");
        System.out.println("Q) Quit");
        System.out.println();
    }
	
	
}
