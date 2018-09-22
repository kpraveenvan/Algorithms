package org.praveen.graphs;


import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSortUsingStack {

    private int vertexCount;
    private LinkedList<Integer> adjListArray[];
    private Stack<Integer> stack;
    private boolean visited[];

    public TopologicalSortUsingStack(int vertexCount) {
        this.vertexCount = vertexCount;
        adjListArray = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adjListArray[i] = new LinkedList<>();
        }
        visited = new boolean[vertexCount];
        stack = new Stack<Integer>();
    }

    private void addEdge(int src, int dest) {

        if (verifyVertices(src, dest)) {
            adjListArray[src].addFirst(dest);
        }
    }

    private boolean verifyVertices(int src, int dest) {

        return src >= 0 && dest >= 0 && src < vertexCount && dest < vertexCount;
    }

    private void topologicalSortUtil(int node) {

        visited[node] = true;

        for (int i : adjListArray[node]) { //if it has any adjacency node and if those are not visited Then start topological sort from that vertex.
            if (!visited[i]) {
                topologicalSortUtil(i);
            }
        }

        stack.push(node);
    }

    private void topologicalSort() {

        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) { //If not visited start topological sort from that node
                topologicalSortUtil(i);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(" " + stack.pop());
        }
    }

    public static void main(String[] args) {

        TopologicalSortUsingStack graph = new TopologicalSortUsingStack(6);

        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 1);
        graph.addEdge(4, 0);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);

        graph.topologicalSort();

    }

}
