#pragma once

#include <stdlib.h>
#include <stdio.h>
#include <string>
#include <sstream>
#include <iostream>
#include <ostream>

using namespace std;

class Sommet{

public:

	int _clef;
	string _v;
	double _degres;

	Sommet(int clef, string v, double degres) :_clef(clef), _v(v), _degres(degres) {};
	Sommet(int clef, string v) : _clef(clef), _v(v), _degres(NULL) {};

	operator string () const {

		ostringstream s;
		s << "( Indice : " << _clef << ", Nom : " << getNomSommet() << ", degre : " << getDegreSommet() << ")";

		return s.str();
	}

	bool operator ==(const Sommet &s) {
		return ((this->_v == s._v) && (this->_degres == s._degres));
	}

	void setDegreSommet(const double degre) {
		_degres = degre;
	}
	double getDegreSommet()const {
		return _degres;
	}
	string getNomSommet()const {
		return _v;
	}


};

ostream & operator << (ostream &s, const Sommet &sm) {
	return s << (string)sm << endl;
}

