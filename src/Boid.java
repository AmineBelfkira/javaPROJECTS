import java.awt.Point;
//import java.lang.Math;
import java.util.Random;

/**Cette classe définit un Boid avec ses vecteurs
 * vitesse position et acceleration
 * @author boussemid
 *
 */
public class Boid {
	private Point pos; //vecteur position
	private Point vit; //vecteur vitesse
	private Point acc; //vecteur acceleration
	int vois=10;
	
	
	public Point getAcc() {
		return acc;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public void setVit(Point vit) {
		this.vit = vit;
	}

	public void setAcc(Point acc) {
		this.acc = acc;
	}

	public void setVois(int vois) {
		this.vois = vois;
	}

	public int getVois() {
		return vois;
	}

	
	
	public Point getPos() {
		return pos;
	}
	
	public Point getVit() {
		return vit;
	}
	
	/**cette fonction nous permet de mettre un 
	 * boid à une vitesse spécifique
	 * @param v
	 */
	public void setvitmax(int v){
		/**pour le cas où la vitesse est nulle je mets le boid à une vitesse 
		positive suivant x et y de norme v en parametre*/
		if(vit.x==0 && vit.y==0){
			vit.setLocation(v/Math.sqrt(2),v/Math.sqrt(2));
		}
		/**Dans l'autre cas je multilie le vecteur vitesse
		 * du boid par le coefficient Vinitiale/Vparametre
		 * pour mettre le vecteur à la norme Vparam
		 */
		else{
			double norme_vitesse= Math.sqrt(Math.pow(vit.x,2)+Math.pow(vit.y,2));
			vit.setLocation(vit.x*v/norme_vitesse,vit.y*v/norme_vitesse);
		}
	}
	
	/**
	 * Constructor de boid avec la position comprise entre x et y
	 * et vecteur vitesse aleatoire et petit.
	 * @param x
	 * @param y
	 */
	public Boid(int x,int y/*int x,int y,int vx,int vy*/) {
		Random r=new Random();
		this.pos=new Point(Math.abs(r.nextInt(x)),Math.abs(r.nextInt(y)));
		this.vit=new Point(Math.abs((r.nextInt())%3),Math.abs((r.nextInt())%3));
		this.acc=new Point(0,0);
	}
	
	
	@Override
	public String toString() {
		return "position:"+this.pos.toString()+"à vitesse:"+this.vit.toString();
	}
	
	
}
