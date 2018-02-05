package attack_graph_optimisation.graph;

import java.util.ArrayList;

public class Node {

	private ArrayList<Edge> in;
	private ArrayList<Edge> out;
	private int id;
	private int level;
	
	public Node(int id, ArrayList<Edge> in, ArrayList<Edge> out) {
		this.id = id;
		this.in = in;
		this.out = out;
	}

	public ArrayList<Edge> getIn() {
		return in;
	}

	public void setIn(ArrayList<Edge> in) {
		this.in = in;
	}

	public ArrayList<Edge> getOut() {
		return out;
	}

	public void setOut(ArrayList<Edge> out) {
		this.out = out;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
