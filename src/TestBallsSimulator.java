import gui.GUISimulator ;
import gui.Oval;

import java.awt.Color;

public class TestBallsSimulator{
	public static void main(String[] args) {
		int size=5;
		GUISimulator gui = new GUISimulator(500 , 1000 , Color.BLACK ) ;
		BallsSimulator bal= new BallsSimulator(gui,size);
		gui.setSimulable(bal);
		for (int i = 0 ; i < size ; i++) {
				gui.addGraphicalElement(
				new Oval(bal.getX(i),bal.getY(i),
				Color.decode("#1f77b4"),Color.decode("#1f77b4"),20) ) ;
		}
	}
}
