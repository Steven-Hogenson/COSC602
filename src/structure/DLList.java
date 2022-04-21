
package structure;

/**
 * Implements a double-linked list ADT.  
 * Uses the class DLListNode for single nodes.
 * 
 * @author X. David Zheng
 * @version 2.0
 */
public class DLList {
	public DLListNode head;
	public DLListNode tail;
	
	/**
	 * The default constructor
	 */
	public DLList() {
		head = tail = null;
	}
	
	/**
	 * This method appends an Object at the end of the list
	 * @param element the Object to be appended
	 */
	public void append(Object element) {
		if(head == null)
		{
			head = tail = new DLListNode(element, null, null);
		}
		else 
		{
			tail = new DLListNode(element, tail, null);
			tail.prev.next = tail;
		}
	}
	
	/**
	 * This method inserts an Object at the beginning of the list
	 * @param element the Object to be inserted
	 */
	public void insert(Object element) {
		if(head == null)
		{
			head = tail = new DLListNode(element, null, null);
		}
		else 
		{
			head = new DLListNode(element, null, head);
			head.next.prev = head;
		}
	}
	
	/**
	 * This method clears the list.
	 */
	public void clear() 
	{
		head = tail = null;
	}
	
	/**
	 * This method removes a node from the list, 
	 * which contains the same value as the element.
	 * @param element 	the value to be compared to
	 * @return false if not found, true if removed successfully
	 */
	public void remove(Object element) 
	{
		if(head == null) return;
		
		if(((Comparable)head.data).compareTo((Comparable)element) == 0) 
		{//The head node is the node to be removed. 
			if(head == tail) //List contains one node.
			{
				head = tail = null;
			}
			else
			{
				head = head.next;
				head.prev = null;
			}
			return;
		}
		
		if (head == tail)
		{//The DLList contains only one node and it is not the node to be removed.
			return;
		}
		
		DLListNode ref = head.next;
		while(ref != tail)
		{
			if(((Comparable)ref.data).compareTo((Comparable)element) == 0) 
			{
				ref.prev.next = ref.next;
				ref.next.prev = ref.prev;
				return;
			}
			ref = ref.next;
		} 
		
		if(((Comparable)tail.data).compareTo((Comparable)element) == 0) 
		{//The tail node is the node to be removed. 
			tail = tail.prev;
			tail.next = null;
		}
	}
	
	/**
	 * This method checks whether the list is empty
	 * @return boolean true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * This method returns the entire list in a String form
	 * @return the String form of the list
	 */
	public String toString() {
		String out = "The DLList contains: \n";
		DLListNode ref = head;
		if(head == null)
			return out + "0 nodes.";
		else
			out += "head -->\t";
		
		while(ref != tail) 
		{
			out += ref.data + "\t<-->\t";
			ref = ref.next;
		}
		out += ref.data + "\t<-- tail";
		return out;
	}
}
