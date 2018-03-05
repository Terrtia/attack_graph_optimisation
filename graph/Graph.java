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

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}
	
	public int getNodesNumber(){
		return this.nodes.length;
	}
	
	public Node getNodeId(int id){
		return this.nodes[id];
	}
	
	public void setNodeLevel(int id, int level){
		this.nodes[id].setLevel(level);
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public String toString() {
		return "Graph [nodes=" + Arrays.toString(nodes) + ", edges=" + edges
				+ "]";
	}

	
}
