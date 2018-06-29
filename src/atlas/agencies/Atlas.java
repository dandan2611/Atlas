package atlas.agencies;

import atlas.agent.Cible;
import atlas.missions.Mission;

import java.util.HashMap;

public class Atlas extends Agence {

    @Override
    public int identifiant() {
        return 0;
    }

    @Override
    public String nom() {
        return "Atlas";
    }

    @Override
    public void missions() {
        HashMap<Integer, Mission> missions = new HashMap();

        Mission ciblePrioritaire = new Mission(
                "Cible prioritaire",
                "Pour cette mission, il y a deux cibles. Renvoyer le nom de la cible la plus proche des deux.",
                2,
                mission -> {

                    return mission.cibles[0].distance < mission.cibles[1].distance ? mission.cibles[0].nom : mission.cibles[1].nom;

                }
        );

        Mission argentFacile = new Mission(
                "Argent facile",
                "Il y a trois cibles. Renvoyer le nom de la cible qui a le plus d'argent parmis les 3.",
                3,
                mission -> {

                    Cible meilleure = mission.cibles[0];

                    for (Cible cible : mission.cibles) {
                        if (cible.argent > meilleure.argent) meilleure = cible;
                    }

                    return meilleure.nom;

                }
        );

        Mission comptageDeRessources = new Mission(
                "Comptage de ressources",
                "Pour cette mission, il y a dix cibles. Donner la somme de l'argent possédé par les dix cibles.",
                10,
                mission -> {

                    int argent = 0;

                    for (Cible cible : mission.cibles) {
                        argent += cible.argent;
                    }

                    return argent;

                }
        );

        Mission ageMoyen = new Mission(
                "Âge moyen",
                "Pour cette mission, il y a dix cibles. Donner la moyenne d'age des cibles, arrondi au nombre entier inférieur.",
                10,
                mission -> {

                    int age = 0;

                    for (Cible c : mission.cibles) {

                        age += c.age;

                    }

                    return age / 10;

                }
        );

        Mission sagesseDesAnciens = new Mission(
                "La sagesse des anciens",
                "Pour cette mission, il y a 20 cibles. Donner le nombre de cibles qui ont 40 ans ou plus.",
                20,
                mission -> {

                    int nombre = 0;

                    for (Cible c : mission.cibles) {

                        if (c.age >= 40) {

                            nombre++;

                        }
                    }

                    return nombre;

                }
        );

        Mission menaceLointaine = new Mission(
                "Menace lointaine",
                "Atlas a identifié des cibles. Certaines sont marquées comme étant dangereuses. Donner la distance de la cible dangereuse qui est la plus éloignée.",
                20,
                mission -> {

                    float distanceMax = 0;

                    for (Cible c : mission.cibles) {

                        if (c.distance >= distanceMax && c.dangereux) {

                            distanceMax = c.distance;
                        }


                    }

                    return distanceMax;

                }
        );

        ajouterMission(1, ciblePrioritaire);
        ajouterMission(2, argentFacile);
        ajouterMission(3, comptageDeRessources);
        ajouterMission(4, ageMoyen);
        ajouterMission(5, sagesseDesAnciens);
        ajouterMission(6, menaceLointaine);

    }

}
