package algorithms.random.uniform;

import graph.structure.Arc;
import graph.structure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Wilson {
    public static ArrayList<Arc> generateTree(Graph graph, int start) {
        ArrayList<Arc> arcs = new ArrayList<>();
        HashSet<Integer> inVertices = new HashSet<>();
        ArrayList<Integer> outVertices = new ArrayList<>();

        inVertices.add(start);
        for (int k = 0; k < graph.order; k++) {
            if (k == start) continue;

            outVertices.add(k);
        }


        while(! outVertices.isEmpty()) {
            // choose random vertex not in spanning tree
            int currentVertex = outVertices.get(new Random().nextInt(outVertices.size()));

            HashMap<Integer, Arc> path = new HashMap<>(); // path to random vertex chosen

            // walk while not connected to the spanning tree
            while (! inVertices.contains(currentVertex)) {
                Arc randomOutNeighbour = graph.outNeighbours(currentVertex).get(new Random().nextInt(graph.outNeighbours(currentVertex).size()));

                path.put(currentVertex, randomOutNeighbour);
                currentVertex = randomOutNeighbour.getDest();
            }

            // connect path to the spanning tree
            outVertices.removeAll(path.keySet());
            inVertices.addAll(path.keySet());
            arcs.addAll(path.values());
        }

        return arcs;
    }
}
