import java.util.List;

public interface Graph<V> {
    void addVertex(V v);
    // The direction of the edge depends on the implementation of the graph.

    void addEdge(V source, V dest);
    // The list is empty if the vertex does not exist or has no adjacent vertices.

    List<V> adjacencyList(V v);

}

