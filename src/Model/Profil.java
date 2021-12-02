package Model;

import java.io.Serializable;
/**Classe permettant de créer les profils
 * 
 * @author Fondateurs du Tower
 */
public class Profil implements Serializable, Comparable<Profil> {

	private String nom;
	private String prenom;
	private String pseudo;
	private String mail;
	private String pass;
	private boolean admin;
	private boolean ban;
	private int score;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Constructeur sans attributs
	 * Création du profil de base.
	 */
	public Profil () {
		this.pseudo = "Connectez-vous";
		this.score = 0;
	}

	/** 2eme Constructeur permettant de mettre en place le profil d'un utilisateur
	 * 
	 * @param nom
	 * @param prenom
	 * @param pseudo
	 * @param mail
	 * @param cs, mot de passe
	 */
	public Profil(String nom, String prenom, String pseudo, String mail, char[] cs) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setMail(mail);
		pass= ChartoString(cs);
		this.setPseudo(pseudo);
		this.admin = false;
	}

	// ----------------------------------- GETTERS && SETTERS && toString ----------------------------------------

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Profil [nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", mail=" + mail +
				", admin=" + admin + ", score=" + score + "]";
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}

	// --------------------------------------- METHODES ---------------------------------------------

	public void adminer () {
		admin = admin ? false: true;
	}

	public void ban () {
		ban = ban ? false: true;
	}

	/**Méthode abstraite de l'objet Comparable qu'il faut implémenter
	 * Méthode qui nous permet de comparer les scores des différents Joueurs afin de les classer
	 */
	@Override
	public int compareTo(Profil o) {
		Profil monprofil = (Profil)o ; 
		if (this.getScore() < monprofil.getScore()) {
			return 1;
		} else if (this.getScore() == monprofil.getScore()) {
			return 0;
		} else {
			return -1;
		}
	}

	/**Methode qui permet de transformer un tableau de char en String, toujours pour le mot de passe
	 * 
	 * @param tab, mot de passe
	 * @return String
	 */
	public String ChartoString( char[] tab) {
		String pass = null;
		for (char  c: tab) {
			pass = pass + Character.toString(c);
		}
		return pass;
	}
}
