package org.praveen.practicetree;

import java.util.Stack;

public class TreeOperations {


    public static void preOrderTraversal(BTree bTree) {

        Stack<BTree> bTreeStack = new Stack<>();
        bTreeStack.push(bTree);

        while (!bTreeStack.isEmpty()) {
            BTree node = bTreeStack.pop();
            System.out.print(node.getData() + " -- ");

            if (node.getRight() != null) {
                bTreeStack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                bTreeStack.push(node.getLeft());
            }
        }
    }

    public static void inOrderTraversal(BTree bTree) {

        Stack<BTree> bTreeStack = new Stack<>();

        //Push All left nodes
        pushNodeAndAllLeftNodes(bTree, bTreeStack);

        while (!bTreeStack.isEmpty()) {

            BTree node = bTreeStack.pop();
            System.out.print(node.getData() + " -- ");

            if (node.getRight() != null) {
                pushNodeAndAllLeftNodes(node.getRight(), bTreeStack);
            }
        }
    }

    public static void pushNodeAndAllLeftNodes(BTree bTree, Stack<BTree> bTreeStack) {

        while (bTree != null) {
            bTreeStack.push(bTree);
            bTree = bTree.getLeft();
        }
    }

    public static void postOrderTraversal(BTree bTree) {

        Stack<BTree> bTreeStack = new Stack<>();
        Stack<BTree> bTreeStackRemember = new Stack<>();
        pushNodeAndAllLeftNodes(bTree, bTreeStack); //Fist push all left nodes

        while (!bTreeStack.isEmpty()) {

            BTree node = bTreeStack.peek();
            if (!bTreeStackRemember.isEmpty() && node == bTreeStackRemember.peek()) {
                node = bTreeStack.pop();
                bTreeStackRemember.pop();
                System.out.print(node.getData() + "----");
            } else {
                if (node.getRight() == null) {
                    node = bTreeStack.pop();
                    System.out.print(node.getData() + "----");
                } else {
                    bTreeStackRemember.push(node);
                    pushNodeAndAllLeftNodes(node.getRight(), bTreeStack);
                }
            }
        }
    }

    public static void postOrderTraversalTwoStacks(BTree bTree) {

        Stack<BTree> bTreeStack1 = new Stack<>();
        Stack<BTree> bTreeStack2 = new Stack<>();

        bTreeStack1.push(bTree);

        while (!bTreeStack1.isEmpty()) {

            BTree node = bTreeStack1.pop();
            bTreeStack2.push(node); //Push the nodes onto stack 2 when popped from the stack1

            if (node.getLeft() != null) { //Push the Left node first
                bTreeStack1.push(node.getLeft());
            }
            if (node.getRight() != null) {
                bTreeStack1.push(node.getRight());
            }
        }

        while (!bTreeStack2.isEmpty()) {

            BTree node = bTreeStack2.pop();
            System.out.print(node.getData() + " -- ");

        }
    }

    public static void postOrderTraversalSingleStack(BTree bTree) {

        Stack<BTree> bTreeStack = new Stack<>();
        pushNodeAndAllLeftNodes(bTree, bTreeStack);
        while (!bTreeStack.isEmpty()) {

            BTree current = bTreeStack.peek();
            if (current.getRight() == null) {
                BTree node = bTreeStack.pop();
                System.out.print(node.getData() + " -- ");

                //This is the key - Check if the element which is popped is the right node of the top element in the Stack!
                while (!bTreeStack.isEmpty() && bTreeStack.peek().getRight() != null && bTreeStack.peek().getRight() == node) {
                    node = bTreeStack.pop();
                    System.out.print(node.getData() + " -- ");
                }
            } else {
                pushNodeAndAllLeftNodes(current.getRight(), bTreeStack);
            }
        }
    }

    public static void preOrderTraversalRecursive(BTree bTree) {

        if (bTree != null) {
            System.out.print(bTree.getData() + " -- ");
            preOrderTraversalRecursive(bTree.getLeft());
            preOrderTraversalRecursive(bTree.getRight());
        }
    }

    public static void inOrderTraversalRecursive(BTree bTree) {

        if (bTree != null) {
            inOrderTraversalRecursive(bTree.getLeft());
            System.out.print(bTree.getData() + " -- ");
            inOrderTraversalRecursive(bTree.getRight());
        }
    }

    public static void postOrderTraversalRecursive(BTree bTree) {

        if (bTree != null) {
            postOrderTraversalRecursive(bTree.getLeft());
            postOrderTraversalRecursive(bTree.getRight());
            System.out.print(bTree.getData() + " -- ");
        }
    }

    public static BTree constructBTree() {

        BTree node1 = new BTree(1);
        BTree node2 = new BTree(2);
        BTree node3 = new BTree(3);
        BTree node4 = new BTree(4);
        BTree node5 = new BTree(5);
        BTree node6 = new BTree(6);
        BTree node7 = new BTree(7);
        BTree node8 = new BTree(8);
        BTree node9 = new BTree(9);
        BTree node10 = new BTree(10);

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node4.setLeft(node6);
        node5.setLeft(node7);
        node5.setRight(node8);
        node3.setLeft(node9);
        node9.setRight(node10);

        return node1;
    }

    public static BTree constructBTreeSimple() {

        BTree node1 = new BTree(1);
        BTree node2 = new BTree(2);
        BTree node3 = new BTree(3);
        BTree node4 = new BTree(4);
        BTree node5 = new BTree(5);
        BTree node6 = new BTree(6);
        BTree node7 = new BTree(7);

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);


        return node1;
    }

    public static BTree constructBTreeComplex() {

        BTree node1 = new BTree(1);
        BTree node2 = new BTree(2);
        BTree node3 = new BTree(3);
        BTree node4 = new BTree(4);
        BTree node5 = new BTree(5);
        BTree node6 = new BTree(6);
        BTree node7 = new BTree(7);
        BTree node8 = new BTree(8);
        BTree node9 = new BTree(9);
        BTree node10 = new BTree(10);
        BTree node11 = new BTree(11);

        node1.setLeft(node2);
        node1.setRight(node11);
        node2.setLeft(node3);
        node2.setRight(node8);
        node3.setLeft(node4);
        node3.setRight(node5);
        node5.setLeft(node6);
        node6.setRight(node7);
        node8.setRight(node9);
        node9.setLeft(node10);


        return node1;
    }

    public static void main(String[] args) {

        BTree bTree = TreeOperations.constructBTree();
//        TreeOperations.preOrderTraversal(bTree);
//        System.out.println();
//        TreeOperations.inOrderTraversal(bTree);
//        System.out.println();
//        TreeOperations.postOrderTraversal(bTree);
//        System.out.println();
//        TreeOperations.postOrderTraversalSingleStack(bTree);
        bTree = TreeOperations.constructBTreeComplex();
//        System.out.println();
//        TreeOperations.preOrderTraversal(bTree);
//        System.out.println();
//        TreeOperations.inOrderTraversal(bTree);
//        System.out.println();
//        TreeOperations.postOrderTraversal(bTree);
//        System.out.println();
        TreeOperations.postOrderTraversalSingleStack(bTree);
        System.out.println();
        TreeOperations.postOrderTraversalTwoStacks(bTree);
    }

    public static class BTree {

        private int data;
        private BTree left;
        private BTree right;

        public BTree(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public BTree getLeft() {
            return left;
        }

        public void setLeft(BTree left) {
            this.left = left;
        }

        public BTree getRight() {
            return right;
        }

        public void setRight(BTree right) {
            this.right = right;
        }
    }
}
