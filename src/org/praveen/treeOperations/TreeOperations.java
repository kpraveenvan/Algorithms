package org.praveen.treeOperations;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeOperations {

    //Operations
    //PreOrder Traversal - Recursive
    //PreOrder Traversal - Iterative
    //InOrder Traversal - Recursive
    //InOrder Traversal - Iterative
    //postOrder Traversal - Recursive
    //postOrder Traversal - Iterative - 2 stacks
    //postOrder Traversal - Iterative - 2 stacks Custom
    //postOrder Traversal - Iterative - 1 stacks  -> //In A loop Check If the current is right of the top of the stack then pop the top of the stack too!!
    //LevelOrder Traversal
    //Maximum in a BTree - Recursive
    //Maximum in BTree - Iterative
    //Searching an Element in BTree - Recursive
    //Searching an Element in BTree - Iterative
    //Insert Element in BTree - Recursive - BST
    //Insert Element in BTree - Iterative
    //Sizeof BTree - Recursive
    //Sizeof BTree - Iterative
    //PreOrder Traversal - Recursive
    //PreOrder Traversal - Special Case Level order 1,2,3,4,5,6,7 (Simple), Order should be 4,5,6,7,2,3,1
    //Height of BTree - Recursive
    //Height of BTree - Iterative
    //Minimum Height of BTree
    //Deepest Node of BTree
    //Deleting a Node in BTree - BST
    //Number of leaf nodes in BTree
    //Number of full nodes in BTree
    //Number of half nodes in BTree
    //** Two Trees Structurally Identical - Recursive
    //Two Trees Structurally Identical - Iterative
    //Maximum width - Btree
    //** Diameter of BTree
    //Level with maximum Sum
    //*** Print root to leaf paths
    //*** Existence of path from root to any node with a Sum
    //Sum of all elements in bTree - Recursion
    //Sum of all elements in bTree - Iterative - use level order traversal
    //Construct a Mirror of the Tree (Left Nodes become Right and Right Becomes left)
    //Check if two trees a Mirror of the each other


    public static void preOrderRecursive(BTree bTree) {

        if (bTree != null) {
            System.out.print(bTree.getData() + " -- ");
            preOrderRecursive(bTree.getLeft());
            preOrderRecursive(bTree.getRight());
        }
    }

    //PreOrder Traversal - Iterative
    public static void preOrderIterative(BTree bTree) {

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

    //InOrder Traversal - Recursive
    public static void inOrderRecursive(BTree bTree) {

        if (bTree != null) {
            inOrderRecursive(bTree.getLeft());
            System.out.print(bTree.getData() + " -- ");
            inOrderRecursive(bTree.getRight());
        }
    }

    //InOrder Traversal - Iterative
    public static void inOrderIterative(BTree bTree) {

        Stack<BTree> bTreeStack = new Stack<>();
        pushCurrentNodeAndAllLeftNodes(bTree, bTreeStack);

        while (!bTreeStack.isEmpty()) {

            BTree node = bTreeStack.pop();
            System.out.print(node.getData() + " -- ");

            if (node.getRight() != null) {
                pushCurrentNodeAndAllLeftNodes(node.getRight(), bTreeStack);
            }
        }
    }

    //postOrder Traversal - Recursive
    public static void postOrderRecursive(BTree bTree) {

        if (bTree != null) {
            postOrderRecursive(bTree.getLeft());
            postOrderRecursive(bTree.getRight());
            System.out.print(bTree.getData() + " -- ");
        }
    }

    //postOrder Traversal - Iterative - 2 stacks
    public static void postOrderIterative2Stacks(BTree bTree) {

        Stack<BTree> stack1 = new Stack<>();
        Stack<BTree> stack2 = new Stack<>();

        stack1.push(bTree);

        while (!stack1.isEmpty()) {

            BTree node = stack1.pop();
            stack2.push(node);

            if (node.getLeft() != null) {
                stack1.push(node.getLeft());
            }
            if (node.getRight() != null) {
                stack1.push(node.getRight());
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().getData() + " -- ");
        }
    }

    //postOrder Traversal - Iterative - 2 Stacks Custom
    public static void postOrderIterative2StacksCustom(BTree bTree) {

        Stack<BTree> stack1 = new Stack<>();
        Stack<BTree> stack2 = new Stack<>();

        pushCurrentNodeAndAllLeftNodes(bTree, stack1);

        while (!stack1.isEmpty()) {

            BTree node = stack1.peek();

            //Make sure we are not going in circles
            if (!stack2.isEmpty() && stack2.peek().equals(node)) {
                stack1.pop();
                stack2.pop();
                System.out.print(node.getData() + " -- ");
            } else if (node.getRight() == null) {
                stack1.pop();
                System.out.print(node.getData() + " -- ");
            } else {
                stack2.push(node); //This is to remember that we we have visited the right node,
                pushCurrentNodeAndAllLeftNodes(node.getRight(), stack1);
            }
        }
    }

    //PostOrder Traversal - Iterative - 1 Stack
    public static void postOrderIterative1Stack(BTree bTree) {

        Stack<BTree> bTreeStack = new Stack<>();
        pushCurrentNodeAndAllLeftNodes(bTree, bTreeStack);
        while (!bTreeStack.isEmpty()) {

            BTree current = bTreeStack.peek();

            if (current.getRight() == null) {
                bTreeStack.pop();
                System.out.print(current.getData() + " -- ");

                while (!bTreeStack.isEmpty() && bTreeStack.peek().getRight() != null && bTreeStack.peek().getRight().equals(current)) { //In A loop Check If the current is right of the top of the stack then pop the top of the stack too!!
                    current = bTreeStack.pop();
                    System.out.print(current.getData() + " -- ");
                }
            } else {
                pushCurrentNodeAndAllLeftNodes(current.getRight(), bTreeStack);
            }
        }

    }

    //LevelOrder Traversal
    public static void levelOrderTraversal(BTree bTree) {

        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);

        while (!bTreeQueue.isEmpty()) {
            BTree node = bTreeQueue.poll();
            System.out.print(node.getData() + " -- ");

            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
            }
        }
    }

    //Maximum in a BTree - Recursive
    public static int maximumInBTreeRecursive(BTree bTree) {

        int max = Integer.MIN_VALUE;
        if (bTree != null) {
            int leftMax = maximumInBTreeRecursive(bTree.getLeft());
            int rightMax = maximumInBTreeRecursive(bTree.getRight());

            if (leftMax > max) {
                max = leftMax;
            }
            if (rightMax > max) {
                max = rightMax;
            }
            if (bTree.getData() > max) {
                max = bTree.getData();
            }
        }
        return max;
    }

    //Maximum in a BTree - Iterative
    public static int maximumInBTreeIterative(BTree bTree) {

        int max = Integer.MIN_VALUE;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);
        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            if (node.getData() > max) {
                max = node.getData();
            }

            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
            }
        }
        return max;
    }

    //Searching an Element in BTree - Recursive
    public static boolean searchingElementRecursive(BTree bTree, int element) {

        boolean found = false;
        if (bTree != null) {
            boolean foundLeftTree = searchingElementRecursive(bTree.getLeft(), element);
            boolean foundRightTree = searchingElementRecursive(bTree.getRight(), element);
            if (foundLeftTree) {
                found = true;
            }
            if (foundRightTree) {
                found = true;
            }
            if (bTree.getData() == element) {
                found = true;
            }
        }
        return found;
    }

    //Searching an Element in BTree - Iterative
    public static boolean searchingElementIterative(BTree bTree, int element) {

        boolean found = false;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        while (!bTreeQueue.isEmpty()) {
            BTree node = bTreeQueue.poll();
            if (node.getData() == element) {
                found = true;
                break;
            }

            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
            }
        }
        return found;
    }

    //Insert Element in BTree - Iterative
    public static void insertElementBTree(BTree bTree, int element) {

        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);

        while (!bTreeQueue.isEmpty()) {
            BTree node = bTreeQueue.poll();
            if (node.getLeft() == null) {
                node.setLeft(new BTree(element));
                break;
            } else {
                bTreeQueue.offer(node.getLeft());
            }

            if (node.getRight() == null) {
                node.setLeft(new BTree(element));
                break;
            } else {
                bTreeQueue.offer(node.getRight());
            }
        }
    }

    //Sizeof BTree - Recursive
    public static int sizOfBTreeRecursive(BTree bTree) {

        int size = 0;
        if (bTree != null) {

            int sizeOfLeft = sizOfBTreeRecursive(bTree.getLeft());
            int sizeOfRight = sizOfBTreeRecursive(bTree.getRight());
            size = sizeOfLeft + sizeOfRight + 1;
        }

        return size;
    }

    //Size of Btree - Iterative
    public static int sizeOfBTreeIterative(BTree bTree) {

        int size = 0;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);

        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            size++;

            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
            }
        }
        return size;
    }

    //Level Order Traversal - Reverse order
    public static void levelOrderReverseOrder(BTree bTree) {

        Stack<BTree> bTreeStack = new Stack<>();
        Queue<BTree> bTreeQueue = new LinkedList<>();

        bTreeQueue.offer(bTree);
        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
            }

            bTreeStack.push(node);
        }

        while (!bTreeStack.isEmpty()) {
            System.out.print(bTreeStack.pop().getData() + " -- ");
        }
    }

    //Height of BTree - Iterative - End of a level insert an null and while iterating check for null (if null add 1 to height)
    public static int heightOfBTreeIterative(BTree bTree) {

        int height = 1;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);
        bTreeQueue.offer(null);
        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            if (node != null) {
                if (node.getLeft() != null) {
                    bTreeQueue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    bTreeQueue.offer(node.getRight());
                }
            } else {
                if (!bTreeQueue.isEmpty()) {
                    height++;
                    bTreeQueue.offer(null);
                }
            }
        }
        return height;
    }

    //Minimum Height of BTree
    public static int minimumHeightBTree(BTree bTree) {

        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);
        bTreeQueue.offer(null);
        int height = 1;

        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            if (node != null) {
                if (node.getLeft() == null || node.getRight() == null) {
                    return height;
                }
                bTreeQueue.offer(node.getLeft());
                bTreeQueue.offer(node.getRight());
            } else {
                if (!bTreeQueue.isEmpty()) { //If node is null and you have only null left
                    height++;
                    bTreeQueue.offer(null);
                }
            }
        }
        return height;
    }

    //Deepest Node of BTree - Last Node in Level Order Traversal
    public static BTree deepestNodeOfbTree(BTree bTree) {

        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);
        BTree deepestNode = null;
        while (!bTreeQueue.isEmpty()) {

            deepestNode = bTreeQueue.poll();
            if (deepestNode.getLeft() != null) {
                bTreeQueue.offer(deepestNode.getLeft());
            }
            if (deepestNode.getRight() != null) {
                bTreeQueue.offer(deepestNode.getRight());
            }
        }
        return deepestNode;
    }

    //Number of leaf nodes in BTree
    public static int numberOfLeafNodes(BTree bTree) {

        int numberOfleafNodes = 0;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);

        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
            }

            if (node.getLeft() == null && node.getRight() == null) {
                numberOfleafNodes++;
            }
        }
        return numberOfleafNodes;
    }

    //Number of full nodes in BTree
    public static int numberOfFullNodesInBTree(BTree bTree) {

        int numberOfFullNodes = 0;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);
        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
            }
            if (node.getLeft() != null && node.getRight() != null) {
                numberOfFullNodes++;
            }
        }
        return numberOfFullNodes;
    }

    //Number of half nodes in BTree
    public static int numberOfHalfNodesInBTree(BTree bTree) {

        int numberOfHalfNodes = 0;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);

        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();
            if (node.getLeft() != null) {
                bTreeQueue.offer(node.getLeft());
                if (node.getRight() == null) {
                    numberOfHalfNodes++;
                }
            }
            if (node.getRight() != null) {
                bTreeQueue.offer(node.getRight());
                if (node.getLeft() == null) {
                    numberOfHalfNodes++;
                }
            }
        }
        return numberOfHalfNodes;
    }

    //Two Trees Structurally Identical
    public static boolean isStructurallyIdenticalRecursive(BTree bTree1, BTree bTree2) {

        if (bTree1 == null && bTree2 == null) { //Both null - return true
            return true;
        } else if (bTree1 != null && bTree2 != null) { //If both Not null - check its left and right tree subtrees
            return isStructurallyIdenticalRecursive(bTree1.getLeft(), bTree2.getLeft()) && isStructurallyIdenticalRecursive(bTree1.getRight(), bTree2.getRight());
        } else {
            return false;
        }
    }

    //Two Trees Structurally Identical - Iterative
    public static boolean isStructurallyIdenticalIterative(BTree bTree1, BTree bTree2) {

        if ((bTree1 == null && bTree2 != null) || (bTree1 != null && bTree2 == null)) {
            return false;
        }
        Queue<BTree> bTreeQueue1 = new LinkedList<>();
        Queue<BTree> bTreeQueue2 = new LinkedList<>();

        bTreeQueue1.offer(bTree1);
        bTreeQueue2.offer(bTree2);

        while (!bTreeQueue1.isEmpty() && !bTreeQueue2.isEmpty()) {

            BTree node1 = bTreeQueue1.poll();
            BTree node2 = bTreeQueue2.poll();

            if ((node1.getLeft() != null && node2.getLeft() == null) || (node1.getLeft() == null && node2.getLeft() != null)) {
                return false;
            }
            if ((node1.getRight() != null && node2.getRight() == null) || (node1.getRight() == null && node2.getRight() != null)) {
                return false;
            }
            if (node1.getLeft() != null && node2.getLeft() != null) {
                bTreeQueue1.offer(node1.getLeft());
                bTreeQueue2.offer(node2.getLeft());
            }
            if (node1.getRight() != null && node2.getRight() != null) {
                bTreeQueue1.offer(node1.getRight());
                bTreeQueue2.offer(node2.getRight());
            }
        }
        return true;
    }

    //Maximum width of BTree
    public static int widthOfBTree(BTree bTree) {

        int diameter = 0;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);
        bTreeQueue.offer(null);

        int count = 0;
        while (!bTreeQueue.isEmpty()) {
            BTree node = bTreeQueue.poll();
            if (node != null) {
                if (node.getLeft() != null) {
                    bTreeQueue.offer(node.getLeft());
                    count++;
                }
                if (node.getRight() != null) {
                    bTreeQueue.offer(node.getRight());
                    count++;
                }
            } else {

                if (count > diameter) {
                    diameter = count;
                }
                count = 0; //Reset Count
                if (!bTreeQueue.isEmpty()) {
                    bTreeQueue.offer(null);
                }
            }
        }
        return diameter;
    }

    //Diameter of BTree
    public static int diameterOfBTree(BTree bTree) {

        if (bTree == null) {
            return 0;
        }

        int leftSubTreeHeight = heightOfBTreeRecursive(bTree.getLeft());
        int rightSubTreeHeight = heightOfBTreeRecursive(bTree.getRight());

        int diameterLeftSubTree = diameterOfBTree(bTree.getLeft());
        int diameterRightSubTree = diameterOfBTree(bTree.getRight());

        //Max of the 3
        // a) Diameter of left subtree
        // b) Diameter of right subtree
        // c) left subtree height + right subtree height + 1

        return Math.max((leftSubTreeHeight + rightSubTreeHeight + 1),
                Math.max(diameterLeftSubTree, diameterRightSubTree));

    }

    //Height of BTree - Recursive
    public static int heightOfBTreeRecursive(BTree bTree) {

        if (bTree == null) {
            return 0;
        }

        int leftSubTreeHeight = heightOfBTreeRecursive(bTree.getLeft());
        int rightSubTreeHeight = heightOfBTreeRecursive(bTree.getRight());

        return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
    }

    //Level with maximum Sum
    public static int levelWithMaximumSum(BTree bTree) {

        int level = 0;
        int currentLevel = 0;
        int sum = Integer.MIN_VALUE;
        int levelSum = Integer.MIN_VALUE;
        Queue<BTree> bTreeQueue = new LinkedList<>();
        bTreeQueue.offer(bTree);
        bTreeQueue.offer(null);

        while (!bTreeQueue.isEmpty()) {

            BTree node = bTreeQueue.poll();

            if (node != null) {
                levelSum += node.getData();
                if (node.getLeft() != null) {
                    bTreeQueue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    bTreeQueue.offer(node.getRight());
                }
            } else {
                currentLevel++;
                if (levelSum > sum) {
                    sum = levelSum;
                    level = currentLevel;
                }
                levelSum = 0;
                if (!bTreeQueue.isEmpty()) {
                    bTreeQueue.offer(null);
                }
            }
        }
        return level;
    }

    //Print root to leaf paths
    public static void printLeafToRootPaths(BTree bTree) {

        int[] path = new int[1000]; //All Paths
        printLeafToRootPathsRecursive(bTree, path, 0);
    }

    private static void printLeafToRootPathsRecursive(BTree bTree, int[] path, int pathLen) {

        if (bTree == null) {
            return;
        }

        path[pathLen] = bTree.getData();
        pathLen++;

        if (bTree.getLeft() == null && bTree.getRight() == null) {
            printBTreePath(path, pathLen);
        } else {
            printLeafToRootPathsRecursive(bTree.getLeft(), path, pathLen);
            printLeafToRootPathsRecursive(bTree.getRight(), path, pathLen);
        }
    }

    private static void printBTreePath(int[] path, int pathlen) {
        for (int i = 0; i < pathlen; i++) {
            System.out.print(path[i] + " -- ");
        }
        System.out.println();
    }

    //*** Existence of path from root to any node with a Sum
    public static boolean isSumExistsInAPath(BTree bTree, int sum) {

        if (sum == 0) {
            return true;
        }
        if (sum < 0 || bTree == null) {
            return false;
        }
        if (sum == bTree.getData()) {
            return true;
        }

        boolean isSumExistsLeftTree = isSumExistsInAPath(bTree.getLeft(), sum - bTree.getData());
        boolean isSumExistsRightTree = isSumExistsInAPath(bTree.getRight(), sum - bTree.getData());

        return isSumExistsLeftTree || isSumExistsRightTree;
    }

    //Sum of all elements in bTree - Iterative
    public static int sumOfAllElements(BTree bTree) {

        if (bTree == null) {
            return 0;
        }

        return bTree.getData() + sumOfAllElements(bTree.getLeft()) + sumOfAllElements(bTree.getRight());
    }

    //Construct a Mirror of the Tree
    private static BTree mirrorOfBTree(BTree bTree) {

        if (bTree != null) {
            mirrorOfBTree(bTree.getLeft());
            mirrorOfBTree(bTree.getRight());

            BTree tmp = bTree.getLeft();
            bTree.setLeft(bTree.getRight());
            bTree.setRight(tmp);
        }
        return bTree;
    }

    //Check if two trees a Mirror of the each other
    public static boolean twoTreesMirrorOfEachOther(BTree bTree1, BTree bTree2) {

        if (bTree1 == null && bTree2 == null) {
            return true;
        }
        if (bTree1 == null || bTree2 == null) { //Only one null
            return false;
        } else {
            boolean isLeftSubTreeSame = twoTreesMirrorOfEachOther(bTree1.getLeft(), bTree2.getRight());
            boolean isRightSubTreeSame = twoTreesMirrorOfEachOther(bTree1.getRight(), bTree2.getLeft());
            return isLeftSubTreeSame && isRightSubTreeSame && bTree1.getData() == bTree2.getData();
        }
    }



    //Utility - Push Node and all its Left Nodes (if exist)
    public static void pushCurrentNodeAndAllLeftNodes(BTree bTree, Stack<BTree> bTreeStack) {

        while (bTree != null) {
            bTreeStack.push(bTree);
            bTree = bTree.getLeft();
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

        BTree simpleBTree = TreeOperations.constructBTreeSimple();
        TreeOperations.treeOperations(simpleBTree);

        BTree normalBTree = TreeOperations.constructBTree();
        TreeOperations.treeOperations(normalBTree);

        BTree complexBTree = TreeOperations.constructBTreeComplex();
        TreeOperations.treeOperations(complexBTree);
    }

    public static void treeOperations(BTree bTree) {

//        TreeOperations.insertElementBTree(bTree, 25);
        System.out.println("Pre Order Traversals");
        TreeOperations.preOrderRecursive(bTree);
        System.out.println();
        TreeOperations.preOrderIterative(bTree);
        System.out.println();

        System.out.println("In Order Traversals");
        TreeOperations.inOrderRecursive(bTree);
        System.out.println();
        TreeOperations.inOrderIterative(bTree);
        System.out.println();

        System.out.println("Post Order Traversals");
        TreeOperations.postOrderRecursive(bTree);
        System.out.println();
        TreeOperations.postOrderIterative2Stacks(bTree);
        System.out.println();
        TreeOperations.postOrderIterative2StacksCustom(bTree);
        System.out.println();
        TreeOperations.postOrderIterative1Stack(bTree);
        System.out.println();

        System.out.println("Level Order Traversal");
        TreeOperations.levelOrderTraversal(bTree);
        System.out.println();

        System.out.println("Level Order Traversal Reverse");
        TreeOperations.levelOrderReverseOrder(bTree);
        System.out.println();

        System.out.println("Maximum in BTree Recursive " + TreeOperations.maximumInBTreeRecursive(bTree));
        System.out.println("Maximum in BTree Iterative " + TreeOperations.maximumInBTreeIterative(bTree));

        System.out.println("is element 16 in  BTree Recursive ? " + TreeOperations.searchingElementRecursive(bTree, 16));
        System.out.println("is element 7 in  BTree Recursive ? " + TreeOperations.searchingElementRecursive(bTree, 7));

        System.out.println("Size of BTree Recursive " + TreeOperations.sizOfBTreeRecursive(bTree));
        System.out.println("Size of BTree Iterative " + TreeOperations.sizeOfBTreeIterative(bTree));
        System.out.println("Height of BTree Iterative " + TreeOperations.heightOfBTreeIterative(bTree));

        System.out.println("Minimum Height of BTree " + TreeOperations.minimumHeightBTree(bTree));
        System.out.println("Deepest Node of BTree " + TreeOperations.deepestNodeOfbTree(bTree).getData());
        System.out.println("Number Of leaf Nodes of BTree " + TreeOperations.numberOfLeafNodes(bTree));
        System.out.println("Number Of Full Nodes of BTree " + TreeOperations.numberOfFullNodesInBTree(bTree));
        System.out.println("Number Of Half Nodes of BTree " + TreeOperations.numberOfHalfNodesInBTree(bTree));
        System.out.println("Is Structurally identical to itself Recursive " + TreeOperations.isStructurallyIdenticalRecursive(bTree, bTree));
        BTree node1 = new BTree(1);
        BTree node2 = new BTree(2);
        node1.setLeft(node2);
        System.out.println("Is Structurally identical to Other Tree Recursive " + TreeOperations.isStructurallyIdenticalRecursive(bTree, node1));

        System.out.println("Is Structurally identical to itself Iterative " + TreeOperations.isStructurallyIdenticalIterative(bTree, bTree));
        System.out.println("Is Structurally identical to Other Tree Iterative " + TreeOperations.isStructurallyIdenticalIterative(bTree, node1));
        System.out.println("Width of Tree " + TreeOperations.widthOfBTree(bTree));
        System.out.println("Diameter of Tree " + TreeOperations.diameterOfBTree(bTree));
        System.out.println("Level with Maximum sum of Tree " + TreeOperations.levelWithMaximumSum(bTree));
        System.out.println("Paths from Root -> ");
        TreeOperations.printLeafToRootPaths(bTree);
        //Should Exist in simple and complex
        System.out.println("Is Sum Exists in Path " + TreeOperations.isSumExistsInAPath(bTree, 10));
        System.out.println("Sum of All Elements " + TreeOperations.sumOfAllElements(bTree));
        System.out.println("Mirror of the Tree ");
        TreeOperations.levelOrderTraversal(mirrorOfBTree(bTree));
        System.out.println();

        BTree node3 = new BTree(1);
        BTree node4 = new BTree(2);
        node3.setRight(node4);

        System.out.println("Is two BTrees Mirrors of each other - Should be true " + TreeOperations.twoTreesMirrorOfEachOther(node1, node3));
        System.out.println("Is two BTrees Mirrors of each other - Should be true " + TreeOperations.twoTreesMirrorOfEachOther(bTree, mirrorOfBTree(bTree)));
        System.out.println("Is two BTrees Mirrors of each other - Should be false " + TreeOperations.twoTreesMirrorOfEachOther(bTree, node1));
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
