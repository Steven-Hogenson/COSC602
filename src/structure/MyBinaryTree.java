
package structure;

/**
 * General binary tree implemented as an abstract class.
 * @author X. David Zheng
 * @version 2.0
 */
public abstract class MyBinaryTree
{
	public MyBinaryTreeNode root;

	/**
	 * Remove all the tree nodes.
	 */
	public void clear()
	{
		root = null;
	}

	/**
	 * Check whether the tree is empty.
	 * @return	true if the tree contains no nodes, false otherwise.
	 */
	public boolean isEmpty()
	{
		return root == null;
	}

	/**
	 * Get the number of nodes in the tree.
	 * @return	The number of nodes in the tree.
	 */
	public int size()
	{
		return sizeHelper(root);
	}

	/*
	 * The private helper method for size().
	 * Get the number of nodes in the tree rooted at r.
	 * @param	rt	The current root of the tree.
	 * @return		The number of nodes in the tree.
	 */
	private int sizeHelper(MyBinaryTreeNode rt)
	{
		if (rt == null)
			return 0;

		return sizeHelper(rt.left) + sizeHelper(rt.right) + 1;
	}

	/**
	 * Get the height of the tree.
	 * @return		The height of the tree.
	 */
	public int height()
	{
		return heightHelper(root, -1);
	}

	/*
	 * The private helper method for height().
	 * Get the height of the subtree rooted at rt.
	 * @param rt	The current root.
	 * @param ht	The current height.
	 * @return		The height of the tree.
	 */
	private int heightHelper(MyBinaryTreeNode rt, int ht)
	{
		if (rt == null)
			return ht;

		return Math.max(heightHelper(rt.left, ht+1),
						heightHelper(rt.right, ht+1));
	}
	
	/**
	 * Preorder traverse the tree and print out each node.
	 */
	public String preorderTraversal()
	{//return type of a String is for subclass to override this method and return a String.
		preorderHelper(root);
		System.out.println();
		return "";
	}

	/*
	 * The private helper method for preorderTraversal().
	 */
	private void preorderHelper(MyBinaryTreeNode rt)
	{
		if(rt == null)	return;

		System.out.print("\t" + rt.data);
		preorderHelper(rt.left);
		preorderHelper(rt.right);
	}

	/**
	 * Inorder traverse the tree and print out each node.
	 */
	public String inorderTraversal()
	{//return type of a String is for subclass to override this method and return a String.
		inorderHelper(root);
		System.out.println();
		return "";
	}

	/*
	 * The private helper method for inorderTraversal().
	 */
	private void inorderHelper(MyBinaryTreeNode rt)
	{
		if(rt == null)	return;

		inorderHelper(rt.left);
		System.out.print("\t" + rt.data);
		inorderHelper(rt.right);
	}

	/**
	 * Postorder traverse the tree and print out each node.
	 */
	public String postorderTraversal()
	{//return type of a String is for subclass to override this method and return a String.
		postorderHelper(root);
		System.out.println();
		return "";
	}

	/*
	 * The private helper method for postorderTraversal().
	 */
	private void postorderHelper(MyBinaryTreeNode rt)
	{
		if(rt == null)	return;

		postorderHelper(rt.left);
		postorderHelper(rt.right);
		System.out.print("\t" + rt.data);
	}
}
