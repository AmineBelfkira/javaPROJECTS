
import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class JeuSchelling implements Simulable{
	CelluleSchelling[][] tableau;
	int taille;
	GUISimulator gui;
	int etatTotale;
	int K;
	Point LesCellulesVacantes[];
	int NombreCelluleVac;
	int i=1;
	
	public JeuSchelling(int taille,GUISimulator g,int etatTotale,int K,int NombreCelluleVac) {
		this.taille=taille;
		this.NombreCelluleVac=NombreCelluleVac;//on initialise le nombre de cellules vacantes
		gui=g;
		int nbr=0;
		this.K=K;
		this.etatTotale=etatTotale+1;
		LesCellulesVacantes=new Point[NombreCelluleVac];
		//On initialise les cellules vacantes de manière aleatoire dans le tableau 
		while(nbr<NombreCelluleVac) {
			Random r = new Random();
			int i=Math.abs(r.nextInt()%taille);
			int j=Math.abs(r.nextInt()%taille);
			if (!find(LesCellulesVacantes,i,j,nbr)){//pour ne pas ecraser une cellule déjà vacante
				LesCellulesVacantes[nbr]=new Point(i,j);
				nbr+=1;
			}
		}
		tableau=new CelluleSchelling[taille][taille];
		for (int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				Random r = new Random();
				int t=Math.abs(r.nextInt())%etatTotale;
				//System.out.println(t);
				while(t==0) {
					t=Math.abs(r.nextInt())%etatTotale;//Car on itialise comme si on a pas de cellules non vacantes
				}
				tableau[i][j]=new CelluleSchelling(tableau,i,j,t,etatTotale,K,LesCellulesVacantes);
				
			}
		
		}
		//les cellules vouée à devenir vacante vont prendre un etat null
		for (int i=0;i<NombreCelluleVac;i++) {
			tableau[(int)LesCellulesVacantes[i].getX()][(int)LesCellulesVacantes[i].getY()].etatAvant=0;
			tableau[(int)LesCellulesVacantes[i].getX()][(int)LesCellulesVacantes[i].getY()].etatApres=0;
		}
		
	}
	
	public boolean find(Point[] t,int i,int j,int nbr) {
		/*
		 * fonction utilisée lors de la création du tableau des cellules vacantes
		*/
		for (int k=0;k<nbr;k++) {
			if ((int)t[k].getX()==i && (int)t[k].getY()==j) return true;
		}
		return false;
	}

	public void affiche(GUISimulator g) {
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille ; j++) {
				if(this.tableau[i][j].etatApres==0) {
					//les cellules vacantes vont prendre une couleur noir
					gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), 
							Color.BLACK
								,Color.BLACK, 80) ) ;
				}
				else {
					//les autres cellules prendront une couleur selon leur etat et selon la fonction color() 
						gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), 
									Color.decode(color(this.tableau[i][j].etatApres))
										,Color.decode(color(this.tableau[i][j].etatApres)), 80) ) ;
									//System.out.println(color(this.tableau[i][j].etatApres));
					}
				}
		
		
		}
	}
	
	public String color(int i) {
		String hex=String.valueOf((i%etatTotale)*10000+0xC0C0C0<<2);
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
				if(tableau[i][j].etatAvant!=0) {
				tableau[i][j].EtatCellule();
				}
			}
        }
		
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille; j++) {
				tableau[i][j].etatAvant=tableau[i][j].etatApres;
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
		int nbr=0;
		while(nbr<NombreCelluleVac) {
			Random r = new Random();
			int i=Math.abs(r.nextInt()%taille);
			int j=Math.abs(r.nextInt()%taille);
			if (!find(LesCellulesVacantes,i,j,nbr)){
				LesCellulesVacantes[nbr]=new Point(i,j);
				nbr+=1;
			}
		}
		tableau=new CelluleSchelling[taille][taille];
		for (int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				Random r = new Random();
				int t=Math.abs(r.nextInt())%etatTotale;
				System.out.println(t);
				
				while(t==0) {
					t=Math.abs(r.nextInt())%etatTotale;
				}
				tableau[i][j]=new CelluleSchelling(tableau,i,j,t,etatTotale,K,LesCellulesVacantes);
				
			}
		
		}
		for (int i=0;i<NombreCelluleVac;i++) {
			tableau[(int)LesCellulesVacantes[i].getX()][(int)LesCellulesVacantes[i].getY()].etatAvant=0;
			tableau[(int)LesCellulesVacantes[i].getX()][(int)LesCellulesVacantes[i].getY()].etatApres=0;
		}
		affiche(gui);
		
	}
    



}



