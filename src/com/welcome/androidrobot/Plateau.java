package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.Input.TouchEvent;
import com.welcome.framework.implementation.Event;

public class Plateau {

	public Partie partie;

	public Case[][] cases = new Case[Assets.nLignes][Assets.nColonnes];
	public Pion[] pions = new Pion[Assets.nbPions];

	//ANIMATION
	int charge = 0;
	int directionDeplacement = 0;
	boolean deplacement = false;

	public void paint(Graphics g){
		for(int i=0; i<Assets.nLignes; i++){
			for(int j=0; j<Assets.nColonnes; j++){
				cases[i][j].paint(g);
			}
		}
		for(int i=0; i<5; i++){
			pions[i].paint(g);
		}
	}
	
	public void paintSymbole(Graphics g){
		Case c;
		for(int i=0; i<Assets.nLignes; i++){
			for(int j=0; j<Assets.nColonnes; j++){
				c = cases[i][j];
				c.paintSymbole(g);
				if(partie.objectifCourant.idSymbole==c.symbole){
					g.drawRect(c.x+1, c.y+1, Assets.tailleCase-2, Assets.tailleCase-2, partie.objectifCourant.couleur);
				}
			}
		}
	}

	public Plateau(Partie partie){
		this.partie = partie;
		boolean b = true;
		this.initializeMur();
		int k=0,l=0;
		for(int i=0; i<5; i++){
			b = true;
			while(b){
				k = (int) (Math.random()*Assets.nColonnes);
				l = (int) (Math.random()*Assets.nLignes);
				b = cases[k][l].active;
				for(int j = 0; j<i; j++){
					b = b || (pions[j].i==k && pions[j].j==l);
				}
			}
			pions[i] = new Pion(k,l,i);
		}

		for(Pion p : this.pions){
			cases[p.i][p.j].estOccupe = true;
		}
		for(int i=1; i<=Assets.nbSymboles; i++){
			b = true;
			while(b){
				k = (int) (Math.random()*(Assets.nLignes));
				l = (int) (Math.random()*(Assets.nColonnes));
				b = cases[k][l].active || cases[k][l].estOccupe;
			}
			if(cases[k][l].symbole==0){
				cases[k][l].symbole = i;
			}
		}
	}

	public void update(Vector<Event> events){
			
		if(partie.mode == Etat.JOUER){
			jouer(events);
		}
	}
	
	public void jouer(Vector<Event> events){
		if(!deplacement){
			for(Event e : events){
				if(e.isDown){
					//SELECTION
					partie.caseSelectionne = partie.selection(e.x,e.y);
					if(partie.pionSelectionne==null){
						partie.pionSelectionne = partie.avoirPion(partie.caseSelectionne);
					}

				}
				if(e.isUp){
					if(partie.pionSelectionne==null){
						return;
					}
					Case c = partie.selection(e.x,e.y);
					if(c!=null){
						directionDeplacement = partie.calculerDirection(c);
						deplacement=true;
						System.out.println("Deplacement activé vers "+directionDeplacement);
						cases[partie.pionSelectionne.i][partie.pionSelectionne.j].estOccupe = false;
						//Memorisation des positions précédentes
						for(Pion pion : pions){
							pion.iPrecedent.add(pion.i);
							pion.jPrecedent.add(pion.j);
						}
					}
					this.partie.nbCoups++;
					if(partie.nbCoups>partie.annonce){
						partie.succes = false;
						partie.mode = Etat.REINIT;
					}
				}
			}

		}
		else if(charge>=1){

			deplacement = partie.deplacer(partie.pionSelectionne,directionDeplacement);
			charge = 0;
			if(!deplacement){
				partie.pionSelectionne = null;
				partie.caseSelectionne = null;
			}
		}else if(charge<1){
			charge++;
			
		}
	}

	public void initializeMur(){
		boolean[] murs = new boolean[4];
		int milieu = Assets.nColonnes/2;
		for(int i=0; i<Assets.nLignes; i++){
			for(int j=0; j<Assets.nColonnes; j++){
				murs = new boolean[4];
				murs[0] = j==Assets.nColonnes-1;
				murs[1] = i==0;
				murs[2] = j==0;
				murs[3] = i==Assets.nLignes-1;
				cases[i][j] = new Case(i,j,murs,0);
				if(i>milieu-2 && i<milieu+2 && j>milieu-2 && j<milieu+2){
					cases[i][j].active = true;
				}
			}
		}
		int k=0,l=0;
		for(int i=0; i<Assets.nbMur; i++){
			k = (int) (Math.random()*(Assets.nLignes));
			l = (int) (1+Math.random()*(Assets.nColonnes-1));
			cases[k][l].murs[2] = true;
			cases[k][l-1].murs[0] = true;
			k = (int) (1+Math.random()*(Assets.nLignes-1));
			l = (int) (Math.random()*(Assets.nColonnes));
			cases[k][l].murs[1] = true;
			cases[k-1][l].murs[3] = true;
		}
		//on banalise les cases du milieu
		// haut
		cases[milieu-1][milieu-1].murs[1] = true;
		cases[milieu-1][milieu].murs[1] = true;
		cases[milieu-1][milieu+1].murs[1] = true;
		// bas
		cases[milieu+1][milieu-1].murs[3] = true;
		cases[milieu+1][milieu].murs[3] = true;
		cases[milieu+1][milieu+1].murs[3] = true;
		// gauche
		cases[milieu+1][milieu-1].murs[2] = true;
		cases[milieu][milieu-1].murs[2] = true;
		cases[milieu-1][milieu-1].murs[2] = true;
		// droite
		cases[milieu+1][milieu+1].murs[0] = true;
		cases[milieu][milieu+1].murs[0] = true;
		cases[milieu-1][milieu+1].murs[0] = true;
	}
}
