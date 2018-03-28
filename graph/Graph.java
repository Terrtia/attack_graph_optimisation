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
		for(int i=0; i<this.edges.size(); i++){
			this.edges.get(i).setMoyen(moyen);
		}
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public int getMoyenCost(int num_moyen) {
		return this.moyen.getCost(num_moyen);
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
	
	public Edge getEdgeWithIndex(int index) {
		for(int i=0; i<this.edges.size(); i++) {
			if(this.edges.get(i).getId_edge() == index){
				return this.edges.get(i);
			}
		}
		return null;
	}
	
	public double getEdgeEfficiency(int id, int edge) {
		return this.moyen.getEdgeEfficiency(id, edge);
	}
	
	public int getMoyenNumber(){
		return this.moyen.getMoyenNumber();
	}
	
	public void setRandomMoyen(){
		
		for(int i = 0; i<this.edges.size(); i++){
			this.edges.get(i).setNum_moyen(0);
			this.edges.get(i).setEfficiency(0);
		}
		
		//copy Edge ArrayList
		ArrayList<Edge> edge_copy = new ArrayList<>(this.getEdges());
		
		Random rand = new Random();
		int minIndex = 0;
		int maxIndex = this.edges.size() - 1;
		int minMoyen = 0;
		int maxMoyen = this.getMoyenNumber() - 1;
		int res = 0;
		int randNumberIndex = 0;
		int randMoyen = 0;
		int total_cost = 0;
		
		while(res != 5){
			randNumberIndex = rand.nextInt((maxIndex - minIndex) + 1) + minIndex;
			randMoyen = rand.nextInt((maxMoyen - minMoyen) + 1) + minMoyen;
			
			if(total_cost + this.getMoyenCost(randMoyen) <= this.budget){
				Edge tmp_edge = edge_copy.get(randNumberIndex);
				Edge selct_edge = this.getEdgeWithIndex(tmp_edge.getId_edge());
				int real_edge_id = selct_edge.getId_edge();
				selct_edge.setNum_moyen(randMoyen);
				selct_edge.setEfficiency(this.getEdgeEfficiency(real_edge_id, randMoyen));
				total_cost += this.getMoyenCost(randMoyen);
				edge_copy.remove(randNumberIndex);
				
				//change max random index number
				maxIndex --;
				
				//finish while
				if(edge_copy.size() == 0){
					res = 5;
				}
				
			} else {
				res++;
			}
			
			
		}
	}
	
		
	public ArrayList<Node> listeSucesseur(Node s){
		ArrayList<Node> liste = new ArrayList<>();
		for (int i = 0; i < edges.size(); i++){
			if (edges.get(i).getFrom() == s){
				liste.add(edges.get(i).getTo());
			}
		}
		return liste;
	}
		
	public ArrayList<Node> listePredecesseur(Node s){
		ArrayList<Node> liste = new ArrayList<>();
		for (int i = 0; i < edges.size(); i++){
			if (edges.get(i).getTo() == s){
				liste.add(edges.get(i).getFrom());
			}
		}
		return liste;
	}

	@Override
	public String toString() {
		return "Graph [nodes=" + Arrays.toString(nodes) + ", edges=" + edges
				+ "]";
	}

	
}
