/**
 * Tasfiya Mubasshira
 * 114870281
 * CSE 214 sec 7
 * @author Tasfiya Mubasshira
 * 
 * This class creates an Iteminfo object containing all the information related to an item
 */
package hw2;

public class ItemInfo {

	private String name = null;
	private double price = 0;
	private String rfidTagNumber= null;
	private final int RFID_TAG_NUMBER_LENGTH =9;
	private String originalLocation = null;
	private final int ORIGINAL_LOCATION_LENGTH=6;
	private String currentLocation = null;
	private final int CART_LOCATION_LENGTH=4;
	
	/**
	 * Default Constructor
	 */
	public ItemInfo(){
		
	}
	
	/**
	 * Constructor to create the ItemInfo Object using the parameters
	 * @param n This is the name of the item as a String
	 * @param p This is the price of item as a double
	 * @param rfidTag This is the RFID Tag Number of the item as a String
	 * @param oL This is the original Location of the item as a String
	 * @param cL This is the current Location of the item as a String
	 * @throws IllegalLengthException This exception is thrown if the RFID Tag Number's length is not 9
	 * @throws IllegalHexadecimalException This excpetion is thrown if the RFID Tag Number is not a hexadecimal
	 */
	public ItemInfo(String n, double p, String rfidTag, String oL, String cL) throws IllegalLengthException, IllegalHexadecimalException {
		setName(n);
		setPrice(p);
		setRfidTagNumber(rfidTag);
		setOriginalLocation(oL);
		setCurrentLocation(cL);
	}
	
	/**
	 * 
	 * @return name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the name of the item to the String in the paramter 
	 * @param n This is the name being set to the item
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * 
	 * @return price of the item
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * sets the price of the item to the double in the paramter 
	 * @param p This is the price being set to the item
	 */
	public void setPrice(double p) {
		price = p;
	}
	
	
	/**
	 * 
	 * @return RFID Tag Number of the item
	 */
	public String getRfidTagNumber() {
		
		return rfidTagNumber;
	}
	
	
	/**
	 * sets the RFID tag number of item as the parameter and throws exceptions when it is invalid
	 * @param r This is the RFID Tag number being set to the item
	 * @throws IllegalLengthException This exception is thrown if the RFID Tag Number's length is not 9
	 * @throws IllegalHexadecimalException This excpetion is thrown if the RFID Tag Number is not a hexadecimal
	 */
	public void setRfidTagNumber(String r) throws IllegalLengthException, IllegalHexadecimalException {
		
		if(r.length() != RFID_TAG_NUMBER_LENGTH){
			throw new IllegalLengthException();
		}
		else if(!r.matches("^[0-9a-fA-F]+$") || r.contains(" ")){
			throw new IllegalHexadecimalException();
			
		}
		
		rfidTagNumber = r;
	}
	
	/**
	 * 
	 * @return original Location of the item
	 */
	public String getOriginalLocation() {
		return originalLocation;
	}
	
	/**
	 * sets the original location of the item according to the parameter
	 * @param o This is the original location the item is being set to
	 * @throws IllegalArgumentException This exception is thrown if the original location is not in the format of "s" followed by 5 digits
	 */
	public void setOriginalLocation(String o) throws IllegalArgumentException {
		
		if (o.length() != ORIGINAL_LOCATION_LENGTH || !o.toLowerCase().startsWith("s") || !o.substring(1,6).matches("^[0-9]+$"))
            throw new IllegalArgumentException("Original Shelf Location must start with s with 5 digits following");
		
		originalLocation = o;
	}
	
	/**
	 * 
	 * @return the current location of the item
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}
	
	/**
	 * sets the currect location to the location in the parameter
	 * @param c the location being set as the current location
	 * @throws IllegalArgumentException If the location being set is not in the proper format of a shelf location, or cart location or set to out, then an excption is thrown
	 */
	public void setCurrentLocation(String c) throws IllegalArgumentException{
		//checks if the paramter is a proper shelf location
		boolean shelfLocation=false;
		if (c.length() == ORIGINAL_LOCATION_LENGTH && c.toLowerCase().startsWith("s") && c.substring(1,6).matches("^[0-9]+$")){
			shelfLocation=true;
		}
		//checks if the paramter is a proper cart location
		boolean cartLocation=false;
		if (c.length() == CART_LOCATION_LENGTH && c.toLowerCase().startsWith("c") && c.substring(1,4).matches("^[0-9]+$")){
			cartLocation=true;
		}
		
		if (!shelfLocation && !cartLocation && !c.toLowerCase().equals("out")) {
			throw new IllegalArgumentException("Current Location is not on the shelf, in a cart, or sold");
		}
		
		currentLocation = c;
	}
	
	
	
	
	
	
	
	
	
}

/**
 * This class creates an exception to be thrown if the RFID TAG Number is not the proper length (9)
 * @author tasfiya mubasshira
 *
 */
class IllegalLengthException extends Exception{
	IllegalLengthException(){
		
	}
	IllegalLengthException(String s){
		super(s);
		System.out.println(s);
	}
}

/**
 * This class creates an exception to be thrown if the RFID TAG Number is not a hexadecimal
 * @author tasfiya mubasshira
 *
 */
class IllegalHexadecimalException extends Exception{
	IllegalHexadecimalException(){
		
	}
	
	IllegalHexadecimalException(String s){
		super(s);
		System.out.println(s);
	}
}
