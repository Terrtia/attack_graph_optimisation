package attack_graph_optimisation.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import attack_graph_optimisation.graph.Graph;
import attack_graph_optimisation.graph.Node;
//import javafx.util.*; D'OU VIENS JAVAX ? PACKAGE ?

public class Bellman {

	public class pcc{
		public double valeur;//valeur du plus court chemin
		public Node sommet;//sommet a partir duquel on trouve le plus court chemin
		public pcc(double valeur, Node sommet) {
			this.valeur=valeur;
			this.sommet=sommet;
		}
	}
	
	public Graph graphe;
	Map<Node, pcc> listePlusCourtChemin;//liste du plus court chemin pour chaque sommet
	ArrayList<Node> M, //ensemble des sommets a etudier
					 Mprime;//variable temporaire d'ensemble de sommet a etudier
	boolean circuitAbsorbant = false;
	
	public Bellman(Graph graphe) {
		this.graphe=graphe;
		listePlusCourtChemin = new HashMap<>();
		M = new ArrayList<>();
		Mprime = new ArrayList<>();
		PlusCourtChemin();
		AfficherPlusCourtChemin();
	}
	
	public void PlusCourtChemin(){
		double infini = 999;
		int k = 0;
		int n = graphe.getEdges().size();
		
		listePlusCourtChemin.put(graphe.getNodes()[0], new pcc(0,null));//initialisation du premier sommet a 0
		for (int j = 1; j < graphe.getNodes().length;j++)
			listePlusCourtChemin.put(graphe.getNodes()[j], new pcc(infini,null));//initialisation des sommets restant a l'infini
		
		M.add(graphe.getNodes()[0]);//on ajoute le premier sommet a l'ensemble M
		Map<Node,ArrayList<Node>> listeSucc = getSuccesseurM(M);
		while ((k <= n - 1) && (listeSucc.size() != 0)){
			k++;
			Mprime.clear();
			
			for(Map.Entry<Node,ArrayList<Node>> entry : listeSucc.entrySet()){//pour tout les successeurs des sommets present dans M
				//On parcourt la liste de tous les successeur de M[i]
				for (int i = 0; i < entry.getValue().size(); i++){
					Pair<Node, pcc> it = findTabPcc(entry.getKey());
					//poid = poid de l'arete parcourue + valeur du plus court chemin du prédecessur du sommet traite
					
					double poid = getPoidArete(entry.getKey(), entry.getValue().get(i)) + it.getValue().valeur;
					it = findTabPcc(entry.getValue().get(i));
					if (poid < it.getValue().valeur){//si le nouveau poid < poid courant
						Node s = it.getKey();
						listePlusCourtChemin.remove(it.getKey());
						listePlusCourtChemin.put(s, new pcc(poid,entry.getKey()));
						if(!Mprime.contains(s)) {
							Mprime.add(s);
						}
					}
					
				}
			}
			
			M = new ArrayList<>();
			
			M = Mprime;
			listeSucc = getSuccesseurM(M);
		}
		
		if (getSuccesseurM(M).size() != 0)
			circuitAbsorbant = true;
	}
	
	public void AfficherPlusCourtChemin(){
		System.out.println("---------------------------------------------------\n");
		if (circuitAbsorbant)
			System.out.println("Il existe un circuit absorbant pour ce graphe !\n");
		
		for(Map.Entry<Node,pcc> entry : listePlusCourtChemin.entrySet()){
			boolean hasPCC = true;
			ArrayList<Node>  listeChemin = new ArrayList<>();
			listeChemin = getCheminPCC(entry.getKey(),listeChemin);
			Collections.reverse(listeChemin);
			
			System.out.println("PCC de " + entry.getKey().getId() + ":");
			
			for (int i = 0; i < listeChemin.size(); i++){
				if (listeChemin.get(0) != graphe.getNodes()[0]){
					System.out.println("Il n existe pas de plus court chemin pour ce sommet");
					hasPCC = false;
				}
				else
					System.out.print(listeChemin.get(i).getId() + " -> ");
			}
			if (hasPCC){
				System.out.print(entry.getKey().getId());
				System.out.println(" : " + entry.getValue().valeur + "\n");
			}
			else
				System.out.println();
		}
		System.out.println("---------------------------------------------------");
	}
	
	/*Permet d'afficher recursivement le chemin du plus court chemin d'un sommet*/
	public ArrayList<Node> getCheminPCC(Node s, ArrayList<Node> v){
		if (s != graphe.getNodes()[0]){
			if (circuitAbsorbant){
				if (Collections.frequency(v, s) == 1){
					return v;
				}
				else{
					Pair<Node, pcc> it = findTabPcc(s);
					v.add(it.getValue().sommet);
					v = getCheminPCC(it.getValue().sommet, v);
				}
			}
			else{
				Pair<Node, pcc> it = findTabPcc(s);
				if(it.getValue().sommet!=null) {
					v.add(it.getValue().sommet);
					v = getCheminPCC(it.getValue().sommet, v);
				}
			}
		}
		return v;
	}
	
	/*Recupere tout les successeurs des sommets present dans M*/
	public Map<Node,ArrayList<Node>> getSuccesseurM(ArrayList<Node> M){
		Map<Node,ArrayList<Node>> listeSucc = new HashMap<>();
		for (int i = 0; i < M.size(); i++){
			ArrayList<Node> liste = new ArrayList<>();
			for (int j = 0; j < graphe.listeSucesseur(M.get(i)).size(); j++){
				liste.add(graphe.listeSucesseur(M.get(i)).get(j));
			}
			listeSucc.put(M.get(i), liste);
		}
		return listeSucc;
	}
	
	/*Renvoie le poid d'une arete pour 2 sommets*/
	double getPoidArete(Node s1, Node s2){
		for (int i = 0; i < graphe.getEdges().size(); i++){
			if (graphe.getEdges().get(i).getFrom() == s1 && graphe.getEdges().get(i).getTo() == s2)
				return graphe.getEdges().get(i).getEfficiency();
		}
		return 0.0;
	}
	
	public Pair<Node, pcc> findTabPcc(Node s) {
		Iterator	<Map.Entry<Node, pcc>> it = listePlusCourtChemin.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			if(entry.getKey().equals(s)) {
				return new Pair<>(s, (pcc)entry.getValue());
			}
		}
		return new Pair<>(null, null);
	}
}


