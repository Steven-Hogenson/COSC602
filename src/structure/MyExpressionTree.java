package structure;

/**
 * @author Steven Hogenson on 4/20/2022
 * @project StevenHogenson_Java
 */
public class MyExpressionTree extends MyBinaryTree {


    private String preorderString;
    private String inorderString;
    private String postOrderString;


    public MyExpressionTree() {
        root = null;
    }

    public MyExpressionTree(MyBinaryTreeNode rt) {
        root = rt;
    }

    public static int evaluate(MyBinaryTreeNode rt) {
        if (rt == null) {
            return -1;
        }
        if (rt.left == null && rt.right == null) {
            return Character.getNumericValue((Character) rt.data);
        }
        switch ((Character) rt.data) {
            case '-':
                return evaluate(rt.left) - evaluate(rt.right);
            case '+':
                return evaluate(rt.left) + evaluate(rt.right);
            case '*':
                return evaluate(rt.left) * evaluate(rt.right);
            case '/':
                return evaluate(rt.left) / evaluate(rt.right);
            case '%':
                return evaluate(rt.left) % evaluate(rt.right);
        }
        return -1;
    }

    public String preorderTraversal() {
        preorderString = "";//resets String back to empty String each time a preorder traversal happens
        preorderHelper(root);
        return preorderString;
    }

    private void preorderHelper(MyBinaryTreeNode rt) {
        if (rt == null) {
            return;
        }

        preorderString += " " + rt.data;
        preorderHelper(rt.left);
        preorderHelper(rt.right);

    }

    public String inorderTraversal() {
        inorderString = "";//resets String back to empty String each time an inorder traversal happens
        inorderHelper(root);
        return inorderString;
    }

    private void inorderHelper(MyBinaryTreeNode rt) {
        if (rt == null) {
            return;
        }

        inorderHelper(rt.left);
        inorderString += " " + rt.data;
        inorderHelper(rt.right);
    }

    public String postorderTraversal() {
        postOrderString = "";//resets String back to empty String each time a postorder traversal happens
        postorderHelper(root);
        return postOrderString;
    }


    private void postorderHelper(MyBinaryTreeNode rt) {
        if (rt == null) {
            return;
        }

        postorderHelper(rt.left);
        postorderHelper(rt.right);
        postOrderString += " " + rt.data;
    }
}
