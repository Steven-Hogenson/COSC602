
package structure;

/**
 * Implements a doubly ended queue ADT.  
 * Uses the class DLListNode for single nodes.
 * Inherits the DLList class.
 * 
 * @author X. David Zheng
 * @version 2.0
 */
public class MyDeque extends DLList 
{

	/**
	 * Default constructor.
	 */
	public MyDeque()
	{
		super();
	}
	
	/**
	 * This method returns the Object at the front of the deque.
	 * @return the Object at the front of the deque
	 */
	public Object front() {
		if(head == null)
			return null;
		
		return head.data;
	}
	
	/**
	 * This method returns the Object at the back of the deque.
	 * @return the Object at the back of the deque
	 */
	public Object back() {
		if(head == null)
			return null;
		
		return tail.data;
	}
	
	/**
	 * This method inserts an Object at the back of the deque.
	 * @param element the Object to be inserted
	 */
	public void insertBack(Object element) {
		append(element);
	}
	
	/**
	 * This method removes the Object at the back of the deque and returns it.
	 * @return the Object that was removed from the back of the deque
	 */
	public Object removeBack() {
		if(head == null)
			return null;
		
		Object temp = back();
		if(head == tail) //single node case
		{
			head = tail = null;
			return temp;
		}
		
		tail = tail.prev;
		tail.next = null;
		return temp;
	}
	
	/**
	 * This method inserts the Object at the head of the deque.
	 * @param element Object to be inserted at the head of the deque
	 */
	public void insertFront(Object element) {
		insert(element);	
	}
	
	/**
	 * This method removes the Object at the head of the deque and returns it.
	 * @return the Object that was removed from the head of the deque
	 */
	public Object removeFront() {
		if(head == null)
			return null;
		
		Object temp = front();
		if(head == tail) //single node case
		{
			head = tail = null;
			return temp;
		}
		
		head = head.next;
		head.prev = null;
		return temp;
	}
	
	/**
	 * This method checks whether the deque is empty.
	 * @return boolean true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * This method returns the deque in a String form.
	 * @return the String form of the deque
	 */
	public String toString() {
		String out = "The deque contains: \n";
		DLListNode ref = head;
		if(head == null)
			return out + "0 nodes.";
		else
			out += "front -->\t";
		
		while(ref != tail) 
		{
			out += ref.data + "\t<-->\t";
			ref = ref.next;
		}
		
		out += ref.data + "\t<-- back";
		return out;
	}
}
