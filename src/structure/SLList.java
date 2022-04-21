
package structure;

/**
 * @author X. David Zheng
 * @version 2.0
 */
public class SLList {
	private SLListNode head;
	private SLListNode tail;
	private int size;

	/**
	 * The default constructor.
	 */
	public SLList() {
		head = tail = null;
		size = 0;
	}

	/**
	 * The size method.
	 * 
	 * @return the number of elements in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Check to see if the list is empty.
	 * 
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Adds the given element to the end of the List.
	 * 
	 * @param element The appended element.
	 */
	public void append(Object element) {
		if (isEmpty()) {
			head = tail = new SLListNode(element, null);
			size++;
			return;
		}

		tail = tail.next = new SLListNode(element, null);
		size++;
	}

	/**
	 * Adds the given element to the front of the List.
	 * 
	 * @param element The element to be added
	 */
	public void insert(Object element) {
		if (isEmpty()) {
			head = tail = new SLListNode(element, null);
			size++;
			return;
		}

		head = new SLListNode(element, head);
		size++;
	}

	/**
	 * Clears the list of all elements.
	 */
	public void clear() {
		head = tail = null;
		size = 0;
	}

	/**
	 * Finds and removes the given element. 
	 * If there are duplicates only the first one will be removed.
	 * 
	 * @param element The element to be removed.
	 * @return true if the element found and removed, false otherwise.
	 */
	public boolean remove(Object element) {
		if (isEmpty()) {
			return false;
		}

		if (((Comparable) (head.data)).compareTo((Comparable) element) == 0) 
		{// The head node equals to the element.
			if (head == tail) {// If head node is the only node on the list.
				head = tail = null;
				size = 0;
				return true;
			}
			head = head.next;
			size--;
			return true;
		}

		if (head == tail) 
		{// The list has only one node, then we have finished checking in last step.
			return false;
		}

		// The list at least has two nodes.
		SLListNode ref = head;
		while (ref.next != tail) 
		{// Checking the middle nodes, the last node is not checked.
			if (((Comparable) (ref.next.data)).compareTo((Comparable) element) == 0) {
				ref.next = ref.next.next;
				size--;
				return true;
			}
			ref = ref.next;
		}

		if (((Comparable) (tail.data)).compareTo((Comparable) element) == 0) 
		{// The last node equals to the element.
			tail = ref; // ref currently pointing to the node before the tail node.
			ref.next = null;
			size--;
			return true;
		}
		return false;
	}

	/**
	 * Formats the list nodes into a string
	 * 
	 * @return The result string.
	 */
	public String toString() {
		String out = "The SLList contains:\n";
		SLListNode ref = head;
		if (isEmpty())
			return out + "0 nodes.";
		else
			out += "head ->\t";

		while (ref.next != null) {
			out += ref.data + "\t->\t";
			ref = ref.next;
		}

		out += ref.data + "\t->null"; // Add the last node.
		return out;
	}

	/**
	 * This print method is created for the purpose of phone number to word project.
	 * 
	 */
	public void print() {

		if (isEmpty()) {
			System.out.println("No words found.");
			return;
		}

		SLListNode ref = head;
		while (ref != null) {
			System.out.println(ref.data);
			ref = ref.next;
		}
		System.out.println();
		System.out.println(size + " words found.");
	}
}
