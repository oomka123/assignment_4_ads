import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Vertex<T> {
    private T data;
    private Map<Vertex<T>, Double> adjacencyVertices;

    public Vertex(T data) {
        this.data = data;
        this.adjacencyVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<T> dest, Double weight) {
        adjacencyVertices.put(dest, weight);
    }

    public T getData() {
        return data;
    }

    public Map<Vertex<T>, Double> getAdjacencyVertices() {
        return adjacencyVertices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex<?> vertex)) return false;
        return Objects.equals(data, vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
