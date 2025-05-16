import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private final Map<T, T> edgeTo = new HashMap<>();
    private final Set<T> marked = new HashSet<>();
    private final T start;

    public BreadthFirstSearch(UnweightedGraph<T> graph, T start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(UnweightedGraph<T> graph, T current) {
        Queue<T> queue = new LinkedList<>();
        marked.add(current);
        queue.offer(current);

        while (!queue.isEmpty()) {
            T v = queue.poll();
            for (T w : graph.getAdjVertices(v)) {
                if (!marked.contains(w)) {
                    marked.add(w);
                    edgeTo.put(w, v);
                    queue.offer(w);
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
        LinkedList<T> path = new LinkedList<>();
        for (T x = key; !x.equals(start); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
