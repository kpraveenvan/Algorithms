package org.praveen.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BreathFirstSearchAdjacencyList {

    private int vertexCount;
    private LinkedList<Integer> adjListArray[];
    private boolean visited[];
    private Queue<Integer> queue;

    public BreathFirstSearchAdjacencyList(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjListArray = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adjListArray[i] = new LinkedList<>();
        }
        this.visited = new boolean[vertexCount];
        this.queue = new LinkedList<>();
    }

    private void addEdge(int src, int dest) {

        if (isValidNodes(src, dest)) {
            adjListArray[src].addFirst(dest); //Add nodes at the src Index.
            adjListArray[dest].addFirst(src); //since the graph is bidirectional
        }
    }

    private void bfs(int source) {

        queue.offer(source);
        while (!queue.isEmpty()) {

            int next = queue.poll();  //Get an element from the queue
            visited[next] = true;    //Mark it as Visited and print the element
            System.out.print(" " + next);

            for (int i : adjListArray[next]) { //Add all its non visited child
                if (!visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    private boolean isValidNodes(int src, int dest) {
        return src >= 0 && dest >= 0 && src < vertexCount && dest < vertexCount;
    }

    public static void main(String[] args) {
        BreathFirstSearchAdjacencyList graph = new BreathFirstSearchAdjacencyList(8);
        graph.addEdge(0, 1);
        graph.addEdge(1, 7);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(4, 7);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);

        graph.bfs(0);

    }
}
