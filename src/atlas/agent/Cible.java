package atlas.agent;

import java.util.ArrayList;

public class Cible {

    /**
     * Nom, prénom de la cible
     */
    public String nom, prenom;

    /**
     * Age
     */
    public int age;

    /**
     * Argent
     */
    public int argent;

    /**
     * Distance du lieu de déploiment de l'agent
     */
    public float distance;

    /**
     * Est-elle dangereuse ?
     */
    public boolean dangereux = false;

    /**
     * Les autres cibles que cette cible connait
     */
    public ArrayList<Cible> connaissances = new ArrayList<>();


    public void afficherInformations() {

        System.out.println("Informations de la cible nommée " + nom + " " + prenom);
        System.out.println("-> Age: " + age + " ans");
        System.out.println("-> Argent: " + argent + "€");
        System.out.println("-> Distance: " + distance + " km");
        System.out.println("-> Dangereuse: " + (dangereux ? "Oui" : "Non"));
        if (!connaissances.isEmpty()) {
            System.out.println("Connaissances :" + connaissances);
        } else {
            System.out.print("Cette cible ne connait aucune autre cible");
        }

    }

    @Override
    public String toString() {
        return nom;
    }
}
