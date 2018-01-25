package org.praveen.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SearchElementInBTree {


    public boolean searchInBTreeRecursion(BinaryTreeNode binaryTreeNode, int data) {

        if (binaryTreeNode == null) {
            return false;
        }
        if (binaryTreeNode.getData() == data) {
            return true;
        }
        return searchInBTreeRecursion(binaryTreeNode.getLeft(), data) || searchInBTreeRecursion(binaryTreeNode.getRight(), data);
    }

    public boolean searchInBTreeNonRecursion(BinaryTreeNode binaryTreeNode, int data) {
        if (binaryTreeNode == null) {
            return false;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(binaryTreeNode);
        while (!queue.isEmpty()) {
            BinaryTreeNode tmp = queue.poll();
            if (tmp.getData() == data) {
                return true;
            }
            if (tmp.getLeft() != null) {
                queue.offer(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                queue.offer(tmp.getRight());
            }
        }
        return false;
    }

    public static void main(String[] args) {

        BinaryTreeNode treeNode = BinaryTreeBuilder.constructSampleBinaryTree();
        SearchElementInBTree searchElementInBTree = new SearchElementInBTree();
        System.out.println(searchElementInBTree.searchInBTreeRecursion(treeNode, 19));
        System.out.println(searchElementInBTree.searchInBTreeRecursion(treeNode, 14));
        System.out.println(searchElementInBTree.searchInBTreeNonRecursion(treeNode, 19));
        System.out.println(searchElementInBTree.searchInBTreeNonRecursion(treeNode, 14));
    }
}
