/**
 * Tasfiya Mubasshira
 * 114870281
 * CSE 214 sec 7
 * @author Tasfiya Mubasshira
 * 
 * 
 * The ItemInfoNode class creates the nodes for the ItemList linking two adjescent lists as in doubly linked list
 */

package hw2;

public class ItemInfoNode {
	
	private ItemInfo info;
	private ItemInfoNode prev=null;
	private ItemInfoNode next=null;
	
	/**
	 * Default Constructor
	 * 
	 */
	public ItemInfoNode() {
		
	}
	
	/**
	 * This Constructor creates ItemInfoNodes and instatiates an ItemInfo object
	 * @param i This is the ItemInfo Object that is being instatiated
	 * The prev and next ItemInfoNodes are already instantiated to null
	 */
	public ItemInfoNode(ItemInfo i) {
		setInfo(i);
	}
	
	/**
	 * 
	 * @return previous Node
	 */
	public ItemInfoNode getPrev() {
		return prev;
	}

	/**
	 * The previous node is set to the node in the parameter
	 * @param p This is the node being set as the previous node
	 */
	public void setPrev(ItemInfoNode p) {
		prev = p;
	}

	/**
	 * 
	 * @return next Node
	 */
	public ItemInfoNode getNext() {
		return next;
	}

	/**
	 * The next node is set to the node in the parameter
	 * @param n This is the node being set as the next node
	 */
	public void setNext(ItemInfoNode n) {
		next = n;
	}

	/**
	 * 
	 * @return info (the item the node is being created for) 
	 */
	public ItemInfo getInfo() {
		return info;
	}

	/**
	 * The ItemInfo object is being instantiated
	 * @param item The item that the object is being instantiated to
	 */
	public void setInfo(ItemInfo item) {
		this.info = item;
	}
	
	
	@Override
	/**
	 * @return a String of the item at a node's information formatted
	 */
	public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%.2f\n",info.getName(),info.getRfidTagNumber(),info.getOriginalLocation(),info.getCurrentLocation(),info.getPrice());
    }
	
	

}
