package graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

public class MdpOublie extends JFrame  {

	private Monde monde;
	private JTextField pseudo;
	private JTextField email;
	private JPasswordField mdpass;
	private JPasswordField mdpassverif;
	
	private static final long serialVersionUID = 1L;
	
	/**Construteur
	 * Création de la frame lancée au moment où on clique sur le Bouton Mot de passe oublié dans la Fenêtre d'inscription
	 * 
	 * @param monde
	 */
	public MdpOublie(Monde monde) {
		super("Mot de passe Oublié");
		setSize(380,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.monde = monde;

		JPanel g = new JPanel();

		g.add(droiteProf());
		add(g);
		setVisible(true);
	}

	/**Méthode qui nous permet de créer la partie Gauche du JPanel Droite
	 * 
	 * @return JPanel 
	 */
	public JPanel droiteGa() {
		JPanel droiteg = new JPanel();
		BoxLayout c = new BoxLayout(droiteg, BoxLayout.Y_AXIS);
		droiteg.setLayout(c);
		droiteg.setBackground(new Color(0xff, 0x96, 0x4f));

		JLabel prof= new JLabel("Mot De Passe Oublié");
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

		JLabel mail = new JLabel ("Email: ");
		mail.setAlignmentX(Component.CENTER_ALIGNMENT);
		mail.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,55)));
		droiteg.add(mail);


		JLabel motdp = new JLabel ("Nouveau mot de passe : ");
		motdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		motdp.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(motdp);

		JLabel motdpconf = new JLabel ("Confirmez mot de passe : ");
		motdpconf.setAlignmentX(Component.CENTER_ALIGNMENT);
		motdpconf.setFont(font2);
		droiteg.add(Box.createRigidArea(new Dimension(0,50)));
		droiteg.add(motdpconf);

		return droiteg;
	}

	/**Méthode qui nous permet de créer la partie Droite de la JPanel Droite
	 * 
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
		droited.add(Box.createRigidArea(new Dimension(0,30)));

		email = new JTextField ("Entrer l'Email");
		email.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,20)));
		droited.add(email);
		droited.add(Box.createRigidArea(new Dimension(0,30)));

		mdpass = new JPasswordField (10);
		mdpass.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,20)));
		droited.add(mdpass);
		droited.add(Box.createRigidArea(new Dimension(0,20)));

		mdpassverif = new JPasswordField (10);
		mdpassverif.setAlignmentX(Component.CENTER_ALIGNMENT);
		droited.add(Box.createRigidArea(new Dimension(0,30)));
		droited.add(mdpassverif);
		droited.add(Box.createRigidArea(new Dimension(0,20)));


		JButton valider = new JButton("Valider");
		valider.setBackground(new Color(0x99, 0xcc, 0x00));
		valider.setForeground(Color.WHITE);
		valider.setAlignmentX(Component.CENTER_ALIGNMENT);
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Profil c : monde.getProfil()) {
					if (c.getPseudo().equals(pseudo.getText())) {
						if(verifmdp(mdpass,mdpassverif) && c.getMail().equals(email.getText())) {
							String b = Integer.toString((int) (10000*Math.random()) + 100);
							try {
								Mail.envoiemail(c.getMail(),b);
							} catch (MessagingException e1) {
								e1.printStackTrace();
							}
								//nous permet d'envoyer des mails Afin de vérifier l'identité de la personne qui soihaite changer de Mot de Passe
							if (!(JOptionPane.showInputDialog(null, "Code Envoyé Par Mail","Veuillez saisir le code", 
									JOptionPane.INFORMATION_MESSAGE).equals(b))) {
								JOptionPane.showOptionDialog(null, "Le code de confirmation n'est pas le bon","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
							} else {
								c.setPass(c.ChartoString(mdpass.getPassword()));
								dispose();
							}
							monde.serialiser();
						} else {
							JOptionPane.showOptionDialog(null, "les deux mots de passes ne sont pas les même ou le mail est pas le bon","Tower Info", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
						}
					}
				}
			}
		});

		droited.add(Box.createRigidArea(new Dimension(0,40)));
		droited.add(valider);
		droited.add(Box.createRigidArea(new Dimension(0,150)));
		return droited;


	}

	/**Méthode qui nous permet de créer JPanel Droite
	 * 
	 * @return JPanel
	 */
	public JPanel droiteProf(){

		JPanel droite = new JPanel(new GridLayout(1,2));
		droite.add(droiteGa());
		droite.add(droiteDr());

		return droite;

	}

	/**Méthode qui permet de vérifier si les deux mots de passe rentrées sont compatibles
	 * Il faut noter que JPasswordField.getPassword() renvoie un tableau de char d'où l'existence cette méthode
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

}
