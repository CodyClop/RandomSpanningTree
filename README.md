# Random Spanning Tree Algorithms

---

## Project Presentation

The objective of this project is to compare different spanning tree generators on different graph types. <br>
A tool for visualisation is provided in order to differentiate uniform random spanning trees from not uniform ones.<br>

Even though I implemented a non-uniform algorithm as well as a non-random algorithm both based on a breadth-first search,
I am only interested in uniform generators. These algorithms are only here for reference.

Four uniform spanning tree algorithms are tested : Aldous-Broder's, Wilson's, Random Arc Insertion and Random Min-Weight Tree.
A description of these algorithms are given further.

## Visualisation

Uncommenting line 62 of the Main class will generate a spanning tree on a Grid and create a window with the tree represented.
A typical uniformly generated tree will not show any kind of pattern in shapes or colors. <br>
Try visualising a tree generated by a Breadth-First Search's Algorithm or a RandomBFS' algorithm to see what
a not uniformly generated tree looks like.

## Algorithms

### Aldous-Broder's Algorithm
In the Aldous-Broder's algorithm, we randomly explore the graph by choosing a random arc from the current position. When a new
vertex is discovered, the arc that led us to this vertex is retained.

### Wilson's Algorithm
The Wilson's Algorithm works like Aldous-Broder's Algorithm except we choose a vertex that is not in the spanning tree and try
to reach it with the same random walk. Once reached, the path leading to this vertex (without cycle) is retained.

### Random Arc Insertion
In this algorithm we randomly pick an arc and try to insert it in our spanning tree. If it creates a cycle then we don't take it.<br>
In order to know if a cycle is formed, we utilise a Union-Find structure.

### Random Min-Weight Tree
We give a random weight for every arc in the graph and once it is done, we use the Prim's algorithm in order to find a minimum
weighted spanning tree.

## Results

Average computation time with my personal computer on 20 iterations :

|                            | Grid  | Complete | Lollipop | ErdosRenyi |
|----------------------------|:-----:|:--------:|:--------:|:----------:|
| **Aldous-Broder**          | 49ms  |   5ms    |  1170ms  |    8ms     |
| **Wilson**                 | 61ms  |   4ms    |  423ms   |    7ms     |
| **Random Arc Insertion**   | 76ms  |  827ms   |   72ms   |   526ms    |
| **Random Min-Weight Tree** | 332ms |   45ms   |   15ms   |   100ms    |

## Conclusions

Algorithms based on random walks like Aldous-Broder's or Wilson's algorithm are not effective in 
sparse graphs but are in dense ones.<br>
The Random Arc Insertion algorithm is the opposite : effective in sparse graphs but not in dense ones. <br>
The Random Min-Weight Tree algorithm seems to be the most adaptive out of the four tested algorithms.
