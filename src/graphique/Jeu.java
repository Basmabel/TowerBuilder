package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.BlocRectangle;
import Model.Monde;
import controller.Souris;
/**Classe permettant de créer de manière graphique notre jeu
 * 
 * @author Fondateurs du Tower
 */
public class Jeu extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Monde monde;
	private Timer mt = new Timer(100, this);
	private Image bloc;
	private BufferedImage image;

	/**Constructeur
	 * Mis en place de l'image de fond
	 * Et l'image pour les blocs
	 * @param monde
	 */
	public Jeu(Monde monde) {
		this.monde = monde;
		File sh =new File("Ressources/BlocJeu.png");
		//Photo de la fenêtre sur le BlocRectangle
		File ch =new File("Ressources/FondJeu(1).jpg");
		//Photo de fond de jeu
		try {
			bloc = ImageIO.read(new File(sh.getAbsolutePath()));
			image = ImageIO.read(new File(ch.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(new JLabel(new ImageIcon(image)));
		mt.start();
		addMouseListener(new Souris(monde));
	}
	
    /**Méthode paint
     * Tracer le lanceur et les blocs
     * 
     */
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		int a = (int) monde.getXlanceur();
		g.fillRect((a - 15), 0, 30, 50);
		g.fillRect((a - 25), 50, 50, 30);
		g.setColor(BlocRectangle.convertisseurcouleur(monde.getCouleur()));
		g.fillRect(a - (int) (monde.getLongueur() / 2.0), 80, (int) monde.getLongueur(), (int) monde.getLargeur());

		for (BlocRectangle c : monde.getTour()) {
			int a1 = (int) c.getCoord().x + (int) c.getLongueur() / 2;
			g.setColor(c.getCouleur());
			g.fillRect((int) c.getCoord().x, (int) c.getCoord().y, (int) c.getLongueur(), (int) c.getLargeur());
			g.drawImage(bloc, a1 - 40, (int) c.getCoord().y, this);
			// Dessin de l'Image de la fenêtre dans le milieu du BlocRectangle

		}
	}

	/**Méthode ActionPerformed, methode abstraite du l'object ActionListener  
	 * Envoie de la taille de la fenetre au monde pour que le lanceur s'adapte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		monde.setWidth(getWidth());
		monde.setHeight(getHeight());
	}

}
