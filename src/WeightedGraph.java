import java.util.*;

public class WeightedGraph<T> {
    private final boolean directed;
    private final Map<Vertex<T>, Map<Vertex<T>, Double>> adjVertices = new HashMap<>();

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(T data) {
        adjVertices.putIfAbsent(new Vertex<>(data), new HashMap<>());
    }

    public void addEdge(T from, T to, double weight) {
        Vertex<T> vFrom = new Vertex<>(from);
        Vertex<T> vTo = new Vertex<>(to);
        addVertex(from);
        addVertex(to);
        adjVertices.get(vFrom).put(vTo, weight);
        if (!directed) {
            adjVertices.get(vTo).put(vFrom, weight);
        }
    }

    public Set<Vertex<T>> getVertices() {
        return adjVertices.keySet();
    }

    public Map<Vertex<T>, Double> getNeighbors(Vertex<T> vertex) {
        return adjVertices.getOrDefault(vertex, new HashMap<>());
    }
}
