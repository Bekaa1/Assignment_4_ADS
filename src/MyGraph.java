import java.util.*;

public class MyGraph<V> implements Graph<V> {
    // Represents whether the graph is undirected or not
    private final boolean undirected;

    // A map that stores the vertices and their adjacent vertices
    private final Map<V, List<V>> map = new HashMap<>();

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    @Override
    public void addVertex(V v) {
// If the vertex already exists, do nothing
        if (hasVertex(v))
            return;

// Add the vertex to the map with an empty list of adjacent vertices
        map.put(v, new LinkedList<>());
    }

    @Override
    public void addEdge(V source, V dest) {
// If the source vertex doesn't exist, add it
        if (!hasVertex(source))
            addVertex(source);

// If the destination vertex doesn't exist, add it
        if (!hasVertex(dest))
            addVertex(dest);

// If the edge already exists or is a self-loop, do nothing
        if (hasEdge(source, dest) || source.equals(dest))
            return; // reject parallels & self-loops

// Add the destination vertex to the list of adjacent vertices for the source vertex
        map.get(source).add(dest);

// If the graph is undirected, add the source vertex to the list of adjacent vertices for the destination vertex
        if (undirected)
            map.get(dest).add(source);
    }

    public int getVerticesCount() {
// Return the number of vertices in the map
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;

// Iterate over the vertices in the map
        for (V v : map.keySet()) {
// Add the number of adjacent vertices for the current vertex to the count
            count += map.get(v).size();
        }

// If the graph is undirected, divide the count by 2 to avoid double-counting edges
        if (undirected)
            count /= 2;

// Return the count of edges
        return count;
    }

    public boolean hasVertex(V v) {
// Return whether the map contains the given vertex
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
// If the source vertex doesn't exist, return false
        if (!hasVertex(source)) return false;

// Return whether the list of adjacent vertices for the source vertex contains the destination vertex
        return map.get(source).contains(dest);
    }

    @Override
    public List<V> adjacencyList(V v) {
// If the vertex doesn't exist, return null
        if (!hasVertex(v)) return null;

// Return the list of adjacent vertices for the given vertex
        return map.get(v);
    }
}