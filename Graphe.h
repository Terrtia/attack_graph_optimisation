#pragma once

#include <string>
#include <iostream>
#include <ostream>
#include <sstream>
#include <vector>
#include "Sommet.h"
#include "Arete.h"

using namespace std;


class Graphe {

private:

	vector<Sommet*> _lSommets;
	vector<Arete*> _lAretes;

	int _prochaineClef;

public:
	Graphe() :_prochaineClef(0), _lAretes(NULL), _lSommets(NULL) {}


	Sommet* creeSommet(const string info) {
		Sommet* s = new Sommet(_prochaineClef++, info);
		_lSommets.push_back(s);
		return s;
	}

	//template <class S, class T>
	~Graphe()
	{}

	void creeArete(string nomArete, Sommet  *deb, Sommet* fin, const double & info) {

		// ici tester que les 2 sommets sont bien existants dans le graphe
		//if (!appartient(deb, _lSommets)) throw Erreur("début d'arête non défini");

		//if (!appartient(fin, _lSommets)) throw Erreur("fin d'arête non définie");

		Arete* a = new Arete(nomArete, deb, fin, info);
		_lAretes.push_back(a);
		//return a;
	}

	vector<Sommet*> getListSommets()const {
		return _lSommets;
	}

	vector<Arete*> getListAretes()const {
		return _lAretes;
	}

	Sommet* getSommet(string nom)const {
		
		vector<Sommet*>::iterator itSommets;
		vector<Sommet*> listSommets = getListSommets();

		for (itSommets = listSommets.begin(); itSommets != listSommets.end(); itSommets++) {
		
			if ((*itSommets)->getNomSommet() == nom)return (*itSommets);
		
		}

	}

	int nombreSommets()const {
		//return PElement::taille(_lSommets);
		return _lSommets.size();
	}

	int nombreAretes()const {
		//return PElement::taille(_lAretes);
		return _lAretes.size();
	}

	operator string () const {

		vector<Sommet*>::iterator it;
		vector<Arete*>::iterator it2;

		vector<Sommet*> listSommets = getListSommets();
		vector<Arete*> listAretes = getListAretes();

		ostringstream s;
		s << "( liste des sommets : ";
		for (it = listSommets.begin(); it != listSommets.end(); it++) {
			s << *(*it) << ", ";
		}
		s << "liste des aretes : ";
		for (it2 = listAretes.begin(); it2 != listAretes.end(); it2++) {
			s << *(*it2) << " ";
		}
		s << ")";
return s.str();

	}

	void initPoidsSommet() {

		vector<Sommet*>::iterator it;
		vector<Sommet*> listSommets = getListSommets();

		for (it = listSommets.begin(); it != listSommets.end(); it++) {
			(*it)->setDegreSommet(999999);
		}

	}

	vector<Arete*> successeurArete(vector<Sommet*> M) {

		vector<Sommet*>::iterator itSommets;
		vector<Arete*>::iterator itAretes;

		vector<Arete*> listAretes = getListAretes();

		vector<Arete*> resultat;

		// Parcours tous les sommets de la liste rentrée en paramètre
		for (itSommets = M.begin(); itSommets != M.end(); itSommets++) {

			// Parcours toutes les arêtes du graphe pour tester les successeurs
			for (itAretes = listAretes.begin(); itAretes != listAretes.end(); itAretes++) {

				if ((*itSommets) == (*itAretes)->predecesseurArete()) {

					resultat.push_back(*itAretes);

				}
			}
		}

		return resultat;

	}

	vector<Arete*> successeurAreteSommet(Sommet* M) {

		vector<Arete*>::iterator itAretes;

		vector<Arete*> listAretes = getListAretes();

		vector<Arete*> resultat;

			// Parcours toutes les arêtes du graphe pour tester les successeurs
		for (itAretes = listAretes.begin(); itAretes != listAretes.end(); itAretes++) {

			if ((M) == (*itAretes)->predecesseurArete()) {

				resultat.push_back(*itAretes);

			}
		}


		return resultat;

	}

	vector<Arete*> predecesseurArete(vector<Sommet*> M) {

		vector<Sommet*>::iterator itSommets;
		vector<Arete*>::iterator itAretes;

		vector<Arete*> listAretes = getListAretes();

		vector<Arete*> resultat;

		// Parcours tous les sommets de la liste rentrée en paramètre
		for (itSommets = M.begin(); itSommets != M.end(); itSommets++) {

			// Parcours toutes les arêtes du graphe pour tester les successeurs
			for (itAretes = listAretes.begin(); itAretes != listAretes.end(); itAretes++) {

				if ((*itSommets) == (*itAretes)->successeurArete()) {

					resultat.push_back(*itAretes);

				}
			}
		}

		return resultat;

	}

	vector<Arete*> predecesseurAreteSommet(Sommet* M) {

		vector<Arete*>::iterator itAretes;

		vector<Arete*> listAretes = getListAretes();

		vector<Arete*> resultat;

		// Parcours toutes les arêtes du graphe pour tester les successeurs
		for (itAretes = listAretes.begin(); itAretes != listAretes.end(); itAretes++) {

			if ((M) == (*itAretes)->successeurArete()) {

				resultat.push_back(*itAretes);

			}
		}


		return resultat;

	}

	vector<Sommet*> predecesseurSommet(Sommet* S) {

		vector<Arete*>::iterator itAretes;
		vector<Sommet*> resultatSommetPred;
		vector<Arete*> listAretes = getListAretes();

		for (itAretes = listAretes.begin(); itAretes != listAretes.end(); itAretes++) {


			if (S == (*itAretes)->successeurArete()) {

				resultatSommetPred.push_back((*itAretes)->predecesseurArete());

			}

		}

		return resultatSommetPred;

	}

	Arete* getArete(Sommet* s1, Sommet* s2) {

		vector<Arete*>::iterator itAretes;
		vector<Arete*> listArete = getListAretes();

		for (itAretes = listArete.begin(); itAretes != listArete.end(); itAretes++) {

			if ((*(*itAretes)->predecesseurArete() == *s1)&& (*(*itAretes)->successeurArete() == *s2)) {

				return *itAretes;

			}

		}

		return NULL;

	}

	vector<Arete*> gammaMoins(vector<Arete*> listPred, vector<Sommet*> m) {

		vector<Arete*>::iterator itListPred;
		vector<Arete*> listPredecesseur = listPred;

		vector<Arete*> res;

		for (itListPred = listPredecesseur.begin(); itListPred != listPredecesseur.end(); itListPred++) {
		
			if (find(m.begin(),m.end(),(*itListPred)->predecesseurArete()) != m.end()) {
				
				res.push_back(*itListPred);

			}
		
		}
		return res;

	}

	double minPoidsSommet(Sommet* s, vector<Arete*> gammaMoins) {

		double min = 99999;
		double sommeDegreInfArete;
		vector<Sommet*> listSommetsPred;
		vector<Arete*> gamma = gammaMoins;
		listSommetsPred = predecesseurSommet(s);
		vector<Sommet*>::iterator itSommets;
		vector<Arete*>::iterator itAretes;

		for (itAretes = gamma.begin(); itAretes != gamma.end(); itAretes++) {

			sommeDegreInfArete = (*itAretes)->getValeur() + (*itAretes)->predecesseurArete()->getDegreSommet();

			if (sommeDegreInfArete < min){

				min = sommeDegreInfArete;

			}

		}

		return min;

	}

	void FordBell(Sommet* sommetInit) {

		bool finFordBell = false;
		vector<Sommet*> M;
		vector<Sommet*> M2;

		vector<Arete*> gammaMo;
		vector<Arete*> areteSucc;
		vector<Arete*>::iterator itSuccesseur;

		double min = 0;
		int k = 0;

		initPoidsSommet();
		sommetInit->setDegreSommet(0);

		M.push_back(sommetInit);
		
		while ((k <= nombreSommets()) && (!successeurArete(M).empty())) {

			k = k + 1;
			M2.clear();
			areteSucc = successeurArete(M);

			for (itSuccesseur = areteSucc.begin(); itSuccesseur != areteSucc.end(); itSuccesseur++) {

				//Avoir le min

				gammaMo = gammaMoins(predecesseurAreteSommet((*itSuccesseur)->successeurArete()),M);

				min = minPoidsSommet((*itSuccesseur)->successeurArete(), gammaMo);
				
				if (min < (*itSuccesseur)->successeurArete()->getDegreSommet()) {

					M2.push_back((*itSuccesseur)->successeurArete());
					(*itSuccesseur)->successeurArete()->setDegreSommet(min);


				} //fin si

			} //fin pour

			M = M2;

		} //fin tant que


	}


	
	














/*
	bool estEgal(const Sommet * s1, const Sommet * s2)const {
		return (((this->_lAretes->_debut == s1) && (this->_lAretes->_fin == s2)) || ((this->_lAretes->_debut == s2) && (this->_lAretes->_fin == s1)));
	}
	*/



	/**
	recherche la liste des paires (voisin, arête) adjacentes de sommet dans le graphe
	*/
	/*
	pair< Sommet *, Arete* >* adjacences(const Sommet * sommet) const
	{
		const Arete * l;

		pair< Sommet *, Arete* > * r;				// pair< Sommet<T> *, Arete<S,T>* >

		for (l = _lAretes, r = NULL; l; l = l->s)

			if (sommet == l->v->debut)
				r = new pair< Sommet *, Arete* >
				(new pair< Sommet *, Arete* >(l->v->fin, l->v), r);
			else
				if (sommet == l->v->fin)
					r = new pair< Sommet *, Arete* >
					(new pair< Sommet *, Arete* >
						(l->v->debut, l->v), r);
		return r;
	}
	*/

	/*cherche l'arête s1 - s2 ou l'arête s2 - s1 si elle existe
	*
	* DONNEES : s1 et s2 deux sommets quelconques du graphe
	* RESULTATS : l'arête s'appuyant sur s1 et s2 si elle existe, NULL sinon
	*
	* */


	/*
	Arete *getAreteParSommets(const Sommet * s1, const Sommet * s2) const
	{
		Arete * l;

		for (l = this->_lAretes; l; l = l->s)
			if (l->v->estEgal(s1, s2))
				return l->v;

		return NULL;
	}
	*/


	/*
	operator string() const
	{
		ostringstream oss;
		oss << "Graphe( \n";
		oss << "prochaine clef = " << this->_prochaineClef << endl;
		oss << "nombre de sommets = " << this->nombreSommets() << "\n";

		//oss << Sommet::toString(_lSommets, "", "\n", "");

		oss << "nombre d'arêtes = " << this->nombreAretes() << "\n";

		//oss << Arete::toString(_lAretes, "", "\n", "");
		oss << ")";
		return oss.str();
	}*/

};

ostream & operator << (ostream &s, const Graphe &g) {
	return s << (string)g << endl;
	//return s << endl;
}