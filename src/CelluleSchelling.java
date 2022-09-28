import java.util.*;
import java.awt.Point;
public class CelluleSchelling {
	/*
	 * Cette classe est comme celle de la cellule d'immigration toutefois il faut definir un 
	 * tableau de point correspendant au cellules vacantes 
	 */
	int etatAvant; //Correspend aussi à l'etat actuel de la cellule
	int etatApres;// etat future
	int[] tab;//tableau qui va servir à sauvaugarder les etats differents entourant notre cellule
	CelluleSchelling [][] tableau;
	int etatTotale;
	//x et y correspandent au cordonnées de la cellule
	int x,y;
	int K;
	
	Point LesCellulesVacantes[];//tableau de point correspendant au cellules vacantes 


	
	public CelluleSchelling(CelluleSchelling[][] tableau, int x, int y
			,int etat,int etatTotale,int K,Point[] LesCellulesVacantes) {
	        this.tableau = tableau;
	        this.etatAvant = this.etatApres = etat % etatTotale;
	        this.etatTotale=etatTotale;
	        this.x = x;
	        this.y = y;
	        this.K=K;
	        this.LesCellulesVacantes=LesCellulesVacantes;
	 }
	
	
	int etatDiffent() {
		/*
		 * On calcule le nombre d'etat different de l'etat de la cellule et qui ne sont pas vacant
		 */
		int len = tableau.length;
        tab=new int[etatTotale];
        int etatdifferents=0;
        
        if (tableau[(x-1+len)%len][(y-1+len)%len].etatAvant!=etatAvant
        		&& tableau[(x-1+len)%len][(y-1+len)%len].etatAvant!=0) 
        	tab[tableau[(x-1+len)%len][(y-1+len)%len].etatAvant]=1;
    	if (tableau[(x-1+len)%len][(y+len)%len].etatAvant!=etatAvant
    			&& tableau[(x-1+len)%len][(y+len)%len].etatAvant!=0)
    		tab[tableau[(x-1+len)%len][(y+len)%len].etatAvant]=1;
    	if (tableau[(x-1+len)%len][(y+1+len)%len].etatAvant!=etatAvant
    	   && tableau[(x-1+len)%len][(y+1+len)%len].etatAvant!=0) 
    		tab[tableau[(x-1+len)%len][(y+1+len)%len].etatAvant]=1;;
    	if (tableau[(x+len)%len][(y-1+len)%len].etatAvant!=etatAvant
    			&& tableau[(x+len)%len][(y-1+len)%len].etatAvant!=0) 
    		tab[tableau[(x+len)%len][(y-1+len)%len].etatAvant]=1;
    	if (tableau[(x+len)%len][(y+1+len)%len].etatAvant!=etatAvant
    			&& tableau[(x+len)%len][(y+1+len)%len].etatAvant!=0)
    		tab[tableau[(x+len)%len][(y+1+len)%len].etatAvant]=1;
    	if (tableau[(x+1+len)%len][(y-1+len)%len].etatAvant!=etatAvant
    			&& tableau[(x+1+len)%len][(y-1+len)%len].etatAvant!=0)
    		tab[tableau[(x+1+len)%len][(y-1+len)%len].etatAvant]=1;
    	if (tableau[(x+1+len)%len][(y+len)%len].etatAvant!=etatAvant
    			&& tableau[(x+1+len)%len][(y+len)%len].etatAvant!=0) 
    		tab[tableau[(x+1+len)%len][(y+len)%len].etatAvant]=1;
    	if (tableau[(x+1+len)%len][(y+1+len)%len].etatAvant!=etatAvant
    			&& tableau[(x+1+len)%len][(y+1+len)%len].etatAvant!=0) 
    		tab[tableau[(x+1+len)%len][(y+1+len)%len].etatAvant]=1;
    	
        for(int i=0;i<etatTotale;i++) {
        	if(tab[i]==1) {
        		etatdifferents+=1;
        		}
        }
        return etatdifferents;
	}
	
	void EtatCellule() {
		/*
		 * On applique les règle du jeu: si on cas plus de K etat differents la cellule deviendra
		 * vacante et la cellule vacante sera remplacée par cette etat
		 */
		
	        int etatdifferents=etatDiffent();
	        
	        if(etatdifferents>=K) {
	        	etatApres=0;
	        	Random r= new Random();
	        	int p=Math.abs(r.nextInt()%(LesCellulesVacantes.length));
	        	tableau[(int)LesCellulesVacantes[p].getX()][(int)LesCellulesVacantes[p].getY()].etatApres=etatAvant;
	        	LesCellulesVacantes[p]=new Point(x,y);
	        }
	        else etatApres=etatAvant;
	        
	       	}
	
	

   
    



}
