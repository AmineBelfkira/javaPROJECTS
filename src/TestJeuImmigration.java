import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class TestJeuImmigration {
	public static void main(String[] args) {
		//On test le jeu d'immigration
		int taille=5;
		GUISimulator gui = new GUISimulator(500 , 500 , Color.BLACK ) ;
		jeuImmigration bal=new jeuImmigration(taille,gui,4);
		gui.setSimulable(bal);
		
		
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille ; j++) {
				gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), Color.decode(bal.color(bal.tableau[i][j].etatApres))
						,Color.decode(bal.color(bal.tableau[i][j].etatApres)), 80) ) ;
				}	
		}
			
	}
}





