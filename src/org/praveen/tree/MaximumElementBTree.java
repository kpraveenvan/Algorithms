package org.praveen.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumElementBTree {

    int maxValue = Integer.MIN_VALUE;

    public int getMaxElementInBTreeRecursion(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode != null) {
            int maxElementInLeft = getMaxElementInBTreeRecursion(binaryTreeNode.getLeft());
            int maxElementInRight = getMaxElementInBTreeRecursion(binaryTreeNode.getRight());

            maxValue = maxElementInLeft > maxElementInRight ? maxElementInLeft : maxElementInRight; //Get Max values of current iteration

            if (binaryTreeNode.getData() > maxValue) { //Compare the value of the max till current iteration with the current element.
                maxValue = binaryTreeNode.getData();
            }
        }
        return maxValue;
    }

    //Use level Order Traversal
    public int getMaxElementInBTreeNonRecursion(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode != null) {
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.offer(binaryTreeNode);
            while (!queue.isEmpty()) {
                BinaryTreeNode tmp = queue.poll();
                if (maxValue < tmp.getData()) {
                    maxValue = tmp.getData();
                }
                if (tmp.getLeft() != null) {
                    queue.offer(tmp.getLeft());
                }
                if (tmp.getRight() != null) {
                    queue.offer(tmp.getRight());
                }
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        MaximumElementBTree maximumElementBTree = new MaximumElementBTree();
        BinaryTreeNode treeNode = BinaryTreeBuilder.constructSampleBinaryTree();
        System.out.println(maximumElementBTree.getMaxElementInBTreeRecursion(treeNode));
        System.out.println(maximumElementBTree.getMaxElementInBTreeNonRecursion(treeNode));
    }
}
