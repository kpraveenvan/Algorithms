package org.praveen.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSortUsingQueue {

    private int vertexCount;
    private LinkedList<Integer> adjListArray[];
    private int degree[];
    private Queue<Integer> queue;

    public TopologicalSortUsingQueue(int vertexCount) {

        this.vertexCount = vertexCount;
        adjListArray = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adjListArray[i] = new LinkedList<>();
        }
        queue = new LinkedList<>();
        degree = new int[vertexCount];
    }

    private void addEdge(int src, int dest) {

        if (isValidVertices(src, dest)) {
            adjListArray[src].addFirst(dest);
            degree[dest] += 1;
        }
    }

    private boolean isValidVertices(int src, int dest) {

        return src >= 0 && dest >= 0 && src < vertexCount && dest < vertexCount;
    }

    private void topologicalSort() {

        for (int i = 0; i < vertexCount; i++) {
            if (degree[i] == 0) { //Push all the nodes which has 0 indegree
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();  //Remove an element from the queue and print it.
            System.out.print(" " + node);
            for (int i : adjListArray[node]) { //if the element has adjacency vertices, decrease the degree by one
                degree[i] -= 1;
                if (degree[i] == 0) { //if the degree is zero push the element into the queue.
                    queue.offer(i);
                }
            }
        }
    }

    public static void main(String[] args) {

        TopologicalSortUsingQueue graph = new TopologicalSortUsingQueue(6);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 1);
        graph.addEdge(4, 0);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);

        graph.topologicalSort();
    }
}
