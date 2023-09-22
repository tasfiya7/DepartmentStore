/**
 * Tasfiya Mubasshira
 * 114870281
 * CSE 214 sec 7
 * @author Tasfiya Mubasshira
 * 
 * 
 * The ItemList class creates a linked list with InfoItem Nodes. 
 * It contains refernces to the head tail and cursor.
 * 
 */

package hw2;

public class ItemList extends ItemInfo {

	private ItemInfoNode head=null; //points to the first node in the linked list
	private ItemInfoNode tail=null; // points to the last node in a linked list
	private ItemInfoNode cursor=null; // used to move throughout the linked list and points to the current node	
	
	/**
	 * This creates the default Constructor
	 * Since the InfoItem Nodes head, tail, and cursor are already instantiated to null in the beginning we don't need to set them in the constructor
	 */
	public ItemList() {
		
	}
	
	/**
	 * Checks if the list is empty. If the head is still equal to null, it would mean the list is empty.
	 * @return boolean (true/false) on whether head is equal to null or not.
	 * O(1) only one thing is being compared
	 */
	public boolean isEmpty(){
		return (head == null);
	}
	
	
	/**
	 * resets the cursor to the head to start from the beginning of the list
	 * O(n) only one item is set/looked at
	 */
	public void resetCursorToHead() {

        cursor = head;
    }
	
	
	/**
	 * This takes in all the necessaray information about an item in the parameters and creates ItemInfo Object with it. 
	 * It then creates ItemNodes for the item. It goes through all the nodes comparing the RFID Tag number to determine where in the list to put the current item.
	 * If the RFID Tag number of the current item is greater that the RFID Tag number passed then it is put before it or else put after. This sorts the list.
	 * 
	 * Time complexity is O(n) as the worst case would mean looping through the list to find the correct position
	 * 
	 * @param name This is the name of an item, as a String, used to instantiate the ItermInfo Object.
	 * @param price This is the price of an item, as a double, used to instantiate the ItemInfo Object.
	 * @param rfidTag This is the RFID Tag number of an item, as a String, used to instantiate the ItemInfo Object.
	 * @param initPosition This is a string referring to the original position of an item used to instantiate the ItemInfo Object.
	 * @throws IllegalLengthException This exception is thrown if the RFID Tag Number is invalid due to it not being the correct length.
	 * @throws IllegalHexadecimalException This exception is thrown if the RFID Tag Number is invalid due to it not being a hexadecimal.
	 */
	public void insertInfo(String name, double price, String rfidTag, String initPosition) throws IllegalLengthException, IllegalHexadecimalException{
	
		ItemInfo item = new ItemInfo(name, price, rfidTag, initPosition, initPosition);
		ItemInfoNode itemNode = new ItemInfoNode(item);
		
		
		//If the list is empty the head,tail, and curser all point to the item's node
		if (isEmpty()==true) {
			head = itemNode;
			tail = itemNode;
			cursor = itemNode;
		}
		
		//appends in front of the head if the RFID Tag number is smaller
		else if(head.getInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) >= 0) {
			
			itemNode.setNext(head);
			head.setPrev(itemNode);
			itemNode.setPrev(null);
			head = itemNode;
			cursor=head;
			
		}
		
		//Put after the tail if the RFID Tag Number is greater
		else if (tail.getInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) <= 0) {
			
			itemNode.setPrev(tail);
            tail.setNext(itemNode);
            itemNode.setNext(null);
            tail = itemNode;
            cursor=tail;
			
		}
		
		// traverses list and comparees RFID Tag Numbers to find the correct position in which the list would be sorted
		// not being put before th head or after the tail
		else {
			boolean countinue=true; // to stop once position in the linked list is found/set
			for(cursor = head; cursor != null && countinue; cursor = cursor.getNext()){
				if(cursor.getInfo().getRfidTagNumber().compareTo(rfidTag) >= 0){
					itemNode.setNext(cursor);
					cursor.getPrev().setNext(itemNode);
					itemNode.setPrev(cursor.getPrev());
					cursor.setPrev(itemNode);
					cursor = itemNode;
					countinue = false;
				}
			}
			
		}
		
		
	}
	
	/**
	 * This method removes all the nodes in the list that have a current location of "out" as that means those items have been purchased
	 * This is done by changing which nodes are pointed to previous and after the current cursor
	 * The items that are removed are printed 
	 * 
	 * O(n) complexity as we have to loop through the entire list
	 */
	public void removeAllPurchased() {
		//formatted table
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Item Name", "RFID", "Original Location", "Current Location", "Price");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------\n");
        
        resetCursorToHead();
        // loops through the list
        while (cursor!=null) {
        	if(cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")){
				System.out.println(cursor.toString());
				
				
				//if the cursor that is to be removed is the only node left then cursor is set to null once removed.
				if(cursor.getPrev() == null && cursor.getNext() == null){
					cursor = null;
				}
				
				// if the head is the cursor node being removed then the head is set to the next node in the list
					else if(cursor==head){
						head = cursor.getNext();
					}
				/// if the tail is the cursor node being removed then the node previos to the tail will now be the tail and the node after the taill will be null
					else if (cursor==tail) {
						tail = tail.getPrev();
	                    tail.setNext(null);
					}
				// if the cursor to be removed is not the head or tail the node previouse to the cursor is now linked to the node after the cursor
					else {
						cursor.getNext().setPrev(cursor.getPrev());
	                    cursor.getPrev().setNext(cursor.getNext());
					}
        	}
        	cursor=cursor.getNext(); // cursor moves to next cursor
        }
	}
	
	/**
	 * This method moves the item from  one location (the source) to another (the destination). It returns a boolean (true/false) based on whether the item is found at the source location.
	 * @param rfidTag This this the RfID tag of the item being removed.
	 * @param source This is the location of the item before ebeing removed
	 * @param dest This is the location 
	 * @return boolean (true/false) based on whether the item is found at the source location.
	 * @throws IllegalSourceException this throws an exception if the location of the node before moving (the source) is set to "out" as that means it is already purchased and can't be moved from there
	 * 
	 * O(n) complexity as the entire list is looped through
	 */
	public boolean moveItem(String rfidTag, String source, String dest) throws IllegalSourceException {
		
		if(isEmpty()){
			return false;
		}
		
		resetCursorToHead();
		
		// if item is already purchased
		if(source.equalsIgnoreCase("out") ) {
			throw new IllegalSourceException();
		}
		
		//loops through list and if the item at the source loaction is found true is returned and the item's location is changed to the destination location
		while (cursor != null) {
            if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTag) && cursor.getInfo().getCurrentLocation().equalsIgnoreCase(source)) {
                cursor.getInfo().setCurrentLocation(dest);
                return true;
            } else {
                cursor = cursor.getNext();
            }
		}
		return false;
			
		
	}
	
	
	
	/**
	 * This method prints all the items in the list in a formatted table
	 * O(n) complexity as the entire list is looped through
	 */
	public void printAll() {
		//formats table
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Item Name", "RFID", "Original Location", "Current Location", "Price");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------\n");

        resetCursorToHead();
        //loops through all the items, then prints it
        for(cursor = head; cursor != null; cursor = cursor.getNext()){
			System.out.println(cursor.toString()); // printed in formatted tabgle based on the toString method in the Item Node class
		}
		System.out.println();
	}
	
	/**
	 * This method prints out all the items in the list that are at a specific location
	 * O(n) complexity because it loops through the entire list
	 * @param Location This is the location being compared to all the items in list to see if it matches up and if it does the item is printed
	 */
	public void printByLocation(String Location) {
		//formats table
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Item Name", "RFID", "Original Location", "Current Location", "Price");
		System.out.printf("-----------------------------------------------------------------------------------------------------------------\n");

        resetCursorToHead();
        //loops through the list and checks if the current location is equal to the loaction in the parameter and if it is, the it is printed in a formatted table using the toString method
        for(cursor = head; cursor != null; cursor = cursor.getNext()){
        if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(Location))
            System.out.println(cursor.toString());
        }
        
	}
	
	/**
	 * This method loops through the list to see if any items that are not already purchased are not at their original location and if it isn't it will be moved back to the original location.
	 * The items moved back are printed in a formatted table
	 * O(n) complexity as the entire list is looped through
	 */
	public void cleanStore() {
		resetCursorToHead();
		
		System.out.println("The following item(s) have been moved back to their original location");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Item Name", "RFID", "Original Location", "Current Location", "Price");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------\n");
        
        //loops through the list 
        for(cursor = head; cursor != null; cursor = cursor.getNext()) {
        	String cL = cursor.getInfo().getCurrentLocation();
			String oL = cursor.getInfo().getOriginalLocation();
			if (oL.compareToIgnoreCase(cL) != 0 && !cL.equalsIgnoreCase("out") && cL.toLowerCase().charAt(0)!=('c')) { // if not purchased and not current loaction is not the same as the original location item is printed and and moved back 
                System.out.println(cursor.toString());
                cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());
            }
			
        }
        
		
	}
	
	/**
	 * This method removes all the item in the specific cart from the list as it is being purchased and returns the total price of the items that werre in the cart
	 * It also displays the item(s) removed in a formatted table
	 * @param cartNumber This refers to the cart that the items are being purchased from
	 * @return cost(double), the addition of the prices of all the item in the cart that are being purchased
	 * O(n) complexity as the entire list is looped through to find the items in the cart
	 */
	public double checkOut(String cartNumber) {
		double cost=0;
		resetCursorToHead();
		
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Item Name", "RFID", "Original Location", "Current Location", "Price");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------\n");

        for(cursor = head; cursor != null; cursor = cursor.getNext()) {
        	String cL = cursor.getInfo().getCurrentLocation();
        	if (cL.equalsIgnoreCase(cartNumber)) {
                cursor.getInfo().setCurrentLocation("out");
                System.out.println(cursor.toString());
                cost += cursor.getInfo().getPrice();
        	}
        }
        
        
        
        return cost;
		
	}
	
	/**
	 * This method prints out all the items in the list that have a specific RFID Tag Number
	 * O(n) complexity because it loops through the entire list
	 * @param r This is the RFIDD Tag number being compared to all the items in the list to see if it matches up and if it does the item is printed
	 */
	public void printByRFID(String r) {
		resetCursorToHead();
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Item Name", "RFID", "Original Location", "Current Location", "Price");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------\n");
        while (cursor != null) {
			if(r.equalsIgnoreCase(cursor.getInfo().getRfidTagNumber())){
				System.out.println(cursor.toString());
			}
            cursor = cursor.getNext();

        }
	}
	
	

	
	
	
	
}

/**
 * This is an exception class that is thrown when the source loaction from which an item would be moved is "out" 
 * @author tasfiya mubasshira
 *
 */
class IllegalSourceException extends Exception{
	
	IllegalSourceException(){
		
	}
	IllegalSourceException(String s){
		super();
		System.out.println(s);
	}
	
}
