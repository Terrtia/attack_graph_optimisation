package attack_graph_optimisation;

import java.util.ArrayList;
import java.util.Random;

import attack_graph_optimisation.algo.Bellman;
import attack_graph_optimisation.algo.LevelDecomposition;
import attack_graph_optimisation.graph.Edge;
import attack_graph_optimisation.graph.Graph;
import attack_graph_optimisation.graph.Node;

public class Attack_graph {

	private Graph graph;
	private LevelDecomposition levelDecomposition = new LevelDecomposition();
	
	public Attack_graph() {
		//create test Graph
				ArrayList<Edge> edges = new ArrayList<Edge>();
				
				//create Node
				int numNodes = 10;
				Node[] nodes = new Node[numNodes];
				for(int i = 0; i<numNodes; i++){
					nodes[i] = new Node(i);
				}
				
				//create Edge
				Edge e;
				int id_edge = 0;
				// 0=>1
				e = new Edge(id_edge, nodes[0], nodes[1]);
				//e.setEfficiency(1);
				edges.add(e);
				id_edge++;
				// 0=>2
				e = new Edge(id_edge, nodes[0], nodes[2]);
				//e.setEfficiency(4);
				edges.add(e);
				id_edge++;
				// 0=>3
				e = new Edge(id_edge, nodes[0], nodes[3]);
				//e.setEfficiency(2);
				edges.add(e);
				id_edge++;
				
				// 1=>4
				e = new Edge(id_edge, nodes[1], nodes[4]);
				//e.setEfficiency(2);
				edges.add(e);
				id_edge++;
				// 1=>5
				e = new Edge(id_edge, nodes[1], nodes[5]);
				//e.setEfficiency(3);
				edges.add(e);
				id_edge++;
				
				// 2=>5
				e = new Edge(id_edge, nodes[2], nodes[5]);
				//e.setEfficiency(3);
				edges.add(e);
				id_edge++;
				
				// 4=>7
				e = new Edge(id_edge, nodes[4], nodes[7]);
				//e.setEfficiency(2);
				edges.add(e);
				id_edge++;
				
				// 3=>6
				e = new Edge(id_edge, nodes[3], nodes[6]);
				//e.setEfficiency(1);
				edges.add(e);
				id_edge++;
				
				// 6=>9
				e = new Edge(id_edge, nodes[6], nodes[9]);
				//e.setEfficiency(4);
				edges.add(e);
				id_edge++;
				
				// 9=>8
				e = new Edge(id_edge, nodes[9], nodes[8]);
				//e.setEfficiency(2);
				edges.add(e);
				id_edge++;
				
				// 5=>8
				e = new Edge(id_edge, nodes[5], nodes[8]);
				//e.setEfficiency(3);
				edges.add(e);
				id_edge++;
				
				Graph graph = new Graph(nodes, edges);
				
				//Create Moyen Object
				//num_moyen = 4 
				int num_moyen = 5;
				//create cost Array
				int[] cost = new int[num_moyen];
				cost[0] = 0; // Cost 0 must be always set to 0 ! moyen 0 = no protection
				cost[1] = 2;
				cost[2] = 5;
				cost[3] = 8;
				cost[4] = 10;
				
				//create tabEfficiency
				Random rand = new Random();
				int max = 100;
				int min = 0;
				
				double[][] tabEfficiency = new double[id_edge][num_moyen];
				for(int i=0; i<id_edge; i++){
					for(int j=0; j<num_moyen; j++){
						if(j == 0){
							tabEfficiency[i][j] = 0;
						} else {
							tabEfficiency[i][j] = rand.nextInt((max - min) + 1) + min;
						}
					}
				}
				
				Moyen moyen = new Moyen(tabEfficiency, cost);
				
				
				
				this.graph = graph;
				this.graph.setMoyen(moyen);
				
				this.setBudget(80);
				this.graph.setRandomMoyen();
				System.out.println("Graph Total Cost = " + graph.getTotalCost());
		
				//Bellman bellman = new Bellman(graph);

				
				Boolean get_a_cycle = this.levelDecomposition.level_decomposition(graph);
				System.out.println("cycle: " + get_a_cycle);
				
				System.out.println(graph.toString());
				System.out.println(moyen.toString());
				
				//DEBUG
				/*Node[] nodess = this.graph.getNodes();
				for(int i=0; i<this.graph.getNodesNumber(); i++){
					System.out.println("id " + nodess[i].getId() + " level " + nodess[i].getLevel());
				}*/
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public int getBudget() {
		return this.graph.getBudget();
	}

	public void setBudget(int budget) {
		this.graph.setBudget(budget);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Attack_graph attack_graph = new Attack_graph();
	}

}
