package main;

import javax.mail.MessagingException;

import Model.Monde;
import graphique.Menu;
public class Main {
    
	
	/**Constructeur de la Classe Main
	 * 
	 * @param args
	 * @throws MessagingException, lève une exception en cas d'erreur au niveau du Mail.
	 */
	public static void main(String[] args) throws MessagingException {
		Monde monde = new Monde();
		new Menu(monde);
	}

}
