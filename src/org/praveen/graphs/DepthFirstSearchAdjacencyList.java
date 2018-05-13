package org.praveen.graphs;


import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearchAdjacencyList {

    int vertexCount;
    LinkedList<Integer> adjListArray[];
    boolean visited[]; //Visited Array
    Stack<Integer> stack = new Stack<>();

    public DepthFirstSearchAdjacencyList(int vertexCount) {

        this.vertexCount = vertexCount;
        this.visited = new boolean[vertexCount]; //Initially all vertices are not visited
        adjListArray = new LinkedList[vertexCount]; //Initialize the Array
        for (int i = 0; i < vertexCount; i++) {
            adjListArray[i] = new LinkedList<Integer>(); //Initialize each List
        }
    }

    public void addEdge(int src, int dest) {

        if (verifyNodes(src, dest)) {
            //Add Dest at the src index
            adjListArray[src].addFirst(dest);

            //Since the graph is bidirectional
            adjListArray[dest].addFirst(src);
        }
    }

    public boolean verifyNodes(int src, int dest) {
        return src >= 0 && dest >= 0 && src < vertexCount && dest < vertexCount;
    }

    public void dfs(int source) {

        System.out.print(" " + source);
        visited[source] = true;
        stack.push(source);
        while (!stack.isEmpty()) {

            int node = stack.peek(); //look at the node
            int nonVisitedNode = getAdjacentNonVisitedVertex(node); //get the closest non visited node
            if (nonVisitedNode != -1) {
                stack.push(nonVisitedNode); //Push the non visited Node.
                visited[nonVisitedNode] = true; //Mark that node as visited.
                System.out.print(" " + nonVisitedNode);
            } else { //If it has no non visited node
                stack.pop(); //pop from the stack
            }
        }
    }

    public int getAdjacentNonVisitedVertex(int src) {

        for (int i : adjListArray[src]) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1; // There is no node that is non visited.
    }

    public static void main(String[] args) {
        DepthFirstSearchAdjacencyList graph = new DepthFirstSearchAdjacencyList(8);
        graph.addEdge(0, 1);
        graph.addEdge(1, 7);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(4, 7);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);

        graph.dfs(0);
    }
}
