package org.praveen.tree;


import java.util.LinkedList;
import java.util.Queue;

public class InsertElementInBTree {

    //Do a level Order Traversal and find out the first element whose first left or right node is empty and insert there!!!
    public BinaryTreeNode insertElementBTree(BinaryTreeNode binaryTreeNode, int data) {

        if (binaryTreeNode == null) {
            binaryTreeNode = new BinaryTreeNode(data);
            return binaryTreeNode;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(binaryTreeNode);
        while (!queue.isEmpty()) {
            BinaryTreeNode tmp = queue.poll();
            if (tmp.getLeft() != null) {
                queue.offer(tmp.getLeft());
            } else {
                tmp.setLeft(new BinaryTreeNode(data));
                return binaryTreeNode;
            }
            if (tmp.getRight() != null) {
                queue.offer(tmp.getRight());
            } else {
                tmp.setRight(new BinaryTreeNode(data));
                return binaryTreeNode;
            }
        }
        return binaryTreeNode;
    }
}
