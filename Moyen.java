package attack_graph_optimisation;

public class Moyen {
	
	private int cost;
	private double[][] tabEfficiency;

	public Moyen(double[][] tabEfficiency, int cost) {
		this.tabEfficiency = tabEfficiency;
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public double[][] getTabEfficiency() {
		return tabEfficiency;
	}
	
	public double getTabEfficiency(int id, int edge) {
		return tabEfficiency[id][edge];
	}

	public void setTabEfficiency(double[][] tabEfficiency) {
		this.tabEfficiency = tabEfficiency;
	}

}
