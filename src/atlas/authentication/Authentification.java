package atlas.authentication;

import java.util.Random;

public class Authentification {

	private static String motDePasse = "";

	static {

        Random r = new Random();
		r.setSeed(System.currentTimeMillis());

        motDePasse = "";
        for (int i = 0; i < 5; i++) {

            motDePasse += (char) ('a' + r.nextInt(26));

        }

    }

    /**
     * Permet de vérifier si le mot de passe passé en paramètres est correct
     *
     * @param motDePasse_
     * @return TRUE ou FALSE si OUI ou NON
     */
    public static boolean testerMotDePasse(String motDePasse_) {

        return motDePasse.equals(motDePasse_);

    }
}
