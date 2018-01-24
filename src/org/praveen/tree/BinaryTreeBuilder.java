package org.praveen.tree;

public class BinaryTreeBuilder {

    public static BinaryTreeNode constructSampleBinaryTree() {

        BinaryTreeNode n1 = new BinaryTreeNode(5);
        BinaryTreeNode n2 = new BinaryTreeNode(7);
        BinaryTreeNode n3 = new BinaryTreeNode(9);
        BinaryTreeNode n4 = new BinaryTreeNode(12);
        BinaryTreeNode n5 = new BinaryTreeNode(13);
        BinaryTreeNode n6 = new BinaryTreeNode(19);
        BinaryTreeNode n7 = new BinaryTreeNode(15);
        BinaryTreeNode n8 = new BinaryTreeNode(17);
        BinaryTreeNode n9 = new BinaryTreeNode(21);

        n1.setLeft(n2);
        n1.setRight(n3);
        n2.setLeft(n4);
        n2.setRight(n5);
        n3.setLeft(n6);
        n4.setLeft(n7);
        n5.setLeft(n8);
        n6.setRight(n9);

        return n1;
    }
}
