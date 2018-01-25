package org.praveen.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SizeOfBinaryTree {

    public int sizeOfBTreeRecursive(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode == null) {
            return 0;
        }
        int sizeOfLeftTree = binaryTreeNode.getLeft() == null ? 0 : sizeOfBTreeRecursive(binaryTreeNode.getLeft());
        int sizeOfRighTree = binaryTreeNode.getRight() == null ? 0 : sizeOfBTreeRecursive(binaryTreeNode.getRight());
        return 1 + sizeOfLeftTree + sizeOfRighTree;
    }

    public int sizeOfBTreeNonRecursive(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode == null) {
            return 0;
        }
        int count = 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(binaryTreeNode);
        while (!queue.isEmpty()) {
            BinaryTreeNode tmp = queue.poll();
            count++;
            if (tmp.getLeft() != null) {
                queue.offer(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                queue.offer(tmp.getRight());
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BinaryTreeNode treeNode = BinaryTreeBuilder.constructSampleBinaryTree();
        SizeOfBinaryTree sizeOfBinaryTree = new SizeOfBinaryTree();
        System.out.println(sizeOfBinaryTree.sizeOfBTreeRecursive(treeNode));
        System.out.println(sizeOfBinaryTree.sizeOfBTreeNonRecursive(treeNode));
    }
}
