import java.util.*;

public class Main {

    public static void main(String[] args) {
// Create a new weighted graph with bidirectional edges
        WeightedGraph<String> weightedGraph = new WeightedGraph<>(true);

// Populate the weighted graph with vertices and edges
        populateWithWeights(weightedGraph);

// Use Dijkstra's algorithm to find the shortest path from Almaty to Kyzylorda
        System.out.println("Dijkstra's algorithm:");
        Search<String> djk = new DijkstraSearch<>(weightedGraph, "Almaty");
        printPath(djk, "Kyzylorda");

// Print a separator
        System.out.println("--------------------------------");

// Create a new unweighted graph with bidirectional edges
        MyGraph<String> unweightedGraph = new MyGraph<>(true);

// Populate the unweighted graph with vertices and edges
        populateWithoutWeights(unweightedGraph);

// Use breadth-first search to find a path from Almaty to Kyzylorda
        System.out.println("Breadth-first search:");
        Search<String> bfs = new BreadthFirstSearch<>(unweightedGraph, "Almaty");
        printPath(bfs, "Kyzylorda");
    }

    public static void populateWithoutWeights(Graph<String> graph) {
// Add edges to the graph
        graph.addEdge("Almaty", "Astana");
        graph.addEdge("Shymkent", "Atyrau");
        graph.addEdge("Atyrau", "Astana");
        graph.addEdge("Almaty", "Shymkent");
        graph.addEdge("Shymkent", "Atana");
        graph.addEdge("Astana", "Kostanay");
        graph.addEdge("Shymkent", "Kyzylorda");
    }

    public static void populateWithWeights(WeightedGraph<String> graph) {
// Add edges with weights to the graph
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    public static void printPath(Search<String> search, String key) {
// Get the path from the search algorithm
        Iterable<String> path = search.pathTo(key);

// If the path is not null and not empty, print it
        if (path != null && path.iterator().hasNext()) {
            for (String v : path) {
                System.out.print(v + " -> ");
            }
            System.out.println();
        } else {
// If the path is null or empty, print a message
            System.out.println("No path found");
        }
    }
}