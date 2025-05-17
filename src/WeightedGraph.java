import java.util.*;

public class WeightedGraph<T> {
    private final boolean directed;
    private final Map<T, Vertex<T>> vertices;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addVertex(T data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(T source, T dest, double weight) {
        addVertex(source);
        addVertex(dest);
        Vertex<T> v1 = vertices.get(source);
        Vertex<T> v2 = vertices.get(dest);
        v1.addAdjacentVertex(v2, weight);
        if (!directed) {
            v2.addAdjacentVertex(v1, weight);
        }
    }

    public Vertex<T> getVertex(T data) {
        return vertices.get(data);
    }

    public Set<T> getAllVertexData() {
        return vertices.keySet();
    }

    public Map<Vertex<T>, Double> getAdjacencyVertices(T data) {
        Vertex<T> vertex = vertices.get(data);
        if (vertex == null) return new HashMap<>();
        return vertex.getAdjacencyVertices();
    }
}
