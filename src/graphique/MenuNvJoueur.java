package graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.mail.MessagingException;
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
import controller.Mail;

public class MenuNvJoueur extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Monde monde;
	private JTextField nomfield;
	private JTextField prenomfield;
	private JTextField emailfield;
	private JTextField pseudofield;
	private JPasswordField mdpass;
	private JPasswordField mdpassconfirm;

    /** Constructeur de la fenetre de création d'un profil
     * 
     * @param monde
     */
	public MenuNvJoueur(Monde monde) {
		super("Inscription");
		setSize(700,600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.monde = monde;

		JPanel g = new JPanel(new GridLayout(1,2));
		JPanel droite = new JPanel(new GridLayout(1,2));

		droite.add(droiteg());
		droite.add(droited());
		g.add(gauche());
		g.add(droite);

		add(g);
		setVisible(true);
	}
	
	/**Méthode qui permet de vérifier si les deux mot de passe rentrés sont les mêmes
	 * 
	 * @param a, mot de passe
	 * @param b, mot de passe de confirmation
	 * @return boolean
	 */
	public boolean verifmdp (JPasswordField a, JPasswordField b) {
		int i = 0;
		boolean v = true;
		for (char c : a.getPassword()) {
			if(!(c == (b.getPassword()[i]))) {
				v = false;
			}
			i++;
		}
		return v;
	}
	
	/**Methode qui permet de vérifier que le pseudo ainsi que le mail rentrés ne correspondent pas déja à un autre compte
	 * 
	 * @param pseudo
	 * @param mail
	 * @return boolean
	 */
	public boolean verifpseudomail (String pseudo, String mail) {
		boolean v = true;
		for (Profil c : monde.getProfil()) {
			if (c.getPseudo().equals(pseudo) || pseudo.equals("Connectez-vous") || c.getMail().equals(mail)) {
				v = false;
			}
		}
		return v;
	}
	
	/**Méthode qui permet de créer le JPanel gauche de la fenetre (L'Image)
	 * 
	 * @return JPanel
	 */
	public JPanel gauche() {
		JPanel gauche = new JPanel();
		BoxLayout b = new BoxLayout(gauche, BoxLayout.Y_AXIS);
		gauche.setLayout(b);
		gauche.setBackground(new Color(0xff, 0xff, 0xff));

		File ch = new File("Ressources/LogoJeu.png");
		JLabel image = new JLabel (new ImageIcon(ch.getAbsolutePath()));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);

		gauche.add(Box.createRigidArea(new Dimension(0,10)));
		gauche.add(image);
		gauche.add(Box.createRigidArea(new Dimension(0,50)));

		return gauche;
	}
	
	/**Méthode qui permet de créer la partie Gauche du JPanel Droit 
	 * (Intitulés des cases à remplir)
	 * 
	 * @return JPanel
	 */
	public JPanel droiteg() {
		JPanel droiteg = new JPanel();
		BoxLayout c = new BoxLayout(droiteg, BoxLayout.Y_AXIS);
		droiteg.setLayout(c);
		droiteg.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel prof= new JLabel("INSCRIPTION");
		Font font = new Font("Comic Sans MS",Font.BOLD,18);
		prof.setFont(font);
		prof.setAlignmentX(Component.CENTER_ALIGNMENT);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(prof);

		JLabel nom = new JLabel ("Nom : ");
		nom.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font1 = new Font("Arial",Font.BOLD,14);
		nom.setFont(font1);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(nom);

		JLabel prenom = new JLabel ("Prénom : ");
		prenom.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font2 = new Font("Arial",Font.BOLD,14);
		prenom.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(prenom);

		JLabel email = new JLabel ("Email : ");
		email.setAlignmentX(Component.CENTER_ALIGNMENT);
		email.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(email);

		JLabel uti = new JLabel ("Pseudo : ");
		uti.setAlignmentX(Component.CENTER_ALIGNMENT);
		uti.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(uti);

		JLabel motdp = new JLabel ("Mot De Passe : ");
		motdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		motdp.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(motdp);

		JLabel confimotdp = new JLabel ("Confirmez : ");
		confimotdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		confimotdp.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(confimotdp);

		return droiteg;
	}

	/**Méthode qui permet de créer la partie Droite du JPanel Droit 
	 * (Cases à remplir)
	 * 
	 * @return JPanel
	 */
	public JPanel droited() {
		JPanel droited = new JPanel();
		BoxLayout d = new BoxLayout(droited, BoxLayout.Y_AXIS);
		droited.setLayout(d);
		droited.setBackground(new Color(0xff, 0x96, 0x4f));

		nomfield = new JTextField ("Entrer Nom");
		nomfield.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,115)));
		droited.add(nomfield);

		prenomfield = new JTextField ("Entrer Prénom");
		prenomfield.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,50)));
		droited.add(prenomfield);

		emailfield = new JTextField ("Entrer Email");
		emailfield.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,50)));
		droited.add(emailfield);

		pseudofield = new JTextField ("Entrer Pseudo");
		pseudofield.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,50)));
		droited.add(pseudofield);

		mdpass = new JPasswordField (10);
		mdpass.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,50)));
		droited.add(mdpass);

		mdpassconfirm = new JPasswordField (10);
		mdpassconfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,50)));
		droited.add(mdpassconfirm);


		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(verifmdp(mdpassconfirm, mdpass) && verifpseudomail(pseudofield.getText(),emailfield.getText())) {
					String b = Integer.toString((int) (10000*Math.random()) + 100);
					try {
						Mail.envoiemail(emailfield.getText(),b);
					} catch (MessagingException e1) {
						e1.printStackTrace();
					}
						//Nous permet d'envoyer un Mail pour verifier l'identité du Nouveau Joueur
					if (!(JOptionPane.showInputDialog(null, "Code Envoyé Par Mail","Veuillez saisir le code", 
							JOptionPane.INFORMATION_MESSAGE).equals(b))) {
						JOptionPane.showOptionDialog(null, "Le code de confirmation n'est pas le bon","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
					} else {
						dispose();
						monde.getProfil().add(new Profil(nomfield.getText(), prenomfield.getText(), pseudofield.getText(), emailfield.getText(),mdpass.getPassword()));
						monde.setConnect(monde.getProfil().get(monde.getProfil().size()-1));
					}
					monde.serialiser();
				} else {
					JOptionPane.showOptionDialog(null, "Votre pseudo,mail ou votre mot de passe est invalide","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				}
			}
		});
		valider.setBackground(new Color(0x99, 0xcc, 0x00));
		valider.setForeground(Color.WHITE);
		valider.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,40)));
		droited.add(valider);
		droited.add(Box.createRigidArea(new Dimension(0,150)));

		return droited;
	}

}


