package org.praveen.tree;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfBinaryTree {

    public int getHeightOfBTreeRecursion(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode == null) {
            return 0;
        }
        int heightOfLeftTree = 1 + getHeightOfBTreeRecursion(binaryTreeNode.getLeft());
        int heightOfRightTree = 1 + getHeightOfBTreeRecursion(binaryTreeNode.getRight());

        return heightOfLeftTree > heightOfRightTree ? heightOfLeftTree : heightOfRightTree;
    }

    //At the end of a level we offer a  null into the queue
    public int getHeightOfBTreeNonRecursion(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode == null) {
            return 0;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(binaryTreeNode);
        queue.offer(null); // Offered all elements in Level 1 Hence Count 1
        int count = 1;

        while (!queue.isEmpty()) {
            BinaryTreeNode tmp = queue.poll();
            if (tmp != null) { //if element is null then we reached end of a level.
                if (tmp.getLeft() == null && tmp.getRight() == null) {
                    queue.poll(); //Remove the null as there is no more levels
                }
                if (tmp.getLeft() != null) {
                    queue.offer(tmp.getLeft());
                }
                if (tmp.getRight() != null) {
                    queue.offer(tmp.getRight());
                }
            } else { //Next Level -> Null Change in Level
                count++; //Increment Level
                queue.offer(null); // Next Level
            }
        }

        return count;
    }

    //TODO
    //Using Stack -> Understand PostOrder Traversal First using only 1 Stack.

    public static void main(String[] args) {
        HeightOfBinaryTree heightOfBinaryTree = new HeightOfBinaryTree();
        BinaryTreeNode treeNode = BinaryTreeBuilder.constructSampleBinaryTree();
        System.out.println("Recursion -> " + heightOfBinaryTree.getHeightOfBTreeRecursion(treeNode));
        System.out.println("Non Recursion -> " + heightOfBinaryTree.getHeightOfBTreeNonRecursion(treeNode));
    }

}
