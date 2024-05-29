import java.util.*;


public class DijkstraSearch<V> implements Search<V> {

    private Map<Vertex<V>, Vertex<V>> edgeTo;

    private Map<Vertex<V>, Double> distTo;

    // This priority queue is used to store the vertices in the graph in order of their distance from the source vertex.
    private PriorityQueue<Vertex<V>> pq;

    // This constructor initializes the Dijkstra's shortest path algorithm for a given weighted graph and source vertex.
    public DijkstraSearch(WeightedGraph<V> graph, V sourceData) {

        // Initialize the edgeTo and distTo maps.
        this.edgeTo = new HashMap<>();
        this.distTo = new HashMap<>();

        this.pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));

        // Get the source vertex from the graph.
        Vertex<V> source = graph.getVertex(sourceData);

        for (Vertex<V> vertex : graph.getAllVertices()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        // Add the source vertex to the priority queue.
        pq.offer(source);

        dijkstra(graph);
    }

    // This method implements the Dijkstra's shortest path algorithm.
    private void dijkstra(WeightedGraph<V> graph) {

        // While the priority queue is not empty, process the vertex with the smallest distance from the source vertex.
        while (!pq.isEmpty()) {

            Vertex<V> current = pq.poll();

            // For each adjacent vertex of the current vertex, check if the distance of the adjacent vertex can be improved by going through the current vertex.
            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacent().entrySet()) {

                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();

                // Calculate the new distance of the adjacent vertex by going through the current vertex.
                double newDist = distTo.get(current) + weight;

                // If the new distance is smaller than the current distance of the adjacent vertex, update the distance and the predecessor of the adjacent vertex, and add the adjacent vertex to the priority queue.
                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.offer(neighbor);
                }
            }
        }
    }

    // This method returns the shortest path from the source vertex to a given destination vertex.
    @Override
    public Iterable<V> pathTo(V destinationData) {

        // Initialize an empty list to store the path.
        List<V> path = new ArrayList<>();

        // Get the destination vertex from the graph.
        Vertex<V> destination = edgeTo.keySet().stream()
                .filter(v -> v.getData().equals(destinationData))
                .findFirst().orElse(null);

        if (destination == null || distTo.get(destination) == Double.POSITIVE_INFINITY) return path;

        // Traverse the path from the destination vertex to the source vertex by following the predecessor vertices in the edgeTo map, and add the vertices to the path list.
        for (Vertex<V> vertex = destination; vertex != null; vertex = edgeTo.get(vertex)) {
            path.add(vertex.getData());
        }

        // Reverse the path list and return it.
        Collections.reverse(path);
        return path;
    }
}
