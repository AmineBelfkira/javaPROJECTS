import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class TestJeuSchelling {

	public static void main(String[] args) {
		int taille=9;
		GUISimulator gui = new GUISimulator(500 , 500 , Color.BLACK ) ;
		JeuSchelling  bal=new JeuSchelling (taille,gui,10,4,10);
		gui.setSimulable(bal);
		
		
		for (int i = 0 ; i < taille ; i++) {
			for (int j = 0 ; j < taille ; j++) {
				if(bal.tableau[i][j].etatApres==0) {
					gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), 
							Color.BLACK,Color.BLACK, 80) ) ;
				}
				else {
						gui.addGraphicalElement(new Rectangle(100*(i+1), 100*(j+1), 
									Color.decode(bal.color(bal.tableau[i][j].etatApres))
										,Color.decode(bal.color(bal.tableau[i][j].etatApres)), 80) ) ;
									//System.out.println(color(this.tableau[i][j].etatApres));
					}
				}
		
		
		}
	}
}

