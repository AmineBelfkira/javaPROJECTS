import java.awt.Point;
import java.lang.Math;
/**Cette classe est un vecteur de plusieurs Boids
 *
 */
public class vectBoid {
	private Boid[] vect;//vect est le vecteur de boids
	private int num;//nombre de boids en vecteur
	private int[][] voisins;//tableau qui permet de savoir les voisins d'un boid
	int rayon_voisin=30;//rayon de detection de voisins d'un boid
	/**coefficient pour multiplier les forces d'alignement de cohesion 
	 * et de repulsion car les données sont énormes*/
	double force=0.002; 
	
	/**initialiser le tableaux de voisins à -1
	 * l'idée est que si le boid d'indice i admet pour voisin le voisin
	 * d'indice j la valeur de la case voisins[i][j] est mise en 0
	 */
	public void inittab() {
		for (int i = 0;i < voisins.length; i++) {
	         for (int j = 0;j < voisins.length;j++) {
	            voisins[i][j] = -1;
	            //System.out.print(voisins[i][j] + " ");
	         }
		}
	}
	
	public Boid getVect(int i) {
		return vect[i];
	}
	
	public int getNum() {
		return num;
	}
	
	public int getVois() {
		return rayon_voisin;
	}
	
	public void setVois(int vois) {
		this.rayon_voisin = vois;
	}


	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * Constructor de type classe vectBoid
	 * @param num : numéro de boids à créer
	 * @param x : longueur horizenotale de l'écran de simulation
	 * @param y : longueur verticale de l'écran de simulation
	 */
	public vectBoid(int num,int x,int y) {
		this.vect=new Boid[num];
		setNum(num);
		this.voisins=new int[num][num];
		this.inittab();
		for(int i=0;i<num;i++){
			vect[i]=new Boid(x,y);
		}
	}
	
	/**Verifie si deux point sont des voisins; on calcule la distance entre 
	 * les deux points en parametres, si elle est inferieure à rayon voisin elle 
	 * retourne true sinon retourne un false
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean estVoisin(Point p1,Point p2) {
		int x1=p1.x;int x2=p2.x;int y1=p1.y;int y2=p2.y;
		if(Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2))<rayon_voisin) return true;
		else return false;
	}
	
	/**Remplissage de tableaux de voisins
	 * si le boid d'indice i admet pour voisin le voisin
	 * d'indice j la valeur de la case voisins[i][j] est mise en 0
	 * on distingue le cas si i==j pour ne pas considere un boid voisin de lui même
	 */
	public void VoisBoids() {
		for(int i=0;i<num;i++){
			for(int j=0;j<num;j++) {
				if(i!=j) {
					if(estVoisin(this.vect[i].getPos(),this.vect[j].getPos())) {
						this.voisins[i][j]=0;
					}
				}
			}
		}
		/*for(int i=0;i<num;i++){
			String s=new String();
			for(int j=0;j<num;j++) {
				s+="["+voisins[i][j]+"]";
			}
			System.out.println(s);
		}*/
	}
	
	/**Calcule la forces d'alignement sur un boids appliquée par ses voisins 
	 * on appelle desired le vecteur vitesse souhaité à avoir, ce vecteur est égal à la moyenne
	 * de tous les vecteurs vitesses voisins au Boid
	 * ainsi d'après les lois de Physique; la force finale ajoutée au vecteur acceleration 
	 * d'un boid est la difference 
	 * entre vecteur desired et vecteur vitesse initiale; on n'oublie pas de multiplier par 
	 * le coefficient force pour affaiblir sa valeur
	 */
	public void alignement() {
		Point desired=new Point(0,0);
		for(int i=0;i<num;i++) {
			int nombre_voisins=0;
			for(int j=0;j<num;j++) {
				if(voisins[i][j]==0) {
					desired.translate(vect[j].getVit().x,vect[j].getVit().y);
					nombre_voisins++;
				}
			}
			if(nombre_voisins>0) {
				desired.setLocation(desired.getX()/nombre_voisins,desired.getY()/nombre_voisins);
				vect[i].getAcc().setLocation(vect[i].getAcc().x+(desired.x-vect[i].getVit().x)*force
						,vect[i].getAcc().y+(desired.y-vect[i].getVit().y)*force);
			}
		}
	}
	
	/**Calcule la forces de cohesion sur un boids appliquée par ses voisins 
	 * on appelle desired ,le vecteur position souhaité à avoir, ce vecteur est égal à la moyenne
	 * de tous les vecteurs positions voisins au Boid (Barycentre car masse==1)
	 * ainsi d'après les lois de Physique; la force finale ajoutée au vecteur acceleration 
	 * d'un boid est la difference 
	 * entre vecteur desired et vecteur position initiale; on n'oublie pas de multiplier par 
	 * le coefficient force pour affaiblir sa valeur
	 */
	public void cohesion() {
		Point desired=new Point(0,0);
		for(int i=0;i<num;i++) {
			int nombre_voisins=0;
			for(int j=0;j<num;j++) {
				if(voisins[i][j]==0) {
					desired.translate(vect[j].getPos().x,vect[j].getPos().y);
					nombre_voisins++;
					
				}
			}
			if(nombre_voisins>0) {
				System.out.println(nombre_voisins);
				desired.setLocation(desired.getX()/nombre_voisins,desired.getY()/nombre_voisins);
				vect[i].getAcc().setLocation(vect[i].getAcc().x+(desired.x-vect[i].getPos().x)*force,
						vect[i].getAcc().y+(desired.y-vect[i].getPos().y)*force);
			}
			
		}
	}
	
	/**Calcule la forces de répulsion sur un boids appliquée par ses voisins 
	 * on appelle desired ,le vecteur position souhaité à avoir, ce vecteur est égal 
	 * à la moyenne de la difference antre le vecteur position initiale 
	 * avec tous les vecteurs positions voisins au Boid mulitiplié par -1/rayon_voisins
	 * pour que ce soit une fonction repulsive et que si le boids est si proche 
	 * la force de répulsion devient imortante
	 * ainsi d'après les lois de Physique; la force finale ajoutée au vecteur acceleration 
	 * d'un boid est la difference 
	 * entre vecteur desired et vecteur position initiale; on n'oublie pas de multiplier par 
	 * le coefficient force pour affaiblir sa valeur
	 */

	public void repulsion() {
		Point desired=new Point(0,0);
		for(int i=0;i<num;i++) {
			int nombre_voisins=0;
			for(int j=0;j<num;j++) {
				if(voisins[i][j]==0) {
					Point difference=new Point(vect[j].getPos().x-vect[i].getPos().x,vect[j].getPos().y-vect[i].getPos().y);
					difference.setLocation(difference.x*(1/rayon_voisin), difference.y*(1/rayon_voisin));
					desired.translate(difference.x,difference.y);
					nombre_voisins++;
				}
			}
			if(nombre_voisins>0) {
				desired.setLocation((desired.getX()/nombre_voisins)*force,(desired.getY()/nombre_voisins)*force);
				vect[i].getAcc().setLocation(vect[i].getAcc().x+(desired.x-vect[i].getVit().x)*force
						,vect[i].getAcc().y+(desired.y-vect[i].getVit().y)*force);
			}
		}
	}
	
	/**Cette fonction met àa jour le tableau de voisins de Boid
	 * ,les vecteur d'acceleration de chaque boid en additionant les forces d'alignement
	 * de cohesion et de repulsion, aplliquer les lois de Newton sur le vecteur de Boids
	 * et remet le vecteur acceleration au vecteur nul ainsi qu'initialiser le tableau de voisins
	 */
	public void update(){
		setMaxVit(5);
		this.VoisBoids();
		this.alignement();
		this.cohesion();
		setVois(30);
		this.VoisBoids();
		this.repulsion();
		for(int i=0;i<num;i++) {
			vect[i].setVit(new Point(vect[i].getVit().x+vect[i].getAcc().x,vect[i].getVit().y+vect[i].getAcc().y));
			vect[i].setPos(new Point(vect[i].getPos().x+vect[i].getVit().x,vect[i].getPos().y+vect[i].getVit().y));
			vect[i].setAcc(new Point(0,0));
		}
		inittab();
	}
	
	/**Cette fonction met les vitesse de chaque Boid en du vecteur 
	 * à la vitesse maximale entée en paramètre
	 * @param vitesse_max
	 */
	public void setMaxVit(int vitesse_max) {
		for(int i=0;i<vect.length;i++) {
			vect[i].setvitmax(vitesse_max);
		}
	}
	
	/**Cette fonction se charge de garder les Boids dans l'écran, j'ai choisi de 
	 * ne pas utiliser le rebondissement mais plutot une contuité de Bords de l'écran de simulation; 
	 * si le Boid franchit un Bord il réapparait sur l'autre bord parallèle
	 * @param x: longueur horizontale de l'écran
	 * @param y: longueur verticale de l'ecran
	 */
	public void resterDedans(int x,int y){
		for(int i=0;i<num;i++) {
			if(this.vect[i].getPos().x > x) {
				this.vect[i].getPos().x=Math.abs(this.vect[i].getPos().x)%x;
			}else if(this.vect[i].getPos().x<0) {
				this.vect[i].getPos().x=Math.abs(this.vect[i].getPos().x)%x;
			}
			if(this.vect[i].getPos().y > y) {
				this.vect[i].getPos().y=Math.abs(this.vect[i].getPos().y)%y;
			}else if(this.vect[i].getPos().y < 0) {
				this.vect[i].getPos().y=Math.abs(this.vect[i].getPos().y)%y;
			}
		}
		
	}

	@Override
	public String toString(){
		String strin = new String();
		
		for(int i=0;i<vect.length;i++) {
			strin=strin+"le boid "+i+" a "+vect[i].toString()+"\n";
		}
		return strin;
	}
}
