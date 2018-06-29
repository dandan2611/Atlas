package atlas.missions;

import atlas.agent.Agent;
import atlas.agent.Cible;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Mission {

	/**
     Difficulté de la mission, cette variable est également un identifiant
	 */
    public int difficulte;

	/**
	 Nom de code de la mission
	 */
	public String code;

	/**
	 Objectif
	 */
	public String objectif;

	/**
	 Données d'entrée
	 */
	public Cible[] cibles;
	public int nombreCibles = 10;

	/**
	 * Solution
	 */
	private Solution solutionPrevue;

	/**
	 * Créer une mission en initialisant une solution prévue par défaut
	 *
	 * @param code
	 * @param objectif
	 * @param nombreCibles
	 * @param solutionPrevue
	 */
	public Mission(String code, String objectif, int nombreCibles, Solution solutionPrevue) {
		this.code = code;
		this.objectif = objectif;
		this.nombreCibles = nombreCibles;
		this.solutionPrevue = solutionPrevue;

	}

	/**
	 * Cibles sauvegardées
	 */
	private Cible[] copiescibles;

	/**
	 * @param solution Tester une solution grâce a celle-ci donnée en paramètres
	 * @param agent Agent éxecutant cette solution
	 * @return Si la solution est valide ou non
	 */
	public boolean testeSolution(Solution solution, Agent agent) {
		System.out.println("------------------------------");
		System.out.println("Mission lancée : " + code);
		System.out.println("------------------------------");

		genererCibles();

		// On fait une sauvegarde des cibles au cas ou une solution modifie le tableau (exemple: programme de tri)
		copiescibles = new Cible[cibles.length];
		for(int i = 0; i<cibles.length; i++) {
			copiescibles[i] = cibles[i];
		}

		Object resultat = "";
		Object resultatAttendu = solutionPrevue.resoudre(this);

		cibles = copiescibles;

		boolean isResultatNumeriqueAttendu = false;
		double resultatNumeriqueAttendu = 0.0;

		if(resultatAttendu instanceof Integer) {

			isResultatNumeriqueAttendu = true;
			resultatNumeriqueAttendu = ((Integer)resultatAttendu).doubleValue();

		} else if(resultatAttendu instanceof Float) {

			isResultatNumeriqueAttendu = true;
			resultatNumeriqueAttendu = ((Float)resultatAttendu).doubleValue();

		} else if(resultatAttendu instanceof BigInteger) {

			isResultatNumeriqueAttendu = true;
			resultatNumeriqueAttendu = ((BigInteger)resultatAttendu).doubleValue();

		} else if(resultatAttendu instanceof Double) {

			isResultatNumeriqueAttendu = true;
			resultatNumeriqueAttendu = ((Double)resultatAttendu).doubleValue();

		} else if(resultatAttendu instanceof Long) {

			isResultatNumeriqueAttendu = true;
			resultatNumeriqueAttendu = ((Long)resultatAttendu).doubleValue();

		} else if(resultatAttendu instanceof Object[]) {

			String resultatTexte = "";

			for(Object c : ((Object[])resultatAttendu)) {

				resultatTexte+=c.toString()+"; ";

			}

			resultatAttendu = resultatTexte;

		} else if(resultatAttendu instanceof ArrayList) {

			String resultatTexte = "";

			for(Object c : ((ArrayList)resultatAttendu)) {

				resultatTexte+=c.toString()+"; ";

			}

			resultatAttendu = resultatTexte;

		}

		try {

			resultat = solution.resoudre(this);

			System.out.println("Résultat de la mission : " + resultat);

			if (isResultatNumeriqueAttendu) {

				double resultatNumerique = 0.0;

				try {

					resultatNumerique = Double.parseDouble(resultat.toString());

				} catch (Exception e) {
				}

				if (resultat instanceof Integer) {

					resultatNumerique = ((Integer) resultat).doubleValue();

				} else if (resultat instanceof Float) {

					resultatNumerique = ((Float) resultat).doubleValue();

				} else if (resultat instanceof BigInteger) {

					resultatNumerique = ((BigInteger) resultat).doubleValue();

				} else if (resultat instanceof Double) {

					resultatNumerique = ((Double) resultat).doubleValue();

				} else if (resultat instanceof Long) {

					resultatNumerique = ((Long) resultat).doubleValue();

				}
				if (resultatNumerique == resultatNumeriqueAttendu) {
					validerMission(agent);

					return true;
				}
			} else if (resultat != null && resultat instanceof Object[]) {

				String resultatTexte = "";

				for(Object c : ((Object[])resultat)) {

					resultatTexte+=c.toString()+"; ";

				}

				resultat = resultatTexte;

			} else if(resultat!=null && resultat instanceof ArrayList) {

				String resultatTexte = "";

				for(Object c : ((ArrayList)resultat)) {

					resultatTexte+=c.toString()+"; ";

				}
				resultat = resultatTexte;

			}
			if (resultat != null
					&& resultatAttendu.toString().trim().toLowerCase().equals(resultat.toString().toLowerCase().trim())) {
				validerMission(agent);

				return true;

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------");
		System.out.println("Mission échouée :(");
		System.out.println("Vous avez trouvé " + resultat + " mais il fallait trouver " + resultatAttendu);
		System.out.println("------------------------------");
		return false;
	}

	/**
	 * Valider une mission
	 * @param agent Agent accomplissant cette mission
	 */
	private void validerMission(Agent agent) {
		System.out.println("------------------------------");
		System.out.println("Mission réussie!");

		if (!agent.missionReussies.contains(this)) { // Si la mission n'a pas encore été faite, l'agent gagne de l'expérience

            System.out.println("L'agent " + agent.nom + " " + agent.prenom + " gagne " + (this.difficulte * 1000) + " d'expérience");
            agent.experience += this.difficulte * 1000;

		}

		System.out.println("------------------------------");

	}

	/**
	 * Générer toutes les cibles, avec 10 cibles de base
	 */
	private void genererCibles() {

		cibles = new Cible[nombreCibles];
		for (int i = 0; i < nombreCibles; i++) {

			cibles[i] = new Cible();
			cibles[i].nom = generateName();
			cibles[i].age = (Math.abs(rand.nextInt()) % 56) + 18;
			cibles[i].argent = (Math.abs(rand.nextInt()) % 5000000);
			cibles[i].distance = rand.nextFloat() * 6000f + 250;
			cibles[i].dangereux = rand.nextDouble() < 0.3;

		}

		for (int i = 0; i < nombreCibles; i++) {

			int nbConnaissances = Math.abs(rand.nextInt()) % (nombreCibles / 5 + 1);
			for (int j = 0; j < nbConnaissances; j++) {
				int c = Math.abs(rand.nextInt())%nombreCibles;
				if(i!=c && !cibles[i].connaissances.contains(cibles[c])) {
					cibles[i].connaissances.add(cibles[c]);
					cibles[c].connaissances.add(cibles[i]);
				}
			}
			System.out.println("Cible " + i + ": ");
			cibles[i].afficherInformations();

		}
		System.out.println("------------------------------");

	}

    /*
    Partie non documentée :
    Générateur de nom d'agent / agence
     */

	private static String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
			"Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
			"Zork", "Mad", "Cry", "Zur", "Creo", "Azur", "Rei", "Cro",
			"Mar", "Luk", "Ad", "Ae", "Ara", "Bal", "Bei", "Bi", "Bry", "Cai", "Car", "Chae", "Cra", "Da",
			"Dae", "Dor", "Eil", "El", "Ela", "En", "Er", "Fa", "Fae", "Far", "Fen", "Gen", "Gil", "Glyn",
			"Gre", "Hei", "Hele", "Her", "Ian", "Iar", "Ili", "Ina", "Jo", "Kea", "Kel", "Key",
			"Kris", "Leo", "Lia", "Lora", "Lu", "Mag", "Mia", "Mor", "Nae", "Neri", "Nor", "Ola",
			"Olo", "Oma", "Ori", "Pa", "Per", "Pet", "Phi", "Pres", "Qi", "Qin", "Qui",
			"Rey", "Ro", "Sar", "Sha", "Syl", "The", "Tor", "Tra", "Tris", "Ula", "Ume", "Uri", "Va",
			"Val", "Ven", "Vir", "Waes", "Wran", "Wyn",
			"Xyr", "Yel", "Yes", "Yin", "Zin", "Zum", "Zyl"};

	private static String[] Middle = { "air", "ir", "mit", "sor", "meel", "clo",
		"red", "cran", "ark", "arc", "mirid", "loris", "cres", "mur", "zer",
		"marac", "zoir", "h", "d", "z", "g", "m", "n", "salmar", "urak",
		"balar","ban","bel","ber","can","car","ceran","cyn","dan","di","dith",
		"dov","faren","fiel","fin","fir","geir","gel","golor","gwyn","han","hari",
		"hice","horn","jeon","jor","jyr","kal","kas","kian","krana","lam","lan","lar",
		"lee","len","leth","lynn","maer","mar","men","moir","myar","mys","na","nal","nan",
		"ral","ran","rel","ren","ric","ri","rieth","ris","ro","ron","rora","ror","salor",
		"sand","satr","stin","sys","than","thyr","tor","tris","tum","val","var",
		"ynor","yr","zana","zeir","zorw","zum"
	};

	private static String[] End = { "id", "ed", "ah", "arc", "es", "er", "en", "io", "a", "is", "in", "os", "in", "yn", "yh", "y", "as", "ean",
		"it", "ed", "ure", "azur", "ic", "an", "e", "eh", "i", "ih", "o", "oh", "ice", "ace", "oce", "u", "uh" };

	private static Random rand = new Random();


	private static String generateName() {

		String name = Beginning[rand.nextInt(Beginning.length)] + Middle[rand.nextInt(Middle.length)];

		if (name.length() < 8 && Math.random() < 0.5) {

			name += End[rand.nextInt(End.length)];

		}

		return name;

	}
}
