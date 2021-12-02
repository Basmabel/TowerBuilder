package graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Profil;

public class Classement extends JFrame {
	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;

	private LinkedList <Profil> listeprofil = new LinkedList<Profil>();
	//Liste des profils créés
	private Font font1 = new Font("Arial",Font.BOLD,14);
	//Police d'écriture générale pour l'interface
	private JPanel droiteg;
	//Déclaration du JPanel de gauche à l'interieur du JPanel de droite
	private JPanel droited;
	//Déclaration du JPanel de droite à l'interieur du JPanel de droite
	private JPanel droitem;
	//Déclaration du JPanel de milieu à l'interieur du JPanel de droite

	/** Constructeur
	 * Dimensionnement de la fenêtre
	 * Création des différents panels et association à leur méthode
	 * Ajout des composants
	 * */
	public Classement( LinkedList<Profil> profils) {
		super("Classement");
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.listeprofil = profils;

		JPanel g = new JPanel(new GridLayout(1,2));
		//JPanel général contenant tous les autres JPanels séparés en 2 colonnes
		JPanel droite = new JPanel(new GridLayout(1,3));
		//JPanel de droite séparé en 3 colonnes
		JScrollPane droiteglob = new JScrollPane (droite);
		//JSrollPane pour faire defiler le classement

		droiteg = droiteg();
		droited = droited();
		droitem = droitem();
		
		//On appelle la methode sort() pour trier la liste dans l'ordre decroissant des scores
		sort();

		droite.add(droiteg);
		droite.add(droitem);
		droite.add(droited);

		g.add(gauche());
		g.add(droiteglob);
		add(g);
		setVisible(true);
	}

	/** Methode JPanel gauche()
	 * Renvoie le JPanel de gauche qui contient l'image du logo du jeu  
	 * */
	public JPanel gauche () {
		JPanel gauche = new JPanel();
		//Création du JPanel de gauche
		BoxLayout b = new BoxLayout(gauche, BoxLayout.Y_AXIS);
		//Le panel est mis dans la case de gauche du panel principal g 
		gauche.setLayout(b);
		gauche.setBackground(new Color(0xff, 0xff, 0xff));

		File ch = new File("Ressources/LogoJeu.png");
		JLabel image = new JLabel (new ImageIcon(ch.getAbsolutePath()));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		//L'image est centrée dans la case

		gauche.add(Box.createRigidArea(new Dimension(0,10)));
		gauche.add(image);
		gauche.add(Box.createRigidArea(new Dimension(0,50)));

		return gauche;
	}

	/**Methode JPanel droiteg()
	 * Renvoie le Jpanel de gauche dans le JPanel de droite
	 * Ce panel affiche le titre et les numeros du classement
	 * */
	public JPanel droiteg () {
		JPanel droiteg = new JPanel();
		BoxLayout c = new BoxLayout(droiteg, BoxLayout.Y_AXIS);
		//Le panel est mis dans la case de gauche du panel de droite
		droiteg.setLayout(c);
		//Placement de la case dans la fenetre
		droiteg.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel titre= new JLabel("CLASSEMENT");
		Font font = new Font("Comic Sans MS",Font.BOLD,16);
		//Police pour le titre "CLASSEMENT"
		titre.setFont(font);
		titre.setAlignmentX(Component.CENTER_ALIGNMENT);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		//Placement du titre dans le panel
		droiteg.add(titre);

		JLabel vide = new JLabel (" ");
		//Ce lablel permet d'equilibrer avec les colonnes se trouvant dans les autres colonnes
		vide.setAlignmentX(Component.CENTER_ALIGNMENT);
		vide.setFont(font1);
		droiteg.add(Box.createRigidArea(new Dimension(0,30)));
		droiteg.add(vide);

		return droiteg;
	}

	/**Methode JPanel droited()
	 * Renvoie le Jpanel de droite dans le JPanel de droite
	 * Ce panel affiche le score des joueurs
	 * */

	public JPanel droited () {
		JPanel droited = new JPanel();
		BoxLayout e = new BoxLayout(droited, BoxLayout.Y_AXIS);
		//Le panel est mis dans la case de droite du panel de droite
		droited.setLayout(e);
		droited.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel score = new JLabel (" Score: ");
		score.setAlignmentX(Component.CENTER_ALIGNMENT);
		score.setFont(font1);
		//Utilisation de la police generale
		droited.add(Box.createRigidArea(new Dimension(0,100)));
		droited.add(score);

		return droited;
	}

	/**Methode JPanel droitem()
	 * Renvoie le Jpanel du milieu dans le JPanel de droite
	 * Ce panel affiche les pseudos des joueurs 
	 * */

	public JPanel droitem () {
		JPanel droitem = new JPanel();
		BoxLayout d = new BoxLayout(droitem, BoxLayout.Y_AXIS);
		//Le panel est mis dans la case du milieu du panel de droite
		droitem.setLayout(d);
		droitem.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel pseud = new JLabel (" Pseudo: ");
		pseud.setAlignmentX(Component.CENTER_ALIGNMENT);
		pseud.setFont(font1);
		droitem.add(Box.createRigidArea(new Dimension(0,100)));
		droitem.add(pseud);

		return droitem;
	}

	/**Methode sort()
	 * Cette methode trie la liste de profil dans l'ordre drcroissant  de leur score maximal
	 * Puis affiche la liste des pseudos associes a leur score dans l'ordre
	 * */

	public void sort() {
		Collections.sort(listeprofil); 
		//On appelle la methode compareTo() dans profil qui trie la liste dans l'ordre croissant
		for (int i=0; i< listeprofil.size(); i++){

			JLabel num = new JLabel ((i+1)+". ");
			//Création du label qui contient les pseudos dans l'ordre decroissant
			num.setAlignmentX(Component.RIGHT_ALIGNMENT);
			num.setFont(font1);
			droiteg.add(Box.createRigidArea(new Dimension(0,20)));
			droiteg.add(num);

			JLabel pse = new JLabel (listeprofil.get(i).getPseudo());

			pse.setAlignmentX(Component.CENTER_ALIGNMENT);
			//Placement du label
			pse.setFont(font1);
			droitem.add(Box.createRigidArea(new Dimension(0,20)));
			droitem.add(pse);

			JLabel sc = new JLabel (Integer.toString(listeprofil.get(i).getScore()));
			//Création du label qui contient les scores dans l'ordre decroissant

			sc.setAlignmentX(Component.CENTER_ALIGNMENT);
			sc.setFont(font1);
			droited.add(Box.createRigidArea(new Dimension(0,20)));
			droited.add(sc);
		}

	}



}


