import java.util.ArrayList;
import java.util.LinkedList;

public class BreadthFirstSearch {
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
            for (Edge neighbor : graph.neighbours(vertex)) {
                if (! visited[neighbor.dest]) {
                    vertices.addFirst(neighbor.dest);
                    visited[neighbor.dest] = true;
                    arcs.add(new Arc(neighbor, false));
                }
            }
        }
        return arcs;
    }
}
