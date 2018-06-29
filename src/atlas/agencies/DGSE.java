package atlas.agencies;

import atlas.agent.Cible;
import atlas.authentication.Authentification;
import atlas.missions.Mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DGSE extends Agence {

    @Override
    public int identifiant() {
        return 1;
    }

    @Override
    public String nom() {
        return "DGSE";
    }

    @Override
    public void missions() {
        HashMap<Integer, Mission> missions = new HashMap();

        Mission etudeDemographique = new Mission(
                "Etude démographique",
                "Donner la différence d'age entre la cible la plus jeune et la plus agée.",
                10,
                mission -> {

                    int ageMin = 1000;
                    int ageMax = 0;

                    for (Cible c : mission.cibles) {

                        if (c.age < ageMin) {

                            ageMin = c.age;

                        }

                        if (c.age > ageMax) {

                            ageMax = c.age;

                        }

                    }

                    return ageMax - ageMin;

                }
        );

        Mission mauvaisesFrequentations = new Mission(
                "Mauvaises fréquentations",
                "Donner le nombre de cibles qui connaissent au moins 1 cible marquée comme étant dangereuse.",
                new Random().nextInt(15) + 5,
                mission -> {

                    int nombre = 0;

                    for (Cible c : mission.cibles) {

                        for (Cible c2 : c.connaissances) {

                            if (c2.dangereux) {

                                nombre++;
                                break;

                            }

                        }

                    }

                    return nombre;

                }
        );

        Mission cibleUltraPrioritaire = new Mission(
                "Cible ultra prioritaire",
                "Donner le nom de la cible qui connait le plus de cibles marquées comme étant dangereuses.",
                new Random().nextInt(200) + 100,
                mission -> {

                    int nombreMax = 0;
                    String nom = "";

                    for (Cible c : mission.cibles) {

                        int nombre = 0;

                        for (Cible c2 : c.connaissances) {

                            if (c2.dangereux) {

                                nombre++;

                            }
                        }

                        if (nombre >= nombreMax) {

                            nombreMax = nombre;
                            nom = c.nom;

                        }

                    }

                    return nom;

                }
        );

        Mission classementDesFortunes = new Mission(
                "Classement des fortunes",
                "Il y a 10 cibles. Votre mission est de donner les cibles dans l'ordre de richesse, en partant de ceux ayant le moins d'argent. Renvoyer un tableau Cible[10]. \nIndication : Utiliser le tri à bulle",
                10,
                mission -> {

                    for (int i = mission.cibles.length; i > 1; i--) {

                        for (int j = 0; j < i - 1; j++) {

                            if (mission.cibles[j + 1].argent < mission.cibles[j].argent) {

                                Cible echange = mission.cibles[j + 1];
                                mission.cibles[j + 1] = mission.cibles[j];
                                mission.cibles[j] = echange;

                            }

                        }

                    }


                    return mission.cibles;

                }
        );

        Mission personnesCles = new Mission(
                "Personnes clés",
                "Il y a 20 cibles. Si on espionne une cible, on a automatiquement aussi des informations sur les cibles qu'elle connait.\nPour utiliser au mieux nos 3 agents de terrain disponibles, on va espionner seulement 3 cibles qu'on choisira au mieux pour qu'à 3 elles connaissent le plus de personnes différentes.\nDonner en résultat le nombre de cibles différentes sur lesquelles on obtiendra ainsi des informations, en ayant bien choisi les 3 cibles pour que ce soit le nombre maximal.",
                20,
                mission -> {

                    ArrayList<Cible> ciblesCouvertes = new ArrayList<>();
                    int nombreMaxCiblesCouvertes = 0;

                    for (Cible c1 : mission.cibles) {
                        for (Cible c2 : mission.cibles) {
                            for (Cible c3 : mission.cibles) {
                                if (c1 != c2 && c2 != c3) {
                                    ciblesCouvertes.clear();
                                    ciblesCouvertes.add(c1);
                                    ciblesCouvertes.add(c2);
                                    ciblesCouvertes.add(c3);

                                    for (Cible connaissance1 : c1.connaissances) {
                                        if (!ciblesCouvertes.contains(connaissance1))
                                            ciblesCouvertes.add(connaissance1);
                                    }

                                    for (Cible connaissance2 : c2.connaissances) {
                                        if (!ciblesCouvertes.contains(connaissance2))
                                            ciblesCouvertes.add(connaissance2);
                                    }

                                    for (Cible connaissance3 : c3.connaissances) {
                                        if (!ciblesCouvertes.contains(connaissance3))
                                            ciblesCouvertes.add(connaissance3);
                                    }

                                    if (nombreMaxCiblesCouvertes < ciblesCouvertes.size())
                                        nombreMaxCiblesCouvertes = ciblesCouvertes.size();
                                }
                            }
                        }
                    }

                    return nombreMaxCiblesCouvertes;

                }
        );

        Mission crackerLeMotDePasse = new Mission(
                "Cracker le mot de passe",
                "Trouver le mot de passe des systèmes informatiques de l'agence ennemie. Il est composé de 5 lettres en minuscule.\nExemple : azert\nPour tester si le mot de passe est correct, utiliser Authentification.testerMotDePasse(String motDePasse) qui renvoie true si c'est le bon mot de passe.",
                0,
                mission -> {

                    String chars = "abcdefghijklmnopqrstuvwxyz";
                    String motDePasse = "";

                    while (!Authentification.testerMotDePasse(motDePasse)) {
                        for (int i = 0; i < 5; i++) {

                            motDePasse += chars.charAt(new Random().nextInt(chars.length()) - 1);

                        }

                    }

                    return motDePasse;

                }
        );

        ajouterMission(7, etudeDemographique);
        ajouterMission(8, mauvaisesFrequentations);
        ajouterMission(9, cibleUltraPrioritaire);
        ajouterMission(10, classementDesFortunes);
        ajouterMission(11, personnesCles);
        ajouterMission(12, crackerLeMotDePasse);
    }

}
