package binaryTree;

import java.util.Stack;

public class FlattenTree {

  static TreeNode previous = null;
  
  public static void main(String[] args) {
    TreeNode three = new TreeNode(3);
    TreeNode four = new TreeNode(4);
    TreeNode two = new TreeNode(2, three, four);
    TreeNode six = new TreeNode(6);
    TreeNode five = new TreeNode(5, null, six);
    TreeNode one = new TreeNode(1, two, five);
    flatten1(one);
  }
  
  private static void flatten1(TreeNode root) {
    if (root == null) return;
    flatten1(root.right);
    flatten1(root.left);
    root.right = previous;
    root.left = null;
    previous = root;
  }
  
  private static void flatten(TreeNode root) {
    Stack<TreeNode> queue = new Stack<>();
    TreeNode prev = null, origin = null;
    while (root != null || !queue.isEmpty()) {
        while (root != null) {
            
            queue.push(root);
            if (prev != null) {
                
                prev.right = new TreeNode(root.val);
//                prev.left = null;
                System.out.printf("prev is %d, root is %d\n", prev.val, root.val);
            }
            if (prev == null) {
                prev = new TreeNode(root.val);
                origin = prev;
            } else {
                prev = prev.right;
            }
            
            root = root.left;

        }
        System.out.println("exit the inner while loop");
        root = queue.pop();
        System.out.println("root is " + root.val);
        root = root.right;
        if (root != null) {
            System.out.println("root's right is " + root.val);
        } else {
            System.out.println("root's right is null");
        }
    }
    root = origin;
}

}
