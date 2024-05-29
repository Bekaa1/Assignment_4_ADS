import java.util.*;

public class WeightedGraph<V> {
    // The keys are the vertex data, and the values are the Vertex objects that contain the adjacency list.
    private Map<V, Vertex<V>> vertices;

    // Boolean to indicate whether the graph is directed or undirected.
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    // Adds a new vertex to the graph with the given data.
    public void addVertex(V data) {
        if (!vertices.containsKey(data)) {
            vertices.put(data, new Vertex<>(data));
        }
    }

    // If the graph is undirected, the edge is also added
    // in the opposite direction.
    public void addEdge(V sourceData, V destData, double weight) {
        addVertex(sourceData);
        addVertex(destData);
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);
        source.addAdjacent(dest, weight);
        if (!directed) {
            dest.addAdjacent(source, weight);
        }
    }

    // Returns the Vertex object for the given vertex data, or null if the vertex does not exist in the graph.
    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    // Returns a collection of all the Vertex objects in the graph.
    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }
}
