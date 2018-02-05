package attack_graph_optimisation.graph;

import java.util.ArrayList;

import attack_graph_optimisation.Moyen;

public class Edge {
	
	private Node from;
	private Node to;
	private Moyen moyen;
	private double efficiency;
	

	public Edge(Node from, Node to) {
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
	

}
