package attack_graph_optimisation;

import java.util.Arrays;

public class Moyen {
	
	private int[] cost;
	private double[][] tabEfficiency;

	public Moyen(double[][] tabEfficiency, int[] cost) {
		this.tabEfficiency = tabEfficiency;
		this.cost = cost;
	}

	public int getCost(int num_moyen) {
		return cost[num_moyen];
	}

	public void setCost(int cost, int num_moyen) {
		this.cost[num_moyen] = cost;
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

	public String toStringDoubleArray(double[][] tabEfficiency){
		StringBuilder b = new StringBuilder();
		b.append(System.lineSeparator());
		for(int i=0; i<tabEfficiency.length; i++){
			for(int j=0; j<tabEfficiency[i].length; j++){
				b.append("[" + tabEfficiency[i][j] + "]");
			}
			b.append(System.lineSeparator());
		}
		return b.toString();
	}
	
	public String toString() {
		return "Moyen [cost=" + Arrays.toString(cost) + ", tabEfficiency="
				+ toStringDoubleArray(tabEfficiency) + "]";
	}

	
}
