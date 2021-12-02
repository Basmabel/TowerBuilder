package controller;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Music {

	private static String chemins;
	public Clip clip;
	
	
    /** Constructeur de classe Musique
     * 
     * @param chemin, chemin du fichier musique dans le dossier Ressources.
     */
	public Music(String chemin) {
		chemins = chemin;
		File musicPath = new File (chemins);
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
		}
		catch (Exception e ) {
			e.printStackTrace();
		}

	}

	/**Methode permettant le lancement de la musique une seule fois	 */
	public void start() {
		clip.start();

	}

	/**Méthode permettant de lancer la musique en Boucle */
	public void startLoop() {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);

	}

	/** Méthode permettant de stoper la musique */
	public void stop() {
		clip.stop();
	}



}


