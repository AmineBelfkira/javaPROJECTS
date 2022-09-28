import java.awt.Point;
import java.lang.Math;
import java.util.Random;
public class TestBoids {

	public static void main(String[] args) {
		vectBoid Bo=new vectBoid(4,50,24);
		System.out.println(Bo);
		Bo.VoisBoids();
		System.out.println(Bo);
		//Point p1=new Point(0,0);
		//Point p2=new Point(5,5);
		Bo.setMaxVit(20);
		System.out.println(Bo);
		
		//System.out.println(estVoisin(p1,p2));
		
		
		
	}

}
