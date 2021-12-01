import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class RandomMinWeightTree {
    public static ArrayList<Arc> generateTree(Graph graph, int start) {
        ArrayList<Arc> arcs = new ArrayList<>();
        Random rand = new Random();

        // random weights
        for (Edge edge : graph) {
            edge.weight = rand.nextFloat();
        }

        // Initialize
        double[] cost = new double[graph.order];
        int[] pred = new int[graph.order];
        for (int k = 0; k < graph.order; k++) {
            cost[k] = Double.MAX_VALUE;
            pred[k] = -1;
        }

        cost[start] = 0;
        pred[start] = start;

        // order vertices by cost
        PriorityQueue<Integer> vertices = new PriorityQueue<>((Comparator.comparingDouble(o -> cost[o])));
        for (int k = 0; k < graph.order; k++) {
            vertices.add(k);
        }

        while (! vertices.isEmpty()) {
            int vertex = vertices.poll();
            for (Arc arc : graph.outNeighbours(vertex)) {
                if (! vertices.contains(arc.getDest())) continue;
                if (cost[arc.getDest()] >= arc.support.weight) {
                    pred[arc.getDest()] = vertex;
                    cost[arc.getDest()] = arc.support.weight;

                    // update destination's position in queue
                    vertices.remove(arc.getDest());
                    vertices.add(arc.getDest());
                }
            }
        }

        for (int k = 0; k < graph.order; k++) {
            if (pred[k] == -1) continue;
            arcs.add(new Arc(new Edge(pred[k], k, 1), false));
        }

        return arcs;
    }
}
