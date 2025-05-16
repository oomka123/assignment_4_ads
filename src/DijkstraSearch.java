import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private final Map<T, T> edgeTo = new HashMap<>();
    private final Map<T, Double> distTo = new HashMap<>();
    private final T start;

    public DijkstraSearch(WeightedGraph<T> graph, T start) {
        this.start = start;
        for (Vertex<T> v : graph.getVertices()) {
            distTo.put(v.getData(), Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);

        PriorityQueue<Vertex<T>> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.get(v.getData())));
        pq.add(new Vertex<>(start));

        while (!pq.isEmpty()) {
            Vertex<T> current = pq.poll();
            for (Map.Entry<Vertex<T>, Double> entry : graph.getNeighbors(current).entrySet()) {
                T neighbor = entry.getKey().getData();
                double weight = entry.getValue();
                double newDist = distTo.get(current.getData()) + weight;
                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current.getData());
                    pq.add(new Vertex<>(neighbor));
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(T key) {
        return distTo.containsKey(key) && distTo.get(key) < Double.POSITIVE_INFINITY;
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
