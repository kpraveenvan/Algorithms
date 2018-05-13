package org.praveen.graphs;


import java.util.LinkedList;

public class DepthFirstSearchRecursive {

    int vertexCount;
    LinkedList<Integer> adjListArray[];
    boolean visited[];

    public DepthFirstSearchRecursive(int vertexCount) {
        this.vertexCount = vertexCount;
        adjListArray = new LinkedList[vertexCount]; //Initialize the Array
        for (int i = 0; i < vertexCount; i++) {
            adjListArray[i] = new LinkedList<>(); //Initialize each list in the Array
        }
        this.visited = new boolean[vertexCount];
    }

    public void addEdge(int src, int dest) {
        if (isValidEdge(src, dest)) {

            adjListArray[src].addFirst(dest); //Add the destination to the list
            adjListArray[dest].addFirst(src); //Since we have undirectional Graph
        }
    }


    public boolean isValidEdge(int src, int dest) {

        return src >= 0 && dest >= 0 && src < vertexCount && dest < vertexCount;
    }

    public void dfs(int src) {

        System.out.print(" " + src); //Print and mark the node as visited.
        visited[src] = true;

        for (int i : adjListArray[src]) { //find its immediate nodes
            if (!visited[i]) { //if the edge is not visited
                dfs(i);    //Start the dfs again with that edge
            }
        }
    }

    public static void main(String[] args) {

        DepthFirstSearchRecursive graph = new DepthFirstSearchRecursive(8);
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
