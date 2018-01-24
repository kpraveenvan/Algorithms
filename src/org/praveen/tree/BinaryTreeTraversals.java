package org.praveen.tree;

import java.util.Stack;

public class BinaryTreeTraversals {

    //Pre Order Traversals.
    public void preOrderTraversalRecursion(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode != null) {
            System.out.println(binaryTreeNode.getData());
            preOrderTraversalRecursion(binaryTreeNode.getLeft());
            preOrderTraversalRecursion(binaryTreeNode.getRight());
        }
    }

    public void preOrderTraversalNonRecursion(BinaryTreeNode binaryTreeNode) {

        Stack<BinaryTreeNode> st = new Stack();
        st.push(binaryTreeNode);
        while (!st.empty()) {
            binaryTreeNode = st.pop();
            System.out.println(binaryTreeNode.getData());
            //Since its a stack the order is importent
            if (binaryTreeNode.getRight() != null) {
                st.push(binaryTreeNode.getRight());
            }
            if (binaryTreeNode.getLeft() != null) {
                st.push(binaryTreeNode.getLeft());
            }
        }
    }

    //InOrder Traversal
    public void inOrderTraversalRecursion(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode != null) {
            inOrderTraversalRecursion(binaryTreeNode.getLeft());
            System.out.println(binaryTreeNode.getData());
            inOrderTraversalRecursion(binaryTreeNode.getRight());
        }
    }

    public void inOrderTraversalNonRecursion(BinaryTreeNode binaryTreeNode) {

        Stack<BinaryTreeNode> st = new Stack<>();

        //Push All the left Nodes First
        while (binaryTreeNode != null) {
            st.push(binaryTreeNode);
            binaryTreeNode = binaryTreeNode.getLeft();
        }

        while (!st.isEmpty()) {
            binaryTreeNode = st.pop();
            System.out.println(binaryTreeNode.getData());

            //Get the Right Node
            if (binaryTreeNode.getRight() != null) {
                binaryTreeNode = binaryTreeNode.getRight();

                //Push all the left nodes for that right node
                while (binaryTreeNode != null) {
                    st.push(binaryTreeNode);
                    binaryTreeNode = binaryTreeNode.getLeft();
                }
            }
        }
    }

    //Post Order Traversal
    public void postOrderTraversalRecursive(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode != null) {
            postOrderTraversalRecursive(binaryTreeNode.getLeft());
            postOrderTraversalRecursive(binaryTreeNode.getRight());
            System.out.println(binaryTreeNode.getData());
        }
    }

    //Funda is root is the last element in the list.
    public void postOrderTraversalNonRecursive(BinaryTreeNode binaryTreeNode) {

        Stack<BinaryTreeNode> st1 = new Stack<>();
        Stack<BinaryTreeNode> st2 = new Stack<>();
        st1.push(binaryTreeNode);
        while (!st1.isEmpty()) {

            BinaryTreeNode tmp = st1.pop();
            st2.push(tmp); //Push the poped element to the stack2 [Fist Element poped in reverse order will be root element]

            //Now Push the Left and Right elemets to stack 1
            if (tmp.getLeft() != null) {
                st1.push(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                st1.push(tmp.getRight());
            }
        }

        //stack2 has the elements in PostOrder traversal in reverse order.
        while (!st2.isEmpty()) {
            BinaryTreeNode tmp = st2.pop();
            System.out.println(tmp.getData());
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = BinaryTreeBuilder.constructSampleBinaryTree();
        BinaryTreeTraversals binaryTreeTraversals = new BinaryTreeTraversals();
        binaryTreeTraversals.preOrderTraversalRecursion(root);
        System.out.println("-------------------");
        binaryTreeTraversals.preOrderTraversalNonRecursion(root);
        System.out.println("-------------------");
        binaryTreeTraversals.inOrderTraversalRecursion(root);
        System.out.println("-------------------");
        binaryTreeTraversals.inOrderTraversalNonRecursion(root);
        System.out.println("-------------------");
        binaryTreeTraversals.postOrderTraversalRecursive(root);
        System.out.println("-------------------");
        binaryTreeTraversals.postOrderTraversalNonRecursive(root);
    }

}
