public class celluleImmigration {
	/*
	 * La definition de la cellule d'immigration ce fera comme dans le cas précédent sauf que 
	 * dans ce cas la cellule prendra plusieur etat (le nombre d'état sera precisé par 
	 * l'utilisateur).

	 */
	int etatAvant;
	int etatApres;
	celluleImmigration [][] tableau;//Tableau des cellules immigration dans la classe Jeu immigration
	int etatTotale;
	int x,y;//cordonnées de la cellule dans le tableau


	
	public celluleImmigration(celluleImmigration [][] tableau, int x, int y, int etat,int etatTotale) {
	        this.tableau = tableau;
	        this.etatAvant = etat;//Correspend aussi à l'etat actuel de la cellule
	        this.etatApres = etat;// etat future
	        this.etatTotale=etatTotale;
	        this.x = x;
	        this.y = y;
	    }
	
	int nbEtatsplusun() {
		/*
		 * le traitement de la cellule consiste à calculer le nombre d'etat k+1 
		 */
		int len = tableau.length;
		int EtatKPlusUn=0;
		/*
		 * On a pas utiliser une simple boucle for c'est parceque on a eu un probleme pour 
		 * ne pas traiter le cas i=j=1 c'est à dire la cellule en question 
		 */
		if (tableau[(x-1+len)%len][(y-1+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	if (tableau[(x-1+len)%len][(y+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	if (tableau[(x-1+len)%len][(y+1+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	if (tableau[(x+len)%len][(y-1+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	if (tableau[(x+len)%len][(y+1+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	if (tableau[(x+1+len)%len][(y-1+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	if (tableau[(x+1+len)%len][(y+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	if (tableau[(x+1+len)%len][(y+1+len)%len].etatAvant==(etatAvant+1)) EtatKPlusUn++;
    	return EtatKPlusUn;
	}
	
	void EtatCellule() {
	        //On applique les régles du jeu càd si on a au moin 2 état k+1 
	        int EtatKPlusUn=nbEtatsplusun();
	        
	        if (EtatKPlusUn>2) {
	            etatApres =(etatAvant+1)%etatTotale;
	        }
	       
	    }
    
 

}


