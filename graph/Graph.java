package attack_graph_optimisation.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	
	private Node[] nodes;
	private ArrayList<Edge> edges;

	public Graph(Node[] nodes, ArrayList<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
		
	}

	@Override
	public String toString() {
		return "Graph [nodes=" + Arrays.toString(nodes) + ", edges=" + edges
				+ "]";
	}

	
}
