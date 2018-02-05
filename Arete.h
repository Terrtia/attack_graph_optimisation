#pragma once

#include <string>
#include <iostream>
#include <ostream>
#include <sstream>
#include "Sommet.h"

using namespace std;

class Arete{

public:

	string _nom;
	Sommet* _debut;
	Sommet* _fin;
	double _v;

	Arete(string nom, Sommet* deb, Sommet* fin, double val) :_nom(nom), _debut(deb), _fin(fin), _v(val) {
/*		deb->_degres++;
		fin->_degres++;
*/	}

	operator string () const {

		ostringstream s;
		s << "( " << _nom << ", sommet debut : " << *_debut << ", sommet fin : " << *_fin << ", valeur : " << _v << " )";

		return s.str();
	}

	Sommet* predecesseurArete() const{
		
		return _debut;

	}

	Sommet* successeurArete() const {

		return _fin;

	}
	
	string getNom()const {
		return _nom;
	}

	double getValeur()const {
		return _v;
	}

};

ostream & operator << (ostream &s, const Arete &a) {
	return s << (string)a << endl;
}
