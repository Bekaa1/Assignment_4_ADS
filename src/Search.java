// Interface for search algorithms that find a path between two vertices in a graph.
public interface Search<V> {

    // Returns an iterable of vertices that form a path from the starting vertex to the destination vertex.
    Iterable<V> pathTo(V destinationData);
}
