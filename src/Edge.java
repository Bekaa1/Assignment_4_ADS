/**
 * The edge connects two vertices (v and w) and has an associated weight.
 */
public class Edge<V> {
    // The first vertex connected by the edge
    private final V v;
    // The second vertex connected by the edge
    private final V w;
    // presents cost or distance between the vertices
    private final double weight;


    // Constructs an Edge object with the given vertices and weight

    public Edge(V v, V w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    //Returns one of the vertices connected by the edge (either v or w)

    public V either() {
        return v;
    }

    public V other(V vertex) {
        if (vertex.equals(v)) {
            return w;
        } else if (vertex.equals(w)) {
            return v;
        } else {
            throw new RuntimeException("Incorrect vertex");
        }
    }

    public double weight() {
        return weight;
    }
}