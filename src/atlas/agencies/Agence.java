package atlas.agencies;

import atlas.agent.Agent;
import atlas.missions.Mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Agence {

    /**
     * Identifiant de l'agence
     */
    public abstract int identifiant();

    /**
     * Nom de l'agence
     */
    public abstract String nom();

    /**
     * Agent recruté
     */
    private atlas.agent.Agent agentRecrute;

    /**
     * Missions disponibles dans cette agence par leur ordre de difficulté
     */
    public Map<Integer, Mission> missions;

    /**
     * Missions disponibles à la création de l'agence
     *
     * @return Missions disponibles
     */
    public abstract void missions();

    /**
     * Initialiser l'agence de base
     */
    public Agence() {
        missions = new HashMap<>();
        missions();
    }

    /**
     * Faire rejoindre un Agent à l'agence
     *
     * @param agent Agent souhaitant rejoindre l'agence
     */
    public void rejoindreAgence(Agent agent) {
        this.agentRecrute = agent;
    }

    /**
     * Afficher les missions disponibles dans l'agence dans la console
     */
    public void afficherMissions() {

        /*
        Lister toutes les missions dans la Map missions
         */
        for (Mission mission : missions.values()) {

            /*
            Une fois une mission récupérée sous la variable mission, afficher ses informations
             */

            System.out.println(mission.difficulte + " - " + mission.code + " - Fait: " + (agentRecrute.missionReussies.contains(mission) ? "Oui" : "Non"));

        }

    }

    /**
     * Ajouter une mission à l'agence
     *
     * @param difficulte Difficulté de cette mission
     * @param mission    Mission souhaitant être ajoutée
     */
    public void ajouterMission(int difficulte, Mission mission) {
        mission.difficulte = difficulte;
        missions.put(difficulte, mission);
    }

    public static List<Agence> agences = chargerAgences();

    private static List<Agence> chargerAgences() {

        List<Agence> agences;

        agences = new ArrayList<>();
        agences.add(new atlas.agencies.Atlas());
        agences.add(new DGSE());

        return agences;

    }
}
