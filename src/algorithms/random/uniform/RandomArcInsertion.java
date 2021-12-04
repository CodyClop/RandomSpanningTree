package algorithms.random.uniform;

import graph.structure.Arc;
import graph.structure.Graph;

import java.util.*;

public class RandomArcInsertion {
    private static int[] vertices_parent;
    private static int[] vertices_rank;

    public static ArrayList<Arc> generateTree(Graph graph) {
        ArrayList<Arc> arcsToAdd = new ArrayList<>();
        ArrayList<Arc> solution = new ArrayList<>();

        vertices_parent = new int[graph.order];
        vertices_rank = new int[graph.order];


        for (int k = 0; k < graph.order; k++) {
            vertices_parent[k] = k;
            vertices_rank[k] = 0;

            arcsToAdd.addAll(graph.outNeighbours(k));
        }



        while(!arcsToAdd.isEmpty()) {
            // pick random arc
            int randomArcIndex = new Random().nextInt(arcsToAdd.size());
            Arc randomArc = arcsToAdd.get(randomArcIndex);
            arcsToAdd.remove(randomArcIndex);

            // check if it creates a cycle
            if (find_root(randomArc.getSource()) == find_root(randomArc.getDest())) continue;

            // add arc
            unite_trees(randomArc.getSource(), randomArc.getDest());
            solution.add(randomArc);
        }

        return solution;
    }

    private static void unite_trees(int x, int y) {
        int x_root = find_root(x);
        int y_root = find_root(y);
        if (x_root != y_root) {
            if (vertices_rank[x_root] < vertices_rank[y_root]) {
                vertices_parent[x_root] = y_root;
            } else {
                vertices_parent[y_root] = x_root;
                if (vertices_rank[x_root] == vertices_rank[y_root]) vertices_rank[x_root]++;
            }
        }
    }

    private static int find_root(int vertex) {
        if (vertices_parent[vertex] != vertex) {
            vertices_parent[vertex] = find_root(vertices_parent[vertex]);
        }
        
        return vertices_parent[vertex];
    }
}
