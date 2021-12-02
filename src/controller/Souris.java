package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Monde;

public class Souris implements MouseListener {
	private Monde monde;


	/** Constructeur de la classe Souris.
	 * 
	 * @param monde , on donne en param�tre du MouseListener notre classe objet Monde.
	 */
	public Souris (Monde monde) {
		super();
		this.monde = monde;
	}

 
	@Override
	/**M�thode permettant de d'activer la methode testBloc(), si et seulement si Etat est true donc si le jeu est en cours.
	 * 
	 * @param e, repr�sente l'�v�nement quand on clique sur la Souris
	 */
	public void mouseClicked(MouseEvent e) {
		if (monde.getEtat()) {
			monde.testBloc();	  
		}
	}
	
	/**Methodes abstraites � implementer.
	 * Vu qu'on les utilise pas, on met rien dedans mais il est n�cessaire d'override celles de la classe m�re
	 * afin de pouvoir compiler.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}


}
