import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class RandomBFS {
    public static ArrayList<Arc> generateTree(Graph graph, int start) {
        ArrayList<Arc> arcs = new ArrayList<>();
        boolean[] visited = new boolean[graph.order];
        for(int i = 0; i < graph.order; i++) {
            visited[i] = false;
        }

        LinkedList<Integer> vertices = new LinkedList<>();
        vertices.addFirst(start);
        visited[start] = true;

        while (! vertices.isEmpty()) {
            int vertex = vertices.pollLast();
            for (Arc neighbor : graph.outNeighbours(vertex)) {
                if (! visited[neighbor.getDest()]) {
                    int index = new Random().nextInt(vertices.size() + 1);
                    vertices.add(index, neighbor.getDest());
                    visited[neighbor.getDest()] = true;
                    arcs.add(neighbor);
                }
            }
        }
        return arcs;
    }
}
