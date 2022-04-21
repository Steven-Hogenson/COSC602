
package structure;

/**
 * The binary tree node can be used for general binary tree or BST.
 * @author X. David Zheng
 * @version 2.0
 */
public class MyBinaryTreeNode implements Comparable<MyBinaryTreeNode>
{
	public Object data;
	public MyBinaryTreeNode left;
	public MyBinaryTreeNode right;
	
	/**
	 * The constructor.
	 * @param d		The data part.
	 */
	public MyBinaryTreeNode(Object d)
	{
		data = d;
		left = null;
		right = null;
	}
	
	/**
	 * The overloaded constructor.
	 * @param d		The data part.
	 * @param l		Reference to the left child.
	 * @param r		Reference to the right child.
	 */
	public MyBinaryTreeNode(Object d, MyBinaryTreeNode l, MyBinaryTreeNode r)
	{
		data = d;
		left = l;
		right = r;
	}
	
	/**
	 * Get the string representation of the current node.
	 * @return	The string representation
	 */
	public String toString()
	{
		return data.toString();
	}
	
	/**
	 * Implement the compareTo method of the Comparable interface.
	 * @param	target The target MyBinaryTreeNode to be compared to.
	 * @return	-1 if this < target, 1 if this > target, 0 otherwise.
	 */
	public int compareTo(MyBinaryTreeNode target)
	{
		return ((Comparable)this.data).compareTo(target.data);
	}
}
