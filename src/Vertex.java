import java.util.*;

// Represents a vertex in a graph.
public class Vertex<V> {
    // The data associated with this vertex.
    private V data;

    // The map of adjacent vertices and the weights of the edges connecting them.
    private Map<Vertex<V>, Double> adjacentVertices;

    // Creates a new vertex with the given data.
    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    // Returns the data associated with this vertex.
    public V getData() {
        return data;
    }

    // Adds an edge to this vertex with the given destination vertex and weight.
    public void addAdjacent(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    // Returns the map of adjacent vertices and the weights of the edges connecting them.
    public Map<Vertex<V>, Double> getAdjacent() {
        return adjacentVertices;
    }

    // Returns a string representation of this vertex.
    @Override
    public String toString() {
        return data.toString();
    }
}
