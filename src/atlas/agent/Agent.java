package atlas.agent;

import atlas.agencies.Agence;
import atlas.missions.Mission;
import atlas.missions.Solution;

import java.util.ArrayList;

public class Agent {

	/**
	 * Nom de l'agent
	 */
	public String nom;

	/**
     * Prénom de l'agent
	 */
	public String prenom;

	/**
	 * Mission actuellement assign�e � l'agent
	 */
    public atlas.missions.Mission missionEnCours;

	/**
	 * Agence employant l'agent
	 */
    public atlas.agencies.Agence agence;


	/**
     * Missions déjà effectuées
	 */
	public ArrayList<Mission> missionReussies = new ArrayList<>();

	/**
     * Niveau d'expérience l'agent
	 */
	public int experience = 0;

    /**
     * Afficher les missions disponibles pour l'agent
     */
    public void afficherMissionsDisponibles() {
        System.out.println("------------------------------");
        System.out.println("Missions disponibles:");
        if (agence != null) {
            agence.afficherMissions();
        } else {
            System.out.println("Aucune, l'agent n'est pas employé par une agence.");
        }
        System.out.println("------------------------------");
    }

    /**
     * Permet à l'agent de rejoindre une mission par son code
     *
     * @param code
     */
    public void choisirMission(String code) {
        Mission m = null;
        for (Mission mission : agence.missions.values()) {
            if (mission.code.equalsIgnoreCase(code)) m = mission;
        }
        if (m == null) {
            System.err.println("La mission spécifiée n'est pas valide !");
            return;
        }
        missionEnCours = m;
        afficherMissionEnCours();
    }

    /**
     * Permet à l'agent de rejoindre une mission par son niveau de difficulté
     *
     * @param difficulte
     */
    public void choisirMission(int difficulte) {
        Mission m = agence.missions.get(difficulte);
        if (m == null) {
            System.err.println("La difficulté de votre mission n'est pas valide !");
            return;
        }
        missionEnCours = m;
        afficherMissionEnCours();
    }

    /**
     * Permet d'afficher les missions en cours de cet agent
     */
    public void afficherMissionEnCours() {
        if (missionEnCours != null) {
            System.out.println("------------------------------");
            System.out.println("Mission assignée : " + missionEnCours.code);
            System.out.println("Objectif : " + missionEnCours.objectif);
            System.out.println("------------------------------");

        } else {
            System.out.println("Aucune mission en cours.");
        }
    }

    /**
     * Afficher les informations de l'agent
     */
    public void afficherInformationsAgent() {
        System.out.println("------------------------------");
        System.out.println("Agent " + prenom + " " + nom);
        System.out.println("Agence actuelle : " + (agence != null ? agence.nom() : "Aucune"));
        System.out.println("Niveau d'expérience : " + experience);
        System.out.println("Mission assignée : " + (missionEnCours != null ? missionEnCours.code : "Aucune"));
        System.out.println("------------------------------");
    }

    /**
     * Faire rejoindre une agence à l'agent
     *
     * @param nomAgence
     */
    public void rejoindreAgence(String nomAgence) {
        for
                (Agence agence : Agence.agences) {
            if (agence.nom().equalsIgnoreCase(nomAgence)) this.agence = agence;
        }
        agence.rejoindreAgence(this);
    }

    /**
     * Accomplir la mission actuelle de l'agent en y spécifiant la solution
     *
     * @param solution Solution qui sera vérifiée
     */
    public void accomplirMission(Solution solution) {

        if (missionEnCours != null) {

            if (missionEnCours.testeSolution(solution, this)) {

                missionReussies.add(missionEnCours);
                missionEnCours = null;

                if (experience > 10000 && (agence == null || agence.nom().equals("Atlas"))) {

                    System.out.println("L'agence DGSE propose de vous recruter !");

                }
            }

        } else {

            System.err.println("Aucune mission en cours.");

        }

    }
}
