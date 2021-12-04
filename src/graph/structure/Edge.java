package graph.structure;

public class Edge implements Comparable<Edge> {

	private final int source;
	private final int dest;
	public double weight;
	
	public Edge(int source, int dest, double weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
	
	public int compareTo(Edge e) {
		return Double.compare(this.weight, e.weight);
	}
	
	public int oppositeExtremity(int vertex) {
		return (dest == vertex ? source : dest);
	}
	
	public int getSource() {
		return this.source;
	}
	
	public int getDest() {
		return this.dest;
	}
	
}
