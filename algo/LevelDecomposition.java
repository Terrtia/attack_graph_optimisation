package attack_graph_optimisation.algo;

import java.util.ArrayList;
import attack_graph_optimisation.graph.Edge;
import attack_graph_optimisation.graph.Graph;
import attack_graph_optimisation.graph.Node;

public class LevelDecomposition {

	public LevelDecomposition() {
		
	}
	
	public boolean level_decomposition(Graph graph){
		//copy Edge ArrayList
		ArrayList<Edge> edge_copy = new ArrayList<>(graph.getEdges());
		
		Edge edge_test;
		int level = 0;
		// number of Edge that point on this Node
		int[] in_edge = new int[graph.getNodesNumber()];
		// while condition
		boolean res = true;
		boolean get_a_level = false;
		
		for(int i=0; i< graph.getNodesNumber(); i++) {
			in_edge[i] = 0;
		}
		
		while(res){
			
			for(int i=0; i<edge_copy.size(); i++){
				edge_test = edge_copy.get(i);
				in_edge[edge_test.getToNodeId()] +=1;
			}
			
			for(int i=0; i< graph.getNodesNumber(); i++) {
				if(in_edge[i] == 0){
					get_a_level = true;
					graph.setNodeLevel(i, level);
					in_edge[i] = -1;
					this.removeEdgeFromANode(edge_copy, graph.getNodeId(i));
				}
			}
			
			if(get_a_level == false){
				return true;
			}
			
			/*for(int i=0; i<in_edge.length ; i++) {
				System.out.println("avin_edge[" + i + "] = " + in_edge[i]);
			}*/
			
			level++;
			for(int i=0; i< graph.getNodesNumber(); i++) {
				if(in_edge[i] != -1){
					in_edge[i] = 0;
				}
			}
			
			/*for(int i=0; i<in_edge.length ; i++) {
				System.out.println("in_edge[" + i + "] = " + in_edge[i]);
			}*/
			
			if(edge_copy.size() == 0){
				res = false;
			}

		}
		
		return false;
	}
	
	public void removeEdgeFromANode(ArrayList<Edge> edge_copy, Node node){
		/*System.out.println("remove node from " + node.getId());
		System.out.println("edge_copy.size() " + edge_copy.size());*/
		for(int i=0; i<edge_copy.size(); i++){
			//System.out.println("edge:" + edge_copy.get(i).getFrom().getId() + "=>" + edge_copy.get(i).getTo().getId());
			if(edge_copy.get(i).getFrom().getId() == node.getId()){
				//System.out.println("remove edge:" + edge_copy.get(i).getFrom().getId() + "=>" + edge_copy.get(i).getTo().getId());
				edge_copy.remove(i);
				i = -1;
			}
		}
		
	}

}
