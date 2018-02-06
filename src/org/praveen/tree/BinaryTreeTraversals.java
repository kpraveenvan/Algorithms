package org.praveen.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeTraversals {

    //Pre Order Traversals.
    public static void preOrderTraversalRecursion(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode != null) {
            System.out.print(" " + binaryTreeNode.getData());
            preOrderTraversalRecursion(binaryTreeNode.getLeft());
            preOrderTraversalRecursion(binaryTreeNode.getRight());
        }
    }

    public static void preOrderTraversalNonRecursion(BinaryTreeNode binaryTreeNode) {

        Stack<BinaryTreeNode> st = new Stack();
        st.push(binaryTreeNode);
        while (!st.empty()) {
            binaryTreeNode = st.pop();
            System.out.print(" " + binaryTreeNode.getData());
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
    public static void inOrderTraversalRecursion(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode != null) {
            inOrderTraversalRecursion(binaryTreeNode.getLeft());
            System.out.print(" " + binaryTreeNode.getData());
            inOrderTraversalRecursion(binaryTreeNode.getRight());
        }
    }

    public static void inOrderTraversalNonRecursion(BinaryTreeNode binaryTreeNode) {

        Stack<BinaryTreeNode> st = new Stack<>();

        //Push All the left Nodes First
        while (binaryTreeNode != null) {
            st.push(binaryTreeNode);
            binaryTreeNode = binaryTreeNode.getLeft();
        }

        while (!st.isEmpty()) {
            binaryTreeNode = st.pop();
            System.out.print(" " + binaryTreeNode.getData());

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
    public static void postOrderTraversalRecursive(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode != null) {
            postOrderTraversalRecursive(binaryTreeNode.getLeft());
            postOrderTraversalRecursive(binaryTreeNode.getRight());
            System.out.print(" " + binaryTreeNode.getData());
        }
    }

    //Funda is root is the last element in the list.
    public static void postOrderTraversalNonRecursive(BinaryTreeNode binaryTreeNode) {

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
            System.out.print(" " + tmp.getData());
        }
    }

    public static int heightOfBinaryTree(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode == null) {
            return 0;
        }
        int heightOfLeftTree = 1 + heightOfBinaryTree(binaryTreeNode.getLeft());
        int heightOfRightTree = 1 + heightOfBinaryTree(binaryTreeNode.getRight());
        return heightOfLeftTree > heightOfRightTree ? heightOfLeftTree : heightOfRightTree;
    }


    //put all the elements in queue level wise.
    public static void levelOrderTraversal(BinaryTreeNode binaryTreeNode) {

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(binaryTreeNode);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            if (node != null) {
                System.out.print(" " + node.getData());
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }
        }
    }

    public static void PostOrderSingleStack(BinaryTreeNode binaryTreeNode) {

        BinaryTreeNode current = binaryTreeNode; // Initialize to the root
        Stack<BinaryTreeNode> stack = new Stack<>();

        while (current != null || !stack.isEmpty()) {

            if (current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                if (!stack.isEmpty()) {
                    BinaryTreeNode tmp = stack.peek();
                    if (tmp.getRight() == null) {

                        tmp = stack.pop(); //We actually pop from the stack if there is no right child.
                        System.out.print(" " + tmp.getData());

                        // check if the element which is poped is the right child of the top of the stack
                        if (tmp == stack.peek().getRight()) {
                            //This means we are done visiting the right tree of the top of the stack
                            tmp = stack.pop(); // this means pop again
                            System.out.print(" " + tmp.getData());
                        }
                    } else {
                        current = tmp.getRight(); //If top of the stack has a right child
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = BinaryTreeBuilder.constructSampleBinaryTree();
        BinaryTreeTraversals.preOrderTraversalRecursion(root);
        System.out.println("");
        BinaryTreeTraversals.preOrderTraversalNonRecursion(root);
        System.out.println("");
        BinaryTreeTraversals.inOrderTraversalRecursion(root);
        System.out.println("");
        BinaryTreeTraversals.inOrderTraversalNonRecursion(root);
        System.out.println("");
        BinaryTreeTraversals.postOrderTraversalRecursive(root);
        System.out.println("");
        BinaryTreeTraversals.postOrderTraversalNonRecursive(root);
        System.out.println("");
        BinaryTreeTraversals.PostOrderSingleStack(root);
        System.out.println("");
        System.out.println("Height -> " + BinaryTreeTraversals.heightOfBinaryTree(root));
        BinaryTreeTraversals.levelOrderTraversal(root);

    }

}
