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
            for (Arc neighbor : graph.outNeighbours(vertex)) {
                if (! visited[neighbor.getDest()]) {
                    vertices.addFirst(neighbor.getDest());
                    visited[neighbor.getDest()] = true;
                    arcs.add(neighbor);
                }
            }
        }
        return arcs;
    }
}
