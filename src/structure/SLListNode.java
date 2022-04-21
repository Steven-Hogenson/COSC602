
package structure;

/**
 * @author X. David Zheng
 * @version 2.0
 */
public class SLListNode 
{
	public Object data;
	public SLListNode next;
	
	/**
	 * The constructor.
	 * @param	d	The real data to be saved into the SLListNode.
	 * @param	n	The reference to the next node in the SLList.
	 */
	public SLListNode(Object d, SLListNode n)
	{
		data = d;
		next = n;
	}
}
