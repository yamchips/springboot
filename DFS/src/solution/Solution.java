package solution;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class Solution {
  public TreeNode invertTree(TreeNode root) {
    Queue<TreeNode> nodes = new LinkedList<>();
    if (root == null) return null;
    nodes = getList(root);
    // reverse the linkedlist and return the middle element
    LinkedList<TreeNode> nodesReversed = new LinkedList<>();
    while (!nodes.isEmpty()) {
        TreeNode curr = nodes.poll();
        nodesReversed.addFirst(curr);
    }
    return nodesReversed.get((int)Math.ceil(nodesReversed.size()/2));

    
}

public Queue<TreeNode> getList(TreeNode root) {
    LinkedList<TreeNode> nodes = new LinkedList<>();
    nodes.offer(root);
    if (root.left != null) nodes.addAll(0, getList(root.left));
    if (root.right != null) nodes.addAll(-1, getList(root.right));
    return nodes;
}
}
