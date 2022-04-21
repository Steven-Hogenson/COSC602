
package structure;

/**
 * The binary search tree.
 * @author X. David Zheng
 * @version 2.0
 */
public class MyBinarySearchTree extends MyBinaryTree
{

	/**
	 * Default Constructor
	 */
	public MyBinarySearchTree()
	{
		root = null;
	}
	
	/**
	 * Insert the newItem as the data value into the tree.
	 * @param newItem	The data value to be inserted.
	 */
	public void insert(Object newItem)
	{
		root = insertHelper(root, new MyBinaryTreeNode(newItem));
	}
	
	/*
	 * The helper method for the insert().
	 * @param	rt	The root of current subtree.
	 * @param	newNode	The new node contain the newItem to be inserted.
	 * @return	The root of the subtree.
	 */
	private MyBinaryTreeNode insertHelper(MyBinaryTreeNode rt, 
											MyBinaryTreeNode newNode)
	{
		if (rt == null)
		{
			rt = newNode;
			return rt;
		}
		
		if (newNode.compareTo(rt) < 0)
		{
			rt.left = insertHelper(rt.left, newNode);
		}
		else
		{
			rt.right = insertHelper(rt.right, newNode);
		}
		return rt;
	}
	
	/**
	 * Find the maximum data value in the tree nodes.
	 * @return	The maximum data value.
	 */
	public Object max()
	{
		if (root == null)
			return null;
		
		return maxHelper(root).data;
	}
	
	/*
	 * The helper method for max().
	 * @param	The root of the current subtree.
	 * @return	The tree node contains the maximum data value.
	 */
	private MyBinaryTreeNode maxHelper(MyBinaryTreeNode rt)
	{
		return (rt.right == null) ? rt : maxHelper(rt.right);
	}
	
	/**
	 * Find the minimum data value in the tree nodes.
	 * @return	The minimum data value.
	 */
	public Object min()
	{
		if (root == null)
			return null;
		
		return minHelper(root).data;
	}
	
	/*
	 * The helper method for min().
	 * @param	The root of the current subtree.
	 * @return	The tree node contains the minimum data value.
	 */
	private MyBinaryTreeNode minHelper(MyBinaryTreeNode rt)
	{
		return (rt.left == null) ? rt : minHelper(rt.left);
	}
	
	/**
	 * Find the tree node contains the same data value as the target.
	 * @param target	The target data value to be compared to.
	 * @return			The tree node contains the data value.
	 */
	public MyBinaryTreeNode find(Object target)
	{
		return findHelper(root, new MyBinaryTreeNode(target));
	}
	
	/*
	 * The helper method for find().
	 * @param	rt	The root of the current subtree.
	 * @param	target	A tree node contains the target value.
	 * @return	The tree node contains the same value as the target node.
	 */
	private MyBinaryTreeNode findHelper(MyBinaryTreeNode rt, 
										MyBinaryTreeNode targetNode)
	{
		if (rt == null)
			return null;
		
		if (targetNode.compareTo(rt) < 0)
			return findHelper(rt.left, targetNode);
		
		if (targetNode.compareTo(rt) > 0)
			return findHelper(rt.right, targetNode);
		
		return rt;
	}
	
	/**
	 * Remove a tree node contains the same data value as the target.
	 * Update the tree structure so that it maintains BST property.
	 * To update is using a successor to replace the node being removed. 
	 * @param target	The target data value to be compared to.
	 */
	public void remove(Object target)
	{
		if (root == null)
			return;
		
		MyBinaryTreeNode targetNode = new MyBinaryTreeNode(target);
		if (root.compareTo(targetNode) == 0)
		{//The root node is the node to be removed.
			MyBinaryTreeNode sp1 = successorParent(root);
			
			if(sp1 == null && root != null)
			{//The root node has no right child.
				root = root.left;
			}
			else if (sp1 == root)
			{//The right child is the successor. It must have no left child.
				sp1.right.left = root.left;
				root = root.right;
			}
			else
			{//The successor must be the left child of successor parent.
				MyBinaryTreeNode rightChildOfsucc = sp1.left.right;
				sp1.left.left = root.left;
				sp1.left.right = root.right;
				root = sp1.left;
				sp1.left = rightChildOfsucc;
			}
			return;
		}
		
		//The root node is not the node to be removed.
		MyBinaryTreeNode targetParent = findParent(root, targetNode);
		if(targetParent == null)
		{
			return;
		}
	
		if(targetParent.left != null)
		{
			if (targetParent.left.compareTo(targetNode) == 0)
			{//The left child of targetParent is the node to be removed.
				MyBinaryTreeNode rNode = targetParent.left;
				MyBinaryTreeNode sp2 = successorParent(rNode);
				
				if(rNode.left == null && rNode.right == null)
				{
					targetParent.left = null;
					return;
				}
				else if (sp2 == null && rNode != null)
				{//The target node has no right child.
					targetParent.left = rNode.left;
				}
				else if (sp2 == rNode)
				{//The right child is the successor. 
				 //It must have no left child.
					sp2.right.left = rNode.left;
					targetParent.left = rNode.right;
				}
				else
				{//The successor must be the left child of successor parent.
					MyBinaryTreeNode rightChildOfsucc = sp2.left.right;
					sp2.left.left = rNode.left;
					sp2.left.right = rNode.right;
					targetParent.left = sp2.left;
					sp2.left = rightChildOfsucc;
				}
				
				return;
			}
		}
		
		if(targetParent.right != null)
		{
			if (targetParent.right.compareTo(targetNode) == 0)
			{//The right child of targetParent is the node to be removed.
				MyBinaryTreeNode rNode = targetParent.right;
				MyBinaryTreeNode sp3 = successorParent(rNode);
				
				if(rNode.left == null && rNode.right == null)
				{
					targetParent.right = null;
					return;
				}
				else if (sp3 == null && rNode != null)
				{//The target node has no right child.
					targetParent.right = rNode.left;
				}
				else if (sp3 == rNode)
				{//The right child is the successor. 
				 //It must have no left child.
					sp3.right.left = rNode.left;
					targetParent.right = rNode.right;
				}
				else
				{//The successor must be the left child of successor parent.
					MyBinaryTreeNode rightChildOfsucc = sp3.left.right;
					sp3.left.left = rNode.left;
					sp3.left.right = rNode.right;
					targetParent.right = sp3.left;
					sp3.left = rightChildOfsucc;
				}
				
				return;
			}
		}
	}
	
	/*
	 * Private helper method to be used in remove.
	 * Finds the parent node of a node contains the value as the targetNode.
	 * @param	rt	The root of the current subtree.
	 * @param	targetNode	The target node to be compared to.
	 * @return	The parent node of the node  
	 * 			contains the same value as the target node.
	 */
	private MyBinaryTreeNode findParent(MyBinaryTreeNode rt, 
										MyBinaryTreeNode targetNode)
	{
		if (targetNode.compareTo(rt) < 0)
		{
			if (rt.left == null)
			{
				return null;
			}
			else if(targetNode.compareTo(rt.left) == 0)
			{
				return rt;
			}
			else
			{
				return findParent(rt.left, targetNode);
			}
		}
		else
		{
			if (rt.right == null)
			{
				return null;
			}
			else if(targetNode.compareTo(rt.right) == 0)
			{
				return rt;
			}
			else
			{
				return findParent(rt.right, targetNode);
			}
		}
	}
	
	/*
	 * Private helper method to be used in remove.
	 * Finds the parent node of the successor node in the subtree.
	 * @param	The root of the current subtree.
	 * @return	The parent node of the successor node of 
	 * 			a subtree rooted at rt.
	 * 			null, if no successor node exists.
	 */
	private MyBinaryTreeNode successorParent(MyBinaryTreeNode rt)
	{
		if (rt == null)
			return null;
		
		if (rt.right == null)
			return null;
		
		if (rt.right.left == null)
			return rt;
		
		MyBinaryTreeNode temp = rt.right;
		while (temp.left.left != null)
		{
			temp = temp.left;
		}
		return temp;
	}

}
