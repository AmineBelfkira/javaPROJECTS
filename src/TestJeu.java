import gui.*;

import java.awt.Color;

public class TestJeu {
	//ON test ainsi notre jeu
	public static void main(String[] args) {
		int taille=5;
		GUISimulator gui = new GUISimulator(500 , 500 , Color.BLACK ) ;
		jeu bal=new jeu(5,gui);
		gui.setSimulable(bal);
		
		
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille ; j++) {
				if (bal.tableau[i][j].etatApres==0) {
					gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), Color.WHITE, Color.WHITE, 80) ) ;
				}
				else {
					gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1),Color.decode("#1f77b4"),Color.decode("#1f77b4"),80) ) ;
				}
		
			}
		}
		}

	

}

