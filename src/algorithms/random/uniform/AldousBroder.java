package algorithms.random.uniform;

import graph.structure.Arc;
import graph.structure.Graph;

import java.util.ArrayList;
import java.util.Random;


public class AldousBroder {
    public static ArrayList<Arc> generateTree(Graph graph, int start) {
        ArrayList<Arc> arcs = new ArrayList<>();
        boolean[] visited = new boolean[graph.order];
        for(int i = 0; i < graph.order; i++) {
            visited[i] = false;
        }

        int currentVertex = start;
        visited[start] = true;
        int nb_visited = 1;

        while (nb_visited != graph.order) {
            Arc randomOutNeighbour = graph.outNeighbours(currentVertex).get(new Random().nextInt(graph.outNeighbours(currentVertex).size()));
            currentVertex = randomOutNeighbour.getDest();
            if (! visited[currentVertex]) {
                arcs.add(randomOutNeighbour);
                visited[currentVertex] = true;
                nb_visited++;
            }
        }
        return arcs;
    }
}
