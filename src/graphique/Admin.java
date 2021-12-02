package graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Monde;

public class Admin extends JFrame implements ActionListener {
	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;

	private Monde monde;
	
	// LinkedList stockant tous les boutons avec lesquels nous devont intéragir.
	private LinkedList <JButton> statut = new LinkedList<JButton>();
	private LinkedList <JButton> ban = new LinkedList<JButton>();
	private LinkedList <JButton> reset = new LinkedList<JButton>();

	private Font font1 = new Font("Arial",Font.BOLD,14);
	private JPanel droiteg = new JPanel();
	private JPanel droitereset = new JPanel();
	private JPanel droited = new JPanel();
	private JPanel droiteban = new JPanel();
	private JPanel droitem = new JPanel();
	private JPanel droite = new JPanel(new GridLayout(1,3));
	private JPanel g = new JPanel(new GridLayout(1,2));
	private JScrollPane droiteglob = new JScrollPane(droite);

	/** Constructeur de la Classe Admin permettant la gestion des Utilisateurs (Ban, reset etc..)
	 * 
	 * @param monde, on donne bien évidement le Monde en paramètre afin d'avoir
	 * un accès aux attributs en lecture notamment.
	 */
	public Admin(Monde monde) {
		super("Administration");
		setSize(800,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.monde = monde;
		droiteg();
		droited();
		droiteban();
		droitem();
		droitereset();
		sort();
		droite.add(droiteg);
		droite.add(droitem);
		droite.add(droited);
		droite.add(droiteban);
		droite.add(droitereset);
		g.add(gauche());
		g.add(droiteglob);
		add(g);
		setVisible(true);
	}

	
	/**Méthode permettant de crée le JPanel gauche de la JFrame Administrateur
	 * 
	 * @return JPanel
	 */
	public JPanel gauche () {
		JPanel gauche = new JPanel();
		BoxLayout b = new BoxLayout(gauche, BoxLayout.Y_AXIS);
		gauche.setLayout(b);
		gauche.setBackground(new Color(0xff, 0xff, 0xff));

		File ch = new File("Ressources/LogoJeu.png");
		JLabel image = new JLabel (new ImageIcon(ch.getAbsolutePath()));
		image.setAlignmentX(Component.LEFT_ALIGNMENT);

		gauche.add(Box.createRigidArea(new Dimension(0,50)));
		gauche.add(image);
		gauche.add(Box.createRigidArea(new Dimension(0,400)));

		return gauche;
	}

	/**Méthode permettant de crée la partie Gauche du JPanel Droite de la JFrame Administrateur
	 * On implémente l'attribut droiteg
	 */
	public void droiteg () {
		BoxLayout c = new BoxLayout(droiteg, BoxLayout.Y_AXIS);
		droiteg.setLayout(c);
		droiteg.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel titre= new JLabel("Membres");
		Font font = new Font("Comic Sans MS",Font.BOLD,16);
		titre.setFont(font);
		titre.setAlignmentX(Component.CENTER_ALIGNMENT);
		droiteg.add(Box.createRigidArea(new Dimension(0,48)));
		droiteg.add(titre);

		JLabel vide = new JLabel ("     ");
		vide.setAlignmentX(Component.CENTER_ALIGNMENT);
		vide.setFont(font1);
		droiteg.add(Box.createRigidArea(new Dimension(0,30)));
		droiteg.add(vide);
	}

	/**Méthode permettant de crée la partie Droite du JPanel Droite de la JFrame Administrateur
	 * On implémente l'attribut droited
	 */
	public void droited () {
		BoxLayout e = new BoxLayout(droited, BoxLayout.Y_AXIS);
		droited.setLayout(e);
		droited.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel score = new JLabel (" Statut: ");
		score.setAlignmentX(Component.CENTER_ALIGNMENT);
		score.setFont(font1);
		droited.add(Box.createRigidArea(new Dimension(0,100)));
		droited.add(score);
	}

	/**Méthode permettant de crée la partie Ban du JPanel Droite de la JFrame Administrateur
	 * On implémente l'attribut droiteban
	 */
	public void droiteban () {
		BoxLayout x = new BoxLayout(droiteban, BoxLayout.Y_AXIS);
		droiteban.setLayout(x);
		droiteban.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel ban = new JLabel (" Ban: ");
		ban.setAlignmentX(Component.CENTER_ALIGNMENT);
		ban.setFont(font1);
		droiteban.add(Box.createRigidArea(new Dimension(0,100)));
		droiteban.add(ban);
	}

	/**Méthode permettant de crée la partie Reset du JPanel Droite de la JFrame Administrateur
	 * On implémente l'attribut droitreset
	 */
	public void droitereset () {
		BoxLayout y = new BoxLayout(droitereset, BoxLayout.Y_AXIS);
		droitereset.setLayout(y);
		droitereset.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel reset = new JLabel (" Reset: ");
		reset.setAlignmentX(Component.CENTER_ALIGNMENT);
		reset.setFont(font1);
		droitereset.add(Box.createRigidArea(new Dimension(0,100)));
		droitereset.add(reset);
	}

	/**Méthode permettant de crée la partie milieu du JPanel Droite de la JFrame Administrateur
	 * On implémente l'attribut droitem
	 */
	public void droitem () {
		BoxLayout d = new BoxLayout(droitem, BoxLayout.Y_AXIS);
		droitem.setLayout(d);
		droitem.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel pseud = new JLabel (" Pseudo: ");
		pseud.setAlignmentX(Component.CENTER_ALIGNMENT);
		pseud.setFont(font1);
		droitem.add(Box.createRigidArea(new Dimension(0,100)));
		droitem.add(pseud);

	}

	/**Méthode permettant d'afficher les utilisateurs dans des Label, mais aussi les divers boutons
	 * de l'interface (ban, reset, Admin, user)
	 * Il faut garder en tete que chaque utilisateur avec ses boutons associés sera reperé par un index identique
	 * dans les diverses LinkedList.
	 */
	public void sort() {
		for (int i=0; i< monde.getProfil().size(); i++){

			JLabel num = new JLabel ((i+1)+". ");
			num.setAlignmentX(Component.RIGHT_ALIGNMENT);
			num.setFont(font1);
			droiteg.add(Box.createRigidArea(new Dimension(0,20)));
			droiteg.add(num);

			JLabel pse = new JLabel (monde.getProfil().get(i).getPseudo());

			pse.setAlignmentX(Component.CENTER_ALIGNMENT);
			pse.setFont(font1);
			droitem.add(Box.createRigidArea(new Dimension(0,20)));
			droitem.add(pse);

			JButton sc;
			if (monde.getProfil().get(i).getAdmin()) {
				sc = new JButton("Admin");
				sc.setBackground(new Color(0xdb, 0x58, 0x56));
			} else {
				sc = new JButton(" User ");
				sc.setBackground(new Color(0xa2, 0xbf, 0xfe));
			}
			sc.addActionListener(this);
			statut.add(sc);
			sc.setAlignmentX(Component.CENTER_ALIGNMENT);
			droited.add(Box.createRigidArea(new Dimension(0,12)));
			droited.add(sc);

			JButton b;
			if (monde.getProfil().get(i).isBan()) {
				b = new JButton("Deban");
				b.setBackground(new Color(50, 205, 50));

			} else {
				b = new JButton("Ban");
				b.setBackground(new Color(220, 20, 60));
			}
			b.addActionListener(this);
			this.ban.add(b);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			droiteban.add(Box.createRigidArea(new Dimension(0,12)));
			droiteban.add(b);

			JButton r = new JButton("Reset");
			r.setBackground(new Color(240, 255, 255));
			r.addActionListener(this);
			this.reset.add(r);
			r.setAlignmentX(Component.CENTER_ALIGNMENT);
			droitereset.add(Box.createRigidArea(new Dimension(0,12)));
			droitereset.add(r);
		}
	}

	/**Méthode qui permet de changer le statut des Utilisateurs (Admin -> user, User -> Admin).
	 * Seulement possible si on es Admin, voir Fondateur dans certains cas.
	 * 
	 * @param i, index nous permettant de savoir quel utlisateur il faut target dans les LinkedLists.  
	 */
	public void adminchange (int i) {
		int cas = 0;
		if (!monde.getProfil().get(i).getAdmin() && monde.getConnect().getAdmin()) {
			cas = 1;
		} else if (!monde.getProfil().get(i).getPseudo().equals("Fondateur") && monde.getProfil().get(i).getAdmin() && monde.getConnect().getPseudo().equals("Fondateur")) {
			cas = 1;
		}
		switch (cas) {
		case 1:
			monde.getProfil().get(i).adminer();
			break;
		default:
			JOptionPane.showOptionDialog(null, "Vous n'avez pas la permission de changer le role de " +monde.getProfil().get(i).getPseudo(),"Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			break;
		}
		refresh();
		monde.serialiser();
	}

	/**Méthode qui permet de Ban des Utilisateurs
	 * Fonctionnement identique à la méthode adminchange
	 * 
	 * @param i, index nous permettant de savoir quel utlisateur il faut target dans les LinkedLists. 
	 */
	public void ban (int i) {
		int cas = 0;
		if (!monde.getProfil().get(i).getAdmin() && monde.getConnect().getAdmin()) {
			cas = 1;
		} else if (!monde.getProfil().get(i).getPseudo().equals("Fondateur") && monde.getProfil().get(i).getAdmin() && monde.getConnect().getPseudo().equals("Fondateur")) {
			cas = 1;
		}
		switch (cas) {
		case 1:
			monde.getProfil().get(i).ban();
			break;
		default:
			JOptionPane.showOptionDialog(null, "Vous n'avez pas la permission de ban/deban " +monde.getProfil().get(i).getPseudo(),"Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			break;
		}
		refresh();
		monde.serialiser();
	}

	/**Méthode qui permet de Reset le score des Utilisateurs
	 * Fonctionnement identique à la méthode adminchange, ban
	 * @param i, index nous permettant de savoir quel utlisateur il faut target dans les LinkedLists. 
	 */
	public void reset (int i) {
		int cas = 0;
		if (!monde.getProfil().get(i).getAdmin() && monde.getConnect().getAdmin()) {
			cas = 1;
		} else if (!monde.getProfil().get(i).getPseudo().equals("Fondateur") && monde.getProfil().get(i).getAdmin() && monde.getConnect().getPseudo().equals("Fondateur")) {
			cas = 1;
		}
		switch (cas) {
		case 1:
			monde.getProfil().get(i).setScore(0);
			JOptionPane.showOptionDialog(null, "Le compte de "+ monde.getProfil().get(i).getPseudo() + " a bien été mis à zero.","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			break;
		default:
			JOptionPane.showOptionDialog(null, "Vous n'avez pas la permission de reset "+ monde.getProfil().get(i).getPseudo(),"Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			break;
		}
		monde.serialiser();
	}
	
	/**Méthode ActionPerformed, methode abstraite du l'object ActionListener  
	 * Détection lorsqu'on clique sur un bouton.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0 ; i< statut.size() ; i++) {
			if (e.getSource().equals(statut.get(i))) {
				adminchange(i);
			}
			if (e.getSource().equals(ban.get(i))) {
				ban(i);
			}
			if (e.getSource().equals(reset.get(i))) {
				reset(i);
			}
		}

	}
	
	/** Méthode permettant de refresh la page en cas de modification
	 * Un repaint ne suffit pas, vu qu'on imbrique des panels et qu'on veut modifer le text des boutons.
	 * 
	 */
	public void refresh() {
		droiteg.removeAll();
		droitem.removeAll();
		droited.removeAll();
		droiteban.removeAll();
		droitereset.removeAll();
		droited();
		droitem();
		droiteg();
		droiteban();
		droitereset();
		statut.clear();
		ban.clear();
		reset.clear();
		sort();
		revalidate();
	}

}


