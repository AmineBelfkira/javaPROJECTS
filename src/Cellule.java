
public class Cellule {
	// On defini 2 etats: l'etat avant la modification de la cellule et un etat apres l'execution du programme  
	int etatAvant; //Correspend aussi à l'etat actuel de la cellule
	int etatApres;// etat future
	Cellule[][] tableau;//Tableau des cellules initialisé dans la classe Jeu
	int x,y;
	


	
	public Cellule(Cellule[][] tableau, int x, int y, int vivante) {
	        this.tableau = tableau;
	        this.etatAvant = vivante;
	        this.etatApres = vivante;
	        //x et y correspandent au cordonnées de la cellule
	        this.x = x;
	        this.y = y;
	    }
	
	int CalculeDesEtats() {
		int len = tableau.length;
		int nbVivantes=0;
		//On traite l'état des 8 cellules de sorte qu'on compte les cellules dites vivantes
		
		/*
		 * On a pas utiliser une simple boucle for c'est parceque on a eu un probleme pour 
		 * ne pas traiter le cas i=j=1 c'est à dire la cellule en question 
		 */
		if (tableau[(x-1+len)%len][(y-1+len)%len].etatAvant==1) nbVivantes++;
    	if (tableau[(x-1+len)%len][(y+len)%len].etatAvant==1) nbVivantes++;
    	if (tableau[(x-1+len)%len][(y+1+len)%len].etatAvant==1) nbVivantes++;
    	if (tableau[(x+len)%len][(y-1+len)%len].etatAvant==1) nbVivantes++;
    	if (tableau[(x+len)%len][(y+1+len)%len].etatAvant==1) nbVivantes++;
    	if (tableau[(x+1+len)%len][(y-1+len)%len].etatAvant==1) nbVivantes++;
    	if (tableau[(x+1+len)%len][(y+len)%len].etatAvant==1) nbVivantes++;
    	if (tableau[(x+1+len)%len][(y+1+len)%len].etatAvant==1) nbVivantes++;      
    	return nbVivantes;
	}
	
	
	void EtatCellule() {
		//On applique les règles du jeu comme suivant:
	        int nbVivantes = CalculeDesEtats();
	        if (etatAvant==1) {
	        	if(nbVivantes == 2 || nbVivantes == 3) etatApres = 1;
	        	else etatApres = 0;
	        } else {
	        	if (nbVivantes == 3) etatApres = 1;
	        	else etatApres = 0;
	        } 
	       // le traitement ce fait par modification de l'etat à venir
	        
	       
	    }


}
