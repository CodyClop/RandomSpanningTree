import algorithms.random.RandomBFS;
import algorithms.random.uniform.RandomArcInsertion;
import algorithms.random.uniform.RandomMinWeightTree;
import algorithms.random.uniform.Wilson;
import graph.structure.Arc;
import graph.structure.Edge;
import graph.structure.Graph;
import graph.tools.BreadthFirstSearch;
import graph.tools.Labyrinth;
import graph.tools.RootedTree;
import graph.type.Complete;
import graph.type.ErdosRenyi;
import graph.type.Grid;
import algorithms.random.uniform.AldousBroder;
import graph.type.Lollipop;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;


public class Main {
    /**
     * Generates a spanning tree.
     * Choose the algorithm to be tested here
     */
    public static ArrayList<Edge> genTree(Graph graph, int randomRoot) {
        ArrayList<Edge> randomTree = new ArrayList<>();

        /* -- Choose Uniform Random Spanning Tree Algorithm -- */

//        ArrayList<Arc> randomArcTree = RandomMinWeightTree.generateTree(graph, randomRoot);
//        ArrayList<Arc> randomArcTree = AldousBroder.generateTree(graph, randomRoot);
//        ArrayList<Arc> randomArcTree = Wilson.generateTree(graph, randomRoot);
        ArrayList<Arc> randomArcTree = RandomArcInsertion.generateTree(graph);


        /* -- Choose a Non-Random/Non-Uniform Spanning Tree Algorithm for comparison -- */
//        ArrayList<Arc> randomArcTree = RandomBFS.generateTree(graph, randomRoot); // Random but not uniform
//        ArrayList<Arc> randomArcTree = BreadthFirstSearch.generateTree(graph, randomRoot); // Not Random

        for (Arc a : randomArcTree) randomTree.add(a.support);

        return randomTree;
    }


    public static void main(String[] argv) {

        /* -- Choose Type Of graph.Graph For Measures -- */

//        Graph graph = new Grid(1920/11,1080/11).graph;
//		Graph graph = new Complete(500).graph;
		Graph graph = new ErdosRenyi(2_000, 100).graph;
//		Graph graph = new Lollipop(400).graph;


        int nbrOfSamples = 20; // number of spanning trees generated
        makeMeasures(graph, nbrOfSamples); // comment out to skip measures and only see visualisation

        //visualiseOnGrid(); // uncomment to show a representation of the Spanning Tree given by chosen algorithm on a grid
    }

    /**
     * Prints average stats on generated spanning trees
     * @param graph the graph where spanning trees will be generated on
     * @param nbrOfSamples the number of spanning trees generated
     */
    private static void makeMeasures(Graph graph, int nbrOfSamples) {
        int diameterSum = 0;
        double eccentricitySum = 0;
        long wienerSum = 0;
        int[] degreesSum = {0, 0, 0, 0, 0};
        int[] degrees;

        ArrayList<Edge> randomTree;
        RootedTree rooted;

        long startingTime = System.nanoTime();
        for (int i = 0; i < nbrOfSamples; i++) {
            int randomRoot = new Random().nextInt(graph.order);
            randomTree= genTree(graph, randomRoot);

            rooted = new RootedTree(randomTree,randomRoot);

            diameterSum += rooted.getDiameter();
            eccentricitySum += rooted.getAverageEccentricity();
            wienerSum += rooted.getWienerIndex();

            degrees = rooted.getDegreeDistribution(4);
            for (int j = 1; j < 5; j++) {
                degreesSum[j] = degreesSum[j] + degrees[j];
            }
        }
        long delay = System.nanoTime() - startingTime;

        System.out.println("On " + nbrOfSamples + " samples:");
        System.out.println("Average eccentricity: "
                + (eccentricitySum / nbrOfSamples));
        System.out.println("Average wiener index: "
                + (wienerSum / nbrOfSamples));
        System.out.println("Average diameter: "
                + (diameterSum / nbrOfSamples));
        System.out.println("Average number of leaves: "
                + (degreesSum[1] / nbrOfSamples));
        System.out.println("Average number of degree 2 vertices: "
                + (degreesSum[2] / nbrOfSamples));
        System.out.println("Average computation time: "
                + delay / (nbrOfSamples * 1_000_000L) + "ms");
    }

    /**
     * Creates a grid and generates a spanning tree with the chosen algorithm
     * Shows the spanning tree in a window
     */
    private static void visualiseOnGrid() {
        Grid grid = new Grid(1920/11,1080/11);
        Graph graph = grid.graph;

        Random rand = new Random();
        int randomRoot = rand.nextInt(graph.order);
        ArrayList<Edge> randomTree= genTree(graph, randomRoot);
        RootedTree rooted = new RootedTree(randomTree,randomRoot);

        showGrid(grid,rooted,randomTree);
    }

    /**
     * Creates a graphTools.Labyrinth and shows it in a window
     */
    private static void showGrid(Grid grid, RootedTree rooted, ArrayList<Edge> randomTree) {
        JFrame window = new JFrame("solution");
        final Labyrinth laby = new Labyrinth(grid, rooted);

        laby.setStyleBalanced();
        laby.setShapeSmoothSmallNodes();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(laby);
        window.pack();
        window.setLocationRelativeTo(null);


        for (final Edge e : randomTree) {
            laby.addEdge(e);
        }
        laby.drawLabyrinth();

        window.setVisible(true);
    }


}

