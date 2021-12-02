package Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import graphique.MenuProfil;
import controller.Music;;
/**Classe permettant de cr�er de mani�re algorithmique (que les calculs)
 * Tout ce qu'il faut pour organiser notre jeu.
 * 
 * @author Fondateurs du Tower
 */
public class Monde extends Observable implements ActionListener {

	private LinkedList<BlocRectangle> tour = new LinkedList<BlocRectangle>();	
	// Liste des blocs de la tour
	private LinkedList <Profil> profil = new LinkedList<Profil>();		
	// Liste des profils utilisateurs
	private double xlanceur = 0;	
	// Position du lanceur de blocs
	private double longueur;		
	// Longueur du bloc qui sera lanc�
	private double largeur;			
	// Largeur  "    "   "    "    "
	private double rho;				
	// Masse volumique   "    "    "
	private double width= 0;		
	// Largeur de l'�cran de jeu
	private double height = 0;		
	// Hauteur de l'�cran de jeu
	private double angle= Math.PI/2.0;	
	// Utilis� pour l'animation de la chute de la tour
	private int ajout = 2;			
	// Valeur pour le d�calage du lanceur � chaque it�ration 
	private int nb=0;
	private boolean etat;			
	// D�crit si on peut cliquer ou non avec la souris.
	private boolean tomb = false;	
	// true si un bloc est en pleine chute
	private boolean mauv = false;	
	// true si le bloc du dessus n'est pas bien plac�, i.e. la tour est en d�s�quilibre 
	private boolean chute = false;	
	// true si la tour est en animation de chute
	private boolean pause = false;	
	// permet de detecter une pause et bloquer certains mecanismes.
	private boolean testscroll = false;	
	// Si la tour est trop haute sur l'ecran, on baisse la tour.
	private boolean delay = false;
	// Empeche de gameover et donc reset, si la tour est entrain de s'effrondrer.
	private String couleur = "rouge";
	private Timer lanceur;			
	// Timer utilis� pour it�rer chaque d�placement
	private Profil connect;
	// Profil connect� � un moment donn�
	private Music gameover = new Music ("Ressources/gameover.wav");
	private Music musicjeu = new Music ("Ressources/MusicJeu.wav");

	/** Constructeur du Monde
	 * R�cup�ration des donn�es stock�es pour les profils.
	 * Incr�mentation d'une valeur par d�faut pour les longueurs, largueurs et la masse volumique.
	 * Connexion du profil par d�faut <Connectez-vous>
	 * Initilisation du Timer, g�rant toutes les actions du fichier. On utilise un timer pour toutes nos actions.
	 */
	public Monde() {
		deserialiser();
		setLongueur(100);
		setLargeur(50);
		setConnect(new Profil());
		lanceur = new Timer(10,this);
	}

	// ---------------------------------- Gestion Serialisation ------------------------------------

	/** M�thode permettant de sauvgarder les modifications des profils
	 */
	public void serialiser () {
		File fichier = new File ("Ressources/profil.txt");
		fichier.delete();
		FileOutputStream file;
		try {
			file = new FileOutputStream("Ressources/profil.txt");		
			ObjectOutputStream a = new ObjectOutputStream(file);
			a.writeObject(this.profil);
			a.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** M�thode permettant de r�cup�rer les informations des profils
	 */
	@SuppressWarnings("unchecked")
	public void deserialiser () {
		try {
			File fichier = new  File ("Ressources/profil.txt");
			//System.out.println(fichier.getAbsolutePath());
			FileInputStream file = new FileInputStream(fichier.getAbsoluteFile());		
			ObjectInputStream a = new ObjectInputStream(file);
			this.profil = (LinkedList<Profil>) a.readObject();
			a.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** M�thode permettant de supprimer toutes les informations du fichier text.
	 */
	public void clearprofil () {
		profil.clear();
		serialiser();
	}

	// ----------------------------------- GETTERS & SETTERS ----------------------------------------

	public LinkedList<BlocRectangle> getTour() {
		return tour;
	}

	public boolean getEtat() {
		return etat;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getWidth() {
		return width;
	}

	public Timer getLanceur() {
		return lanceur;
	}

	public double getXlanceur() {
		return xlanceur;
	}

	public void setXlanceur(double xlanceur) {
		this.xlanceur = xlanceur;
	}

	public LinkedList<Profil> getProfil() {
		return profil;
	}

	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public void setVitesse(int vit) {
		lanceur.setDelay(vit);
	}

	public Profil getConnect() {
		return connect;
	}

	public void setConnect(Profil connect) {
		this.connect = connect;
		this.setChanged();
		this.notifyObservers(true);
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	//---------------------------- GESTION COMMANDES JEUX (Jouer , restart ....) -----------------------

	public void pause () {
		lanceur.stop();
		pause = true;
		etat= false;
	}

	public void reprendre () {
		lanceur.start();
		pause = false;
		etat= true;
	}

	public void reset () {
		gameover.stop();
		chute = false;
		pause = false;
		tomb = false;
		tour.clear();
		lanceur.stop();
		xlanceur = width/2.0;
		angle= Math.PI/2.0;
		this.setChanged();
		this.notifyObservers(false);
	}

	public void play () {
		if (!connect.getPseudo().equals("Connectez-vous")) {
			musicjeu.startLoop();
			tomb = false;
			tour.clear();
			tour.add(new BlocRectangle(new APoint(width/2.0, height - 50)));
			System.out.println(height-50);
			setXlanceur(width/2.0);
			lanceur.start();
			etat = true;
			this.setChanged();
			this.notifyObservers(false);
		} else {
			JOptionPane.showOptionDialog(null, "Veuillez  vous connecter avant de jouer","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			new MenuProfil(this);
		}

	}

	// --------------------------- Gestion Equilibre & positionnement & GameOver -------------------------------------

	/** V�rifie si le bloc tombe bien sur le bloc en haut de la tour, sinon il tombe dans le vide et on met fin � la partie
	 * 
	 */
	public void testBloc () {
		APoint coordancien = tour.get(tour.size()-1).getCoord();
		double longueurAncien = tour.get(tour.size()-1).getLongueur();
		tour.add(new BlocRectangle(xlanceur, longueur, largeur, rho , couleur));
		if ((xlanceur - longueurAncien/2.0 >= coordancien.x) && (xlanceur - longueurAncien/2.0 <= coordancien.x + longueurAncien) 
				|| (xlanceur + longueurAncien/2.0 <= coordancien.x + longueurAncien) && (xlanceur + longueurAncien/2.0 >= coordancien.x)) {
			tomb= true;
			etat = false;
		} else {
			etat = false;
			mauv = true;
			musicjeu.stop();
			gameover.startLoop();
			gameover();
		}            
		this.setChanged();
		this.notifyObservers(false);
	}

	/** Simulation de la Chute selon un arc de cercle, si l'equilibre n'est pas bon.
	 * 
	 */
	public void gameoverEq() {
		etat = false;
		double c = 0;
		for (BlocRectangle r : tour) {
			
			if (r.getCoord().y > c && r.getCoord().y <= height - r.getLargeur()) {
				c = r.getCoord().y + r.getLargeur();
			}
		}
		APoint coorddernier = tour.get(tour.size()-1).getCoord();
		int i=1;
		if(tour.get(tour.size()-1).barycentre().x >= tour.get(tour.size()-2).barycentre().x ) {
			for(BlocRectangle rectangle : tour){
				System.out.println(rectangle.getCoord().y+"||" + height);
				if(rectangle.getCoord().y + rectangle.getLargeur() <= height-rectangle.getLargeur()){
					APoint a = new APoint((c- tour.get(i-1).getCoord().y)*Math.cos(angle)+tour.get(i-1).getCoord().x,c-(c- tour.get(i-1).getCoord().y)*(1-Math.sin(angle)));
					rectangle.setCoord(a);
					angle= angle- Math.PI/24.0;
					this.setChanged();
					this.notifyObservers(false);
				}
				i++;
			}
			
			if (tour.get(tour.size()-1).getCoord().equals(coorddernier)){
				chute = false;
				delay = true;
			}
			
		}else{
			for(BlocRectangle rectangle : tour){
				System.out.println(rectangle.getCoord().y + "||" + rectangle.getLargeur()+ "||" + height);
				if(rectangle.getCoord().y + rectangle.getLargeur() <= height - rectangle.getLargeur()){
					APoint a = new APoint(-(c- tour.get(i-1).getCoord().y)*Math.cos(angle)+tour.get(i-1).getCoord().x,c-(c- tour.get(i-1).getCoord().y)*(1-Math.sin(angle)));
					rectangle.setCoord(a);
					angle= angle- Math.PI/24.0;		
					this.setChanged();
					this.notifyObservers(false);
				}
				i++;
			}

			if(tour.get(tour.size()-1).getCoord().equals(coorddernier)) {
				chute = false;
				delay = true;
			}

		}

	}

	/** Si la partie est perdu
	 * 
	 * Arret de la  musique + interdiction de cliquer et donc ajouter des blocs
	 * Sauvegarde du score du joueur.
	 */
	public void gameover() {
		musicjeu.stop();
		gameover.startLoop();
		etat = false;
		JOptionPane.showOptionDialog(null, "Game Over, You Loose","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		if (getConnect().getScore() < tour.size()-1) {
			getConnect().setScore(tour.size()-1);
			serialiser();
			this.setChanged();
			this.notifyObservers(true);
		}
		delay = false;
		reset();
	}

	/** V�rifie que la tour est en �quilibre
	 * @return true si la tour est en �quilibre
	 * @return false sinon
	 */
	public boolean testEq(){

		int i = tour.size()-1;								
		// Incr�menteur de la boucle
		APoint blocsBarycentre = tour.get(i).barycentre();	
		// Barycentre du groupe de blocs, initialis� � celui du bloc

		// le plus haut
		double masseBlocs = tour.get(i).masse();			
		// Masse du groupe de blocs, initialis�e � celle du bloc le

		// plus haut
		BlocRectangle blocSuivant;

		do{
			i--;
			blocSuivant = tour.get(i);	
			// initalis� au second bloc en partant du haut

			// Si le barycentre du/des bloc(s) du dessus n'est pas au-dessus du bloc suivant, la tour n'est pas en �quilibre
			if((blocsBarycentre.x>(blocSuivant.barycentre().x+blocSuivant.getLongueur()/2)) ||
					(blocsBarycentre.x<(blocSuivant.barycentre().x-blocSuivant.getLongueur()/2)))
			{return false;}

			// Sinon, on ajoute le bloc du dessous � ce groupe de bloo, avant de parcourir la boule avec le bloc en dessous
			// de celui-ci
			blocsBarycentre = barycentreCommun(blocsBarycentre, masseBlocs, blocSuivant.barycentre(), blocSuivant.masse());
			masseBlocs += blocSuivant.masse();
		}while(i>0);
		// Si le bloc tout en bas est en dessous du barycentre commun de tous les autres blocs (dernier parcours de la boucle)
		// alors la tour est en �quilibre
		return true;
	}

	/**Renvoie le barycentre commun entre 2 points
	 * 
	 * @param b1 Point 1 
	 * @param m1, masse associ�e au point 1	 
	 * @param b2, Point 2 
	 * @param m2, masse associ�e au point
	 * @return APoint , le barycentre
	 */
	public  APoint barycentreCommun(APoint b1, double m1, APoint b2, double m2){
		//Ici on retourne directement le r�sultat gr�ce � la formule des barycentres
		return new APoint((m1*b1.x + m2*b2.x)/(m1+m2), (m1*b1.y + m2*b2.y)/(m1+m2));
	}

	/** D�cale la tour vers le bas lorsque que l'on pose un bloc, afin de pr�server la distance entre le haut de la tour
	 * et le lanceur
	 */
	public void scroll () {
		testscroll = (tour.get(tour.size()-1).getCoord().y <= width/2.0);
		for (BlocRectangle c : tour ) {
			c.setCoord(new APoint(c.getCoord().x , c.getCoord().y + 10));
		}
		this.setChanged();
		this.notifyObservers(false);
	}

	// ------------------------------ Gestion Timer, Lanceur , Tomber ---------------------------------------------

	/**D�cale le lanceur en fonction de sa position
	 * 
	 */
	public void deplaceLanceur () {
		if ((getXlanceur() + 35 + Math.abs(ajout) >= width) || (getXlanceur() - 35 - Math.abs(ajout) <= 0)) {
			ajout = -ajout;
		}
		setXlanceur(getXlanceur() + ajout);
		this.setChanged();
		this.notifyObservers(false);
	}
	/** Si le bloc tombe sur un bloc de la tour et donc le positionnement est valide.
	 * 
	 */
	public void tomberBlocSimpleBonPlacement () {
		if (tour.get(tour.size()-1).getCoord().y >= tour.get(tour.size()-2).getCoord().y - tour.get(tour.size()-1).getLargeur()) {
			tour.get(tour.size()-1).setCoord(new APoint(tour.get(tour.size()-1).getCoord().x, tour.get(tour.size()-2).getCoord().y - tour.get(tour.size()-1).getLargeur()));           
			tomb=false;
			etat = true;
			if(!testEq()) {
				chute= true;
			}
			testscroll = (tour.get(tour.size()-1).getCoord().y <= width/2.0);
		} else {
			APoint a =  tour.get(tour.size()-1).getCoord();
			tour.get(tour.size()-1).setCoord(new APoint(a.x, a.y+5));
		}        
		this.setChanged();
		this.notifyObservers(false);
	}

	/** Si le joueur place mal son bloc et donc il a perdu.
	 * 
	 */
	public void tomberBlocSimpleMauvaisPlacement () {
		if (tour.get(tour.size()-1).getCoord().y >= height - tour.get(tour.size()-1).getLargeur()) {
			tour.get(tour.size()-1).setCoord(new APoint(tour.get(tour.size()-1).getCoord().x,height-tour.get(tour.size()-1).getLargeur())); 
			mauv = false;
		} else {
			APoint a =  tour.get(tour.size()-1).getCoord();
			tour.get(tour.size()-1).setCoord(new APoint(a.x, a.y+5));
		}		
		this.setChanged();
		this.notifyObservers(false);
	}

	/** Gestion du timer et des actions via une variable n
	 * 
	 * En fonction de la valeur de n  et de l'etat des booleans on r�alise certaines actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(lanceur)) {
			deplaceLanceur();
			nb++;
			if(tomb && (nb%2 == 0)){
				tomberBlocSimpleBonPlacement();
			}
			if(mauv && (nb%2 == 0)){
				tomberBlocSimpleMauvaisPlacement();
			}
			if(chute && (nb%100 == 0)) {
				gameoverEq();
			}
			if (testscroll && (nb%10 == 0) ) {
				scroll();
			}
			if (delay && (nb%10 == 0) ) {
				gameover();
			}
		}

	}
}


