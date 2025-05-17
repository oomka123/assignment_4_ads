import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private final Map<T, T> edgeTo = new HashMap<>();
    private final Set<T> marked = new HashSet<>();
    private final T start;

    public BreadthFirstSearch(WeightedGraph<T> graph, T start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(WeightedGraph<T> graph, T root) {
        Queue<T> queue = new LinkedList<>();
        marked.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            for (Vertex<T> neighbor : graph.getAdjacencyVertices(current).keySet()) {
                T data = neighbor.getData();
                if (!marked.contains(data)) {
                    marked.add(data);
                    edgeTo.put(data, current);
                    queue.add(data);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(T key) {
        return marked.contains(key);
    }

    @Override
    public List<T> pathTo(T key) {
        if (!hasPathTo(key)) return null;
        List<T> path = new ArrayList<>();
        for (T x = key; !x.equals(start); x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
