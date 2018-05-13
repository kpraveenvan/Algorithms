package org.praveen.graphs;

public class GraphUsingAdjacencyMatrix {

    boolean adjMatrix[][];
    int vertexCount;

    public GraphUsingAdjacencyMatrix(int vertexCount) {
        this.vertexCount = vertexCount;
        adjMatrix = new boolean[vertexCount][vertexCount];
    }

    public void addEdge(int vertex1, int vertex2) {
        if (verifyVertices(vertex1, vertex2)) {
            adjMatrix[vertex1][vertex2] = true;
            adjMatrix[vertex2][vertex1] = true;
        }
    }

    public void removeEdge(int vertex1, int vertex2) {
        if (verifyVertices(vertex1, vertex2)) {
            adjMatrix[vertex1][vertex2] = false;
            adjMatrix[vertex2][vertex1] = false;
        }
    }

    public boolean isEdge(int vertex1, int vertex2) {
        if (verifyVertices(vertex1, vertex2)) {
            return adjMatrix[vertex1][vertex2];
        } else {
            return false;
        }
    }

    public boolean verifyVertices(int vertex1, int vertex2) {
        return vertex1 < vertexCount && vertex2 < vertexCount && vertex1 >= 0 && vertex2 >= 0;
    }
}
