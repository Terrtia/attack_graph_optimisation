package attack_graph_optimisation.graph;

import attack_graph_optimisation.Moyen;

public class Edge {
	
	private Node from;
	private Node to;
	private Moyen moyen;
	private int num_moyen;
	private int id_edge;
	private double efficiency;
	

	public Edge(int id_edge, Node from, Node to) {
		this.id_edge = id_edge;
		this.from = from;
		this.to = to;
	}


	public Node getFrom() {
		return from;
	}


	public void setFrom(Node from) {
		this.from = from;
	}


	public Node getTo() {
		return to;
	}


	public void setTo(Node to) {
		this.to = to;
	}


	public Moyen getMoyen() {
		return moyen;
	}


	public void setMoyen(Moyen moyen) {
		this.moyen = moyen;
	}


	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}
	
	public int getId_edge() {
		return id_edge;
	}

	public void setId_edge(int id_edge) {
		this.id_edge = id_edge;
	}

	public int getNum_moyen() {
		return num_moyen;
	}

	public void setNum_moyen(int num_moyen) {
		this.num_moyen = num_moyen;
	}

	public int getToNodeId(){
		return this.to.getId();
	}
	
	public int getEdgeCost(){
		return this.moyen.getCost(this.num_moyen);
	}

}
