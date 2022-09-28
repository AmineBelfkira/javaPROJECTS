import java.awt.Color;

import gui.GUISimulator;
import gui.Oval;

public class TestBoidsSimulateur {

	public static void main(String[] args) {
		
		int size=50;
		GUISimulator gui = new GUISimulator(500, 500 , Color.BLACK ) ;
		BoidsSimulator bal= new BoidsSimulator(gui,size);
		gui.setSimulable(bal);
		for (int i = 0 ; i < size ; i++) {
				gui.addGraphicalElement(
				new Oval(bal.getVect(i).getPos().x,bal.getVect(i).getPos().y,
				Color.decode("#1f77b4"),Color.decode("#1f77b4"),10) ) ;
		}

	}

}
