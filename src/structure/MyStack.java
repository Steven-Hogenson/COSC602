
package structure;

/**
 * A singly linked list based stack structure.
 * @author X. David Zheng
 * @version 2.0
 */
public class MyStack {
	public SLListNode top;
	
	/**
	 * Default constructor
	 */
	public MyStack()
	{
		top = null;
	}
	
	/**
	 * Clears the stack.
	 */
	public void clear()
	{
		top = null;
	}
	
	/**
	 * Check whether the stack is empty.
	 * @return	true if empty false otherwise.
	 */
	public boolean isEmpty()
	{
		return top == null;
	}
	
	/**
	 * Removes the top object of the stack and return the removed object.
	 * @return the object which is popped
	 */
	public Object pop()
	{
		if(isEmpty())
		{
			return null;
		}
		
		Object temp = top.data;
		top = top.next;
		return temp;
	}
	
	/**
	 * Adds a node to the top of the stack.
	 * @param element to be added as the data of the new node.
	 */
	public void push(Object element)
	{
		top= new SLListNode(element, top);
	}
	
	/**
	 * Returns the data contained in the top node of the stack.
	 * @return the top data object.
	 */
	public Object top()
	{
		if(isEmpty())
		{
			return null;
		}
		
		return top.data;
	}
	
	/**
     * Converts the current stack into a string from top to bottom. 
     * @return the result string    
     */
	public String toString()
	{
		String out = "The Stack contains:\n";
		SLListNode ref = top;
		if(isEmpty())
			return "0 nodes.";
		else
			out += "top ->\t";
		
		while(ref.next != null)
		{
			out += ref.data + "\t->\t";
			ref = ref.next;
		}
		
		out += ref.data +"\t->null"; //Add the last node.
		return out;
	}
}
