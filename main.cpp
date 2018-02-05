#pragma once

#include "Sommet.h"
#include "Arete.h"
#include "Graphe.h"
#include "vector"
#include <fstream>
#include <iostream>

using namespace std;

int main() {

	string nomFichier;
	string resultat;

	cout << "Entrez le nom de votre fichier sans .txt" << endl;

	cin >> nomFichier;

	cout << "Entrez le nom de votre fichier resultat sans .txt" << endl;

	cin >> resultat;

	ifstream fichier(nomFichier + ".txt", ios::in);
	ofstream fichierCree(resultat + ".txt");

	//ifstream fichier("data_VRPTW_100_10_5_9.txt", ios::in);  // on ouvre le fichier en lecture

	if (fichier)  // si l'ouverture a réussi
	{

		Graphe* g = new Graphe();
		
		// instructions
		string ligne;
		int pos=0;
		string* nomArete = NULL;
		string sommetDebut;
		string sommetFin;
		string sommetInit;
		int poids;
		int i=0;
		string nomAreteTemp;

		vector<string*> vectString;
		vector<string*>::iterator itString;
		vector<Sommet*>::iterator itSommets;
		vector<Arete*>::iterator itAretes;


// ------------- CREATION DES SOMMETS ---------------------
		while (getline(fichier, ligne) && (ligne.find("sectionArcs") != 0))
		{
			
			g->creeSommet(ligne);
		
		}


// ------------- Affichage de la liste des sommets ----------------
		
		vector<Sommet*> listSommets = g->getListSommets();
		/*
		for (itSommets = res.begin(); itSommets != res.end(); itSommets++)
		{
			cout << *(*itSommets)  << endl << endl;
		}
		*/

// --------------------CREATION DES ARETES-------------------------------
		while (getline(fichier, ligne) && (ligne.find("sources") != 0)){
		
			/*
			if (ligne.size() < 2) {
				getline(fichier, ligne);
			}*/

	//récupération du nom de l'arête
			nomAreteTemp = ligne;
			vectString.push_back(&nomAreteTemp);

	//récupération du sommet source
			getline(fichier, ligne);
			sommetDebut = ligne;

	//récupération du sommet cible
			getline(fichier, ligne);
			sommetFin = ligne;

	//récupération du poids de l'arête
			getline(fichier, ligne);
			poids = stoi(ligne);

	//ne pas prendre le temps
			getline(fichier, ligne);

			itString = vectString.begin();

			g->creeArete(*(*(itString)),(g->getSommet(sommetDebut)), (g->getSommet(sommetFin)), poids);
			i++;

		}

// ---------------------RECUPERATION DU SOMMET INITIAL DE L'ALGO FORD-BELL -----------------------------
		getline(fichier, ligne);
		sommetInit = ligne;

		vector<Arete*> resArete = g->getListAretes();
		
// Affichage des arêtes du graphe
/*
		for (itAretes = resArete.begin(); itAretes != resArete.end(); itAretes++)
		{
			cout << "Arete : " << *(*itAretes) << endl;
		}
*/

// On exécute l'algo avec le sommet initial
		g->FordBell((g->getSommet(sommetInit)));

// On affiche le degré des sommets
		for (itSommets = listSommets.begin(); itSommets != listSommets.end(); itSommets++)
		{
			fichierCree << "Sommet : " << (*itSommets)->getNomSommet() << ", Degre : " << (*itSommets)->getDegreSommet() << endl;
		}
	
		fichierCree.close();
		fichier.close();  // on ferme le fichier
	
	}
	else cerr << "Impossible d'ouvrir le fichier !" << endl;

	system("pause");

}