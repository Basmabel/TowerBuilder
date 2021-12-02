package graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.Monde;
import Model.Profil;

public class MenuProfil extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Monde monde;
	private JTextField pseudo;
	private JPasswordField mdpass;


    /**Constructeur de la fenetre permettant la connexion
     * 
     * @param monde
     */
	public MenuProfil(Monde monde) {
		super("Connexion");
		setSize(700,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.monde = monde;

		JPanel g = new JPanel(new GridLayout(1,2));

		g.add(gaucheProf());
		g.add(droiteProf());
		add(g);
		setVisible(true);
	}

	/**Méthode permettant la création du Panel Gauche
	 * (L'Image)
	 * @return JPanel
	 */
	public JPanel gaucheProf(){
		JPanel gauche = new JPanel();
		BoxLayout b = new BoxLayout(gauche, BoxLayout.Y_AXIS);
		gauche.setLayout(b);
		gauche.setBackground(new Color(0xff, 0xff, 0xff));

		File ch = new File("Ressources/LOGOJEU2.png");
		JLabel image = new JLabel (new ImageIcon(ch.getAbsolutePath()));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);

		gauche.add(Box.createRigidArea(new Dimension(0,20)));
		gauche.add(image);
		gauche.add(Box.createRigidArea(new Dimension(0,50)));

		return gauche;
	}

	/**Méthode permettant la création de la partie Gauche du Panel Droit
	 * (Intitulés des cases)
	 * @return JPanel
	 */
	public JPanel droiteGa() {
		JPanel droiteg = new JPanel();
		BoxLayout c = new BoxLayout(droiteg, BoxLayout.Y_AXIS);
		droiteg.setLayout(c);
		droiteg.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel prof= new JLabel("Profil Utilisateur");
		prof.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font = new Font("Comic Sans MS",Font.BOLD,18);
		prof.setFont(font);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(prof);

		JLabel uti = new JLabel ("Pseudo: ");
		uti.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font2 = new Font("Arial",Font.BOLD,14);
		uti.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,55)));
		droiteg.add(uti);		

		JLabel motdp = new JLabel ("Mot De Passe : ");
		motdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		motdp.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,60)));
		droiteg.add(motdp);

		return droiteg;
	}

	/**Méthode permettant la création de la partie Droite du Panel Droit
	 * (Intitulés des cases à remplir)
	 * @return JPanel
	 */
	public JPanel droiteDr() {

		JPanel droited = new JPanel();
		BoxLayout d = new BoxLayout(droited, BoxLayout.Y_AXIS);
		droited.setLayout(d);
		droited.setBackground(new Color(0xff, 0x96, 0x4f));

		pseudo = new JTextField ("Entrer le pseudo");
		pseudo.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,130)));
		droited.add(pseudo);
		droited.add(Box.createRigidArea(new Dimension(0,25)));

		mdpass = new JPasswordField (10);
		mdpass.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,30)));
		droited.add(mdpass);
		droited.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton mdpoublie = new JButton("Mot de Passe Oublié");
		mdpoublie.setBackground(new Color(0,0,0,0));
		mdpoublie.setOpaque(false);
		mdpoublie.setForeground(Color.BLUE);
		mdpoublie.setAlignmentX(Component.CENTER_ALIGNMENT);
		mdpoublie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MdpOublie(monde);
				dispose();
			}
			});
		droited.add(mdpoublie);
		droited.add(Box.createRigidArea(new Dimension(0,20)));

		
		
		JButton valider = new JButton("Valider");
		valider.setBackground(new Color(0x99, 0xcc, 0x00));
		valider.setForeground(Color.WHITE);
		valider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Profil bot;
				for (Profil c : monde.getProfil()) {
					if (c.getPseudo().equals(pseudo.getText())) {
						bot = c;
						if(bot.getPass().equals(converter(mdpass)) && !bot.isBan()) {
							monde.setConnect(bot);
							dispose();
						} else if (bot.isBan()) {
							JOptionPane.showOptionDialog(null, "Ce compte est banni","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
						} else {
							JOptionPane.showOptionDialog(null, "Vos informations sont incrorrectes","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
						} 
							//Nous permet de verifier que le mot de passe rentré est bien le Bon 
					}
				}
			}});
		
		droited.add(Box.createRigidArea(new Dimension(0,10)));
		droited.add(valider);
		droited.add(Box.createRigidArea(new Dimension(0,150)));
		return droited;

	}

	/**Méthode permettant la création du Panel Droit
	 * Panel dans lequel on place tous les éléments des méthodes droiteGa(), droiteDr()
	 * @return JPanel
	 */
	public JPanel droiteProf(){

		JPanel droite = new JPanel(new GridLayout(1,2));
		droite.add(droiteGa());
		droite.add(droiteDr());

		return droite;
	}

	/**Méthode qui permet de transformer les données ecrites dans le JPassword Field en String
	 * 
	 * @param a, convertir le mot de passe d'un tableau de char (getPassword()) en un String.
	 * @return String
	 */
	public String converter (JPasswordField a) {
		String b = null;
		for (char c : a.getPassword()) {
			b = b + Character.toString(c);
		}
		return b;
	}


}
