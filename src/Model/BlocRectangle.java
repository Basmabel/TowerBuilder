package Model;

import java.awt.Color;
import java.util.HashMap;

public class BlocRectangle {

	private double longueur;
	private double largeur;
	private APoint coord = new APoint(0, 0); // Coordonnées du coin du bloc le plus près de l'origine
													// En bas à gauche si l'origine est en bas à gauche
	private double rho;		// Masse volumique du bloc
	private Color couleur = new Color(0xa2, 0xbf, 0xfe);

	/** 1er Constructeur
	 * Comme les blocs sont créés au niveau du lanceur, on met les coordonnées de celui-ci en paramètre
	 * 
	 * @param xlanceur, position du lanceur en x sur la fenetre
	 * @param longueur
	 * @param largeur
	 * @param masse , masse volumique/surfacique
	 * @param couleur
	 */
	public BlocRectangle(double xlanceur, double longueur, double largeur, double masse, String couleur) {
		this.longueur= longueur;
		this.largeur = largeur;
		this.coord.x = xlanceur-(longueur/2.0);
		this.rho = masse;
		this.couleur = convertisseurcouleur(couleur);
	}
	
	/**2ème Constructeur
	 * 
	 * @param a, APoint recupère les coordonnées du centre
	 * @param masse
	 */
	public BlocRectangle(APoint a) {
		coord.x = a.x;
		coord.y = a.y;
		this.longueur = 100;
		this.largeur = 50;
		this.rho = 40;
	}

	// ----------------------------------- GETTERS && SETTERS && toString ----------------------------------------
	public APoint getCoord() {
		return coord;
	}

	public void setCoord(APoint coord) {
		this.coord = coord;
	}

	public double getLongueur() {
		return longueur;
	}

	public double getLargeur() {
		return largeur;
	}

	public Color getCouleur() {
		return couleur;
	}
	
	@Override
	public String toString() {
		return coord+ " | Longueur : "+longueur+ " | Largeur : " +largeur+ " | Masse volumique : " +rho
				+ " | Couleur : "+couleur ;
	}
	// --------------------------------------- METHODES ---------------------------------------------
	
	/** Comme le bloc est un rectangle homogène, le barycentre est au milieu de celui-ci
	 * 
	 * @return APoint
	 */
	public APoint barycentre () {
		return new APoint(coord.x+longueur/2, coord.y+largeur/2);
	}
	
    /**Renvoie la masse en fonction de la longueur et la largeur.
     * 
     * @return double
     */
	public double masse() {
		return rho*largeur*longueur;
	}

	/**Prend une chaîne de caractère correspondant à une couleur en paramètre
	 * 
	 * Revoie la Couleur correspondante
	 * @param couleur
	 * @return Color
	 */
	public static Color convertisseurcouleur (String couleur) {
		String [] listecouleur = {"Rouge" ,"Bleu" , "Vert" , "Cyan" , "Jaune" , "Rose" , "Orange"};
		Color [] listecolor = {Color.red , new Color(0xa2, 0xbf, 0xfe) , Color.green, Color.cyan, Color.yellow, Color.pink , Color.orange};
		HashMap<String, Color> a = new HashMap<String, Color>();
		int i = 0;
		for (String c: listecouleur) {
			a.put(c, listecolor[i]);
			i++;
		}
		return a.get(couleur);
	}


}
