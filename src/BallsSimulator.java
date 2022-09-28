import java.awt.Color;

import gui.*;

public class BallsSimulator implements Simulable {
	private Balls b;//=new Balls(3);
	GUISimulator gui;
	
	/*public void setSize(int i) {
		b.setSize(i);
	}*/

	/**Constructor de vecteur de balles à simuler
	 * 
	 * @param g
	 * @param size
	 */
	public BallsSimulator(GUISimulator g,int size) {
		b=new Balls(size);
		gui=g;
		b.setSize(size);
	}
	/**Assurer l'affichage des balles sur l'écran de simulation
	 * 
	 * @param g
	 * @param rayon
	 */
	public void affiche(GUISimulator g,int rayon) {
		for (int i = 0 ; i < this.b.getsize()  ; i++) {
			gui.addGraphicalElement(
			new Oval(b.getX(i),b.getY(i),
			Color.decode("#1f77b4"),Color.decode("#1f77b4"),rayon) ) ;
		}
	}
	

	public int getX(int i){
		return b.getX(i);
	}
	
	public int getY(int i){
		return b.getY(i);
	}
	/**
	 * ici le vecteur déplacement des balles est toujours egal à 10
	 * 
	 */
	@Override 
	public void next(){
		b.translate(10, 10,gui.getPanelHeight(),gui.getPanelWidth(),20);
		System.out.println(b);
		//System.out.println(gui.getPanelHeight());
		//System.out.println(gui.getPanelWidth());
		gui.reset();
		affiche(gui,20);
	}
	
	@Override
	public void restart(){
		b.reInit();
		System.out.println(b);
		gui.reset();
		affiche(gui,20);
	}
}
