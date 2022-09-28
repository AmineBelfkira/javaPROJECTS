
import java.awt.Color;
import java.util.Random;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;


public class jeu implements Simulable{
	Cellule[][] tableau;
	int taille;
	GUISimulator gui;
	int i=1;
	
	
	public jeu(int taille,GUISimulator g) {
		this.taille=taille;
		gui=g;
		tableau=new Cellule[taille][taille];
		for (int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				Random r = new Random();
				//L'etat de chaque cellule prend de manière aléatoire l'etat vivant ou mort
				tableau[i][j]=new Cellule(tableau,i,j,Math.abs(r.nextInt()%2));
			//exemple projet:
			//On prend l'exemple du sujet pour le test
			
			if(i==1 && j==1) tableau[i][j]=new Cellule(tableau,i,j,1);
			else if(i==1 && j==2) tableau[i][j]=new Cellule(tableau,i,j,1);
			else if(i==2 && j==1) tableau[i][j]=new Cellule(tableau,i,j,1);
			else if(i==3 && j==2) tableau[i][j]=new Cellule(tableau,i,j,1);
			else if(i==4 && j==4) tableau[i][j]=new Cellule(tableau,i,j,1);
			else tableau[i][j]=new Cellule(tableau,i,j,0);
			
			
			}
		}
	}
	
	public void affiche(GUISimulator g) {
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille ; j++) {
				if (tableau[i][j].etatApres==0) {
					gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), Color.WHITE, Color.WHITE, 80) ) ;
				}
				else {
					gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1),Color.decode("#1f77b4"),Color.decode("#1f77b4"),80) ) ;
				}
		
			}
		}
		
		
	}
	
	
	public void play(){
		/*
		 * Le jeu se fait comme suite :
		 * 1/ On determine l'etat à venir de chaque cellule de tableau 
		 * 2/ Après determination, on applique les modification c'est à dire chaque cellule prend l'etat suivant
		 */
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille ; j++) {
				tableau[i][j].EtatCellule();
			}
        }
		
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille; j++) {
				tableau[i][j].etatAvant = tableau[i][j].etatApres;
			}
        }
	}

   
 
	@Override 
	public void next(){
		this.play();
		gui.reset();
		affiche(gui);
		System.out.println("Étape "+i);
		i++;
	}
	
	
	@Override 
	public void restart(){
		for (int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				Random r = new Random();
				tableau[i][j]=new Cellule(tableau,i,j,Math.abs(r.nextInt()%2));;
			
			}
		}
		gui.reset();
		affiche(gui);
		
	}
    



}
