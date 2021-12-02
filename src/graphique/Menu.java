package graphique;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import Model.Monde;
import Model.Profil;
/**Classe permettant de créer l'interface graphique générale de notre code
 * 
 * @author Fondateurs du Tower
 */
public class Menu extends JFrame implements Observer {

	private Monde monde;
	private Jeu jeu;
	private JPanel options;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**Constructeur du Menu
	 * Frame principale du jeu
	 * @param monde
	 */
	public Menu(Monde monde) {
		super("Tower");
		this.setSize(1100, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);

		monde.addObserver(this);
		jeu = new Jeu(monde);
		this.monde = monde;
		options = option();



		add(tool(), BorderLayout.NORTH);
		repaint();
		add(jeu, BorderLayout.CENTER);
		add(options, BorderLayout.WEST);

		this.setVisible(true);
	}

	/** Méthode abstraite update qui provient de la classe abstraite Observer
	 *
	 *  Nous permet d'actualiser la fenetre lorsqu'on réalise des modifications dans le Monde.
	 */
	@Override
	public void update(Observable o, Object arg) {
		//si whois est true on actualise la fenetre option (Si le joueur change, score etc..), sinon juste la fenetre jeu.
		boolean whois = (boolean) arg;
		if (whois) {
			remove(options);
			options = option();
			add(options, BorderLayout.WEST);
		}
		jeu.repaint();
		revalidate();
	}

	/**Méthode qui permet la création du menu des  à gauche de la JFrame
	 * 
	 * Elle permet de changer certains paramètres du jeu (Vitesse, masse volumique etc...)
	 * @return JPanel
	 */	
	public JPanel option() {
		JPanel options = new JPanel();
		options.setBackground(new Color(0xff, 0xff, 0xff));
		BoxLayout layoutmain = new BoxLayout(options, BoxLayout.Y_AXIS );
		options.setLayout(layoutmain);
		options.setBorder(BorderFactory.createTitledBorder("Options"));

		JPanel pancouleur = new JPanel();
		pancouleur.setBackground(new Color(0xca, 0xff, 0xfb));
		pancouleur.setBorder(BorderFactory.createTitledBorder("Couleur"));
		BoxLayout layout = new BoxLayout(pancouleur, BoxLayout.X_AXIS );
		pancouleur.setLayout(layout);
		JLabel couleur = new JLabel("Couleur : ");	
		String [] listecouleur = {"Rouge" ,"Bleu" , "Vert" , "Cyan" , "Jaune" , "Rose" , "Orange"};		
		JComboBox<String>combocouleur = new JComboBox<String>(listecouleur);
		combocouleur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				monde.setCouleur((String)combocouleur.getSelectedItem());
				jeu.repaint();
			}
		});
		pancouleur.add(couleur);
		pancouleur.add(combocouleur);

		JPanel dimension = new JPanel();
		BoxLayout layoutdimension = new BoxLayout(dimension, BoxLayout.Y_AXIS );
		dimension.setLayout(layoutdimension);
		dimension.setBorder(BorderFactory.createTitledBorder("Dimension"));
		dimension.setBackground(new Color(0xca, 0xff, 0xfb));


		JPanel panlongueur = new JPanel(new FlowLayout());
		JLabel longueur = new JLabel ("Longueur : ");
		JTextArea lon = new JTextArea ("100");

		panlongueur.add(longueur, FlowLayout.LEFT);
		panlongueur.add(lon,FlowLayout.CENTER);
		panlongueur.setBackground(new Color(0xca, 0xff, 0xfb));


		JPanel panlargeur = new JPanel(new FlowLayout());
		JLabel largeur = new JLabel ("Largeur : ");
		JTextArea lar = new JTextArea ("50");

		panlargeur.add(largeur, FlowLayout.LEFT);
		panlargeur.add(lar, FlowLayout.CENTER);
		panlargeur.setBackground(new Color(0xca, 0xff, 0xfb));

		JPanel panbutton = new JPanel(new FlowLayout());
		JButton validate = new JButton("Valider");
		validate.setBackground(new Color(0xb0, 0xff, 0x9d));
		validate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					monde.setLongueur(Integer.parseInt(lon.getText()));
					monde.setLargeur(Integer.parseInt(lar.getText()));
				} catch (NumberFormatException b) {
					JOptionPane.showOptionDialog(null, "Veuillez  rentrer des entiers","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				}
				jeu.repaint();
			}    
		});
		panbutton.add(validate, FlowLayout.LEFT);
		panbutton.setBackground(new Color(0xca, 0xff, 0xfb));

		dimension.add(panlongueur);
		dimension.add(panlargeur);
		dimension.add(panbutton);

		JPanel masse = new JPanel();
		BoxLayout layoutmasse = new BoxLayout(masse, BoxLayout.Y_AXIS );
		masse.setLayout(layoutmasse);
		masse.setBorder(BorderFactory.createTitledBorder("Masse Volumique"));
		masse.setBackground(new Color(0xca, 0xff, 0xfb));

		JPanel mv = new JPanel(new FlowLayout());
		JLabel mv1 = new JLabel ("Unite kg/m^3 : ");
		JTextArea mv2 = new JTextArea ("40");
		mv.setBackground(new Color(0xca, 0xff, 0xfb));

		JPanel panbutton2 = new JPanel(new FlowLayout());
		JButton validate2 = new JButton("Valider");
		validate2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					monde.setRho(Integer.parseInt(mv2.getText()));
				} catch (NumberFormatException b) {
					JOptionPane.showOptionDialog(null, "Veuillez  rentrer des entiers","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				}
				jeu.repaint();
			}
		});
		validate2.setBackground(new Color(0xb0, 0xff, 0x9d));
		panbutton2.add(validate2, FlowLayout.LEFT);
		panbutton2.setBackground(new Color(0xca, 0xff, 0xfb));

		mv.add(mv1, FlowLayout.LEFT);
		mv.add(mv2, FlowLayout.CENTER);
		masse.add(mv);
		masse.add(panbutton2);

		JPanel config = new JPanel();
		BoxLayout layoutconfig = new BoxLayout(config, BoxLayout.Y_AXIS );
		config.setLayout(layoutconfig);
		config.setBorder(BorderFactory.createTitledBorder("Configuration Jeu"));
		config.setBackground(new Color(0xca, 0xff, 0xfb));

		JPanel cg = new JPanel(new FlowLayout());
		JLabel cg1 = new JLabel ("Vitesse (1-10) : ");
		JTextArea cg2 = new JTextArea ("10");
		cg.setBackground(new Color(0xca, 0xff, 0xfb));

		JPanel panbutton1 = new JPanel(new FlowLayout());
		JButton validate1 = new JButton("Valider");
		validate1.setBackground(new Color(0xb0, 0xff, 0x9d));
		validate1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (( (Integer.parseInt(cg2.getText()) <= 10 ) && (Integer.parseInt(cg2.getText()) > 0 ))) {
						int a = Integer.parseInt(cg2.getText());
						monde.setVitesse(a);
					} else {
						JOptionPane.showOptionDialog(null, "Vitesse Trop lente ou invalide","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
					}
				} catch (NumberFormatException b) {
					JOptionPane.showOptionDialog(null, "Veuillez  rentrer des entiers","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				}

				jeu.repaint();
			}
		});
		panbutton1.add(validate1, FlowLayout.LEFT);
		panbutton1.setBackground(new Color(0xca, 0xff, 0xfb));

		cg.add(cg1, FlowLayout.LEFT);
		cg.add(cg2, FlowLayout.CENTER);
		config.add(cg);
		config.add(panbutton1);

		JPanel info = new JPanel();
		info.setBorder(BorderFactory.createTitledBorder("Information"));
		info.setBackground(new Color(0xca, 0xff, 0xfb));


		JPanel panpseudo = new JPanel(new FlowLayout());
		JLabel pseudo = new JLabel ("Pseudo : " + monde.getConnect().getPseudo());

		panpseudo.add(pseudo,FlowLayout.LEFT);
		panpseudo.setBackground(new Color(0xca, 0xff, 0xfb));

		JPanel panscore = new JPanel(new FlowLayout());
		JLabel score = new JLabel ("Reccord : " + monde.getConnect().getScore());

		panscore.add(score, FlowLayout.LEFT);
		panscore.setBackground(new Color(0xca, 0xff, 0xfb));

		info.add(panpseudo);
		info.add(panscore);


		options.add(pancouleur);
		options.add(Box.createRigidArea(new Dimension(0,50)));
		options.add(dimension);
		options.add(Box.createRigidArea(new Dimension(0,50)));
		options.add(masse);
		options.add(Box.createRigidArea(new Dimension(0,50)));
		options.add(config);
		options.add(Box.createRigidArea(new Dimension(0,50)));
		options.add(info);
		return options;
	}

	/**Méthode qui permet la création de la Bar d'outils
	 * 
	 * Elle permet également de faire fonctionner tous les boutons qui y sont via des classes anonymes.
	 * @return JToolBar
	 */
	public JToolBar tool () {
		JToolBar menu = new JToolBar();

		JButton jouer = new JButton("Jouer");
		jouer.setBackground(new Color(0xff, 0xfe, 0x7a));
		jouer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!monde.isPause()) {
					monde.play();
				} else {
					JOptionPane.showOptionDialog(null, "Le jeu est en pause, cliquez sur pause/go ou reset ","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				}
				
			}
		});

		JButton restart = new JButton ("Restart");
		restart.setBackground(new Color(0xff, 0x96, 0x4f));
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				monde.reset();
				jeu.repaint();
			}
		});

		JButton admin = new JButton ("Administration");
		admin.setBackground(new Color(0xff, 0x96, 0x4f));
		admin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Admin(monde);	
			}
		});

		JButton stop = new JButton("Stop / Go");
		stop.setBackground(new Color(0xdb, 0x58, 0x56));
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!monde.isPause()) {
					monde.pause();
				} else {
					monde.reprendre();
				}
			}
		});

		JButton classement = new JButton("Classement");
		classement.setBackground(new Color(0xff, 0xb7, 0xce));
		classement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Classement(monde.getProfil());
			}
		});

		JButton profil  = new JButton("Nouveau Profil");
		profil.setBackground(new Color(0xa2, 0xbf, 0xfe));
		profil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuNvJoueur(monde);
			}
		});

		JButton connect  = new JButton("De/Connexion");
		connect.setBackground(new Color(0xca, 0xa0, 0xff));
		connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (monde.getConnect().getPseudo().equals("Connectez-vous")) {
					new MenuProfil(monde);
				} else {
					monde.setConnect(new Profil());
				}

			}});

		menu.add(jouer);
		menu.add(restart);
		menu.add(stop);
		menu.add(classement);
		menu.add(connect);
		menu.add(profil);
		menu.add(Box.createGlue());
		menu.add(admin);

		return menu;
	}



}
