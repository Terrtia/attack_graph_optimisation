package attack_graph_optimisation;

import java.util.ArrayList;
import java.util.Random;

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
				edges.add(e);
				id_edge++;
				// 0=>2
				e = new Edge(id_edge, nodes[0], nodes[2]);
				edges.add(e);
				id_edge++;
				// 0=>3
				e = new Edge(id_edge, nodes[0], nodes[3]);
				edges.add(e);
				id_edge++;
				
				// 1=>4
				e = new Edge(id_edge, nodes[1], nodes[4]);
				edges.add(e);
				id_edge++;
				// 1=>5
				e = new Edge(id_edge, nodes[1], nodes[5]);
				edges.add(e);
				id_edge++;
				
				// 2=>5
				e = new Edge(id_edge, nodes[2], nodes[5]);
				edges.add(e);
				id_edge++;
				
				// 4=>7
				e = new Edge(id_edge, nodes[4], nodes[7]);
				edges.add(e);
				id_edge++;
				
				// 3=>6
				e = new Edge(id_edge, nodes[3], nodes[6]);
				edges.add(e);
				id_edge++;
				
				// 6=>9
				e = new Edge(id_edge, nodes[6], nodes[9]);
				edges.add(e);
				id_edge++;
				
				// 9=>8
				e = new Edge(id_edge, nodes[9], nodes[8]);
				edges.add(e);
				id_edge++;
				
				// 5=>8
				e = new Edge(id_edge, nodes[5], nodes[8]);
				edges.add(e);
				id_edge++;
				
				Graph graph = new Graph(nodes, edges);
				
				//Create Moyen Object
				//num_moyen = 4 
				int num_moyen = 4;
				//create cost Array
				int[] cost = new int[num_moyen];
				cost[0] = 2;
				cost[1] = 5;
				cost[2] = 8;
				cost[3] = 10;
				
				//create tabEfficiency
				Random rand = new Random();
				int max = 100;
				int min = 0;
				
				double[][] tabEfficiency = new double[id_edge][num_moyen];
				for(int i=0; i<id_edge; i++){
					for(int j=0; j<num_moyen; j++){
					tabEfficiency[i][j] = rand.nextInt((max - min) + 1) + min;
					}
				}
				
				Moyen moyen = new Moyen(tabEfficiency, cost);
				
				this.graph = graph;
				
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Attack_graph();
	}

}
