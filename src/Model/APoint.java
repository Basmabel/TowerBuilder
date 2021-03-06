package Model;
/**
 * Classe permettant de gérer un point dans un espace 2D
 * @author Equipe pédagogique IF2A
 */ 

public class APoint {

	// Attributs de la Classe
	public double x;
	public double y;

	/**
	 * Le constructeur
	 * @param les coordonnées du point
	 */
	public APoint(double ax, double ay){
		x = ax;
		y = ay;
	}

	/**
	 * pour calculer la distance euclidienne par rapport à un autre point
	 * @param le point à partir duquel il faut calculer la distance
	 * @return la distance euclidienne
	 */        
	public double distance( APoint otherPoint ) {
		double dx = x - otherPoint.x;
		double dy = y - otherPoint.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Pour afficher les coordonnées du point
	 * @return les coordonnées du point sous la forme [x=1.0,y=1.0]
	 */
	public String toString() {
		String res ="";
		res="[x=" + x + ",y=" + y + "]";
		return res;  
	}    
}

