package attack_graph_optimisation.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import attack_graph_optimisation.Moyen;

public class Graph {
	
	private Node[] nodes;
	private ArrayList<Edge> edges;
	private int budget;
	private Moyen moyen;

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

	public Moyen getMoyen() {
		return moyen;
	}

	public void setMoyen(Moyen moyen) {
		this.moyen = moyen;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getTotalCost(){
		int totalCost = 0;
		for(int i=0; i<this.edges.size(); i++){
			totalCost += this.edges.get(i).getEdgeCost();
		}
		return totalCost;
	}
	
	public int getMoyenNumber(){
		return this.moyen.getMoyenNumber();
	}
	
	public void setRandomMoyen(){
		int[] index = new int[this.getNodesNumber()];
		
		for(int i=0; i<index.length; i++){
			index[i] = i;
		}
		
		Random rand = new Random();
		int minIndex = 0;
		int maxIndex = index.length;
		int minMoyen = 0;
		int maxMoyen = this.getMoyenNumber();
		int res = 0;
		int randNumber = 0;
		int randMoyen = 0;
		int total_cost = 0;
		
		while(res != 5){
			randNumber = rand.nextInt((maxIndex - minIndex) + 1) + minIndex;
			randMoyen = rand.nextInt((maxMoyen - minMoyen) + 1) + minMoyen;
			
			if(total_cost +  <= this.budget){
				
			}
		}
	}

	@Override
	public String toString() {
		return "Graph [nodes=" + Arrays.toString(nodes) + ", edges=" + edges
				+ "]";
	}

	
}
