package controller;
/** Utilisation de l'API Java mail
 * On l'utilise pour envoyer nos mails
 * La Librairie externe est associée au projet via le Buildpath
 * Sur Geany on change la méthode de compilation.
 * 
 * @author Fondateur du Tower + API Java mail
 */
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;


public class Mail {

	/* Compte utilisé pour envoyer des mails.
	 */
	protected static String emailAccount = "INSATower@gmail.com";
	protected static String password = "Tower1234";

	/** Méthode permettant d'envoyer le message et de se connecter.
	 * 
	 * @param adresse, adresse mail du destinataire
	 * @param message, code d'activation
	 * @throws MessagingException , lève une exception en cas d'erreur.
	 */
	public static void envoiemail (String adresse, String message) throws MessagingException {
		Properties prop = new Properties();
		/**Différentes propriétés caractérisant la connexion au serveur gmail
		 * smtp permet de définir que l'adresse envoyant des mails est de type gmail
		 * On definit aussi les ports et les moyens et type de connexion
		 */
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");


		// Ouverture d'une session gmail sur le compte donné en attribut.
		Session session = Session.getInstance(prop , new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication () {
				return new PasswordAuthentication(emailAccount, password);
			}

		});

		Message msg = mess(session, adresse, message);
		Transport.send(msg);	
	}

	/**Méthode permettant de mettre en forme le message et de le paramétrer 
	 * 
	 * @param session
	 * @param adresse, du destinataire
	 * @param message , code d'activation
	 * @return le message 
	 */
	private static Message mess(Session session, String adresse, String message) {
		String saut = System.getProperty("line.separator");
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(emailAccount));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(adresse));
			msg.setSubject("INSATower Information");
			msg.setText("Bonjour," +saut + saut
					+ "Voici votre code d'activation: "+message+ saut+
					"Nous vous souhaitons un bon jeu." + saut+ saut+
					"Cordialement,"+saut+ saut+"Les Fondateurs de Tower");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return msg;
	}

}
