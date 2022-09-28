import java.awt.Color;

import gui.*;

public class BoidsSimulator implements Simulable{

	private vectBoid b;
	GUISimulator gui;
	int size;
	
	
	public Boid getVect(int i) {
		return b.getVect(i);
	}
	/**Constructor de BoidsSimulaor
	 * 
	 * @param g
	 * @param size : nombre de balles
	 */
	public BoidsSimulator(GUISimulator g,int size) {
		b=new vectBoid(size,g.getPanelWidth(),g.getPanelHeight());
		gui=g;
		this.size=size;
	}
	
	/**Assurer l'affichage des balles sur l'écran de simulation
	 * 
	 * @param g
	 * @param rayon
	 */
	public void affiche(GUISimulator g,int rayon) {
		for (int i = 0 ; i < this.b.getNum() ; i++) {
			gui.addGraphicalElement(
			new Oval(b.getVect(i).getPos().x,b.getVect(i).getPos().y,
			Color.decode("#1f77b4"),Color.decode("#1f77b4"),rayon) ) ;
		}
	}
	
	/**on efface l'ecran, garder les boids à l'intérieur
	 * mettre à jour les forces exercées sur les Boids
	 * et les afficher
	 */
	@Override
	public void next() {
		// TODO Auto-generated method stub
		gui.reset();
		b.resterDedans(gui.getPanelWidth(),gui.getPanelHeight());
		b.update();
		System.out.println(b);
		affiche(gui,10);
	}
	
	@Override
	public void restart() {
		// TODO Auto-generated method stub
		b=new vectBoid(size,gui.getPanelWidth(),gui.getPanelHeight());
		affiche(gui,10);
	}

}