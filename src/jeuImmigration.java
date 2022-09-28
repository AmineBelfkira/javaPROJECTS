import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;



public class jeuImmigration implements Simulable{
		celluleImmigration[][] tableau;
		int taille;
		GUISimulator gui;
		int etatTotale;
		int i=1;
		
		public jeuImmigration(int taille,GUISimulator g,int etatTotale) {
			this.taille=taille;
			gui=g;
			this.etatTotale=etatTotale;
			tableau=new celluleImmigration[taille][taille];
			for (int i=0;i<taille;i++) {
				for(int j=0;j<taille;j++) {
					Random r = new Random();
					int t=r.nextInt();
					/*
					 * L'etat de chaque cellule prend de manière aléatoire un etat de manière
					 * a ce que le nombre des etats ne depassent pas l'etatTotale fixé par l'utilisateur
					 */
					tableau[i][j]=new celluleImmigration(tableau,i,j,Math.abs(t)%etatTotale,etatTotale);
				
				}
			}
			//on affiche chaque etat et sa couleur correspendante
			for(int i=0;i<etatTotale;i++) {
				System.out.println("etat "+i+" de couleur: "+(Color.decode(color(i))));
			}
		}
		

		public void affiche(GUISimulator g) {
			for (int i = 0 ; i < taille ; i++) {
				for (int j = 0 ; j < taille ; j++) {
					gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), 
							Color.decode(color(this.tableau[i][j].etatApres))
							,Color.decode(color(this.tableau[i][j].etatApres)), 80) ) ;
					}
			
			
			}
		}
		
		public String color(int i) {
			/*
			 * On attribu à chaque etat une couleur qui s'intensifie de manière croissante 
			 * selon l'etat
			 */
			
			String hex=String.valueOf((i%etatTotale)*1000+0xC0C0C0<<2);
			return hex;
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
					tableau[i][j]. etatAvant = tableau[i][j].etatApres;;
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
			gui.reset();
			for (int i=0;i<taille;i++) {
				for(int j=0;j<taille;j++) {
					Random r = new Random();
					int t=r.nextInt();
					tableau[i][j]=new celluleImmigration(tableau,i,j,Math.abs(t)%etatTotale,etatTotale);
				}
			}
			affiche(gui);
			
		}
	    



	}
