package org.praveen.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShortestPathUnweightedGraph {

    private int vertexCount;
    private LinkedList<Integer> adjListArray[];
    private boolean visited[];
    private int predecessor[];
    private int distance[];
    private Queue<Integer> queue;

    public ShortestPathUnweightedGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        adjListArray = new LinkedList[vertexCount];
        distance = new int[vertexCount];
        predecessor = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adjListArray[i] = new LinkedList<>();
            distance[i] = -1; //Not calculated
            predecessor[i] = -1; //Not Calculated
        }
        visited = new boolean[vertexCount];
        queue = new LinkedList<Integer>();
    }

    private void addEdge(int src, int dest) {

        if (isValidVertices(src, dest)) {
            adjListArray[src].addFirst(dest);
            adjListArray[dest].addFirst(src);
        }
    }

    private boolean isValidVertices(int src, int dest) {

        return src >= 0 && dest >= 0 && src < vertexCount && dest < vertexCount;
    }

    public boolean modifiedBfsForShortestDistance(int src, int dest) {

        distance[src] = 0; //Distance from itself = 0;
        queue.offer(src);
        while (!queue.isEmpty()) {

            int node = queue.poll();
            visited[node] = true;

            for (int i : adjListArray[node]) { //add all the adjacent vertices into the queue if they are not visited
                if (!visited[i]) {
                    visited[i] = true;
                    distance[i] = distance[node] + 1; //Distance of the adjacence of the node = 1 + distance of the Node.
                    predecessor[i] = node; //Predecessor is the node itself.
                    queue.offer(i);

                    if (i == dest) { //Destination is found
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printShortestDistance(int src, int dest) {

        if (modifiedBfsForShortestDistance(src, dest)) {

            //Fallow the predecessor's predecessor from destination to source.

            Stack<Integer> stack = new Stack<>();
            stack.push(dest);

            int temp = dest;
            while (temp != src) {
                temp = predecessor[temp];
                stack.push(temp);
            }
            System.out.print("Path from Src to Destination -> ");
            while (!stack.isEmpty()) {
                System.out.print(" " + stack.pop());
            }

            System.out.println(" Distance from Src to Destination -> " + distance[dest]);
        } else {

            //No Path found
        }
    }

//    public void bfs(int src) {
//
//        queue.offer(src);
//        while (!queue.isEmpty()) {
//
//            int node = queue.poll();
//            visited[node] = true;
//            System.out.print(" " + node);
//
//            for(int i : adjListArray[node]) { //add all the adjacent vertices into the queue if they are not visited
//                if(!visited[i]) {
//                    visited[i] = true;
//                    queue.offer(i);
//                }
//            }
//        }
//    }

    public static void main(String[] args) {

        ShortestPathUnweightedGraph graph = new ShortestPathUnweightedGraph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(3, 7);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

//        graph.bfs(0);
        graph.printShortestDistance(2, 6);
    }
}
