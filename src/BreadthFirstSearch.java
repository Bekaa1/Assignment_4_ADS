import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {

    // This map stores the edge from each vertex to its parent in the BFS tree.
    private Map<V, V> edgeTo;

    // This variable stores the source vertex for the BFS search.
    private V source;

    public BreadthFirstSearch(Graph<V> graph, V sourceData) {

        // Initialize the edgeTo map.
        this.edgeTo = new HashMap<>();

        // Set the source vertex.
        this.source = sourceData;

        // Perform the BFS search.
        bfs(graph, source);
    }

    private void bfs(Graph<V> graph, V source) {

        // Initialize a queue to store the vertices to be visited.
        Queue<V> queue = new LinkedList<>();

        // Initialize a set to store the visited vertices.
        Set<V> visited = new HashSet<>();

        // Add the source vertex to the queue and mark it as visited.
        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {

            // Dequeue the next vertex to be visited.
            V current = queue.poll();


            for (V neighbor : graph.adjacencyList(current)) {
                if (!visited.contains(neighbor)) {
                    edgeTo.put(neighbor, current);
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    // This method returns the path from the source vertex to a given destination vertex, or an empty list if no path exists.
    @Override
    public Iterable<V> pathTo(V destinationData) {

        // Initialize an empty list to store the path.
        List<V> path = new ArrayList<>();

        // If the destination vertex is not in the BFS tree or is not the source vertex, return an empty list.
        if (!edgeTo.containsKey(destinationData) && !destinationData.equals(source)) {
            return path; //Path not found
        }

        for (V vertex = destinationData; vertex != null; vertex = edgeTo.get(vertex)) {
            path.add(vertex);
        }

        // Reverse the path and return it.
        Collections.reverse(path);
        return path;
    }
}
