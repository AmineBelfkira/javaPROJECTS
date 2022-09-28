import java.awt.Point;
public class Balls {
	private Point balles[];//vecteur de balles
	private Point posin= new Point(100,100);//valeur dee laposition initiale de la premiere balle
	int size=5;//nombre de balles au cas où ce n'est pas initialisé 
	/**ce tableau est utilisé pour savoir le bord de rebondissement de la balles
	pour distinguer le cas de déplacement positif ou negatif suivant les deux axes*/
	int tab[][]=new int[size][2];
	
	public void setSize(int i) {
		size=i;
	}
	
	public int getsize() {
		return balles.length;
	}
	
	public int getX(int i){
		if(i>balles.length) {
			throw new IllegalArgumentException("le nombre dépasse la taille du tableau");
		}else {
		return (int) balles[i].getX();
		}
	}
	public int getY(int i){
		if(i>balles.length) {
			throw new IllegalArgumentException("le nombre dépasse la taille du tableau");
		}else {
		return (int) balles[i].getY();
		}
	}
	/**construit un vecteur de balles alignés sur la même ligne horizeontale
	 * et espacées de +100 horizontalement
	 * @param size
	 */
	public Balls(int size) {
		this.balles=new Point[size];
		for(int i=0;i<balles.length;i++) {
			Point pos= new Point(posin);
			pos.translate(i*100,0);
			this.balles[i]=new Point(pos);
			this.size=size;
		}
	}
	
	/**Assurer le rebondissement des balles sur 
	 * les bords de l'écran de simulation
	 * @param dx
	 * @param dy
	 * @param lon
	 * @param lar
	 * @param rayon
	 */
	void translate(int dx,int dy,int lon,int lar,int rayon) {
		
		for(int i=0;i<size;i++) {
			if((int)this.balles[i].getX()+dx+rayon>lon && tab[i][0]==0) tab[i][0]=1;
			if((int)this.balles[i].getY()+dy+rayon>lar && tab[i][1]==0) tab[i][1]=1;
			if((int)this.balles[i].getX()-dx<rayon && tab[i][0]==1) tab[i][0]=0;
			if((int)this.balles[i].getY()-dy<rayon && tab[i][1]==1) tab[i][1]=0;
			if(tab[i][0]==0 && tab[i][1]==0) this.balles[i].translate(dx, dy);
			if(tab[i][0]==1 && tab[i][1]==0) this.balles[i].translate(-dx, dy);
			if(tab[i][0]==0 && tab[i][1]==1) this.balles[i].translate(dx,-dy);
			if(tab[i][0]==1 && tab[i][1]==1) this.balles[i].translate(-dx,-dy);
		}
	}
	
	/**Remettre toutes les balles dans leurs positions initiales
	 * 
	 */
	void reInit() {
		for(int i=0;i<balles.length;i++) {
			Point pos= new Point(posin);
			pos.translate(i*100,0);
			this.balles[i].setLocation(pos.getX(), pos.getY());
			//System.out.println(this.balles[i]);
		}
	}
	
	@Override
	public String toString(){
		String position = new String();
		for(int i=0;i<balles.length;i++) {
			position=position+balles[i].toString();
		}
		return position;
	}
}
