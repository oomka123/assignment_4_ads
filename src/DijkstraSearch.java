import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private final Map<T, Double> distTo = new HashMap<>();
    private final Map<T, T> edgeTo = new HashMap<>();
    private final Set<T> visited = new HashSet<>();
    private final T start;

    public DijkstraSearch(WeightedGraph<T> graph, T start) {
        this.start = start;
        for (T v : graph.getAllVertexData()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);

        PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            T current = pq.poll();
            if (!visited.add(current)) continue;

            for (Map.Entry<Vertex<T>, Double> entry : graph.getAdjacencyVertices(current).entrySet()) {
                T neighbor = entry.getKey().getData();
                double weight = entry.getValue();
                double newDist = distTo.get(current) + weight;

                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(T key) {
        return distTo.get(key) < Double.POSITIVE_INFINITY;
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
