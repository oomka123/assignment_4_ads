import java.util.*;

public class UnweightedGraph<T> {
    private final boolean directed;
    private final Map<T, List<T>> adjVertices = new HashMap<>();

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(T data) {
        adjVertices.putIfAbsent(data, new ArrayList<>());
    }

    public void addEdge(T from, T to) {
        addVertex(from);
        addVertex(to);
        adjVertices.get(from).add(to);
        if (!directed) {
            adjVertices.get(to).add(from);
        }
    }

    public List<T> getAdjVertices(T data) {
        return adjVertices.getOrDefault(data, new ArrayList<>());
    }
}
