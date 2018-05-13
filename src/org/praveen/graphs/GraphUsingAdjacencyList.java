package org.praveen.graphs;

import java.util.LinkedList;

public class GraphUsingAdjacencyList {

    LinkedList<Integer> adjListArray[]; //Array of LinkedLists, each List represent the list of nodes it can reach directly
    int vertexCount;

    public GraphUsingAdjacencyList(int vertexCount) {
        this.vertexCount = vertexCount;
        adjListArray = new LinkedList[vertexCount]; //Initialize the Array.
        for (int i = 0; i < vertexCount; i++) {
            adjListArray[i] = new LinkedList<Integer>(); //Initialize each Linked List in the Array.
        }
    }

    public void addEdge(int src, int dest) {

        if (verifyVertices(src, dest)) {

            //Add dest at the src index (to the Linked List at the begining)
            adjListArray[src].addFirst(dest);

            //Since this is a undirected graph
            adjListArray[dest].addFirst(src);
        }
    }

    public boolean isEdge(int src, int dest) {
        if (verifyVertices(src, dest)) {
            return adjListArray[src].contains(dest);
        } else {
            return false;
        }
    }

    public boolean verifyVertices(int src, int dest) {
        return src >= 0 && dest >= 0 && src < vertexCount && dest < vertexCount;
    }
}
